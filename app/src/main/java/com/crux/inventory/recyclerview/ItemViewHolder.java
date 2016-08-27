package com.crux.inventory.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.gpsinsight.inventory.R;

public class ItemViewHolder extends RecyclerView.ViewHolder {

    TextView name;

    public ItemViewHolder(View itemView) {
        super(itemView);
        this.name = (TextView) itemView.findViewById(R.id.name);
    }
}
