package GUI;

import Classes.Emprestimo;
import DAO.EmprestimoDAO;
import DAO.ErpDAOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class EmprestimoEdita extends javax.swing.JFrame {

    private Emprestimo emprestimo;
    private EmprestimoDAO dao = new EmprestimoDAO();
    private int idUsuarioLogado;

    public EmprestimoEdita(Emprestimo emprestimo) {
        this(emprestimo, 0); // Chama o construtor completo com ID 0 como padrão
    }

    public EmprestimoEdita(Emprestimo emprestimo, int idUsuario) {
        this.emprestimo = emprestimo;
        this.idUsuarioLogado = idUsuario;

        initComponents();
        configurarTabela();
        ocultarColunasTabela();

        preencherCampos();
        carregarComboBoxes();
        carregarItens();

        if (emprestimo.getData_hora_devolucao() != null) {
            setReadOnlyMode();
        }
    }

    private void configurarTabela() {

        DefaultTableModel model = new DefaultTableModel(
                new Object[][]{},
                new String[]{"ID_Item", "Objeto", "Quantidade", "Data Retirada", "Observação", "Excluir"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 5; // só a coluna Excluir
            }
        };

        jTable1.setModel(model);

        // Listener de clique (sem mexer no initComponents)
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jRadioButton1 = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        CBAluno = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        CBProfessor = new javax.swing.JComboBox<>();
        jButton4 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        Observação = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        Retirada = new javax.swing.JTextField();

        jRadioButton1.setText("jRadioButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jButton1.setText("Cancelar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jButton2.setText("Confirmar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel2.setText("Data hora devolução:");

        jButton3.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jButton3.setText("Data e hora automatica");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel1.setText("Devolver --->");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID_Item", "Objeto", "Quantidade", "Data Retirada", "Observação", "Excluir"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable1);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel4.setText("Aluno:");

        CBAluno.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        CBAluno.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CBAlunoItemStateChanged(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel5.setText("Professor:");

        CBProfessor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        CBProfessor.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CBProfessorItemStateChanged(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jButton4.setText("Adicionar item");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel7.setText("Observação");

        Observação.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ObservaçãoActionPerformed(evt);
            }
        });

        jLabel8.setText("Data Hora Retirada");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(138, 138, 138)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(385, 385, 385)))
                        .addComponent(jButton2)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(27, 27, 27)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(CBAluno, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(jButton3)
                                .addGap(0, 150, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel7)
                            .addComponent(CBProfessor, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Observação, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Retirada)))
                        .addGap(16, 16, 16))))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 660, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CBAluno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(CBProfessor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(73, 73, 73)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Observação, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(Retirada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(0, 0, 0)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        LocalDateTime DateTimeAtual = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String StringDateTime = DateTimeAtual.format(formatter);
        jTextField3.setText(StringDateTime);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            // 1️⃣ Valida a data de devolução
            if (jTextField3.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Por favor, insira a data e hora de devolução.",
                        "Data não informada",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

            LocalDateTime dataDevolucao = LocalDateTime.parse(jTextField3.getText(), formatter);

            emprestimo.setNome_aluno((String) CBAluno.getSelectedItem());
            emprestimo.setNome_professor((String) CBProfessor.getSelectedItem());
            emprestimo.setData_hora_devolucao(dataDevolucao);

            // 3️⃣ Armazena o ID do usuário que está realizando a alteração (sobrescreve o anterior)
            if (idUsuarioLogado > 0) {
                emprestimo.setID_usuario(idUsuarioLogado);
            }

            // 4️⃣ Atualiza o empréstimo na tabela emprestimo (incluindo o novo ID do usuário e a data de devolução)
            EmprestimoDAO dao = new EmprestimoDAO();
            dao.Atualizar(emprestimo);

            // 5️⃣ Atualiza o estoque: devolve TODOS os itens emprestados de uma vez
            // O método devolverEmprestimo() no DAO é responsável por:
            // - Devolver o volume do item ao estoque (quantidade aumenta, emprestados diminui)
            // - Garantir que emprestados e qtd não fiquem negativos (chegando no máximo a 0)
            dao.devolverEmprestimo(emprestimo.getID_emprestimo(), dataDevolucao);

            // 6️⃣ Fecha a janela
            dispose();

            javax.swing.JOptionPane.showMessageDialog(this, "Empréstimo devolvido com sucesso!");

        } catch (Exception ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(this, "Erro ao devolver empréstimo: " + ex.getMessage());
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void CBAlunoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CBAlunoItemStateChanged
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            emprestimo.setNome_aluno((String) CBAluno.getSelectedItem());
            try{
                dao.Atualizar(emprestimo);
            }catch(ErpDAOException e){
                JOptionPane.showMessageDialog(this, "Erro ao salvar");
            }
            
        }
    }//GEN-LAST:event_CBAlunoItemStateChanged

    private void CBProfessorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CBProfessorItemStateChanged
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            emprestimo.setNome_professor((String) CBProfessor.getSelectedItem());
            try{
                dao.Atualizar(emprestimo);
            }catch(ErpDAOException e){
                JOptionPane.showMessageDialog(this, "Erro ao salvar");
            }
        }
    }//GEN-LAST:event_CBProfessorItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // 1. Cria a instância da tela de Adicionar Item
        AdicionaItem addFrame = new AdicionaItem(emprestimo);
        addFrame.setLocationRelativeTo(this);
        addFrame.setVisible(true);

        // 2. Adiciona um ouvinte para quando a tela de Adicionar Item for fechada
        addFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                // 3. Recarrega a tabela de itens desta tela (EmprestimoEdita)
                // para mostrar o novo item que foi adicionado
                carregarItens();
            }
        });
    }//GEN-LAST:event_jButton4ActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosed

    private void ObservaçãoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ObservaçãoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ObservaçãoActionPerformed
    private void ocultarColunasTabela() {

        // Remove Observação
        jTable1.getColumnModel().removeColumn(
                jTable1.getColumnModel().getColumn(4)
        );

        // Remove Data Retirada
        jTable1.getColumnModel().removeColumn(
                jTable1.getColumnModel().getColumn(3)
        );

        // Remove ID_item (agora é o nome do item, mas deve ser escondido)
        jTable1.getColumnModel().removeColumn(
                jTable1.getColumnModel().getColumn(0)
        );
    }

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {
        int linhaView = jTable1.getSelectedRow();
        int colunaView = jTable1.getSelectedColumn();
        int itensValidos = 0;

        if (linhaView < 0) {
            return;
        }

        // Verifica se clicou na coluna Excluir (pela VIEW)
        if (jTable1.getColumnName(colunaView).equals("Excluir")) {

            int linhaModel = jTable1.convertRowIndexToModel(linhaView);
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

            // O nome do item está na coluna 1 (visível)
            String nomeItem = (String) model.getValueAt(linhaModel, 1);
            int qtd = (int) model.getValueAt(linhaModel, 2);
            if(qtd == 0){
                JOptionPane.showMessageDialog(this, "Esse item já foi excluído");
                return;
            }
            for (int i = 0; i < model.getRowCount(); i++) {
                int tempQtd = (int) model.getValueAt(i, 2);
                System.out.print("TempQTD: " + tempQtd);
                if (tempQtd > 0) {
                    itensValidos++;
                }
                if (itensValidos > 1) {
                    break;
                }
            }

            if (itensValidos < 2) {
                JOptionPane.showMessageDialog(this, "Necessário que haja ao menos um item no empréstimo");
                return;
            }
            int confirmacao = JOptionPane.showConfirmDialog(
                    this,
                    "Deseja devolver o item: " + nomeItem + " (Qtd: " + qtd + ")?",
                    "Confirmar Devolução",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirmacao == JOptionPane.YES_OPTION) {
                try {
                    // Busca o ID_itens usando o novo método do DAO
                    int idItens = dao.buscarIdItens(emprestimo.getID_emprestimo(), nomeItem);

                    if (idItens > 0) {
                        dao.devolverItemIndividual(
                                idItens,
                                qtd,
                                emprestimo.getID_emprestimo(),
                                idUsuarioLogado
                        );

                        JOptionPane.showMessageDialog(this, "Item devolvido com sucesso!");
                        carregarItens();
                    } else {
                        JOptionPane.showMessageDialog(this, "Item não encontrado no banco de dados!");
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao devolver item: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        }
    }

    private void setReadOnlyMode() {
        // Bloqueia edição dos ComboBoxes
        CBAluno.setEnabled(false);
        CBProfessor.setEnabled(false);

        // Bloqueia edição da data
        jTextField3.setEditable(false);
        jButton3.setEnabled(false); // botão de pegar data automática

        // Bloqueia botões de ação
        jButton2.setEnabled(false); // Confirmar
        jButton4.setEnabled(false); // Adicionar item

        // Opcional: muda o título para indicar que é apenas leitura
        jLabel1.setText("Empréstimo (Somente leitura) --->");
    }

    private void preencherCampos() {
        if (emprestimo != null) {
            // Data de devolução
            jTextField3.setText(
                    emprestimo.getData_hora_devolucao() != null
                    ? emprestimo.getData_hora_devolucao().toString()
                    : ""
            );

            // Observação
            Observação.setText(
                    emprestimo.getObservacao() != null
                    ? emprestimo.getObservacao()
                    : ""
            );

            // Data Hora Retirada
            Retirada.setText(
                    emprestimo.getData_hora_retirada() != null
                    ? emprestimo.getData_hora_retirada().toString()
                    : ""
            );
        }
    }

    private void carregarItens() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0); // limpa tabela

        EmprestimoDAO dao = new EmprestimoDAO();
        ArrayList<Object[]> itens = dao.listarItensEmprestimos();
        for (Object[] item : itens) {
            int idEmprestimo = (int) item[0];
            if (idEmprestimo == emprestimo.getID_emprestimo()) {
                Object[] linha = new Object[6];
                linha[0] = item[1]; // Nome do item (usado como identificador na coluna 0, que será escondida)
                linha[1] = item[1]; // Nome do item
                linha[2] = item[2]; // Quantidade
                linha[3] = item[6] != null ? item[6].toString() : ""; // Data retirada
                linha[4] = item[5]; // Observação
                linha[5] = "Excluir"; // Botão Excluir
                model.addRow(linha);
            }
        }
    }

    private void carregarComboBoxes() {
        String aluno = emprestimo.getNome_aluno();
        String professor = emprestimo.getNome_professor();
        
        CBAluno.removeAllItems();
        CBProfessor.removeAllItems();

        ArrayList<Emprestimo> emprestimos = dao.Listar();

        for (Emprestimo e : emprestimos) {
            if (e.getNome_aluno() != null && !itemExiste(CBAluno, e.getNome_aluno())) {
                CBAluno.addItem(e.getNome_aluno());
            }
            if (e.getNome_professor() != null && !itemExiste(CBProfessor, e.getNome_professor())) {
                CBProfessor.addItem(e.getNome_professor());
            }
        }
        CBAluno.getModel().setSelectedItem(aluno);
        CBProfessor.getModel().setSelectedItem(professor);
    }

    private boolean itemExiste(javax.swing.JComboBox<String> combo, String item) {
        for (int i = 0; i < combo.getItemCount(); i++) {
            if (combo.getItemAt(i).equals(item)) {
                return true;
            }
        }
        return false;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CBAluno;
    private javax.swing.JComboBox<String> CBProfessor;
    private javax.swing.JTextField Observação;
    private javax.swing.JTextField Retirada;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
