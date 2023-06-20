package com.muhammet.ilkproje.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PersonelSaveRequestDto {

    @NotBlank(message = "Ad boş olamaz")
    String ad;
    /**
     * 0 555 999 8866
     */
    @Size(min = 11,max = 16, message = "Telefon 11 ile 16 karakter arasında olmalıdır")
    String telefon;

    String adres;
    String token;
//
//    @Email(message = "Email formatı hatalı")
//    String email;

//    @NotBlank
//    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=*!])(?=\\S+$).{8,}$",message = "Şifre en az 8 karakter olmalıdır ve en az 1 büyük harf, 1 küçük harf ve 1 rakam içermelidir ve özel karakterler olmalıdır")
//    String password;

}
