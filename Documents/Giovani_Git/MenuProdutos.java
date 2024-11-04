
package com.loja.ui;

import com.loja.gerenciador.GerenciadorProdutos;
import com.loja.modelo.Produto;
import java.util.List;
import java.util.Scanner;

    public class MenuProdutos {
        private Scanner scanner = new Scanner(System.in);
        private GerenciadorProdutos gerenciador = new GerenciadorProdutos();

        public void exibirMenu() {
            int opcao;
            do {
                System.out.println("\n=== Menu Principal ===");
                System.out.println("1. Cadastrar Produto");
                System.out.println("2. Buscar Produto por ID");
                System.out.println("3. Listar Todos os Produtos");
                System.out.println("4. Atualizar Produto");
                System.out.println("5. Deletar Produto");
                System.out.println("6. Buscar por Nome");
                System.out.println("7. Buscar por Categoria");
                System.out.println("8. Sair");
                System.out.print("Escolha uma opção: ");
                opcao = lerEntradaInteira("");

                switch (opcao) {
                    case 1: cadastrarProduto(); break;
                    case 2: buscarProduto(); break;
                    case 3: listarProdutos(); break;
                    case 4: atualizarProduto(); break;
                    case 5: deletarProduto(); break;
                    case 6: buscarPorNome(); break;
                    case 7: buscarPorCategoria(); break;
                    case 8: System.out.println("Saindo..."); break;
                    default: System.out.println("Opção inválida, tente novamente.");
                }
            } while (opcao != 8);
        }

        private void cadastrarProduto() {
            System.out.println("\n=== Cadastro de Produto ===");
            String nome = lerEntradaString("Digite o nome: ");
            double preco = lerEntradaDouble("Digite o preço: ");
            int quantidade = lerEntradaInteira("Digite a quantidade: ");
            String categoria = lerEntradaString("Digite a categoria: ");

            Produto produto = new Produto(nome, preco, quantidade, categoria);
            gerenciador.criar(produto);
        }

        private void buscarProduto() {
            System.out.println("\n=== Busca de Produto ===");
            int id = lerEntradaInteira("Digite o ID: ");
            Produto produto = gerenciador.buscarPorId(id);
            if (produto != null) {
                System.out.println("Produto encontrado:");
                System.out.println(produto);
            } else {
                System.out.println("Produto não encontrado.");
            }
        }

        private void listarProdutos() {
            System.out.println("\n=== Listagem de Produtos ===");
            List<Produto> produtos = gerenciador.listarTodos();
            if (produtos.isEmpty()) {
                System.out.println("Nenhum produto cadastrado.");
            } else {
                for (Produto produto : produtos) {
                    System.out.println(produto);
                }
            }
        }

        private void atualizarProduto() {
            System.out.println("\n=== Atualização de Produto ===");
            int id = lerEntradaInteira("Digite o ID do produto que deseja atualizar: ");
            Produto existente = gerenciador.buscarPorId(id);
            if (existente != null) {
                String nome = lerEntradaString("Digite o novo nome (atual: " + existente.getNome() + "): ");
                double preco = lerEntradaDouble("Digite o novo preço (atual: " + existente.getPreco() + "): ");
                int quantidade = lerEntradaInteira("Digite a nova quantidade (atual: " + existente.getQuantidadeEstoque() + "): ");
                String categoria = lerEntradaString("Digite a nova categoria (atual: " + existente.getCategoria() + "): ");

                Produto atualizado = new Produto(nome, preco, quantidade, categoria);
                atualizado.setId(id);
                if (gerenciador.atualizar(atualizado)) {
                    System.out.println("Produto atualizado com sucesso!");
                } else {
                    System.out.println("Erro ao atualizar o produto.");
                }
            } else {
                System.out.println("Produto não encontrado.");
            }
        }

        private void deletarProduto() {
            System.out.println("\n=== Deletar Produto ===");
            int id = lerEntradaInteira("Digite o ID do produto que deseja deletar: ");
            if (gerenciador.deletar(id)) {
                System.out.println("Produto deletado com sucesso.");
            } else {
                System.out.println("Produto não encontrado.");
            }
        }

        private void buscarPorNome() {
            System.out.println("\n=== Busca por Nome ===");
            String nome = lerEntradaString("Digite o nome: ");
            List<Produto> encontrados = gerenciador.buscarPorNome(nome);
            if (encontrados.isEmpty()) {
                System.out.println("Nenhum produto encontrado com esse nome.");
            } else {
                for (Produto produto : encontrados) {
                    System.out.println(produto);
                }
            }
        }

        private void buscarPorCategoria() {
            System.out.println("\n=== Busca por Categoria ===");
            String categoria = lerEntradaString("Digite a categoria: ");
            List<Produto> encontrados = gerenciador.buscarPorCategoria(categoria);
            if (encontrados.isEmpty()) {
                System.out.println("Nenhum produto encontrado nessa categoria.");
            } else {
                for (Produto produto : encontrados) {
                    System.out.println(produto);
                }
            }
        }

        private String lerEntradaString(String mensagem) {
            System.out.print(mensagem);
            return scanner.nextLine();
        }

        private double lerEntradaDouble(String mensagem) {
            double valor;
            while (true) {
                try {
                    System.out.print(mensagem);
                    valor = Double.parseDouble(scanner.nextLine());
                    return valor;
                } catch (NumberFormatException e) {
                    System.out.println("Entrada inválida. Tente novamente.");
                }
            }
        }

        private int lerEntradaInteira(String mensagem) {
            int valor;
            while (true) {
                try {
                    System.out.print(mensagem);
                    valor = Integer.parseInt(scanner.nextLine());
                    return valor;
                } catch (NumberFormatException e) {
                    System.out.println("Entrada inválida. Tente novamente.");
                }
            }
        }
    }


