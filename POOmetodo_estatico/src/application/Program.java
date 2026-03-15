package application;

import java.util.Scanner;

import entities.Quadrado;
import util.CalcularArea;

public class Program {
	public static void main(String[]args) {
		
		Quadrado sqr = new Quadrado();
		Scanner sc = new Scanner(System.in);
		double area;
		
		System.out.println("Digite o lado do quadrado");
		sqr.lado = sc.nextInt();
		
		area = CalcularArea.Calcular(sqr.lado);
		
		System.out.printf("area do quadrado = %.1f", area);
		
		sc.close();
		
	}
}
