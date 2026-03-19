package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import entities.Department;
import entities.HourContract;
import entities.Worker;
import entities.WorkerLevel;

public class Program {
	public static void main(String[]args) {
		
		Scanner sc = new Scanner(System.in);
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		System.out.println("Enter department's name:");
		String departmentName = sc.nextLine();
		
		Department department = new Department(departmentName);
		
		System.out.println("Enter worker data:");
		
		System.out.print("Name:");
		String name = sc.nextLine();
		
		System.out.println("Level:");
		WorkerLevel level = WorkerLevel.valueOf(sc.nextLine());
		
		System.out.println("Base salary:");
		Double baseSalary = sc.nextDouble();
		
		Worker worker = new Worker(name, level, baseSalary, department);
		
		System.out.println("How many contracts to this worker?");
		int n = sc.nextInt();
		
		for(int i = 1; i <= n; i++) {
			System.out.printf("Enter contract's #%d data", i);
			
			System.out.print("\nDate (dd/mm/yyyy):");
			LocalDate contractDate = LocalDate.parse(sc.next(), fmt);
			
			System.out.print("Value per hour:");
			Double valuePerHour = sc.nextDouble();
			
			System.out.print("Hours:");
			int hours = sc.nextInt();
			
			HourContract hourContract = new HourContract(contractDate, valuePerHour, hours);
			worker.addContract(hourContract);
		}
		
		
		System.out.print("Enter month and year to calculate income (mm/yyyy): ");
		String monthAndYear = sc.next();
		
		int month = Integer.parseInt(monthAndYear.substring(0, 2));
		int year = Integer.parseInt(monthAndYear.substring(3));
		
		System.out.printf("\nName: %s", worker.getName());
		System.out.printf("\nDeparment: %s", worker.getDeparment().getName());
		System.out.printf("\nIncome for %s: %.2f", monthAndYear, worker.income(year, month));
		
		
		sc.close();
	}
}
