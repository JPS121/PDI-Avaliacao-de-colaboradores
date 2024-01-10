package jsp.pdi.api.domain.funcionario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoFuncionario(
        @NotNull
        int id,
        String nome,
        @Email
        String email,
        String cargo
) {
}
