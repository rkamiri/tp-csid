package fr.paris8univ.iut.csid.csidwebrepositorybase.core;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.domain.GitRepository;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.domain.Stats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping(value="repositories/{name}")
public class StatsController {

    private final StatsService statsService;

    @Autowired
    public StatsController(StatsService statsService) {
        this.statsService = statsService;
   }

    @GetMapping("/stats")
    public List<Stats> getStats(@PathVariable String name, @RequestParam String statsTypes){
        return statsService.getStats(name, statsTypes);
    }
}