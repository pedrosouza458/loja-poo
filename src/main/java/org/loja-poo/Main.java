package org.example;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println("SEJA BEM VINDO AO E-COMMERCE");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Por favor, informe o seu nome");
        String nome = scanner.nextLine();

        System.out.println("Por favor, informe o seu endereço");
        String endereco = scanner.nextLine();

        FormaPagamento formaPagamento = FormaPagamento.PIX;

        Cliente cliente = new Cliente(nome, endereco, formaPagamento);

        Produto produto1 = new Produto("PC Logitech", 1500.00, CategoriaProduto.ELETRONICO);
        Produto produto2 = new Produto("Cabo USB", 200.00, CategoriaProduto.ELETRONICO);
        Produto produto3 = new Produto("Livro exemplo", 200.00, CategoriaProduto.LIVRO);

        // com sobrecarga (sem lista de produtos)
        Pedido pedido1 = new Pedido(cliente, CupomDesconto.DESCONTO70);
        pedido1.adicionarProduto(produto1);
        pedido1.adicionarProduto(produto2);
        pedido1.adicionarProduto(produto3);

        pedido1.exibirDetalhesDaCompra();

        // com sobrecarga (sem lista de produtos e cupom de desconto)
        Pedido pedido2 = new Pedido(cliente);
        pedido2.adicionarProduto(produto1);
        pedido2.adicionarProduto(produto2);

        pedido2.exibirDetalhesDaCompra();


        // DESCONTO50, DESCONTO70, DESCONTO20, FRETEGRATIS, DESCONTO15, NENHUM
    }
}