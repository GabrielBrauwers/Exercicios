package DAO;

import Util.Conexao;
import java.sql.Connection;
import Classes.Usuario;
import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class UsuarioDAO {

    private Connection conn;

    public UsuarioDAO() {
        try {
            this.conn = Conexao.getConnection();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void Inserir(Usuario usuario) {
        PreparedStatement ps = null;
        Connection connL = null;
        /*if (usuario == null) {
            throw new ErpDAOException("O objeto usuario não pode ser nulo.");
        }*/
        try {
            String SQL = "INSERT INTO usuario (nome, data_hora_cadastro, inativo, login, senha, administrador) "
                    + "values (?,?,?,?,?,?)";
            connL = this.conn;
            ps = connL.prepareStatement(SQL);
            ps.setString(1, usuario.getNome());
            Timestamp dataSQL = Timestamp.valueOf(usuario.getData_hora_cadastro()); // deveria funcionar
            ps.setTimestamp(2, dataSQL);
            ps.setInt(3, usuario.getInativo());
            ps.setString(4, usuario.getLogin());
            ps.setString(5, usuario.getSenha());
            ps.setInt(6, usuario.getAdministrador());

            ps.executeUpdate();
        } catch (SQLException sqle) {

        } finally {
            Conexao.close(connL, ps);
        }
    }

    public void Atualizar(Usuario usuario) {
        String SQL = "UPDATE usuario set nome = ?, data_hora_cadastro = ?, inativo = ?, login = ?, senha = ?, administrador = ? where ID_usuario = ?";
        PreparedStatement ps = null;
        Connection connL = null;
        if (usuario == null) {

        }

        try {
            connL = this.conn;
            ps = connL.prepareStatement(SQL);
            connL = this.conn;
            ps = connL.prepareStatement(SQL);
            ps.setString(1, usuario.getNome());
            Timestamp dataSQL = Timestamp.valueOf(usuario.getData_hora_cadastro());
            ps.setTimestamp(2, dataSQL);
            ps.setInt(3, usuario.getInativo());
            ps.setString(4, usuario.getLogin());
            ps.setString(5, usuario.getSenha());
            ps.setInt(6, usuario.getAdministrador());
            ps.setInt(7, usuario.getID_usuario());
            ps.executeUpdate();

        } catch (SQLException sqle) {
            System.out.println("Erro ao inserir itens" + sqle);
        } finally {
            Conexao.close(connL, ps);
        }
    }

    public void AtualizarSenha(Usuario usuario) {
        try {
            this.conn = Conexao.getConnection();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        String SQL = "UPDATE usuario set senha = ? where ID_usuario = ?";
        PreparedStatement ps = null;
        Connection connL = null;
        if (usuario == null) {

        }

        try {
            connL = this.conn;
            ps = connL.prepareStatement(SQL);
            connL = this.conn;
            ps = connL.prepareStatement(SQL);
            ps.setString(1, usuario.getSenha());
            ps.setInt(2, usuario.getID_usuario());
            ps.executeUpdate();

        } catch (SQLException sqle) {
            System.out.println("Erro ao inserir itens" + sqle);
        } finally {
            Conexao.close(connL, ps);
        }
    }

    public Usuario Procurar(int codigo) {
        Usuario usuario = new Usuario();
        PreparedStatement ps = null;
        Connection connL = null;
        ResultSet rs = null;

        try {
            String SQL = "SELECT * FROM usuario WHERE ID_usuario = ?";
            connL = this.conn;
            ps = connL.prepareStatement(SQL);
            ps.setInt(1, codigo);
            rs = ps.executeQuery();
            while (rs.next()) {
                int ID_usuario = rs.getInt("ID_usuario");
                String nome = rs.getString("nome");
                LocalDateTime data_hora_cadastro = rs.getTimestamp("data_hora_cadastro").toLocalDateTime(); // deve funcionar
                int inativo = rs.getInt("inativo");
                String login = rs.getString("login");
                String senha = rs.getString("senha");
                int administrador = rs.getInt("administrador");

                usuario = new Usuario(ID_usuario, nome, data_hora_cadastro, inativo, login, senha, administrador);
            }

        } catch (SQLException sqle) {
            System.out.println("Erro ao inserir itens" + sqle);
        } finally {
            Conexao.close(connL, ps);
        }
        return usuario;
    }

    public ArrayList Listar() {
        PreparedStatement ps = null;
        Connection connL = null;
        ResultSet rs = null;
        ArrayList usuarios = new ArrayList();
        try {
            String SQL = "SELECT * FROM usuario ORDER BY nome";
            connL = this.conn;
            ps = connL.prepareStatement(SQL);
            rs = ps.executeQuery();

            while (rs.next()) {
                int ID_usuario = rs.getInt("ID_usuario");
                String nome = rs.getString("nome");
                LocalDateTime data_hora_cadastro = rs.getTimestamp("data_hora_cadastro").toLocalDateTime();
                int inativo = rs.getInt("inativo");
                String login = rs.getString("login");
                String senha = rs.getString("senha");
                int administrador = rs.getInt("administrador");

                usuarios.add(new Usuario(ID_usuario, nome, data_hora_cadastro, inativo, login, senha, administrador));
            }

        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        } finally {
            Conexao.close(connL, ps, rs);
        }

        return usuarios;
    }

    public void Excluir(int codigo) {
        PreparedStatement ps = null;
        Connection connL = null;
        try {
            String SQL = "DELETE FROM usuario WHERE ID_usuario=?";
            connL = this.conn;
            ps = connL.prepareStatement(SQL);
            ps.setInt(1, codigo);
            ps.executeUpdate();
        } catch (SQLException sqle) {
            System.out.println("Erro ao excluir usuario " + sqle);
        } finally {
            Conexao.close(connL, ps);
        }

    }

}
