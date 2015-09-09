package com.jenuine.lovetips;

import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ListTitlesAdapter extends RecyclerView.Adapter {
    private ArrayList<Post> posts;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the vieews for a data item in a view holder
    OnItemClickListener onItemClickListener;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data item is just a string in this case

        public TextView mTextView;

        public ViewHolder(TextView v) {
            super(v);
            mTextView = v;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                mTextView.setBackgroundResource(R.drawable.ripple_effect);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onItemClickListener.onItemClick(view, getPosition());
        }

    }


    // Provide a suitable constructor (depends on the kind of dataset)
    public ListTitlesAdapter(Data data) {
        posts = data.getArrayList();

    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public void setOnClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view
        TextView v = (TextView) LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.textview, viewGroup, false);

        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        // - get element from your dataset at this position
        // = replace the contents of the view with that element
        ((ViewHolder) viewHolder).mTextView.setText(posts.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }
}
