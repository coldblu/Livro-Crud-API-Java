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
import java.util.NoSuchElementException;
import java.util.Optional;
@Service
@Component
public class EmprestimoServiceImpl implements EmprestimoService {
    @Autowired
    private EmprestimoRepository emprestimoRepository;
    @Override
    public Emprestimo incluirEmprestimo(Emprestimo emprestimo) {
        emprestimo.setDataEmprestimo(LocalDate.now()); // Atualiza a data de empréstimo
        return emprestimoRepository.save(emprestimo);
    }

    @Override
    public List<Emprestimo> listarEmprestimosAtivos() {
        LocalDate currentDate = LocalDate.now();
        return emprestimoRepository.findAllByDataDevolucaoIsNullOrDataDevolucaoAfter(currentDate);
    }

    public List<Emprestimo> listarEmprestimosFinalizados() {
        LocalDate currentDate = LocalDate.now();
        LocalDate currentDatePlusOneDay = currentDate.plusDays(1);
        return emprestimoRepository.findAllByDataDevolucaoBefore(currentDatePlusOneDay);
    }



    @Override
    public Emprestimo finalizarEmprestimo(long id) {
        // Verificar se há o empréstimo no banco de dados
        Optional<Emprestimo> emprestimoExistenteBD = emprestimoRepository.findById(id);
        if (emprestimoExistenteBD.isPresent()) {
            Emprestimo emprestimoExistente = emprestimoExistenteBD.get();

            // Atualizar os atributos
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

        if (emprestimoExistenteBD.isPresent()) {
            emprestimoRepository.deleteById(id);
            return emprestimoExistenteBD;
        } else {
            throw new NoSuchElementException("O empréstimo com o ID " + id + " não foi encontrado.");
        }
    }


    @Override
    public Optional<Emprestimo> buscarEmprestimo(long id) {
        Optional<Emprestimo> emprestimoDB = emprestimoRepository.findById(id);
        return emprestimoDB;
    }

    @Override
    public boolean verificarEmprestimoAtivo(long livroId) {
        List<Emprestimo> emprestimosAtivos = emprestimoRepository.findByLivroIDAndDataDevolucaoIsNull(livroId);
        return !emprestimosAtivos.isEmpty();
    }

}
