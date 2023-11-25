package com.application.ecommerce.service;

import com.application.ecommerce.dto.user.SignInDto;
import com.application.ecommerce.dto.user.SignInResponseDto;
import com.application.ecommerce.dto.user.SignUpResponseDto;
import com.application.ecommerce.dto.user.SignupDto;
import com.application.ecommerce.exceptions.AuthenticationFailException;
import com.application.ecommerce.exceptions.CustomException;

public interface UserService {
    SignUpResponseDto signUp(SignupDto signupDto) throws CustomException;

    SignInResponseDto signIn(SignInDto signInDto) throws AuthenticationFailException, CustomException;
}
