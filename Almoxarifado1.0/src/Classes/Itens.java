package Classes;

/**
 *
 * @author heitor
 */

public class Itens {
    int ID_itens;
    int ID_emprestimo;
    int ID_item;
    int qtd;

    public Itens() {
    }

    public Itens(int ID_itens, int ID_emprestimo, int ID_item, int qtd) {
        this.ID_itens = ID_itens;
        this.ID_emprestimo = ID_emprestimo;
        this.ID_item = ID_item;
        this.qtd = qtd;
    }

    public int getID_itens() {
        return ID_itens;
    }

    public void setID_itens(int ID_itens) {
        this.ID_itens = ID_itens;
    }

    public int getID_emprestimo() {
        return ID_emprestimo;
    }

    public void setID_emprestimo(int ID_emprestimo) {
        this.ID_emprestimo = ID_emprestimo;
    }

    public int getID_item() {
        return ID_item;
    }

    public void setID_item(int ID_item) {
        this.ID_item = ID_item;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }
    
}
