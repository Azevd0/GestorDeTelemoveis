package com.SpringPhone.Cellphone.controller;

import com.SpringPhone.Cellphone.dto.MarcaDto;
import com.SpringPhone.Cellphone.model.request.MarcaRegisterRequest;
import com.SpringPhone.Cellphone.service.MarcaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/marcas")
public class MarcaController {

    private final MarcaService marcaService;

    public MarcaController(MarcaService marcaService) {
        this.marcaService = marcaService;
    }

    @GetMapping
    public ResponseEntity<List<MarcaDto>> listAll() {
        return ResponseEntity.ok(marcaService.findAll());
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<MarcaDto> findByNome(@PathVariable String nome) {
        return ResponseEntity.ok(marcaService.findByNome(nome));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MarcaDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(marcaService.findById(id));
    }

    @PostMapping
    public ResponseEntity<MarcaDto> criarMarca(@RequestBody MarcaRegisterRequest dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(marcaService.criarMarca(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MarcaDto> update(@PathVariable Long id, @RequestBody MarcaRegisterRequest dto) {
        return ResponseEntity.ok(marcaService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMarca(@PathVariable Long id) {
        marcaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}