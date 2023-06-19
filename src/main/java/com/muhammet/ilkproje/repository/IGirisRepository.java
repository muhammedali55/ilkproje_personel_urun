package com.muhammet.ilkproje.repository;

import com.muhammet.ilkproje.repository.entity.Giris;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGirisRepository extends JpaRepository<Giris,Long> {

}
