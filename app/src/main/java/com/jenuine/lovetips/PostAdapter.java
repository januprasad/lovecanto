package com.jenuine.lovetips;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<SubPostData> posts;
    private int rowLayout, headerLayout;
    private Context mContext;
    String title;
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    public PostAdapter(Post posts, int headerLayout, int rowLayout, Context context) {
        this.posts = posts.getSubPostDatas();
        this.title = posts.getTitle();
        this.rowLayout = rowLayout;
        this.headerLayout = headerLayout;
        this.mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        if (viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
            return new ViewHolder(v);
        } else if (viewType == TYPE_HEADER) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(headerLayout, viewGroup, false);
            return new ViewHeader(v);
        } else
            return null;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int i) {

        if (holder instanceof ViewHeader) {
            ViewHeader viewHeader = (ViewHeader) holder;
            viewHeader.title.setText(title);
        } else if (holder instanceof ViewHolder) {
            SubPostData subPost = posts.get(i-1);
            ViewHolder viewHolder = (ViewHolder) holder;
            if(!subPost.getTitle().isEmpty()){
                viewHolder.title.setVisibility(View.VISIBLE);
                viewHolder.title.setText(subPost.getTitle());
            }else
                viewHolder.title.setVisibility(View.GONE);
            viewHolder.content.setText(subPost.getContent());
            //cast holder to VHHeader and set data for header.
        }


    }

    @Override
    public int getItemCount() {
        return posts == null ? 0 : posts.size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position))
            return TYPE_HEADER;
        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView content;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            title.setBackgroundResource(R.drawable.ripple_effect);
            content = (TextView) itemView.findViewById(R.id.content);
        }

    }

    public static class ViewHeader extends RecyclerView.ViewHolder {
        public TextView title;

        public ViewHeader(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
        }

    }
}
