package org.example;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private Cliente cliente;
    private List<Produto> listaDeProdutos = new ArrayList<>();
    private CupomDesconto cupomDesconto;
    private Double valorTotalDoPedido = 0.0;

    public Pedido(Cliente cliente, List<Produto> listaDeProdutos, CupomDesconto cupomDesconto) {
        this.cliente = cliente;
        this.listaDeProdutos = listaDeProdutos;
        this.cupomDesconto = cupomDesconto;
    }

    public Pedido(Cliente cliente, CupomDesconto cupomDesconto) {
        this.cliente = cliente;
        this.cupomDesconto = cupomDesconto;
    }

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Produto> getListaDeProdutos() {
        return listaDeProdutos;
    }

    public void setListaDeProdutos(List<Produto> listaDeProdutos) {
        this.listaDeProdutos = listaDeProdutos;
    }

    public CupomDesconto getCupomDesconto() {
        return cupomDesconto;
    }

    public void setCupomDesconto(CupomDesconto cupomDesconto) {
        this.cupomDesconto = cupomDesconto;
    }

    public Double getValorTotalDoPedido() {
        return valorTotalDoPedido;
    }

    public void setValorTotalDoPedido(Double valorTotalDoPedido) {
        this.valorTotalDoPedido = valorTotalDoPedido;
    }

    public void adicionarProduto(Produto produto){
        this.listaDeProdutos.add(produto);
    }

    public void exibirDetalhesDaCompra(){
        System.out.println("Detalhes da compra:");
        this.totalizarPreco();
        this.exibirDetalhesDoCliente();
        this.exibirFormaDePagamento();
        this.aplicarDescontoEmProduto();
        this.exibirValorFinalDoPedido();
        this.exibirListaProdutosNoPedido();
    }

    public void totalizarPreco(){
        for (Produto produto: this.getListaDeProdutos()){
            valorTotalDoPedido += produto.getPreco();
        }
    }

    public void exibirDetalhesDoCliente(){
        System.out.println("Nome: " + this.cliente.getNome());
        System.out.println("Endereço: " + this.cliente.getEndereco());
    }

    public void exibirListaProdutosNoPedido(){
        System.out.println("----------------------------");
        System.out.println("Lista de produtos: \n");
        int counter = 1;
        for (Produto produto : this.getListaDeProdutos()){
            System.out.println("Produto " + counter);
            System.out.println("- Nome: " + produto.getNome());
            System.out.println("- Preço: R$ " + produto.getPreco());
        }
        System.out.println("---------------------------- \n");
    }

    public void exibirFormaDePagamento(){
        if(this.cliente.getFormaPagamento().equals(FormaPagamento.PIX) || this.cliente.getFormaPagamento().equals(FormaPagamento.DINHEIRO)){
            System.out.println("Compra efetuada com sucesso! Entrega no endereço: " + this.cliente.getEndereco());
        }
        if(this.cliente.getFormaPagamento().equals(FormaPagamento.CREDITO) || this.cliente.getFormaPagamento().equals(FormaPagamento.DEBITO)){
            System.out.println("Compra em processamento. Entrega após confirmação no endereço: " + this.cliente.getEndereco());
        }
        if(this.cliente.getFormaPagamento().equals(FormaPagamento.BOLETO)){
            System.out.println("Aguardando pagamento do boleto. Entrega após compensação bancária.");
        }
    }

    private void exibirValorFinalDoPedido(){
        System.out.println("Valor total do pedido: R$ " + this.getValorTotalDoPedido());
    }

    public void aplicarDescontoEmProduto(){

        if(this.cupomDesconto == null){
            return;
        }
        // NENHUM
        if(this.cupomDesconto.equals(CupomDesconto.NENHUM)){
            return;
        }
        // FRETEGRAIS
        if(this.cupomDesconto.equals(CupomDesconto.FRETEGRATIS)){
            this.setValorTotalDoPedido(this.valorTotalDoPedido - CupomDesconto.FRETEGRATIS.getValorDoCupom());
        }
        // CUSTOFRETE
        if(this.cupomDesconto.equals(CupomDesconto.CUSTOFRETE)){
            this.setValorTotalDoPedido(this.valorTotalDoPedido + CupomDesconto.CUSTOFRETE.getValorDoCupom());
        }
        // DESCONTO15
        if(this.cupomDesconto.equals(CupomDesconto.DESCONTO15)){
            this.setValorTotalDoPedido(this.valorTotalDoPedido - CupomDesconto.DESCONTO15.getValorDoCupom());
        }
        // DESCONTO20
        if(this.cupomDesconto.equals(CupomDesconto.DESCONTO20)){
            if(this.getListaDeProdutos().size() < 4){
                return;
            }
            if(this.getValorTotalDoPedido() < 150.00){
                return;
            }
            this.setValorTotalDoPedido(this.valorTotalDoPedido - CupomDesconto.DESCONTO20.getValorDoCupom());
        }
        // DESCONTO50
        if(this.cupomDesconto.equals(CupomDesconto.DESCONTO50)){
            if(this.getListaDeProdutos().size() < 6){
                return;
            }
            if(this.getValorTotalDoPedido() < 350.00){
                return;
            }
            if(!checarCategoriaProduto(CategoriaProduto.ELETRONICO)){
                return;
            }
            this.setValorTotalDoPedido(this.valorTotalDoPedido - CupomDesconto.DESCONTO50.getValorDoCupom());
        }
        // DESCONTO70
        if(this.cupomDesconto.equals(CupomDesconto.DESCONTO70)){
             if(this.getListaDeProdutos().size() < 6){
                 return;
             }
            if(this.getValorTotalDoPedido() < 350.00){
                return;
            }
             if(!checarCategoriaProduto(CategoriaProduto.ELETRONICO)){
                 return;
             }
            if(!checarFormaDePagamentoDoProduto(FormaPagamento.PIX)){
                return;
            }
            this.setValorTotalDoPedido(this.valorTotalDoPedido - CupomDesconto.DESCONTO70.getValorDoCupom());
        }
    }

    // TODO COLOCAR NA CLASSE PRODUTO
    public boolean checarCategoriaProduto(CategoriaProduto categoriaProduto){
        for (Produto produto : this.getListaDeProdutos()){
            if(produto.getCategoriaProduto().equals(categoriaProduto)){
                return true;
            }
        }
        return false;
    }

    public boolean checarFormaDePagamentoDoProduto(FormaPagamento formaPagamento){
        return this.getCliente().getFormaPagamento().equals(formaPagamento);
    }
}
