package br.ueg.atividade01.prog.web.service.impl;

import br.ueg.atividade01.prog.web.dto.PessoaDTO;
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
        Optional<Pessoa> pessoaBD = pessoaRepository.findPessoaByIdPessoa(idPessoa);
        if(pessoaBD.isPresent()){
            Pessoa pessoaAlterar = pessoaBD.get();
            pessoaAlterar.setNomePessoa(pessoa.getNomePessoa());
            pessoaAlterar.setEmailPessoa(pessoa.getEmailPessoa());
            return pessoaRepository.save(pessoaAlterar);
        } else {
            throw new NotFoundException("Pessoa não encontrado");
        }
    }

    @Override
    public Optional<Pessoa> buscarPessoaPeloId(long idPessoa) {
        Optional<Pessoa> pessoaBD = pessoaRepository.findPessoaByIdPessoa(idPessoa);
        return pessoaBD;
    }

    @Override
    public Optional<Pessoa> buscarPessoaPorEmail(String email) {
        Optional<Pessoa> pessoaBD = pessoaRepository.findPessoaByEmailPessoa(email);
        return pessoaBD;
    }

    @Override
    public Optional<Pessoa> excluirPessoa(long idPessoa) {
        Optional<Pessoa> pessoaBD = pessoaRepository.findPessoaByIdPessoa(idPessoa);
        pessoaRepository.deleteById(idPessoa);
        return pessoaBD;
    }

    @Override
    public List<PessoaDTO> listarTodasPessoas() {
        List<PessoaDTO> listaPessoas = pessoaMapper.toDTO(pessoaRepository.findAll());
        return listaPessoas;
    }
}
