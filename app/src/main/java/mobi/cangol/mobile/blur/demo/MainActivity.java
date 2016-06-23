package mobi.cangol.mobile.blur.demo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import mobi.cangol.mobile.blur.StackBlurManager;

public class MainActivity extends Activity {

    private static final String TAG="Blur";
    private ImageView imageView0;
    private ImageView imageView1;
    private ImageView imageView2;
    private TextView textView0;
    private TextView textView1;
    private TextView textView2;

    private StackBlurManager stackBlurManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView0= (TextView) findViewById(R.id.textView0);
        textView1= (TextView) findViewById(R.id.textView1);
        textView2= (TextView) findViewById(R.id.textView2);

        imageView0= (ImageView) findViewById(R.id.imageView0);
        imageView1= (ImageView) findViewById(R.id.imageView1);
        imageView2= (ImageView) findViewById(R.id.imageView2);
        stackBlurManager=new StackBlurManager(BitmapFactory.decodeResource(getResources(),R.mipmap.test));

        long curTime1=System.currentTimeMillis();
        Bitmap bitmap1=stackBlurManager.processJava(70);
        textView1.setText("Java:"+(System.currentTimeMillis()-curTime1)+"ms");
        imageView1.setImageBitmap(bitmap1);

        long curTime2=System.currentTimeMillis();
        Bitmap bitmap2=stackBlurManager.processNative(70);
        textView2.setText("Native:"+(System.currentTimeMillis()-curTime2)+"ms");
        imageView2.setImageBitmap(bitmap2);
    }
}
