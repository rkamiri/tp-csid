package fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

@Component
public class GithubRepositoryDao {

    private final RestTemplate template;

    @Autowired
    public GithubRepositoryDao(RestTemplateBuilder restBuilder){
        this.template= restBuilder.build();
    }

    //Create the request to get the content of the git repo
    public GitRepositoryDTO getRepositoryDto(String repoName, String owner) throws URISyntaxException {
        return this.template.getForEntity(new URI("https://api.github.com/repos/"+owner+"/"+repoName), GitRepositoryDTO.class).getBody();
    }

}
