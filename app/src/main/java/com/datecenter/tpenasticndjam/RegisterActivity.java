package com.datecenter.tpenasticndjam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText username, email, password, repassword;
    ConstraintLayout signUp;
    TextView singIn;

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username = findViewById(R.id.username);
        email=findViewById(R.id.username);
        password = findViewById(R.id.password);
        repassword = findViewById(R.id.repassword);
        signUp = findViewById(R.id.btn_save_registre);
        singIn =findViewById(R.id.txt_login);

        dbHelper = new DBHelper(this);

        //Recuperation du buton d'enregistrement
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //recuperation du contenu des informations de l'user
                String user = username.getText().toString();
                String mail = email.getText().toString();
                String pass= password.getText().toString();
                String repass = repassword.getText().toString();
                //test des contenues des informations
                if(user.equals("") || mail.equals("")||pass.equals("")||repass.equals(""))
                    Toast.makeText(RegisterActivity.this, "Tous les champs sont obligatoire", Toast.LENGTH_LONG).show();
                else{//tester l'egalié entre le pass et repass
                    if (pass.equals(repass)){
                        //checker si le user n'existe pas dans la bd
                        Boolean checkUser=dbHelper.checkUsername(user);
                        if(checkUser==false){
                            //insertion de notre user dans la bd
                            Boolean insert = dbHelper.insertUser(mail,user,pass);
                            if(insert==true){
                                Toast.makeText(RegisterActivity.this, "User added success!", Toast.LENGTH_LONG).show();

                                startActivity(new Intent(RegisterActivity.this,HomeActivity.class));
                            }
                            else Toast.makeText(RegisterActivity.this, "User add failed", Toast.LENGTH_LONG).show();
                        }
                        else Toast.makeText(RegisterActivity.this, "User already exist, please signIn", Toast.LENGTH_LONG).show();
                    }
                    else Toast.makeText(RegisterActivity.this, "Password not matching", Toast.LENGTH_LONG).show();
                }
            }
        });

        //Recuperation du textView pour la redirection dans la page de login
        singIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });


    }
}