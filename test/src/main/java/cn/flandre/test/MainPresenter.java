package cn.flandre.test;

import android.support.annotation.NonNull;

public class MainPresenter implements MainIndex.Presenter {
    private MainIndex.View view;
    private MainIndex.Model model;

    public MainPresenter(@NonNull MainIndex.View view){
        this.view = view;
        this.model = new MainModel(this);
    }

    @Override
    public void getMainData() {
        model.getMainData();
    }

    @Override
    public void onGetMainData(Result result) {
        view.showData(result.getData());
    }
}
