package jsp.pdi.api.domain.pdi;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizcaoPdi(
        @NotNull
        int pdi_id,
        String avaliacao
) {

}
