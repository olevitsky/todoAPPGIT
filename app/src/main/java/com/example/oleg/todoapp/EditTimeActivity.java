package com.example.oleg.todoapp;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.example.oleg.todoapp.R;

public class EditTimeActivity extends ActionBarActivity {
    private int itemPos;
    EditText etNewItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_time);
        //experiment wtih new activity screen dimensions
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.x = -100;
        params.height = 500;
        params.width = 1000;
        params.y = -50;

        //this.getWindow().setBackgroundDrawable(new ColorDrawable(0));

        String itemText = getIntent().getStringExtra("itemText");
        itemPos = getIntent().getIntExtra("pos",0);
        etNewItem = (EditText) findViewById(R.id.ed_text);
        etNewItem.setText(itemText);
        this.getWindow().setAttributes(params);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.edit_time, menu);

        return true;
    }

    public void onEditItem(View v) {
        String itemText = etNewItem.getText().toString();
        // Prepare data intent
        Intent i = new Intent();
        // Pass relevant data back as a result
        i.putExtra("itemText" , itemText);
        i.putExtra("pos" , itemPos);
        setResult(RESULT_OK, i); // set result code and bundle data for response
        finish(); // closes the activity, pass data to parent
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
}
