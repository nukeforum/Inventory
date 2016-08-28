package com.crux.inventory.recyclerview;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.crux.inventory.Item;
import com.gpsinsight.inventory.R;

import io.realm.OrderedRealmCollection;
import io.realm.Realm;
import io.realm.RealmRecyclerViewAdapter;

public class ItemAdapter extends RealmRecyclerViewAdapter<Item, ItemViewHolder> {

    private static final String TAG = "ItemAdapter";
    private Context context;

    public ItemAdapter(@NonNull Context context, @Nullable OrderedRealmCollection<Item> data) {
        super(context, data, true);
        this.context = context;
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
            final Item item = (Item) data.get(position);
            holder.name.setText(item.getName());
            final float points = item.getPoints();
            if (points == 0) {
                holder.itemView.setBackgroundColor(Color.RED);
            } else {
                int red = (int) (255f / (points / 10f * 255f));
                int green = (int) (255f * points / 10f);
                Log.d(TAG, "Points: " + String.valueOf(points));
                Log.d(TAG, "Red: " + String.valueOf(red));
                Log.d(TAG, "Green: " + String.valueOf(green));
                holder.itemView.setBackgroundColor(Color.rgb((int) (255.0f * (10.0f - points) / 10.0f), (int) (255.0f * points / 10.0f), 0));
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Realm realm = Realm.getDefaultInstance();
                    realm.beginTransaction();
                    if (points == 0) {
                        item.setPoints(10);
                    } else {
                        item.setPoints(item.getPoints() - 1);
                    }
                    realm.commitTransaction();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        OrderedRealmCollection data = getData();
        return data != null ? data.size() : 0;
    }
}
