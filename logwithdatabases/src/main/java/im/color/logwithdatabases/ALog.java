package im.color.logwithdatabases;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import java.util.Locale;

import im.color.logwithdatabases.db.DbHelper;
import im.color.logwithdatabases.db.LogBean;

/**
 * Android Log的包装类,免去一些麻烦
 */
public class ALog {
    private static String PRE = "&";

    public static final String space = "----------------------------------------------------------------------------------------------------";

    private static boolean LOGV = false;
    private static boolean LOGD = false;
    private static boolean LOGI = false;
    private static boolean LOGW = false;
    private static boolean LOGE = false;
    private static boolean LOGA = false;

    private static DbHelper dbHelper;

    public static void setDbHelper(Context ctx) {
        if (null == dbHelper) {
            dbHelper = new DbHelper (ctx);
        }
    }

    public static void setDebugMode(boolean flag) {
        LOGV = flag;
        LOGD = flag;
        LOGI = flag;
        LOGW = flag;
        LOGE = flag;
    }

    public static void v(String tag, String format, Object... args) {
        v (false, tag, format, args);
    }

    public static void d(String tag, String format, Object... args) {
        d (false, tag, format, args);
    }

    public static void i(String tag, String format, Object... args) {
        i (false, tag, format, args);
    }

    public static void w(String tag, String format, Object... args) {
        w (false, tag, format, args);
    }

    public static void e(String tag, String format, Object... args) {
        e (false, tag, format, args);
    }

    public static void wtf(String tag, String format, Object... args) {
        wtf (false, tag, format, args);
    }

    //======================================================================

    public static void v(String format, Object... args) {
        v (false, "", format, args);
    }

    public static void d(String format, Object... args) {
        d (false, "", format, args);
    }

    public static void i(String format, Object... args) {
        i (false, "", format, args);
    }

    public static void w(String format, Object... args) {
        w (false, "", format, args);
    }

    public static void e(String format, Object... args) {
        e (false, "", format, args);
    }

    public static void wtf(String format, Object... args) {
        wtf (false, "", format, args);
    }

    //======================================================================

    public static void v(boolean insertDb, String format, Object... args) {
        v (insertDb, "", format, args);
    }

    public static void d(boolean insertDb, String format, Object... args) {
        d (insertDb, "", format, args);
    }

    public static void i(boolean insertDb, String format, Object... args) {
        i (insertDb, "", format, args);
    }

    public static void w(boolean insertDb, String format, Object... args) {
        w (insertDb, "", format, args);
    }

    public static void e(boolean insertDb, String format, Object... args) {
        e (insertDb, "", format, args);
    }

    public static void wtf(boolean insertDb, String format, Object... args) {
        wtf (insertDb, "", format, args);
    }

    //======================================================================

    public static void v(boolean insertDb, String tag, String format, Object... args) {
        if (LOGV) {
            LogInfo logInfo = buildMessage (insertDb, tag, format, args, Log.VERBOSE);
            for (String info : logInfo.getInfo ()) {
                Log.v (logInfo.getTag (), info);
            }
        }
    }

    public static void d(boolean insertDb, String tag, String format, Object... args) {
        if (LOGD) {
            LogInfo logInfo = buildMessage (insertDb, tag, format, args, Log.DEBUG);
            for (String info : logInfo.getInfo ()) {
                Log.d (logInfo.getTag (), info);
            }
        }
    }

    public static void i(boolean insertDb, String tag, String format, Object... args) {
        if (LOGI) {
            LogInfo logInfo = buildMessage (insertDb, tag, format, args, Log.INFO);
            for (String info : logInfo.getInfo ()) {
                Log.i (logInfo.getTag (), info);
            }
        }
    }

    public static void w(boolean insertDb, String tag, String format, Object... args) {
        if (LOGW) {
            LogInfo logInfo = buildMessage (insertDb, tag, format, args, Log.WARN);
            for (String info : logInfo.getInfo ()) {
                Log.w (logInfo.getTag (), info);
            }
        }
    }

    public static void e(boolean insertDb, String tag, String format, Object... args) {
        if (LOGE) {
            LogInfo logInfo = buildMessage (insertDb, tag, format, args, Log.ERROR);
            for (String info : logInfo.getInfo ()) {
                Log.e (logInfo.getTag (), info);
            }
        }
    }

    public static void wtf(boolean insertDb, String tag, String format, Object... args) {
        if (LOGA) {
            LogInfo logInfo = buildMessage (insertDb, tag, format, args, Log.ERROR);
            for (String info : logInfo.getInfo ()) {
                Log.wtf (logInfo.getTag (), info);
            }
        }
    }


    private static LogInfo buildMessage(boolean insertDb, String tag, String format, Object[] args, int logLevel) {
        try {
            String msg = (args == null || args.length == 0) ?
                    (TextUtils.isEmpty (format) ? "--->format null<---" : format)
                    : String.format (Locale.US, format, args);

            StackTraceElement[] trace = new Throwable ().fillInStackTrace ().getStackTrace ();
            String caller = "";
            String callingClass = "";
            String callFile = "";
            int lineNumber = 0;
            for (int i = 2; i < trace.length; i++) {
                Class<?> clazz = trace[i].getClass ();
                if (!clazz.equals (ALog.class)) {
                    callingClass = trace[i].getClassName ();
                    callingClass = callingClass.substring (callingClass
                            .lastIndexOf ('.') + 1);
                    caller = trace[i].getMethodName ();
                    callFile = trace[i].getFileName ();
                    lineNumber = trace[i].getLineNumber ();
                    break;
                }
            }

            long threadId = Thread.currentThread ().getId ();
            String method = String.format (Locale.US, "[%03d] %s.%s(%s:%d)"
                    , threadId, callingClass, caller, callFile, lineNumber);

            //============================================
            if (insertDb && null != dbHelper) {
                LogBean bean = new LogBean ();
                bean.setCallClass (callingClass);
                bean.setCallFile (callFile);
                bean.setCallMethod (caller);
                bean.setLineNumber (lineNumber);
                bean.setTag (tag);
                bean.setLogInfo (msg);
                bean.setThreadId (Thread.currentThread ().getId ());
                bean.setLoglevel (logLevel);
                insert (bean);
            }

            //==============================================
            String methodString = formartLength (method, 100);

            StringBuilder stringBuilder = new StringBuilder ();
            int i = msg.length () / 3800;

            String[] message = new String[i + 1];
            while (i > -1) {
                stringBuilder.append (methodString);
                stringBuilder.append (">");
                stringBuilder.append (msg);
                message[i] = String.format (Locale.US, "%s> %s", methodString, msg);
                i--;
            }

            LogInfo logInfo = new LogInfo (formartLength (PRE + tag, 30), message);

            return logInfo;
        } catch (Exception e) {
            e.printStackTrace ();
        }
        return new LogInfo ("error", new String[]{"error"});
    }

    private static void insert(LogBean bean) {
        dbHelper.insertList (bean);
    }

    private static String formartLength(String src, int len) {
        StringBuilder sb = new StringBuilder ();
        if (null != src && src.length () >= len) {
            sb.append (src);
        } else {
            sb.append (src);
            sb.append (space.substring (0, len - src.length ()));
        }
        return sb.toString ();
    }

    public static void dumpDb() {
        if (null != dbHelper) {
            dbHelper.dumpDb ();
        }
    }

    static class LogInfo {
        String tag;
        String[] info;

        LogInfo(String tag, String[] info) {
            this.info = info;
            this.tag = tag;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String[] getInfo() {
            return info;
        }

        public void setInfo(String[] info) {
            this.info = info;
        }
    }


}