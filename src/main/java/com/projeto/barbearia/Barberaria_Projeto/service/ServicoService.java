package com.projeto.barbearia.Barberaria_Projeto.service;

import com.projeto.barbearia.Barberaria_Projeto.dto.ServicoCreateDTO;
import com.projeto.barbearia.Barberaria_Projeto.dto.ServicoResponseDTO;
import com.projeto.barbearia.Barberaria_Projeto.dto.ServicoUpdateDTO;
import com.projeto.barbearia.Barberaria_Projeto.model.Servico;
import com.projeto.barbearia.Barberaria_Projeto.repository.ServicoRepository;
import jakarta.persistence.EntityNotFoundException;
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

    // Buscar Por Id
    public ServicoResponseDTO buscarPorId(Long id) {
        return rep.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Serviço não encontrado com ID " + id));
    }

    // Buscar Por Nome
    public ServicoResponseDTO buscarPorNome (String nome){
        return rep.findByNome(nome)
                .map(this::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Serviço Não Encontrado Com Esse Nome" + nome));
    }

    // Atualizar serviço
    @Transactional
    public ServicoResponseDTO atualizar (ServicoUpdateDTO atualizar){
        Servico servico = rep.findById(atualizar.id())
                .orElseThrow(() -> new EntityNotFoundException("Serviço não Encontrado"));

        if (!servico.getNome().equals(atualizar.nome()) && rep.findByNome(atualizar.nome()).isPresent()) {
            throw new DataIntegrityViolationException("Já existe um serviço com este nome.");
        }
        if (atualizar.preco().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O preço deve ser maior que zero.");
        }
        servico.setNome(atualizar.nome());
        servico.setPreco(atualizar.preco());

        return toDTO(rep.save(servico));
    }

    // Deletar serviço
    @Transactional
    public void deletar (Long id){
        Servico servico = rep.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Serviço não encontrado"));
        if (rep.countAgendamentosAtivos(servico) > 0){
            throw new IllegalStateException("Não é possível excluir serviço com agendamentos ativos.");
        }
        rep.delete(servico);
    }
}