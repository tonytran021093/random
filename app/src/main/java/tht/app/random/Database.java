package tht.app.random;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void QueryData(String sql) {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    public long insertData(String sql) {
        SQLiteDatabase database = getWritableDatabase();
        QueryData(sql);
        long result = -1;
        String queryLastRowInserted = "select last_insert_rowid()";
        Cursor cursor = database .rawQuery(queryLastRowInserted, null);
        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    result = cursor.getLong(0);
                }
            } finally {
                cursor.close();
            }
        }
        return result;
    }

    public Cursor GetData(String sql) {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
