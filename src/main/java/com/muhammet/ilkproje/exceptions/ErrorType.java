package com.muhammet.ilkproje.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ErrorType {
    INTERNAL_ERROR(5100,"Sunucuda beklenmeyen hata oluştu",HttpStatus.INTERNAL_SERVER_ERROR),
    BAD_REQUEST_ERROR(4100, "Parametre eksik yada hatalı", HttpStatus.BAD_REQUEST),
    INVALID_TOKEN(9998,"Geçersiz Token",HttpStatus.BAD_REQUEST),
    LOGIN_ERROR(4110,"Kullanıcı adı yada şifre hatalı",HttpStatus.BAD_REQUEST),
    REGISTER_REPASSWORD_ERROR(4111,"Girilen şifreler uyuşmuyor",HttpStatus.BAD_REQUEST),
    JWT_TOKEN_CREATE_ERROR(4114,"Token Oluşturma Hatası",HttpStatus.BAD_REQUEST),
    REGISTER_KULLANICIADI_KAYITLI(4112,"Kullanıcı adı zaten kayıtlı",HttpStatus.BAD_REQUEST),
    PERSONEL_EKLENEMEDI(1001,"Personel Eklenemedi",HttpStatus.BAD_REQUEST),
    PERSONEL_GUNCELLENEMEDI(1002,"Personel Güncellenemedi",HttpStatus.BAD_REQUEST),
    PERSONEL_SILINEMEDI(1003,"Personel Silinemedi",HttpStatus.BAD_REQUEST),
    PERSONEL_BULUNAMADI(1004,"Personel Bulunamadı",HttpStatus.BAD_REQUEST),
    INTERNAL_SERVER_ERROR(9999,"Internal Server Error",HttpStatus.INTERNAL_SERVER_ERROR);

    int code;
    String message;
    HttpStatus httpStatus;
}
