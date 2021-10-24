package cn.lemongo97.aom.api;


import cn.lemongo97.aom.api.response.GithubReleasesResponseDTO;
import com.google.gson.Gson;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

/**
 * 文档来源：https://docs.github.com/en/rest
 */
public interface GithubAPI {

    GithubAPI service = new Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create(new Gson()))
            .build()
            .create(GithubAPI.class);

    /**
     * 使用时替换 owner 和 repo 值
     * 以 Redis 为例： https://api.github.com/repos/redis/redis/releases
     */
    @GET("repos/{owner}/{repo}/releases")
    Call<List<GithubReleasesResponseDTO>> listReleases(@Path("owner") String owner, @Path("repo") String repo);
}
