import java.util.Scanner;

public class Exercicio_1 {
	public static void main(String[] args){
		
		double sl, imp;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Digite o salario do cidadão e calcularemos seu imposto de renda: ");
		
		sl = sc.nextDouble();
		
		if(sl <= 2000) {
			System.out.println("O cidadão está isento de pagar seu imposto de renda!");
		}
		if(sl > 2000 && sl <= 3000) {
			
			imp = (sl - 2000) * 0.08;
			
			System.out.printf("o impostoi de renda sera no valor de: %.2f", imp);
		}
		if(sl > 3000 && sl <= 4500) {

			imp = (sl - 2000) * 0.18;
			
			System.out.printf("o impostoi de renda sera no valor de: %.2f", imp);
		}
		if(sl > 4500) {
			
			imp = (sl - 2000) * 0.28;
			
			System.out.printf("o impostoi de renda sera no valor de: %.2f", imp);
			
		}
		
		sc.close();
	}
}
