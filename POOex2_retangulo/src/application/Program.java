package application;

import java.util.Scanner;

import entities.Retangulo;

public class Program {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		Retangulo ret = new Retangulo();
		
		
		System.out.print("Digite a altura do retangulo: ");
		ret.height = sc.nextDouble();
		System.out.print("Digite a largura do retangulo: ");
		ret.width = sc.nextDouble();
		
		System.out.println(ret);
		
		
		sc.close();
		
	}
}
