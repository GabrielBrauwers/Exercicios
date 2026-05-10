package GUI;

import DAO.AuditoriaDAO;
import Classes.Auditoria;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;

public class AuditoriaGUI extends javax.swing.JFrame {

    private boolean ignorarEventos = false;
    private SimpleDateFormat formatoData = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private AuditoriaDAO dao = new AuditoriaDAO();
    private DefaultTableModel modelo = new DefaultTableModel(
        new String[]{"Item", "Usuario", "Data_Hora", "Evento", "Qantidade", "Motivo"}, 0
    ) {
        @Override
        public boolean isCellEditable(int row, int column) {
            System.out.println("um teste)");
            return false;
        }
    };

    public AuditoriaGUI() {
        initComponents();
        

        // Configura JFrame para tela cheia
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Painel principal com BorderLayout
        JPanel painelPrincipal = new JPanel(new BorderLayout());

        // Painel de filtros (combo boxes) na parte superior
        JPanel painelFiltros = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        painelFiltros.add(new JLabel("Item:"));
        painelFiltros.add(CBItem);
        painelFiltros.add(new JLabel("Usuário:"));
        painelFiltros.add(CBUsuario);
        painelFiltros.add(new JLabel("Data:"));
        painelFiltros.add(CBData);
        painelFiltros.add(new JLabel("Evento:"));
        painelFiltros.add(CBEvento);
        JButton sair = new JButton("Sair");
        sair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        painelFiltros.add(sair);

        // Adiciona filtros no topo
        painelPrincipal.add(painelFiltros, BorderLayout.NORTH);

        // Adiciona a tabela com scroll no centro
        painelPrincipal.add(new JScrollPane(jTable1), BorderLayout.CENTER);

        // Define o painel principal no JFrame
        this.setContentPane(painelPrincipal);

        carregarComboEventos();
        carregarComboItens();
        carregarComboUsuarios();
        carregarComboDatas();
        
        jTable1.setModel(modelo);
        carregarTabela();

        
        System.out.println("Modelo da tabela: " + jTable1.getModel().getClass().getName());
        
        this.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        CBItem = new javax.swing.JComboBox<>();
        CBData = new javax.swing.JComboBox<>();
        CBEvento = new javax.swing.JComboBox<>();
        CBUsuario = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Item", "Usuario", "Data_Hora", "Evento", "Qantidade", "Motivo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setText("Filtrar por");

        jLabel2.setText("Data");

        jLabel3.setText("Usuario");

        jLabel5.setText("Item");

        jLabel6.setText("Evento");

        CBItem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        CBItem.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CBItemItemStateChanged(evt);
            }
        });

        CBData.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        CBData.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CBDataItemStateChanged(evt);
            }
        });

        CBEvento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        CBEvento.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CBEventoItemStateChanged(evt);
            }
        });

        CBUsuario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        CBUsuario.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CBUsuarioItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(54, 54, 54)
                                .addComponent(jLabel3)
                                .addGap(59, 59, 59)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(43, 43, 43)
                                .addComponent(jLabel6))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(CBItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(CBUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(CBData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(CBEvento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(CBItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CBData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CBEvento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CBUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 13, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CBEventoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CBEventoItemStateChanged
        if (ignorarEventos) {
            return;
        }
        if (evt.getStateChange() != ItemEvent.SELECTED) {
            return;
        }

        String eventoSelecionado = (String) CBEvento.getSelectedItem();
        if (eventoSelecionado == null) {
            return;
        }

        resetarFiltros(CBEvento);

        try {
            if (eventoSelecionado.equals("Todos")) {
                carregarTabela();
            } else {
                atualizarTabela(dao.listarPorEvento(eventoSelecionado));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao filtrar por evento");
        }

    }//GEN-LAST:event_CBEventoItemStateChanged

    private void CBItemItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CBItemItemStateChanged
        if (ignorarEventos) {
            return;
        }
        if (evt.getStateChange() != ItemEvent.SELECTED) {
            return;
        }

        String itemSelecionado = (String) CBItem.getSelectedItem();
        if (itemSelecionado == null) {
            return;
        }

        resetarFiltros(CBItem);

        try {
            if (itemSelecionado.equals("Todos")) {
                carregarTabela();
            } else {
                atualizarTabela(dao.listarPorItem(itemSelecionado));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao filtrar por item");
        }

    }//GEN-LAST:event_CBItemItemStateChanged

    private void CBDataItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CBDataItemStateChanged
        if (ignorarEventos) {
            return;
        }
        if (evt.getStateChange() != ItemEvent.SELECTED) {
            return;
        }

        String dataSelecionada = (String) CBData.getSelectedItem();
        if (dataSelecionada == null) {
            return;
        }

        resetarFiltros(CBData);

        try {
            if (dataSelecionada.equals("Todos")) {
                carregarTabela();
            } else {
                atualizarTabela(dao.listarPorData(dataSelecionada));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao filtrar por data");
        }
    }//GEN-LAST:event_CBDataItemStateChanged

    private void CBUsuarioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CBUsuarioItemStateChanged
        if (ignorarEventos) {
            return;
        }
        if (evt.getStateChange() != ItemEvent.SELECTED) {
            return;
        }

        String usuarioSelecionado = (String) CBUsuario.getSelectedItem();
        if (usuarioSelecionado == null) {
            return;
        }

        resetarFiltros(CBUsuario);

        try {
            if (usuarioSelecionado.equals("Todos")) {
                carregarTabela();
            } else {
                atualizarTabela(dao.listarPorUsuario(usuarioSelecionado));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao filtrar por usuário");
        }

    }//GEN-LAST:event_CBUsuarioItemStateChanged
    private void carregarComboItens() {
        CBItem.removeAllItems();
        CBItem.addItem("Todos");

        ArrayList<String> itens = dao.listarNomesItens();
        for (String nome : itens) {
            CBItem.addItem(nome);
        }
    }

    private void resetarFiltros(JComboBox<String> exceto) {
        ignorarEventos = true;

        if (CBItem != exceto) {
            CBItem.setSelectedIndex(0);
        }
        if (CBUsuario != exceto) {
            CBUsuario.setSelectedIndex(0);
        }
        if (CBData != exceto) {
            CBData.setSelectedIndex(0);
        }
        if (CBEvento != exceto) {
            CBEvento.setSelectedIndex(0);
        }

        ignorarEventos = false;
    }

    private void carregarComboUsuarios() {
        CBUsuario.removeAllItems();
        CBUsuario.addItem("Todos");

        try {
            ArrayList<String> usuarios = dao.listarNomesUsuarios();
            for (String u : usuarios) {
                CBUsuario.addItem(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao carregar usuários: " + e.getMessage());
        }
    }

    private void carregarComboEventos() {
        CBEvento.removeAllItems();
        CBEvento.addItem("Todos");

        try {
            ArrayList<Auditoria> lista = dao.listar();
            for (Auditoria a : lista) {
                if (((DefaultComboBoxModel<String>) CBEvento.getModel()).getIndexOf(a.getEvento()) == -1) {
                    CBEvento.addItem(a.getEvento());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao carregar eventos: " + e.getMessage());
        }
    }

    private void carregarComboDatas() {
        CBData.removeAllItems();
        CBData.addItem("Todos");
        ArrayList<String> datas = dao.listarDatas();
        for (String d : datas) {
            CBData.addItem(d);
        }
    }

    private void atualizarTabela(ArrayList<Auditoria> lista) {
        modelo.setRowCount(0);

        try {
            for (Auditoria a : lista) {
                String nomeItem = dao.getNomeItem(a.getId_item());
                String nomeUsuario = dao.getNomeUsuario(a.getId_usuario());

                String dataHoraFormatada = a.getDataHora() != null
                        ? formatoData.format(a.getDataHora())
                        : "";

                modelo.addRow(new Object[]{
                    nomeItem,
                    nomeUsuario,
                    dataHoraFormatada,
                    a.getEvento(),
                    a.getQuantidade(),
                    a.getMotivo()
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void carregarTabela() {
        try {
            ArrayList<Auditoria> lista = dao.listar();
            atualizarTabela(lista);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CBData;
    private javax.swing.JComboBox<String> CBEvento;
    private javax.swing.JComboBox<String> CBItem;
    private javax.swing.JComboBox<String> CBUsuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private JComboBox<String> cbItem;
    private JComboBox<String> cbUsuario;
    private JComboBox<String> cbDataHora;
    private JComboBox<String> cbEvento;
    // End of variables declaration//GEN-END:variables
}
