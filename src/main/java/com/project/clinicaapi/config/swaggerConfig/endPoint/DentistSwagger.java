package com.project.clinicaapi.config.swaggerConfig.endPoint;

import com.project.clinicaapi.dto.request.register.DentistRegisterDTO;
import com.project.clinicaapi.dto.request.update.DentistUpdateDTO;
import com.project.clinicaapi.dto.response.DentistResponseDTO;
import com.project.clinicaapi.entity.User;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

@Tag(name = "Dentistas", description = "Operações relacionadas á dentistas")
public interface DentistSwagger {

    ResponseEntity<DentistResponseDTO> save(DentistRegisterDTO dentistDTO, User userLogged, UriComponentsBuilder uriBuilder);

    ResponseEntity<Page<DentistResponseDTO>> findPage(@Parameter(hidden = true) Pageable pageable, String name, String specialty);

    ResponseEntity<DentistResponseDTO> findById(String id);

    ResponseEntity<DentistResponseDTO> findByCro(String cro);

    ResponseEntity<DentistResponseDTO> update(String id, DentistUpdateDTO dentistDTO, User userLogged);

}
