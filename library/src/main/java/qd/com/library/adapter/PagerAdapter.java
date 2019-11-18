package qd.com.library.adapter;

import java.io.Serializable;
import java.util.List;


public abstract class PagerAdapter<T extends Serializable, Model extends IPageModel<T>, Holder extends AHolder> extends AAdapter<Holder> {
    protected Model mPagerAdapterModel;

    public PagerAdapter() {
        mPagerAdapterModel = initModel();
    }

    public void updateList(List<T> list) {
        mPagerAdapterModel.setList(list);
        try {
            notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mergeList(List<T> list) {
        mPagerAdapterModel.addList(list);
        try {
            notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeItem(int position) {
        List<T> list = mPagerAdapterModel.getList();
        list.remove(position);
//        mPagerAdapterModel.setList(list);
        try {
            notifyItemRemoved(position);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public abstract Model initModel();

    public T getCurrentItem(int pos) {
        if (pos >= 0) {
            return mPagerAdapterModel.get(pos);
        } else {
            return null;
        }
    }

    @Override
    public int getItemCount() {
        return mPagerAdapterModel == null ? 0 : mPagerAdapterModel.getCount();
    }

    public abstract int getLayoutId();
}
