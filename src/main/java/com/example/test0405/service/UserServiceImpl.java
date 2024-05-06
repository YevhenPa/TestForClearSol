package com.example.test0405.service;

import com.example.test0405.model.User;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final Map<Long, User> userStorage = new ConcurrentHashMap<>();

    @Override
    public Optional<User> getUserById(Long id) {
        return Optional.ofNullable(userStorage.get(id));
    }

    @Override
    public User createUser(User user) {
        LocalDate currentDate = LocalDate.now();
        Period age = Period.between(user.getBirthDate(), currentDate);

        if (age.getYears() < 18 || (age.getYears() == 18 && age.getMonths() == 0 && age.getDays() == 0)) {
            throw new IllegalArgumentException("User must be at least 18 years old to register.");
        }

        Long userId = generateUserId();
        user.setId(userId);
        userStorage.put(userId, user);

        return user;
    }

    @Override
    public User updateUser(Long id, Map<String, Object> updatedFields) {
        if (userStorage.containsKey(id)) {
            User user = userStorage.get(id);
            updatedFields.forEach((key, value) -> {
                try {
                    Field field = User.class.getDeclaredField(key);
                    field.setAccessible(true);
                    field.set(user, value);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            });
            userStorage.put(id, user);
            return user;
        } else {
            return null;
        }
    }

    @Override
    public boolean deleteUser(Long id) {
        return userStorage.remove(id) != null;
    }

    @Override
    public List<User> getUsersByBirthDateRange(LocalDate from, LocalDate to) {
        return userStorage.values().stream()
                .filter(user -> user.getBirthDate().isAfter(from) &&
                        user.getBirthDate().isBefore(to)).collect(Collectors.toList());
    }

    private Long generateUserId() {
        return System.currentTimeMillis();
    }

}