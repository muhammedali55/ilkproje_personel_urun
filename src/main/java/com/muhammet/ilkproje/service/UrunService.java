package com.muhammet.ilkproje.service;

import com.muhammet.ilkproje.repository.IUrunRepository;
import com.muhammet.ilkproje.repository.entity.Urun;
import com.muhammet.ilkproje.utility.BaseService;
import com.muhammet.ilkproje.utility.IService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UrunService extends BaseService<Urun,Long> {
    private final IUrunRepository repository;
    public UrunService(IUrunRepository repository){
        super(repository);
        this.repository=repository;
    }
}
