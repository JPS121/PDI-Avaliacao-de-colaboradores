package jsp.pdi.api.domain.pdi;

import jsp.pdi.api.domain.funcionario.Funcionario;

import java.time.LocalDate;

public record DadosConsultaPdi(
        int pdi_id,
        Funcionario funcionario,
        String avaliacao,
        LocalDate data
) {

    public DadosConsultaPdi(Pdi pdi) {
        this(
                pdi.getPdi_id(),
                pdi.getFuncionario(),
                pdi.getAvaliacao(),
                pdi.getData()
        );
    }
}
