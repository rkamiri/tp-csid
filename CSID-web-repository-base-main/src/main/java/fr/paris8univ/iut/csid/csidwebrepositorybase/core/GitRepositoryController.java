package fr.paris8univ.iut.csid.csidwebrepositorybase.core;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.domain.GitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping(value="/repositories")
public class GitRepositoryController {

    private final GitRepositoryService service;

    @Autowired
    public GitRepositoryController(GitRepositoryService service) {
        this.service = service;
   }

    @GetMapping
    public List<GitRepository> getRepositories(){
        return service.getRepositories();
    }

    @GetMapping("/{name}")
    public ResponseEntity<GitRepository> findOneRepository(@PathVariable String name) throws MalformedURLException, URISyntaxException {
        return service.findOneRepository(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    @PostMapping
    public ResponseEntity<GitRepository> createRepository(@RequestBody GitRepository gitRepository) throws URISyntaxException {
        service.createRepository(gitRepository);
        URI location = new URI("/repositories/"+gitRepository.getName());
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{name}")
    public ResponseEntity putRepository(@PathVariable String name,@RequestBody GitRepository gitRepository) {
        service.putRepository(name, gitRepository);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{name}")
    public ResponseEntity patchRepository(@PathVariable String name,@RequestBody GitRepository gitRepository) {
        service.patchRepository(name,gitRepository);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{name}")
    public void deleteRepository(@PathVariable String name) {
        service.deleteRepository(name);
    }

}