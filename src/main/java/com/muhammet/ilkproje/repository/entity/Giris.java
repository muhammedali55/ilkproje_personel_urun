package com.muhammet.ilkproje.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tblgiris", schema = "public")
public class Giris {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long personelid;
    Long urunid;
    Long tarih;
    Integer adet;
    Double birimfiyat;
    Double toplamfiyat;
    Long createat;
    Long updateat;
    int state;
}
