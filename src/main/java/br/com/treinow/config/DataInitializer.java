package br.com.treinow.config;

import br.com.treinow.models.entities.PermissionEntity;
import br.com.treinow.models.entities.RoleEntity;
import br.com.treinow.repositories.jpa.PermissionRepository;
import br.com.treinow.repositories.jpa.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public void run(String... args) throws Exception {
        // Se já houver roles, não faz nada para evitar duplicação.
        if (roleRepository.count() > 0) {
            return;
        }

        // PASSO 1: Criar todas as permissões usando o construtor vazio e setters
        // -- Alunos/Membros
        PermissionEntity alunoCreate = new PermissionEntity();
        alunoCreate.setName("ALUNO_CREATE");
        alunoCreate.setDescription("Permite registrar novos alunos");

        PermissionEntity alunoUpdate = new PermissionEntity();
        alunoUpdate.setName("ALUNO_UPDATE");
        alunoUpdate.setDescription("Permite atualizar dados cadastrais de alunos");

        PermissionEntity alunoReadAll = new PermissionEntity();
        alunoReadAll.setName("ALUNO_READ_ALL");
        alunoReadAll.setDescription("Permite listar e buscar todos os alunos");

        PermissionEntity alunoHistoricoRead = new PermissionEntity();
        alunoHistoricoRead.setName("ALUNO_HISTORICO_READ");
        alunoHistoricoRead.setDescription("Permite verificar o histórico e evolução de um aluno");

        // -- Perfil do Próprio Usuário
        PermissionEntity profileUpdateOwn = new PermissionEntity();
        profileUpdateOwn.setName("PROFILE_UPDATE_OWN");
        profileUpdateOwn.setDescription("Permite que o usuário atualize seu próprio perfil");

        // -- Treinos e Exercícios
        PermissionEntity treinoCreate = new PermissionEntity();
        treinoCreate.setName("TREINO_CREATE");
        treinoCreate.setDescription("Permite criar e montar fichas de treino para alunos");

        PermissionEntity treinoUpdate = new PermissionEntity();
        treinoUpdate.setName("TREINO_UPDATE");
        treinoUpdate.setDescription("Permite atualizar fichas de treino de alunos");

        PermissionEntity treinoReadOwn = new PermissionEntity();
        treinoReadOwn.setName("TREINO_READ_OWN");
        treinoReadOwn.setDescription("Permite que o aluno consulte sua própria ficha de treino");

        PermissionEntity exercicioRead = new PermissionEntity();
        exercicioRead.setName("EXERCICIO_READ");
        exercicioRead.setDescription("Permite consultar a base de exercícios");

        // -- Operações Diárias
        PermissionEntity checkinCreate = new PermissionEntity();
        checkinCreate.setName("CHECKIN_CREATE");
        checkinCreate.setDescription("Permite realizar o check-in de um aluno");

        PermissionEntity catracaManage = new PermissionEntity();
        catracaManage.setName("CATRACA_MANAGE");
        catracaManage.setDescription("Permite controlar o acesso e liberação da catraca");

        PermissionEntity aulaSchedule = new PermissionEntity();
        aulaSchedule.setName("AULA_SCHEDULE");
        aulaSchedule.setDescription("Permite agendar aulas e horários de treino");

        PermissionEntity avaliacaoFisicaSchedule = new PermissionEntity();
        avaliacaoFisicaSchedule.setName("AVALIACAO_FISICA_SCHEDULE");
        avaliacaoFisicaSchedule.setDescription("Permite agendar avaliações físicas");

        PermissionEntity presencaManage = new PermissionEntity();
        presencaManage.setName("PRESENCA_MANAGE");
        presencaManage.setDescription("Permite registrar presença de alunos em aulas ou treinos");

        // -- Financeiro e Vendas
        PermissionEntity planoSell = new PermissionEntity();
        planoSell.setName("PLANO_SELL");
        planoSell.setDescription("Permite vender planos e realizar matrículas");

        PermissionEntity pagamentoReceive = new PermissionEntity();
        pagamentoReceive.setName("PAGAMENTO_RECEIVE");
        pagamentoReceive.setDescription("Permite receber pagamentos de mensalidades");

        PermissionEntity pagamentoCreate = new PermissionEntity();
        pagamentoCreate.setName("PAGAMENTO_CREATE");
        pagamentoCreate.setDescription("Permite lançar pagamentos e emitir recibos");

        PermissionEntity contratoDiscountApprove = new PermissionEntity();
        contratoDiscountApprove.setName("CONTRATO_DISCOUNT_APPROVE");
        contratoDiscountApprove.setDescription("Permite aprovar descontos e alterações em contratos");

        PermissionEntity contratoUpdate = new PermissionEntity();
        contratoUpdate.setName("CONTRATO_UPDATE");
        contratoUpdate.setDescription("Permite alterar contratos");

        PermissionEntity inadimplenciaRead = new PermissionEntity();
        inadimplenciaRead.setName("INADIMPLENCIA_READ");
        inadimplenciaRead.setDescription("Permite monitorar a inadimplência");

        PermissionEntity inadimplenciaNotify = new PermissionEntity();
        inadimplenciaNotify.setName("INADIMPLENCIA_NOTIFY");
        inadimplenciaNotify.setDescription("Permite notificar inadimplentes e gerar cobranças");

        PermissionEntity boletoCreate = new PermissionEntity();
        boletoCreate.setName("BOLETO_CREATE");
        boletoCreate.setDescription("Permite gerar boletos");

        PermissionEntity financeiroConciliar = new PermissionEntity();
        financeiroConciliar.setName("FINANCEIRO_CONCILIAR");
        financeiroConciliar.setDescription("Permite conciliar movimentações financeiras");

        // -- Relatórios e Gestão
        PermissionEntity reportFrequencyReadBasic = new PermissionEntity();
        reportFrequencyReadBasic.setName("REPORT_FREQUENCY_READ_BASIC");
        reportFrequencyReadBasic.setDescription("Permite emitir relatórios básicos de frequência");

        PermissionEntity reportFrequencyReadFull = new PermissionEntity();
        reportFrequencyReadFull.setName("REPORT_FREQUENCY_READ_FULL");
        reportFrequencyReadFull.setDescription("Permite acompanhar métricas detalhadas de frequência");

        PermissionEntity reportVendasRead = new PermissionEntity();
        reportVendasRead.setName("REPORT_VENDAS_READ");
        reportVendasRead.setDescription("Permite acompanhar metas de vendas");

        PermissionEntity reportFaturamentoRead = new PermissionEntity();
        reportFaturamentoRead.setName("REPORT_FATURAMENTO_READ");
        reportFaturamentoRead.setDescription("Permite emitir relatórios de faturamento");

        PermissionEntity funcionarioHorarioManage = new PermissionEntity();
        funcionarioHorarioManage.setName("FUNCIONARIO_HORARIO_MANAGE");
        funcionarioHorarioManage.setDescription("Permite gerenciar horários de funcionários e instrutores");

        // -- Administração do Sistema
        PermissionEntity roleManage = new PermissionEntity();
        roleManage.setName("ROLE_MANAGE");
        roleManage.setDescription("Permite gerenciar roles e suas permissões");

        PermissionEntity userManageStaff = new PermissionEntity();
        userManageStaff.setName("USER_MANAGE_STAFF");
        userManageStaff.setDescription("Permite criar e gerenciar usuários da equipe (funcionários)");

        List<PermissionEntity> allPermissions = Arrays.asList(
                alunoCreate, alunoUpdate, alunoReadAll, alunoHistoricoRead, profileUpdateOwn, treinoCreate,
                treinoUpdate, treinoReadOwn, exercicioRead, checkinCreate, catracaManage, aulaSchedule,
                avaliacaoFisicaSchedule, presencaManage, planoSell, pagamentoReceive, pagamentoCreate,
                contratoDiscountApprove, contratoUpdate, inadimplenciaRead, inadimplenciaNotify, boletoCreate,
                financeiroConciliar, reportFrequencyReadBasic, reportFrequencyReadFull, reportVendasRead,
                reportFaturamentoRead, funcionarioHorarioManage, roleManage, userManageStaff
        );
        permissionRepository.saveAll(allPermissions);

        // PASSO 2: Criar as Roles e associar as permissões
        RoleEntity alunoRole = new RoleEntity();
        alunoRole.setName("ROLE_ALUNO");
        alunoRole.setDescription("Aluno / Membro da academia");
        alunoRole.setPermissions(new HashSet<>(Arrays.asList(profileUpdateOwn, treinoReadOwn, exercicioRead)));

        RoleEntity recepcionistaRole = new RoleEntity();
        recepcionistaRole.setName("ROLE_RECEPCIONISTA");
        recepcionistaRole.setDescription("Recepcionista / Atendente da academia");
        recepcionistaRole.setPermissions(new HashSet<>(Arrays.asList(alunoCreate, alunoUpdate, checkinCreate, catracaManage, aulaSchedule, reportFrequencyReadBasic, planoSell, pagamentoReceive, avaliacaoFisicaSchedule)));

        RoleEntity coordenadorRole = new RoleEntity();
        coordenadorRole.setName("ROLE_COORDENADOR");
        coordenadorRole.setDescription("Coordenador / Gerente da academia");
        coordenadorRole.setPermissions(new HashSet<>(Arrays.asList(contratoDiscountApprove, contratoUpdate, inadimplenciaRead, inadimplenciaNotify, reportVendasRead, reportFrequencyReadFull, funcionarioHorarioManage)));

        RoleEntity instrutorRole = new RoleEntity();
        instrutorRole.setName("ROLE_INSTRUTOR");
        instrutorRole.setDescription("Instrutor / Personal Trainer");
        instrutorRole.setPermissions(new HashSet<>(Arrays.asList(treinoCreate, treinoUpdate, presencaManage, alunoHistoricoRead, avaliacaoFisicaSchedule)));

        RoleEntity financeiroRole = new RoleEntity();
        financeiroRole.setName("ROLE_FINANCEIRO");
        financeiroRole.setDescription("Responsável Financeiro / Administrativo");
        financeiroRole.setPermissions(new HashSet<>(Arrays.asList(pagamentoCreate, boletoCreate, inadimplenciaNotify, financeiroConciliar, reportFaturamentoRead)));

        RoleEntity adminRole = new RoleEntity();
        adminRole.setName("ROLE_ADMIN");
        adminRole.setDescription("Administrador com acesso total ao sistema");
        adminRole.setPermissions(new HashSet<>(allPermissions));

        roleRepository.saveAll(Arrays.asList(alunoRole, recepcionistaRole, coordenadorRole, instrutorRole, financeiroRole, adminRole));

        System.out.println(">>> Dados iniciais de Roles e Permissions inseridos com sucesso via DataInitializer! <<<");
    }
}