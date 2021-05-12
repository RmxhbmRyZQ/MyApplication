package cn.flandre.brvah.bean;

import com.chad.library.adapter.base.entity.SectionEntity;

public class GroupData extends SectionEntity<IndexData> {
    public GroupData(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public GroupData(IndexData indexData) {
        super(indexData);
    }
}
