package ESA24.repo;

import ESA24.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;


public interface PlayerRepository extends JpaRepository<Player, UUID> {

}
