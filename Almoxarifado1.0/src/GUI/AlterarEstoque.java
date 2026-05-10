package GUI;

import Classes.Estoque;
import DAO.EstoqueDAO;

import javax.swing.JOptionPane;

public class AlterarEstoque extends javax.swing.JFrame {

    private Estoque itemEstoque;
    private int usuarioId;
    private int quantidadeAnterior;
    private int disponibilidadeAnterior;
    private String nomeAnterior;
    private String descricaoAnterior;

    public AlterarEstoque(int idItem, int usuarioId) {
        this.usuarioId = usuarioId;
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Alterar Item do Estoque");

        carregarDadosItem(idItem);
        txtEmprestados.setEditable(false);
    }

    private void carregarDadosItem(int idItem) {
        EstoqueDAO dao = new EstoqueDAO();
        this.itemEstoque = dao.procurar(idItem);

        if (itemEstoque.getID_item() != 0) {
            this.quantidadeAnterior = itemEstoque.getQuantidade();
            this.nomeAnterior = itemEstoque.getItem();
            this.descricaoAnterior = itemEstoque.getDescricao();

            if (itemEstoque.getInativo() == 1) {
                this.disponibilidadeAnterior = 2;
            } else if (itemEstoque.getIndisponivel() == 1) {
                this.disponibilidadeAnterior = 1;
            } else {
                this.disponibilidadeAnterior = 0;
            }

            txtItem.setText(itemEstoque.getItem());
            txtQuantidade.setText(String.valueOf(itemEstoque.getQuantidade()));
            txtDescricao.setText(itemEstoque.getDescricao());
            txtEmprestados.setText(String.valueOf(itemEstoque.getEmprestados()));

            if (itemEstoque.getInativo() == 1) {
                rbInativo.setSelected(true);
            } else if (itemEstoque.getIndisponivel() == 1) {
                rbIndisponivel.setSelected(true);
            } else {
                rbDisponivel.setSelected(true);
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoDisponibilidade = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtQuantidade = new javax.swing.JTextPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtItem = new javax.swing.JTextPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtDescricao = new javax.swing.JTextPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtEmprestados = new javax.swing.JTextPane();
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        rbIndisponivel = new javax.swing.JRadioButton();
        rbDisponivel = new javax.swing.JRadioButton();
        rbInativo = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Item");

        jLabel2.setText("quantidade");

        jLabel3.setText("descricao");

        jLabel4.setText("emprestados");

        jLabel5.setText("disponibilidade");

        jScrollPane1.setViewportView(txtQuantidade);

        jScrollPane2.setViewportView(txtItem);

        jScrollPane3.setViewportView(txtDescricao);

        jScrollPane4.setViewportView(txtEmprestados);

        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        grupoDisponibilidade.add(rbIndisponivel);
        rbIndisponivel.setText("Indisponível");
        rbIndisponivel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbIndisponivelActionPerformed(evt);
            }
        });

