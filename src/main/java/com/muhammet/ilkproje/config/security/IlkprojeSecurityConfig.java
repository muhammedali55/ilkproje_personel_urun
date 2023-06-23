package com.muhammet.ilkproje.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class IlkprojeSecurityConfig {

    @Bean
    JwtTokenFilter getJwtTokenfilter(){
        return new JwtTokenFilter();
    }

    /**
     * Spring Security uygulama içinde güvenlik yapılandırmasoını SecurityfilterChain nesnesi ile sağlar.
     * b nesneyi özelleştirmek için bu sınıftan bir bean oluşturmak gereklidir.
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain ilkprojeSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {

        /**
         * Spring Security nin default kullanımlda olan tüm yapılandırmasını düzelnlememiz gereklidir.
         * ilk olarak csrf in devre dışı bırakılması gereklidir. çünkü biz bu yapıda RestAPI kullanacağız
         *
         */
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        /**
         *  req.anyRequest().permitAll()  -> tüm istekleri kabul et
         *   req.anyRequest().authenticated() -> tüm isteklere yetki doğrulamasını zorla
         *   /** -> root altındaki tüm path ler
         *   /admin/** -> admin altındaki tüm istekler
         */
        httpSecurity.authorizeHttpRequests(req->
                req.requestMatchers(
                        "/api/v1/personel/findall","/api/v1/user/**",
                        "/v3/api-docs/**", "/swagger-ui/**","/api/v1/personel/gettoken"
                ).permitAll()
                        .requestMatchers("/api/v1/root/**").hasAnyRole("Super_Admin")
                        .requestMatchers("/api/v1/admin/**").hasAnyRole("Admin")
                        .anyRequest().authenticated()
                );
        /**
         * Filter işleminden önce bu kısım devreye girerek requst içindeki JWT yi kullanarak oturum açacak
         */
        httpSecurity.addFilterBefore(getJwtTokenfilter(), UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

}
