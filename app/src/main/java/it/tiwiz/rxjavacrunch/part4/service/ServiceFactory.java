package it.tiwiz.rxjavacrunch.part4.service;

import retrofit.RestAdapter;

public class ServiceFactory {

    public static <T> T createServiceFrom(final Class<T> serviceClass, String endpoint) {

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(endpoint)
                .build();

        T service = adapter.create(serviceClass);

        return service;
    }
}
