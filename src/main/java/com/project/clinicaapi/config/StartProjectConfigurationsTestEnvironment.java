package com.project.clinicaapi.config;

import com.project.clinicaapi.entity.Dentist;
import com.project.clinicaapi.entity.User;
import com.project.clinicaapi.entity.WorkDay;
import com.project.clinicaapi.enumerated.Role;
import com.project.clinicaapi.enumerated.Specialty;
import com.project.clinicaapi.enumerated.WorkDayEnum;
import com.project.clinicaapi.repository.UserRepository;
import com.project.clinicaapi.repository.WorkDayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;


@Configuration
@RequiredArgsConstructor
public class StartProjectConfigurationsTestEnvironment implements CommandLineRunner {

    private final UserRepository userRepository;

    private final WorkDayRepository workDayRepository;

    @Override
    public void run(String... args)  {

        Dentist dentist = Dentist.dentistBuilder()
                .name("nome")
                .password("1424224")
                .country("Brasil")
                .cellphone("193189744")
                .state("MG")
                .city("Belo Horizonte")
                .role(Role.DENTIST)
                .email("almada@hotmail.com")
                .enabled(true)
                .specialty(Specialty.ORTHODONTICS)
                .build();

            userRepository.save(dentist);

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

        userRepository.save(dentist);


//        for(int i =0 ; i< dentist.getWorkDays().size(); i++) {
//            System.out.println(dentist.getWorkDays().get(i).getWorkDay().toString());
//        }

    }


}
