package cn.flandre.defineview.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import cn.flandre.defineview.R;
import cn.flandre.defineview.ui.view.CheckBoxView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final CheckBoxView checkBoxView = findViewById(R.id.checkbox);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxView.setCheck(!checkBoxView.isCheck());
            }
        });
    }
}
