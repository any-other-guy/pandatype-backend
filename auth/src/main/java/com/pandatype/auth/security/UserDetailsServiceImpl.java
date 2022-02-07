package com.pandatype.auth.security;

import com.pandatype.auth.entities.UserEntity;
import com.pandatype.auth.mapper.UserMapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserMapper userMapper;

    @Override
    @Transactional
    // Acutually this is loadUserByEmail not gonna lie
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userMapper.selectUserEntityByEmail(email);
        if (userEntity == null) {
            throw new UsernameNotFoundException(email);
        }
        // Add role to user
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + userEntity.getUserRole()));
        // Pass email, password and role to Spring Security
        return new User(userEntity.getEmail(), userEntity.getPassword(), authorities);
    }
}