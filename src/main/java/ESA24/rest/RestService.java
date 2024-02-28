package ESA24.rest;

import ESA24.model.Hero;
import ESA24.model.Player;
import ESA24.repo.HeroRepository;
import ESA24.repo.PlayerRepository;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("restapi")
public class RestService {

    private final HeroRepository heroRepository;

    @Autowired
    public RestService(HeroRepository heroRepository) {
        this.heroRepository = heroRepository;
    }

    @GetMapping("/MainPage/json/{id}")
    public ResponseEntity<List<Hero>> getJsonHeroes(@PathVariable(name = "id") String Id) {
        UUID formattedId = UUID.fromString(Id);

        List<Hero> heroes = heroRepository.findAllByPlayerId(formattedId);

        return heroes != null
                ? new ResponseEntity<>(heroes, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping(value = "/MainPage/xml/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public ModelAndView  getXMLHeroes(@PathVariable(name = "id") String Id) throws JsonProcessingException {
        UUID formattedId = UUID.fromString(Id);

        List<Hero> heroes = heroRepository.findAllByPlayerId(formattedId);

        ModelAndView modelAndView = new ModelAndView("heroes");
        Source source = new StreamSource(new ByteArrayInputStream(new XmlMapper().writeValueAsBytes(heroes)));
        modelAndView.addObject(source);

        return modelAndView;
    }
}
