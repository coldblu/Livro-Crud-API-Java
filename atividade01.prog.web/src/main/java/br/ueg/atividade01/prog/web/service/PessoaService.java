package br.ueg.atividade01.prog.web.service;

import br.ueg.atividade01.prog.web.dto.PessoaListaDTO;
import br.ueg.atividade01.prog.web.model.Pessoa;

import java.util.List;
import java.util.Optional;

public interface PessoaService {
    Pessoa incluirPessoa(Pessoa pessoa);
    Pessoa alterarPessoa(Pessoa pessoa, long idPessoa);
    Optional<Pessoa> buscarPessoaPeloId(long idPessoa);
    Optional<Pessoa> buscarPessoaPorEmail(String email);
    Optional<Pessoa> excluirPessoa(long idPessoa);
    List<PessoaListaDTO> listarTodasPessoas();
}
