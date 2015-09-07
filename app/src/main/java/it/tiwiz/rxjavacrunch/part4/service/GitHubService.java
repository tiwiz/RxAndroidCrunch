package it.tiwiz.rxjavacrunch.part4.service;

import it.tiwiz.rxjavacrunch.part4.model.GitHubUser;
import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by tiwiz on 07/09/15.
 */
public interface GitHubService {

    String ENDPOINT = "https://api.github.com";

    @GET("/users/{user}")
    Observable<GitHubUser> getUserData(@Path("user") String user);


}
