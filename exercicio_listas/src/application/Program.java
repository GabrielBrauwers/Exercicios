package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entities.Funcionario;

public class Program {
	public static void main(String[]args) {
		
		List<Funcionario> list = new ArrayList<>();
		Scanner sc = new Scanner(System.in);
		
		int test = 1;
		
		System.out.print("Quantos funcionarios deseja adicionar? \n\n");
		int size = sc.nextInt();
		
		
		for(int i = 0; i < size; i++) {
			sc.nextLine();
			
			System.out.print("\nId: ");
			Integer id = sc.nextInt();
			
			System.out.print("\nNome: ");
			sc.nextLine();
			String nome = sc.nextLine();
			
			
			System.out.print("\nSalario: ");
			Double salario = sc.nextDouble();
			
			Funcionario funcionario = new Funcionario(id, nome, salario);
			list.add(funcionario);
		}
		
		do {
			System.out.print("Digite o id do funcionario que tera um aumento de salario: \n");
			int idAumento = sc.nextInt();
			Integer pos = posicao(list, idAumento);
			
			if(pos == null) {
				System.out.println("Este id não existe!");
				test = 0;
			}
			else {
				test = 1;
				System.out.print("Digite o aumento em porcentagem: \n\n");
				double porcent = sc.nextInt();
				list.get(pos).calcularSalario(porcent);
			}
		}while(test == 0);
		
		for(Funcionario funcionario : list) {
			System.out.println(funcionario);
		}
		sc.close();
	}
	
	public static Integer posicao(List<Funcionario> list, int id) {
		
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getId() == id) {
				return i;
			}
		}
		return null;
	}
}
