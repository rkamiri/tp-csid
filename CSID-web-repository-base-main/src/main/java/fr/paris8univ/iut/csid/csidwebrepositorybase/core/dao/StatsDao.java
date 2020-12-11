package fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatsDao extends JpaRepository<StatsEntity,String>{

    /*@Query("select e from StatsEntity e where e.name=:name and e.entry_type =: type order by e.value asc")
    List<StatsEntity> findAllByTypeAsc(@Param("name")String name, @Param("type") String type);*/

    @Query(value = "select * from statistiques as s where s.name= ?1 and s.entry_type= ?2 order by value asc ",nativeQuery=true)
    List<StatsEntity> findAllByTypeAsc(String repository, String statType);
}
