package com.appinc.laboratorio.Controller;

import com.appinc.laboratorio.Interface.IPersona;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Connection {

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("http://35.224.231.248:88/api/")
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = builder.build();

    private static HttpLoggingInterceptor loggingInterceptor =
            new HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BASIC);

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private <S> S createService(Class<S> serviceClass) {
        httpClient.readTimeout(60, TimeUnit.SECONDS).connectTimeout(60, TimeUnit.SECONDS);
        if (!httpClient.interceptors().contains(loggingInterceptor)) {
            httpClient.addInterceptor(loggingInterceptor);
            builder.client(httpClient.build());
        }

        return retrofit.create(serviceClass);
    }

    public IPersona IPersona() {
        return this.createService(IPersona.class);
    }
}
