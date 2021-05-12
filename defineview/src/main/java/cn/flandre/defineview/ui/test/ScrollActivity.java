package cn.flandre.defineview.ui.test;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import cn.flandre.defineview.R;
import cn.flandre.defineview.ui.view.BarChart;
import cn.flandre.defineview.ui.view.CheckBoxView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ScrollActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);
        findViewById(R.id.checkbox).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBoxView checkBoxView = (CheckBoxView) v;
                checkBoxView.setCheck(!checkBoxView.isCheck());
            }
        });
    }
}
