package br.ueg.atividade01.prog.web;

import br.ueg.atividade01.prog.web.dto.CadastroDTO;
import br.ueg.atividade01.prog.web.model.Emprestimo;
import br.ueg.atividade01.prog.web.model.Livro;
import br.ueg.atividade01.prog.web.model.Pessoa;
import br.ueg.atividade01.prog.web.model.Usuario;
import br.ueg.atividade01.prog.web.repository.LivroRepository;
import br.ueg.atividade01.prog.web.repository.PessoaRepository;
import br.ueg.atividade01.prog.web.repository.UsuarioRepository;
import br.ueg.atividade01.prog.web.service.UsuarioService;
import br.ueg.atividade01.prog.web.service.impl.AuthServiceImpl;
import br.ueg.atividade01.prog.web.service.impl.EmprestimoServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import java.time.LocalDate;


@SpringBootApplication(scanBasePackages = {
		"br.ueg.atividade01.*", // modificar conforme o pacote padrão do seu projeto
})
@EntityScan(basePackageClasses = { Jsr310JpaConverters.class }, basePackages ={"br.ueg.atividade01.prog.web.model"})
public class Application {
	private final LivroRepository livroRepository;
	private final EmprestimoServiceImpl emprestimoService;

	private final UsuarioService usuarioService;
	private final AuthServiceImpl authService;
	private final UsuarioRepository usuarioRepository;
	private final PessoaRepository pessoaRepository;


