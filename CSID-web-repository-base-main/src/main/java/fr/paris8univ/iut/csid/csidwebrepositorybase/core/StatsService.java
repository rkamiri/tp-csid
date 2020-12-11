package fr.paris8univ.iut.csid.csidwebrepositorybase.core;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.domain.GitRepository;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.domain.GitRepositoryRepository;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.domain.Stats;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.domain.StatsRepository;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@Service
public class StatsService {

    private final StatsRepository statsRepository;

    public StatsService(StatsRepository statsRepository) {
        this.statsRepository = statsRepository;
    }

    public List<Stats> getStats(String name, String statsType){
       return this.statsRepository.getStats(name, statsType);
    }

}
