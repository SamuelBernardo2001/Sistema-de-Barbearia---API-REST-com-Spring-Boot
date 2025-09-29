package com.projeto.barbearia.Barberaria_Projeto.service;

import com.projeto.barbearia.Barberaria_Projeto.dto.ServicoCreateDTO;
import com.projeto.barbearia.Barberaria_Projeto.dto.ServicoResponseDTO;
import com.projeto.barbearia.Barberaria_Projeto.dto.ServicoUpdateDTO;
import com.projeto.barbearia.Barberaria_Projeto.model.Servico;
import com.projeto.barbearia.Barberaria_Projeto.repository.ServicoRepository;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServicoService {

    private final ServicoRepository rep;

    public ServicoService(ServicoRepository servicoRepository){
        this.rep = servicoRepository;
    }

    // Converter CreateDTO -> Entity
    private Servico toEntityCreate (ServicoCreateDTO dto){
        return new Servico(null, dto.nome(), dto.preco());
    }

    // Converte UpdateDTo -> Entity
    private Servico toEntityUpdate(ServicoUpdateDTO dto) {
        return new Servico(dto.id(), dto.nome(), dto.preco());
    }

    // Converte Entity -> DTO
    private ServicoResponseDTO toDTO(Servico servico){
        return new ServicoResponseDTO(servico.getId(), servico.getNome(), servico.getPreco());
    }

    // Criar novo serviço com validações
    @Transactional
    public ServicoResponseDTO salvar (ServicoCreateDTO salvar){
        if(rep.findByNome(salvar.nome()).isPresent()){
            throw new DataIntegrityViolationException("Já existe um serviço com o nome informado.");
        }

        if (salvar.preco().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O preço deve ser maior que zero.");
        }

        Servico entity = toEntityCreate(salvar);
        return toDTO(rep.save(entity));
    }

    // Listar todos
    public List<ServicoResponseDTO> listar(){
        return rep.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
