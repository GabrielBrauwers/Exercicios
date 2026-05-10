package GUI;

import Classes.Estoque;
import DAO.EstoqueDAO;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;

public class AddEstoque extends javax.swing.JFrame {

    // Tela responsavel por cadastrar novos itens no estoque.

    // ID do usuario atual usado para registrar a auditoria do cadastro.
    private int usuarioId;

    // Construtor principal chamado pela tela de listagem.
    public AddEstoque(int usuarioId) {
        initComponents();
        this.usuarioId = usuarioId;
        configurarJanela();
    }

    private void configurarJanela() {
        // Configura a janela para abrir centralizada, no topo e com foco visual de popup.
        setTitle("Adicionar Item ao Estoque");
        setAlwaysOnTop(true);
        setResizable(false);
        setAutoRequestFocus(true);
        setFocusableWindowState(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Deixa o foco preparado para o campo principal quando a janela abrir.
        txtItem.requestFocusInWindow();

        // Agrupa os radios para permitir apenas uma opcao de disponibilidade por vez.
        ButtonGroup grupoDisponibilidade = new ButtonGroup();
        grupoDisponibilidade.add(rbDisponivel);
        grupoDisponibilidade.add(rbIndisponivel);
        grupoDisponibilidade.add(rbInativo);

        // Define o status disponivel como padrao ao abrir a tela.
        rbDisponivel.setSelected(true);
    }

    // Construtor padrao usado quando a tela e aberta sem informar usuario.
    public AddEstoque() {
        this(1);
    }

    // Bloco gerado pelo editor visual do NetBeans com os componentes da interface.
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        rbIndisponivel = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtQuantidade = new javax.swing.JTextPane();
        rbDisponivel = new javax.swing.JRadioButton();
        rbInativo = new javax.swing.JRadioButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtItem = new javax.swing.JTextPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtDescricao = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setType(java.awt.Window.Type.POPUP);

        jLabel1.setText("Item");

        jLabel2.setText("quantidade");

        jLabel3.setText("descricao");

        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Fechar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        jLabel5.setText("disponibilidade");

        rbIndisponivel.setText("Indisponível");
        rbIndisponivel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbIndisponivelActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(txtQuantidade);

        rbDisponivel.setText("Disponível");
        rbDisponivel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbDisponivelActionPerformed(evt);
            }
        });

        rbInativo.setText("Inativo");
        rbInativo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbInativoActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportView(txtItem);

        jScrollPane3.setViewportView(txtDescricao);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(63, 63, 63)
                                .addComponent(jLabel1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(btnSalvar)
                                .addGap(239, 239, 239)
                                .addComponent(btnCancelar))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(36, 36, 36)
                                .addComponent(rbDisponivel)
                                .addGap(37, 37, 37)
                                .addComponent(rbIndisponivel)
                                .addGap(37, 37, 37)
                                .addComponent(rbInativo)))))
                .addContainerGap(22, Short.MAX_VALUE))
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
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Evento do botao Salvar responsavel por validar e cadastrar o item.
    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        try {
            // Le o nome digitado e valida se o campo foi preenchido.
            String nomeItem = txtItem.getText().trim();
            if (nomeItem.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Por favor, informe o nome do item.",
                        "Campo obrigatorio",
                        JOptionPane.WARNING_MESSAGE);
                txtItem.requestFocus();
                return;
            }

            // Converte a quantidade digitada para numero inteiro.
            int quantidade;
            try {
                quantidade = Integer.parseInt(txtQuantidade.getText().trim());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this,
                        "Por favor, informe um valor numerico valido para quantidade.",
                        "Erro de formatacao",
                        JOptionPane.WARNING_MESSAGE);
                txtQuantidade.requestFocus();
                return;
            }

            // Impede cadastrar itens com quantidade zero ou negativa.
            if (quantidade <= 0) {
                JOptionPane.showMessageDialog(this,
                        "A quantidade deve ser maior que zero.",
                        "Valor invalido",
                        JOptionPane.WARNING_MESSAGE);
                txtQuantidade.requestFocus();
                return;
            }

            // Verifica se ja existe um item com o mesmo nome no estoque.
            EstoqueDAO dao = new EstoqueDAO();
            if (dao.existeItemComMesmoNome(nomeItem)) {
                JOptionPane.showMessageDialog(this,
                        "Ja existe um item com esse nome no estoque.",
                        "Item duplicado",
                        JOptionPane.WARNING_MESSAGE);
                txtItem.requestFocus();
                return;
            }

            // Monta o objeto que sera enviado para gravacao no banco.
            Estoque estoque = new Estoque();
            estoque.setItem(nomeItem);
            estoque.setQuantidade(quantidade);
            estoque.setDescricao(txtDescricao.getText().trim());
            estoque.setEmprestados(0);

            // Define o status de disponibilidade do novo item.
            if (rbDisponivel.isSelected()) {
                estoque.setIndisponivel(0);
                estoque.setInativo(0);
            } else if (rbIndisponivel.isSelected()) {
                estoque.setIndisponivel(1);
                estoque.setInativo(0);
            } else if (rbInativo.isSelected()) {
                estoque.setIndisponivel(0);
                estoque.setInativo(1);
            } else {
                JOptionPane.showMessageDialog(this,
                        "Por favor, selecione uma opcao de disponibilidade.",
                        "Campo obrigatorio",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Pede um motivo para registrar a criacao na auditoria.
            String motivo = JOptionPane.showInputDialog(this,
                    "Informe o motivo para adicionar este item:",
                    "Motivo da adicao",
                    JOptionPane.QUESTION_MESSAGE);

            if (motivo == null) {
                return;
            }

            if (motivo.trim().isEmpty()) {
                motivo = "Novo item criado";
            }

            // Salva o item no banco e registra a auditoria.
            boolean sucesso = dao.inserirEAuditar(estoque, usuarioId, motivo);

            if (sucesso) {
                JOptionPane.showMessageDialog(this,
                        "Item adicionado com sucesso.",
                        "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE);
                limparCampos();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Erro ao inserir item no banco de dados.",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Validacao", JOptionPane.WARNING_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Erro ao salvar item: " + e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void limparCampos() {
        // Limpa o formulario para permitir um novo cadastro.
        txtItem.setText("");
        txtQuantidade.setText("");
        txtDescricao.setText("");
        rbDisponivel.setSelected(true);
        rbIndisponivel.setSelected(false);
        rbInativo.setSelected(false);
        txtItem.requestFocus();
    }

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // Fecha a janela sem salvar o cadastro.
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void rbIndisponivelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbIndisponivelActionPerformed
        // Evento reservado para futuras regras da opcao indisponivel.
    }//GEN-LAST:event_rbIndisponivelActionPerformed

    private void rbDisponivelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbDisponivelActionPerformed
        // Evento reservado para futuras regras da opcao disponivel.
    }//GEN-LAST:event_rbDisponivelActionPerformed

    private void rbInativoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbInativoActionPerformed
        // Evento reservado para futuras regras da opcao inativo.
    }//GEN-LAST:event_rbInativoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JRadioButton rbDisponivel;
    private javax.swing.JRadioButton rbInativo;
    private javax.swing.JRadioButton rbIndisponivel;
    private javax.swing.JTextPane txtDescricao;
    private javax.swing.JTextPane txtItem;
    private javax.swing.JTextPane txtQuantidade;
    // End of variables declaration//GEN-END:variables
}


