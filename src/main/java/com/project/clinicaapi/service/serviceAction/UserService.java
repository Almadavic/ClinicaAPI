package com.project.clinicaapi.service.serviceAction;

import com.project.clinicaapi.dto.request.update.UserUpdateDTO;
import com.project.clinicaapi.dto.response.UserResponseDTO;
import com.project.clinicaapi.entity.User;
import com.project.clinicaapi.repository.UserRepository;
import com.project.clinicaapi.service.businessRule.commitUser.updateUser.UpdateUserArgs;
import com.project.clinicaapi.service.businessRule.commitUser.updateUser.UpdateUserVerification;
import com.project.clinicaapi.service.businessRule.disableAccount.DisableAccountArgs;
import com.project.clinicaapi.service.businessRule.disableAccount.DisableAccountVerification;
import com.project.clinicaapi.service.customException.NoFieldFilledException;
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

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final UserMapper mapper;

    private final LogRegistration logRegistration;

    private final AttributesListToUpdateService attributesListToUpdateService;

    private final List<UpdateUserVerification> updateUserVerifications;

    private final List<DisableAccountVerification> disableAccountVerifications;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByLogin(username)
                .orElseThrow(() -> new RuntimeException("User not found with the login : " + username));
    }

    @Cacheable(value = "usersPage")
    public Page<UserResponseDTO> findPage(Pageable pageable) {
        return mapper.toUserMonitoringDTOPage(userRepository.findAll(pageable));
    }

    public UserResponseDTO findById(String userId) {
        return mapper.toUserMonitoringDTO(returnUserDataBase(userId));
    }

    @CacheEvict(value = {"usersPage", "secretariesPage"}, allEntries = true)
    public UserResponseDTO update(UserUpdateDTO updateData, User userLogged) {
        updateUserVerifications.forEach(v -> v.verification(new UpdateUserArgs(updateData, userLogged)));
        verifyNullAttibutes(updateData);
        UserResponseDTO userDTO = mapper.toUserDTO(userRepository.save(userLogged));
        logRegistration.saveLog(userLogged.getUsername(), "updated its account data: ");
        return userDTO;
    }

    @CacheEvict(value = {"usersPage", "secretariesPage"}, allEntries = true)
    public void disableAccount(String id, User userLogged) {
        User user = returnUserDataBase(id);
        disableAccountVerifications.forEach(v -> v.verification(new DisableAccountArgs(userLogged, user)));
        user.setEnabled(false);
        userRepository.save(user);
        logRegistration.saveLog(userLogged.getUsername(),  user.getUsername() + "'s account");
    }

    @CacheEvict(value = {"usersPage", "secretariesPage"}, allEntries = true)
    public void delete(String id, User userLogged) {
        User user = returnUserDataBase(id);
        userRepository.delete(user);
        logRegistration.saveLog(userLogged.getUsername(), "deleted the user: " + user.getUsername());
    }

    private User returnUserDataBase(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("The user id: " + userId + " wasn't found on database"));
    }

    private void verifyNullAttibutes(UserUpdateDTO updateData) {

        List<Object> attributes = attributesListToUpdateService.getAttributesGenericsUser(updateData);

        if (attributesListToUpdateService.allAttributesNull(attributes)) {
            throw new NoFieldFilledException();
        }
    }

}
