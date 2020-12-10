package fr.paris8univ.iut.csid.csidwebrepositorybase.core.domain;


public class GitRepository {
	private String name;
	private String owner;
	private Integer open_issues;
	private Integer forks;

	public GitRepository() {}
	
	public GitRepository(String name,String owner, int open_issues,int forks) {
		this.name=name;
		this.owner=owner;
		this.open_issues=open_issues;
		this.forks=forks;
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
	
	public String getOwner() {
		return this.owner;
	}
	
	public void setOwner(String owner) {
		this.owner=owner;
	}
}
