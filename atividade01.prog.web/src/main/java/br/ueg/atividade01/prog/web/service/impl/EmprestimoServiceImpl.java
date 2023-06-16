package br.ueg.atividade01.prog.web.service.impl;

import br.ueg.atividade01.prog.web.model.Emprestimo;
import br.ueg.atividade01.prog.web.repository.EmprestimoRepository;
import br.ueg.atividade01.prog.web.service.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@Service
@Component
public class EmprestimoServiceImpl implements EmprestimoService {
    @Autowired
    private EmprestimoRepository emprestimoRepository;
    @Override
    public Emprestimo incluirEmprestimo(Emprestimo emprestimo) {
        emprestimo.setEmprestimoAtivo(true); // Define o empréstimo como ativo
        return emprestimoRepository.save(emprestimo);
    }

    @Override
    public List<Emprestimo> listarEmprestimosAtivos() {
        return emprestimoRepository.findByEmprestimoAtivo(true);
    }

    @Override
    public Emprestimo finalizarEmprestimo(long id) {
        // Verificar se há o empréstimo no banco de dados
        Optional<Emprestimo> emprestimoExistenteBD = emprestimoRepository.findById(id);
        if (emprestimoExistenteBD.isPresent()) {
            Emprestimo emprestimoExistente = emprestimoExistenteBD.get();

            // Atualizar os atributos relevantes do objeto existente com os valores adequados
            emprestimoExistente.setEmprestimoAtivo(false);
            emprestimoExistente.setDataDevolucao(LocalDate.now()); // Definir a data de devolução como a data atual

            // Salvar o objeto atualizado
            return emprestimoRepository.save(emprestimoExistente);
        } else {
            // Empréstimo com o ID fornecido não encontrado
            throw new NotFoundException("Emprestimo não encontrado");
        }
    }

    @Override
    public Optional<Emprestimo> excluirEmprestimo(long id) {
        Optional<Emprestimo> emprestimoExistenteBD = emprestimoRepository.findById(id);
        emprestimoRepository.deleteById(id);
        return null;
    }

    @Override
    public Optional<Emprestimo> buscarEmprestimo(long id) {
        Optional<Emprestimo> emprestimoDB = emprestimoRepository.findById(id);
        return emprestimoDB;
    }
}
