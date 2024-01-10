package jsp.pdi.api.domain.pdi;

import jakarta.persistence.*;

import jsp.pdi.api.domain.funcionario.Funcionario;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Table(name = "pdi")
@Entity(name= "Pdi")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "pdiId")
public class Pdi {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pdi_id;
    @ManyToOne
    @JoinColumn(insertable = false, updatable=false)
    private Funcionario funcionario;
    private int funcionario_id;
    private String avaliacao;
    private LocalDate  data;

    public Pdi(DadosCadastroPdi dados) {
        this.funcionario_id = dados.funcionario_id();
        this.avaliacao = dados.avaliacao();
        this.data = LocalDate.now();
    }

    public void atualizarDados(DadosAtualizcaoPdi dados) {
        if(dados.avaliacao() != null) {
            this.avaliacao = dados.avaliacao();
        }
    }
}
