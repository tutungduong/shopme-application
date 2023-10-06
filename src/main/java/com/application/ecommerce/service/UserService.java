package com.application.ecommerce.service;

import com.application.ecommerce.dto.SignInDto;
import com.application.ecommerce.dto.SignInResponseDto;
import com.application.ecommerce.dto.SignUpResponseDto;
import com.application.ecommerce.dto.SignupDto;
import com.application.ecommerce.exceptions.AuthenticationFailException;
import com.application.ecommerce.exceptions.CustomException;

public interface UserService {
    SignUpResponseDto signUp(SignupDto signupDto);

    SignInResponseDto signIn(SignInDto signInDto) throws AuthenticationFailException, CustomException;
}
