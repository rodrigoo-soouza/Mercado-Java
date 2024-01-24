package Main;

import Model.Produto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Mercado {
    private static Scanner input = new Scanner(System.in);
    private static ArrayList<Produto> produtos;
    private static Map<Produto, Integer> carrinho;

    public static void main(String[] args){
        produtos = new ArrayList<>();
        carrinho = new HashMap<>();
        menu();
    }

    private static void menu(){

        System.out.println("---------------------------------------------------------");
        System.out.println("------------------Bem Vindo ao Mercado-------------------");
        System.out.println("---------------------------------------------------------");
        System.out.println("---------***** Selecione uma operação *****--------------");
        System.out.println("---------------------------------------------------------");
        System.out.println("|    Opção 1 - Cadastrar         |");
        System.out.println("|    Opção 2 - Listar            |");
        System.out.println("|    Opção 3 - Comprar           |");
        System.out.println("|    Opção 4 - Carrinho          |");
        System.out.println("|    Opção 5 - Sair              |");

        int opcao = input.nextInt();

        switch (opcao){
            case 1:
                cadastrarProdutos();
                break;
            case 2:
                listarProdutos();
                break;
            case 3:
                comprarProdutos();
                break;
            case 4:
                verCarrinho();
                break;
            case 5:
                System.out.println("Obrigado pela preferência");
                System.exit();
                break;
            default:
                System.out.println("Opção Inválida!");
                menu();
                break;
        }
    }

    private static void cadastrarProdutos(){
        System.out.println("Nome do Produto: ");
        String nome = input.next();

        System.out.println("Preço do Produto: ");
        Double preco = input.nextDouble();

        Produto produto = new Produto(nome, preco);
        produtos.add(produto);

        System.out.println(produto.getNome() + "cadastrado com sucesso!");
        menu();
    }

    private static void listarProdutos(){
        if (produtos.size() > 0){
            System.out.println("Lista de produtos: \n");

            for (Produto p : produtos){
                System.out.println(p);
            }
        }
        else{
            System.out.println("Nenhum produto cadastrado!");
        }
        menu();
    }
    private static void comprarProdutos(){
        if (produtos.size() > 0){
            System.out.println("Código do Produto: \n");

            System.out.println("------------------Produtos Disponíveis-------------------");
            for (Produto p : produtos){
                System.out.println(p + "\n");
            }
            int id = Integer.parseInt(input.next());
            boolean isPresente = false;

            for (Produto p : produtos){
                if (p.getId() == id){
                    int qtd = 0;
                    try{
                        qtd = carrinho.get(p);
                        carrinho.put(p, qtd + 1);
                    }
                    catch (NullPointerException e){
                        carrinho.put(p, 1);
                    }
                    System.out.println(p.getNome() + "adicionado ao carrinho.");
                    isPresente = true;

                    if (isPresente){
                        System.out.println("Deseja adicionar outro produto ao carrinho? ");
                        System.out.println("Digite 1 para sim, ou 0 para finalizar a compra. \n");
                        int opcao = Integer.parseInt(input.next());

                        if (opcao  == 1){
                            comprarProdutos();
                        }
                        else{
                            finalizarCompra();
                        }
                    }
                }
                else{
                    System.out.println("Produto não Encontrado!");
                    menu();
                }
            }

        }
        else{
            System.out.println("Não existem produtos cadastrados!");
            menu();
        }
    }
}