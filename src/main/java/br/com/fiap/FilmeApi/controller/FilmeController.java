package br.com.fiap.FilmeApi.controller;

import br.com.fiap.FilmeApi.models.Filme;
import br.com.fiap.FilmeApi.repository.FilmeRepository;
import br.com.fiap.FilmeApi.services.FilmeService;
import br.com.fiap.FilmeApi.services.TmdbService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/filmes")
public class FilmeController {

    @Autowired
    private FilmeService filmeService;

    // List all films (index.html)
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("filmes", filmeService.listarTodos());
        return "index";
    }

    // Show form for new film (form.html)
    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("filme", new Filme());
        return "form";
    }

    // Save film
    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute Filme filme, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "form";
        }
        filmeService.salvar(filme);
        return "redirect:/filmes";
    }

    // Edit film
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        return filmeService.buscarPorId(id)
                .map(filme -> {
                    model.addAttribute("filme", filme);
                    return "form";
                })
                .orElse("redirect:/filmes");
    }

    // Delete film
    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id) {
        filmeService.deletar(id);
        return "redirect:/filmes";
    }

}