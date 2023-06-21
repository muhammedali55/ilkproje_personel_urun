package com.muhammet.ilkproje.repository;

import com.muhammet.ilkproje.repository.entity.Personel;
import com.muhammet.ilkproje.repository.view.VwPersonel;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IPersonelRepository extends JpaRepository<Personel,Long> {

    /**
     * select * from tblpersonel where ad='muhammet'
     * 1- find: başlanır
     * 2- [All, Top, Optional. vs.]: araya bu kelimeler gelebilir
     * 3- By:
     * 4- [Entity Column]: arama yapılack alan
     * 5- [AND, OR, OrderBy, True vs.]
     * 6- method içine alınacak parametreler
     * NOTLAR:
     * - tablo adı dikkate alınarak kolon adının ilk harfi büyük yazılır.
     *   (findByAd -> find-by-ad)
     * - Method içine kabul ettiğiniz değerlerin tipleri önemli adları önemsizdir.
     */
    List<Personel> findAllByAd(String ad);
    Personel findByAd(String ad);

    /**
     * Null Check yerine optinal kullanın
     * Optional ->
     */
    Optional<Personel> findOptionalByAd(String ad);

    /**
     * select * from tblpersonel where ad=1? and adres=2?
     * @param merhaba_adin_nedir
     * @param adres
     * @return persone lisgesini döner
     */
    List<Personel> findAllByAdAndAdres(String merhaba_adin_nedir, String adres);
    List<Personel> findAllByAdOrAdres(String merhaba_adin_nedir, String adres);

    /**
     * select * from tblpersonel where ad like 'm%'
     * @param ad
     * @return
     */
    List<Personel> findAllByAdStartingWith(String ad); // mu
    // select * from tblpersonel where ad like '%m%'
    List<Personel> findAllByAdContaining(String ad);
    // select * from tblpersonel where UPPER(ad) = UPPER('muhammet')
    // select * from tblpersonel where ad ilike '%m%'
    List<Personel> findAllByAdIgnoreCase(String ad);

    /**
     * select ad from tblpersonel order by ad desc
     */
    List<Personel> findAllByOrderByAd(); // a...z
    List<Personel> findAllByOrderByAdDesc(); // z...a

    /**
     * Top  - Limit  -> "Top" tek kayıt getirir.
     * "Top5" -> 5 kayıt getirir.
     * En son eklenmiş olan 3 kişiyi getir.
     * TODO: Buna bakalım hata fırlatıyor.
     */

     List<Personel> findTop3ByOrderByCreateatDesc();

    /**
     * select * from tblpersonel where id in (3,543,544,34,5)
     *
     * select * from tblpersonel where id in (
     *  select userid from tblresimler where location = 'Ankara'
     * )
     * id leri verilen personellerin listesini getirir.
     */
    List<Personel> findAllByIdIn(List<Long> ids);

   List<Personel> findAllByCreateatBetween(Long start, Long end);

    /**
     * LessThan -> tarih<01.01.2023
     * LessThanEquals -> tarih<=01.01.2023
     * GreaterThan, GreaterThanEquals
     *
     * IsNull, IsNotNull
     * tarih = null  - not null
     *
     * isActive  boolean
     *
     * isActiveTrue - False
     *
     */


    @Query("select p from Personel p where p.ad= ?1")
    List<Personel> bulAdinaGore(String ad);

    @Query("select p from Personel p where p.ad= ?1 and p.adres= ?2")
    List<Personel> bulAdinaveAdresineGore(String ad,String adres);

    @Query("select p from Personel p where p.ad= :ad and p.adres= :adres")
    List<Personel> bulAdinaveAdresineGoreParametre(
            @Param("ad") String ad,
            @Param("adres") String adres);


    @Query(value = "select * from tblpersonel where ad = ?1", nativeQuery = true)
    List<Personel> bulGirisTarigineGore(String ad);

    @Query("select count(p)>0 from Personel p where p.ad = ?1 and p.adres=?2 ")
    Boolean personelVarMi(String ad, String adres);


    @Query("select new com.muhammet.ilkproje.repository.view.VwPersonel(p.ad,p.adres,p.telefon) from Personel p")
    List<VwPersonel> findAllVwpersonel();



}
