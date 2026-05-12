package com.SpringPhone.Cellphone.service;

import com.SpringPhone.Cellphone.dto.CelularDto;
import com.SpringPhone.Cellphone.enums.PeriodoDeVendas;
import com.SpringPhone.Cellphone.exceptions.ObjectNotFoundException;
import com.SpringPhone.Cellphone.model.Celular;
import com.SpringPhone.Cellphone.model.Marca;
import com.SpringPhone.Cellphone.model.purchase.CompraRequest;
import com.SpringPhone.Cellphone.model.purchase.Venda;
import com.SpringPhone.Cellphone.model.request.CelularRegisterRequest;
import com.SpringPhone.Cellphone.repository.CelularRepository;
import com.SpringPhone.Cellphone.repository.MarcaRepository;
import com.SpringPhone.Cellphone.repository.VendaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.SpringPhone.Cellphone.enums.PeriodoDeVendas.*;

@Service
public class CelularService {

    private final CelularRepository celularRepository;
    private final MarcaRepository marcaRepository;
    private final ModelMapper modelMapper;
    private final VendaRepository vendaRepository;

    public CelularService(CelularRepository celularRepository, MarcaRepository marcaRepository, ModelMapper modelMapper, VendaRepository vendaRepository) {
        this.celularRepository = celularRepository;
        this.marcaRepository = marcaRepository;
        this.modelMapper = modelMapper;
        this.vendaRepository = vendaRepository;
    }

    @Transactional(readOnly = true)
    public CelularDto findById(Long id) {
        Celular entity = celularRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Celular não encontrado com ID: " + id));
        return convertToDto(entity);
    }

    @Transactional(readOnly = true)
    public List<CelularDto> findAllByMarca(Long idMark) {
        if (!marcaRepository.existsById(idMark)) {
            throw new ObjectNotFoundException("Marca não encontrada");
        }
        return celularRepository.findAllByMarcaId(idMark)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public CelularDto registrarCelular(CelularRegisterRequest request) {
        Marca marca = marcaRepository.findById(request.getMarcaId())
                .orElseThrow(() -> new ObjectNotFoundException("Marca não encontrada"));

        Celular celular = new Celular();
        celular.setModelo(request.getModelo());
        celular.setAno(request.getAno());
        celular.setPreco(request.getPreco());
        celular.setQtd(request.getQtd());
        celular.setMarca(marca);
        celularRepository.save(celular);

        return convertToDto(celular);
    }

    @Transactional
    public CelularDto update(Long idMark, Long idCll, CelularDto dto) {
        Celular cellExistente = celularRepository.findById(idCll)
                .orElseThrow(() -> new ObjectNotFoundException("Celular inexistente"));

        Marca marca = marcaRepository.findById(idMark)
                .orElseThrow(() -> new ObjectNotFoundException("Marca não encontrada"));

        cellExistente.setModelo(dto.getModelo());
        cellExistente.setPreco(dto.getPreco());
        cellExistente.setAno(dto.getAno());
        cellExistente.setQtd(dto.getQuantidade());
        cellExistente.setMarca(marca);

        return convertToDto(celularRepository.save(cellExistente));
    }


    @Transactional
    public Venda comprar(CompraRequest request) {
        Celular celular = celularRepository.findById(request.getCelularId())
                .orElseThrow(() -> new ObjectNotFoundException("Celular não encontrado"));

        if (!celular.getMarca().getId().equals(request.getMarcaId())) {
            throw new IllegalArgumentException("Marca incompatível com o modelo selecionado");
        }

        if (celular.getQtd() < request.getQuantidade()) {
            throw new IllegalArgumentException("Estoque insuficiente. Disponível: " + celular.getQtd());
        }

        celular.setQtd(celular.getQtd() - request.getQuantidade());
        celularRepository.save(celular);

        BigDecimal valorTotal = celular.getPreco().multiply(new BigDecimal(request.getQuantidade()));
        UUID uuidVenda = UUID.randomUUID();
        LocalDateTime agora = LocalDateTime.now();

        Venda vendaEntity = new Venda(
                uuidVenda,
                agora,
                celular.getModelo(),
                request.getQuantidade(),
                valorTotal
        );
        return vendaRepository.save(vendaEntity);
    }
    @Transactional(readOnly = true)
    public List<Venda> getHistorico(PeriodoDeVendas periodo) {
        LocalDateTime dataLimite;

        // Sintaxe compatível com Java 11
        switch (periodo) {
            case HOJE: dataLimite = LocalDateTime.now().withHour(0).withMinute(0);
                break;
            case ESTA_SEMANA: dataLimite = LocalDateTime.now().minusDays(7);
                break;
            case ESTA_QUIZENA: dataLimite = LocalDateTime.now().minusDays(15);
                break;
            case ESTE_MES: default: dataLimite = LocalDateTime.now().minusDays(30);
                break;
        }

        return vendaRepository.findAllByDataCompraAfter(dataLimite)
                .stream()
                .map(venda -> modelMapper.map(venda, Venda.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        if (!celularRepository.existsById(id)) {
            throw new ObjectNotFoundException("Erro ao deletar: ID " + id + " não existe.");
        }
        celularRepository.deleteById(id);
    }

    private CelularDto convertToDto(Celular entity) {
        CelularDto dto = modelMapper.map(entity, CelularDto.class);
        dto.setQuantidade(entity.getQtd());
        return dto;
    }

    private Celular convertToEntity(CelularDto dto) {
        Celular entity = modelMapper.map(dto, Celular.class);
        entity.setQtd(dto.getQuantidade());
        return entity;
    }
}