package com.hulixia1992.interview.androidinterview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hulixia1992.interview.androidinterview.R;

import java.util.List;

/**
 * Created by hulixia on 2016/9/21.
 */
public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ContentViewHolder> {
    private List<String> contents;
    private Context mContext;
    private LayoutInflater mInflater;
    private ContentItemClick mClick;

    public ContentAdapter(List<String> contents, Context context) {
        this.contents = contents;
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    public void setContentItemClick(ContentItemClick click) {
        mClick = click;
    }

    @Override
    public ContentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.content_item, parent, false);
        return new ContentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContentViewHolder holder, int position) {
        TextView tvContent = holder.tvContent;
        tvContent.setText(contents.get(position));
    }

    @Override
    public int getItemCount() {
        return contents.size();
    }

    class ContentViewHolder extends RecyclerView.ViewHolder {
        public TextView tvContent;

        public ContentViewHolder(View itemView) {
            super(itemView);
            tvContent = (TextView) itemView.findViewById(R.id.tv_content_item_title);
            tvContent.setOnClickListener((v) -> {
                mClick.itemClick();
            });
        }
    }

   public interface ContentItemClick {
        void itemClick();
    }
}
