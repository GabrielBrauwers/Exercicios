package GUI;

import Classes.Emprestimo;
import DAO.EmprestimoDAO;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;

public class DetalheEmprestimo extends javax.swing.JFrame {

    private EmprestimoDAO dao = new EmprestimoDAO();
    private ArrayList<Integer> idsEmprestimos = new ArrayList<>();
    private int idUsuarioLogado;

    public DetalheEmprestimo() {
        this(0); // Chama o construtor completo com ID 0 como padrão
    }

    public DetalheEmprestimo(int idUsuario) {
        initComponents();
        this.idUsuarioLogado = idUsuario;
        carregarTabela("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabela = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        Aluno = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jButton1.setText("Ok");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        Tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Objeto", "Quantidade", "Nome aluno", "Nome professor", "Observação", "Data Retirada"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        Tabela.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        Tabela.getTableHeader().setReorderingAllowed(false);
        Tabela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabelaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Tabela);
        if (Tabela.getColumnModel().getColumnCount() > 0) {
            Tabela.getColumnModel().getColumn(2).setMinWidth(120);
            Tabela.getColumnModel().getColumn(2).setMaxWidth(120);
        }

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel1.setText("Edição de Emprestimos:");

        jButton2.setText("Professor");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        Aluno.setText("Aluno");
        Aluno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlunoActionPerformed(evt);
            }
        });

        jButton4.setText("Data");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1126, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addGap(53, 53, 53)
                        .addComponent(Aluno)
                        .addGap(68, 68, 68)
                        .addComponent(jButton2)
                        .addGap(75, 75, 75)
                        .addComponent(jButton4))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(500, 500, 500)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2)
                    .addComponent(Aluno)
                    .addComponent(jButton4))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TabelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelaMouseClicked
        int linha = Tabela.getSelectedRow();
        System.out.println("Linha clicada: " + linha);
        if (linha >= 0) {
            int idEmprestimo = idsEmprestimos.get(linha);
            System.out.println("ID selecionado: " + idEmprestimo);
            abrirEdicaoEmprestimo(idEmprestimo);
        }
    }//GEN-LAST:event_TabelaMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed

    }//GEN-LAST:event_formWindowClosed

    private void AlunoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlunoActionPerformed
        carregarTabela("Aluno");
    }//GEN-LAST:event_AlunoActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        carregarTabela("Professor");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        carregarTabela("Data");
    }//GEN-LAST:event_jButton4ActionPerformed
// Novo método sobrecarregado para carregar tabela com filtros

    private void carregarTabela(String tipoFiltro) {
        DefaultTableModel model = (DefaultTableModel) Tabela.getModel();
        model.setRowCount(0); // limpa tabela
        idsEmprestimos.clear();

        // Pega todos os registros detalhados
        ArrayList<Object[]> lista = dao.listarItensEmprestimos();
        
        // Ordena a lista conforme o filtro
        switch (tipoFiltro) {
            case "Aluno":
                lista.sort((a, b) -> a[3].toString().compareToIgnoreCase(b[3].toString())); // coluna aluno
                break;
            case "Professor":
                lista.sort((a, b) -> a[4].toString().compareToIgnoreCase(b[4].toString())); // coluna professor
                break;
            case "Data":
                lista.sort((a, b) -> {
                    // coluna data retirada
                    String dataA = a[6] != null ? a[6].toString() : "";
                    String dataB = b[6] != null ? b[6].toString() : "";
                    return dataB.compareTo(dataA); // mais recente primeiro
                });
                break;
        }

        // Map para agrupar por ID_emprestimo
        Map<Integer, ArrayList<Object[]>> mapaEmprestimos = new LinkedHashMap<>();

        for (Object[] linha : lista) {
            // Filtra apenas empréstimos com data de devolução nula (não devolvidos)
            if (linha[7] == null) {
                int id = Integer.parseInt(linha[0].toString());
                mapaEmprestimos.putIfAbsent(id, new ArrayList<>());
                mapaEmprestimos.get(id).add(linha);
            }
        }

        // Preenche a tabela agrupando os itens
        for (Map.Entry<Integer, ArrayList<Object[]>> entry : mapaEmprestimos.entrySet()) {
            int idEmprestimo = entry.getKey();
            ArrayList<Object[]> detalhes = entry.getValue();

            idsEmprestimos.add(idEmprestimo);

            StringBuilder itensConcatenados = new StringBuilder();
            StringBuilder qtdConcatenadas = new StringBuilder();

            for (Object[] d : detalhes) {
                if (itensConcatenados.length() > 0) {
                    itensConcatenados.append(", ");
                }
                itensConcatenados.append(d[1].toString());

                if (qtdConcatenadas.length() > 0) {
                    qtdConcatenadas.append(", ");
                }
                qtdConcatenadas.append(d[2].toString());
            }

            Object[] primeiro = detalhes.get(0);
            Object nomeAluno = primeiro[3];
            Object nomeProfessor = primeiro[4];
            Object observacao = primeiro[5];
            Object dataRetirada = primeiro[6];
            Object dataDevolucao = primeiro[7];

            model.addRow(new Object[]{
                itensConcatenados.toString(),
                qtdConcatenadas.toString(),
                nomeAluno != null ? nomeAluno.toString() : "",
                nomeProfessor != null ? nomeProfessor.toString() : "",
                observacao != null ? observacao.toString() : "",
                dataRetirada != null ? dataRetirada.toString() : "",
                dataDevolucao != null ? dataDevolucao.toString() : ""
            });
        }
        
    }

    private void abrirEdicaoEmprestimo(int idEmprestimo) {
        try {
            Emprestimo e = dao.Procurar(idEmprestimo);
            if (e != null) {
                EmprestimoEdita edit = new EmprestimoEdita(e, idUsuarioLogado); // Passa o ID do usuário logado
                edit.setLocationRelativeTo(this); // Use 'edit' em vez do nome da classe
                edit.setVisible(true);

                edit.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                        carregarTabela(""); // Recarrega após fechar a edição
                    }
                });
            } else {
                JOptionPane.showMessageDialog(this, "Empréstimo não encontrado!");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Aluno;
    private javax.swing.JTable Tabela;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
