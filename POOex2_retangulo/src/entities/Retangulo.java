package entities;

public class Retangulo {
	
	public double width;
	public double height;
	
	public double calcularArea() {
		
		double area = this.height * this.width;
		
		return area;
	}
	
	public double calcularPerimetro() {
		
		double perimetro = (this.height * 2) + (this.width * 2);
		
		return perimetro;
	}
	
	public double calcularDiagonal() {
		
		double diagonal = Math.sqrt(Math.pow(this.height, 2) + Math.pow(this.width, 2));
		
		return diagonal;
		
	}
	
	public String toString() {
		
		return "Largura: "
				+ width
				+ "\n"
				+ "Altura: " 
				+ height 
				+ "\n"
				+ "Area: "
				+ calcularArea()
				+ "\n"
				+ "Perimetro: "
				+ calcularPerimetro()
				+ "\n"
				+ "Diagonal: "
				+ calcularDiagonal();
	}
	
}
