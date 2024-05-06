package com.example.test0405.service;

import com.example.test0405.model.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public interface UserService {

    Optional<User> getUserById(Long id);

    User createUser(User user);

    User updateUser(Long id, Map<String, Object> updatedFields);

    boolean deleteUser(Long id);

    List<User> getUsersByBirthDateRange(LocalDate from, LocalDate to);

}