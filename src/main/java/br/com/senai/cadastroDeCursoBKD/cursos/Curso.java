package br.com.senai.cadastroDeCursoBKD.cursos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name="cursos")
@Entity(name = "Curso")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 255)
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Periodo periodo;

    @Column(nullable = false)
    private boolean ativo;


    public Curso(DadosCadastroCurso dados) {
        this.nome = dados.nome();
        this.descricao = dados.descricao();
        this.periodo = dados.periodo();
        this.ativo = true;
    }


    public enum Periodo {
        MATUTINO, VESPERTINO, NOTURNO, INTEGRAL
    }


    public void atualizarCurso(DadosAtualizarCurso dados) {
        if(dados.nome() != null && !dados.nome().isBlank())
            this.nome = dados.nome();
        if(dados.descricao() != null && !dados.descricao().isBlank())
            this.descricao = dados.descricao();
        if(dados.periodo() != null)
            this.periodo = dados.periodo();
    }


    public void excluirCurso() {
        this.ativo = false;
    }
}