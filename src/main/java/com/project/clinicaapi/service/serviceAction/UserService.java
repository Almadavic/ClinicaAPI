package com.project.clinicaapi.service.serviceAction;

import com.project.clinicaapi.dto.response.UserResponseDTO;
import com.project.clinicaapi.entity.User;
import com.project.clinicaapi.repository.UserRepository;
import com.project.clinicaapi.service.customException.ResourceNotFoundException;
import com.project.clinicaapi.util.LogRegistration;
import com.project.clinicaapi.util.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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

    private final LogRegistration logRegistration;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByLogin(username)
                .orElseThrow(() -> new RuntimeException("User not found with the login : " + username));
    }

    @Cacheable(value = "usersPage")
    public Page<UserResponseDTO> findPage(Pageable pageable) {
        return mapper.toUserDTOPage(userRepository.findAll(pageable));
    }

    public UserResponseDTO findById(String userId) {
        return mapper.toUserDTO(returnUserDataBase(userId));
    }

    @CacheEvict(value = {"usersPage", "secretariesPage"}, allEntries = true)
    public void delete(String id, User userLogged) {
        User user = returnUserDataBase(id);
        userRepository.delete(user);
        logRegistration.saveLog(userLogged.getUsername(), "deleted the secretary: " + user.getUsername());
    }

    private User returnUserDataBase(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("The user id: " + userId + " wasn't found on database"));
    }

}
