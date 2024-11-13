package com.example.springfront.controller;




import com.example.springfront.Error.ResourceNotFoundException;
import com.example.springfront.model.Categoria;
import com.example.springfront.model.Produto;
import com.example.springfront.repository.ProdutoRepository;
import com.example.springfront.service.CategoriaService;
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
    private CategoriaService categoriaService;



    @GetMapping
    public String listarProdutos(Model model) {
        List<Produto> produtos = produtoService.getProduto();
        model.addAttribute("produtos", produtos);
        List<Produto> produtosC = produtoService.ListarporC();
        model.addAttribute("produtosC",produtosC);
        List<Categoria> categorias = categoriaService.listarTodas();
        model.addAttribute("categorias",categorias);
        // Passando a lista de produtos para o template
        return "produtos"; // Retorna o nome do template (produtos.html)
    }

   @GetMapping("/editar/{id}")
   public String editar(@PathVariable Long id, Model model){
       List<Categoria> categorias = categoriaService.listarTodas();
        Produto produto = produtoService.getProdutoPorId(id);
        if (produto != null){
            model.addAttribute("produto",produto);
            model.addAttribute("categorias",categorias);
            return "editarProduto";

        }
       return "redirect:/produtos";
   }

    @PostMapping("/editar/{id}")
    public String editarProduto(@ModelAttribute("produtoEditado") Produto produto, @RequestParam("categoriaId") Long categoriaId) {
        Categoria categoria = categoriaService.buscasPorId(categoriaId);
        produto.setCategoria(categoria);
        produtoService.editar(produto);
        return "redirect:/produtos"; // Redireciona para a página de listagem após adicionar
    }





    @GetMapping("/buscar")
    public String filtrarPorNome(@RequestParam ("nome")String nome, Model model){
        List<Produto> produtosNome  = produtoService.getProdutosPorNomes(nome);
        if (produtosNome != null && !produtosNome.isEmpty()){
            model.addAttribute("produtos",produtosNome );
            return "produtos";

        }else {
            throw new ResourceNotFoundException("nome não encontrado");
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
        Produto produto = produtoService.getProdutoPorId(id);
        if (produto != null) {
            model.addAttribute("produto", produto);
            return "produtodetalhes";
        }
        return "redirect:/produtos"; // Redireciona caso o produto não seja encontrado
    }

    @PostMapping("/adicionar")
    public String adicionarProduto(@ModelAttribute("novoProduto") Produto produto, @RequestParam("categoriaId") Long categoriaId) {
        Categoria categoria = categoriaService.buscasPorId(categoriaId);
        produto.setCategoria(categoria);
        produtoService.inserir(produto);
        return "redirect:/produtos"; // Redireciona para a página de listagem após adicionar
    }

    @PostMapping("/deletar/{id}")
    public String deletarProduto(@PathVariable Long id) {
        produtoService.apagar(id);
        return "redirect:/produtos"; // Redireciona para a página de listagem após deletar
    }
    }

