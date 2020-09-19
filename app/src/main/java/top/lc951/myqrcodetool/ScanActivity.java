package top.lc951.myqrcodetool;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;

import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ScanActivity extends AppCompatActivity {

    @BindView(R.id.dbv)
    DecoratedBarcodeView dbv;
    @BindView(R.id.button_led)
    ImageButton buttonLed;
    private CaptureManager capture;

    private DecoratedBarcodeView barcodeScannerView;
    private boolean bTorch = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        ButterKnife.bind(this);
        initView();
        capture = new CaptureManager(this, barcodeScannerView);
        capture.initializeFromIntent(getIntent(), savedInstanceState);

        capture.decode();
    }


    @Override
    public void onResume() {
        super.onResume();
        capture.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        capture.onPause();
        barcodeScannerView.setTorchOff();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        capture.onDestroy();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        capture.onSaveInstanceState(outState);
    }


    private void initView() {
        barcodeScannerView = initializeContent();
        /*根据闪光灯状态设置imagebutton*/
        barcodeScannerView.setTorchListener(new DecoratedBarcodeView.TorchListener() {
            @Override
            public void onTorchOn() {
                buttonLed.setBackground(getResources().getDrawable(R.drawable.flashlight_line_on));
                bTorch = true;
            }

            @Override
            public void onTorchOff() {
                buttonLed.setBackground(getResources().getDrawable(R.drawable.flashlight_line_off));
                bTorch = false;
            }
        });



    }
    /**
     * Override to use a different layout.
     *
     * @return the DecoratedBarcodeView
     */
    protected DecoratedBarcodeView initializeContent() {
        return (DecoratedBarcodeView)findViewById(R.id.dbv);
    }

    @OnClick(R.id.button_led)
    public void onViewClicked() {
        if(bTorch){
            barcodeScannerView.setTorchOff();
        } else {
            barcodeScannerView.setTorchOn();
        }
    }
}
