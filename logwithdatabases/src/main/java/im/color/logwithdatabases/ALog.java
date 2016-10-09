package im.color.logwithdatabases;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;

import im.color.logwithdatabases.db.DbHelper;
import im.color.logwithdatabases.db.LogBean;

/**
 *
 */
public class ALog {
    private static String PRE = "&";

    public static final String space = "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━";

    public static final String wtfSpace = "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━";
    public static final String wtfSpaceUp = "┏";
    public static final String wtfSpaceDown = "┗";

    public static boolean LOGV = false;
    public static boolean LOGD = false;
    public static boolean LOGI = false;
    public static boolean LOGW = false;
    public static boolean LOGE = false;
    public static boolean LOGA = false;
    public static boolean isDebug = false;

    private static DbHelper dbHelper;

    public static void setDbHelper(Context ctx) {
        if (null == dbHelper) {
            dbHelper = new DbHelper(ctx);
        }
    }

    public static void setDebugMode(boolean flag) {
        LOGV = flag;
        LOGD = flag;
        LOGI = flag;
        LOGW = flag;
        LOGE = flag;
        isDebug = flag;
    }

    public static void v(String tag, String format, Object... args) {
        if (LOGV) {
            if (TextUtils.isEmpty(tag)) {
                tag = "";
            }
            List<String> list = buildMessage(false, tag, format, args, Log.VERBOSE);
            String callClass = list.get(0);
            for (int i = 1; i < list.size(); i++) {
                String msg = list.get(i);
                Log.v(formartEnd(PRE + tag, callClass, 30), (i > 1 ? "    " : "") + msg);
            }
        }
    }

    public static void v(String format, Object... args) {
        if (LOGV) {
            List<String> list = buildMessage(false, "", format, args, Log.VERBOSE);
            String callClass = list.get(0);
            for (int i = 1; i < list.size(); i++) {
                String msg = list.get(i);
                Log.v(formartEnd(PRE, callClass, 30), (i > 1 ? "    " : "") + msg);
            }
        }
    }

    public static void v(Throwable throwable, String tag, String format, Object... args) {
        if (LOGV) {
            if (TextUtils.isEmpty(tag)) {
                tag = "";
            }
            List<String> list = buildMessage(false, tag, format, args, Log.VERBOSE);
            String callClass = list.get(0);
            for (int i = 1; i < list.size(); i++) {
                String msg = list.get(i);
                if (null == throwable) {
                    Log.v(formartEnd(PRE + tag, callClass, 30), (i > 1 ? "    " : "") + msg);
                } else {
                    Log.v(formartEnd(PRE + tag, callClass, 30), (i > 1 ? "    " : "") + msg, throwable);
                }
            }
        }
    }

    public static void v(boolean insertDb, Throwable throwable, String tag, String format, Object... args) {
        if (LOGV) {
            if (TextUtils.isEmpty(tag)) {
                tag = "";
            }
            List<String> list = buildMessage(insertDb, tag, format, args, Log.VERBOSE);
            String callClass = list.get(0);
            for (int i = 1; i < list.size(); i++) {
                String msg = list.get(i);
                if (null == throwable) {
                    Log.v(formartEnd(PRE + tag, callClass, 30), (i > 1 ? "    " : "") + msg);
                } else {
                    Log.v(formartEnd(PRE + tag, callClass, 30), (i > 1 ? "    " : "") + msg, throwable);
                }
            }
        }
    }


    public static void d(String tag, String format, Object... args) {
        if (LOGV) {
            if (TextUtils.isEmpty(tag)) {
                tag = "";
            }
            List<String> list = buildMessage(false, tag, format, args, Log.DEBUG);
            String callClass = list.get(0);
            for (int i = 1; i < list.size(); i++) {
                String msg = list.get(i);
                Log.d(formartEnd(PRE + tag, callClass, 30), (i > 1 ? "    " : "") + msg);
            }
        }
    }

    public static void d(String format, Object... args) {
        if (LOGV) {
            List<String> list = buildMessage(false, "", format, args, Log.DEBUG);
            String callClass = list.get(0);
            for (int i = 1; i < list.size(); i++) {
                String msg = list.get(i);
                Log.d(formartEnd(PRE, callClass, 30), (i > 1 ? "    " : "") + msg);
            }
        }
    }

    public static void d(Throwable throwable, String tag, String format, Object... args) {
        if (LOGD) {
            if (TextUtils.isEmpty(tag)) {
                tag = "";
            }
            List<String> list = buildMessage(false, tag, format, args, Log.DEBUG);
            String callClass = list.get(0);
            for (int i = 1; i < list.size(); i++) {
                String msg = list.get(i);
                if (null == throwable) {
                    Log.d(formartEnd(PRE + tag, callClass, 30), (i > 1 ? "    " : "") + msg);
                } else {
                    Log.d(formartEnd(PRE + tag, callClass, 30), (i > 1 ? "    " : "") + msg, throwable);
                }
            }
        }
    }

