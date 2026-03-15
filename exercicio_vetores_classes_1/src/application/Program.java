package application;

import java.util.Scanner;

import entities.Pessoa;

public class Program {
	public static void main (String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		
		System.out.println("Quantas pessoas deseja inserir? ");
		int n = sc.nextInt();
		Pessoa[] vect = new Pessoa[n];
		
		double alturaTotal = 0;
		int pessoaMenor = 0;
		
		for(int i = 0; i < n; i++) {
			sc.nextLine();
			
			System.out.print("Nome: ");
			String nome = sc.nextLine();
			
			System.out.print("Idade: ");
			int idade = sc.nextInt();
			
			System.out.print("Altura: ");
			double altura = sc.nextDouble();
			
			alturaTotal += altura;
			
			if(idade < 16) {
				pessoaMenor ++;
			}
			
			vect[i] = new Pessoa(nome, idade, altura); 
			
		}
		
		double alturaMedia = alturaTotal / n;
		double porcent = pessoaMenor * 100 / n;
		
		System.out.printf("Média da altura das pessoas: %.2f\n", alturaMedia);
		System.out.printf("Porcentagem de pessoas com menos de 16 anos: %.2f\n", porcent);
		System.out.println("Pessoas menores de 16: ");
		for(int i = 0; i < n; i++) {
			if(vect[i].getIdade() < 16) {
				System.out.println(vect[i].getNome());
			}
		}
		
		
		sc.close();
	}
}
