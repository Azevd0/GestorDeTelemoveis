package com.SpringPhone.Cellphone.repository;

import com.SpringPhone.Cellphone.model.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MarcaRepository extends JpaRepository<Marca,Long> {

    Optional<Marca> findByNomeContainingIgnoreCase(String nome);
    boolean existsByNomeIgnoreCase(String nome);

}
