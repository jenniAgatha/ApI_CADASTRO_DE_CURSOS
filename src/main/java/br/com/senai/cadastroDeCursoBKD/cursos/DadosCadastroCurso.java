package br.com.senai.cadastroDeCursoBKD.cursos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DadosCadastroCurso(
        @NotBlank(message = "Nome é obrigatório")
        @Size(min = 3, max = 100, message = "Nome deve ter entre 3 e 100 caracteres")
        String nome,

        @NotBlank(message = "Descrição é obrigatória")
        @Size(min = 10, max = 255, message = "Descrição deve ter entre 10 e 255 caracteres")
        String descricao,

        @NotNull(message = "Período é obrigatório")
        Curso.Periodo periodo
) { }
 