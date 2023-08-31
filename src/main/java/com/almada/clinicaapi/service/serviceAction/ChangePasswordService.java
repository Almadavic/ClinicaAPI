package com.almada.clinicaapi.service.serviceAction;

import com.almada.clinicaapi.dto.request.ChangePasswordDTO;
import com.almada.clinicaapi.entity.ChangePassword;
import com.almada.clinicaapi.entity.User;
import com.almada.clinicaapi.repository.ChangePasswordRepository;
import com.almada.clinicaapi.repository.UserRepository;
import com.almada.clinicaapi.service.businessRule.changePasswordForgotten.ChangePasswordForgottenArgs;
import com.almada.clinicaapi.service.businessRule.changePasswordForgotten.ChangePasswordForgottenValidation;
import com.almada.clinicaapi.service.customException.ResourceNotFoundException;
import com.almada.clinicaapi.service.customException.SendEmailException;
import com.almada.clinicaapi.util.GenerateCode;
import com.almada.clinicaapi.util.LogRegistration;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChangePasswordService {

    @Value("${spring.mail.username}")
    private String emailFrom;

    private final JavaMailSender javaMailSender;

    private final ChangePasswordRepository changePasswordRepository;

    private final UserRepository userRepository;

    private final PasswordEncoder encoder;

    private final LogRegistration logRegistration;

    private final List<ChangePasswordForgottenValidation> changePasswordForgottenValidations;

    @Transactional
    public void sendCodeToEmail(User user) {

        if (user.getChangePassword() != null) {
            changePasswordRepository.delete(user.getChangePassword());
            user = userRepository.save(user);
        }

        String userEmail = user.getEmail();

        String code = GenerateCode.randomCode();

        ChangePassword cp = new ChangePassword(user, code);

        changePasswordRepository.save(cp);

        buildEmailMessage(userEmail, "Mudar Senha | Clinica API",
                user.getName() + ", \n" +
                        "Acesse o recurso : /users/changepassword\n" +
                        " coloque o seguinte código: " + code + " e informe a nova senha e confirmação!");

    }

    public void changePassword(ChangePasswordDTO changePasswordDTO) {

        String code = changePasswordDTO.getCode();

        ChangePassword changePassword = returnChangePasswordByCode(code);

        changePasswordForgottenValidations.forEach(v -> v.verification(new ChangePasswordForgottenArgs(changePasswordDTO)));

        String password = changePasswordDTO.getPassword();

        User user = userRepository.findUserInnerCodeChangePassword(code);
        user.setPassword(encoder.encode(changePasswordDTO.getPassword()));

        userRepository.save(user);
        changePasswordRepository.delete(changePassword);

        String userLogin = user.getUsername();

        buildEmailMessage(user.getEmail(), "Senha alteraca com sucesso  | Clinica API",
                user.getName() + ", Sua senha foi alterada com sucesso no sistema\n" +
                        "Nova senha: " + password);

        logRegistration.saveLog(userLogin, "changed its account");

    }

    private void buildEmailMessage(String emailTo, String subject, String text) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailFrom);
        message.setTo(emailTo);
        message.setSubject(subject);
        message.setText(text);

        try {
            javaMailSender.send(message);
        } catch (Exception e) {
            throw new SendEmailException();
        }
    }

    private ChangePassword returnChangePasswordByCode(String code) {
        return changePasswordRepository.findByCode(code)
                .orElseThrow(() -> new ResourceNotFoundException("The code doesn't existis on dataBase"));
    }

}