	public Application(LivroRepository livroRepository, EmprestimoServiceImpl emprestimoService, UsuarioService usuarioService, AuthServiceImpl authService, UsuarioRepository usuarioRepository, PessoaRepository pessoaRepository) {
		this.livroRepository = livroRepository;
		this.emprestimoService = emprestimoService;
		this.usuarioService = usuarioService;
		this.authService = authService;
		this.usuarioRepository = usuarioRepository;
		this.pessoaRepository = pessoaRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {
			System.out.println("Executado");

			//teste
			Livro livro = new Livro();
			livro.setTitulo("Percy Jackson e o Ladrão de Raios");
			livro.setAutor("Rick Riordan");
			livro.setAnoPublicacao(2005);
			livro.setEditora("Intrínseca");
			livro.setGenero("Fantasia");
			livro.setNumeroDePaginas(400);
			livro.setDataDeRegistro(LocalDate.now());

			try {
				livroRepository.save(livro);
			}catch(Exception e) {
				e.printStackTrace();
			}

			livro = new Livro();
			livro.setTitulo("O Apanhador no Campo de Centeio");
			livro.setAutor("J.D. Salinger");
			livro.setAnoPublicacao(1951);
			livro.setEditora("Editora do Autor");
			livro.setGenero("Romance");
			livro.setNumeroDePaginas(208);
			livro.setDataDeRegistro(LocalDate.now());

			try {
				livroRepository.save(livro);
			}catch(Exception e) {
				e.printStackTrace();
			}

			livro = new Livro();
			livro.setTitulo("1984");
			livro.setAutor("George Orwell");
			livro.setAnoPublicacao(1949);
			livro.setEditora("Companhia das Letras");
			livro.setGenero("Ficção distópica");
			livro.setNumeroDePaginas(336);
			livro.setDataDeRegistro(LocalDate.now());

			try {
				livroRepository.save(livro);
			}catch(Exception e) {
				e.printStackTrace();
			}

			//Usuarios /************************************************************/
			CadastroDTO cadastroDTO = new CadastroDTO();
			cadastroDTO.setEmailPessoa("admin");
			cadastroDTO.setNomePessoa("admin");
			cadastroDTO.setSenha("admin");
			authService.cadastrarPessoaUsuario(cadastroDTO);
			Usuario usuario = usuarioRepository.findUsuarioByEmailUsuario("admin");
			usuario.setRole("admin");
			usuarioRepository.save(usuario);


			// --------- Usuarios comuns -------------------------------------------/

            cadastroDTO = new CadastroDTO();
			cadastroDTO.setEmailPessoa("João Pessoa");
			cadastroDTO.setNomePessoa("joao.pessoa@gmail.com");
			cadastroDTO.setSenha("123456");
			authService.cadastrarPessoaUsuario(cadastroDTO);

            cadastroDTO = new CadastroDTO();
			cadastroDTO.setEmailPessoa("Maria Rosa");
			cadastroDTO.setNomePessoa("rosa.maria@gmail.com");
			cadastroDTO.setSenha("123456");
			authService.cadastrarPessoaUsuario(cadastroDTO);

            cadastroDTO = new CadastroDTO();
			cadastroDTO.setEmailPessoa("Carlos Magnus");
			cadastroDTO.setNomePessoa("magnus@carlos.br");
			cadastroDTO.setSenha("123456");
			authService.cadastrarPessoaUsuario(cadastroDTO);

			cadastroDTO = new CadastroDTO();
			cadastroDTO.setEmailPessoa("Elena Jett");
			cadastroDTO.setNomePessoa("jett.elena@hotmail.com");
			cadastroDTO.setSenha("123456");
			authService.cadastrarPessoaUsuario(cadastroDTO);

			cadastroDTO = new CadastroDTO();
			cadastroDTO.setEmailPessoa("Miranda Nanda");
			cadastroDTO.setNomePessoa("mira.nanda@yahoo.com");
			cadastroDTO.setSenha("123456");
			authService.cadastrarPessoaUsuario(cadastroDTO);

			cadastroDTO = new CadastroDTO();
			cadastroDTO.setEmailPessoa("Hans Aus");
			cadastroDTO.setNomePessoa("hans@gmail.com");
			cadastroDTO.setSenha("123456");
			authService.cadastrarPessoaUsuario(cadastroDTO);

			//************************************************************//

			/*
			//Emprestimo
			Emprestimo emprestimo = new Emprestimo();
			//Prmeiro
			emprestimo.setLivro(livroRepository.findLivroByidLivro(1));
			Pessoa pessoa = new Pessoa();
			pessoa = pessoaRepository.findPessoaByEmailPessoa("joao.pessoa@gmail.com");
			emprestimo.setPessoa(pessoa);
			emprestimo = emprestimoService.incluirEmprestimo(emprestimo);
			emprestimo = emprestimoService.finalizarEmprestimo(emprestimo.getIdEmprestimo());

			//Segundo
			emprestimo = new Emprestimo();
			emprestimo.setLivro(livroRepository.findLivroByidLivro(1));
            pessoa = new Pessoa();
			pessoa = pessoaRepository.findPessoaByEmailPessoa("rosa.maria@gmail.com");
			emprestimo.setPessoa(pessoa);
			emprestimo = emprestimoService.incluirEmprestimo(emprestimo);
			emprestimo = emprestimoService.finalizarEmprestimo(emprestimo.getIdEmprestimo());

			//Terceiro
			emprestimo = new Emprestimo();
			emprestimo.setLivro(livroRepository.findLivroByidLivro(2));
			pessoa = new Pessoa();
			pessoa = pessoaRepository.findPessoaByEmailPessoa("magnus@carlos.br");
			emprestimo.setPessoa(pessoa);
			emprestimo = emprestimoService.incluirEmprestimo(emprestimo);
			emprestimo = emprestimoService.finalizarEmprestimo(emprestimo.getIdEmprestimo());

			//Quarto
			emprestimo = new Emprestimo();
			emprestimo.setLivro(livroRepository.findLivroByidLivro(3));
			pessoa = new Pessoa();
			pessoa = pessoaRepository.findPessoaByEmailPessoa("mira.nanda@yahoo.com");
			emprestimo.setPessoa(pessoa);
			emprestimo = emprestimoService.incluirEmprestimo(emprestimo);
			emprestimo = emprestimoService.finalizarEmprestimo(emprestimo.getIdEmprestimo());

			//Quinto
			emprestimo = new Emprestimo();
			emprestimo.setLivro(livroRepository.findLivroByidLivro(2));
			pessoa = new Pessoa();
			pessoa = pessoaRepository.findPessoaByEmailPessoa("jett.elena@hotmail.com");
			emprestimo.setPessoa(pessoa);
			emprestimo = emprestimoService.incluirEmprestimo(emprestimo);

			//Quinto
			emprestimo = new Emprestimo();
			emprestimo.setLivro(livroRepository.findLivroByidLivro(1));
			pessoa = new Pessoa();
			pessoa = pessoaRepository.findPessoaByEmailPessoa("hans@gmail.com");
			emprestimo.setPessoa(pessoa);
			emprestimo = emprestimoService.incluirEmprestimo(emprestimo);*/


		};
	}

}
