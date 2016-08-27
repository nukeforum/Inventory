package com.crux.inventory;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.crux.inventory.recyclerview.ItemAdapter;
import com.gpsinsight.inventory.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @BindView(R.id.item_list)
    RecyclerView itemList;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @OnClick(R.id.fab) void onClick(View v) {
        showDialog();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Realm realm = Realm.getDefaultInstance();
        itemList.setLayoutManager(new LinearLayoutManager(this));
        itemList.setAdapter(new ItemAdapter(this, realm.where(Item.class).findAll()));
    }

    private void showDialog() {
        final View layout = LayoutInflater.from(this).inflate(R.layout.new_item_dialog, null);
        final EditText editText = (EditText) layout.findViewById(R.id.item_naming_field);
        new AlertDialog.Builder(this)
                .setMessage("Add item")
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Realm realm = Realm.getDefaultInstance();
                        Item item = new Item(editText.getText().toString());
                        realm.beginTransaction();
                        realm.insertOrUpdate(item);
                        realm.commitTransaction();
                    }
                })
                .setNegativeButton("Cancel", null)
                .setView(layout)
                .create()
                .show();
    }
}
