package cn.flandre.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.TextView;
import cn.flandre.ui.R;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Objects;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        GridLayout gridLayout = findViewById(R.id.grid);
        for (int i = 0;i<5;i++) {
            TextView textView = new TextView(this);
            textView.setText("dawlkd");
            textView.setTextSize(20);
            textView.setTextColor(0xff000000);
            textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            GridLayout.LayoutParams params = new GridLayout.LayoutParams(GridLayout.spec(0),
                    GridLayout.spec(i, 1.0f));
            textView.setLayoutParams(params);
            gridLayout.addView(textView);
        }
        int breakPoint = 0;
    }
}
