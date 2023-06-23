package com.muhammet.ilkproje.config.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class JwtTokenManager {
    /**
     * 1- Token Oluşturma
     * 2- Token Doğrulama
     * 3- Token içinden Kullanıcı bilgilerinin çekilmesi
     * DİKKAT!!!!!!!
     * bir token oluşturulması için token in imzalanması gereklidir. Bunun için bir şifre
     * anahtarına ihtiyaç var ancak bu bilginin gizli tutulması gereklidir. bu nedenle
     * bu bilgi application.yml dosyasında tutulmamalıdır. Ayrıca bu ilgi application.yml
     * içiden de systemEnvironment üzerinden çekilmelidir.
     */
    @Value("${jwt.secret}")
    private String secretKey;
    private String passwordKey = "HEKaOwc1D3CXEay7EMJgeIsWrgAgbQVC";

    private final Long exDate = 1000L * 30; // 10 saniye
    public Optional<String> createToken(Long id){
        String token = null;
        try{
            /**
             * DİKKAT!!!!
             * claim nesneleri açık public şekilde tutulur. Bu nedenle gizli bilgileri asla ama asla
             * buraya yazmayınız, kişilerin kişisel bilgileri buraya yazılmamalıdır.
             * mail, telefon, şifre v.s. asla yazmıyoruz.
             */
            token = JWT.create()
                    .withAudience()
                    .withClaim("id",id) // Key - Value şeklinde bilgi tutar.
                    .withClaim("usertype","personel")
                    .withClaim("password","buraya şifre yazdım")
                    .withIssuer("com.muhammet") // oluşturan kişi, uygulama, microservice
                    .withIssuedAt(new Date()) // token oluşturulma zamanı
                    .withExpiresAt(new Date(System.currentTimeMillis()+ exDate)) // token geçerlilik süresi
                    .sign(Algorithm.HMAC512(passwordKey)); // token imzalama algoritması
         return Optional.of(token);
        }catch (Exception e){
            return Optional.empty();
        }
    }

    public Optional<Long> getUserIdFromToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC512(passwordKey);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("com.muhammet")
                    .build();
            DecodedJWT jwt = verifier.verify(token); // eğer doğrulama olmaz ise hata fırlatır.
            if(jwt==null)
                return Optional.empty();
          Long id = jwt.getClaim("id").asLong();
          return Optional.of(id);
        }catch (Exception e) {
            return Optional.empty();
        }
    }
}
