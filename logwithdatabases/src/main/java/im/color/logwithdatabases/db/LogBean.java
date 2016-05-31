package im.color.logwithdatabases.db;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by color on 16/5/31.
 */
public class LogBean {
    private String callFile;
    private String callClass;
    private String callMethod;
    private long lineNumber;

    private long threadId;
    private String logInfo;
    private String tag;
    private long serialno;
    private String stime;
    private int loglevel;

    public LogBean() {
        serialno = System.currentTimeMillis ();
    }

    public String getCallFile() {
        return callFile;
    }

    public void setCallFile(String callFile) {
        this.callFile = callFile;
    }

    public String getCallClass() {
        return callClass;
    }

    public void setCallClass(String callClass) {
        this.callClass = callClass;
    }

    public String getCallMethod() {
        return callMethod;
    }

    public void setCallMethod(String callMethod) {
        this.callMethod = callMethod;
    }

    public long getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(long lineNumber) {
        this.lineNumber = lineNumber;
    }

    public long getThreadId() {
        return threadId;
    }

    public void setThreadId(long threadId) {
        this.threadId = threadId;
    }

    public String getLogInfo() {
        return logInfo;
    }

    public void setLogInfo(String logInfo) {
        this.logInfo = logInfo;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public long getSerialno() {
        return serialno;
    }

    public void setSerialno(long serialno) {
        this.serialno = serialno;
    }

    public String getStime() {
        SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss.sss", Locale.getDefault ());
        Date date = new Date (serialno);
        String stime = sdf.format (date);
        return stime;
    }

    public void setStime(String stime) {
        this.stime = stime;
    }

    public int getLoglevel() {
        return loglevel;
    }

    public void setLoglevel(int loglevel) {
        this.loglevel = loglevel;
    }
}
