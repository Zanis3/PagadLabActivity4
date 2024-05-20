package com.example.pagadlabactivity4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddContact extends AppCompatActivity {

    Button btnSubmitContact, btnBack, btnClear;
    EditText txtName, txtAge, txtEmail, txtContactNum;
    String name, contactNum, email;
    int age;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_contact);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txtName = findViewById(R.id.txtName);
        txtAge = findViewById(R.id.txtAge);
        txtContactNum = findViewById(R.id.txtContactNum);
        txtEmail = findViewById(R.id.txtEmail);

        btnSubmitContact = findViewById(R.id.btnSubmitContact);
        btnBack = findViewById(R.id.btnBack);
        btnClear = findViewById(R.id.btnClear);

        intent = new Intent(this, Contacts.class);

        submitContact();
        returnToList();
        clearText();
    }

    public void submitContact(){

        btnSubmitContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = txtName.getText().toString();
                String strAge = txtAge.getText().toString();
                contactNum = txtContactNum.getText().toString().replaceAll("\\s", "");;
                email = txtEmail.getText().toString();

                if(name.isEmpty() || txtAge.getText().toString().isEmpty() || contactNum.isEmpty() || email.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please fill up all fields to continue.",
                            Toast.LENGTH_SHORT).show();
                }
                else{
                    age = Integer.parseInt(strAge);
                    int check = 0;
                    if(age < 0 || age > 116){
                        Toast.makeText(getApplicationContext(),"Age is not acceptable. Please try again.",
                                Toast.LENGTH_SHORT).show();
                    }
                    else{
                        check+=1;
                    }

                    if(contactNum.length() != 11){
                        Toast.makeText(getApplicationContext(),"Please follow the contact number format and try again.",
                                Toast.LENGTH_SHORT).show();
                    }
                    else{
                        check+=1;
                    }

                    if(check == 2){
                        Toast.makeText(getApplicationContext(),"Contact Added!",
                                Toast.LENGTH_SHORT).show();

                        intent.putExtra("id_name", name);
                        intent.putExtra("id_contactNum", contactNum);
                        intent.putExtra("id_email", email);
                        intent.putExtra("id_age", age);

                        startActivity(intent);
                        finish();
                    }
                }
            }
        });
    }

    public void returnToList(){
        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(this, Contacts.class);
            startActivity(intent);
            finish();
        });
    }

    public void clearText(){
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtName.setText("");
                txtAge.setText("");
                txtEmail.setText("");
                txtContactNum.setText("");
            }
        });
    }
}