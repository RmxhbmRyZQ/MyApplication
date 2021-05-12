package cn.flandre.test2.presenter;

import cn.flandre.test2.view.View;

public interface Presenter {
    public void onCreate();

    public void onStart();

    public void onStop();

    public void onDestroy();

    public void attachView(View view);
}
