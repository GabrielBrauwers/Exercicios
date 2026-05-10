/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DAO.ErpDAOException;
import Util.Conexao;
import Classes.Estoque;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class EstoqueDAO {

    private Connection conn;

    public EstoqueDAO() {
        try {
            this.conn = Conexao.getConnection();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void inserir(Estoque estoque) {
        PreparedStatement ps = null;
        Connection connL = null;
        try {
            String SQL = "INSERT INTO estoque (item, quantidade, descricao, emprestados, indisponivel, inativo) "
                    + "values (?,?,?,?,?,?)";
            connL = this.conn;
            ps = connL.prepareStatement(SQL);
            ps.setString(1, estoque.getItem());
            ps.setInt(2, estoque.getQuantidade());
            ps.setString(3, estoque.getDescricao());
            ps.setInt(4, estoque.getEmprestados());
            ps.setInt(5, estoque.getIndisponivel());
            ps.setInt(6, estoque.getInativo());

            ps.executeUpdate();
        } catch (SQLException sqle) {
            System.out.println("Erro ao inserir novo item no estoque" + sqle);
        } finally {
            Conexao.close(connL, ps);
        }
    }

    public void atualizar(Estoque estoque) {
        PreparedStatement ps = null;
        Connection connL = null;

        try {
            String SQL = "UPDATE estoque set item = ?, quantidade = ?, descricao = ?, emprestados = ?, indisponivel = ?, inativo = ? where ID_item = ?";
            connL = this.conn;
            ps = connL.prepareStatement(SQL);
            ps.setString(1, estoque.getItem());
            ps.setInt(2, estoque.getQuantidade());
            ps.setString(3, estoque.getDescricao());
            ps.setInt(4, estoque.getEmprestados());
            ps.setInt(5, estoque.getIndisponivel());
            ps.setInt(6, estoque.getInativo());
            ps.setInt(7, estoque.getID_item());

            ps.executeUpdate();

        } catch (SQLException sqle) {
            System.out.println("Erro ao atualizar item do estoque" + sqle);
        } finally {
            Conexao.close(connL, ps);
        }
    }

    public Estoque procurar(int codigo) {
        Estoque estoque = new Estoque();
        PreparedStatement ps = null;
        Connection connL = null;
        ResultSet rs = null;

        try {
            String SQL = "SELECT * FROM estoque WHERE ID_item = ?";
            connL = this.conn;
            ps = connL.prepareStatement(SQL);
            ps.setInt(1, codigo);
            rs = ps.executeQuery();
            while (rs.next()) {
                int ID_item = rs.getInt("ID_item");
                String item = rs.getString("item");
                int quantidade = rs.getInt("quantidade");
                String descricao = rs.getString("descricao");
                int emprestados = rs.getInt("emprestados");
                int indisponivel = rs.getInt("indisponivel");
                int inativo = rs.getInt("inativo");

                estoque = new Estoque(ID_item, item, quantidade, descricao, emprestados, indisponivel, inativo);
            }

        } catch (SQLException sqle) {
            System.out.println("Erro ao procurar item" + sqle);
        } finally {
            Conexao.close(connL, ps);
        }
        return estoque;
    }

    public ArrayList Listar() {
        PreparedStatement ps = null;
        Connection connL = null;
        ResultSet rs = null;
        ArrayList listaEstoque = new ArrayList();
        try {
            String SQL = "SELECT * FROM estoque ORDER BY item";
            connL = this.conn;

            ps = connL.prepareStatement(SQL);
            rs = ps.executeQuery();

            while (rs.next()) {
                int ID_item = rs.getInt("ID_item");
                String item = rs.getString("item");
                int quantidade = rs.getInt("quantidade");
                String descricao = rs.getString("descricao");
                int emprestados = rs.getInt("emprestados");
                int indisponivel = rs.getInt("indisponivel");
                int inativo = rs.getInt("inativo");

                listaEstoque.add(new Estoque(ID_item, item, quantidade, descricao, emprestados, indisponivel, inativo));
            }

        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        } finally {
            Conexao.close(connL, ps);
        }

        return listaEstoque;
    }

    public void excluir(Estoque estoque) {
        PreparedStatement ps = null;
        Connection connL = null;
        if (estoque == null) {
            System.out.println("O objeto estoque não pode ser nulo.");
        }

        try {
            String SQL = "DELETE FROM estoque WHERE ID_item=?";
            connL = this.conn;

            ps = connL.prepareStatement(SQL);
            ps.setInt(1, estoque.getID_item());
            ps.executeUpdate();
        } catch (SQLException sqle) {
            System.out.println("Erro ao excluir item " + sqle);
        } finally {
            Conexao.close(connL, ps);
        }
    }

    public void atualizarCompleto(Estoque estoque, int usuarioId, int quantidadeAnterior,
            int disponibilidadeAnterior, String nomeAnterior,
            String descricaoAnterior, String motivoGeral) {
        Connection connL = null;
        PreparedStatement psUpdate = null;

        try {
            connL = Conexao.getConnection();

            String SQL = "UPDATE estoque SET item = ?, quantidade = ?, descricao = ?, "
                    + "emprestados = ?, indisponivel = ?, inativo = ? WHERE ID_item = ?";
            psUpdate = connL.prepareStatement(SQL);
            psUpdate.setString(1, estoque.getItem());
            psUpdate.setInt(2, estoque.getQuantidade());
            psUpdate.setString(3, estoque.getDescricao());
            psUpdate.setInt(4, estoque.getEmprestados());
            psUpdate.setInt(5, estoque.getIndisponivel());
            psUpdate.setInt(6, estoque.getInativo());
            psUpdate.setInt(7, estoque.getID_item());

            psUpdate.executeUpdate();
            psUpdate.close();

            int disponibilidadeAtual = calcularDisponibilidade(estoque);
            Timestamp agora = new Timestamp(System.currentTimeMillis());

            if (quantidadeAnterior != estoque.getQuantidade()) {
                String evento = (estoque.getQuantidade() > quantidadeAnterior) ? "adição" : "remoção";
                int diferenca = Math.abs(estoque.getQuantidade() - quantidadeAnterior);
                String motivoDetalhado = (motivoGeral != null && !motivoGeral.isEmpty() ? motivoGeral + " | " : "")
                        + "Quantidade: " + quantidadeAnterior + " → " + estoque.getQuantidade();

                inserirAuditoria(connL, estoque.getID_item(), usuarioId, evento,
                        agora, motivoDetalhado, diferenca);
            }

            if (nomeAnterior != null && !nomeAnterior.equals(estoque.getItem())) {
                String motivoDetalhado = (motivoGeral != null && !motivoGeral.isEmpty() ? motivoGeral + " | " : "")
                        + "Nome alterado: '" + nomeAnterior + "' → '" + estoque.getItem() + "'";

                inserirAuditoria(connL, estoque.getID_item(), usuarioId, "alteração",
                        new Timestamp(agora.getTime() + 100), motivoDetalhado, 0);
            }

            if (descricaoAnterior != null && !descricaoAnterior.equals(estoque.getDescricao())) {
                String motivoDetalhado = (motivoGeral != null && !motivoGeral.isEmpty() ? motivoGeral + " | " : "")
                        + "Descrição alterada";

                inserirAuditoria(connL, estoque.getID_item(), usuarioId, "alteração",
                        new Timestamp(agora.getTime() + 200), motivoDetalhado, 0);
            }

            if (disponibilidadeAnterior != disponibilidadeAtual) {
                String evento = "";
                String[] status = {"disponível", "indisponível", "inativo"};

                if (disponibilidadeAtual == 2) {
                    evento = "exclusão";
                } else if (disponibilidadeAtual == 1) {
                    evento = "indisponibilizado";
                } else if (disponibilidadeAtual == 0 && disponibilidadeAnterior == 1) {
                    evento = "disponibilizado";
                } else if (disponibilidadeAtual == 0 && disponibilidadeAnterior == 2) {
                    evento = "ativação";
                }

                if (!evento.isEmpty()) {
                    String motivoDetalhado = (motivoGeral != null && !motivoGeral.isEmpty() ? motivoGeral + " | " : "")
                            + "Status: " + status[disponibilidadeAnterior] + " → " + status[disponibilidadeAtual];

                    inserirAuditoria(connL, estoque.getID_item(), usuarioId, evento,
                            new Timestamp(agora.getTime() + 300), motivoDetalhado, 0);
                }
            }

        } catch (SQLException sqle) {
            System.out.println("Erro ao atualizar item: " + sqle.getMessage());
            sqle.printStackTrace();
        } finally {
            if (psUpdate != null) try {
                psUpdate.close();
            } catch (SQLException e) {
            }
            if (connL != null) try {
                connL.close();
            } catch (SQLException e) {
            }
        }
    }

    private void inserirAuditoria(Connection conn, int idItem, int usuarioId,
            String evento, Timestamp dataHora, String motivo,
            int quantidade) throws SQLException {
        String SQLAuditoria = "INSERT INTO auditoria (Id_item, id_usuario, evento, data_hora, motivo, quantidade) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(SQLAuditoria);
        ps.setInt(1, idItem);
        ps.setInt(2, usuarioId);
        ps.setString(3, evento);
        ps.setTimestamp(4, dataHora);
        ps.setString(5, motivo);
        ps.setInt(6, quantidade);
        ps.executeUpdate();
        ps.close();
    }

    public void atualizarCompleto(Estoque estoque, int usuarioId, int quantidadeAnterior,
            int disponibilidadeAnterior, String motivoGeral) {
        atualizarCompleto(estoque, usuarioId, quantidadeAnterior, disponibilidadeAnterior,
                null, null, motivoGeral);
    }

    private int calcularDisponibilidade(Estoque estoque) {
        if (estoque.getInativo() == 1) {
            return 2;
        } else if (estoque.getIndisponivel() == 1) {
            return 1;
        } else {
            return 0;
        }
    }

    public void AtualizarQuantidade(int id, int valor) { //deve funcionar
        PreparedStatement ps = null;
        Connection connL = null;

        try {
            String SQL = "UPDATE estoque SET quantidade = ? WHERE ID_item = ?";
            connL = this.conn;
            ps = connL.prepareStatement(SQL);
            ps.setInt(1, valor);
            ps.setInt(2, id);
            ps.executeUpdate();

        } catch (SQLException sqle) {
            System.out.println("Erro ao atualizar item do estoque" + sqle);
        } finally {
            //Conexao.close(connL, ps);
        }
    }

    public boolean inserirComAuditoria(Estoque estoque, int usuarioId, String motivo) {
        Connection connL = null;
        PreparedStatement ps = null;
        ResultSet generatedKeys = null;
        boolean sucesso = false;

        try {

            String SQL = "INSERT INTO estoque (item, quantidade, descricao, emprestados, indisponivel, inativo) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";

            connL = Conexao.getConnection();

            ps = connL.prepareStatement(SQL, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, estoque.getItem());
            ps.setInt(2, estoque.getQuantidade());
            ps.setString(3, estoque.getDescricao());
            ps.setInt(4, estoque.getEmprestados());
            ps.setInt(5, estoque.getIndisponivel());
            ps.setInt(6, estoque.getInativo());

            int linhasAfetadas = ps.executeUpdate();

            if (linhasAfetadas > 0) {

                generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int idItem = generatedKeys.getInt(1);

                    registrarAuditoriaCriacao(connL, idItem, usuarioId, motivo, estoque);

                    sucesso = true;
                }
            }

        } catch (SQLException sqle) {
            System.out.println("Erro ao inserir item com auditoria: " + sqle.getMessage());
            sucesso = false;
        } finally {

            if (generatedKeys != null) {
                try {
                    generatedKeys.close();
                } catch (SQLException e) {
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                }
            }
            if (connL != null) {
                try {
                    connL.close();
                } catch (SQLException e) {
                }
            }
        }

        return sucesso;
    }

    private void registrarAuditoriaCriacao(Connection conn, int idItem, int usuarioId,
            String motivo, Estoque estoque) throws SQLException {
        String SQLAuditoria = "INSERT INTO auditoria (Id_item, id_usuario, evento, data_hora, motivo, quantidade) "
                + "VALUES (?, ?, ?, NOW(), ?, ?)";

        PreparedStatement psAuditoria = null;

        try {

            String status = estoque.getInativo() == 1 ? "INATIVO"
                    : estoque.getIndisponivel() == 1 ? "INDISPONÍVEL" : "DISPONÍVEL";

            String detalhes = "criação - Item: " + estoque.getItem()
                    + " | Quantidade: " + estoque.getQuantidade()
                    + " | Status: " + status
                    + " | Motivo: " + (motivo == null || motivo.isEmpty() ? "Novo item" : motivo);

            psAuditoria = conn.prepareStatement(SQLAuditoria);
            psAuditoria.setInt(1, idItem);
            psAuditoria.setInt(2, usuarioId);
            psAuditoria.setString(3, "criação");
            psAuditoria.setString(4, detalhes);
            psAuditoria.setInt(5, estoque.getQuantidade());

            psAuditoria.executeUpdate();

        } finally {
            if (psAuditoria != null) {
                try {
                    psAuditoria.close();
                } catch (SQLException e) {
                }
            }
        }
    }

    public boolean inserirEAuditar(Estoque estoque, int usuarioId, String motivo) {
        Connection connL = null;
        boolean sucesso = false;

        try {
            connL = Conexao.getConnection();
            connL.setAutoCommit(false);

            String SQL = "INSERT INTO estoque (item, quantidade, descricao, emprestados, indisponivel, inativo) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = connL.prepareStatement(SQL, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, estoque.getItem());
            ps.setInt(2, estoque.getQuantidade());
            ps.setString(3, estoque.getDescricao());
            ps.setInt(4, estoque.getEmprestados());
            ps.setInt(5, estoque.getIndisponivel());
            ps.setInt(6, estoque.getInativo());

            int linhasAfetadas = ps.executeUpdate();

            if (linhasAfetadas > 0) {
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int idItem = generatedKeys.getInt(1);

                    String SQLAuditoria = "INSERT INTO auditoria (Id_item, id_usuario, evento, data_hora, motivo, quantidade) "
                            + "VALUES (?, ?, ?, NOW(), ?, ?)";

                    PreparedStatement psAudit = connL.prepareStatement(SQLAuditoria);
                    psAudit.setInt(1, idItem);
                    psAudit.setInt(2, usuarioId);
                    psAudit.setString(3, "criação");

                    String detalhes = "Novo item criado: " + estoque.getItem()
                            + " | Qtd: " + estoque.getQuantidade()
                            + " | Motivo: " + (motivo == null || motivo.isEmpty() ? "Criação padrão" : motivo);

                    psAudit.setString(4, detalhes);
                    psAudit.setInt(5, estoque.getQuantidade());
                    psAudit.executeUpdate();
                    psAudit.close();

                    connL.commit();
                    sucesso = true;
                }
                generatedKeys.close();
            }

            ps.close();

        } catch (SQLException sqle) {
            System.out.println("Erro ao inserir com auditoria: " + sqle.getMessage());
            try {
                if (connL != null) {
                    connL.rollback();
                }
            } catch (SQLException e) {
                System.out.println("Erro no rollback: " + e.getMessage());
            }
            sucesso = false;
        } finally {
            if (connL != null) {
                try {
                    connL.setAutoCommit(true);
                    connL.close();
                } catch (SQLException e) {
                }
            }
        }

        return sucesso;
    }

    public boolean existeItemComMesmoNome(String nomeItem) {
        return existeItemComMesmoNome(nomeItem, null);
    }

    public boolean existeItemComMesmoNome(String nomeItem, Integer idIgnorado) {
        PreparedStatement ps = null;
        Connection connL = null;
        ResultSet rs = null;

        if (nomeItem == null || nomeItem.trim().isEmpty()) {
            return false;
        }

        try {
            StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM estoque WHERE UPPER(TRIM(item)) = UPPER(TRIM(?))");
            if (idIgnorado != null) {
                sql.append(" AND ID_item <> ?");
            }

            connL = Conexao.getConnection();
            ps = connL.prepareStatement(sql.toString());
            ps.setString(1, nomeItem.trim());
            if (idIgnorado != null) {
                ps.setInt(2, idIgnorado);
            }

            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException sqle) {
            System.out.println("Erro ao verificar nome duplicado: " + sqle.getMessage());
        } finally {
            Conexao.close(connL, ps, rs);
        }

        return false;
    }

}
