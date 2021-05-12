package cn.flandre.test2.model;

import cn.flandre.test2.bean.IndexData;
import cn.flandre.test2.net.IndexRetrofit;
import io.reactivex.Observable;

public class IndexModelImpl implements IndexModel {
    private IndexRetrofit retrofit;

    public IndexModelImpl() {
        retrofit = new IndexRetrofit();
    }

    @Override
    public Observable<IndexData> getObservable(int page){
        return retrofit.getService().getCall(page);
    }
}
