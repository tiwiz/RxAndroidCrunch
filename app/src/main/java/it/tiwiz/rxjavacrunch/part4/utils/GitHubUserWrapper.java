package it.tiwiz.rxjavacrunch.part4.utils;

import it.tiwiz.rxjavacrunch.part4.service.GitHubService;
import it.tiwiz.rxjavacrunch.part4.service.ServiceFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 *
 */
public class GitHubUserWrapper {

    private static final String[] interestingUsers = {"tiwiz", "rock3r", "Takhion", "dextorer", "Mariuxtheone"};

    public static void getUsersInto(final GitHubUsersAdapter adapter) {

        GitHubService gitHubService = ServiceFactory.createServiceFrom(GitHubService.class, GitHubService.ENDPOINT);

        Observable.from(interestingUsers)
                .flatMap(user -> gitHubService.getUserData(user))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(gitHubUser -> {
                    adapter.addUser(gitHubUser);
                });
    }
}
