package com.application.ecommerce.service.impl;

import com.application.ecommerce.config.MessageStrings;
import com.application.ecommerce.exceptions.AuthenticationFailException;
import com.application.ecommerce.model.AuthenticationToken;
import com.application.ecommerce.model.User;
import com.application.ecommerce.repository.TokenRepository;
import com.application.ecommerce.service.TokenService;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class TokenServiceImpl implements TokenService {

    private TokenRepository tokenRepository;

    public TokenServiceImpl(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }
    // save the confirmation token
    @Override
    public void saveConfirmationToken(AuthenticationToken authenticationToken) {
        tokenRepository.save(authenticationToken);
    }
    // get Uer from the token
    @Override
    public User getUser(String token) {
        AuthenticationToken authenticationToken = tokenRepository.findTokenByToken(token);
        if (Objects.nonNull(authenticationToken)) {
            if (Objects.nonNull(authenticationToken.getUser())) {
                return authenticationToken.getUser();
            }
        }
        return null;
    }
    // get token of the User
    @Override
    public AuthenticationToken getToken(User user) {
        return tokenRepository.findTokenByUser(user);
    }
    // check if the token is valid
    @Override
    public void authenticate(String token) throws AuthenticationFailException  {
        if (!Objects.nonNull(token)) {
            throw new AuthenticationFailException(MessageStrings.AUTH_TOEKN_NOT_PRESENT);
        }
        if (!Objects.nonNull(getUser(token))) {
            throw new AuthenticationFailException(MessageStrings.AUTH_TOEKN_NOT_VALID);
        }
    }

}
