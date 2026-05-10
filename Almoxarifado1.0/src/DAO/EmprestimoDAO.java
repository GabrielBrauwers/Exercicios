package DAO;

import Classes.Emprestimo;
import Util.Conexao;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class EmprestimoDAO {

    private Connection conn;

    public EmprestimoDAO() {
        try {
            this.conn = Conexao.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ---------- INSERIR ----------
    public int Inserir(Emprestimo e) throws ErpDAOException {
        if (e == null) {
            throw new ErpDAOException("Objeto Emprestimo não pode ser nulo.");
        }

        String sql = "INSERT INTO emprestimo (ID_usuario, nome_aluno, nome_professor, observacao, data_hora_retirada, data_hora_devolucao) VALUES (?,?,?,?,?,?)";

        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, e.getID_usuario());
            ps.setString(2, e.getNome_aluno());
            ps.setString(3, e.getNome_professor());
            ps.setString(4, e.getObservacao());
            ps.setTimestamp(5, Timestamp.valueOf(e.getData_hora_retirada()));
            ps.setTimestamp(6, e.getData_hora_devolucao() != null ? Timestamp.valueOf(e.getData_hora_devolucao()) : null);

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException ex) {
            throw new ErpDAOException("Erro ao inserir empréstimo: " + ex.getMessage(), ex);
        }
        return -1;
    }

    /*   public void Inserir(Emprestimo e) throws ErpDAOException {
        if (e == null) {
            throw new ErpDAOException("Objeto Emprestimo não pode ser nulo.");
        }

        String sql = "INSERT INTO emprestimo (ID_usuario, nome_aluno, nome_professor, observacao, data_hora_retirada, data_hora_devolucao) VALUES (?,?,?,?,?,?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, e.getID_usuario());
            ps.setString(2, e.getNome_aluno());
            ps.setString(3, e.getNome_professor());
            ps.setString(4, e.getObservacao());
            ps.setTimestamp(5, Timestamp.valueOf(e.getData_hora_retirada()));
            ps.setTimestamp(6, e.getData_hora_devolucao() != null ? Timestamp.valueOf(e.getData_hora_devolucao()) : null);
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new ErpDAOException("Erro ao inserir empréstimo: " + ex.getMessage(), ex);
        }
    }
     */
    // ---------- ATUALIZAR ----------
    public void Atualizar(Emprestimo e) throws ErpDAOException {
        if (e == null) {
            throw new ErpDAOException("Objeto Emprestimo não pode ser nulo.");
        }

        String sql = "UPDATE emprestimo SET ID_usuario=?, nome_aluno=?, nome_professor=?, observacao=?, data_hora_retirada=?, data_hora_devolucao=? WHERE ID_emprestimo=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, e.getID_usuario());
            ps.setString(2, e.getNome_aluno());
            ps.setString(3, e.getNome_professor());
            ps.setString(4, e.getObservacao());
            ps.setTimestamp(5, Timestamp.valueOf(e.getData_hora_retirada()));
            ps.setTimestamp(6, e.getData_hora_devolucao() != null ? Timestamp.valueOf(e.getData_hora_devolucao()) : null);
            ps.setInt(7, e.getID_emprestimo());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new ErpDAOException("Erro ao atualizar empréstimo: " + ex.getMessage(), ex);
        }
    }

    // ---------- PROCURAR ----------
    public Emprestimo Procurar(int id) {
        Emprestimo e = null;
        String sql = "SELECT * FROM emprestimo WHERE ID_emprestimo=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    e = new Emprestimo(
                            rs.getInt("ID_emprestimo"),
                            rs.getInt("ID_usuario"),
                            rs.getString("nome_aluno"),
                            rs.getString("nome_professor"),
                            rs.getString("observacao"),
                            rs.getTimestamp("data_hora_retirada").toLocalDateTime(),
                            rs.getTimestamp("data_hora_devolucao") != null ? rs.getTimestamp("data_hora_devolucao").toLocalDateTime() : null
                    );
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return e;
    }

    // ---------- LISTAR ----------
    public ArrayList<Emprestimo> Listar() {
        ArrayList<Emprestimo> lista = new ArrayList<>();
        String sql = "SELECT * FROM emprestimo";

        try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new Emprestimo(
                        rs.getInt("ID_emprestimo"),
                        rs.getInt("ID_usuario"),
                        rs.getString("nome_aluno"),
                        rs.getString("nome_professor"),
                        rs.getString("observacao"),
                        rs.getTimestamp("data_hora_retirada").toLocalDateTime(),
                        rs.getTimestamp("data_hora_devolucao") != null ? rs.getTimestamp("data_hora_devolucao").toLocalDateTime() : null
                ));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    // ---------- EXCLUIR ----------
    public void Excluir(int id) throws ErpDAOException {
        String sql = "DELETE FROM emprestimo WHERE ID_emprestimo=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new ErpDAOException("Erro ao excluir empréstimo: " + ex.getMessage(), ex);
        }
    }

    // ---------- LISTAR ITENS ----------
    public ArrayList<Object[]> listarItensEmprestimos() {
        ArrayList<Object[]> lista = new ArrayList<>();
        String sql = "SELECT e.ID_emprestimo, es.item, i.qtd, e.nome_aluno, e.nome_professor, e.observacao, e.data_hora_retirada,e.data_hora_devolucao FROM emprestimo e JOIN itens i ON e.ID_emprestimo = i.ID_emprestimo JOIN estoque es ON i.ID_item = es.ID_item WHERE e.data_hora_devolucao IS NULL ORDER BY e.nome_aluno";

        try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Object[] linha = new Object[8];
                linha[0] = rs.getInt("ID_emprestimo");
                linha[1] = rs.getString("item");
                linha[2] = rs.getInt("qtd");
                linha[3] = rs.getString("nome_aluno");
                linha[4] = rs.getString("nome_professor");
                linha[5] = rs.getString("observacao");
                linha[6] = rs.getTimestamp("data_hora_retirada");
                linha[7] = rs.getTimestamp("data_hora_devolucao");
                lista.add(linha);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return lista;
    }

    public void devolverEmprestimo(int idEmprestimo, LocalDateTime dataDevolucao)
            throws ErpDAOException {

        String sqlAtualizaEmprestimo
                = "UPDATE emprestimo SET data_hora_devolucao=? WHERE ID_emprestimo=?";

        String sqlBuscaItens
                = "SELECT ID_item, qtd FROM itens WHERE ID_emprestimo=?";

        String sqlAtualizaEstoque
                = "UPDATE estoque SET quantidade = quantidade + ?, emprestados = emprestados - ? "
                + "WHERE ID_item=?";

        try {
            conn.setAutoCommit(false); // 🔒 inicia transação

            // 1️⃣ Marca o empréstimo como devolvido
            PreparedStatement psEmp = conn.prepareStatement(sqlAtualizaEmprestimo);
            psEmp.setTimestamp(1, Timestamp.valueOf(dataDevolucao));
            psEmp.setInt(2, idEmprestimo);

            if (psEmp.executeUpdate() == 0) {
                throw new ErpDAOException("Empréstimo não encontrado.");
            }

            // 2️⃣ Busca itens do empréstimo
            PreparedStatement psBusca = conn.prepareStatement(sqlBuscaItens);
            psBusca.setInt(1, idEmprestimo);
            ResultSet rs = psBusca.executeQuery();

            boolean temItens = false;
            System.out.println("ID do empréstimo: " + idEmprestimo);

            while (rs.next()) {
                temItens = true;

                int idItem = rs.getInt("ID_item");
                int qtd = rs.getInt("qtd");

                // 3️⃣ Devolve ao estoque
                PreparedStatement psEstoque = conn.prepareStatement(sqlAtualizaEstoque);
                psEstoque.setInt(1, qtd);
                psEstoque.setInt(2, qtd);
                psEstoque.setInt(3, idItem);
                psEstoque.executeUpdate();
                System.out.println("Devolvendo item: " + idItem + ", qtd: " + qtd);

            }

            if (!temItens) {
                throw new ErpDAOException("Nenhum item encontrado para este empréstimo.");
            }

            conn.commit(); // ✅ tudo certo

        } catch (Exception ex) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
            }
            throw new ErpDAOException("Erro ao devolver empréstimo: " + ex.getMessage(), ex);

        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e1) {
            }
        }
    }

    public void adicionarItemEmprestimo(int idEmprestimo, String nomeItem, int qtd) throws SQLException {
        // 1️⃣ Pega o ID do item pelo nome
        String sqlBuscaId = "SELECT ID_item FROM estoque WHERE item = ?";
        PreparedStatement psBusca = conn.prepareStatement(sqlBuscaId);
        psBusca.setString(1, nomeItem);
        ResultSet rs = psBusca.executeQuery();

        if (!rs.next()) {
            throw new SQLException("Item não encontrado no estoque: " + nomeItem);
        }
        int idItem = rs.getInt("ID_item");
        int qtdDisponivel = rs.getInt("quantidade");

        //Pega a quantidade disponível no estoque
        if (qtd > qtdDisponivel) {
            throw new SQLException("Quantidade maior que o estoque disponível.");
        }

        // Verificação se item já existente na tabela (Fran)
        // Vendo se o item já existe
        String sqlVerificarId = "SELECT qtd FROM itens WHERE ID_emprestimo = ? AND ID_item = ?";
        PreparedStatement psVerificar = conn.prepareStatement(sqlVerificarId);
        psVerificar.setInt(1, idEmprestimo);
        //estava com nome agora busca com Id
        psVerificar.setInt(2, idItem);

        ResultSet rsVerificar = psVerificar.executeQuery();

        if (rsVerificar.next()) {

            // Verifica se a soma da quantidade atual
            int qtdAtual = rsVerificar.getInt("qtd");

            // nova quantidade não ultrapassa o estoque
            if ((qtdAtual + qtd) > qtdDisponivel) {
                throw new SQLException("Quantidade total excede o estoque.");
            }

            String sqlNovo = "UPDATE itens SET qtd = ? WHERE ID_emprestimo = ? AND ID_item = ?";
            PreparedStatement psNovo = conn.prepareStatement(sqlNovo);
            psNovo.setInt(1, qtdAtual + qtd);
            psNovo.setInt(2, idEmprestimo);
            psNovo.setInt(3, idItem);
            psNovo.executeUpdate();
        } else {

            // 2️⃣ Adiciona na tabela itens
            String sqlInsere = "INSERT INTO itens (ID_emprestimo, ID_item, qtd) VALUES (?, ?, ?)";
            PreparedStatement psInsere = conn.prepareStatement(sqlInsere);
            psInsere.setInt(1, idEmprestimo);
            psInsere.setInt(2, idItem);
            psInsere.setInt(3, qtd);
            psInsere.executeUpdate();

            // 3️⃣ Atualiza estoque
            atualizarEstoqueAoEmprestar(nomeItem, qtd);
        }
    }
    // Atualiza estoque ao emprestar

    public void atualizarEstoqueAoEmprestar(String nomeItem, int qtd) throws SQLException {
        // Agora busca por quantidade também 
        String sqlAtualiza = "UPDATE estoque SET quantidade = quantidade - ?, emprestados = emprestados + ? WHERE item = ?";
        PreparedStatement ps = conn.prepareStatement(sqlAtualiza);
        ps.setInt(1, qtd);
        ps.setInt(2, qtd);
        ps.setString(3, nomeItem);

        int affected = ps.executeUpdate();
        if (affected == 0) {
            throw new SQLException("Erro ao atualizar estoque do item: " + nomeItem);
        }
    }

    public ArrayList<Object[]> listarItensEstoque() {
        ArrayList<Object[]> lista = new ArrayList<>();
        String sql = "SELECT ID_item, item, quantidade, emprestados FROM estoque ORDER BY item";

        try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Object[] linha = new Object[4];
                linha[0] = rs.getInt("ID_item");      // ID do item
                linha[1] = rs.getString("item");      // Nome do item
                linha[2] = rs.getInt("quantidade");   // Quantidade disponível
                linha[3] = rs.getInt("emprestados");  // Quantidade emprestada
                lista.add(linha);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return lista;
    }

    public ArrayList ListarRecente() {
        PreparedStatement ps = null;
        Connection connL = null;
        ResultSet rs = null;
        ArrayList emprestimos = new ArrayList();
        try {
            String SQL = "SELECT * FROM emprestimo ORDER BY ID_emprestimo DESC";
            connL = this.conn;
            ps = connL.prepareStatement(SQL);
            rs = ps.executeQuery();

            while (rs.next()) {
                int ID_emprestimo = rs.getInt("ID_emprestimo");
                int ID_usuario = rs.getInt("ID_usuario");
                String nome_aluno = rs.getString("nome_aluno");
                String nome_professor = rs.getString("nome_professor");
                String observacao = rs.getString("observacao");
                LocalDateTime data_hora_retirada = rs.getObject("data_hora_retirada", LocalDateTime.class);
                LocalDateTime data_hora_devolucao = rs.getObject("data_hora_devolucao", LocalDateTime.class);
                emprestimos.add(new Emprestimo(ID_emprestimo, ID_usuario, nome_aluno, nome_professor, observacao, data_hora_retirada, data_hora_devolucao));
            }

        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        } finally {
            //Conexao.close(connL, ps);
        }

        return emprestimos;
    }

    public void devolverItemIndividual(int idItens, int qtd, int idEmprestimo, int idUsuario) throws ErpDAOException {
        String sqlBuscaIdItem = "SELECT ID_item FROM itens WHERE ID_itens=?";
        String sqlZerarItem = "UPDATE itens SET qtd = 0 WHERE ID_itens=?";
        String sqlAtualizaEstoque = "UPDATE estoque SET quantidade = quantidade + ?, emprestados = GREATEST(0, emprestados - ?) WHERE ID_item=?";

        try {
            conn.setAutoCommit(false); // Inicia transação

            // 1️⃣ Busca o ID_item do item a ser devolvido
            PreparedStatement psBusca = conn.prepareStatement(sqlBuscaIdItem);
            psBusca.setInt(1, idItens);
            ResultSet rs = psBusca.executeQuery();

            if (!rs.next()) {
                throw new ErpDAOException("Item não encontrado.");
            }

            int idItem = rs.getInt("ID_item");

            // 2️⃣ Zera a quantidade do item na tabela itens (não deleta)
            PreparedStatement psZerar = conn.prepareStatement(sqlZerarItem);
            psZerar.setInt(1, idItens);
            psZerar.executeUpdate();

            // 3️⃣ Atualiza o estoque (devolve a quantidade) e garante que emprestados não fique negativo
            PreparedStatement psEstoque = conn.prepareStatement(sqlAtualizaEstoque);
            psEstoque.setInt(1, qtd);
            psEstoque.setInt(2, qtd);
            psEstoque.setInt(3, idItem);
            psEstoque.executeUpdate();

            conn.commit(); // Confirma transação

        } catch (Exception ex) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
            }
            throw new ErpDAOException("Erro ao devolver item individual: " + ex.getMessage(), ex);
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e1) {
            }
        }

    }

    public int buscarIdItens(int idEmprestimo, String nomeItem) throws SQLException {
        int idItens = -1;
        String sql = "SELECT i.ID_itens FROM itens i "
                + "JOIN estoque e ON i.ID_item = e.ID_item "
                + "WHERE i.ID_emprestimo = ? AND e.item = ? AND i.qtd > 0";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idEmprestimo);
            ps.setString(2, nomeItem);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    idItens = rs.getInt("ID_itens");
                }
            }
        }
        return idItens;
    }
}
