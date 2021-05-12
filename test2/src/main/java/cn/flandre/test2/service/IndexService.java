package cn.flandre.test2.service;

import cn.flandre.test2.bean.IndexData;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IndexService {
    @GET("article/list/{page}/json")
    public Observable<IndexData> getCall(@Path("page") int page);
}
