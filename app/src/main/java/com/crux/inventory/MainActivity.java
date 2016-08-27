package com.crux.inventory;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.gpsinsight.inventory.R;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.item_list)
    RecyclerView itemList;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
