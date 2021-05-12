package cn.flandre.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import cn.flandre.ui.R;
import cn.flandre.ui.bean.Data;
import cn.flandre.ui.view.RateView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
public class RateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);
        Gson gson = new GsonBuilder().create();  // 实例化对象
        Data data = gson.fromJson(getString(R.string.data), Data.class);

        RateView rateView = findViewById(R.id.rate);
        rateView.setData(data.getData().getScoring_trend());
    }
}
