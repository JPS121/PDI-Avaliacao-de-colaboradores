package jsp.pdi.api.domain.funcionario;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Table(name = "funcionarios")
@Entity(name= "Funcionario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Funcionario {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String email;
    private String cargo;
    private Boolean ativo;

    public Funcionario(DadosCadastroFuncionario dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.cargo = dados.cargo();
        this.ativo = true;
    }

    public void atualizarDados(DadosAtualizacaoFuncionario dados) {
        if(dados.nome()!= null) {
            this.nome = dados.nome();
        }
        if(dados.email()!= null) {
            this.email = dados.email();
        }
        if(dados.cargo() != null) {
             this.cargo = dados.cargo();
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}
