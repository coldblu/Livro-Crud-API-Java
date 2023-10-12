package br.ueg.atividade01.prog.web.mapper;

import br.ueg.atividade01.prog.web.dto.EmprestimoDTO;
import br.ueg.atividade01.prog.web.dto.EmprestimoListaDTO;
import br.ueg.atividade01.prog.web.model.Emprestimo;
import br.ueg.atividade01.prog.web.service.LivroService;
import br.ueg.atividade01.prog.web.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Component
public class EmprestimoMapperImpl implements EmprestimoMapper{
    @Autowired
    private LivroService livroService;
    @Autowired
    private PessoaService pessoaService;

    @Override
    public EmprestimoDTO toEmprestimoDTO(Emprestimo emprestimo) {
        if(emprestimo.getPessoa() != null && emprestimo.getLivro() != null ) {
            EmprestimoDTO emprestimoDTO = new EmprestimoDTO();
            emprestimoDTO.setIdEmprestimo(emprestimo.getIdEmprestimo());
            emprestimoDTO.setLivroID(emprestimo.getLivro().getIdLivro());
            emprestimoDTO.setPessoaID(emprestimo.getPessoa().getIdPessoa());
            emprestimoDTO.setLivroNome(emprestimo.getLivro().getTitulo());
            emprestimoDTO.setPessoaNome(emprestimo.getPessoa().getNomePessoa());
            emprestimoDTO.setPessoaEmail(emprestimo.getPessoa().getEmailPessoa());
            emprestimoDTO.setDataEmprestimo(emprestimo.getDataEmprestimo());
            emprestimoDTO.setDataDevolucao(emprestimo.getDataDevolucao());

            return emprestimoDTO;
        }
        else return  null;
    }

    @Override
    public Emprestimo toEmprestimoModel(EmprestimoDTO emprestimoDTO) {
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setIdEmprestimo(emprestimoDTO.getIdEmprestimo());
        emprestimo.setLivro(livroService.buscarLivroPeloId(emprestimoDTO.getLivroID()).orElseThrow(() -> new NoSuchElementException("Livro não encontrado")));
        emprestimo.setPessoa(pessoaService.buscarPessoaPeloId(emprestimoDTO.getPessoaID()).orElseThrow(() -> new NoSuchElementException("Pessoa não encontrada")));
        emprestimo.setDataEmprestimo(emprestimoDTO.getDataEmprestimo());
        emprestimo.setDataDevolucao(emprestimoDTO.getDataDevolucao());
        return emprestimo;
    }

    @Override
    public List<EmprestimoListaDTO> toEmprestimoDTOList(List<Emprestimo> emprestimos) {
        List<EmprestimoListaDTO> emprestimosDTO = new ArrayList<>();
        if (!CollectionUtils.isEmpty(emprestimos)) {//Verifica se a lista nao e nula
            for (Emprestimo emprestimo : emprestimos) {
                EmprestimoListaDTO emprestimoListaDTO = new EmprestimoListaDTO();
                emprestimoListaDTO.setIdEmprestimo(emprestimo.getIdEmprestimo());
                emprestimoListaDTO.setLivroID(emprestimo.getLivro().getIdLivro());
                emprestimoListaDTO.setPessoaID(emprestimo.getPessoa().getIdPessoa());
                emprestimoListaDTO.setLivroNome(emprestimo.getLivro().getTitulo());
                emprestimoListaDTO.setPessoaNome(emprestimo.getPessoa().getNomePessoa());
                emprestimoListaDTO.setPessoaEmail(emprestimo.getPessoa().getEmailPessoa());
                emprestimoListaDTO.setDataEmprestimo(emprestimo.getDataEmprestimo());
                emprestimoListaDTO.setDataDevolucao(emprestimo.getDataDevolucao());
                emprestimosDTO.add(emprestimoListaDTO);
            }
        }
        return emprestimosDTO;
    }


}
