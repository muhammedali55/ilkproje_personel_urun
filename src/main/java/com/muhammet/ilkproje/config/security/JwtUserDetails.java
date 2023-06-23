package com.muhammet.ilkproje.config.security;

import com.muhammet.ilkproje.utility.StaticValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class JwtUserDetails implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    public UserDetails getUserByAuthId(Long authid){
        boolean isUserActive = StaticValues.userIds.stream().filter(x-> Objects.equals(x, authid)).toList().size()>0;
        if (!isUserActive) return null;

        List<GrantedAuthority> authorities = new ArrayList<>();
        /**
         * Burada belirtilen isimlendirmeler tamamen size aittir. özel bir kullanım değildir.
         * Yetki Adı: yönetici, asistan,
         */
        StaticValues.roles.forEach(x->{
            authorities.add(new SimpleGrantedAuthority(x)); // "Admin","User" v.s.
        });
        return User.builder()
                .username(authid.toString()) // kullanıcı adı
                .password("") // şifre
                .accountLocked(false) // kişinin hesabı banlanmış
                .accountExpired(false) // kişinin hesabı geçerli mi süresi dolmuşmu
                .authorities(authorities)
                .build();
    }
}
