package DAO;

import Classes.Auditoria;
import Util.Conexao;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
   import java.util.HashMap;
   import java.util.Map;

public class AuditoriaDAO {

    private Connection conn;

    public AuditoriaDAO() {
        try {
            this.conn = Conexao.getConnection();
        } catch (Exception e) {
            System.out.println("Erro ao conectar: " + e.getMessage());
        }
    }

    public ArrayList<Auditoria> listar() {
        ArrayList<Auditoria> lista = new ArrayList<>();
        String sql = "SELECT * FROM auditoria";

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Auditoria a = mapResultSetParaAuditoria(rs);
                lista.add(a);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public Auditoria buscarPorId(int id) {
        String sql = "SELECT * FROM auditoria WHERE id_auditoria=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetParaAuditoria(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getNomeItem(int id_item) {
        String nome = "Desconhecido";
        String sql = "SELECT item FROM estoque WHERE ID_item=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id_item);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    nome = rs.getString("item");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nome;
    }

    public String getNomeUsuario(int id_usuario) {
        String nome = "Desconhecido";
        String sql = "SELECT nome FROM usuario WHERE id_usuario=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id_usuario);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    nome = rs.getString("nome");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nome;
    }

    public ArrayList<String> listarNomesItens() {
        ArrayList<String> itens = new ArrayList<>();
        String sql = "SELECT item FROM estoque ORDER BY item";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                itens.add(rs.getString("item"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return itens;
    }

    public ArrayList<String> listarNomesUsuarios() {
        ArrayList<String> usuarios = new ArrayList<>();
        String sql = "SELECT nome FROM usuario ORDER BY nome";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                usuarios.add(rs.getString("nome"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    public ArrayList<String> listarDatas() {
        ArrayList<String> datas = new ArrayList<>();
        String sql = "SELECT DISTINCT CAST(data_hora AS DATE) as data FROM auditoria ORDER BY data";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Date d = rs.getDate("data");
                if (d != null) {
                    datas.add(d.toString());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return datas;
    }

    public ArrayList<Auditoria> listarPorItem(String nomeItem) {
        ArrayList<Auditoria> lista = new ArrayList<>();
        String sql = "SELECT * FROM auditoria a JOIN estoque e ON a.id_item = e.ID_item WHERE e.item = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nomeItem);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapResultSetParaAuditoria(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public ArrayList<Auditoria> listarPorUsuario(String usuarioNome) {
        ArrayList<Auditoria> lista = new ArrayList<>();
        String sql = "SELECT * FROM auditoria a JOIN usuario u ON a.id_usuario = u.ID_usuario WHERE u.nome = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, usuarioNome);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapResultSetParaAuditoria(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public ArrayList<Auditoria> listarPorEvento(String evento) {
        ArrayList<Auditoria> lista = new ArrayList<>();
        String sql = "SELECT * FROM auditoria WHERE evento = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, evento);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapResultSetParaAuditoria(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public ArrayList<Auditoria> listarPorData(String data) {
        ArrayList<Auditoria> lista = new ArrayList<>();
        String sql = "SELECT * FROM auditoria WHERE CAST(data_hora AS DATE) = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, data);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapResultSetParaAuditoria(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // ---------------------- Método auxiliar para mapear ResultSet ----------------------
    private Auditoria mapResultSetParaAuditoria(ResultSet rs) throws SQLException {
        Auditoria a = new Auditoria();
        a.setId_auditoria(rs.getInt("id_auditoria"));
        a.setId_item(rs.getInt("id_item"));
        a.setId_usuario(rs.getInt("id_usuario"));
        a.setMotivo(rs.getString("motivo"));

        Timestamp ts = rs.getTimestamp("data_hora");
        if (ts != null) {
            a.setDataHora(new Date(ts.getTime())); // converte Timestamp para Date
        }

        a.setQuantidade(rs.getInt("quantidade"));
        a.setEvento(rs.getString("evento"));
        return a;
    }
    
 
public Map<String, Map<String, Integer>> resumoEstoque() {
    Map<String, Map<String, Integer>> resumo = new HashMap<>();

    String sql = "SELECT e.item, a.evento, SUM(a.quantidade) AS total " +
                 "FROM auditoria a " +
                 "JOIN estoque e ON a.id_item = e.ID_item " +
                 "GROUP BY e.item, a.evento " +
                 "ORDER BY e.item";

    try (PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            String item = rs.getString("item");
            String evento = rs.getString("evento");
            int quantidade = rs.getInt("total");

            resumo.putIfAbsent(item, new HashMap<>());
            resumo.get(item).put(evento, quantidade);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return resumo;
}
}
