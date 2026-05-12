package com.SpringPhone.Cellphone.controller;

import com.SpringPhone.Cellphone.dto.CelularDto;
import com.SpringPhone.Cellphone.enums.PeriodoDeVendas;
import com.SpringPhone.Cellphone.model.purchase.CompraRequest;
import com.SpringPhone.Cellphone.model.purchase.Venda;
import com.SpringPhone.Cellphone.model.request.CelularRegisterRequest;
import com.SpringPhone.Cellphone.service.CelularService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/celulares")
public class CelularController {

    private final CelularService celularService;

    public CelularController(CelularService celularService) {
        this.celularService = celularService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CelularDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(celularService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<CelularDto>> listByMarca(@RequestParam(value = "marca") Long idMark) {
        return ResponseEntity.ok(celularService.findAllByMarca(idMark));
    }
    @GetMapping("/vendas")
    public ResponseEntity<List<Venda>> listarHistorico(
            @RequestParam(value = "periodo", defaultValue = "HOJE") PeriodoDeVendas periodo) {

        List<Venda> historico = celularService.getHistorico(periodo);
        return ResponseEntity.ok(historico);
    }

    @PostMapping
    public ResponseEntity<CelularDto> registrarCelular(@Valid @RequestBody CelularRegisterRequest dto) {
        CelularDto createdDto = celularService.registrarCelular(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDto);
    }

    @PostMapping("/comprar")
    public ResponseEntity<Venda> comprar(@RequestBody CompraRequest request) {
        Venda comprovante = celularService.comprar(request);
        return ResponseEntity.ok(comprovante);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CelularDto> update(
            @RequestParam(value = "marca") Long idMark,
            @PathVariable Long id,
            @Valid @RequestBody CelularDto dto) {
        return ResponseEntity.ok(celularService.update(idMark, id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        celularService.delete(id);
        return ResponseEntity.noContent().build();
    }
}