    public static void d(boolean insertDb, Throwable throwable, String tag, String format, Object... args) {
        if (LOGD) {
            if (TextUtils.isEmpty(tag)) {
                tag = "";
            }
            List<String> list = buildMessage(insertDb, tag, format, args, Log.DEBUG);
            String callClass = list.get(0);
            for (int i = 1; i < list.size(); i++) {
                String msg = list.get(i);
                if (null == throwable) {
                    Log.d(formartEnd(PRE + tag, callClass, 30), (i > 1 ? "    " : "") + msg);
                } else {
                    Log.d(formartEnd(PRE + tag, callClass, 30), (i > 1 ? "    " : "") + msg, throwable);
                }
            }
        }
    }


    public static void i(String tag, String format, Object... args) {
        if (LOGI) {
            if (TextUtils.isEmpty(tag)) {
                tag = "";
            }
            List<String> list = buildMessage(false, tag, format, args, Log.INFO);
            String callClass = list.get(0);
            for (int i = 1; i < list.size(); i++) {
                String msg = list.get(i);
                Log.i(formartEnd(PRE + tag, callClass, 30), (i > 1 ? "    " : "") + msg);
            }
        }
    }

    public static void i(String format, Object... args) {
        if (LOGI) {
            List<String> list = buildMessage(false, "", format, args, Log.INFO);
            String callClass = list.get(0);
            for (int i = 1; i < list.size(); i++) {
                String msg = list.get(i);
                Log.i(formartEnd(PRE, callClass, 30), (i > 1 ? "    " : "") + msg);
            }
        }
    }

    public static void i(Throwable throwable, String tag, String format, Object... args) {
        if (LOGI) {
            if (TextUtils.isEmpty(tag)) {
                tag = "";
            }
            List<String> list = buildMessage(false, "", format, args, Log.INFO);
            String callClass = list.get(0);
            for (int i = 1; i < list.size(); i++) {
                String msg = list.get(i);
                if (null == throwable) {
                    Log.i(formartEnd(PRE + tag, callClass, 30), (i > 1 ? "    " : "") + msg, throwable);
                } else {
                    Log.i(formartEnd(PRE + tag, callClass, 30), (i > 1 ? "    " : "") + msg, throwable);
                }
            }
        }
    }

    public static void i(boolean insertDb, Throwable throwable, String tag, String format, Object... args) {
        if (LOGI) {
            if (TextUtils.isEmpty(tag)) {
                tag = "";
            }
            List<String> list = buildMessage(insertDb, "", format, args, Log.INFO);
            String callClass = list.get(0);
            for (int i = 1; i < list.size(); i++) {
                String msg = list.get(i);
                if (null == throwable) {
                    Log.i(formartEnd(PRE + tag, callClass, 30), (i > 1 ? "    " : "") + msg, throwable);
                } else {
                    Log.i(formartEnd(PRE + tag, callClass, 30), (i > 1 ? "    " : "") + msg, throwable);
                }
            }
        }
    }


    public static void w(String tag, String format, Object... args) {
        if (LOGI) {
            if (TextUtils.isEmpty(tag)) {
                tag = "";
            }
            List<String> list = buildMessage(false, tag, format, args, Log.WARN);
            String callClass = list.get(0);
            for (int i = 1; i < list.size(); i++) {
                String msg = list.get(i);
                Log.w(formartEnd(PRE + tag, callClass, 30), (i > 1 ? "    " : "") + msg);
            }
        }
    }

    public static void w(String format, Object... args) {
        if (LOGI) {
            List<String> list = buildMessage(false, "", format, args, Log.WARN);
            String callClass = list.get(0);
            for (int i = 1; i < list.size(); i++) {
                String msg = list.get(i);
                Log.w(formartEnd(PRE, callClass, 30), (i > 1 ? "    " : "") + msg);
            }
        }
    }

    public static void w(Throwable throwable, String tag, String format, Object... args) {
        if (LOGW) {
            if (TextUtils.isEmpty(tag)) {
                tag = "";
            }
            List<String> list = buildMessage(false, tag, format, args, Log.WARN);
            String callClass = list.get(0);
            for (int i = 1; i < list.size(); i++) {
                String msg = list.get(i);
                if (null == throwable) {
                    Log.w(formartEnd(PRE + tag, callClass, 30), (i > 1 ? "    " : "") + msg);
                } else {
                    Log.w(formartEnd(PRE + tag, callClass, 30), (i > 1 ? "    " : "") + msg, throwable);
                }
            }
        }
    }

