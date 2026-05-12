package com.SpringPhone.Cellphone.service;

import com.SpringPhone.Cellphone.dto.MarcaDto;
import com.SpringPhone.Cellphone.exceptions.ObjectNotFoundException;
import com.SpringPhone.Cellphone.model.Marca;
import com.SpringPhone.Cellphone.model.request.MarcaRegisterRequest;
import com.SpringPhone.Cellphone.repository.MarcaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MarcaService {

    private final MarcaRepository marcaRepository;
    private final ModelMapper modelMapper;

    public MarcaService(MarcaRepository marcaRepository, ModelMapper modelMapper) {
        this.marcaRepository = marcaRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional(readOnly = true)
    public List<MarcaDto> findAll() {
        return marcaRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public MarcaDto findById(Long id) {
        Marca marca = marcaRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Marca não cadastrada com Id " + id));
        return convertToDto(marca);
    }

    @Transactional(readOnly = true)
    public MarcaDto findByNome(String nome) {
        Marca marca = marcaRepository.findByNomeContainingIgnoreCase(nome)
                .orElseThrow(() -> new ObjectNotFoundException("Marca de nome " + nome + " não encontrada"));
        return convertToDto(marca);
    }

    @Transactional
    public MarcaDto criarMarca(MarcaRegisterRequest request) {
        validarNomeUnico(request.getNome());
        Marca marca = new Marca();
        marca.setNome(request.getNome());
        return modelMapper.map(marcaRepository.save(marca), MarcaDto.class);
    }

    @Transactional
    public MarcaDto update(Long id, MarcaRegisterRequest dto) {
        MarcaDto marcaExistente = findById(id);

        if (!marcaExistente.getNome().equalsIgnoreCase(dto.getNome())) {
            validarNomeUnico(dto.getNome());
        }
        Marca marcaParaAtualizar = modelMapper.map(dto, Marca.class);
        marcaParaAtualizar.setId(id);

        return convertToDto(marcaRepository.save(marcaParaAtualizar));
    }

    @Transactional
    public void delete(Long id) {
        Marca marca = marcaRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Id não encontrado"));

        if (marca.getCelulares() != null && !marca.getCelulares().isEmpty()) {
            throw new DataIntegrityViolationException("Erro! Marca não pode ser deletada, pois ainda contém itens associados");
        }
        marcaRepository.deleteById(id);
    }

    private void validarNomeUnico(String nome) {
        if (marcaRepository.existsByNomeIgnoreCase(nome)) {
            throw new IllegalArgumentException("Esta marca já existe");
        }
    }

    private MarcaDto convertToDto(Marca marca) {
        return modelMapper.map(marca, MarcaDto.class);
    }
}