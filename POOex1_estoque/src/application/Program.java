package application;

import java.util.Scanner;

import entities.Produto;

public class Program {
	public static void main(String[] srgs) {
		
		Produto p = new Produto();
		Scanner sc = new Scanner(System.in);
		int quantadd, quantremove;
		
		
		System.out.print("Digite o nome do produto a ser adicionado: ");
		p.nome = sc.next();
		
		System.out.print("Digite o preço do item: ");
		p.preco = sc.nextInt();
		
		System.out.print("Digite a quantidade que será adicionada ao estoque: ");
		p.quant = sc.nextInt();
		
		System.out.println(p.toString());
		System.out.println("\n\ndigite uma quantidade para adicionar ao estoque: ");
		
		quantadd = sc.nextInt();
		p.addProduto(quantadd);
		
		System.out.printf("Informações atualizadas do produto:" + p.toString());
		System.out.println("\n\ndigite uma quantidade para remover do estoque: ");
		
		quantremove = sc.nextInt();
		p.removeProduto(quantremove);
		
		System.out.printf("Informações atualizadas do produto:" + p.toString());
		
		sc.close();
	}
}
