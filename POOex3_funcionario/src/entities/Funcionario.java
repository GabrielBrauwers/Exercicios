package entities;

public class Funcionario {

	public String nome;
	public double salarioBruto;
	public double tax;
	
	public double salarioLiquido() {
		
		salarioBruto -= tax;
		
		return salarioBruto ;
	}
	
	public void aumentoDeSalario(int porcent) {
		
		salarioBruto += salarioBruto * porcent / 100.0;
				
	}
	
	
}
