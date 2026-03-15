package application;

import java.util.Scanner;

import entities.Funcionario;

public class Program {
	public static void main(String[]args) {
		
		Scanner sc = new Scanner(System.in);
		Funcionario f = new Funcionario();
		int porcentagem;
		
		System.out.print("Digite o nome do funcionario: ");
		f.nome = sc.nextLine();
		System.out.print("Digite o salário bruto do funcionario: ");
		f.salarioBruto = sc.nextDouble();
		System.out.printf("Quanto %s paga de imposto: ", f.nome);
		f.tax = sc.nextDouble();
		
		System.out.printf("\n\nNome do funcionario: %s\nSalário liquido: %.2f", f.nome, f.salarioLiquido());
		
		System.out.printf("\n\nVoce deseja dar ao %s um aumento de quantos porcentos? ", f.nome);
		porcentagem = sc.nextInt();
		f.aumentoDeSalario(porcentagem);
		
		System.out.printf("\n\nNome do funcionario: %s\nSalário liquido: %.2f", f.nome, f.salarioBruto);
		
		sc.close();
	}
}
