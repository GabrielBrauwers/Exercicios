package application;

import java.util.Scanner;

import entities.Pessoa;

public class Program {
	public static void main(String[]args) {
		
		Scanner sc = new Scanner(System.in);
		Pessoa[] vect = new Pessoa[10];
		int teste;
		
		System.out.print("Quantos quartos serão alugados? \n");
		int n = sc.nextInt();
		
		for(int i = 0; i < n; i++) {
			
			do {
				sc.nextLine();
				teste = 1;
				
				System.out.print("Digite o nome da pessoa: \n");
				String nome = sc.nextLine();
				
				System.out.print("Digite o número da pessoa: \n");
				int numero = sc.nextInt();
				
				System.out.print("Digite o quarto da pessoa: \n");
				int quarto = sc.nextInt();
				
				if(vect[quarto] != null) {
					teste = 0;
					System.out.println("Quarto ja ocupado!\n");
				}
				else {
					vect[quarto] = new Pessoa(nome, numero, quarto);
				}
			}while(teste == 0);
		}
		
		for(int i = 0; i < 10; i++) {
			if(vect[i] != null) {
				System.out.printf("Informações do quarto: %d\n", i);
				System.out.printf("Nome: %s\n", vect[i].getNome());
				System.out.printf("Numero: %d\n", vect[i].getNumero());
				System.out.printf("Quarto: %d\n\n\n", vect[i].getQuarto());
			}
		}
		
		sc.close();
	}
}
