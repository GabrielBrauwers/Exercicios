package entities;

public class Aluno {

	public String nome;
	public double n1;
	public double n2;
	public double n3;
	
	
	public void calcularNotaFinal() {
		double media = n1 + n2 + n3;
		
		if(media > 60) {
			System.out.printf("Final grade: %.1f\nPASSOU", media);
		}
		else {
			System.out.printf("Final grade: %.1f\nREPROVOU", media);
		}
	}
	
}
