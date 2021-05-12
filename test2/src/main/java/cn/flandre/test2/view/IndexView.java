package cn.flandre.test2.view;

import cn.flandre.test2.bean.IndexData;

public interface IndexView extends View {
    public void onSuccess(IndexData.Datas[] data);

    public void onError(Throwable t);
}
