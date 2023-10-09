package br.ueg.atividade01.prog.web.mapper;

import br.ueg.atividade01.prog.web.dto.PessoaDTO;
import br.ueg.atividade01.prog.web.dto.PessoaListaDTO;
import br.ueg.atividade01.prog.web.model.Pessoa;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Component
public class PessoaMapperImpl implements PessoaMapper{
    @Override
    public PessoaDTO toPessoaDTO(Pessoa pessoa) {
        PessoaDTO pessoaDTO = new PessoaDTO();
        pessoaDTO.setIdPessoa(pessoa.getIdPessoa());
        pessoaDTO.setNomePessoa(pessoa.getNomePessoa());
        pessoaDTO.setEmailPessoa(pessoa.getEmailPessoa());

        return pessoaDTO;
    }

    @Override
    public List<PessoaListaDTO> toListaPessoaDTO(List<Pessoa> pessoas) {
        List<PessoaListaDTO> listaPessoasDTO =new ArrayList<>();
        for(Pessoa p: pessoas){
            PessoaListaDTO pessoaListaDTO = new PessoaListaDTO();
            pessoaListaDTO.setEmailPessoa(p.getEmailPessoa());
            pessoaListaDTO.setNomePessoa(p.getNomePessoa());
            listaPessoasDTO.add(pessoaListaDTO);
        }
        return listaPessoasDTO;
    }

    @Override
    public Pessoa toPessoaModel(PessoaDTO pessoaDTO) {
        Pessoa pessoa = new Pessoa();
        pessoa.setIdPessoa(pessoaDTO.getIdPessoa());
        pessoa.setEmailPessoa(pessoaDTO.getEmailPessoa());
        pessoa.setNomePessoa(pessoaDTO.getNomePessoa());
        return pessoa;
    }
}
