package ESA24.controller;


import ESA24.model.Hero;
import ESA24.model.Player;
import ESA24.service.HeroService;
import ESA24.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/heroes")
public class HeroController {

    @Autowired
    private HeroService heroService;

    @Autowired
    private PlayerService playerService;

    @GetMapping("/create")
    public String getCreatePage(Model model) {
        model.addAttribute("hero", new Hero());
        List<UUID> playerIds = playerService.getAll().stream().map(Player::getId).collect(Collectors.toList());
        model.addAttribute("playerIds", playerIds);
        return "CreateHero";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Hero hero, @RequestParam("playerId") UUID playerId) {
        heroService.create(hero, playerId);
        return "redirect:/heroes";
    }

    @GetMapping
    public String getAll(Model model) {
        List<Hero> heroes = heroService.getAll();
        model.addAttribute("heroes", heroes);
        return "ShowHeroes";
    }

    @GetMapping("/{id}/update")
    public String getUpdatePage(@PathVariable("id") UUID heroId, Model model) {
        model.addAttribute("hero", heroService.get(heroId));
        List<UUID> playerIds = playerService.getAll().stream().map(Player::getId).collect(Collectors.toList());
        model.addAttribute("playerIds", playerIds);
        return "UpdateHero";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") UUID heroId, @ModelAttribute Hero hero, @RequestParam UUID playerId) {
        heroService.update(heroId, hero, playerId);
        return "redirect:/heroes";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") UUID id) {
        heroService.delete(id);
        return "redirect:/heroes";
    }

}
