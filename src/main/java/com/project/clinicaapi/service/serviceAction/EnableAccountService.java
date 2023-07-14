package com.project.clinicaapi.service.serviceAction;

import com.project.clinicaapi.dto.request.EnableAccountDTO;
import com.project.clinicaapi.entity.*;
import com.project.clinicaapi.repository.EnableAccountRepository;
import com.project.clinicaapi.repository.UserRepository;
import com.project.clinicaapi.service.businessRule.enableAccount.EnableAccountArgs;
import com.project.clinicaapi.service.businessRule.enableAccount.EnableAccountVerification;
import com.project.clinicaapi.service.customException.ResourceNotFoundException;
import com.project.clinicaapi.service.customException.SendEmailException;
import com.project.clinicaapi.util.LogRegistration;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class EnableAccountService {

    @Value("${spring.mail.username}")
    private String emailFrom;

    private final EnableAccountRepository enableAccountRepository;

    private final UserRepository userRepository;

    private final LogRegistration logRegistration;

    private final JavaMailSender javaMailSender;

    private final PasswordEncoder encoder;

    private final List<EnableAccountVerification> enableAccountVerifications;

    public void enableAccount(EnableAccountDTO enableAccountDTO) {

        enableAccountVerifications.forEach(ea -> ea.verification(new EnableAccountArgs(enableAccountDTO)));

        String code = enableAccountDTO.getCode();

        String password = enableAccountDTO.getPassword();

        EnableAccount enableAccount = returnEnableAccountByCode(code);

        User user = userRepository.findUserInnerCode(code);
        enableAccount(user, password);

        userRepository.save(user);
        enableAccountRepository.delete(enableAccount);

        String userLogin = user.getUsername();

        buildEmailMessage(user, user.getEnableAccount(), "Ativação de conta  | Clinica API", user.getName() +
                ", Sua conta foi ativada com sucesso no sistema\n" +
                "Login: " + userLogin + "\n" +
                "Senha: " + password);

        logRegistration.saveLog(userLogin, "enabled its account");
    }

    public void sendCodeToEmail(User user) {

        String code = randomPasswordGenerate();

        EnableAccount enableAccount = new EnableAccount(user, code);

        enableAccountRepository.save(enableAccount);

        buildEmailMessage(user, enableAccount, "Ativar conta | Clinica API",
                "Acesse o recurso : /users/enableaccount\n" +
                        " coloque o seguinte código: " + code + " e informe sua senha e confirmação!");

    }

    private void enableAccount(User user, String password) {
        user.setEnabled(true);
        user.setPassword(encoder.encode(password));
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

    @Transactional
    private void buildEmailMessage(User user, EnableAccount enableAccount, String subject, String text) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailFrom);
        message.setTo(user.getEmail());
        message.setSubject(subject);
        message.setText(text);

        try {
            javaMailSender.send(message);
        }catch (Exception e) {
            enableAccountRepository.delete(enableAccount);
            userRepository.delete(user);
            throw new SendEmailException();
        }
    }


    private EnableAccount returnEnableAccountByCode(String code) {
        return enableAccountRepository.findByCode(code)
                .orElseThrow(() -> new ResourceNotFoundException("The code doesn't existis on dataBase"));
    }

}
