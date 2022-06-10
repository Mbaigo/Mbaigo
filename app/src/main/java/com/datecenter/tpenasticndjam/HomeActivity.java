package com.datecenter.tpenasticndjam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.Date;

public class HomeActivity extends AppCompatActivity {

     EditText name, lastName, birthDay;
     Switch isNormal;
     Button btn_add, btn_view;
     ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //recuperation des zones de texte
        name= findViewById(R.id.et_name);
        lastName=findViewById(R.id.et_lastName);
        birthDay=findViewById(R.id.et_birthDay);
        isNormal=findViewById(R.id.status);
        listView=findViewById(R.id.lv_student);
        //recupreation des butons
        btn_add = findViewById(R.id.btn_add);
        btn_view = findViewById(R.id.btn_view_list);
        //Action sur les butons
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StudentModel studentModel;
                try {
                     studentModel =new StudentModel(-1, name.getText().toString(),lastName.getText().toString(),birthDay.getText().toString(),isNormal.isChecked());
                    Toast.makeText(HomeActivity.this, studentModel.toString(), Toast.LENGTH_SHORT).show();

                } catch (Exception e){
                    Toast.makeText(HomeActivity.this, "Erreur lors de la creation", Toast.LENGTH_SHORT).show();
                    studentModel = new StudentModel(-1,"error","err","err",false);
                }
                DBHelper dbHelper = new DBHelper(HomeActivity.this);
                boolean isAdded = dbHelper.addStudent(studentModel);
                Toast.makeText(HomeActivity.this, "Success" +isAdded, Toast.LENGTH_SHORT).show();
            }
        });

        btn_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeActivity.this, "ListView buton clicked", Toast.LENGTH_LONG).show();
            }
        });
    }
}