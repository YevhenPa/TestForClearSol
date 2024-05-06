package com.example.test0405.controller;

import com.example.test0405.model.User;
import com.example.test0405.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    }

    @Test
    void testCreateUser() {
        User user = new User(null, "test@example.com", "John"
                , "Doe", LocalDate.of(2000, 1, 1)
                , "123 Street", "1234567890");
        when(userService.createUser(any(User.class))).thenReturn(user);

        ResponseEntity<User> response = userController.createUser(user);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    void testUpdateUser() {
        Map<String, Object> fields = new HashMap<>();
        fields.put("firstName", "Jane");

        User updatedUser = new User(1L, "test@example.com", "Jane"
                , "Doe", LocalDate.of(2000, 1, 1)
                , "123 Street", "1234567890");
        when(userService.updateUser(eq(1L), eq(fields))).thenReturn(updatedUser);

        ResponseEntity<User> response = userController.updateUser(1L, fields);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedUser, response.getBody());
    }

    @Test
    void testDeleteUser() {
        when(userService.deleteUser(1L)).thenReturn(true);

        ResponseEntity<String> response = userController.deleteUser(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("User deleted successfully", response.getBody());
    }

    @Test
    void testGetUserById() {
        User user = new User(1L, "test@example.com", "John"
                , "Doe", LocalDate.of(2000, 1, 1)
                , "123 Street", "1234567890");
        when(userService.getUserById(1L)).thenReturn(Optional.of(user));

        ResponseEntity<User> response = userController.getUserById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    void testSearchUsersByBirthDateRange() {
        LocalDate fromDate = LocalDate.of(1990, 1, 1);
        LocalDate toDate = LocalDate.of(2000, 1, 1);
        User user = new User(1L, "test@example.com", "John"
                , "Doe", LocalDate.of(1995, 1, 1)
                , "123 Street", "1234567890");
        when(userService.getUsersByBirthDateRange(fromDate, toDate)).thenReturn(Collections.singletonList(user));

        ResponseEntity<?> response = userController.searchUsersByBirthDateRange(fromDate, toDate);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Collections.singletonList(user), response.getBody());
    }
}
