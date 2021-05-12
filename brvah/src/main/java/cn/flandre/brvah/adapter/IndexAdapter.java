package cn.flandre.brvah.adapter;

import android.support.annotation.Nullable;
import cn.flandre.brvah.R;
import cn.flandre.brvah.bean.IndexData;
import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class IndexAdapter extends BaseItemDraggableAdapter<IndexData, BaseViewHolder> {
    public IndexAdapter(int layoutResId, @Nullable List<IndexData> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, IndexData item) {
        helper.setText(R.id.left, item.getT1());
        helper.setText(R.id.right, item.getT2());
    }
}
