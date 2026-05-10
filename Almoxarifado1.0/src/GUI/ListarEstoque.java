package GUI;

import Classes.Estoque;
import DAO.EstoqueDAO;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class ListarEstoque extends javax.swing.JFrame {

    // Tela principal de gestao e consulta dos itens do estoque.

    // Modelo da tabela exibida na tela.
    private DefaultTableModel modeloTabela;
    // Permite ordenar e filtrar os dados mostrados na tabela.
    private TableRowSorter<DefaultTableModel> rowSorter;
    // ID do usuario atual usado ao abrir outras telas do modulo.
    private int usuarioId;
    // Lista completa do estoque carregada do banco.
    private ArrayList<Estoque> listaCompleta;
    // Estados dos filtros de exibicao.
    private boolean filtroDisponiveis = true;
    private boolean filtroIndisponiveis = false;
    private boolean filtroInativos = false;

    // Construtor que prepara a interface, os filtros e o carregamento inicial.
    public ListarEstoque() {
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Gestao de Estoque");
        setAlwaysOnTop(true);
        setResizable(false);

        this.usuarioId = 1;

        // Configura os filtros iniciais e carrega os dados da tela.
        configurarFiltros();
        carregarEstoqueCompleto();
        aplicarFiltros();
    }

    private void configurarFiltros() {
        // Define quais filtros comecam marcados ao abrir a janela.
        btnDisponiveis.setSelected(true);
        btnIndisponiveis.setSelected(false);
        btnInativos.setSelected(false);

        filtroDisponiveis = true;
        filtroIndisponiveis = false;
        filtroInativos = false;
    }

    private void carregarEstoqueCompleto() {
        try {
            // Busca todos os itens do estoque no banco.
            EstoqueDAO dao = new EstoqueDAO();
            listaCompleta = dao.Listar();

            // Monta a tabela sem a coluna de ID, exibindo apenas os dados uteis ao usuario.
            String[] colunas = {"Item", "Quantidade", "Descricao", "Emprestados", "Status"};

            modeloTabela = new DefaultTableModel(colunas, 0) {
                @Override
                public Class<?> getColumnClass(int columnIndex) {
                    switch (columnIndex) {
                        case 1:
                        case 3:
                            return Integer.class;
                        case 0:
                        case 2:
                        case 4:
                            return String.class;
                        default:
                            return Object.class;
                    }
                }

                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            tabelaEstoque.setModel(modeloTabela);

            // Ajusta as larguras das colunas visiveis.
            if (tabelaEstoque.getColumnCount() > 0) {
                tabelaEstoque.getColumnModel().getColumn(0).setPreferredWidth(200);
                tabelaEstoque.getColumnModel().getColumn(1).setPreferredWidth(10);
                tabelaEstoque.getColumnModel().getColumn(2).setPreferredWidth(200);
                tabelaEstoque.getColumnModel().getColumn(3).setPreferredWidth(10);
                tabelaEstoque.getColumnModel().getColumn(4).setPreferredWidth(10);
            }

            // Ativa o campo de pesquisa dinamica.
            configurarProcura();

            // Mantem todas as colunas alinhadas a esquerda.
            javax.swing.table.DefaultTableCellRenderer alinhamentoEsquerda = new javax.swing.table.DefaultTableCellRenderer();
            alinhamentoEsquerda.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

            for (int i = 0; i < tabelaEstoque.getColumnCount(); i++) {
                tabelaEstoque.getColumnModel().getColumn(i).setCellRenderer(alinhamentoEsquerda);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Erro ao carregar estoque: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void aplicarFiltros() {
        // Limpa a tabela antes de preencher novamente com os itens filtrados.
        modeloTabela.setRowCount(0);

        for (Estoque e : listaCompleta) {
            // Descobre o status textual do item com base nos campos do objeto.
            String status;
            if (e.getInativo() == 1) {
                status = "INATIVO";
            } else if (e.getIndisponivel() == 1) {
                status = "INDISPONIVEL";
            } else {
                status = "DISPONIVEL";
            }

            boolean deveMostrar = false;

            // Verifica se o item deve aparecer conforme os filtros marcados.
            if (filtroDisponiveis && status.equals("DISPONIVEL")) {
                deveMostrar = true;
            }
            if (filtroIndisponiveis && status.equals("INDISPONIVEL")) {
                deveMostrar = true;
            }
            if (filtroInativos && status.equals("INATIVO")) {
                deveMostrar = true;
            }

            if (deveMostrar) {
                // Adiciona a linha na tabela sem exibir o ID.
                Object[] linha = {
                    e.getItem(),
                    e.getQuantidade(),
                    e.getDescricao(),
                    e.getEmprestados(),
                    status
                };
                modeloTabela.addRow(linha);
            }
        }

        // Se nenhum filtro estiver marcado, mostra todos os itens.
        if (!filtroDisponiveis && !filtroIndisponiveis && !filtroInativos) {
            carregarTodosItens();
        }

        // Reaplica a pesquisa digitada apos atualizar a tabela.
        if (rowSorter != null && txtProcurar.getText().trim().length() > 0) {
            filtrarTabela();
        }
    }

    private void carregarTodosItens() {
        // Recarrega a tabela inteira sem aplicar filtros de status.
        modeloTabela.setRowCount(0);
        for (Estoque e : listaCompleta) {
            String status;
            if (e.getInativo() == 1) {
                status = "INATIVO";
            } else if (e.getIndisponivel() == 1) {
                status = "INDISPONIVEL";
            } else {
                status = "DISPONIVEL";
            }

            Object[] linha = {
                e.getItem(),
                e.getQuantidade(),
                e.getDescricao(),
                e.getEmprestados(),
                status
            };
            modeloTabela.addRow(linha);
        }
    }

    private void configurarProcura() {
        // Liga o campo de pesquisa ao modelo da tabela.
        rowSorter = new TableRowSorter<>(modeloTabela);
        tabelaEstoque.setRowSorter(rowSorter);

        txtProcurar.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                filtrarTabela();
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                filtrarTabela();
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                filtrarTabela();
            }
        });
    }

    private void filtrarTabela() {
        // Filtra os resultados pelo texto digitado em Item e Descricao.
        String texto = txtProcurar.getText().trim();

        if (texto.length() == 0) {
            rowSorter.setRowFilter(null);
        } else {
            try {
                RowFilter<DefaultTableModel, Object> rf = RowFilter.regexFilter("(?i)" + texto, 0, 2);
                rowSorter.setRowFilter(rf);
            } catch (java.util.regex.PatternSyntaxException e) {
                return;
            }
        }
    }

    private void abrirAlterar() {
        // Abre a tela de alteracao para o item selecionado na tabela.
        int linhaSelecionada = tabelaEstoque.getSelectedRow();

        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this,
                    "Por favor, selecione um item da tabela para alterar.");
            return;
        }

        int linhaReal = linhaSelecionada;
        if (rowSorter != null) {
            linhaReal = tabelaEstoque.convertRowIndexToModel(linhaSelecionada);
        }

        Estoque itemSelecionado = null;
        int itensVisiveis = -1;

        for (Estoque e : listaCompleta) {
            String status;
            if (e.getInativo() == 1) {
                status = "INATIVO";
            } else if (e.getIndisponivel() == 1) {
                status = "INDISPONIVEL";
            } else {
                status = "DISPONIVEL";
            }

            boolean deveMostrar = false;
            if (filtroDisponiveis && status.equals("DISPONIVEL")) {
                deveMostrar = true;
            }
            if (filtroIndisponiveis && status.equals("INDISPONIVEL")) {
                deveMostrar = true;
            }
            if (filtroInativos && status.equals("INATIVO")) {
                deveMostrar = true;
            }
            if (!filtroDisponiveis && !filtroIndisponiveis && !filtroInativos) {
                deveMostrar = true;
            }

            if (deveMostrar) {
                itensVisiveis++;
                if (itensVisiveis == linhaReal) {
                    itemSelecionado = e;
                    break;
                }
            }
        }

        if (itemSelecionado == null) {
            JOptionPane.showMessageDialog(this,
                    "Nao foi possivel localizar o item selecionado.");
            return;
        }

        AlterarEstoque alterarJanela = new AlterarEstoque(itemSelecionado.getID_item(), usuarioId);
        alterarJanela.setLocationRelativeTo(this);
        alterarJanela.setVisible(true);

        // Quando a janela de alteracao fechar, atualiza os dados da tabela.
        alterarJanela.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                recarregarDados();
            }
        });
    }

    private void recarregarDados() {
        // Busca novamente os dados do banco e reaplica os filtros atuais.
        EstoqueDAO dao = new EstoqueDAO();
        listaCompleta = dao.Listar();
        aplicarFiltros();
    }

    // Bloco gerado pelo editor visual do NetBeans com os componentes da interface.
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        btnDisp = new javax.swing.ButtonGroup();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaEstoque = new javax.swing.JTable();
        btnFechar = new javax.swing.JButton();
        Adicionar = new javax.swing.JButton();
        txtProcurar = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnDisponiveis = new javax.swing.JRadioButton();
        btnInativos = new javax.swing.JRadioButton();
        btnIndisponiveis = new javax.swing.JRadioButton();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tabelaEstoque.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tabelaEstoque.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaEstoqueMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaEstoque);

        btnFechar.setText("Fechar");
        btnFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFecharActionPerformed(evt);
            }
        });

        Adicionar.setText("Adicionar");
        Adicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AdicionarActionPerformed(evt);
            }
        });

        txtProcurar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProcurarActionPerformed(evt);
            }
        });

        jLabel1.setText("Procurar");

        jLabel2.setText("DoubleClick = Alterar");

        btnDisponiveis.setText("Disponiveis");
        btnDisponiveis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDisponiveisActionPerformed(evt);
            }
        });

        btnInativos.setText("Inativos");
        btnInativos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInativosActionPerformed(evt);
            }
        });

        btnIndisponiveis.setText("Indisponiveis");
        btnIndisponiveis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIndisponiveisActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1025, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Adicionar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtProcurar, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(btnDisponiveis)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnIndisponiveis)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnInativos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnFechar)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 465, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFechar)
                    .addComponent(Adicionar)
                    .addComponent(txtProcurar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(btnDisponiveis)
                    .addComponent(btnIndisponiveis)
                    .addComponent(btnInativos))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Evento de duplo clique que abre a tela de alteracao do item selecionado.
    private void tabelaEstoqueMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaEstoqueMouseClicked
        if (evt.getClickCount() == 2) {
            abrirAlterar();
        }
    }//GEN-LAST:event_tabelaEstoqueMouseClicked

    // Fecha a tela de gestao do estoque.
    private void btnFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharActionPerformed
        dispose();
    }//GEN-LAST:event_btnFecharActionPerformed

    private void AdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AdicionarActionPerformed
        // Abre a janela de cadastro como popup sobre a tela de gestao.
        AddEstoque addJanela = new AddEstoque(usuarioId);
        addJanela.setLocationRelativeTo(this);
        addJanela.setAlwaysOnTop(true);
        addJanela.setVisible(true);
        addJanela.toFront();
        addJanela.requestFocus();
        this.setEnabled(false);

        addJanela.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                // Reabilita a tela principal quando o popup estiver fechando.
                ListarEstoque.this.setEnabled(true);
                ListarEstoque.this.toFront();
                ListarEstoque.this.requestFocus();
            }

            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                // Reabilita a tela principal, traz de volta o foco e recarrega os dados.
                ListarEstoque.this.setEnabled(true);
                ListarEstoque.this.toFront();
                ListarEstoque.this.requestFocus();
                recarregarDados();
            }
        });
    }//GEN-LAST:event_AdicionarActionPerformed

    // Executa a pesquisa quando o usuario confirma o texto digitado.
    private void txtProcurarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProcurarActionPerformed
        filtrarTabela();
    }//GEN-LAST:event_txtProcurarActionPerformed

    // Atualiza a lista exibida quando o filtro de disponiveis e alterado.
    private void btnDisponiveisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDisponiveisActionPerformed
        filtroDisponiveis = btnDisponiveis.isSelected();
        aplicarFiltros();
    }//GEN-LAST:event_btnDisponiveisActionPerformed

    // Atualiza a lista exibida quando o filtro de indisponiveis e alterado.
    private void btnIndisponiveisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIndisponiveisActionPerformed
        filtroIndisponiveis = btnIndisponiveis.isSelected();
        aplicarFiltros();
    }//GEN-LAST:event_btnIndisponiveisActionPerformed

    // Atualiza a lista exibida quando o filtro de inativos e alterado.
    private void btnInativosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInativosActionPerformed
        filtroInativos = btnInativos.isSelected();
        aplicarFiltros();
    }//GEN-LAST:event_btnInativosActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Adicionar;
    private javax.swing.ButtonGroup btnDisp;
    private javax.swing.JRadioButton btnDisponiveis;
    private javax.swing.JButton btnFechar;
    private javax.swing.JRadioButton btnInativos;
    private javax.swing.JRadioButton btnIndisponiveis;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelaEstoque;
    private javax.swing.JTextField txtProcurar;
    // End of variables declaration//GEN-END:variables
}


