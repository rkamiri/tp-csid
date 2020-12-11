package fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GitRepositoryDTO {

    private String name;
    private String login;
    private Integer open_issues;
    private Integer forks;
    private String lastedit;

    public GitRepositoryDTO(String name,String login, int open_issues,int forks, String lastedit) {
        this.name=name;
        this.login=login;
        this.open_issues=open_issues;
        this.forks=forks;
        this.lastedit=lastedit;
    }

    public Integer getOpen_issues() {
        return open_issues;
    }

    public void setOpen_issues(int open_issues) {
        this.open_issues = open_issues;
    }

    public Integer getForks() {
        return forks;
    }

    public void setForks(int forks) {
        this.forks = forks;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name=name;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login=login;
    }

    public void setLastEdit(String lastedit){this.lastedit=lastedit;}

    public String getLastEdit(){return this.lastedit;}
}
