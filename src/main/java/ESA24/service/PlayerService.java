package ESA24.service;


import ESA24.aspect.Loggable;
import ESA24.model.Hero;
import ESA24.model.Player;
import ESA24.repo.HeroRepository;
import ESA24.repo.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private HeroRepository heroRepository;

    @Loggable
    public void create(Player player) {
        playerRepository.save(player);
    }

    public Player get(UUID id) {
        return playerRepository.findById(id).get();
    }

    public List<Player> getAll() {
        return playerRepository.findAll().stream().sorted(Comparator.comparing(Player::getId)).collect(Collectors.toList());
    }
    @Loggable
    public void update(UUID playerId, Player newPlayerData) {
        Player player = playerRepository.findById(playerId).get();
        player.setFirstName(newPlayerData.getFirstName());
        player.setLastName(newPlayerData.getLastName());
        player.setBirthdate(newPlayerData.getBirthdate());
        player.setQuantityGames(newPlayerData.getQuantityGames());
        playerRepository.save(player);
    }
    @Loggable
    public void delete(UUID playerId) {
        heroRepository.deleteAllByPlayerId(playerId);
        playerRepository.deleteById(playerId);
    }

    public List<Hero> getHeroes(UUID id) {
        Player player = get(id);
        return player.getHeroes().stream().sorted(Comparator.comparing(Hero::getNameHero)).collect(Collectors.toList());
    }
}

