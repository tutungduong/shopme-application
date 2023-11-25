package com.application.ecommerce.controller;

import com.application.ecommerce.dto.user.SignInDto;
import com.application.ecommerce.dto.user.SignInResponseDto;
import com.application.ecommerce.dto.user.SignUpResponseDto;
import com.application.ecommerce.dto.user.SignupDto;
import com.application.ecommerce.exceptions.AuthenticationFailException;
import com.application.ecommerce.exceptions.CustomException;
import com.application.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("user")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/signup")
    public SignUpResponseDto Signup(@RequestBody SignupDto signupDto) throws CustomException {
        return userService.signUp(signupDto);
    }

    @PostMapping("/signIn")
    public SignInResponseDto SignIn(@RequestBody SignInDto signInDto) throws CustomException, AuthenticationFailException {
        return userService.signIn(signInDto);
    }

}
