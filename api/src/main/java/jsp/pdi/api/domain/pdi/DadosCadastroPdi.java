package jsp.pdi.api.domain.pdi;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


import java.time.LocalDate;

public record DadosCadastroPdi(

        @NotNull
        int pdi_id,
        @NotNull
        int funcionario_id,
        @NotBlank
        String avaliacao,
        @NotNull
        LocalDate data
) {
}
