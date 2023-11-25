package com.application.ecommerce.service.impl;

import com.application.ecommerce.config.MessageStrings;
import com.application.ecommerce.exceptions.AuthenticationFailException;
import com.application.ecommerce.model.AuthenticationToken;
import com.application.ecommerce.model.User;
import com.application.ecommerce.repository.TokenRepository;
import com.application.ecommerce.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    TokenRepository repository;

    @Override
    // save the confirmation token
    public void saveConfirmationToken(AuthenticationToken authenticationToken) {
        repository.save(authenticationToken);
    }
    @Override
    // get token of the User
    public AuthenticationToken getToken(User user) {
        return repository.findTokenByUser(user);
    }
    @Override
    // get Uer from the token
    public User getUser(String token) {
        AuthenticationToken authenticationToken = repository.findTokenByToken(token);
        if (Objects.nonNull(authenticationToken)) {
            if (Objects.nonNull(authenticationToken.getUser())) {
                return authenticationToken.getUser();
            }
        }
        return null;
    }
    @Override
    // check if the token is valid
    public void authenticate(String token) throws AuthenticationFailException {
        if (!Objects.nonNull(token)) {
            throw new AuthenticationFailException(MessageStrings.AUTH_TOEKN_NOT_PRESENT);
        }
        if (!Objects.nonNull(getUser(token))) {
            throw new AuthenticationFailException(MessageStrings.AUTH_TOEKN_NOT_VALID);
        }
    }

}
