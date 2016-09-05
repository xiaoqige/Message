package com.example.nef.message;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/1.
 */
public class DBmanager{

    private static File dbFile;

    public DBmanager() {

    }
//复制
  public void copy(Context context) {

        try {
            InputStream input = context.getAssets().open("sms.db");
            File file = context.getExternalFilesDir("");
            dbFile = new File(file, "sms.db");
            if (dbFile.exists()) {
                return;
            }
           file.mkdirs();
            OutputStream out = new FileOutputStream(dbFile);
            int len = 0;
            while ((len = input.read()) != -1) {
                out.write(len);
            }
            out.flush();
            out.close();
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //读
    public List<String> tiTle(){
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(dbFile,null);
        List<String> list = new ArrayList<>();
        String sql = "select * from ZSMSCATEGORY;";
        Cursor cursor = db.rawQuery(sql,null);
        while (cursor.moveToNext()){
            String s = cursor.getString(2);
            list.add(s);
        }
        cursor.close();
        db.close();
        return list;
    }
//zid
    public List<Integer> zid(){
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(dbFile,null);
        List<Integer> list = new ArrayList<>();
        String sql = "select * from ZSMSCATEGORY;";
        Cursor cursor = db.rawQuery(sql,null);
        while (cursor.moveToNext()){
            list.add(cursor.getInt(1));
        }
        cursor.close();
        db.close();
        return list;
    }

    public List<String> smscontent(int zid){
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(dbFile,null);
            List<String> list = new ArrayList<>();
            String sql = "select ZSMSCONTENT.ZCONTENT_ID,ZSMSCONTENT.ZCONTENT from ZSMSCONTENT,ZSMSRELATION WHERE ZSMSCONTENT.ZCONTENT_ID=ZSMSRELATION.ZCONTENT  AND ZCATEGORY_ID='%d';";
            String sesql = String.format(sql,zid);
            Cursor cursor = db.rawQuery(sesql,null);
            while (cursor.moveToNext()){
                String s = cursor.getString(1);
                list.add(s);
            }
            cursor.close();
            db.close();
        return list;
    }
    //收藏
    public void insert(int ZCATEGORY_ID, int ZCONTENT){
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(dbFile,null);
        String sql = "insert into ZSMSRELATION(ZCATEGORY_ID,ZCONTENT) values (%d,%d);";
        String insql = String.format(sql,ZCATEGORY_ID,ZCONTENT);
        db.execSQL(insql);
        db.close();
    }
    public  int collect(){
        int s=0;
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(dbFile,null);
        String sql = "select * from ZSMSCATEGORY where ZNAME = '收藏';";
        Cursor cursor = db.rawQuery(sql,null);
        if (cursor.moveToNext()){
           s = cursor.getInt(1);
        }
        cursor.close();
        db.close();
        return s;
    }

//zcontent_id
public int id(String ZCONTENT){
    SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(dbFile,null);
    int zcontent_id=0;
    String sql = "select ZCONTENT_ID from ZSMSCONTENT where ZCONTENT='%s';";
    String sesql = String.format(sql,ZCONTENT);
    Cursor cursor = db.rawQuery(sesql,null);
    while (cursor.moveToNext()){
        zcontent_id = cursor.getInt(0);
    }
    db.close();
    cursor.close();
    return zcontent_id;
}

}
