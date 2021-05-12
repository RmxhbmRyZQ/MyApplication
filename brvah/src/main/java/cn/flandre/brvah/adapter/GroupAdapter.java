package cn.flandre.brvah.adapter;

import cn.flandre.brvah.R;
import cn.flandre.brvah.bean.GroupData;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class GroupAdapter extends BaseSectionQuickAdapter<GroupData, BaseViewHolder> {
    /**
     *
     * @param layoutResId
     * @param sectionHeadResId
     * @param data
     */
    public GroupAdapter(int layoutResId, int sectionHeadResId, List<GroupData> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, GroupData item) {
        helper.setImageResource(R.id.header, R.drawable.ic_launcher_background);
    }

    @Override
    protected void convert(BaseViewHolder helper, GroupData item) {

        helper.setText(R.id.left, item.t.getT1());
        helper.setText(R.id.right, item.t.getT2());
    }
}
