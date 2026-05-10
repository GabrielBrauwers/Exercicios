/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

public class Estoque {

    int ID_item;
    String item;
    int quantidade;
    String descricao;
    int emprestados;
    int indisponivel;
    int inativo;

    public Estoque() {
    }

    public Estoque(int ID_item, String item, int quantidade, String descricao, int emprestados, int indisponivel, int inativo) {
        this.ID_item = ID_item;
        this.item = item;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.emprestados = emprestados;
        this.indisponivel = indisponivel;
        this.inativo = inativo;
    }

    public int getID_item() {
        return ID_item;
    }

    public void setID_item(int ID_item) {
        this.ID_item = ID_item;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getEmprestados() {
        return emprestados;
    }

    public void setEmprestados(int emprestados) {
        this.emprestados = emprestados;
    }

    public int getIndisponivel() {
        return indisponivel;
    }

    public void setIndisponivel(int indisponivel) {
        this.indisponivel = indisponivel;
    }

    public int getInativo() {
        return inativo;
    }

    public void setInativo(int inativo) {
        this.inativo = inativo;
    }
}