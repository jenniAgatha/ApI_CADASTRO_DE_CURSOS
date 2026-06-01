package br.com.senai.cadastroDeCursoBKD.cursos;

import jakarta.validation.constraints.Size;

public record DadosAtualizarCurso(
        @Size(min = 3, max = 100, message = "Nome deve ter entre 3 e 100 caracteres")
        String nome,

        @Size(min = 10, max = 255, message = "Descrição deve ter entre 10 e 255 caracteres")
        String descricao,

        Curso.Periodo periodo
) { }