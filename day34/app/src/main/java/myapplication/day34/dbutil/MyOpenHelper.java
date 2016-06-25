package myapplication.day34.dbutil;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/6/25.
 */
public class MyOpenHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "school8.db";
    private static final int VERSION = 1;
    public MyOpenHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 创建三个表：student course score
        String sql = "CREATE TABLE IF NOT EXISTS [favorite] (  [_id] INTEGER PRIMARY KEY AUTOINCREMENT, [title] , [keyword],[data] INTEGER);";
        db.execSQL(sql);
        String sql2 = "CREATE TABLE IF NOT EXISTS [cookies] (  [_id] INTEGER PRIMARY KEY AUTOINCREMENT, [title] , [keyword], [data]INTEGER);";
        db.execSQL(sql);

    }
    // 数据库升级时调用
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
    }

}
