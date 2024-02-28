package ESA24.service;

import ESA24.aspect.Loggable;
import ESA24.model.Hero;
import ESA24.model.Player;
import ESA24.repo.HeroRepository;
import ESA24.repo.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class HeroService {

    @Autowired
    private HeroRepository heroRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Loggable
    public void create(Hero hero, UUID playerId) {
        Player player = playerRepository.findById(playerId).get();
        hero.setPlayer(player);
        heroRepository.save(hero);
    }

    public Hero get(UUID id) {
        return heroRepository.findById(id).get();
    }

    public List<Hero> getAll() {
        return heroRepository.findAll().stream().sorted(Comparator.comparing(Hero::getNameHero)).collect(Collectors.toList());
    }
    @Loggable
    public void update(UUID heroId, Hero newHeroData, UUID playerId) {
        Hero hero = heroRepository.findById(heroId).get();
        hero.setNameHero(newHeroData.getNameHero());
        hero.setLevel(newHeroData.getLevel());
        hero.setClassHero(newHeroData.getClassHero());
        hero.setSpellCells(newHeroData.getSpellCells());
        hero.setPlayer(newHeroData.getPlayer());
        Player player = playerRepository.findById(playerId).get();
        hero.setPlayer(player);
        heroRepository.save(hero);
    }

    public void delete(UUID id) {

        Hero hero = heroRepository.findById(id).get();
        hero.getPlayer().getHeroes().remove(hero);
        heroRepository.deleteById(id);
    }
}
