package application;

import java.util.Scanner;

import entities.Pessoa;

public class Program {
	public void main (String[]args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Quantas pessoas deseja cadastrar? \n");
		int n = sc.nextInt();
		Pessoa[] vect = new Pessoa[n];
		
		double mediaAltura = 0;
		double totalAltura = 0;
		int totalHomem = 0;
		int totalMulher = 0;
		double maiorAltura = 0;
		double menorAltura = 1000;
		
		for(int i = 0; i < n; i++) {
			sc.nextLine();

			System.out.printf("sexo da pessoa: \n");
			char sexo = sc.nextLine().charAt(0);
			
			System.out.printf("altura da pessoa: \n");
			double altura = sc.nextDouble();
			
			if(altura > maiorAltura) {
				maiorAltura = altura;
			}
			
			if(altura < menorAltura) {
				menorAltura = altura;
			}
			
			if(sexo == 'f') {
				totalAltura += altura;
				totalMulher ++;
			}
			else {
				totalHomem ++;
			}
			
			vect[i] = new Pessoa(sexo, altura);
			
		}
		
		mediaAltura = totalAltura / totalMulher;
		
		System.out.printf("Menor altura: %.2f\n", menorAltura);
		System.out.printf("Maior altura: %.2f\n", maiorAltura);
		System.out.printf("Media das alturas das mulheres: %.2f\n", mediaAltura);
		System.out.printf("Numero de homens: %d", totalHomem);
		
		sc.close();
	}
}
