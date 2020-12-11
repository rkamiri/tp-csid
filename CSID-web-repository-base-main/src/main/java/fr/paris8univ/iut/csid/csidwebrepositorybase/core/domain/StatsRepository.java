package fr.paris8univ.iut.csid.csidwebrepositorybase.core.domain;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.*;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class StatsRepository {

    private final StatsDao statsDao;

    public StatsRepository(StatsDao statsDao) {
        this.statsDao = statsDao;
    }
    public StatsDao getDao(){
        return statsDao;
    }

    public List<Stats> getStats(String name, String statsType){
        List<StatsEntity> statsEntities = statsDao.findAllByTypeAsc(name, statsType);
        return statsEntities.stream()
                .map(x -> new Stats(x.getName(), x.getEntry_type(), x.getDatetime(), x.getValue()))
                .collect(Collectors.toList());
    }
}
