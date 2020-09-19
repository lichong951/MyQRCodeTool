package top.lc951.myqrcodetool;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.umeng.analytics.MobclickAgent;

public class BaseActivity extends AppCompatActivity {

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();

    }
}
