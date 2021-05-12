package cn.flandre.test2.net;

import cn.flandre.test2.service.IndexService;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class IndexRetrofit {
    private Retrofit retrofit;

    public IndexRetrofit(){
        retrofit = new Retrofit.Builder()
                .baseUrl("https://www.wanandroid.com/") // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 支持RxJava
                .build();
    }

    public IndexService getService(){
        return retrofit.create(IndexService.class);
    }
}
