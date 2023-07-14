package com.project.clinicaapi.service.serviceAction;

import com.project.clinicaapi.dto.request.EnableAccountDTO;
import com.project.clinicaapi.entity.*;
import com.project.clinicaapi.repository.EnableAccountRepository;
import com.project.clinicaapi.repository.UserRepository;
import com.project.clinicaapi.service.businessRule.commitUser.PasswordMatch;
import com.project.clinicaapi.service.customException.ResourceNotFoundException;
import com.project.clinicaapi.util.LogRegistration;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class SendCodeToActiveAccountToEmailService {

    @Value("${spring.mail.username}")
    private String emailFrom;

    private final EnableAccountRepository enableAccountRepository;

    private final LogRegistration logRegistration;

    private final UserRepository userRepository;

    private final JavaMailSender javaMailSender;

    private final PasswordEncoder encoder;

    public void enableAccount(EnableAccountDTO enableAccountDTO) {

        String code = enableAccountDTO.getCode();

        String password = enableAccountDTO.getPassword();

        EnableAccount enableAccount = returnEnableAccountByCode(code);

        PasswordMatch.verification(password, enableAccountDTO.getPasswordConfirmation());

        enableAccount.getUser().setEnabled(true);

        User user = userRepository.findUserInnerCode(code);
        user.setEnabled(true);
        user.setPassword(encoder.encode(password));

        userRepository.save(user);
        enableAccountRepository.delete(enableAccount);


        buildEmailMessage(user.getEmail(), "Ativação de conta", "Sua conta foi ativada com sucesso");
        logRegistration.saveLog(user.getUsername(), "enabled its account");
    }

    public void sendCodeToEmail(User user) {

        String code = randomPasswordGenerate();

        enableAccountRepository.save(new EnableAccount(user, code));

        buildEmailMessage(user.getEmail(), "Ativar conta | Clinica API", "Acesse o recurso : /users/enableaccount\n" +
                " coloque o seguinte código: " + code + " e informe sua senha e confirmação!");

    }

    private String randomPasswordGenerate() {

        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

        Random random = new Random();

        int length = 10;

        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            char randomChar = characters.charAt(randomIndex);
            sb.append(randomChar);
        }

        return sb.toString();

    }

    private void buildEmailMessage(String userEmail, String subject, String text) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailFrom);
        message.setTo(userEmail);
        message.setSubject(subject);
        message.setText(text);

        javaMailSender.send(message);
    }


    private EnableAccount returnEnableAccountByCode(String code) {
        return enableAccountRepository.findByCode(code).orElseThrow(() -> new ResourceNotFoundException(" The code doesn't existis on dataBase "));
    }

}
