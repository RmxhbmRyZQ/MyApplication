package cn.flandre.test2.model;

import cn.flandre.test2.bean.IndexData;
import io.reactivex.Observable;

public interface IndexModel {
    public Observable<IndexData> getObservable(int page);
}
