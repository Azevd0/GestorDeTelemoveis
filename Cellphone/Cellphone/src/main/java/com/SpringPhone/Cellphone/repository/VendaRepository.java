package com.SpringPhone.Cellphone.repository;

import com.SpringPhone.Cellphone.model.purchase.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface VendaRepository extends JpaRepository<Venda, UUID> {
    List<Venda> findAllByDataCompraAfter(LocalDateTime data);}