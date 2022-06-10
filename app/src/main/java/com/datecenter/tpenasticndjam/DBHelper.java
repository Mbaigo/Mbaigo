package com.datecenter.tpenasticndjam;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    //database name
    public final  static  String DBNAME = "mybase.db";
    //constructor
    public DBHelper( Context context) {
        super(context, DBNAME, null, 1);
    }
//La premier methode a etre applee lors de la creation d'une instance de DBHelper
    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users(username TEXT primary key, email TEXT, password TEXT)");
        MyDB.execSQL("create table student(id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT NOT NULL, lastName TEXT NOT NULL,birthDay TEXT, isNormal boolean)");
    }

    //Appelee lorsque la version de la base de donnees a changee
    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
    }
//insertion d'un utilisateur
    public boolean insertUser(String email, String username, String password){
        SQLiteDatabase MyDB =this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result =MyDB.insert("users", null, contentValues);
        if(result==-1)
        return false;
        else  return true;
    }
    //insert student
    public boolean insertStudent(String name, String lastName, String birthDay){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nom", name);
        contentValues.put("prenom",lastName);
        contentValues.put("dateNaiss", birthDay);
        long result=MyDB.insert("student",null, contentValues);
        if(result==-1) return false;
        else return true;
    }

    //add student
    public boolean addStudent(StudentModel studentModel){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", studentModel.getNom());
        contentValues.put("lastName",studentModel.getPrenom());
        contentValues.put("birthDay", studentModel.getDateNaiss());
        contentValues.put("isNormal", studentModel.isNoraml());
        long result=MyDB.insert("student",null, contentValues);
        if(result==-1) return false;
        else return true;
    }
    //checking username if exist in the databse or not
    public Boolean checkUsername(String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor =MyDB.rawQuery("select * from users where username =?", new String[]{username});
        if(cursor.getCount()>0) return  true;
        else return false;
    }

    //checking username and password if exist in the databse or not
    public Boolean checkUsernamePassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from users where username= ? and password = ?",new String[]{username, password});
        if(cursor.getCount()>0) return  true;
        else return false;
    }
}
