package entities;

public class Produto {
	
	public String nome;
	public double preco;
	public int quant;
	
	public void addProduto(int quant) {
		this.quant += quant;
		
	}
	
	public void removeProduto(int quant) {
		this.quant -= quant;
		
	}
	
	public double valorTotalEmEstoque() {
		
		double vTotal = quant * preco;
		
		return vTotal;
		
	}
	
	public String toString() {
		
		return  nome 
				+ ", " 
				+ "Preço: "
				+ preco
				+ ", " 
				+ "Quantidade: "
				+ quant
				+ ", " 
				+ "Valor total em estoque: "
				+ valorTotalEmEstoque();
	}
	
}
