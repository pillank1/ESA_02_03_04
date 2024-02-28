package ESA24.controller;

import ESA24.model.Hero;
import ESA24.model.Player;
import ESA24.repo.HeroRepository;
import ESA24.repo.PlayerRepository;
import ESA24.service.HeroService;
import ESA24.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainPage {

    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private HeroRepository heroRepository;

    @Autowired
    private HeroService heroService;

    @Autowired
    private PlayerService playerService;
    @GetMapping("/mainPage")
    public String mainPage(Model model1, Model model2){
        List<Player> players = playerRepository.findAll();
        List<Hero> heroes = heroRepository.findAll();
        model1.addAttribute("players", players);
        model2.addAttribute("heroes", heroes);
        return "MainPage";
    }
}
