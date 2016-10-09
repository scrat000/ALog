package im.color.logger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import im.color.logwithdatabases.ALog;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "myTag";

    Button btni0;
    Button btni1;
    Button btni2;
    Button btni3;

    Button btnv0;
    Button btnv1;
    Button btnv2;
    Button btnv3;

    Button btnw0;
    Button btnw1;
    Button btnw2;
    Button btnw3;

    Button btnd0;
    Button btnd1;
    Button btnd2;
    Button btnd3;


    Button btne0;
    Button btne1;
    Button btne2;
    Button btne3;

    Button btnwtf0;
    Button btnwtf1;
    Button btnwtf2;
    Button btnwtf3;

    Button dumpdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ALog.setDebugMode(true);
        ALog.setDbHelper(this.getApplicationContext());

        btnv0 = (Button) findViewById(R.id.logv0);
        btnv1 = (Button) findViewById(R.id.logv1);
        btnv2 = (Button) findViewById(R.id.logv2);
        btnv3 = (Button) findViewById(R.id.logv3);

        btni0 = (Button) findViewById(R.id.logi0);
        btni1 = (Button) findViewById(R.id.logi1);
        btni2 = (Button) findViewById(R.id.logi2);
        btni3 = (Button) findViewById(R.id.logi3);

        btnd0 = (Button) findViewById(R.id.logd0);
        btnd1 = (Button) findViewById(R.id.logd1);
        btnd2 = (Button) findViewById(R.id.logd2);
        btnd3 = (Button) findViewById(R.id.logd3);

        btnw0 = (Button) findViewById(R.id.logw0);
        btnw1 = (Button) findViewById(R.id.logw1);
        btnw2 = (Button) findViewById(R.id.logw2);
        btnw3 = (Button) findViewById(R.id.logw3);

        btne0 = (Button) findViewById(R.id.loge0);
        btne1 = (Button) findViewById(R.id.loge1);
        btne2 = (Button) findViewById(R.id.loge2);
        btne3 = (Button) findViewById(R.id.loge3);

        btnwtf0 = (Button) findViewById(R.id.logwtf0);
        btnwtf1 = (Button) findViewById(R.id.logwtf1);
        btnwtf2 = (Button) findViewById(R.id.logwtf2);
        btnwtf3 = (Button) findViewById(R.id.logwtf3);

        dumpdb = (Button) findViewById(R.id.dumpdb);

        btnv0.setOnClickListener(this);
        btnv1.setOnClickListener(this);
        btnv2.setOnClickListener(this);
        btnv3.setOnClickListener(this);

        btni0.setOnClickListener(this);
        btni1.setOnClickListener(this);
        btni2.setOnClickListener(this);
        btni3.setOnClickListener(this);

        btnd0.setOnClickListener(this);
        btnd1.setOnClickListener(this);
        btnd2.setOnClickListener(this);
        btnd3.setOnClickListener(this);

        btnw0.setOnClickListener(this);
        btnw1.setOnClickListener(this);
        btnw2.setOnClickListener(this);
        btnw3.setOnClickListener(this);

        btne0.setOnClickListener(this);
        btne1.setOnClickListener(this);
        btne2.setOnClickListener(this);
        btne3.setOnClickListener(this);

        btnwtf0.setOnClickListener(this);
        btnwtf1.setOnClickListener(this);
        btnwtf2.setOnClickListener(this);
        btnwtf3.setOnClickListener(this);

        dumpdb.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.logv0: {
                for (int i = 0; i < 10; i++) {
                    ALog.v("v:" + i);
                }
            }
            break;
            case R.id.logv1: {
                for (int i = 0; i < 10; i++) {
                    ALog.v(TAG, "v:" + i);
                }
            }
            break;
            case R.id.logv2: {
                for (int i = 0; i < 10; i++) {
                    ALog.v(new Throwable("vvv"), TAG, "v:%02d", i);
                }
            }
            break;
            case R.id.logv3: {
                for (int i = 0; i < 10; i++) {
                    ALog.v(true, new Throwable("vvv"), TAG, "v:%d", i);
                }
            }
            break;
            case R.id.logi0: {
                for (int i = 0; i < 20; i++) {
                    ALog.i("i:" + i);
                }
            }
            break;
            case R.id.logi1: {
                for (int i = 0; i < 20; i++) {
                    ALog.i(TAG, "i:%02d", i);
                }
            }
            break;
            case R.id.logi2: {
                for (int i = 0; i < 20; i++) {
                    ALog.i(new Throwable("iii"), TAG, "i:%d" + i);
                }
            }
            break;
            case R.id.logi3: {
                for (int i = 0; i < 20; i++) {
                    ALog.i(true, new Throwable("iii"), TAG, "i:%d", i);
                }
            }
            break;
            case R.id.logd0: {
                for (int i = 0; i < 10; i++) {
                    ALog.d("d:" + i);
                }
            }
            break;
            case R.id.logd1: {
                for (int i = 0; i < 10; i++) {
                    ALog.d(TAG, "d:%02d", i);
                }
            }
            break;
            case R.id.logd2: {
                for (int i = 0; i < 10; i++) {
                    ALog.d(new Throwable("ddd"), TAG, "d:%2d", i);
                }
            }
            break;
            case R.id.logd3: {
                for (int i = 0; i < 10; i++) {
                    ALog.d(true, new Throwable("ddd"), TAG, "d:%d", i);
                }
            }
            break;
            case R.id.logw0: {
                for (int i = 0; i < 20; i++) {
                    ALog.w("w:" + i);
                }
            }
            break;
            case R.id.logw1: {
                for (int i = 0; i < 20; i++) {
                    ALog.w(TAG, "w:%02d", i);
                }
            }
            break;
            case R.id.logw2: {
                for (int i = 0; i < 20; i++) {
                    ALog.w(new Throwable("www"), TAG, "w:%d", i);
                }
            }
            break;
            case R.id.logw3: {
                for (int i = 0; i < 20; i++) {
                    ALog.w(true, new Throwable("www"), TAG, "w:%d", i);
                }
            }
            break;
            case R.id.loge0: {
                for (int i = 0; i < 5; i++) {
                    ALog.e("e:" + i);
                }
            }
            break;
            case R.id.loge1: {
                for (int i = 0; i < 5; i++) {
                    ALog.e(TAG, "e:%02d", i);
                }
            }
            break;
            case R.id.loge2: {
                for (int i = 0; i < 5; i++) {
                    ALog.e(new Throwable("eee"), TAG, "e:%d", i);
                }
            }
            break;
            case R.id.loge3: {
                for (int i = 0; i < 5; i++) {
                    ALog.e(true, new Throwable("eee"), TAG, "e:%d", i);
                }
            }
            break;
            case R.id.logwtf0: {
                for (int i = 0; i < 8; i++) {
                    ALog.wtf("v:" + i);
                }
            }
            break;
            case R.id.logwtf1: {
                for (int i = 0; i < 8; i++) {
                    ALog.wtf(TAG, "v:%02d", i);
                }
            }
            break;
            case R.id.logwtf2: {
                for (int i = 0; i < 8; i++) {
                    ALog.wtf(new Throwable("eee"), TAG, "wtf:%d", i);
                }
            }
            break;
            case R.id.logwtf3: {
                for (int i = 0; i < 8; i++) {
                    ALog.wtf(true, new Throwable("eee"), TAG, "wtf:" + i);
                }
            }
            break;
            case R.id.dumpdb: {
                ALog.dumpDb();
            }
            break;

        }
    }
}
