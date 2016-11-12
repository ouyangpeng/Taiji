package oyp.com.taiji;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

public class TaijiActivity extends Activity {
    private TaiJi taiJi;
    private float degrees = 0;
    private MyHandler mHandler = new MyHandler(this);

    public void rotateTaiji() {
        taiJi.setRotate(degrees += 5);
    }

    static class MyHandler extends Handler {
        WeakReference<Activity> mActivityReference;

        MyHandler(TaijiActivity activity) {
            mActivityReference = new WeakReference<Activity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            final TaijiActivity activity = (TaijiActivity) mActivityReference.get();
            if (activity != null) {
                activity.rotateTaiji();
                sendEmptyMessageDelayed(0, 80);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taiji);
        taiJi = (TaiJi) findViewById(R.id.view_taiji);
//        mHandler.sendEmptyMessageDelayed(0, 20);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }
}
