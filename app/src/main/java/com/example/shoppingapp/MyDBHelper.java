package com.example.shoppingapp;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class MyDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "DB1";
    private static final int DATABASE_VERSION = 3;
    private static final String TABLE_CRED = "CredTable";

    // colmns
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";

    public MyDBHelper( Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sdb) {

        // CREATE TABLE CredTable (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, email TEXT, password TEXT)
        sdb.execSQL( "CREATE TABLE " + TABLE_CRED +
                        "(" + KEY_NAME + " TEXT PRIMARY KEY , " +
                        KEY_EMAIL + " TEXT , " +
                        KEY_PASSWORD + " TEXT" + ")"
        );

        sdb.execSQL( "CREATE TABLE " + "CartTable " + "(" + "Outfit TEXT PRIMARY KEY , Value TEXT, Price TEXT, Name TEXT, Name2 TEXT" + ")" );

//        SQLiteDatabase database = this.getWritableDatabase();
//        database.close();

    }

    @Override
    public void onUpgrade(SQLiteDatabase sdb, int i, int i1) {
        sdb.execSQL("DROP TABLE IF EXISTS " + TABLE_CRED);
        onCreate(sdb);
    }

    public boolean addCreds(String name, String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name);
        values.put(KEY_EMAIL, email);
        values.put(KEY_PASSWORD, password);

        long result = db.insert(TABLE_CRED, null, values);
        if(result==-1)
            return false;
        return true;

    }

    public boolean addIntoCart(String outfit, String value, String price, String name, String name2){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Outfit", outfit);
        values.put("Value", value);
        values.put("Price",price);
        values.put("Name",name);
        values.put("Name2",name2);

        long result = db.insert("CartTable", null, values);
        if(result==-1)
            return false;
        return true;
    }

    public boolean ifExists(String outfit){
        SQLiteDatabase db = this.getReadableDatabase();
//        ContentValues values = new ContentValues();

        Cursor cursor = db.rawQuery("SELECT * FROM CartTable WHERE Outfit = ?", new String[]{outfit} );
        if(cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
    }

    public boolean isItemInCart(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM CartTable", null );
        if(cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
    }

    public ArrayList<TableModel> getAllOutfit(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM CartTable", null );

        ArrayList<TableModel> arrayList= new ArrayList<>();

        while(cursor.moveToNext()) {
            TableModel model = new TableModel();
            model.outfit = cursor.getString(0);
            model.value = cursor.getString(1);
            model.price = cursor.getString(2);
            model.name = cursor.getString(3);
            model.name2 = cursor.getString(4);

            arrayList.add(model);
        }

        return arrayList;
    }

    public void deleteTableCart(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("CartTable",null,null);
    }

    public boolean checkUsername( String username){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor =db.rawQuery("SELECT * FROM " + TABLE_CRED + " WHERE " + KEY_NAME + " = ?", new String[]{username});
        if(cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
    }

    public boolean checkUsernamePassword( String username, String password){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor =db.rawQuery("SELECT * FROM " + TABLE_CRED + " WHERE " + KEY_NAME + " = ? and "+ KEY_PASSWORD + " = ? ", new String[]{username,password});
        if(cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
    }

    public boolean checkEmail( String email){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor =db.rawQuery("SELECT * FROM " + TABLE_CRED + " WHERE " + KEY_EMAIL + " = ?", new String[]{email});
        if(cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
    }

    public Boolean checkemailpassword(String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from " + TABLE_CRED + " where " + KEY_EMAIL + " = ? and " + KEY_PASSWORD + " = ?", new String[] {email,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public void deleteUser(String username){
        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete(TABLE_CRED, KEY_NAME + " = " + username,null); ==> doubt .. not working
        db.delete(TABLE_CRED, KEY_NAME + " = ? ", new String[]{username});
    }

    public String getUserName1(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_CRED + " WHERE " + KEY_EMAIL + " = ?",new String[]{email});
        cursor.moveToFirst();

//        cursor.close();
//        return cursor.toString();
        return cursor.getString(0);
    }
}
