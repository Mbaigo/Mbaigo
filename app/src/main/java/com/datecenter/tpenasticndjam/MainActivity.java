package com.datecenter.tpenasticndjam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText username, password;
    ConstraintLayout btnLogin;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username =findViewById(R.id.username1);
        password = findViewById(R.id.password1);
//Recuperation du login de la vue xml
        btnLogin = findViewById(R.id.btn_login);

        dbHelper = new DBHelper(this);
       //action sur le bouton login pour demarrer le HomeActivity
       btnLogin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               String user = username.getText().toString();
               String pass = password.getText().toString();

               if (user.equals("")||pass.equals(""))
                   Toast.makeText(getApplicationContext(), "Tous les champs sont obligatoires", Toast.LENGTH_LONG).show();
               else{
                   Boolean checkuserpass = dbHelper.checkUsernamePassword(user,pass);
                   if(checkuserpass==true){
                       Toast.makeText(getApplicationContext(), "Sign In successfull!!", Toast.LENGTH_LONG).show();
                       Intent i = new Intent(MainActivity.this,HomeActivity.class);
                       startActivity(i);
                   }
                   else Toast.makeText(getApplicationContext(), "User invalid",Toast.LENGTH_LONG).show();
               }


           }
       });
        //Recuperation du bouton signUp de la vue xml
        ConstraintLayout btn_Register = findViewById(R.id.btn_register);
        btn_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            }
        });

    }
}