package it.tiwiz.rxjavacrunch.part4.service;

import it.tiwiz.rxjavacrunch.part4.model.DepartureOverview;
import it.tiwiz.rxjavacrunch.part4.model.Stop;
import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 *
 */
public interface GTTService {

    String ENDPOINT = "http://www.5t.torino.it/ws2.1/rest/stops";

    @GET("/all")
    Observable<Stop> getStops();

    @GET("/{stopnumber}/departures")
    Observable<DepartureOverview> getDepartureDetails(@Path("stopnumber") int stopnumber);
}
