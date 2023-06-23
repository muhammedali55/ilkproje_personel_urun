package com.muhammet.ilkproje.service;

import com.muhammet.ilkproje.config.security.JwtTokenManager;
import com.muhammet.ilkproje.dto.request.PersonelSaveRequestDto;
import com.muhammet.ilkproje.exceptions.ErrorType;
import com.muhammet.ilkproje.exceptions.IlkprojeException;
import com.muhammet.ilkproje.mapper.IPersonelMapper;
import com.muhammet.ilkproje.repository.IPersonelRepository;
import com.muhammet.ilkproje.repository.entity.Personel;
import com.muhammet.ilkproje.repository.view.VwPersonel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static com.muhammet.ilkproje.mapper.IPersonelMapper.*;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonelService {
    //1- @Autowired

    private final IPersonelRepository repository;
    private final JwtTokenManager jwtTokenManager;
    // 2-
//    public PersonelService(IPersonelRepository repository){
//        this.repository = repository;
//    }

    public Personel save(Personel personel){
       return repository.save(personel);
    }

    public List<Personel> findAll(){
        return repository.findAll();
    }

    /**
     * 1. yöntem dönüşüm şekline elle yapılan şekli.
     * @param dto
     */
    public void save(PersonelSaveRequestDto dto){
//        1.
//        Personel personel = Personel.builder()
//                .ad(dto.getAd())
//                .adres(dto.getAdres())
//                .telefon(dto.getTelefon())
//                .build();
//             repository.save(personel);
        // 2.
//        Personel personel = IPersonelMapper.INSTANCE.fromPersonelDto(dto);
//        repository.save(personel);
        //3.
        Optional<Long> userId = jwtTokenManager.getUserIdFromToken(dto.getToken());
        if(userId.isEmpty())
            throw new IlkprojeException(ErrorType.INVALID_TOKEN);
        repository.save(INSTANCE.fromPersonelDto(dto));
    }

    public List<Personel> findByAd(String ad){
        return repository.bulGirisTarigineGore(ad);
    }

    public Boolean personelVarMi(String ad,String adres){
     return   repository.personelVarMi(ad,adres);
    }

    public List<VwPersonel> findAllVwpersonel(){
        return repository.findAllVwpersonel();
    }
}
