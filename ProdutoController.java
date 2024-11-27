package br.livraria.projeto_livraria.controllers;

import br.livraria.projeto_livraria.model.Produto;
import br.livraria.projeto_livraria.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping("/produtos") // Endpoint para acessar os produtos
    public List<ProdutoResponse> getAllProdutos() {
        List<Produto> produtos = produtoRepository.findAll();
        
        // Mapear a lista de produtos para uma lista de ProdutoResponse com id, titulo, descricao, preco, autor, categoria, e estoque
        return produtos.stream()
                .map(produto -> new ProdutoResponse(
                        produto.getId(),
                        produto.getTitulo(),
                        produto.getDescricao(),
                        produto.getPreco(),
                        produto.getAutor(),
                        produto.getCategoria(),
                        produto.getEstoque()))
                .collect(Collectors.toList());
    }

    // Classe interna que irá representar os dados que queremos expor no endpoint
    public static class ProdutoResponse {
        private String id;
        private String titulo;
        private String descricao;
        private double preco;
        private String autor;
        private String categoria;
        private int estoque;

        public ProdutoResponse(String id, String titulo, String descricao, double preco, String autor, String categoria, int estoque) {
            this.id = id;
            this.titulo = titulo;
            this.descricao = descricao;
            this.preco = preco;
            this.autor = autor;
            this.categoria = categoria;
            this.estoque = estoque;
        }

        // Getters
        public String getId() {
            return id;
        }

        public String getTitulo() {
            return titulo;
        }

        public String getDescricao() {
            return descricao;
        }

        public double getPreco() {
            return preco;
        }

        public String getAutor() {
            return autor;
        }

        public String getCategoria() {
            return categoria;
        }

        public int getEstoque() {
            return estoque;
        }
    }
}
