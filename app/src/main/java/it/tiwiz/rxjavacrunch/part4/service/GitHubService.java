package it.tiwiz.rxjavacrunch.part4.service;

import it.tiwiz.rxjavacrunch.part4.model.GitHubRepo;
import it.tiwiz.rxjavacrunch.part4.model.GitHubUser;
import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

public interface GitHubService {

    String ENDPOINT = "https://api.github.com";

    @GET("/users/{user}")
    Observable<GitHubUser> getUserData(@Path("user") String user);

    @GET("/users/{user}/repos")
    Observable<GitHubRepo[]> getRepoData(@Path("user") String user);
}
