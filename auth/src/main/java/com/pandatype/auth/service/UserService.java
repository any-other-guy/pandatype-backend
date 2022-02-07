package com.pandatype.auth.service;

import com.pandatype.auth.entities.AuthResponseEntity;
import com.pandatype.auth.entities.UserEntity;
import com.pandatype.auth.mapper.UserMapper;
import com.pandatype.auth.security.TokenUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {
    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private TokenUtils tokenUtils;
    @Resource
    private UserMapper userMapper;
    Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    /**
     * user login service
     *
     * @param userEntity
     * @return AuthResponseEntity response object with token
     */
    public AuthResponseEntity login(final UserEntity userEntity) {
        try {
            // Call AuthenticationManager to validate UsernamePasswordAuthenticationToken object
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userEntity.getEmail(),
                            userEntity.getPassword()));
        } catch (BadCredentialsException e) {
            return new AuthResponseEntity("ERROR", "Incorrect E-mail or Password.");
        }
        // Generate JWT Token and return in a form of AuthResponseEntity
        UserEntity UserData = userMapper.selectUserEntityByEmail(userEntity.getEmail());
        return new AuthResponseEntity("SUCCESS",
                tokenUtils.createToken(UserData), UserData.getUsername());
    }

    /**
     * user registration service
     *
     * @param userEntity
     * @return AuthResponseEntity response object with token
     */
    public AuthResponseEntity save(UserEntity userEntity) throws DataAccessException {
        try {
            // Make password encrypted in db
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            String password = bCryptPasswordEncoder.encode(userEntity.getPassword());
            userEntity.setPassword(password);
            userMapper.insertUserEntity(userEntity);
        } catch (DataAccessException e) {
            LOGGER.error(e.toString());
            return new AuthResponseEntity("ERROR", e.toString());
        }
        String token = tokenUtils.createToken(userEntity);
        return new AuthResponseEntity("SUCCESS", token, userEntity.getUsername());
    }
}