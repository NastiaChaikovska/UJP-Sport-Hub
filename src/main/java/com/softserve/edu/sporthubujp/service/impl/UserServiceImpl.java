package com.softserve.edu.sporthubujp.service.impl;

import com.softserve.edu.sporthubujp.dto.UserDTO;
import com.softserve.edu.sporthubujp.entity.User;
import com.softserve.edu.sporthubujp.mapper.UserMapper;
import com.softserve.edu.sporthubujp.repository.UserRepository;
import com.softserve.edu.sporthubujp.security.PasswordConfig;
import com.softserve.edu.sporthubujp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final static String USER_NOT_FOUND_MSG =
            "user with email %s not found";

    private final UserRepository userRepository;
    private final PasswordConfig passwordConfig;
    private final UserMapper userMapper;


//    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public String signUpUser(UserDTO userDTO) {

        User user = userMapper.dtoToEntity(userDTO);
        boolean userExists = userRepository
                .findByEmail(user.getEmail())
                .isPresent();

        if (userExists) {
            // TODO check of attributes are the same and
            // TODO if email not confirmed send confirmation email.

            throw new IllegalStateException("email already taken");
        }

        String encodedPassword = passwordConfig.passwordEncoder()
                .encode(user.getPassword());

        user.setPassword(encodedPassword);

        userRepository.save(user);

        String token = UUID.randomUUID().toString();

//        ConfirmationToken confirmationToken = new ConfirmationToken(
//                token,
//                LocalDateTime.now(),
//                LocalDateTime.now().plusMinutes(15),
//                user
//        );
//
//        confirmationTokenService.saveConfirmationToken(
//                confirmationToken);

//        TODO: SEND EMAIL

        return token;
    }

    @Override
    public int enableUser(String email) {
        return userRepository.enableUser(email);
    }

    // TODO: optional
    @Override
    public UserDTO selectUserByEmail(String email) throws UsernameNotFoundException {
        return userMapper.entityToDto(userRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException(
                        String.format(USER_NOT_FOUND_MSG, email))));

    }
}
