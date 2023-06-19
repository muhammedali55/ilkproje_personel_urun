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
@Table(name = "urun", schema = "public")
public class Urun {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String ad;
    String aciklama;
    Double fiyat;
    Integer stok;
    String url;
    Long createat;
    Long updateat;
    int state;
}
