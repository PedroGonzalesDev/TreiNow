package br.com.treinow.config;

import br.com.treinow.models.entities.PermissionEntity;
import br.com.treinow.models.entities.RoleEntity;
import br.com.treinow.repositories.jpa.PermissionRepository;
import br.com.treinow.repositories.jpa.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        if (roleRepository.count() > 0) {
            return;
        }

        //Criar todas as entidades de Permissão em memória.
        List<PermissionEntity> allPermissions = Arrays.asList(
                // Alunos, Endereço e Perfil
                createPermission("ALUNO_CREATE", "Permite registrar novos alunos"),
                createPermission("ALUNO_UPDATE", "Permite atualizar dados cadastrais de alunos"),
                createPermission("ALUNO_READ_ALL", "Permite listar e buscar todos os alunos"),
                createPermission("ALUNO_HISTORICO_READ", "Permite verificar o histórico e evolução de um aluno"),
                createPermission("ADDRESS_CREATE", "Permite cadastrar o endereço do usuario"),
                createPermission("ADDRESS_READ", "Permite buscar todos os endereços cadastrados"),
                createPermission("ADDRESS_UPDATE", "Permite atualizar o endereço de um usuario"),
                createPermission("PROFILE_UPDATE_OWN", "Permite que o usuário atualize seu próprio perfil"),
                // Planos (Membership)
                createPermission("MANAGE_MEMBERSHIP", "Permite criar e atualizar planos da academia"),
                createPermission("MEMBERSHIP_READ", "Permite listar todos os planos da academia"),
                // Treinos
                createPermission("TREINO_CREATE", "Permite criar e montar fichas de treino para alunos"),
                createPermission("TREINO_UPDATE", "Permite atualizar fichas de treino de alunos"),
                createPermission("TREINO_READ_OWN", "Permite que o aluno consulte sua própria ficha de treino"),
                createPermission("TREINO_READ", "Permite consultar as tabelas de exercicio"),
                createPermission("TREINO_DELETE", "Permite deletar treinos"),
                // Exercícios
                createPermission("EXERCICIO_CREATE", "Permite criar novos exercícios na biblioteca"),
                createPermission("EXERCICIO_READ", "Permite consultar todos os exercícios da biblioteca"),
                createPermission("EXERCICIO_UPDATE", "Permite atualizar exercícios da biblioteca"),
                createPermission("EXERCICIO_DELETE", "Permite deletar exercícios da biblioteca"),
                // Atributos (Categorias, Músculos)
                createPermission("ATRIBUTOS_CREATE", "Permite criar novos grupos musculares e categorias"),
                createPermission("ATRIBUTOS_READ", "Permite consultar grupos musculares e categorias"),
                createPermission("ATRIBUTOS_UPDATE", "Permite atualizar grupos musculares e categorias"),
                createPermission("ATRIBUTOS_DELETE", "Permite deletar grupos musculares e categorias"),
                // Operações Diárias
                createPermission("CHECKIN_CREATE", "Permite realizar o check-in de um aluno"),
                createPermission("CATRACA_MANAGE", "Permite controlar o acesso e liberação da catraca"),
                createPermission("AULA_SCHEDULE", "Permite agendar aulas e horários de treino"),
                createPermission("AVALIACAO_FISICA_SCHEDULE", "Permite agendar avaliações físicas"),
                createPermission("PRESENCA_MANAGE", "Permite registrar presença de alunos em aulas ou treinos"),
                // Financeiro e Vendas
                createPermission("PLANO_SELL", "Permite vender planos e realizar matrículas"),
                createPermission("PAGAMENTO_RECEIVE", "Permite receber pagamentos de mensalidades"),
                createPermission("PAGAMENTO_CREATE", "Permite lançar pagamentos e emitir recibos"),
                createPermission("CONTRATO_DISCOUNT_APPROVE", "Permite aprovar descontos e alterações em contratos"),
                createPermission("CONTRATO_UPDATE", "Permite alterar contratos"),
                createPermission("INADIMPLENCIA_READ", "Permite monitorar a inadimplência"),
                createPermission("INADIMPLENCIA_NOTIFY", "Permite notificar inadimplentes e gerar cobranças"),
                createPermission("BOLETO_CREATE", "Permite gerar boletos"),
                createPermission("FINANCEIRO_CONCILIAR", "Permite conciliar movimentações financeiras"),
                // Relatórios e Gestão
                createPermission("REPORT_FREQUENCY_READ_BASIC", "Permite emitir relatórios básicos de frequência"),
                createPermission("REPORT_FREQUENCY_READ_FULL", "Permite acompanhar métricas detalhadas de frequência"),
                createPermission("REPORT_VENDAS_READ", "Permite acompanhar metas de vendas"),
                createPermission("REPORT_FATURAMENTO_READ", "Permite emitir relatórios de faturamento"),
                createPermission("FUNCIONARIO_HORARIO_MANAGE", "Permite gerenciar horários de funcionários e instrutores"),
                // Administração do Sistema
                createPermission("ROLE_MANAGE", "Permite gerenciar roles e suas permissões"),
                createPermission("USER_MANAGE_STAFF", "Permite criar e gerenciar usuários da equipe (funcionários)")
        );
        permissionRepository.saveAll(allPermissions);

        // Para facilitar a busca, criamos um mapa de nome -> permissão
        Map<String, PermissionEntity> permissionMap = allPermissions.stream()
                .collect(Collectors.toMap(PermissionEntity::getName, p -> p));

        //Criar as Roles
        RoleEntity alunoRole = new RoleEntity("ROLE_ALUNO", "Aluno / Membro da academia");
        RoleEntity recepcionistaRole = new RoleEntity("ROLE_RECEPCIONISTA", "Recepcionista / Atendente da academia");
        RoleEntity coordenadorRole = new RoleEntity("ROLE_COORDENADOR", "Coordenador / Gerente da academia");
        RoleEntity instrutorRole = new RoleEntity("ROLE_INSTRUTOR", "Instrutor / Personal Trainer");
        RoleEntity financeiroRole = new RoleEntity("ROLE_FINANCEIRO", "Responsável Financeiro / Administrativo");
        RoleEntity adminRole = new RoleEntity("ROLE_ADMIN", "Administrador com acesso total ao sistema");

        //Associar as permissões às roles
        adminRole.setPermissions(new HashSet<>(allPermissions));

        alunoRole.setPermissions(getPermissionsFromMap(permissionMap, "PROFILE_UPDATE_OWN", "TREINO_READ_OWN", "TREINO_READ"));

        recepcionistaRole.setPermissions(getPermissionsFromMap(permissionMap,
                "ALUNO_CREATE", "ALUNO_UPDATE", "ALUNO_READ_ALL", "CHECKIN_CREATE", "CATRACA_MANAGE",
                "AULA_SCHEDULE", "REPORT_FREQUENCY_READ_BASIC", "PLANO_SELL", "PAGAMENTO_RECEIVE",
                "AVALIACAO_FISICA_SCHEDULE", "ADDRESS_CREATE", "ADDRESS_READ", "MEMBERSHIP_READ"));

        coordenadorRole.setPermissions(getPermissionsFromMap(permissionMap,
                "CONTRATO_DISCOUNT_APPROVE", "CONTRATO_UPDATE", "INADIMPLENCIA_READ", "INADIMPLENCIA_NOTIFY",
                "REPORT_VENDAS_READ", "REPORT_FREQUENCY_READ_FULL", "FUNCIONARIO_HORARIO_MANAGE",
                "ADDRESS_CREATE", "ADDRESS_READ", "ALUNO_CREATE", "ALUNO_UPDATE", "ALUNO_READ_ALL",
                "USER_MANAGE_STAFF", "MANAGE_MEMBERSHIP", "MEMBERSHIP_READ"));

        instrutorRole.setPermissions(getPermissionsFromMap(permissionMap,
                "TREINO_CREATE", "TREINO_UPDATE", "PRESENCA_MANAGE", "ALUNO_HISTORICO_READ",
                "AVALIACAO_FISICA_SCHEDULE", "TREINO_DELETE", "EXERCICIO_CREATE", "EXERCICIO_READ",
                "EXERCICIO_UPDATE", "EXERCICIO_DELETE", "ATRIBUTOS_CREATE", "ATRIBUTOS_READ",
                "ATRIBUTOS_UPDATE", "ATRIBUTOS_DELETE"));

        financeiroRole.setPermissions(getPermissionsFromMap(permissionMap,
                "PAGAMENTO_CREATE", "BOLETO_CREATE", "INADIMPLENCIA_NOTIFY", "FINANCEIRO_CONCILIAR",
                "REPORT_FATURAMENTO_READ"));

        // Salvar todas as roles com suas permissões já associadas.
        roleRepository.saveAll(Arrays.asList(alunoRole, recepcionistaRole, coordenadorRole, instrutorRole, financeiroRole, adminRole));

        System.out.println(">>> Dados iniciais de Roles e Permissions inseridos com sucesso! <<<");
    }

    // Mét0do auxiliar para criar permissões sem salvá-las imediatamente
    private PermissionEntity createPermission(String name, String description) {
        PermissionEntity permission = new PermissionEntity();
        permission.setName(name);
        permission.setDescription(description);
        return permission;
    }

    // Mét0do auxiliar para buscar um conjunto de permissões do mapa de forma segura
    private Set<PermissionEntity> getPermissionsFromMap(Map<String, PermissionEntity> map, String... names) {
        return Arrays.stream(names)
                .map(name -> map.get(name))
                .collect(Collectors.toSet());
    }
}