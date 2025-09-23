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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;

@Controller
@RequestMapping("/filmes")
public class FilmeController {

    @Autowired
    private FilmeService filmeService;


    @GetMapping
    public String listar(Model model,@AuthenticationPrincipal OAuth2User user) {
        if (user == null) {
            return "redirect:/login";
        }
        model.addAttribute("filmes", filmeService.listarTodos());
        model.addAttribute("user", user);
        var avatar = user.getAttribute("picture") != null ? user.getAttribute("picture") :  user.getAttribute("avatar_url");
        model.addAttribute("avatar", avatar);
        return "index";
    }


    @GetMapping("/form")
    public String novo(Model model) {
        model.addAttribute("filme", new Filme());
        return "form";
    }

    // Save film
    @PostMapping("/form")
    public String salvar(@Valid @ModelAttribute Filme filme, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "form";
        }
        filmeService.salvar(filme);
        return "redirect:/filmes";
    }



    // Delete film
    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id) {
        filmeService.deletar(id);
        return "redirect:/filmes";
    }

}