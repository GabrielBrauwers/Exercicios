package entities;

public class Usuario {

	private String nome;
	private int conta;
	private double valor;
	
	
	public Usuario(String nome, int conta, double depositoInicial) {
		this.nome = nome;
		this.conta = conta;
		depositarDinheiro(depositoInicial);
	}
	
	public Usuario(String nome, int conta) {
		this.nome = nome;
		this.conta = conta;
	}
	
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	
	public int getConta() {
		return conta;
	}
	
	public double getValor() {
		return valor;
	}
	
	
	public void depositarDinheiro(double valor) {
		
		this.valor += valor;
		
	}
	
	public void sacarDinheiro(double valor) {
		
		this.valor -= valor + 5;
		
	}
	
	public String toString() {
		
		return "\nNome: " + this.nome + 
			   "\nNúmero da conta: " + this.conta +
			   "\nValor em conta: " + this.valor;
	}
	
	
	
}
