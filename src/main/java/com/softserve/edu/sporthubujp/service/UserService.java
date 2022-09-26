package com.softserve.edu.sporthubujp.service;

import com.softserve.edu.sporthubujp.dto.UserDTO;
import com.softserve.edu.sporthubujp.dto.UserSaveProfileDTO;
import com.softserve.edu.sporthubujp.entity.User;

public interface UserService {

    /**
     * Method for saving user {@link User} into the database and
     * sending a token {@link com.softserve.edu.sporthubujp.entity.ConfirmationToken} to confirm
     *
     * @param userDTO - an actual user object of {@link UserDTO} type
     * @return generated confirmation token String
     */
    String signUpUser(UserDTO userDTO);

    /**
     * Method for enabling user and allowing him to use the web application
     *
     * @param email - an email of user
     */
    void enableUser(String email);

    User findUserByEmail(String email);

    UserDTO updateUser(User oldUser, UserSaveProfileDTO newUser);
}