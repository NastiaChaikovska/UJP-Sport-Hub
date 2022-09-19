package com.softserve.edu.sporthubujp.service;

import java.io.IOException;
import java.util.InvalidPropertiesFormatException;

import javax.mail.SendFailedException;

import com.softserve.edu.sporthubujp.dto.UserDTO;
import com.softserve.edu.sporthubujp.dto.UserSavePasswordDTO;
import com.softserve.edu.sporthubujp.dto.UserSaveProfileDTO;
import com.softserve.edu.sporthubujp.entity.User;

public interface UserService {

    String signUpUser(UserDTO userDTO);

    int enableUser(String email);

    User findUserByEmail(String email);

    UserSaveProfileDTO findUserById(String userId);

    //    User findUserByPasswordResetToken(String token);

    UserDTO updateUser(User oldUser, UserSaveProfileDTO newUser);

    UserDTO updatePassword(User oldUser, UserSavePasswordDTO newUser) throws InvalidPropertiesFormatException;

    UserDTO resetUserPassword(User user, String newPassword) throws IOException, SendFailedException, IOException, SendFailedException;
}