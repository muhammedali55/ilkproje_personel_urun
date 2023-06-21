package com.muhammet.ilkproje.repository.entity;

import com.muhammet.ilkproje.utility.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Lombok anotasyonları, Getter,Setter, Constructor, Builder gibi kodları otomatik olarak oluşturur.
 */
/**
 * @Entity anotasyonu, bu sınıfın bir entity olduğunu belirtir.
 * @Table anotasyonu, oluşturulacak ya da var olan bir tabloya bağlanmak içim tanımlanır.
 * tablonun adı, var ise şema adı gibi bilgileri içerir.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "tblpersonel")
public class Personel extends BaseEntity {
    //    // otomatik artan bir syı dizisi oluşturur.
//    @SequenceGenerator(name="sq_tblpersonel_id",sequenceName = "sq_tblpersonel_id",
//    initialValue = 1000,allocationSize = 5)
//    // oluşturulan sq adını eklemeliyiz.
//    @GeneratedValue(generator = "sq_tblpersonel_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String ad;
    /**
     * Hibernate bu sınıftan bir tablo oluştururken default değerleri kullanmasınn
     * istiyor isek column ile özellşetirmete gitmeliyiz.
     * insertable = false, -> eklenebilme özelliğini kapatır.
     * updatable = false, -> güncellenebilme özelliğini kapatır.
     * nullable = false -> alanın null değer olmasını engeller
     * unique = true  -> alanın unique(benzersiz ve tekrarsız) olmasını sağlar.
     * name = "personel_adresi" -> DB deki alan adının ne olcağını belirtir.
     */
    @Column(length = 500)
    String adres;
    String telefon;


}
