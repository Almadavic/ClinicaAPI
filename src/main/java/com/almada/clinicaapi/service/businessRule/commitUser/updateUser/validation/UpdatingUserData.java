package com.almada.clinicaapi.service.businessRule.commitUser.updateUser.validation;

import com.almada.clinicaapi.dto.request.update.AddressUpdateDTO;
import com.almada.clinicaapi.dto.request.update.UserUpdateDTO;
import com.almada.clinicaapi.entity.User;
import com.almada.clinicaapi.enumerated.Gender;
import com.almada.clinicaapi.service.businessRule.commitUser.updateUser.UpdateUserArgs;
import com.almada.clinicaapi.service.businessRule.commitUser.updateUser.UpdateUserVerification;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Order(value = 11)
@Component
@RequiredArgsConstructor
public class UpdatingUserData implements UpdateUserVerification {

    private final PasswordEncoder encoder;

    @Override
    public void verification(UpdateUserArgs args) {

        UserUpdateDTO userDTO = args.userDTO();

        User user = args.user();

        setLogin(userDTO, user);
        setPassword(userDTO, user);
        setEmail(userDTO, user);
        setName(userDTO, user);
        setCellphone(userDTO, user);
        setGender(userDTO, user);
        AddressUpdateDTO addressDTO = userDTO.getAddress();
        if (addressDTO != null) {
            setCountry(userDTO, user);
            setState(userDTO, user);
            setCity(userDTO, user);
        }

    }

    private void setLogin(UserUpdateDTO userDTO, User user) {
        String login = userDTO.getLogin();
        if (login != null) {
            user.setLogin(login);
        }
    }

    private void setPassword(UserUpdateDTO userDTO, User user) {
        String senha = userDTO.getPassword();
        if (senha != null) {
            user.setPassword(encoder.encode(senha));
        }
    }

    private void setEmail(UserUpdateDTO userDTO, User user) {
        String email = userDTO.getEmail();
        if (email != null) {
            user.setEmail(email);
        }
    }

    private void setName(UserUpdateDTO userDTO, User user) {
        String nome = userDTO.getName();
        if (nome != null) {
            user.setName(nome);
        }
    }

    private void setCellphone(UserUpdateDTO userDTO, User user) {
        String celular = userDTO.getCellphone();
        if (celular != null) {
            user.setCellphone(celular);
        }
    }

    private void setGender(UserUpdateDTO userDTO, User user) {
        String gender = userDTO.getGender();
        if (gender != null) {
            user.setGender(Gender.valueOf(gender.toUpperCase()));
        }
    }

    private void setCountry(UserUpdateDTO userDTO, User user) {
        String country = userDTO.getAddress().getCountry();
        if (country != null) {
            user.getAddress().setCountry(country);
        }
    }

    private void setState(UserUpdateDTO userDTO, User user) {
        String state = userDTO.getAddress().getState();
        if (state != null) {
            user.getAddress().setState(state);
        }
    }

    private void setCity(UserUpdateDTO userDTO, User user) {
        String city = userDTO.getAddress().getCity();
        if (city != null) {
            user.getAddress().setCity(city);
        }
    }

}
