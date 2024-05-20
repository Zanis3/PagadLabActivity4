package com.example.pagadlabactivity4;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ViewContact extends AppCompatActivity {

    ImageButton btnCall, btnDelete, btnGoBack;
    TextView lblContactName, lblContactNumView, lblViewAge, lblViewEmail;
    Intent intent, viewContact, deleteRecord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_contact);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnCall = findViewById(R.id.btnCall);
        btnDelete = findViewById(R.id.btnDelete);
        btnGoBack = findViewById(R.id.btnGoBack);

        lblContactName = findViewById(R.id.lblContactName);
        lblContactNumView = findViewById(R.id.lblContactNumView);
        lblViewAge = findViewById(R.id.lblViewAge);
        lblViewEmail = findViewById(R.id.lblViewEmail);

        deleteRecord = new Intent(this, Contacts.class);

        intent = getIntent();
        viewContact = getIntent();
        lblContactName.setText(intent.getStringExtra("view_name"));
        lblContactNumView.setText(intent.getStringExtra("view_contactNum"));
        lblViewAge.setText(String.valueOf(viewContact.getIntExtra("view_age", 0)));
        lblViewEmail.setText(intent.getStringExtra("view_email"));

        call();
        deleteContact();
        goBack();
    }

    public void call(){
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Calling " + intent.getStringExtra("view_name"),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void deleteContact(){
        btnDelete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());

                builder.setTitle("Delete Contact");
                builder.setMessage("Are you sure you want to delete this contact?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int index = viewContact.getIntExtra("view_position", -1);
                        deleteRecord.putExtra("delete_at_index", index);
                        deleteRecord.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(deleteRecord);
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    public void goBack(){
        btnGoBack.setOnClickListener(v -> {
            Intent intent = new Intent(this, Contacts.class);
            startActivity(intent);
            finish();
        });
    }
}