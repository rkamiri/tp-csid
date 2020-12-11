package fr.paris8univ.iut.csid.csidwebrepositorybase.core.domain;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.*;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.utils.GitTime;
import org.springframework.stereotype.Repository;

import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class GitRepositoryRepository {

    private final GitRepositoryDao gitRepositoryDao;
    private final GithubRepositoryDao githubRepositoryDao;
    private final StatsDao statsDao;
    public GitRepositoryRepository(GitRepositoryDao gitRepositoryDao, GithubRepositoryDao githubRepositoryDao, StatsDao statsDao) {
        this.gitRepositoryDao = gitRepositoryDao;
        this.githubRepositoryDao = githubRepositoryDao;
        this.statsDao = statsDao;
    }
    public GitRepositoryDao getDao(){
        return gitRepositoryDao;
    }

    public List<GitRepository> getRepositories(){
        List<GitRepositoryEntity> repositoryEntities = gitRepositoryDao.findAll();
        return repositoryEntities.stream()
                .map(x -> new GitRepository(x.getName(),x.getOwner(),x.getOpen_issues(),x.getForks(), x.getLastEdit()))
                .collect(Collectors.toList());
    }

    public Optional<GitRepository> findOneRepository(String name) throws  URISyntaxException {
        GitRepositoryEntity gitRepoEnt = gitRepositoryDao.findById(name).get();
        GitRepository gitRepo = new GitRepository(gitRepoEnt.getName(), gitRepoEnt.getOwner(), gitRepoEnt.getOpen_issues(), gitRepoEnt.getForks(), gitRepoEnt.getLastEdit());
        GitRepositoryDTO gitRepoDTO = githubRepositoryDao.getRepositoryDto(gitRepo.getName(), gitRepo.getOwner());

        if(GitTime.compareTwoDates(GitTime.convertStringToLocalDateTime(gitRepo.getLastEdit()), GitTime.getActualLocalDate())>=5) {
            System.out.println("yeetus deletus");
            gitRepo.setForks(gitRepoDTO.getForks());
            gitRepo.setOpen_issues(gitRepoDTO.getOpen_issues());
            gitRepo.setLastEdit(GitTime.getActualDate());
            statsDao.save(new StatsEntity(gitRepo.getName(), "forks", gitRepo.getLastEdit(), gitRepo.getForks()));
            statsDao.save(new StatsEntity(gitRepo.getName(), "issues", gitRepo.getLastEdit(), gitRepo.getOpen_issues()));
            patchRepository(gitRepo.getName(), gitRepo);
        }
        return Optional.of(gitRepo);
    }

    public void createRepository(GitRepository gitRepository){
        if(gitRepository.getForks()== null){
            gitRepository.setForks(0);
        }
        if(gitRepository.getOpen_issues()== null){
            gitRepository.setOpen_issues(0);
        }
        gitRepository.setLastEdit(GitTime.getActualDate());
        gitRepositoryDao.save(new GitRepositoryEntity(gitRepository.getName(),gitRepository.getOwner(),gitRepository.getOpen_issues(),gitRepository.getForks(), gitRepository.getLastEdit()));
    }

    public void putRepository(String name,GitRepository gitRepository) {
        Optional<GitRepositoryEntity> repository =  gitRepositoryDao.findById(name);

        if(repository.isEmpty()) {
            createRepository(gitRepository);
        }
        else{
            GitRepositoryEntity repositoryModified = repository.get();
            repositoryModified.setOwner(gitRepository.getOwner());
            repositoryModified.setOpen_issues(gitRepository.getOpen_issues());
            repositoryModified.setForks(gitRepository.getForks());
            gitRepositoryDao.save(repositoryModified);
        }
    }

    public void patchRepository(String name, GitRepository gitRepository) {
        Optional<GitRepositoryEntity> repository =  gitRepositoryDao.findById(name);
        GitRepositoryEntity repositoryModified = repository.get();

        if(gitRepository.getOwner() != null)
            repositoryModified.setOwner(gitRepository.getOwner());
        if(gitRepository.getOpen_issues()!= null)
            repositoryModified.setOpen_issues(gitRepository.getOpen_issues());
        if(gitRepository.getForks()!=null)
            repositoryModified.setForks(gitRepository.getForks());
        if(gitRepository.getLastEdit()!=null)
            repositoryModified.setLastEdit(gitRepository.getLastEdit());
        gitRepositoryDao.save(repositoryModified);
    }

    public void deleteRepository(String name) {
        gitRepositoryDao.deleteById(name);
    }

}
