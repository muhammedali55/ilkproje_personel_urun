package com.muhammet.ilkproje.utility;

import com.muhammet.ilkproje.repository.entity.Personel;

import java.util.List;
import java.util.Optional;

/**
 *
 * @param <T> -> Entity sınıfının adı
 * @param <ID> -> Entitiy Id sinin data type ı
 */
public interface IService<T,ID>{

    T save(T entity);
    Iterable<T> saveAll(Iterable<T> entities);
   T update(T entity);
   void delete(T entity);
   void deleteById(ID id);
   Optional<T> findById(ID id);
   List<T> findAll();
}
