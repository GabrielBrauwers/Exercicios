/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DAO.ErpDAOException;
import Util.Conexao;
import Classes.Item;
import Classes.Emprestimo;
import Classes.Estoque;
import Classes.Itens;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItensDAO {
    
    private Connection conn;

    public ItensDAO() {
        try {
            this.conn = Conexao.getConnection();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
      public void InserirItem(Itens itens) throws ErpDAOException {
        PreparedStatement ps = null;
        Connection connL = null;
        if (itens == null) {
            throw new ErpDAOException("O objeto item não pode ser nulo.");
        }
        try {
            String SQL = "INSERT INTO itens (ID_emprestimo, ID_item, qtd) "
                    + "values (?,?,?)";
            connL = this.conn;
            ps = connL.prepareStatement(SQL);
            ps.setInt(1, itens.getID_emprestimo());
            ps.setInt(2, itens.getID_item());
            ps.setInt(3, itens.getQtd());
            ps.executeUpdate();
        } catch (SQLException sqle) {
            throw new ErpDAOException("Erro ao inserir um novo item " + sqle);
        } finally {
            Conexao.close(connL, ps);
        }
    }
    
    
    
    public void inserir(Item itens){
        PreparedStatement ps = null;
        Connection connL = null;
        try {
            String SQL = "INSERT INTO itens (ID_emprestimo, ID_item, qtd) values (?,?,?)";
            connL = this.conn;
            ps = connL.prepareStatement(SQL);
            
            // FKs
            ps.setInt(1, itens.getEmprestimo().getID_emprestimo());
            ps.setInt(2, itens.getEstoque().getID_item());
            
            ps.setInt(3, itens.getQtd());
            
            ps.executeUpdate();
        }catch(SQLException sqle){
            System.out.println("Erro ao inserir itens" + sqle);
        }
        finally{
           Conexao.close(connL,ps);
        }
    }

    public ArrayList ListarPorEmprestimo(int idEmprestimoFiltro) {
        PreparedStatement ps = null;
        Connection connL = null;
        ResultSet rs = null;
        ArrayList listaItens = new ArrayList();
        try {
            String SQL = "SELECT * FROM itens WHERE ID_emprestimo = ?";
            connL = this.conn;

            ps = connL.prepareStatement(SQL);
            ps.setInt(1, idEmprestimoFiltro);
            rs = ps.executeQuery();

            while (rs.next()) {
                int ID_itens = rs.getInt("ID_itens");
                int ID_emprestimo = rs.getInt("ID_emprestimo");
                int ID_item = rs.getInt("ID_item");
                int qtd = rs.getInt("qtd");

                // Objetos Associados
                Emprestimo empObj = new Emprestimo();
                empObj.setID_emprestimo(ID_emprestimo);
                
                Estoque estObj = new Estoque();
                estObj.setID_item(ID_item);

                listaItens.add(new Item(ID_itens, empObj, estObj, qtd));
            }

        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        } finally {
            Conexao.close(connL, ps);
        }

        return listaItens;
    }
    
    public void excluir(Item itens) {
        PreparedStatement ps = null;
        Connection connL = null;

        try {
            String SQL = "DELETE FROM itens WHERE ID_itens=?";
            connL = this.conn;

            ps = connL.prepareStatement(SQL);
            ps.setInt(1, itens.getID_itens());
            ps.executeUpdate();
        } catch (SQLException sqle) {
            System.out.println("Erro ao excluir itens " + sqle);
        } finally {
            Conexao.close(connL, ps);
        }
    }
    
        public void excluirID(int codigo) {
        PreparedStatement ps = null;
        Connection connL = null;

        try {
            String SQL = "DELETE FROM itens WHERE ID_itens=?";
            connL = this.conn;

            ps = connL.prepareStatement(SQL);
            ps.setInt(1, codigo);
            ps.executeUpdate();
        } catch (SQLException sqle) {
            System.out.println("Erro ao excluir itens " + sqle);
        } finally {
            Conexao.close(connL, ps);
        }
    
        }
    
}