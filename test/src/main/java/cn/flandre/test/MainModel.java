package cn.flandre.test;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.List;

public class MainModel implements MainIndex.Model {
    private MainIndex.Presenter presenter;

    public MainModel(MainIndex.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getMainData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://wanandroid.com/wxarticle/") // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 支持RxJava
                .build();
        MainIndex.MainRequest mainRequest = retrofit.create(MainIndex.MainRequest.class);
        mainRequest.getMainData().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Consumer<Result>() {
                            @Override
                            public void accept(Result result) throws Exception {
                                presenter.onGetMainData(result);
                            }
                        });
    }
}
