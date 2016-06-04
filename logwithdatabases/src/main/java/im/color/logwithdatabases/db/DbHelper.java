package im.color.logwithdatabases.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by color on 16/5/31.
 */
public class DbHelper extends SQLiteOpenHelper {

    private DbHandler dbHandler;

    public DbHelper(Context context) {
        super (context, "log", null, 1);
        HandlerThread handlerThread = new HandlerThread ("DbHelper");
        handlerThread.start ();
        dbHandler = new DbHandler (handlerThread.getLooper ());
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.beginTransaction ();
        try {
            StringBuilder stringBuilder = new StringBuilder ();
            stringBuilder.append ("CREATE TABLE logs(");
            stringBuilder.append ("_id INTEGER PRIMARY KEY");
            stringBuilder.append (",threadid INTEGER NOT NULL");
            stringBuilder.append (",callfile TEXT NOT NULL");
            stringBuilder.append (",callclass TEXT NOT NULL");
            stringBuilder.append (",callmethod TEXT NOT NULL");
            stringBuilder.append (",linenumber INTEGER NOT NULL");
            stringBuilder.append (",loginfo TEXT NOT NULL");
            stringBuilder.append (",taginfo TEXT NOT NULL");
            stringBuilder.append (",serialno INTEGER");
            stringBuilder.append (",stime TEXT NOT NULL");
            stringBuilder.append (");");
            db.execSQL (stringBuilder.toString ());
            db.setTransactionSuccessful ();
        } finally {
            db.endTransaction ();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public synchronized void insertList(LogBean bean) {
        dbHandler.removeMessages (1);
        Message msg = dbHandler.obtainMessage ();
        msg.what = 3;
        msg.obj = bean;
        dbHandler.sendMessage (msg);
    }

    public void dumpDb() {
        SQLiteDatabase db = this.getWritableDatabase ();

        File exportDir = new File (Environment.getExternalStorageDirectory ().getPath (),
                "/exportLog");

        if (!exportDir.exists ()) {
            exportDir.mkdir ();
        }

        //导出数据的文件名
        File file = new File (exportDir, "log_" + System.currentTimeMillis () + ".txt");
        try {
            BufferedWriter bufferedWriter = new BufferedWriter (new FileWriter (file));

            Cursor cursor = db.rawQuery ("SELECT * FROM logs", null);
            int __id = cursor.getColumnIndex ("_id");
            int _threadid = cursor.getColumnIndex ("threadid");
            int _callfile = cursor.getColumnIndex ("callfile");
            int _callclass = cursor.getColumnIndex ("callclass");
            int _callmethod = cursor.getColumnIndex ("callmethod");
            int _linenumber = cursor.getColumnIndex ("linenumber");
            int _loginfo = cursor.getColumnIndex ("loginfo");
            int _taginfo = cursor.getColumnIndex ("taginfo");
            int _serialno = cursor.getColumnIndex ("serialno");
            int _stime = cursor.getColumnIndex ("stime");

            while (cursor.moveToNext ()) {
                bufferedWriter.write (cursor.getString (__id));
                bufferedWriter.write ("\\|");
                bufferedWriter.write (cursor.getString (_threadid));
                bufferedWriter.write ("\\|");
                bufferedWriter.write (cursor.getString (_callfile));
                bufferedWriter.write ("\\|");
                bufferedWriter.write (cursor.getString (_callclass));
                bufferedWriter.write ("\\|");
                bufferedWriter.write (cursor.getString (_callmethod));
                bufferedWriter.write ("\\|");
                bufferedWriter.write (cursor.getString (_linenumber));
                bufferedWriter.write ("\\|");
                bufferedWriter.write (cursor.getString (_loginfo));
                bufferedWriter.write ("\\|");
                bufferedWriter.write (cursor.getString (_taginfo));
                bufferedWriter.write ("\\|");
                bufferedWriter.write (cursor.getString (_serialno));
                bufferedWriter.write ("\\|");
                bufferedWriter.write (cursor.getString (_stime));
                bufferedWriter.newLine ();
            }

            bufferedWriter.close ();
            cursor.close ();

        } catch (IOException e) {
            e.printStackTrace ();
        }

        db.execSQL ("DELETE FROM logs;");
        db.execSQL ("VACUUM logs;");
    }


    class DbHandler extends Handler {
        private ArrayList<LogBean> logList;

        public DbHandler(Looper looper) {
            super (looper);
            logList = new ArrayList<> ();
        }

        @Override
        public void handleMessage(Message msg) {

            if (3 == msg.what) {
                logList.add ((LogBean) msg.obj);
                if (logList.size () > 1000) {
                    sendEmptyMessage (1);
                } else {
                    sendEmptyMessageDelayed (1, 1000);
                }
            } else if (1 == msg.what) {
                insertDb ();
            }
        }

        private void insertDb() {

            SQLiteDatabase db = getWritableDatabase ();
            db.beginTransaction ();
            try {
                for (LogBean bean : logList) {
                    ContentValues cv = new ContentValues ();
                    cv.put ("threadid", bean.getThreadId ());
                    cv.put ("callfile", bean.getCallFile ());
                    cv.put ("callclass", bean.getCallClass ());
                    cv.put ("callmethod", bean.getCallMethod ());
                    cv.put ("linenumber", bean.getLineNumber ());
                    cv.put ("loginfo", bean.getLogInfo ());
                    cv.put ("taginfo", bean.getTag ());
                    cv.put ("serialno", bean.getSerialno ());
                    cv.put ("stime", bean.getStime ());
                    db.insert ("logs", null, cv);
                }
                db.setTransactionSuccessful ();
            } catch (Exception e) {
                e.printStackTrace ();
            } finally {
                db.endTransaction ();
                logList = new ArrayList<> ();
            }
        }
    }

}