    public static void w(boolean insertDb, Throwable throwable, String tag, String format, Object... args) {
        if (LOGW) {
            if (TextUtils.isEmpty(tag)) {
                tag = "";
            }
            List<String> list = buildMessage(insertDb, tag, format, args, Log.WARN);
            String callClass = list.get(0);
            for (int i = 1; i < list.size(); i++) {
                String msg = list.get(i);
                if (null == throwable) {
                    Log.w(formartEnd(PRE + tag, callClass, 30), (i > 1 ? "    " : "") + msg);
                } else {
                    Log.w(formartEnd(PRE + tag, callClass, 30), (i > 1 ? "    " : "") + msg, throwable);
                }
            }
        }
    }


    public static void e(String tag, String format, Object... args) {
        if (LOGE) {
            if (TextUtils.isEmpty(tag)) {
                tag = "";
            }
            List<String> list = buildMessage(false, tag, format, args, Log.ERROR);
            String callClass = list.get(0);
            for (int i = 1; i < list.size(); i++) {
                String msg = list.get(i);
                Log.e(formartEnd(PRE + tag, callClass, 30), (i > 1 ? "    " : "") + msg);
            }
        }
    }

    public static void e(String format, Object... args) {
        if (LOGE) {
            List<String> list = buildMessage(false, "", format, args, Log.ERROR);
            String callClass = list.get(0);
            for (int i = 1; i < list.size(); i++) {
                String msg = list.get(i);
                Log.e(formartEnd(PRE, callClass, 30), (i > 1 ? "    " : "") + msg);
            }
        }
    }

    public static void e(Throwable throwable, String tag, String format, Object... args) {
        if (LOGE) {
            List<String> list = buildMessage(false, tag, format, args, Log.ERROR);
            String callClass = list.get(0);
            for (int i = 1; i < list.size(); i++) {
                String msg = list.get(i);
                if (null == throwable) {
                    Log.e(formartEnd(PRE + tag, callClass, 30), (i > 1 ? "    " : "") + msg);
                } else {
                    Log.e(formartEnd(PRE + tag, callClass, 30), (i > 1 ? "    " : "") + msg, throwable);
                }
            }
        }
    }

    public static void e(boolean insertDb, Throwable throwable, String tag, String format, Object... args) {
        if (LOGE) {
            List<String> list = buildMessage(insertDb, tag, format, args, Log.ERROR);
            String callClass = list.get(0);
            for (int i = 1; i < list.size(); i++) {
                String msg = list.get(i);
                if (null == throwable) {
                    Log.e(formartEnd(PRE + tag, callClass, 30), (i > 1 ? "    " : "") + msg);
                } else {
                    Log.e(formartEnd(PRE + tag, callClass, 30), (i > 1 ? "    " : "") + msg, throwable);
                }
            }
        }
    }


    public static void wtf(String format, Object... args) {
        if (isDebug) {
            List<String> list = buildMessage(false, "", format, args, 8);
            String callClass = list.get(0);
            Log.wtf(formartEnd(PRE, callClass, 30), wtfSpaceUp + wtfSpace);
            for (int i = 1; i < list.size(); i++) {
                String msg = list.get(i);
                Log.wtf(formartEnd(PRE, callClass, 30), "┃" + (i > 1 ? "   " : "") + msg);
            }
            Log.wtf(formartEnd(PRE, callClass, 30), wtfSpaceDown + wtfSpace);
        }
    }

    public static void wtf(String tag, String format, Object... args) {
        if (isDebug) {
            if (TextUtils.isEmpty(tag)) {
                tag = "";
            }
            List<String> list = buildMessage(false, tag, format, args, 8);
            String callClass = list.get(0);
            Log.wtf(formartEnd(PRE + tag, callClass, 30), wtfSpaceUp + wtfSpace);
            for (int i = 1; i < list.size(); i++) {
                String msg = list.get(i);

                Log.wtf(formartEnd(PRE + tag, callClass, 30), "┃" + (i > 1 ? "    " : "") + msg);
            }
            Log.wtf(formartEnd(PRE + tag, callClass, 30), wtfSpaceDown + wtfSpace);
        }
    }

    public static void wtf(Throwable throwable, String tag, String format, Object... args) {
        if (isDebug) {
            if (TextUtils.isEmpty(tag)) {
                tag = "";
            }
            List<String> list = buildMessage(false, tag, format, args, 8);
            String callClass = list.get(0);
            Log.wtf(formartEnd(PRE, callClass, 30), wtfSpaceUp + wtfSpace);
            for (int i = 1; i < list.size(); i++) {
                String msg = list.get(i);
                if (null == throwable) {
                    Log.wtf(formartEnd(PRE, callClass, 30), "┃" + (i > 1 ? "   " : "") + msg);
                } else {
                    Log.wtf(formartEnd(PRE, callClass, 30), "┃" + (i > 1 ? "   " : "") + msg, throwable);
                }
            }
            Log.wtf(formartEnd(PRE, callClass, 30), wtfSpaceDown + wtfSpace);
        }
    }

