package com.muhammet.ilkproje.repository.entity;

import com.muhammet.ilkproje.utility.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "tblgiris")
public class Giris extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long personelid;
    Long urunid;
    Long tarih;
    Integer adet;
    Double birimfiyat;
    Double toplamfiyat;

}
