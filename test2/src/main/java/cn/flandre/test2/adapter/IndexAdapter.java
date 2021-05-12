package cn.flandre.test2.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import cn.flandre.test2.R;
import cn.flandre.test2.bean.IndexData;

import java.util.ArrayList;
import java.util.Arrays;

public class IndexAdapter extends RecyclerView.Adapter<IndexAdapter.ViewHolder> {
    ArrayList<IndexData.Datas> datas = new ArrayList<>();

    public void update(IndexData.Datas[] datas){
        this.datas.addAll(Arrays.asList(datas));
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.index_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(datas.get(position).getTitle());
        holder.author.setText("作者：" + datas.get(position).getAuthor());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView title;
        private TextView author;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            author = itemView.findViewById(R.id.author);
        }
    }
}
