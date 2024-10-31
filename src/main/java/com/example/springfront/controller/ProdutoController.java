package com.example.springfront.controller;




import com.example.springfront.model.Produto;
import com.example.springfront.repository.ProdutoRepository;
import com.example.springfront.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ProdutoRepository produtoRepositorio;


    @GetMapping
    public String listarProdutos(Model model) {
        List<Produto> produtos = produtoService.getProduto();
        model.addAttribute("produtos", produtos); // Passando a lista de produtos para o template
        return "produtos"; // Retorna o nome do template (produtos.html)
    }


    @GetMapping("/buscar")
    public String filtrarPorNome(@RequestParam ("nome")String nome, Model model){
        Produto produtosNome  = produtoService.getProdutoPorNome(nome);
        if(produtosNome != null){
            model.addAttribute("produtos",produtosNome );
            return "produtos";

        }else {
            throw new RuntimeException("nome não encontrado");
        }
    }


    @GetMapping("/inicial-c")
    public String listarPorInicial(Model model){
        List<Produto> produtosC = produtoService.ListarporC();
        model.addAttribute("produtosC",produtosC);
        return "produtosC";
    }





    @GetMapping("/detalhes/{id}")
    public String detalhesProduto(@PathVariable Long id, Model model) {
        Optional<Produto> produto = Optional.ofNullable(produtoService.getProdutoPorId(id));
        if (produto.isPresent()) {
            model.addAttribute("produto", produto.get());
            return "produtodetalhes";
        }
        return "redirect:/produtos"; // Redireciona caso o produto não seja encontrado
    }



    @PostMapping("/adicionar")
    public String adicionarProduto(@ModelAttribute("novoProduto") Produto produto) {
        produtoService.inserir(produto);
        return "redirect:/produtos"; // Redireciona para a página de listagem após adicionar
    }

    @PostMapping("/deletar/{id}")
    public String deletarProduto(@PathVariable Long id) {
        produtoService.apagar(id);
        return "redirect:/produtos"; // Redireciona para a página de listagem após deletar
    }


    }

