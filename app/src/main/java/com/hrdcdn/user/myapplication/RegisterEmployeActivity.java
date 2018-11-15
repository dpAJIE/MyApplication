package com.hrdcdn.user.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterEmployeActivity extends AppCompatActivity {

    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_employe);

        btnRegister = (Button) findViewById(R.id.btn_register);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText unitUsaha = (EditText) findViewById(R.id.unit_usaha);
                EditText namaCalon = (EditText) findViewById(R.id.nama_calon);
                EditText namaPosisi = (EditText) findViewById(R.id.nama_posisi);

                Intent intent = new Intent(RegisterEmployeActivity.this, BerkasActivity.class);
                intent.putExtra("unit_usaha",unitUsaha.getText().toString());
                intent.putExtra("nama_calon",namaCalon.getText().toString());
                intent.putExtra("nama_posisi",namaPosisi.getText().toString());

                startActivity(intent);
            }
        });
    }

}
