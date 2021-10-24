package cn.lemongo97.aom.api;

import cn.lemongo97.aom.api.response.GithubReleasesResponseDTO;
import com.google.gson.Gson;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RedisIOAPI {
    RedisIOAPI service = new Retrofit.Builder()
            .baseUrl("https://download.redis.io/")
            .addConverterFactory(GsonConverterFactory.create(new Gson()))
            .build()
            .create(RedisIOAPI.class);

    /**
     * 使用时替换 packageName 值
     * 以 Redis 为例： https://download.redis.io/releases/redis-0.096.tar.gz
     */
    @GET("releases/{packageName}")
    Call<ResponseBody> download(@Path("packageName") String packageName);
}
