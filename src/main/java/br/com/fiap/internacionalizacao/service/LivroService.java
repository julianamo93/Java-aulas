package br.com.fiap.internacionalizacao.service;

import br.com.fiap.internacionalizacao.dto.LivroRequest;
import br.com.fiap.internacionalizacao.model.Livro;
import br.com.fiap.internacionalizacao.repository.LivroRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {
    private final LivroRepository livroRepository;
    @Autowired
    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public Livro salvarLivro(LivroRequest livroRequest) {
        Livro livro = new Livro();
        BeanUtils.copyProperties(livroRequest, livro);
        return livroRepository.save(livro);
    }

    public Livro atualizarLivro(Long id, LivroRequest livroRequest) {
        Livro livro = buscarLivro(id);
        if (livro == null) {
            return null;
        }
        BeanUtils.copyProperties(livroRequest, livro);
        return livroRepository.save(livro);
    }

    public void excluirLivro(Long id) {
        livroRepository.deleteById(id);
    }

    public Livro buscarLivro(Long id) {
        Optional<Livro> livro = livroRepository.findById(id);
        return livro.orElse(null);
    }

    public List<Livro> buscarLivros() {
        return livroRepository.findAll();
    }
}
