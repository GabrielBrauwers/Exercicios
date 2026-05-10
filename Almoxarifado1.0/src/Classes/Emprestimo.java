package Classes;

import java.time.LocalDateTime;

/**
 *
 * @author heitor
 */

public class Emprestimo {
    int ID_emprestimo;
    int ID_usuario;
    String nome_aluno;
    String nome_professor;
    String observacao;
    LocalDateTime data_hora_retirada;
    LocalDateTime data_hora_devolucao;

    public Emprestimo() {
    }

    public Emprestimo(int ID_emprestimo, int ID_usuario, String nome_aluno, String nome_professor, String observacao, LocalDateTime data_hora_retirada, LocalDateTime data_hora_devolucao) {
        this.ID_emprestimo = ID_emprestimo;
        this.ID_usuario = ID_usuario;
        this.nome_aluno = nome_aluno;
        this.nome_professor = nome_professor;
        this.observacao = observacao;
        this.data_hora_retirada = data_hora_retirada;
        this.data_hora_devolucao = data_hora_devolucao;
    }

    public int getID_emprestimo() {
        return ID_emprestimo;
    }

    public void setID_emprestimo(int ID_emprestimo) {
        this.ID_emprestimo = ID_emprestimo;
    }

    public int getID_usuario() {
        return ID_usuario;
    }

    public void setID_usuario(int ID_usuario) {
        this.ID_usuario = ID_usuario;
    }

    public String getNome_aluno() {
        return nome_aluno;
    }

    public void setNome_aluno(String nome_aluno) {
        this.nome_aluno = nome_aluno;
    }

    public String getNome_professor() {
        return nome_professor;
    }

    public void setNome_professor(String nome_professor) {
        this.nome_professor = nome_professor;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public LocalDateTime getData_hora_retirada() {
        return data_hora_retirada;
    }

    public void setData_hora_retirada(LocalDateTime data_hora_retirada) {
        this.data_hora_retirada = data_hora_retirada;
    }

    public LocalDateTime getData_hora_devolucao() {
        return data_hora_devolucao;
    }

    public void setData_hora_devolucao(LocalDateTime data_hora_devolucao) {
        this.data_hora_devolucao = data_hora_devolucao;
    }
    
}
