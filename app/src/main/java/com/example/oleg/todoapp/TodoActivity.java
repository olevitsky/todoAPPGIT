package com.example.oleg.todoapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class TodoActivity extends ActionBarActivity {
    private ArrayList<String> toDoItems;
    private ArrayAdapter<String> toDoAdapter;
    private ListView lvItems;
    private EditText etNewItem;
    private final int REQUEST_CODE = 20;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        lvItems = (ListView) findViewById(R.id.td_list);
        etNewItem = (EditText) findViewById(R.id.td_edit);
        readItems();
        //getBaseContext() == this;
        toDoAdapter = new ArrayAdapter<String>(getBaseContext(),android.R.layout.simple_list_item_1,toDoItems);
        lvItems.setAdapter(toDoAdapter);
        setupListViewListener();

    }

    private void setupListViewListener() {
        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View item, int pos, long id) {
                // could toDoAdapter.remove(...);
                toDoItems.remove(pos);
                toDoAdapter.notifyDataSetChanged();
                writeItems();
                return true;
            }
        });
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View item, int pos, long id) {
                Intent i = new Intent(TodoActivity.this, EditTimeActivity.class);
                String itemText = toDoAdapter.getItem(pos);
                i.putExtra("itemText" , itemText);
                i.putExtra("pos" , pos);
                startActivityForResult(i, REQUEST_CODE); // brings up the second activity
            }
        });
    }

    private void readItems() {
        File filesDir = getFilesDir(); // special directory containing files for this app
        File todoFile =  new File (filesDir, "todo.txt");
        try {
            toDoItems = new ArrayList<String>(FileUtils.readLines(todoFile));
        } catch (IOException e) {
            toDoItems = new ArrayList<String>(); // no file then create empty list
        } // in case file doesn't exist
    }

    private void writeItems() {
        File filesDir = getFilesDir(); // special directory containing files for this app
        File todoFile =  new File (filesDir, "todo.txt");
        try {
            FileUtils.writeLines(todoFile, toDoItems);
        } catch (IOException e) {
            e.printStackTrace();
        } // in case file doesn't exist
    }
    public void onAddedItem(View v) {
        String itemText = etNewItem.getText().toString();
        if (!itemText.isEmpty()) { // don't add items with no content
            toDoAdapter.add(itemText);
            etNewItem.setText("");
            writeItems();
        }
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.todo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // ActivityOne.java, time to handle the result of the sub-activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // REQUEST_CODE is defined above
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            // Extract name value from result extras
            String itemText = data.getExtras().getString("itemText");
            int pos = data.getExtras().getInt("pos", 0);
            // remove empty item
            if (itemText.isEmpty()) {
                toDoItems.remove(pos);
            } else {
                toDoItems.set(pos, itemText);
            }
            toDoAdapter.notifyDataSetChanged();
            writeItems();
        }
    }
}
