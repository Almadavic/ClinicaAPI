package com.project.clinicaapi.config;

import com.project.clinicaapi.entity.User;
import com.project.clinicaapi.enumerated.Role;
import com.project.clinicaapi.enumerated.Situation;
import com.project.clinicaapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;


@Configuration
@RequiredArgsConstructor
public class StartProjectConfigurationsTestEnvironment implements CommandLineRunner {

    private final UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {


        User u1 = new User();
            u1.setCellphone("139713871381781");
            u1.setName("João");
            u1.setEmail("aodjaojadoaodd");
            u1.setPassword("1903819891");
            u1.setRole(Role.DOCTOR);
            u1.setSituation(Situation.ATIVO);

            u1.getAddress().setCity("Belo Horizonte");
            u1.getAddress().setState("Minas Gerais");
            u1.getAddress().setCountry("Brasil");

            userRepository.save(u1);

    }


}
