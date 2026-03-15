package application;

import java.util.Scanner;

import entities.Usuario;

public class Program {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		double valor;
		Usuario us;
		
		System.out.println("Para abrir sua conta digite as seguintes informações");
		System.out.printf("Seu nome: ");
		String nome = sc.nextLine();
		
		System.out.printf("O número da sua conta: ");
		int conta = sc.nextInt();
		
		System.out.printf("Existe um valor inicial para depósito (S/N)? ");
		char op = sc.next().charAt(0);
		
		if(op == 's') {
			System.out.printf("Valor inicial para depositar na conta: ");
			valor = sc.nextDouble();
			us = new Usuario(nome, conta, valor);
		}
		else {
			us = new Usuario(nome, conta);
		}
		
		
		System.out.println("Dados da conta: " + us);
		
		System.out.printf("Digite um valor para fazer um depósito: ");
		valor = sc.nextDouble();
		us.depositarDinheiro(valor);
		
		System.out.println("Dados atualizados da conta: " + us);
		
		System.out.printf("Digite um valor para fazer um saque: ");
		valor = sc.nextDouble();
		us.sacarDinheiro(valor);
		
		System.out.println("Dados atualizados da conta: " + us);
		
		sc.close();
	}
}