        grupoDisponibilidade.add(rbDisponivel);
        rbDisponivel.setText("Disponível");
        rbDisponivel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbDisponivelActionPerformed(evt);
            }
        });

        grupoDisponibilidade.add(rbInativo);
        rbInativo.setText("Inativo");
        rbInativo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbInativoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(63, 63, 63)
                                .addComponent(jLabel1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(btnSalvar)
                        .addGap(239, 239, 239)
                        .addComponent(btnCancelar))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5)
                        .addGap(36, 36, 36)
                        .addComponent(rbDisponivel)
                        .addGap(37, 37, 37)
                        .addComponent(rbIndisponivel)
                        .addGap(37, 37, 37)
                        .addComponent(rbInativo)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rbIndisponivel)
                            .addComponent(rbInativo))
                        .addComponent(rbDisponivel)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSalvar)
                    .addComponent(btnCancelar))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        try {

            if (txtItem.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "O campo Item é obrigatório.");
                return;
            }

            if ((txtQuantidade.getText().trim().isEmpty()) || (txtQuantidade.getText() == "0")) {
                JOptionPane.showMessageDialog(this, "O campo Quantidade é obrigatório.");
                return;
            }

            int qtd = Integer.parseInt(txtQuantidade.getText());
            if (qtd == 0) {
                JOptionPane.showMessageDialog(this,
                        "Por favor, informe a quantidade do item diferente de 0(zero).",
                        "Campo obrigatório",
                        JOptionPane.WARNING_MESSAGE);
                txtQuantidade.requestFocus();
                return;

            }

            int novaQuantidade;
            try {
                novaQuantidade = Integer.parseInt(txtQuantidade.getText().trim());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "A quantidade deve ser um número válido.");
                return;
            }

            if (novaQuantidade < 0) {
                JOptionPane.showMessageDialog(this, "A quantidade não pode ser negativa.");
                return;
            }

            int novaDisponibilidade;
            if (rbInativo.isSelected()) {
                novaDisponibilidade = 2;
            } else if (rbIndisponivel.isSelected()) {
                novaDisponibilidade = 1;
            } else {
                novaDisponibilidade = 0;
            }

            boolean quantidadeMudou = (novaQuantidade != quantidadeAnterior);
            boolean disponibilidadeMudou = (novaDisponibilidade != disponibilidadeAnterior);
            boolean nomeMudou = !txtItem.getText().trim().equals(nomeAnterior);
            boolean descricaoMudou = !txtDescricao.getText().trim().equals(descricaoAnterior);

            if (!quantidadeMudou && !disponibilidadeMudou && !nomeMudou && !descricaoMudou) {
                JOptionPane.showMessageDialog(this, "Nenhuma alteração foi feita.");
                return;
            }

            StringBuilder resumo = new StringBuilder();
            resumo.append("╔══════════════════════════════════╗\n");
            resumo.append("║     RESUMO DAS ALTERAÇÕES        ║\n");
            resumo.append("╠══════════════════════════════════╣\n");

            String[] statusNomes = {"DISPONÍVEL", "INDISPONÍVEL", "INATIVO"};

            if (disponibilidadeMudou) {
                resumo.append("║ STATUS: ").append(statusNomes[disponibilidadeAnterior])
                        .append(" → ").append(statusNomes[novaDisponibilidade]).append("\n");
            }

            if (quantidadeMudou) {
                resumo.append("║ QUANTIDADE: ").append(quantidadeAnterior)
                        .append(" → ").append(novaQuantidade).append("\n");

                int diferenca = novaQuantidade - quantidadeAnterior;
                String tipoAlteracao = diferenca > 0 ? "ADICIONADO" : "REMOVIDO";
                resumo.append("║ ALTERAÇÃO: ").append(Math.abs(diferenca))
                        .append(" unidades ").append(tipoAlteracao).append("\n");
            }

            if (nomeMudou) {
                resumo.append("║ NOME: '").append(nomeAnterior)
                        .append("' → '").append(txtItem.getText().trim()).append("'\n");
            }

            if (descricaoMudou) {
                resumo.append("║ DESCRIÇÃO: '").append(descricaoAnterior.length() > 30
                        ? descricaoAnterior.substring(0, 30) + "..." : descricaoAnterior)
                        .append("' → '").append(txtDescricao.getText().trim().length() > 30
                        ? txtDescricao.getText().trim().substring(0, 30) + "..." : txtDescricao.getText().trim())
                        .append("'\n");
            }

            resumo.append("╚══════════════════════════════════╝\n\n");
            resumo.append("Informe o motivo geral das alterações (opcional):");

            String motivo = JOptionPane.showInputDialog(this, resumo.toString(),
                    "Confirmar Alterações", JOptionPane.QUESTION_MESSAGE);

            if (motivo == null) {
                return;
            }

            if (motivo.trim().isEmpty()) {
                StringBuilder motivoPadrao = new StringBuilder();

                if (disponibilidadeMudou) {
                    motivoPadrao.append("Status: ").append(statusNomes[disponibilidadeAnterior])
                            .append(" → ").append(statusNomes[novaDisponibilidade]).append(". ");
                }

                if (quantidadeMudou) {
                    motivoPadrao.append("Quantidade: ").append(quantidadeAnterior)
                            .append(" → ").append(novaQuantidade).append(". ");
                }

                if (nomeMudou) {
                    motivoPadrao.append("Nome alterado. ");
                }

                if (descricaoMudou) {
                    motivoPadrao.append("Descrição alterada. ");
                }

                motivo = motivoPadrao.toString();
            }

            itemEstoque.setItem(txtItem.getText().trim());
            itemEstoque.setQuantidade(novaQuantidade);
            itemEstoque.setDescricao(txtDescricao.getText().trim());

            if (rbInativo.isSelected()) {
                itemEstoque.setInativo(1);
                itemEstoque.setIndisponivel(0);
            } else if (rbIndisponivel.isSelected()) {
                itemEstoque.setInativo(0);
                itemEstoque.setIndisponivel(1);
            } else {
                itemEstoque.setInativo(0);
                itemEstoque.setIndisponivel(0);
            }

            EstoqueDAO dao = new EstoqueDAO();

            try {

                dao.atualizarCompleto(itemEstoque, usuarioId, quantidadeAnterior,
                        disponibilidadeAnterior, nomeAnterior,
                        descricaoAnterior, motivo);

                JOptionPane.showMessageDialog(this,
                        "✅ Item atualizado com sucesso!\n"
                        + "Foram registradas auditorias para todas as alterações.");
                dispose();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,
                        "❌ Erro ao salvar alterações: " + e.getMessage());
                e.printStackTrace();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "❌ Erro inesperado: " + e.getMessage());
            e.printStackTrace();
        }

    }//GEN-LAST:event_btnSalvarActionPerformed

    private void rbDisponivelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbDisponivelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbDisponivelActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void rbIndisponivelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbIndisponivelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbIndisponivelActionPerformed

    private void rbInativoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbInativoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbInativoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.ButtonGroup grupoDisponibilidade;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JRadioButton rbDisponivel;
    private javax.swing.JRadioButton rbInativo;
    private javax.swing.JRadioButton rbIndisponivel;
    private javax.swing.JTextPane txtDescricao;
    private javax.swing.JTextPane txtEmprestados;
    private javax.swing.JTextPane txtItem;
    private javax.swing.JTextPane txtQuantidade;
    // End of variables declaration//GEN-END:variables
}
