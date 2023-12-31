package com.muhammet.ilkproje.controller;

import com.muhammet.ilkproje.config.security.JwtTokenManager;
import com.muhammet.ilkproje.constants.RestApis;
import com.muhammet.ilkproje.dto.request.PersonelSaveRequestDto;
import com.muhammet.ilkproje.exceptions.ErrorType;
import com.muhammet.ilkproje.exceptions.IlkprojeException;
import com.muhammet.ilkproje.repository.entity.Personel;
import com.muhammet.ilkproje.repository.view.VwPersonel;
import com.muhammet.ilkproje.service.PersonelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static com.muhammet.ilkproje.constants.RestApis.*;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(PERSONEL)
public class PersonelController {
    private final PersonelService personelService;
    private final JwtTokenManager jwtTokenManager;
    @Value("${myapplication.kafanizagoreyazabilirsiniz}")
    private String deger;

//    @Value("${myapplication.list}")
//    // Örnek Ayarlayalım
//    private Map<String,String> list;

    /**
     * http://localhost:9090/personel/save?ad=muhammet
     *
     * @return
     */
    @PostMapping(SAVE)
    public ResponseEntity<Void> save(@RequestBody @Valid PersonelSaveRequestDto dto){
        personelService.save(dto);
        return ResponseEntity.ok().build();
    }

    /**
     * http://localhost:9090/personel/findAll
     * @return
     */
    @GetMapping(FINDALL)
    public ResponseEntity<List<Personel>> findAll(){
        return ResponseEntity.ok(personelService.findAll());
    }

    @GetMapping("/getvalue")
    public String getapplicationValue(){
        return deger;
    }
//
//    @GetMapping("/getlist")
//    public Map<String,String> getapplicationList(){
//        return list;
//    }

    @GetMapping("/findbyadpersonel")
    public ResponseEntity<List<Personel>> findByAdPersonel(String ad){
        return ResponseEntity.ok(personelService.findByAd(ad));
    }

    @GetMapping(value = "/personelvarmi", headers = "application/json")
    public ResponseEntity<Boolean> personelVarMi(String ad,String adres){
        return ResponseEntity.ok(personelService.personelVarMi(ad,adres));
    }

    @GetMapping("/getviewpersonel")
    public ResponseEntity<List<VwPersonel>>findAllVwpersonel(){
        return ResponseEntity.ok(personelService.findAllVwpersonel());
    }

    @GetMapping("/hatafirlat")
    @PreAuthorize("hasAuthority('Admin_User') or hasAuthority('Super_User')")
    public ResponseEntity<String> hataFirlat(boolean isHata){
        if (isHata)
            throw new IlkprojeException(ErrorType.PERSONEL_BULUNAMADI);
       throw new RuntimeException("Runtime Exception");
    }

    @PostMapping("/gettoken")
    public ResponseEntity<String> getTokenById(Long id){
        return ResponseEntity.ok(jwtTokenManager.createToken(id).get());
    }

    @GetMapping("/getidfromtoken")
    public ResponseEntity<Long> getByIdFromToken(String token){
        return ResponseEntity.ok(jwtTokenManager.getUserIdFromToken(token).get());
    }
}
