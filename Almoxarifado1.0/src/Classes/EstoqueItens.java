package Classes;

/**
 *
 * @author 293876
 */

public class EstoqueItens {
    int ID_itens;
    int qtd;
    String item;
    String descricao;
    int ID_item;

    public EstoqueItens() {
    }

    public EstoqueItens(int ID_itens, int qtd, String item, String descricao, int ID_item) {
        this.ID_itens = ID_itens;
        this.qtd = qtd;
        this.item = item;
        this.descricao = descricao;
        this.ID_item = ID_item;
    }

    public int getID_itens() {
        return ID_itens;
    }

    public void setID_itens(int ID_itens) {
        this.ID_itens = ID_itens;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getID_item() {
        return ID_item;
    }

    public void setID_item(int ID_item) {
        this.ID_item = ID_item;
    }
    
}
