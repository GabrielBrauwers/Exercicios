package application;

import java.util.Scanner;

public class Program {
	public static void main(String[]args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Digite quantas linhas e colunas terá a matriz: ");
		
		System.out.print("\nLinhas: ");
		int n = sc.nextInt();
		
		System.out.print("\nColunas: ");
		int m = sc.nextInt();
		
		int mat[][] = new int[n][m];
		
		for(int i = 0; i < mat.length; i ++) {
			for(int j = 0; j < mat[i].length; j++) {
				mat[i][j] = sc.nextInt();
			}
		}
		
		for (int i = 0; i < mat.length; i++) {
		    for (int j = 0; j < mat[i].length; j++) {
		        System.out.print(mat[i][j] + " ");
		    }
		    System.out.println(); 
		}
		
		System.out.println("\n\nDigite o numero para verificar: ");
		int num = sc.nextInt();
		
		for(int i = 0; i < mat.length; i ++) {
			for(int j = 0; j < mat[i].length; j ++) {
				if(mat[i][j] == num) {
					if(i == 0 && j == 0) {
						System.out.println(mat[i][j + 1] + "  ");
						System.out.println(mat[i + 1][j] + "  ");
					}
					else if(i == 0 && j == (m -1)) {
						System.out.println(mat[i + 1][j] + "  ");
						System.out.println(mat[i][j - 1] + "  ");
					}
					else if(i == 0 && j > 0) {
						System.out.println(mat[i][j + 1] + "  ");
						System.out.println(mat[i + 1][j] + "  ");
						System.out.println(mat[i][j - 1] + "  ");
					}
					
					
					else if(i == 1 && j == 0) {
						System.out.println(mat[i - 1][j] + "  ");
						System.out.println(mat[i][j + 1] + "  ");
						System.out.println(mat[i + 1][j] + "  ");
					}
					else if(i == 1 && j == (m -1)) {
						System.out.println(mat[i + 1][j] + "  ");
						System.out.println(mat[i][j - 1] + "  ");
						System.out.println(mat[i - 1][j] + "  ");
					}
					else if(i == 1 && j > 0) {
						System.out.println(mat[i][j + 1] + "  ");
						System.out.println(mat[i + 1][j] + "  ");
						System.out.println(mat[i][j - 1] + "  ");
						System.out.println(mat[i - 1][j] + "  ");
					}
					
				}
			}
		}
		
		sc.close();
	}
}
