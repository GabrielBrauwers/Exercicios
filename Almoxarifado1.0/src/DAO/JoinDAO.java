package DAO;

import Classes.EstoqueItens;
import Util.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class JoinDAO {

    private Connection conn;

    public JoinDAO() {
        try {
            this.conn = Conexao.getConnection();
        } catch (Exception e) {
            System.out.println("Erro de conexão: " + ":\n" + e.getMessage());
        }
    }

    public ArrayList ListarEstoqueItens(int codigo) { // Método usado para listar itens ao inserir empréstimo
        PreparedStatement ps = null;
        Connection connL = null;
        ResultSet rs = null;
        ArrayList ListaEstoqueItens = new ArrayList();

        try {
            String SQL = "SELECT itens.ID_itens, itens.qtd, estoque.item, estoque.descricao, itens.ID_item FROM estoque, itens WHERE estoque.ID_item=itens.ID_item AND itens.ID_emprestimo=?;";
            connL = this.conn;
            ps = connL.prepareStatement(SQL);
            ps.setInt(1, codigo);
            rs = ps.executeQuery();
 
            while (rs.next()) {
                int ID_itens = rs.getInt("ID_itens");
                int qtd = rs.getInt("qtd");
                String item = rs.getString("item");
                String descricao = rs.getString("descricao");
                int ID_item = rs.getInt("ID_item");
                ListaEstoqueItens.add(new EstoqueItens(ID_itens, qtd, item, descricao,ID_item));
            }

        } catch (SQLException sqle) {
            System.out.println("Erro ao listar join " + sqle);
        } finally {
            //Conexao.close(connL, ps);
        }
        return ListaEstoqueItens;
    }
}
