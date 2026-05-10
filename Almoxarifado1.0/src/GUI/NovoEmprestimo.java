package GUI;

import Classes.Emprestimo;
import Classes.Estoque;
import Classes.EstoqueItens;
import DAO.EmprestimoDAO;
import DAO.EstoqueDAO;
import DAO.ErpDAOException;
import DAO.ItensDAO;
import DAO.JoinDAO;
import static Util.Ldap.listAlunos;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import static Util.Ldap.listProfessores;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class NovoEmprestimo extends javax.swing.JFrame {
    ArrayList<EstoqueItens> itens = new ArrayList();
    ArrayList professores = new ArrayList();
    ArrayList alunos = new ArrayList();
    EmprestimoDAO EMDAO = new EmprestimoDAO();
    JoinDAO JDAO = new JoinDAO();
    ItensDAO IDAO = new ItensDAO();
    int ID_usuario_logado;
    int ID_emprestimo_atual = -1;
    Boolean emprestimo_foi_criado = false;

    public NovoEmprestimo(int id) {
        initComponents();
        this.ID_usuario_logado = id;
        ADICIONAR.setEnabled(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        COMBOALUNO = new javax.swing.JComboBox<>();
        COMBOPROF = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TABELA = new javax.swing.JTable();
        SALVAR = new javax.swing.JButton();
        CANCELAR = new javax.swing.JButton();
        OBSERVACAO = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        DATETIME = new javax.swing.JTextField();
        BOTAODATETIME = new javax.swing.JButton();
        ADICIONAR = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Novo empréstimo");
        setAlwaysOnTop(true);
        setType(java.awt.Window.Type.POPUP);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); 
        jLabel1.setText("Novo Empréstimo");

        jLabel2.setText("Aluno");

        jLabel3.setText("Professor");

        TABELA.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Id", "Item", "Descrição", "Quantidade", "id_estoque"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TABELA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TABELAMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TABELA);
        if (TABELA.getColumnModel().getColumnCount() > 0) {
            TABELA.getColumnModel().getColumn(0).setMinWidth(0);
            TABELA.getColumnModel().getColumn(0).setPreferredWidth(0);
            TABELA.getColumnModel().getColumn(0).setMaxWidth(0);
            TABELA.getColumnModel().getColumn(3).setMinWidth(80);
            TABELA.getColumnModel().getColumn(3).setPreferredWidth(80);
            TABELA.getColumnModel().getColumn(3).setMaxWidth(80);
            TABELA.getColumnModel().getColumn(4).setMinWidth(0);
            TABELA.getColumnModel().getColumn(4).setPreferredWidth(0);
            TABELA.getColumnModel().getColumn(4).setMaxWidth(0);
        }

        SALVAR.setText("Salvar");
        SALVAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SALVARActionPerformed(evt);
            }
        });

        CANCELAR.setText("Cancelar");
        CANCELAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CANCELARActionPerformed(evt);
            }
        });

        jLabel4.setText("Observação");

        jLabel5.setText("Retirada");

        BOTAODATETIME.setText("Pegar data e hora atual");
        BOTAODATETIME.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BOTAODATETIMEActionPerformed(evt);
            }
        });

        ADICIONAR.setText("Adicionar item e criar empréstimo");
        ADICIONAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ADICIONARActionPerformed(evt);
            }
        });

        jLabel6.setText("Lista de itens no empréstimo atual");

        jLabel9.setText("(Clique direito para remover)");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(362, 362, 362)
                                .addComponent(jLabel5)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(DATETIME, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(BOTAODATETIME))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(36, 36, 36)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(ADICIONAR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel9))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(454, 454, 454)
                                .addComponent(SALVAR)
                                .addGap(43, 43, 43)
                                .addComponent(CANCELAR))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(341, 341, 341)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(18, 18, 18)
                                        .addComponent(OBSERVACAO, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(18, 18, 18)
                                        .addComponent(COMBOALUNO, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(18, 18, 18)
                                        .addComponent(COMBOPROF, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(435, 435, 435)
                                .addComponent(jLabel1)))
                        .addGap(0, 319, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(COMBOALUNO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(COMBOPROF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(OBSERVACAO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(DATETIME, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BOTAODATETIME))
                .addGap(18, 18, 18)
                .addComponent(ADICIONAR)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SALVAR)
                    .addComponent(CANCELAR))
                .addGap(40, 40, 40))
        );

        pack();
    }// </editor-fold>

    private void formWindowOpened(java.awt.event.WindowEvent evt) {
        carrega_combo_aluno();
        carrega_combo_prof();
        carrega_tabela();
        ADICIONAR.setEnabled(true);
    }

    private void CANCELARActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
    }

    private void SALVARActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
    }

    private void BOTAODATETIMEActionPerformed(java.awt.event.ActionEvent evt) {
        LocalDateTime DateTimeAtual = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        DATETIME.setText(DateTimeAtual.format(formatter));
    }

    private void ADICIONARActionPerformed(java.awt.event.ActionEvent evt) {
        if (DATETIME.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha a data e hora de retirada.", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String aluno = (String) COMBOALUNO.getSelectedItem();
        String prof = (String) COMBOPROF.getSelectedItem();
        
        if ((aluno == null || aluno.equals("nenhum")) && (prof == null || prof.equals("nenhum"))) {
            JOptionPane.showMessageDialog(this, "Selecione pelo menos um Aluno ou Professor.", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (!emprestimo_foi_criado) {
            Emprestimo e = new Emprestimo();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            e.setID_usuario(ID_usuario_logado);
            e.setNome_aluno(aluno);
            e.setNome_professor(prof);
            e.setObservacao(OBSERVACAO.getText());
            try {
                e.setData_hora_retirada(LocalDateTime.parse(DATETIME.getText(), formatter));
                ID_emprestimo_atual = EMDAO.Inserir(e);

                if (ID_emprestimo_atual > 0) {
                    emprestimo_foi_criado = true;
                } else {
                    JOptionPane.showMessageDialog(this, "Erro ao criar empréstimo no banco.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(this, "Data inválida.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            } catch (ErpDAOException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao salvar: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        JFrame telaAdd = new AdicionarItem(ID_emprestimo_atual);
        telaAdd.setLocationRelativeTo(this);
        telaAdd.setVisible(true);
    }

    private void TABELAMouseClicked(java.awt.event.MouseEvent evt) {
        if (javax.swing.SwingUtilities.isRightMouseButton(evt)) {
            int row = TABELA.rowAtPoint(evt.getPoint());
            if (row >= 0) {
                TABELA.setRowSelectionInterval(row, row);
                int resp = JOptionPane.showConfirmDialog(this, "Remover item?", "Confirmação", JOptionPane.YES_NO_OPTION);
                if (resp == JOptionPane.YES_OPTION) {
                    int idItens = Integer.parseInt((String) TABELA.getValueAt(row, 0));
                    int idEstoque = Integer.parseInt((String) TABELA.getValueAt(row, 4));
                    int qtd = Integer.parseInt((String) TABELA.getValueAt(row, 3));
                    
                    EstoqueDAO EDAO = new EstoqueDAO();
                    Estoque est = EDAO.procurar(idEstoque);
                    EDAO.AtualizarQuantidade(idEstoque, est.getQuantidade() + qtd);
                    IDAO.excluirID(idItens);
                    carrega_tabela();
                }
            }
        }
    }

    private void formWindowActivated(java.awt.event.WindowEvent evt) {
        carrega_tabela();
        ADICIONAR.setEnabled(true);
    }

    public void carrega_combo_aluno() {
        COMBOALUNO.removeAllItems();
        try {
            alunos = listAlunos();
            COMBOALUNO.addItem("nenhum");
            for (Object a : alunos) COMBOALUNO.addItem((String) a);
        } catch (Exception ex) {}
    }

    public void carrega_combo_prof() {
        COMBOPROF.removeAllItems();
        try {
            professores = listProfessores();
            COMBOPROF.addItem("nenhum");
            for (Object p : professores) COMBOPROF.addItem((String) p);
        } catch (Exception ex) {}
    }

    public void carrega_tabela() {
        if (ID_emprestimo_atual == -1) return;
        DefaultTableModel table = (DefaultTableModel) TABELA.getModel();
        table.setRowCount(0);
        try {
            itens = JDAO.ListarEstoqueItens(ID_emprestimo_atual);
            for (EstoqueItens i : itens) {
                table.addRow(new String[]{String.valueOf(i.getID_itens()), i.getItem(), i.getDescricao(), String.valueOf(i.getQtd()), String.valueOf(i.getID_item())});
            }
        } catch (Exception ex) {}
    }

    // Variables declaration
    private javax.swing.JButton ADICIONAR;
    private javax.swing.JButton BOTAODATETIME;
    private javax.swing.JButton CANCELAR;
    private javax.swing.JComboBox<String> COMBOALUNO;
    private javax.swing.JComboBox<String> COMBOPROF;
    private javax.swing.JTextField DATETIME;
    private javax.swing.JTextField OBSERVACAO;
    private javax.swing.JButton SALVAR;
    private javax.swing.JTable TABELA;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
}
