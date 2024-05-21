package com.example.pagadlabactivity4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class Contacts extends AppCompatActivity {

    ListView lvContactList;
    Button btnAddContact, btnGoBackBack;
    ArrayList<String> name, contactNum, email;
    ArrayList<Integer> age;
    ArrayAdapter contactListAdapter, contactListNumberAdapter;
    Intent viewContact, deleteRecord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_contacts);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        lvContactList = findViewById(R.id.lvContactList);
        btnAddContact = findViewById(R.id.btnAddContact);
        btnGoBackBack = findViewById(R.id.btnGoBackBack);

        name = new ArrayList<>();
        contactNum = new ArrayList<>();
        email = new ArrayList<>();
        age = new ArrayList<>();

        contactListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, name);
        lvContactList.setAdapter(contactListAdapter);

        viewContact = new Intent(this, ViewContact.class);

        //contactListNumberAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_2, contactNum);
        //lvContactList.setAdapter(contactListNumberAdapter);

        dummyData();
        addContact();
        viewContact();
        backToMenu();

        deleteRecord = getIntent();
        deleteContact();
    }

    public void addContact(){
        btnAddContact.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddContact.class);
            startActivity(intent);
            finish();
        });
    }

    public void backToMenu(){
        btnGoBackBack.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }

    public void dummyData(){

        if (getIntent().hasExtra("id_name")) {
            name.add(getIntent().getStringExtra("id_name"));
        }
        if (getIntent().hasExtra("id_age")) {
            age.add(getIntent().getIntExtra("id_age", 0));
        }
        if (getIntent().hasExtra("id_contactNum")) {
            contactNum.add(getIntent().getStringExtra("id_contactNum"));
        }
        if (getIntent().hasExtra("id_email")) {
            email.add(getIntent().getStringExtra("id_email"));
        }

        contactListAdapter.notifyDataSetChanged();
    }

    public void viewContact(){
        lvContactList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                viewContact.putExtra("view_name", name.get(position));
                viewContact.putExtra("view_contactNum", contactNum.get(position));
                viewContact.putExtra("view_email", email.get(position));
                viewContact.putExtra("view_age", age.get(position));
                viewContact.putExtra("view_position", position);

                startActivity(viewContact);
            }
        });
    }

    public void deleteContact(){
        if(getIntent().hasExtra("delete_at_index")){
            int index = deleteRecord.getIntExtra("delete_at_index", -1);

            if (index >= 0 && index < name.size()) {
                name.remove(index);
                age.remove(index);
                contactNum.remove(index);
                email.remove(index);
                contactListAdapter.notifyDataSetChanged();
            }
        };
    }
}