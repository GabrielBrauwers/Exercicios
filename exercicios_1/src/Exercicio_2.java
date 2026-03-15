import java.util.Scanner;

public class Exercicio_2 {
	public static void main(String[] args) {
		
		double r, x, p = 3.14159;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Digite o valor do raio para calcular a área do circulo:");
		
		r = sc.nextDouble();
		
		x = p * Math.pow(r, 2);
		
		System.out.printf("A área do circulo de raio %f é: %f", r, x);
		
		sc.close();
	}
}
