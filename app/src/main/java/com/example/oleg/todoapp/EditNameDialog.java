package com.example.oleg.todoapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class EditNameDialog extends DialogFragment  {
    private int itemPos;
    private String itemText;
    private String curPriority;
    private EditText etNewItem;
    private Spinner PullDownPriority;

   // EditText  mEditText;
    OnHeadlineSelectedListener mCallback;


    // Container Activity must implement this interface
    public interface OnHeadlineSelectedListener {
        public void onArticleSelected(String text, int pos, String priority);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (OnHeadlineSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }



    public EditNameDialog() {
        // Empty constructor required for DialogFragment
    }

    /*public static EditNameDialog newInstance(String title) {
        EditNameDialog frag = new EditNameDialog();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_edit_name, container);
        etNewItem = (EditText) view.findViewById(R.id.ed_text);
        PullDownPriority = (Spinner) view.findViewById (R.id.ed_priority);
        Bundle bundle = this.getArguments();
        itemText = bundle.getString("itemText");
        curPriority = bundle.getString("priority");
        itemPos = bundle.getInt("pos");
        getDialog().setTitle("Edit Item");
        etNewItem.setText(itemText);
        if (curPriority.equals(Integer.toString(Color.RED))) {
            PullDownPriority.setSelection(2);
        } else if (curPriority.equals(Integer.toString(Color.YELLOW))) {
            PullDownPriority.setSelection(1);
        } else {
            PullDownPriority.setSelection(0);
        }
        // Show soft keyboard automatically
        etNewItem.requestFocus();
        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        Button btn=(Button)view.findViewById(R.id.e_saveButton);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Is it possible to set callback through xml????
                //String itemText = etNewItem.getText().toString();
                //String curPriority = String.valueOf(PullDownPriority.getSelectedItem());
                itemText = etNewItem.getText().toString();
                curPriority = String.valueOf(PullDownPriority.getSelectedItem());
                String retCurrPriority;
                if(curPriority.equals("High")) {
                    retCurrPriority = Integer.toString(Color.RED);
                } else if(curPriority.equals("Med")) {
                    retCurrPriority = Integer.toString(Color.YELLOW);
                } else {
                    retCurrPriority = Integer.toString(Color.WHITE);
                }
                mCallback.onArticleSelected(itemText, itemPos, retCurrPriority);
                getDialog().dismiss();
            }
        });
        return view;
    }
}