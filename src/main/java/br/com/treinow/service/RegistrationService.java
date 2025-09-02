package br.com.treinow.service;

import br.com.treinow.dtos.CreateMemberDto;
import br.com.treinow.exceptions.BusinessException;
import br.com.treinow.models.entities.*;
import br.com.treinow.models.entities.enums.UserStatus;
import br.com.treinow.repositories.jpa.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class RegistrationService {

    @Autowired  private UserRepository userRepository;
    @Autowired  private MemberRepository memberRepository;
    @Autowired  private MembershipRepository membershipRepository;
    @Autowired  private PasswordEncoder passwordEncoder;
    @Autowired private EmailService emailService;
    @Autowired private RoleRepository roleRepository;


    //Funcionario cadastra um novo membro
    @Transactional
    public void registerByStaff(CreateMemberDto createMemberDto){

        if(userRepository.existsByEmail(createMemberDto.userInfoDto().email())){
            throw new BusinessException("E-mail already in use");
        }

        String temporaryPassword = generateRandomPassword();

        UserEntity newUser = new UserEntity();
        newUser.setName(createMemberDto.userInfoDto().name());
        newUser.setEmail(createMemberDto.userInfoDto().email());
        newUser.setCpf(createMemberDto.userInfoDto().cpf());
        newUser.setPhone(createMemberDto.userInfoDto().phone());
        newUser.setPassword(passwordEncoder.encode(temporaryPassword));
        newUser.setStatus(UserStatus.ACTIVE);
        newUser.setForcePasswordChange(true);
        AddressEntity address = new AddressEntity();
        address.setStreet(createMemberDto.addressInfoDto().street());
        address.setNumber(createMemberDto.addressInfoDto().number());
        address.setComplement(createMemberDto.addressInfoDto().complement());
        address.setCity(createMemberDto.addressInfoDto().city());
        address.setState(createMemberDto.addressInfoDto().state());
        address.setZipCode(createMemberDto.addressInfoDto().zipCode());
        newUser.setAddress(address);
        RoleEntity alunoRole = roleRepository.findByName("ROLE_ALUNO")
                .orElseThrow(() -> new RuntimeException("Erro crítico: A Role 'ROLE_ALUNO' não foi encontrada no banco. Verifique o DataInitializer."));
        newUser.setRole(alunoRole);
        UserEntity savedUser = userRepository.save(newUser);
        createAndSaveMember(createMemberDto, savedUser);

        emailService.sendWelcomeWithTemporaryPassword(newUser.getEmail(), newUser.getName(), temporaryPassword);
    }

    @Transactional
    public void selfRegister(CreateMemberDto createMemberDto){

        if(userRepository.existsByEmail(createMemberDto.userInfoDto().email())){
            throw new BusinessException("E-mail already in use");
        }

        String activationToken = UUID.randomUUID().toString();

        UserEntity newUser = new UserEntity();
        newUser.setName(createMemberDto.userInfoDto().name());
        newUser.setEmail(createMemberDto.userInfoDto().email());
        newUser.setCpf(createMemberDto.userInfoDto().cpf());
        newUser.setPhone(createMemberDto.userInfoDto().phone());
        newUser.setPassword(null);
        newUser.setStatus(UserStatus.PENDING_SETUP);
        newUser.setActivationToken(activationToken);
        newUser.setTokenExpiryDate(LocalDateTime.now().plusHours(24));
        AddressEntity address = new AddressEntity();
        address.setStreet(createMemberDto.addressInfoDto().street());
        address.setNumber(createMemberDto.addressInfoDto().number());
        address.setComplement(createMemberDto.addressInfoDto().complement());
        address.setCity(createMemberDto.addressInfoDto().city());
        address.setState(createMemberDto.addressInfoDto().state());
        address.setZipCode(createMemberDto.addressInfoDto().zipCode());
        newUser.setAddress(address);

        RoleEntity alunoRole = roleRepository.findByName("ROLE_ALUNO")
                .orElseThrow(() -> new RuntimeException("Erro crítico: A Role 'ROLE_ALUNO' não foi encontrada no banco. Verifique o DataInitializer."));
        newUser.setRole(alunoRole);

        UserEntity savedUser = userRepository.save(newUser);
        createAndSaveMember(createMemberDto, savedUser);

        String setPasswordLink = "https://localhost:8080/auth/set-password?token=" + activationToken;
        emailService.sendCompleteRegistrationEmail(newUser.getEmail(), newUser.getName(), setPasswordLink);
    }

    private void createAndSaveMember(CreateMemberDto createMemberDto, UserEntity userEntity){
        if(createMemberDto.membershipId() != null) {
            MembershipEntity plan = membershipRepository.findById(createMemberDto.membershipId())
                    .orElseThrow(() -> new RuntimeException("Plan not found"));

            LocalDate startDate = LocalDate.now();
            LocalDate endDate = calculateEndDate(startDate, plan);

            MemberEntity newMember = new MemberEntity();
            newMember.setUserEntity(userEntity);
            newMember.setMembershipEntity(plan);
            newMember.setPayment(createMemberDto.payment());
            newMember.setSubscriptionStartDate(String.valueOf(startDate));
            newMember.setSubscriptionEndDate(String.valueOf(endDate));
            memberRepository.save(newMember);
        }
    }

    private LocalDate calculateEndDate(LocalDate startDate, MembershipEntity plan){
        String unit = plan.getDurationType().toLowerCase();
        Long value = plan.getDuration();

        return switch (unit) {
            case "dias" -> startDate.plusDays(value);
            case "meses" -> startDate.plusMonths(value);
            case "anos" -> startDate.plusYears(value);
            default -> startDate.plusMonths(1); //Plano padrão 1 meses
        };
    }


    private String generateRandomPassword() {
        // Lógica para gerar uma senha forte e aleatória (ex: 10 caracteres)
        // Existem bibliotecas como a do Apache Commons Lang que ajudam aqui.
        return "temp" + new SecureRandom().nextInt(999999); // Exemplo simples
    }

}
