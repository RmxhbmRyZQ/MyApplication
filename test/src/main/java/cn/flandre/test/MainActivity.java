package cn.flandre.test;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainIndex.View {
    private Adapter adapter;
    private MainIndex.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recycle);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        adapter = new Adapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(false);
        presenter = new MainPresenter(this);
        presenter.getMainData();
    }

    @Override
    public void showData(Result.Content[] data) {
        adapter.update(data);
    }

    private class Adapter extends RecyclerView.Adapter<Adapter.Holder>{
        private Result.Content[] data;

        public void update(Result.Content[] content){
            data = content;
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.main_list,viewGroup,false);
            return new Holder(view);
        }

        @Override
        public int getItemCount() {
            if (data == null)
                return 0;
            return data.length;
        }

        @Override
        public void onBindViewHolder(@NonNull Holder holder, int i) {
            String name = data[i].getName();
            holder.tv.setText("用户名：" + name);
        }

        private class Holder extends RecyclerView.ViewHolder{
            private TextView tv;

            public Holder(@NonNull View itemView) {
                super(itemView);
                tv = itemView.findViewById(R.id.text);
            }
        }
    }
}