    public static void wtf(boolean insertDb, Throwable throwable, String tag, String format, Object... args) {
        if (isDebug) {
            if (TextUtils.isEmpty(tag)) {
                tag = "";
            }
            List<String> list = buildMessage(insertDb, tag, format, args, 8);
            String callClass = list.get(0);
            Log.wtf(formartEnd(PRE, callClass, 30), wtfSpaceUp + wtfSpace);
            for (int i = 1; i < list.size(); i++) {
                String msg = list.get(i);
                if (null == throwable) {
                    Log.wtf(formartEnd(PRE, callClass, 30), "┃" + (i > 1 ? "   " : "") + msg);
                } else {
                    Log.wtf(formartEnd(PRE, callClass, 30), "┃" + (i > 1 ? "   " : "") + msg, throwable);
                }
            }
            Log.wtf(formartEnd(PRE, callClass, 30), wtfSpaceDown + wtfSpace);
        }
    }

    //======================================================================

    private static List<String> buildMessage(boolean insertDb, String tag, String format, Object[] args, int logLevel) {
        List<String> msgList = new ArrayList<>();
        String msg = "";

        try {
            if (args == null || args.length == 0) {
                if (!TextUtils.isEmpty(format)) {
                    msg = format;
                }
            } else {
                msg = String.format(Locale.US, format, args);
            }

            StackTraceElement[] trace = new Throwable().fillInStackTrace().getStackTrace();
            String caller = "";
            String callingClass = "";
            String callFile = "";
            int lineNumber = 0;
            for (int i = 2; i < trace.length; i++) {
                Class<?> clazz = trace[i].getClass();
                if (!clazz.equals(Logger.class)) {
                    callingClass = trace[i].getClassName();
                    callingClass = callingClass.substring(callingClass
                            .lastIndexOf('.') + 1);
                    caller = trace[i].getMethodName();
                    callFile = trace[i].getFileName();
                    lineNumber = trace[i].getLineNumber();
                    break;
                }
            }
            long threadId = Thread.currentThread().getId();
            msgList.add("━" + callingClass);

            String method = String.format(Locale.US, "[%03d] (%s:%d) %s"
                    , threadId, callFile, lineNumber, caller);

            String formartMethod = formartLength(method, 80);

            for (int i = 1; i < msg.length(); i += 2048) {
                if (i + 2048 < msg.length()) {
                    String subMsg = msg.substring(i, i + 2048);
                    String logMessage = String.format(Locale.US, "%s》 %s", formartMethod, subMsg);
                    msgList.add(logMessage);
                } else {
                    String subMsg = msg.substring(i, msg.length());
                    String logMessage = String.format(Locale.US, "%s》 %s", formartMethod, subMsg);
                    msgList.add(logMessage);
                    break;
                }
            }

            //============================================
            if (insertDb && null != dbHelper) {
                LogBean bean = new LogBean();
                bean.setCallClass(callingClass);
                bean.setCallFile(callFile);
                bean.setCallMethod(caller);
                bean.setLineNumber(lineNumber);
                bean.setTag(tag);
                bean.setLogInfo(msg);
                bean.setThreadId(Thread.currentThread().getId());
                bean.setLoglevel(logLevel);
                insert(bean);
            }

        } catch (Exception e) {
            e.printStackTrace();
            StringBuilder errorMsg = new StringBuilder();
            errorMsg.append("━━");
            errorMsg.append(format).append("━");
            if (null != args) {
                for (Object ob : args) {
                    errorMsg.append(ob + "━");
                }
            }
            errorMsg.append("XXXXX━━━");
            msgList.add(errorMsg.toString());
        }
        return msgList;
    }

    private static void insert(LogBean bean) {
        dbHelper.insertList(bean);
    }

    private static String formartLength(String src, int len) {
        StringBuilder sb = new StringBuilder();
        if (null != src && src.length() >= len) {
            sb.append(src);
        } else {
            sb.append(src);
            sb.append(space.substring(0, len - src.length()));
        }
        return sb.toString();
    }

    private static String formartEnd(String src, String end, int len) {
        StringBuilder sb = new StringBuilder();
        if (src.length() + end.length() >= len) {
            sb.append(src);
            sb.append(end);
        } else {
            sb.append(src);
            sb.append(space.substring(0, len - src.length() - end.length()));
            sb.append(end);
        }
        return sb.toString();
    }

    public static void dumpDb() {
        if (null != dbHelper) {
            dbHelper.dumpDb();
        }
    }

}