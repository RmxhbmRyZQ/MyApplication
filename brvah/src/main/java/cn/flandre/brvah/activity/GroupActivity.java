package cn.flandre.brvah.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import cn.flandre.brvah.R;
import cn.flandre.brvah.adapter.GroupAdapter;
import cn.flandre.brvah.bean.GroupData;
import cn.flandre.brvah.bean.IndexData;

import java.util.ArrayList;
import java.util.List;

public class GroupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);
        RecyclerView recyclerView = findViewById(R.id.recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        GroupAdapter adapter = new GroupAdapter(R.layout.index_item, R.layout.index_header, null);
        recyclerView.setAdapter(adapter);
        List<GroupData> data = new ArrayList<>();
        for (int i = 0; i < 500; i++) {
            IndexData indexData = new IndexData(i + "", i + "");
            GroupData groupData = new GroupData(indexData);
            data.add(groupData);
            data.add(new GroupData(true, "www"));
        }
        adapter.addData(data);
    }
}
