package com.cookminute.simpledroidrx.Utils.NavigationDrawer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cookminute.simpledroidrx.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by Philippe on 20/07/16.
 */
public class NavigationDrawerAdapter extends RecyclerView.Adapter<NavigationDrawerAdapter.MyViewHolder> {
    List<NavDrawerItem> data = Collections.emptyList();
    private LayoutInflater inflater;
    private Context context;

    public NavigationDrawerAdapter(Context context, List<NavDrawerItem> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    public void delete(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_navigation_drawer, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        NavDrawerItem current = data.get(position);
        holder.title.setText(current.getTitle());
        holder.img.setImageResource(this.getResource(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView img;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            img = (ImageView) itemView.findViewById(R.id.row_navigation_drawer_icon);
        }
    }

    private int getResource(int position){
        switch (position){
            case 0:
                return R.drawable.ic_world;
            case 1:
                return R.drawable.ic_operator;
            case 2:
                return R.drawable.ic_error;
            case 3:
                return R.drawable.ic_task;
            case 4:
                return R.mipmap.ic_launcher;
            case 5:
                return R.mipmap.ic_launcher;
            default:
                return R.mipmap.ic_launcher;
        }
    }
}
