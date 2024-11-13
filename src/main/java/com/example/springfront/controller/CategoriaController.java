package com.example.springfront.controller;



import com.example.springfront.model.Categoria;
import com.example.springfront.model.Produto;
import com.example.springfront.repository.CategoriaRepository;
import com.example.springfront.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    CategoriaService categoriaService;


    @PostMapping("/adicionar")
    public String adicionarCategoria(@ModelAttribute("novaCategoria") Categoria categoria) {
        categoriaService.inserir(categoria);
        // Redireciona para uma URL que lista as categorias
        return "redirect:/categoria/adicionar";
    }

    @GetMapping("/adicionar")
    public String listarCategorias(Model model) {
        List<Categoria> categoria = categoriaService.listarTodas();
        model.addAttribute("categorias", categoria );
        return "categorias";
        // Nome do template da página de listagem
    }

    @PostMapping("/deletar/{id}")
    public String deletarCategoria(@PathVariable Long id) {
        categoriaService.apagar(id);
        return "redirect:/categoria/adicionar"; // Redireciona para a página de listagem após deletar
    }

}
