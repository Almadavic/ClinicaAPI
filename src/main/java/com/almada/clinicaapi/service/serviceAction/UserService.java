package com.almada.clinicaapi.service.serviceAction;


import com.almada.clinicaapi.dto.request.update.UserUpdateDTO;
import com.almada.clinicaapi.dto.response.UserMonitoringResponseDTO;
import com.almada.clinicaapi.dto.response.UserResponseDTO;
import com.almada.clinicaapi.entity.User;
import com.almada.clinicaapi.mapper.UserMapper;
import com.almada.clinicaapi.repository.UserRepository;
import com.almada.clinicaapi.service.businessRule.commitUser.updateUser.UpdateUserArgs;
import com.almada.clinicaapi.service.businessRule.commitUser.updateUser.UpdateUserVerification;
import com.almada.clinicaapi.service.businessRule.disableAccount.DisableAccountArgs;
import com.almada.clinicaapi.service.businessRule.disableAccount.DisableAccountVerification;
import com.almada.clinicaapi.service.businessRule.forgetPassword.ForgetPasswordArgs;
import com.almada.clinicaapi.service.businessRule.forgetPassword.ForgetPasswordVerification;
import com.almada.clinicaapi.service.customException.AccountNotEnabledException;
import com.almada.clinicaapi.service.customException.NoFieldFilledException;
import com.almada.clinicaapi.service.customException.ResourceNotFoundException;
import com.almada.clinicaapi.util.LogRegistration;
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

    private final ChangePasswordService changePasswordService;

    private final AttributesListToUpdateService attributesListToUpdateService;

    private final List<UpdateUserVerification> updateUserVerifications;

    private final List<DisableAccountVerification> disableAccountVerifications;

    private final List<ForgetPasswordVerification> forgetPasswordVerifications;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByLogin(username)
                .orElseThrow(() -> new RuntimeException("User not found with the login : " + username));
    }

    @Cacheable(value = "usersPage")
    public Page<UserMonitoringResponseDTO> findPage(Pageable pageable) {
        return mapper.toUserMonitoringDTOPage(userRepository.findAll(pageable));
    }

    public UserMonitoringResponseDTO findById(String userId) {
        return mapper.toUserMonitoringDTO(returnUserDataBaseById(userId));
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
        User user = returnUserDataBaseById(id);
        disableAccountVerifications.forEach(v -> v.verification(new DisableAccountArgs(userLogged, user)));
        user.setEnabled(false);
        userRepository.save(user);
        logRegistration.saveLog(userLogged.getUsername(), user.getUsername() + "'s account");
    }

    @CacheEvict(value = {"usersPage", "secretariesPage"}, allEntries = true)
    public void delete(String id, User userLogged) {
        User user = returnUserDataBaseById(id);
        userRepository.delete(user);
        logRegistration.saveLog(userLogged.getUsername(), "deleted the user: " + user.getUsername());
    }

    public String forgetPassword(String email) {

        forgetPasswordVerifications.forEach(v -> v.verification(new ForgetPasswordArgs(email, userRepository)));

        User user = returnUserDataBaseByEmail(email);

        if(!user.isEnabled()) {
            throw new AccountNotEnabledException();
        }

        changePasswordService.sendCodeToEmail(user);

        return "Se " + email + " é o seu e-mail, siga as instruções enviadas para o mesmo";
    }

    private User returnUserDataBaseByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("The email: " + email + " wasn't found on database"));
    }

    private User returnUserDataBaseById(String userId) {
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
