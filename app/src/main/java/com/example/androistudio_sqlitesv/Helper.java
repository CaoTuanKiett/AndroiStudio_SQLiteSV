package com.example.androistudio_sqlitesv;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Helper extends SQLiteOpenHelper {
    public Helper(Context context) {
        super(context , "ManagerComputer.db",null , 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table category(categoryId text primary key ,nameCategory text)");
        sqLiteDatabase.execSQL("create table computer(idComputer text primary key , nameComputer text ,price text ," +
                " categoryId text constraint categoryId references category(categoryId))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public Boolean insertValueCategory(String maKhoa , String tenKhoa){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("maKhoa" , maKhoa);
        contentValues.put("tenKhoa" , tenKhoa);
        long result = db.insert("khoa" ,null ,contentValues);
        if(result == - 1){
            return false ;
        }else{
            return true ;
        }
    }

    public Boolean insertValuecomputer(String maSV , String tenSV ,String lop , String maKhoa){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("maSV" , maSV);
        contentValues.put("tenSV" , tenSV);
        contentValues.put("LopHP" , lop);
        contentValues.put("maKhoa" , maKhoa);
        long result = db.insert("sinhvien" ,null ,contentValues);
        if(result == - 1){
            return false ;
        }else{
            return true ;
        }
    }

    public Boolean updateValuecomputer(String maSV , String tenSV ,String lop , String maKhoa){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenSV" , tenSV);
        contentValues.put("lop" , lop);
        contentValues.put("maKhoa" , maKhoa);
        Cursor cursor = db.rawQuery("select * from sinhvien where maSV=?",new String[]{maSV});

        if(cursor.getCount() > 0){
            long result = db.update("sinhvien" ,contentValues ,"maSV=?" , new String[]{maSV});
            if(result == - 1){
                return false ;
            }else{
                return true ;
            }}else {
            return false ;
        }
    }

    public Boolean deleteValuecomputer(String maSV ){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from sinhvien where maSV=?",new String[]{maSV});

        if(cursor.getCount() > 0){
            long result = db.delete("sinhvien"  ,"maSV=?" , new String[]{maSV});
            if(result == - 1){
                return false ;
            }else{
                return true ;
            }}else {
            return false ;
        }
    }

    public Cursor getvalueComputer(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from sinhvien ",null);
        return cursor ;
    }
}
