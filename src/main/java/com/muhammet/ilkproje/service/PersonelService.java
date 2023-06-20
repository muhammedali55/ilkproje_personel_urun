package com.muhammet.ilkproje.service;

import com.muhammet.ilkproje.dto.request.PersonelSaveRequestDto;
import com.muhammet.ilkproje.repository.IPersonelRepository;
import com.muhammet.ilkproje.repository.entity.Personel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonelService {
    //1- @Autowired

    private final IPersonelRepository repository;

    // 2-
//    public PersonelService(IPersonelRepository repository){
//        this.repository = repository;
//    }

    public void save(Personel personel){
        repository.save(personel);
    }

    public List<Personel> findAll(){
        return repository.findAll();
    }

    /**
     * 1. yöntem dönüşüm şekline elle yapılan şekli.
     * @param dto
     */
    public void save(PersonelSaveRequestDto dto){
        Personel personel = Personel.builder()
                .ad(dto.getAd())
                .adres(dto.getAdres())
                .telefon(dto.getTelefon())
                .build();
        repository.save(personel);
    }

}
