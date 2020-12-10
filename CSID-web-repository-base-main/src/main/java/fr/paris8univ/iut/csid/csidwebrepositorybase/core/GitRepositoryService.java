package fr.paris8univ.iut.csid.csidwebrepositorybase.core;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.domain.GitRepository;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.domain.GitRepositoryRepository;
import org.springframework.stereotype.Service;

@Service
public class GitRepositoryService {

    private final GitRepositoryRepository repositoryRepository;

    public GitRepositoryService(GitRepositoryRepository repositoryRepository) {
        this.repositoryRepository = repositoryRepository;
    }

    public List<GitRepository> getRepositories(){
       return this.repositoryRepository.getRepositories();
    }

    public Optional<GitRepository>  findOneRepository(String name) throws MalformedURLException, URISyntaxException {
        return  this.repositoryRepository.findOneRepository(name);
    }

    public void createRepository(GitRepository gitRepository){
        this.repositoryRepository.createRepository(gitRepository);
    }

    public void putRepository(String name,GitRepository gitRepository) {
        this.repositoryRepository.putRepository(name, gitRepository);
    }

    public void patchRepository(String name, GitRepository gitRepository) {
        this.repositoryRepository.patchRepository(name, gitRepository);
    }

    public void deleteRepository(String name) {
        this.repositoryRepository.deleteRepository(name);
    }

}
