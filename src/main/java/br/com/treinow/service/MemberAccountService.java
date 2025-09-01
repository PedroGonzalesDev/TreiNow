package br.com.treinow.service;

import br.com.treinow.exceptions.BusinessException;
import br.com.treinow.models.entities.UserEntity;
import br.com.treinow.models.entities.enums.UserStatus;
import br.com.treinow.repositories.jpa.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MemberAccountService {

    @Autowired private UserRepository userRepository;
    @Autowired private PasswordEncoder passwordEncoder;

    @Transactional
    public void setInitialPassword(String token, String newPassword){

        UserEntity userEntity = userRepository.findByActivationToken(token)
                .orElseThrow(()-> new BusinessException("Token is expired or invalid."));

        if(userEntity.getTokenExpiryDate().isBefore(LocalDateTime.now())){
            throw new BusinessException("Expired token please request a new one ");
        }

        if(userEntity.getStatus() == UserStatus.ACTIVE){
            throw new BusinessException("This account was already activated");
        }

        userEntity.setPassword(passwordEncoder.encode(newPassword));
        userEntity.setStatus(UserStatus.ACTIVE);
        userEntity.setActivationToken(null);
        userEntity.setTokenExpiryDate(null);

        userRepository.save(userEntity);
    }
}
