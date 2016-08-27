package com.crux.inventory.recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.crux.inventory.Item;
import com.gpsinsight.inventory.R;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

public class ItemAdapter extends RealmRecyclerViewAdapter<Item, ItemViewHolder> {

    public ItemAdapter(@NonNull Context context, @Nullable OrderedRealmCollection<Item> data) {
        super(context, data, true);
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false));
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        OrderedRealmCollection data = getData();
        if (data != null) {
            Item item = (Item) data.get(position);
            holder.name.setText(item.getName());
        }
    }

    @Override
    public int getItemCount() {
        OrderedRealmCollection data = getData();
        return data != null ? data.size() : 0;
    }
}
