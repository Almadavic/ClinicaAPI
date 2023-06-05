package com.project.clinicaapi.config;

import com.project.clinicaapi.entity.*;
import com.project.clinicaapi.enumerated.Gender;
import com.project.clinicaapi.enumerated.Role;
import com.project.clinicaapi.enumerated.Specialty;
import com.project.clinicaapi.enumerated.WorkDayEnum;
import com.project.clinicaapi.repository.AppointmentRepository;
import com.project.clinicaapi.repository.UserRepository;
import com.project.clinicaapi.repository.WorkDayRepository;
import com.project.clinicaapi.util.LogRegistration;
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

    private final PasswordEncoder encoder;

    private final LogRegistration logRegistration;

    @Override
    public void run(String... args) {

        Dentist dentist = Dentist.dentistBuilder()
                .login("dentist")
                .name("nome1")
                .password(encoder.encode("123456"))
                .country("Brasil")
                .cellphone("193189744")
                .state("MG")
                .city("Belo Horizonte")
                .email("almada@hotmail.com")
                .enabled(true)
                .specialty(Specialty.ORTHODONTICS)
                .gender(Gender.MALE)
                .build();

        Patient patient = Patient.patientBuilder()
                .login("patient")
                .name("nome2")
                .password(encoder.encode("123456"))
                .country("Brasil")
                .cellphone("1931891144")
                .state("MG")
                .city("Belo Horizonte")
                .email("sergio@hotmail.com")
                .enabled(true)
                .gender(Gender.MALE)
                .cpf("115613986-02")
                .build();

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

        User u1 = User.builder()
                .login("admin")
                .name("admin")
                .password(encoder.encode("123456"))
                .country("Brasil")
                .cellphone("193181221")
                .state("admin")
                .city("admin")
                .email("admin@hotmail.com")
                .enabled(true)
                .gender(Gender.MALE)
                .build();

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
                .enabled(false)
                .gender(Gender.MALE)
                .build();

        u2.setRole(Role.ADMINISTRATOR);

        userRepository.saveAll(Arrays.asList(dentist, patient, u1, u2, secretary));


        WorkDay wd1 = new WorkDay(WorkDayEnum.MONDAY);
        WorkDay wd2 = new WorkDay(WorkDayEnum.TUESDAY);
        WorkDay wd3 = new WorkDay(WorkDayEnum.WEDNESDAY);
        WorkDay wd4 = new WorkDay(WorkDayEnum.THURSDAY);
        WorkDay wd5 = new WorkDay(WorkDayEnum.FRIDAY);
        WorkDay wd6 = new WorkDay(WorkDayEnum.SATURDAY);

        workDayRepository.saveAll(Arrays.asList(wd1, wd2, wd3, wd4, wd5, wd6));


        dentist.addWorkDay(wd1);
        dentist.addWorkDay(wd2);
        dentist.addWorkDay(wd3);

       dentist = userRepository.save(dentist);


        Appointment appointment = Appointment.builder()
                .procedure("Limpeza de dente")
                .appointmentDay(LocalDate.of(2023, 06, 01))
                .timeStart(LocalTime.now())
                .timeEnd(LocalTime.now().plusHours(1))
                .dentist(dentist)
                .patient(patient)
                .build();

        appointmentRepository.save(appointment);

        logRegistration.saveLog("admin", "authenticated in the system");

    }


}
