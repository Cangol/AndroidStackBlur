package mobi.cangol.mobile.blur.demo;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.TextView;

import mobi.cangol.mobile.blur.StackBlurManager;

public class MainActivity extends Activity {

    private static final String TAG="Blur";
    private TextView textView;
    private StackBlurManager stackBlurManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView= (TextView) findViewById(R.id.textView);
        stackBlurManager=new StackBlurManager(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher));

        stackBlurManager.processJava(10);
    }
}
