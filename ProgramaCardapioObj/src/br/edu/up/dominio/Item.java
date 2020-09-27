package br.edu.up.dominio;

import java.text.DecimalFormat;

public class Item {
	private String codigo;
	private String nome;
	private double preco;
	
	public Item(String codigo, String nome, String preco) {
		this.codigo = codigo;
		this.nome = nome;
		this.preco = Double.parseDouble(preco);
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

	public String toString() {
		return codigo + " - " + nome + ": " + new DecimalFormat("#0.00").format( preco );
	}
	
	public String toCSV(int categoria) {
		String csv = "";
		if (categoria == 1) {
			csv = nome + ";" + new DecimalFormat("#0.00").format( preco );
		} else if ( (categoria == 2) || (categoria == 3) ) {
			csv = new DecimalFormat("#0.00").format( preco ) + "	" + nome; 
		}
		return csv;
	}
}


