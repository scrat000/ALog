package im.color.logger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import im.color.logwithdatabases.ALog;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private static final String TAG = MainActivity.class.getSimpleName ();

    Button btni;
    Button btnv;
    Button btnw;
    Button btnd;
    Button btne;
    Button btna;
    Button dumpdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);

        ALog.setDebugMode (true);
        ALog.setDbHelper (this.getApplicationContext ());

        btnv = (Button) findViewById (R.id.logv);
        btni = (Button) findViewById (R.id.logi);
        btnd = (Button) findViewById (R.id.logd);
        btnw = (Button) findViewById (R.id.logw);
        btne = (Button) findViewById (R.id.loge);
        btna = (Button) findViewById (R.id.loga);
        dumpdb = (Button) findViewById (R.id.dumpdb);

        btnv.setOnClickListener (this);
        btni.setOnClickListener (this);
        btnd.setOnClickListener (this);
        btnw.setOnClickListener (this);
        btne.setOnClickListener (this);
        btna.setOnClickListener (this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId ()) {
            case R.id.logv: {
                ALog.v (TAG, "v");
            }
            break;
            case R.id.logi: {
                ALog.i (TAG, "i");
            }
            break;
            case R.id.logd: {
                ALog.d (TAG, "d");
            }
            break;
            case R.id.logw: {
                ALog.w (TAG, "w");
            }
            break;
            case R.id.loge: {
                ALog.e (TAG, "e");
            }
            break;
            case R.id.loga: {
                ALog.v (TAG, "v");
            }
            break;
            case R.id.dumpdb: {
                ALog.dumpDb ();
            }
            break;
        }
    }
}
