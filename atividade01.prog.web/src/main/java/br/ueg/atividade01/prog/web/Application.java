package br.ueg.atividade01.prog.web;

import br.ueg.atividade01.prog.web.model.Livro;
import br.ueg.atividade01.prog.web.repository.LivroRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import java.time.LocalDate;


@SpringBootApplication
@EntityScan(basePackageClasses = { Jsr310JpaConverters.class }, basePackages = "br.ueg.atividade01.prog.web.model")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(LivroRepository livroRepository) {
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


		};
	}

}
