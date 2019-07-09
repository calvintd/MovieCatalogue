package com.made.calvintd.moviecatalogue.restapi;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private static final String API_KEY = "00205483717a5e1c9728acf6134e11c7";
    private static final String BASE_URL = "https://api.themoviedb.org/";

    private static final HttpLoggingInterceptor log = new HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY);
    private static Retrofit retrofit = null;

    private static OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder()
            .addInterceptor(log)
            .addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request();
                    HttpUrl url = request.url().newBuilder()
                            .addQueryParameter("api_key", API_KEY)
                            .build();
                    request = request.newBuilder()
                            .url(url)
                            .build();
                    return chain.proceed(request);
                }
            });

    private static OkHttpClient client = clientBuilder.build();

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        }
        return retrofit;
    }
}
