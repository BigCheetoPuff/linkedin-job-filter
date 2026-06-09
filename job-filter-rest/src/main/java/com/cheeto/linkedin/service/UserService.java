package com.cheeto.linkedin.service;

import com.cheeto.linkedin.service.dao.UserDao;
import com.cheeto.linkedin.service.mappers.MapperUtils;
import com.cheeto.linkedin.service.models.UserDB;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDao userDao;

    public Long createUser(UserDB user) {
        MapperUtils.assertUpdatedSingleRow(userDao.insert(user));
        return user.getId();
    }

    public Optional<UserDB> getUserById(Long userId) {
        return userDao.findById(userId);
    }

    public Optional<UserDB> getUserByEmail(String email) {
        return userDao.findByEmail(email);
    }
}
