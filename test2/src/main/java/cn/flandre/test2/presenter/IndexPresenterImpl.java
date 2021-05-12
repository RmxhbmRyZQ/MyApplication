package cn.flandre.test2.presenter;

import cn.flandre.test2.bean.IndexData;
import cn.flandre.test2.model.IndexModelImpl;
import cn.flandre.test2.view.IndexView;
import cn.flandre.test2.view.View;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class IndexPresenterImpl implements IndexPresenter {
    private IndexView indexView;
    private IndexModelImpl indexModel;

    @Override
    public void onCreate() {
        indexModel = new IndexModelImpl();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void commend(int page) {
        indexModel.getObservable(0).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<IndexData>() {
                    private IndexData data;
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(IndexData value) {
                data = value;
            }

            @Override
            public void onError(Throwable e) {
                indexView.onError(e);
            }

            @Override
            public void onComplete() {
                if (data != null) {
                    indexView.onSuccess(data.getData().getDatas());
                }
            }
        });
    }

    @Override
    public void attachView(View view) {
        indexView = (IndexView) view;
    }
}
