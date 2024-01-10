package jsp.pdi.api.domain.funcionario;

public record DadosConsultaFuncionario(
        int id,
        String nome,
        String email,
        String cargo,
        Boolean ativo

) {

    public DadosConsultaFuncionario(Funcionario funcionario){
        this(
                funcionario.getId(),
                funcionario.getNome(),
                funcionario.getEmail(),
                funcionario.getCargo(),
                funcionario.getAtivo()
        );
    }
}
