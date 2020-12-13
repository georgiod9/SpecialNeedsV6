package com.example.specialneeds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteAbortException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.nio.channels.InterruptedByTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //DatabaseHelper databaseHelper;
    ArrayList arrayList;
    ArrayAdapter arrayAdapter;
    private ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        Button create_usr_btn = (Button)findViewById(R.id.button_user_signup);
        create_usr_btn.setOnClickListener(this);

        Button create_vol_btn = (Button)findViewById(R.id.button_volunteer_signup);
        create_vol_btn.setOnClickListener(this);

        Button sign_in_btn = (Button)findViewById(R.id.button_signin);
        sign_in_btn.setOnClickListener(this);

        TextView toast = (TextView) findViewById(R.id.textView2);
        toast.setOnClickListener(this);

/*
        ListView test = (ListView) findViewById(R.id.ListView);
        //test.setText(cursor.getString(0));

        databaseHelper = new DatabaseHelper(MainActivity.this);

        arrayList = databaseHelper.getAllText();

        arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, arrayList);

        test.setAdapter(arrayAdapter);
*/
        this.listview = (ListView) findViewById(R.id.ListView);
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();

        String name = "GEORGIO";
        String email = "georgiod9@gmail.com";
        String pass = "123456";

        databaseAccess.addUser(name, email, pass);
        List<String> tt = databaseAccess.getData();
        databaseAccess.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, tt);
        this.listview.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button_user_signup:
                Intent createUser = new Intent(v.getContext(), CreateUserActivity.class);
                startActivity(createUser);
                break;

            case R.id.button_volunteer_signup:
                Intent createVolunteer = new Intent(v.getContext(), CreateVolunteerActivity.class);
                startActivity(createVolunteer);
                break;

            case R.id.button_signin:
                Intent signIn = new Intent(v.getContext(), SignInActivity.class);
                startActivity(signIn);
                break;

            case R.id.textView2:
                Context context = getApplicationContext();
                CharSequence text = "اضغط على الزر أدناه!";
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(context, text, duration);
                toast.setGravity(Gravity.BOTTOM, 0, 0);
                toast.show();

            default:
                break;
        }
    }
}