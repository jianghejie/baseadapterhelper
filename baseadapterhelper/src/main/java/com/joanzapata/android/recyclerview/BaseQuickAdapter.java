package com.joanzapata.android.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jianghejie on 15/8/8.
 */
public abstract class BaseQuickAdapter <T, H extends BaseAdapterHelper> extends RecyclerView.Adapter<BaseAdapterHelper> implements View.OnClickListener{
    protected static final String TAG = BaseQuickAdapter.class.getSimpleName();

    protected final Context context;

    protected final int layoutResId;

    protected final List<T> mItems;

    protected boolean displayIndeterminateProgress = false;

    private OnItemClickListener mOnItemClickListener = null;

    //define interface
    public static interface OnItemClickListener {
        void onItemClick(View view , int position);
    }

    /**
     * Create a QuickAdapter.
     * @param context     The context.
     * @param layoutResId The layout resource id of each item.
     */
    public BaseQuickAdapter(Context context, int layoutResId) {
        this(context, layoutResId, null);
    }

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     * @param context     The context.
     * @param layoutResId The layout resource id of each item.
     * @param mItems        A new list is created out of this one to avoid mutable list
     */
    public BaseQuickAdapter(Context context, int layoutResId, List<T> mItems) {
        this.mItems = mItems == null ? new ArrayList<T>() : mItems;
        this.context = context;
        this.layoutResId = layoutResId;
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }


    public T getItem(int position) {
        if (position >= mItems.size()) return null;
        return mItems.get(position);
    }

    public void addItem(T item) {
        mItems.add(item);
        notifyDataSetChanged();
    }

    public void clear() {
        mItems.clear();
        notifyDataSetChanged();
    }

    @Override
    public BaseAdapterHelper onCreateViewHolder(ViewGroup viewGroup,  int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(layoutResId, viewGroup, false);
        view.setOnClickListener(this);
        BaseAdapterHelper vh = new BaseAdapterHelper(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(BaseAdapterHelper helper,  int position) {
        helper.itemView.setTag(position);
        T item = getItem(position);
        convert((H)helper, item);
    }

    /**
     * Implement this method and use the helper to adapt the view to the given item.
     * @param helper A fully initialized helper.
     * @param item   The item that needs to be displayed.
     */
    protected abstract void convert(H helper, T item);

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(v,(int)v.getTag());
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

}
