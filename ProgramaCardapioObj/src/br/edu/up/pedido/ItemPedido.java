package br.edu.up.pedido;

import java.text.DecimalFormat;

public class ItemPedido {
	private String codigo;
	private String nome;
	private double preco;
	private int qtde;
	
	public ItemPedido(String codigo, String nome, double preco, int qtde) {
		this.codigo = codigo;
		this.nome = nome;
		this.preco = preco;
		this.qtde = qtde;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public int getQtde() {
		return qtde;
	}

	public void setQtde(int qtde) {
		this.qtde = qtde;
	}
	
	public double getTotal() {
		return (this.qtde * this.preco);
	}
	
	public String toString() {
		return codigo + " - " + nome + toPrint();
	}
	
	public String toPrint() {
		double total = 0;
		total = this.qtde * this.preco;
		return " - " + Integer.toString(this.qtde) + " X " +  new DecimalFormat("#0.00").format( this.preco ) +  " - " + new DecimalFormat("#0.00").format( total );
	}
	
	public String toCSV() {
		return codigo + ";" + nome + ";" + Integer.toString(this.qtde) + ";" +  new DecimalFormat("#0.00").format( this.preco );
	}
}
