import java.util.Scanner;

public class Exercicio_3 {
	public static void main(String[] args) {
		
		int a, b, c, d, x;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Digite 4 numeros:");
		
		a = sc.nextInt();
		b = sc.nextInt();
		c = sc.nextInt();
		d = sc.nextInt();
		
		x = (a*b) - (c*d);
		
		System.out.printf("A diferença de %d * %d e %d * %d é %d", a, b, c, d, x);
		
		sc.close();
		
	}
}
