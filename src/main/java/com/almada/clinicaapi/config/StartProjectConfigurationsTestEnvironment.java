package com.almada.clinicaapi.config;

import com.almada.clinicaapi.entity.*;
import com.almada.clinicaapi.enumerated.Gender;
import com.almada.clinicaapi.enumerated.Role;
import com.almada.clinicaapi.enumerated.Specialty;
import com.almada.clinicaapi.enumerated.WorkDayEnum;
import com.almada.clinicaapi.repository.*;
import com.almada.clinicaapi.util.LogRegistration;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;


@Configuration
@RequiredArgsConstructor
@Profile(value = "test")
public class StartProjectConfigurationsTestEnvironment implements CommandLineRunner {

    private final UserRepository userRepository;

    private final WorkDayRepository workDayRepository;

    private final AppointmentRepository appointmentRepository;

    private final EnableAccountRepository enableAccountRepository;

    private final ChangePasswordRepository changePasswordRepository;

    private final PasswordEncoder encoder;

    private final LogRegistration logRegistration;

    @Override
    public void run(String... args) {

        Dentist dentist1 = Dentist.dentistBuilder()
                .login("dentist1")
                .name("dentist1")
                .password(encoder.encode("123456"))
                .country("Brasil")
                .cellphone("(31)98858-8362")
                .state("MG")
                .city("Belo Horizonte")
                .email("dentist1@hotmail.com")
                .cro("137180")
                .specialty(Specialty.ORTHODONTICS)
                .gender(Gender.MALE)
                .build();

        dentist1.setEnabled(true);

        Dentist dentist2 = Dentist.dentistBuilder()
                .login("dentist2")
                .name("dentist2")
                .password(encoder.encode("123456"))
                .country("Brasil")
                .cellphone("(31)98896-8362")
                .state("MG")
                .city("Belo Horizonte")
                .email("dentist2@hotmail.com")
                .cro("137185")
                .specialty(Specialty.ENDODONTICS)
                .gender(Gender.MALE)
                .build();

        dentist2.setEnabled(true);

        Dentist dentist3 = Dentist.dentistBuilder()
                .login("dentist3")
                .name("dentist3")
                .password(encoder.encode("123456"))
                .country("Brasil")
                .cellphone("(31)98895-8362")
                .state("MG")
                .city("Belo Horizonte")
                .email("dentist3@hotmail.com")
                .cro("137182")
                .specialty(Specialty.ORTHODONTICS)
                .gender(Gender.MALE)
                .build();

        dentist3.setEnabled(true);

        Dentist dentist4 = Dentist.dentistBuilder()
                .login("dentist4")
                .name("dentist4")
                .password(encoder.encode("123456"))
                .country("Brasil")
                .cellphone("(31)98894-8362")
                .state("MG")
                .city("Belo Horizonte")
                .email("dentist4@hotmail.com")
                .cro("137188")
                .specialty(Specialty.DENTISTRY)
                .gender(Gender.MALE)
                .build();

        dentist4.setEnabled(true);

        Dentist dentist5 = Dentist.dentistBuilder()
                .login("dentist5")
                .name("dentist5")
                .password(encoder.encode("123456"))
                .country("Brasil")
                .cellphone("(31)98899-8762")
                .state("MG")
                .city("Belo Horizonte")
                .email("dentist5@hotmail.com")
                .cro("00000")
                .specialty(Specialty.DENTISTRY)
                .gender(Gender.MALE)
                .build();

        Dentist dentist6 = Dentist.dentistBuilder()
                .login("dentist6")
                .name("dentist6")
                .country("Brasil")
                .cellphone("(31)98882-8762")
                .state("MG")
                .city("Belo Horizonte")
                .email("dentist6@hotmail.com")
                .cro("00001")
                .specialty(Specialty.DENTISTRY)
                .gender(Gender.MALE)
                .build();

        Patient patient1 = Patient.patientBuilder()
                .login("patient")
                .name("nome2")
                .password(encoder.encode("123456"))
                .country("Brasil")
                .cellphone("1931891144")
                .state("MG")
                .city("Belo Horizonte")
                .email("sergio@hotmail.com")
                .gender(Gender.MALE)
                .cpf("115.613.986-02")
                .build();

        patient1.setEnabled(true);

        Patient patient2 = Patient.patientBuilder()
                .login("patient2")
                .name("nome2")
                .password(encoder.encode("123456"))
                .country("Brasil")
                .cellphone("1931891141")
                .state("MG")
                .city("Belo Horizonte")
                .email("sergio222@hotmail.com")
                .gender(Gender.MALE)
                .cpf("115.613.982-01")
                .build();

        patient2.setEnabled(true);

        Secretary secretary = Secretary.secretaryBuilder()
                .login("secretary")
                .name("nome3")
                .password(encoder.encode("123456"))
                .country("Brasil")
                .cellphone("1931131144")
                .state("MG")
                .city("Belo Horizonte")
                .email("secratary@hotmail.com")
                .gender(Gender.MALE)
                .registration("1156139862302")
                .build();

        Secretary secretary2 = Secretary.secretaryBuilder()
                .login("secretary2")
                .name("nome4")
                .password(encoder.encode("123456"))
                .country("Brasil")
                .cellphone("19311242444")
                .state("MG")
                .city("Belo Horizonte")
                .email("secratary2@hotmail.com")
                .gender(Gender.MALE)
                .registration("1151387")
                .build();

        Secretary secretary3 = Secretary.secretaryBuilder()
                .login("secretary3")
                .name("nome5")
                .password(encoder.encode("123456"))
                .country("Brasil")
                .cellphone("19311242333")
                .state("MG")
                .city("Belo Horizonte")
                .email("secratary38@hotmail.com")
                .gender(Gender.MALE)
                .registration("11513899")
                .build();

        User u1 = User.builder()
                .login("admin")
                .name("admin")
                .password(encoder.encode("123456"))
                .country("Brasil")
                .cellphone("193181221")
                .state("admin")
                .city("admin")
                .email("admin@hotmail.com")
                .gender(Gender.MALE)
                .build();

        u1.setEnabled(true);

        u1.setRole(Role.ADMINISTRATOR);

        User u2 = User.builder()
                .login("joaozin")
                .name("joaojoao")
                .password(encoder.encode("123456"))
                .country("Brasil")
                .cellphone("203181223")
                .state("joao")
                .city("joao")
                .email("joao@hotmail.com")
                .gender(Gender.MALE)
                .build();

        u2.setRole(Role.ADMINISTRATOR);

        User u3 = User.builder()
                .login("delete")
                .name("delete")
                .password(encoder.encode("123456"))
                .country("Delete")
                .cellphone("delete")
                .state("delete")
                .city("delete")
                .email("delete")
                .gender(Gender.MALE)
                .build();

        u3.setRole(Role.PATIENT);

        User u4 = User.builder()
                .login("Almada")
                .name("Victor Almada")
                .password(encoder.encode("123456"))
                .country("Brasil")
                .cellphone("(31)98589-8955")
                .state("Minas Gerais")
                .city("Belo Horizonte")
                .email("almadavic@live.com")
                .gender(Gender.MALE)
                .build();

        u4.setEnabled(true);

        u4.setRole(Role.ADMINISTRATOR);

        userRepository.saveAll(Arrays.asList(dentist1, dentist2, dentist3, dentist4, dentist5, dentist6, patient1, patient2, secretary, secretary2,
                secretary3, u1, u2, u3, u4));

        WorkDay wd1 = new WorkDay(WorkDayEnum.MONDAY);
        WorkDay wd2 = new WorkDay(WorkDayEnum.TUESDAY);
        WorkDay wd3 = new WorkDay(WorkDayEnum.WEDNESDAY);
        WorkDay wd4 = new WorkDay(WorkDayEnum.THURSDAY);
        WorkDay wd5 = new WorkDay(WorkDayEnum.FRIDAY);
        WorkDay wd6 = new WorkDay(WorkDayEnum.SATURDAY);

        workDayRepository.saveAll(Arrays.asList(wd1, wd2, wd3, wd4, wd5, wd6));


        dentist1.addWorkDay(wd1);
        dentist1.addWorkDay(wd2);

        dentist2.addWorkDay(wd2);
        dentist2.addWorkDay(wd1);
        dentist2.addWorkDay(wd3);

        dentist3.addWorkDay(wd6);
        dentist3.addWorkDay(wd5);
        dentist3.addWorkDay(wd4);

        dentist4.addWorkDay(wd3);
        dentist4.addWorkDay(wd1);

        dentist5.addWorkDay(wd1);

        userRepository.saveAll(Arrays.asList(dentist2, dentist3, dentist4, dentist5));

        dentist1 = userRepository.save(dentist1);


        Appointment appointment1 = Appointment.builder()
                .procedure("Limpeza de dente")
                .appointmentDate(LocalDate.of(2023, 6, 26))
                .timeStart(LocalTime.now())
                .timeEnd(LocalTime.now().plusHours(1))
                .dentist(dentist1)
                .patient(patient1)
                .build();

        Appointment appointment2 = Appointment.builder()
                .procedure("Canal no dente")
                .appointmentDate(LocalDate.of(2023, 6, 26))
                .timeStart(LocalTime.now())
                .timeEnd(LocalTime.now().plusHours(1))
                .dentist(dentist1)
                .patient(patient1)
                .build();

        Appointment appointment3 = Appointment.builder()
                .procedure("Tirar o cizo")
                .appointmentDate(LocalDate.of(2023, 6, 26))
                .timeStart(LocalTime.now())
                .timeEnd(LocalTime.now().plusHours(1))
                .dentist(dentist1)
                .patient(patient1)
                .build();

        Appointment appointment4 = Appointment.builder()
                .procedure("Tirar dente de leite")
                .appointmentDate(LocalDate.of(2023, 6, 26))
                .timeStart(LocalTime.now())
                .timeEnd(LocalTime.now().plusHours(1))
                .dentist(dentist1)
                .patient(patient1)
                .build();

        Appointment appointment5 = Appointment.builder()
                .procedure("Ginoplastia")
                .appointmentDate(LocalDate.of(2023, 6, 27))
                .timeStart(LocalTime.now())
                .timeEnd(LocalTime.now().plusHours(1))
                .dentist(dentist1)
                .patient(patient1)
                .build();

        Appointment appointment6 = Appointment.builder()
                .procedure("Ginoplastia")
                .appointmentDate(LocalDate.now().plusDays(1))
                .timeStart(LocalTime.of(17, 30))
                .timeEnd(LocalTime.of(18, 0))
                .dentist(dentist1)
                .patient(patient1)
                .build();

        Appointment appointment7 = Appointment.builder()
                .procedure("Ginoplastia")
                .appointmentDate(LocalDate.now().plusDays(1))
                .timeStart(LocalTime.now().plusMinutes(60))
                .timeEnd(LocalTime.now().plusMinutes(90))
                .dentist(dentist1)
                .patient(patient1)
                .build();

        appointmentRepository.saveAll(Arrays.asList(appointment1, appointment2, appointment3, appointment4, appointment5, appointment6, appointment7));

        logRegistration.saveLog("admin", "authenticated in the system");

        enableAccountRepository.saveAll(Arrays.asList(
                new EnableAccount(dentist1, "abcolpqstu"),
                new EnableAccount(dentist2, "abcolpqstb"),
                new EnableAccount(dentist3, "abcolpqstc")
        ));

        changePasswordRepository.saveAll(Arrays.asList(
                new ChangePassword(secretary, "abcolpqstu"),
                new ChangePassword(secretary2, "abcolpqstb"),
                new ChangePassword(secretary3, "abcolpqstc")
        ));

    }


}
