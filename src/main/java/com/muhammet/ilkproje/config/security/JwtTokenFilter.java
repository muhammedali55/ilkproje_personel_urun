package com.muhammet.ilkproje.config.security;

import com.muhammet.ilkproje.exceptions.ErrorType;
import com.muhammet.ilkproje.exceptions.IlkprojeException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

public class JwtTokenFilter extends OncePerRequestFilter {
    @Autowired
    JwtTokenManager jwtTokenManager;
    @Autowired
    JwtUserDetails jwtUserDetails;
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        /**
         * Gelen isteğin içinde token bilgisinin olup olmadığının kontrol edilmesi gerekir.
         * ilk olarak buna bakarız. Ayrıca eğer kullanıcı zaten önceden bir kimlik kartı
         * almış yani kendisini doğrulamış ise yine burada ek işlem yapmamak adına gelen
         * istediği olduğu gibi iade ederiz.
         */
        final String authHeaderParameters = request.getHeader("Authorization"); // Bearer [TOKEN]
        if(     authHeaderParameters !=null &&  // Bearer Token var mı?
                authHeaderParameters.startsWith("Bearer ") //  Başlıkta Breare ifadesi ile başlıyor mu?
                && SecurityContextHolder.getContext().getAuthentication() == null // Kişinin oturumu varmı?
        ){
            String token = authHeaderParameters.substring(7);
            Optional<Long> userId = jwtTokenManager.getUserIdFromToken(token); // Token bilgi doğrulanmalı
            if(userId.isEmpty())
                throw new IlkprojeException(ErrorType.INVALID_TOKEN);

              UserDetails userDetails = jwtUserDetails.getUserByAuthId(userId.get());
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);

        }
        filterChain.doFilter(request,response);
    }
}
