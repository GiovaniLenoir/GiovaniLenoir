
package com.loja.gerenciador;

import com.loja.modelo.Produto;
import com.loja.exception.ValidacaoException;

import java.util.ArrayList;
import java.util.List;

    public class GerenciadorProdutos {
        private List<Produto> produtos = new ArrayList<>();
        private int proximoId = 1;

        public void criar(Produto produto) {
            validarProduto(produto);
            produto.setId(proximoId++);
            produtos.add(produto);
            System.out.println("Produto cadastrado com sucesso! ID gerado: " + produto.getId());
        }

        public Produto buscarPorId(int id) {
            return produtos.stream()
                    .filter(produto -> produto.getId() == id)
                    .findFirst()
                    .orElse(null);
        }

        public List<Produto> listarTodos() {
            return new ArrayList<>(produtos);
        }

        public boolean atualizar(Produto produto) {
            Produto existente = buscarPorId(produto.getId());
            if (existente != null) {
                existente.setNome(produto.getNome());
                existente.setPreco(produto.getPreco());
                existente.setQuantidadeEstoque(produto.getQuantidadeEstoque());
                existente.setCategoria(produto.getCategoria());
                return true;
            }
            return false;
        }

        public boolean deletar(int id) {
            return produtos.removeIf(produto -> produto.getId() == id);
        }

        public List<Produto> buscarPorNome(String nome) {
            String nomeLower = nome.toLowerCase();
            List<Produto> encontrados = new ArrayList<>();
            for (Produto produto : produtos) {
                if (produto.getNome().toLowerCase().contains(nomeLower)) {
                    encontrados.add(produto);
                }
            }
            return encontrados;
        }

        public List<Produto> buscarPorCategoria(String categoria) {
            String categoriaLower = categoria.toLowerCase();
            List<Produto> encontrados = new ArrayList<>();
            for (Produto produto : produtos) {
                if (produto.getCategoria().toLowerCase().equals(categoriaLower)) {
                    encontrados.add(produto);
                }
            }
            return encontrados;
        }

        private void validarProduto(Produto produto) {
            if (produto.getNome() == null || produto.getNome().isEmpty() || produto.getNome().length() < 2) {
                throw new ValidacaoException("Nome do produto deve ter pelo menos 2 caracteres.");
            }
            if (produto.getPreco() <= 0) {
                throw new ValidacaoException("Preço deve ser maior que zero.");
            }
            if (produto.getQuantidadeEstoque() < 0) {
                throw new ValidacaoException("Quantidade em estoque não pode ser negativa.");
            }
            if (produto.getCategoria() == null || produto.getCategoria().isEmpty()) {
                throw new ValidacaoException("Categoria não pode ser vazia.");
            }
        }
    }


