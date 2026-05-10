/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

public class Item {

    int ID_itens;
    Emprestimo emprestimo; // Representa o ID_emprestimo (Join)
    Estoque estoque;       // Representa o ID_item (Join)
    int qtd;

    public Item() {
    }

    public Item(int ID_itens, Emprestimo emprestimo, Estoque estoque, int qtd) {
        this.ID_itens = ID_itens;
        this.emprestimo = emprestimo;
        this.estoque = estoque;
        this.qtd = qtd;
    }

    public int getID_itens() {
        return ID_itens;
    }

    public void setID_itens(int ID_itens) {
        this.ID_itens = ID_itens;
    }

    public Emprestimo getEmprestimo() {
        return emprestimo;
    }

    public void setEmprestimo(Emprestimo emprestimo) {
        this.emprestimo = emprestimo;
    }

    public Estoque getEstoque() {
        return estoque;
    }

    public void setEstoque(Estoque estoque) {
        this.estoque = estoque;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }
}