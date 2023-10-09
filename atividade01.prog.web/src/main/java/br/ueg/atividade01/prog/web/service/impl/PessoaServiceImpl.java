package br.ueg.atividade01.prog.web.service.impl;

import br.ueg.atividade01.prog.web.dto.PessoaListaDTO;
import br.ueg.atividade01.prog.web.mapper.PessoaMapper;
import br.ueg.atividade01.prog.web.model.Pessoa;
import br.ueg.atividade01.prog.web.repository.PessoaRepository;
import br.ueg.atividade01.prog.web.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;

@Service
@Component

public class PessoaServiceImpl implements PessoaService {
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private PessoaMapper pessoaMapper;
    @Override
    public Pessoa incluirPessoa(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    @Override
    public Pessoa alterarPessoa(Pessoa pessoa, long idPessoa) {
        Pessoa pessoaBD = pessoaRepository.findPessoaByIdPessoa(idPessoa);
        if(pessoaBD != null){
            pessoaBD.setNomePessoa(pessoa.getNomePessoa());
            pessoaBD.setEmailPessoa(pessoa.getEmailPessoa());
            return pessoaRepository.save(pessoaBD);
        } else {
            throw new NotFoundException("Pessoa não encontrado");
        }
    }

    @Override
    public Optional<Pessoa> buscarPessoaPeloId(long idPessoa) {
        return Optional.ofNullable(pessoaRepository.findPessoaByIdPessoa(idPessoa));
    }

    @Override
    public Pessoa buscarPessoaPorEmail(String email) {
        return pessoaRepository.findPessoaByEmailPessoa(email);
    }

    @Override
    public Pessoa excluirPessoa(long idPessoa) {
        Pessoa pessoaBD = pessoaRepository.findPessoaByIdPessoa(idPessoa);
        pessoaRepository.deleteById(idPessoa);
        return pessoaBD;
    }

    @Override
    public List<PessoaListaDTO> listarTodasPessoas() {
        return pessoaMapper.toListaPessoaDTO(pessoaRepository.findAll());
    }
}
