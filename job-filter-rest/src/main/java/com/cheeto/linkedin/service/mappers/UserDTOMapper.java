package com.cheeto.linkedin.service.mappers;

import com.cheeto.jobfilter.model.UserDTO;
import com.cheeto.linkedin.service.dao.UserDao;
import com.cheeto.linkedin.service.models.UserDB;
import org.springframework.stereotype.Component;

@Component
public class UserDTOMapper {

    public static UserDB toDao(UserDTO userDTO) {

        UserDB userDB = new UserDB();

        userDB.setEmail(userDTO.getEmail());
        userDB.setDisplayName(userDTO.getDisplayName());

        return userDB;
    }

    public static UserDTO toDto(UserDB userDB) {

        UserDTO userDTO = new UserDTO();

        userDTO.setEmail(userDB.getEmail());
        userDTO.setDisplayName(userDB.getDisplayName());

        return userDTO;
    }

}
