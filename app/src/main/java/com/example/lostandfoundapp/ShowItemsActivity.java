package com.example.lostandfoundapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lostandfoundapp.DatabaseHelper;
import com.example.lostandfoundapp.LostFoundAdapter;
import com.example.lostandfoundapp.LostandFoundItem;
import com.example.lostandfoundapp.R;

import java.util.List;

public class ShowItemsActivity extends AppCompatActivity implements LostFoundAdapter.OnItemClickListener{

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<LostandFoundItem> itemList;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_items);

        recyclerView = findViewById(R.id.recyclerViewShowItems);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dbHelper = new DatabaseHelper(this);

        // Retrieve items from the database
        itemList = dbHelper.getAllItems();
        Log.d("ShowItemsActivity", "Number of items retrieved: " + itemList.size()); // Log the number of items retrieved

        if (itemList != null && !itemList.isEmpty()) {
            // Create and set the adapter
            adapter = new LostFoundAdapter(itemList, this);
            recyclerView.setAdapter(adapter);

        } else {
            Toast.makeText(this, "No items found in the database", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        // Refresh the data when the activity resumes
        itemList = dbHelper.getAllItems();
        if (itemList != null && !itemList.isEmpty()) {
            // Update the adapter
            adapter = new LostFoundAdapter(itemList, this);
            recyclerView.setAdapter(adapter);
        }
        Log.d("ShowItemsActivity", "Number of items displayed: " + (itemList != null ? itemList.size() : 0)); // Log the number of items displayed
    }

    @Override
    public void onItemClick(int position) {
        // Handle item click here
        // For example, start RemoveItemActivity with the clicked item's details
        LostandFoundItem clickedItem = itemList.get(position);
        Intent intent = new Intent(ShowItemsActivity.this, RemoveItemActivity.class);
        intent.putExtra("ITEM_ID", clickedItem.getId()); // Pass the item ID to the RemoveItemActivity if needed
        startActivity(intent);
    }
}

