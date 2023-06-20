package com.muhammet.ilkproje.repository;

import com.muhammet.ilkproje.repository.entity.Urun;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUrunRepository extends JpaRepository<Urun,Long> {
}
