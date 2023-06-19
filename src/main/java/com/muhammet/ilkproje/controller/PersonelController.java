package com.muhammet.ilkproje.controller;

import com.muhammet.ilkproje.repository.entity.Personel;
import com.muhammet.ilkproje.service.PersonelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/personel")
public class PersonelController {
    private final PersonelService personelService;

    /**
     * http://localhost:9090/personel/save?ad=muhammet
     * @param ad
     * @return
     */
    @GetMapping("/save")
    public ResponseEntity<Void> save(String ad){
        personelService.save(Personel.builder()
                        .ad(ad)
                .build());
        return ResponseEntity.ok().build();
    }

    /**
     * http://localhost:9090/personel/findAll
     * @return
     */
    @GetMapping("/findAll")
    public ResponseEntity<List<Personel>> findAll(){
        return ResponseEntity.ok(personelService.findAll());
    }
}
