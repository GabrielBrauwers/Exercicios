import java.util.Scanner;

public class Exercicio_4 {
	public static void main(String[] args) {
		
		int id;
		double ht, hv, s;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Digite, em ordem, o id do funcionario, suas horas trabalhadas e valor de hora");
		
		id = sc.nextInt();
		ht = sc.nextDouble();
		hv = sc.nextDouble();
		s = ht * hv;
		
		System.out.printf("o Funcionario %d trabalha %.2f horas por dia e recebe %.2f por dia", id, ht, s);
		
		sc.close();
	}
}
