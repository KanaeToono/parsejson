package com.example.conga.json;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by ConGa on 7/03/2016.
 */
public class GSON extends Activity {
    EditText editTextId;
    EditText editTextName;
    EditText editTextEmail;
    EditText editTextAddress;
    EditText editTextGender;
    EditText editTextMobile;
    EditText editTextOffice;
    EditText editTextHome;
    Button btn_ok;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.writejson);
        editTextId = (EditText) findViewById(R.id.editTextId);
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextAddress = (EditText) findViewById(R.id.editTextAddress);
        editTextGender = (EditText) findViewById(R.id.editTextGender);
        editTextMobile = (EditText) findViewById(R.id.editTextMobile);
        editTextOffice = (EditText) findViewById(R.id.editTextOffice);
        editTextHome = (EditText) findViewById(R.id.editTextHome);
        btn_ok = (Button) findViewById(R.id.btn_ok);

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Contact contact = new Contact();
                Gson gson = new Gson();
                contact.setId(editTextId.getText().toString());
                contact.setName(editTextName.getText().toString());
                contact.setEmail(editTextEmail.getText().toString());
                contact.setAddress(editTextAddress.getText().toString());
                contact.setGender(editTextGender.getText().toString());
                contact.setPhone_mobile(editTextMobile.getText().toString());
                contact.setPhone_office(editTextOffice.getText().toString());
                contact.setPhone_home(editTextHome.getText().toString());
                String json = gson.toJson(contact);
                try {
                    FileWriter write = new FileWriter("H:\\file.json");
                    write.write(json);
                    write.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Toast.makeText(getApplicationContext(), json, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
