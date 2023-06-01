package com.project.clinicaapi.service.serviceAction;

import com.project.clinicaapi.dto.response.UserResponseDTO;
import com.project.clinicaapi.entity.User;
import com.project.clinicaapi.repository.UserRepository;
import com.project.clinicaapi.service.customException.ResourceNotFoundException;
import com.project.clinicaapi.util.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final UserMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("User not found with the e-mail : " + username));
    }

    public Page<UserResponseDTO> findPage(Pageable pageable) {
        return mapper.toUserDTOPage(userRepository.findAll(pageable));
    }

    public UserResponseDTO findById(String userId) {
        return mapper.toUserDTO(returnUserDataBase(userId));
    }

    private User returnUserDataBase(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("The user id: "+ userId + " wasn't found on database"));
    }

}
