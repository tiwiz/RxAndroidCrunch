package it.tiwiz.rxjavacrunch.part4.utils;

import it.tiwiz.rxjavacrunch.part4.service.GitHubService;
import it.tiwiz.rxjavacrunch.part4.service.ServiceFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 *
 */
public class GitHubWrapper {

    private static final String[] interestingUsers = {"tiwiz", "rock3r", "Takhion", "dextorer", "Mariuxtheone"};

    public static void getUsersInto(final GitHubUsersAdapter adapter) {

        GitHubService gitHubService = ServiceFactory.createServiceFrom(GitHubService.class, GitHubService.ENDPOINT);

        Observable.from(interestingUsers)
                .flatMap(gitHubService::getUserData)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(adapter::addUser);
    }

    public static void getReposForUsersInto(final String username, final GitHubRepoAdapter adapter) {

        GitHubService gitHubService = ServiceFactory.createServiceFrom(GitHubService.class, GitHubService.ENDPOINT);

        gitHubService.getRepoData(username)
               .flatMap(Observable::from)
               .subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(adapter::addRepo);
    }
}
