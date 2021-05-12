package cn.flandre.test2.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import cn.flandre.test2.R;
import cn.flandre.test2.adapter.IndexAdapter;
import cn.flandre.test2.bean.IndexData;
import cn.flandre.test2.presenter.IndexPresenter;
import cn.flandre.test2.presenter.IndexPresenterImpl;
import cn.flandre.test2.view.IndexView;

public class MainActivity extends AppCompatActivity implements IndexView {
    private IndexPresenter presenter;
    private int page = 0;
    private IndexAdapter adapter;
    private boolean isBottom = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new IndexPresenterImpl();
        presenter.onCreate();
        presenter.attachView(this);

        RecyclerView view = findViewById(R.id.recycle);
        view.setLayoutManager(new LinearLayoutManager(this, LinearLayout.VERTICAL, false));
        adapter = new IndexAdapter();
        view.setAdapter(adapter);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setVisibility(View.INVISIBLE);
                presenter.commend(page);
            }
        });

        view.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState != RecyclerView.SCROLL_STATE_IDLE) return;

                if (isBottom) return;

                isBottom = true;
                presenter.commend(++page);
            }
        });
    }

    @Override
    public void onSuccess(IndexData.Datas[] data) {
        adapter.update(data);
        isBottom = false;
    }

    @Override
    public void onError(Throwable t) {
        Log.e("", t.toString());
    }
}
