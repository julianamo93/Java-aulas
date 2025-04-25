package br.com.fiap.internacionalizacao.controller;

import br.com.fiap.internacionalizacao.dto.LivroRequest;
import br.com.fiap.internacionalizacao.model.Livro;
import br.com.fiap.internacionalizacao.service.LivroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/livro")
public class LivroController {
    private final LivroService livroService;
    @Autowired
    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @GetMapping("/cadastro")
    public String livroCadastro(Model model) {
        model.addAttribute("Livro", new Livro());
        return "LivroCadastro";
    }

    @PostMapping("/cadastrar")
    public String cadastrarLivro(@Valid LivroRequest livroRequest, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "LivroCadastro";
        }
        livroService.salvarLivro(livroRequest);
        return listarLivros(model);
    }

    @GetMapping("/lista")
    public String listarLivros(Model model) {
        List<Livro> livros = livroService.buscarLivros();
        model.addAttribute("listarLivros", livros);
        return "LivroLista";
    }

    @GetMapping("/edicao/{id}")
    public String livroEdicao(@PathVariable Long id, Model model) {
        Livro livro = livroService.buscarLivro(id);
        if (livro == null) {
            return listarLivros(model);
        }
        model.addAttribute("idLivro", id);
        model.addAttribute("livro", livro);
        return "livroEdicao";
    }

    @PostMapping("/editar/{id}")
    public String editarLivro(@PathVariable Long id, @Valid LivroRequest livroRequest, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "LivroEdicao";
        }
        livroService.atualizarLivro(id, livroRequest);
        return listarLivros(model);
    }

    @GetMapping("/excluir/{id}")
    public String excluirLivro(@PathVariable Long id, Model model) {
        livroService.excluirLivro(id);
        return listarLivros(model);
    }
}
