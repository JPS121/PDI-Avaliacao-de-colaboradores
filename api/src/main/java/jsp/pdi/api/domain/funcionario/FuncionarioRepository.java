package jsp.pdi.api.domain.funcionario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {
    List<Funcionario> findAllById(int id);

    List<Funcionario> findAllByIdAndAtivoTrue(int id);

    Page<Funcionario> findAllByAtivoTrue(Pageable paginacao);
}
