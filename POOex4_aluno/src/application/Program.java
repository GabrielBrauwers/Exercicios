package application;

import java.util.Scanner;

import entities.Aluno;

public class Program {
	public static void main(String[] args) {
		
		Aluno aluno = new Aluno();
		Scanner sc = new Scanner(System.in);
		
		System.out.printf("Digite o nome do aluno: ");
		aluno.nome = sc.nextLine();
		
		System.out.println("Digite a nota do primeiro treimestre: ");
		do {
			aluno.n1 = sc.nextDouble();
		}while(aluno.n1 > 30);
		
		System.out.println("Digite a nota do segundo treimestre: ");
		do {
			aluno.n2 = sc.nextDouble();
		}while(aluno.n2 > 35);
		
		System.out.println("Digite a nota do terceiro treimestre: ");
		do {
			aluno.n3 = sc.nextDouble();
		}while(aluno.n3 > 35);
		
		aluno.calcularNotaFinal();
		
		sc.close();
	}
}
