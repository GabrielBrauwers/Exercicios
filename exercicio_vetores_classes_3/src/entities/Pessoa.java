package entities;

public class Pessoa {

	private String nome;
	private int numero;
	private int quarto;
	
	
	public Pessoa(String nome, int numero, int quarto) {
		this.nome = nome;
		this.numero = numero;
		this.quarto = quarto;
	}
	
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}


	public int getNumero() {
		return numero;
	}


	public void setNumero(int numero) {
		this.numero = numero;
	}


	public int getQuarto() {
		return quarto;
	}


	public void setQuarto(int quarto) {
		this.quarto = quarto;
	}
	
	
	
	
}
