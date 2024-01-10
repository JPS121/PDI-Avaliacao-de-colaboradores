package jsp.pdi.api.domain.pdi;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PdiRepository extends JpaRepository<Pdi, Integer> {
    List<Pdi> findAllByFuncionarioId(int id);
}
