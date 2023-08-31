package com.almada.clinicaapi.repository;

import com.almada.clinicaapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByLogin(String login);

    Optional<User> findByEmail(String email);

    Optional<User> findByCellphone(String cellphone);

    @Query("select u from User u INNER JOIN u.enableAccount ea where ea.code = :code")
    User findUserInnerCodeEnableAccount(@Param(value = "code") String code);

    @Query("select u from User u INNER JOIN u.changePassword cg where cg.code = :code")
    User findUserInnerCodeChangePassword(@Param(value = "code") String code);

}
