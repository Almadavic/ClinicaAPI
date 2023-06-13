package com.project.clinicaapi.service.businessRule.commitUser;

import com.project.clinicaapi.entity.User;
import com.project.clinicaapi.enumerated.Gender;
import com.project.clinicaapi.repository.UserRepository;
import com.project.clinicaapi.service.customException.*;
import com.project.clinicaapi.util.ListEnumValues;

import java.util.Arrays;
import java.util.Optional;

public class CommitUserValidations {

    private CommitUserValidations() {

    }

    public static String cellphoneFormatValidation(String celular) {

        if (celular != null) {

            if (celular.startsWith("55") || celular.startsWith("+55")) {
                celular = celular.replaceFirst("55", "")
                        .replace("+", "");
            }

            String regexCelularValid = "^\\([1-9]{2}\\)9?[6-9][0-9]{3}\\-[0-9]{4}$";

            if (!celular.matches(regexCelularValid)) {
                throw new InvalidCellphoneNumberException(celular);
            }

            return celular;
        }

        return null;
    }

    public static void findUserByEmailValidation(UserRepository userRepository, String email) {

        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            throw new EmailAlreadyRegisteredException(email);
        }

    }

    public static void findUserByLoginValidation(UserRepository userRepository, String login) {

        Optional<User> userOptional = userRepository.findByLogin(login);

        if (userOptional.isPresent()) {
            throw new LoginAlreadyRegisteredException(login);
        }

    }

    public static void findUserByCellphoneValidation(UserRepository userRepository, String cellphone) {

        Optional<User> userOptional = userRepository.findByCellphone(cellphone);

        if (userOptional.isPresent()) {
            throw new CellphoneAlreadyRegisteredException(cellphone);
        }

    }

    public static void emailFormatValidation(String email) {

        String regexValidEmail = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

        if (!email.matches(regexValidEmail)) {
            throw new InvalidEmailFormatException(email);
        }

    }

    public static void nameFormatValidation(String nome) {

        String regexNomeValid = "^[a-zA-ZÀ-ú]+([ ][a-zA-ZÀ-ú]+)*$";

        if (!nome.matches(regexNomeValid)) {
            throw new InvalidNameFormatException(nome);
        }

    }

    public static void passwordNull(String password, String passwordConfirmation) {

        if (password != null && passwordConfirmation == null) {
            throw new PasswordNullException();
        }

        if (password == null && passwordConfirmation != null) {
            throw new PasswordNullException();
        }

    }

    public static void passwordMatch(String password, String passwordConfirmation) {

        if (password != null && (!password.equals(passwordConfirmation))) {
            throw new PasswordDoesntMatchException();
        }

    }

    public static void genderValueValidation(String gender) {

        try {
            Gender.valueOf(gender.toUpperCase());
        } catch (IllegalArgumentException exception) {
            throw new InvalidEnumValueException(gender, "Gender", ListEnumValues.returnEnumValues(Arrays.asList(Gender.values())));
        }

    }

}
