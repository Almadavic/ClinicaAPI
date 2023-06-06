//package com.project.clinicaapi.rest.userController;
//
//import com.project.clinicaapi.dto.request.register.AddessRegisterDTO;
//import com.project.clinicaapi.dto.request.register.SecretaryRegisterDTO;
//import com.project.clinicaapi.dto.request.register.UserRegisterDTO;
//import com.project.clinicaapi.rest.ClassTestParent;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//
//import java.util.Arrays;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@ActiveProfiles(value = "test")
//@SpringBootTest
//@AutoConfigureMockMvc
//class SaveUserTest extends ClassTestParent {
//
//    private final String path = "/users";
//
//    @Test
//    void passwordsDontMatch() throws Exception {
//
//        UserRegisterDTO secretaryDTO = UserRegisterDTO.builder()
//                .login("login")
//                .address(new AddessRegisterDTO("country", "state", "city"))
//                .email("email@hotmail.com")
//                .name("name nome")
//                .cellphone("(31)98589-7284")
//                .registration("91237198-B")
//                .gender("MALE")
//                .build();
//
//        UserRegisterDTO userRegisterDTO = new UserRegisterDTO();
//
//
//        mockMvc.perform(post(path)
//                        .header("Authorization", token("admin","123456"))
//                        .contentType("application/json")
//                        .content(objectMapper.writeValueAsString(userDTO)))
//                .andExpect(status().is(badRequest))
//                .andExpect(result -> assertTrue(result.getResolvedException() instanceof PasswordDoesntMatchException))
//                .andExpect(result -> assertEquals("As senhas não correspondem"
//                        , result.getResolvedException().getMessage()));
//
//    }
//
//
//    @Test
//    void loginAlreadyExistsInTheSystem() throws Exception {
//
//        String login = "consultor";
//
//        RegisterUserDTO userDTO = new RegisterUserDTO(login, "123456", "123456", "consultora@hotmail.com", "consultor",
//                "(31)99247-0149", "ATIVO", "ESPECIALISTA");
//
//        mockMvc.perform(post(path)
//                        .header("Authorization", token("admin","123456"))
//                        .contentType("application/json")
//                        .content(objectMapper.writeValueAsString(userDTO)))
//                .andExpect(status().is(internalServerError))
//                .andExpect(result -> assertTrue(result.getResolvedException() instanceof LoginAlreadyRegisteredException))
//                .andExpect(result -> assertEquals("O login: " + login + " já existe no sistema"
//                        , result.getResolvedException().getMessage()));
//
//    }
//
//    @Test
//    void emailAlreadyExistsInTheSystem() throws Exception {
//
//        String email = "consultor@hotmail.com";
//
//        RegisterUserDTO userDTO = new RegisterUserDTO("consultoraa", "123456", "123456", email, "consultor",
//                "(31)99247-0149", "ATIVO", "ESPECIALISTA");
//
//        mockMvc.perform(post(path)
//                        .header("Authorization", token("admin","123456"))
//                        .contentType("application/json")
//                        .content(objectMapper.writeValueAsString(userDTO)))
//                .andExpect(status().is(internalServerError))
//                .andExpect(result -> assertTrue(result.getResolvedException() instanceof EmailAlreadyRegisteredException))
//                .andExpect(result -> assertEquals("O email: " + email + " já existe no sistema"
//                        , result.getResolvedException().getMessage()));
//
//    }
//
//    @Test
//    void invalidEmailFormat() throws Exception {
//
//        String email = "consultor@hotmail.com!";
//
//        RegisterUserDTO userDTO = new RegisterUserDTO("consultora", "123456", "123456", email, "consultor",
//                "(31)99247-0149", "ATIVO", "ESPECIALISTA");
//
//        mockMvc.perform(post(path)
//                        .header("Authorization", token("admin","123456"))
//                        .contentType("application/json")
//                        .content(objectMapper.writeValueAsString(userDTO)))
//                .andExpect(status().is(badRequest))
//                .andExpect(result -> assertTrue(result.getResolvedException() instanceof InvalidEmailFormatException))
//                .andExpect(result -> assertEquals("O email: " + email + " está com um formato inválido"
//                        , result.getResolvedException().getMessage()));
//
//    }
//
//    @Test
//    void invalidNameFormat() throws Exception {
//
//        String nome = "Victor!";
//
//        RegisterUserDTO userDTO = new RegisterUserDTO("consultoraa", "123456", "123456", "consultora@hotmail.com", nome,
//                "(31)99247-0149", "ATIVO", "ESPECIALISTA");
//
//        mockMvc.perform(post(path)
//                        .header("Authorization", token("admin","123456"))
//                        .contentType("application/json")
//                        .content(objectMapper.writeValueAsString(userDTO)))
//                .andExpect(status().is(badRequest))
//                .andExpect(result -> assertTrue(result.getResolvedException() instanceof InvalidNameFormatException))
//                .andExpect(result -> assertEquals("O nome: " + nome + " está com um formato inválido"
//                        , result.getResolvedException().getMessage()));
//
//    }
//
//    @Test
//    void invalidCellphoneFormat() throws Exception {
//
//        String celular = "31924701495aaff";
//
//        RegisterUserDTO userDTO = new RegisterUserDTO("teste", "teste123", "teste123", "teste@hotmail.com", "teste",
//                celular, "ATIVO", "ESPECIALISTA");
//
//
//        mockMvc.perform(post(path)
//                        .header("Authorization", token("admin","123456"))
//                        .contentType("application/json")
//                        .content(objectMapper.writeValueAsString(userDTO)))
//                .andExpect(status().is(badRequest))
//                .andExpect(result -> assertTrue(result.getResolvedException() instanceof InvalidCellphoneNumberException))
//                .andExpect(result -> assertEquals("Celular inserido com formato inválido: " + celular + ", Formato correto: (XX)9XXXX-XXXX"
//                        , result.getResolvedException().getMessage()));
//
//    }
//
//    @Test
//    void invalidUserSituationValue() throws Exception {
//
//        String situacao = "ativooo";
//
//        RegisterUserDTO userDTO = new RegisterUserDTO("consultoraaaa", "123456", "123456", "consultora@hotmail.com",
//                "consultor", "(31)99247-0149", situacao, "ESPECIALISTA");
//
//        mockMvc.perform(post(path)
//                        .header("Authorization", token("admin","123456"))
//                        .contentType("application/json")
//                        .content(objectMapper.writeValueAsString(userDTO)))
//                .andExpect(status().is(badRequest))
//                .andExpect(result -> assertTrue(result.getResolvedException() instanceof InvalidEnumValueException))
//                .andExpect(result -> assertEquals("O valor que você enviou: " + situacao + " para o tipo Situacao não é válido, valores válidos: "
//                                + ListEnumValues.returnEnumValues(Arrays.asList(Situation.values()))
//                        , result.getResolvedException().getMessage()));
//
//    }
//
//    @Test
//    void invalidUserTypeValue() throws Exception {
//
//        String tipoUsuario = "especialistaa";
//
//        RegisterUserDTO userDTO = new RegisterUserDTO("consultora","123456", "123456", "consultora@hotmail.com", "consultor",
//                "(31)99247-0149", "ATIVO", tipoUsuario);
//
//        mockMvc.perform(post(path)
//                        .header("Authorization", token("admin","123456"))
//                        .contentType("application/json")
//                        .content(objectMapper.writeValueAsString(userDTO)))
//                .andExpect(status().is(badRequest))
//                .andExpect(result -> assertTrue(result.getResolvedException() instanceof InvalidEnumValueException))
//                .andExpect(result -> assertEquals("O valor que você enviou: " + tipoUsuario + " para o tipo TipoUsuario não é válido, valores válidos: "
//                                + ListEnumValues.returnEnumValues(Arrays.asList(Role.values()))
//                        , result.getResolvedException().getMessage()));
//
//    }
//
//    @Test
//    void saveUserSuccess() throws Exception {
//
//        String login = "Almada";
//        String senha = "123456";
//
//        RegisterUserDTO userDTO = new RegisterUserDTO(login, senha, senha, "almada@hotmail.com", "almada", "(31)99247-0149",
//                "ATIVO", "ADMINISTRADOR");
//
//        mockMvc.perform(post(path)
//                        .header("Authorization", token("admin","123456"))
//                        .contentType("application/json")
//                        .content(objectMapper.writeValueAsString(userDTO)))
//                .andExpect(status().is(created));
//
//        enterSystemUserSaved(login,senha);
//
//    }
//
//    private void enterSystemUserSaved(String login, String senha) throws Exception {
//
//        LoginData loginData = new LoginData(login, senha);
//
//        mockMvc.perform(post("/auth")
//                        .contentType("application/json")
//                        .content(objectMapper.writeValueAsString(loginData)))
//                .andExpect(status().is(ok));
//
//    }
//
//}
