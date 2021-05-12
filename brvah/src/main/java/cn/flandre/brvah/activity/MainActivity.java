package cn.flandre.brvah.activity;

import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.flandre.brvah.R;
import cn.flandre.brvah.adapter.IndexAdapter;
import cn.flandre.brvah.bean.IndexData;
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback;
import com.chad.library.adapter.base.entity.SectionEntity;
import com.chad.library.adapter.base.listener.OnItemDragListener;
import com.chad.library.adapter.base.listener.OnItemSwipeListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        IndexAdapter adapter = new IndexAdapter(R.layout.index_item, null);
        recyclerView.setAdapter(adapter);
        List<IndexData> datas = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            IndexData data = new IndexData(i + "", i + "");
            datas.add(data);
        }
        adapter.addData(datas);
        adapter.addHeaderView(getView());
        adapter.addHeaderView(getView());
    }

    private View getView(){
        TextView textView = new TextView(this);
        textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        textView.setTextSize(20);
        textView.setText("wwwwwwww");
        return textView;
    }
}
