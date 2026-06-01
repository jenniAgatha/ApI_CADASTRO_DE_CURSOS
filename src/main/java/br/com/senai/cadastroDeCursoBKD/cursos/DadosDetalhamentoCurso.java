package br.com.senai.cadastroDeCursoBKD.cursos;

public record DadosDetalhamentoCurso(
        Long id,
        String nome,
        String descricao,
        Curso.Periodo periodo,
        Boolean ativo
) {
    public DadosDetalhamentoCurso(Curso curso) {
        this(
                curso.getId(),
                curso.getNome(),
                curso.getDescricao(),
                curso.getPeriodo(),
                curso.isAtivo()
        );
    }
}