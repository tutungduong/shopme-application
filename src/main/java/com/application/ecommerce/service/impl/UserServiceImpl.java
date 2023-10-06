package com.application.ecommerce.service.impl;

import com.application.ecommerce.config.MessageStrings;
import com.application.ecommerce.dto.SignInDto;
import com.application.ecommerce.dto.SignInResponseDto;
import com.application.ecommerce.dto.SignUpResponseDto;
import com.application.ecommerce.dto.SignupDto;
import com.application.ecommerce.exceptions.AuthenticationFailException;
import com.application.ecommerce.exceptions.CustomException;
import com.application.ecommerce.model.AuthenticationToken;
import com.application.ecommerce.model.User;
import com.application.ecommerce.repository.TokenRepository;
import com.application.ecommerce.repository.UserRepository;
import com.application.ecommerce.service.TokenService;
import com.application.ecommerce.service.UserService;
import jakarta.xml.bind.DatatypeConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private TokenService tokenService;

    public UserServiceImpl(UserRepository userRepository,
                           TokenService tokenService) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }
    Logger logger = LoggerFactory.getLogger(UserService.class);

    @Transactional
    @Override
    public SignUpResponseDto signUp(SignupDto signupDto) {
        // check if user is already present
        if(Objects.nonNull(userRepository.findByEmail(signupDto.getEmail()))){
            // If the email address has been registered then throw an exception.
            throw  new CustomException("user already present");
        }
        // hash the password
        String encryptedpassword = signupDto.getPassword();
        try {
            encryptedpassword = hashPassword(signupDto.getPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            logger.error("hashing password failed {}", e.getMessage());
        }

        User user = new User(signupDto.getLastName(), signupDto.getLastName(), signupDto.getEmail(),encryptedpassword);
        try {
            // save the User
            userRepository.save(user);
            // success in creating
            final AuthenticationToken authenticationToken = new AuthenticationToken(user);
            // save token in database
            tokenService.saveConfirmationToken(authenticationToken);
            return new SignUpResponseDto("success", "user created successfully");
        } catch (Exception e) {
            // handle signup error
            throw new CustomException(e.getMessage());
        }
    }
    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte [] digest = md.digest();
        String hash = DatatypeConverter
                .printHexBinary(digest).toUpperCase();
        return hash;
    }
    @Override
    public SignInResponseDto signIn(SignInDto signInDto) throws AuthenticationFailException, CustomException {
        // first find User by email
        User user = userRepository.findByEmail(signInDto.getEmail());
        if(!Objects.nonNull(user)){
            throw new AuthenticationFailException("user not present");
        }
        try {
            // check if password is right
            if (!user.getPassword().equals(hashPassword(signInDto.getPassword()))){
                // passwords do not match
                throw  new AuthenticationFailException(MessageStrings.WRONG_PASSWORD);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            logger.error("hashing password failed {}", e.getMessage());
            throw new CustomException(e.getMessage());
        }

        AuthenticationToken token = tokenService.getToken(user);

        if(!Objects.nonNull(token)) {
            // token not present
            throw new CustomException(MessageStrings.AUTH_TOEKN_NOT_PRESENT);
        }

        return new SignInResponseDto ("success", token.getToken());
    }


}
