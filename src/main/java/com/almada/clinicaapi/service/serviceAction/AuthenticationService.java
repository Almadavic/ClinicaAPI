package com.almada.clinicaapi.service.serviceAction;

import com.almada.clinicaapi.config.securityConfig.LoginData;
import com.almada.clinicaapi.config.securityConfig.Token;
import com.almada.clinicaapi.repository.UserRepository;
import com.almada.clinicaapi.service.customException.DatabaseException;
import com.almada.clinicaapi.util.LogRegistration;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final TokenService tokenService;

    private final AuthenticationManager authManager;

    private final UserRepository userRepository;

    private final LogRegistration logRegistration;

    public Token authenticate(LoginData loginData) {

        try {

            Authentication authentication = authManager.authenticate(loginData.toConvert());

            String token = tokenService.generateToken(authentication);

            logRegistration.saveLog(returnTokenUserName(token), "authenticated in the system");

            return new Token(token, "Bearer");

        } catch (AuthenticationException e) {
            throw new DatabaseException("Login and / or password is / are wrong | or your account is disabled");
        }

    }

    private String returnTokenUserName(String tokenJWT) {
        String subject = tokenService.getSubject(tokenJWT);
        return userRepository.findById(subject).get().getUsername();
    }

}
