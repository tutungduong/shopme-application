package com.application.ecommerce.service;

import com.application.ecommerce.dto.SignUpResponseDto;
import com.application.ecommerce.dto.SignupDto;

public interface UserService {
    SignUpResponseDto signUp(SignupDto signupDto);
}
