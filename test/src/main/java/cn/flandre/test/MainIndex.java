package cn.flandre.test;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface MainIndex {
    public interface View{
        public void showData(Result.Content[] data);
    }

    public interface Presenter{
        public void getMainData();

        public void onGetMainData(Result result);
    }

    public interface Model{
        public void getMainData();
    }

    public interface MainRequest{
        @GET("chapters/json")
        public Observable<Result> getMainData();
    }

    public interface Test{
        @POST("a/a")
        public Observable<Result> test(@Body Result result);
    }
}
