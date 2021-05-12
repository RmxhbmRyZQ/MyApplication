package cn.flandre.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import cn.flandre.ui.R;
import cn.flandre.ui.view.ProgressView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int progress = 52;

        ProgressView progressView = findViewById(R.id.progress);
        progressView.setProgress(progress);
        TextView textView = findViewById(R.id.text);
        textView.setText(String.valueOf(progress));
    }
}
