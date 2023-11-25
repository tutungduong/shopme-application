package com.application.ecommerce.service;

import com.application.ecommerce.exceptions.AuthenticationFailException;
import com.application.ecommerce.model.AuthenticationToken;
import com.application.ecommerce.model.User;

public interface AuthenticationService {
    void saveConfirmationToken(AuthenticationToken authenticationToken);
    User getUser(String token);
    AuthenticationToken getToken(User user);
    void authenticate(String token) throws AuthenticationFailException;
}
