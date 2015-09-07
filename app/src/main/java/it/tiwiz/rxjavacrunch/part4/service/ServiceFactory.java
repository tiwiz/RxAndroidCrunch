package it.tiwiz.rxjavacrunch.part4.service;

import retrofit.Retrofit;

/**
 *
 */
public class ServiceFactory {

    public static <T> T createServiceFrom(final Class<T> serviceClass, String endpoint) {

        final Retrofit adapter = new Retrofit.Builder().baseUrl(endpoint).build();

        T service = adapter.create(serviceClass);

        return service;
    }
}
