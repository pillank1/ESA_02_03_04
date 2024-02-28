package ESA24.controller;


import ESA24.aspect.Loggable;
import ESA24.model.Hero;
import ESA24.model.Player;
import ESA24.repo.HeroRepository;
import ESA24.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    private PlayerService playerService;
    @Autowired
    private HeroRepository heroRepository;

    @GetMapping("/create")
    public String getCreatePage(Model model) {
        model.addAttribute(new Player());
        return "CreatePlayer";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Player player) {
        playerService.create(player);
        return "redirect:/players";
    }

    @GetMapping
    public String getAll(Model model) {
        List<Player> players = playerService.getAll();
        model.addAttribute("players", players);
        return "ShowPlayers";
    }

    @GetMapping("/{id}/update")
    public String getUpdatePage(@PathVariable("id") UUID playerId, Model model) {
        model.addAttribute("player", playerService.get(playerId));
        return "UpdatePlayer";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") UUID playerId, @ModelAttribute Player player) {
        playerService.update(playerId, player);
        return "redirect:/players";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") UUID id) {
        playerService.delete(id);
        return "redirect:/players";
    }


}
