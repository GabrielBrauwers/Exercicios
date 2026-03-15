import java.util.Scanner;

public class Exercicio_1 {
	public static void main(String[] args) {
		
		int a, b, x;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Digite dois numeros e mostrarei a soma dos mesmos:");
		
		a = sc.nextInt();
		b = sc.nextInt();
		
		x = a + b;
		
		System.out.printf("a soma de %d e %d é: %d", a, b, x);
		
		sc.close();
	}
}
