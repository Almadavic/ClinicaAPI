//package com.project.clinicaapi.rest.secretaryController;
//
//import com.project.clinicaapi.dto.request.update.AddressUpdateDTO;
//import com.project.clinicaapi.dto.request.update.SecretaryUpdateDTO;
//import com.project.clinicaapi.rest.ClassTestParent;
//import com.project.clinicaapi.service.businessRule.commitSecretary.updateSecretary.UpdateSecretaryArgs;
//import com.project.clinicaapi.service.customException.InvalidEmailFormatException;
//import com.project.clinicaapi.service.customException.NoFieldFilledException;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@ActiveProfiles(value = "test")
//@SpringBootTest
//class UpdateSecretaryTest extends ClassTestParent {
//
//    @Test
//    void noFieldFilledToUpdate() {
//
//        SecretaryUpdateDTO secretaryDTO = SecretaryUpdateDTO.builder()
//                .build();
//
//        mockMvc.perform(patch(path + "/" + returnUser().getId())
//                        .header("Authorization", token("admin","123456"))
//                        .contentType("application/json")
//                        .content(objectMapper.writeValueAsString(secretaryDTO)))
//                .andExpect(status().is(badRequest))
//                .andExpect(result -> assertTrue(result.getResolvedException() instanceof InvalidEmailFormatException))
//                .andExpect(result -> assertEquals("The e-mail: " + email + " contains an invalid format"
//                        , result.getResolvedException().getMessage()));
//
//    }
//
//    @Test
//    void noAddressFieldFilledToUpdate() {
//
//        SecretaryUpdateDTO secretaryDTO = SecretaryUpdateDTO.builder()
//                .address(new AddressUpdateDTO())
//                .build();
//
//        Assertions.assertThrows(NoFieldFilledException.class,
//                () -> service.verification(new UpdateSecretaryArgs(secretaryDTO, null)));
//
//    }
//
//    @Test
//    void fieldFilledToUpdate() {
//
//        SecretaryUpdateDTO secretaryDTO = SecretaryUpdateDTO.builder()
//                .registration("13781791")
//                .build();
//
//        Assertions.assertDoesNotThrow(() -> service.verification(new UpdateSecretaryArgs(secretaryDTO, null)));
//
//    }
//
//    @Test
//    void addressFieldFilledToUpdate() {
//
//        SecretaryUpdateDTO secretaryDTO = SecretaryUpdateDTO.builder()
//                .address(AddressUpdateDTO.builder()
//                        .city("São Paulo")
//                        .build())
//                .build();
//
//        Assertions.assertDoesNotThrow(() -> service.verification(new UpdateSecretaryArgs(secretaryDTO, null)));
//
//    }
//
//}
