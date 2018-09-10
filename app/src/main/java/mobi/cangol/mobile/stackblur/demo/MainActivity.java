package mobi.cangol.mobile.stackblur.demo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import mobi.cangol.mobile.stackblur.StackBlurManager;

public class MainActivity extends Activity {

    private static final String TAG="Blur";
    private ImageView imageView0;
    private ImageView imageView1;
    private ImageView imageView2;
    private TextView textView0;
    private TextView textView1;
    private TextView textView2;

    private StackBlurManager stackBlurManager;
    private final static int RADIUS=70;
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
        blurJava();
        blurRs();
        blurNative();

    }
    private void blurJava(){
        AsyncTask<Void,Void,Bitmap> asyncTask=new AsyncTask<Void,Void,Bitmap>() {
            long idleTime=0;
            @Override
            protected Bitmap doInBackground(Void... params) {
                long curTime=System.currentTimeMillis();
                Bitmap bitmap= stackBlurManager.process(RADIUS);
                idleTime=System.currentTimeMillis()-curTime;
                return bitmap;
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                super.onPostExecute(bitmap);
                textView0.setText("Java:"+idleTime+"ms");
                imageView0.setImageBitmap(bitmap);
            }
        };
        asyncTask.execute();
    }
    private void blurRs(){
        AsyncTask<Void,Void,Bitmap> asyncTask=new AsyncTask<Void,Void,Bitmap>() {
            long idleTime=0;
            @Override
            protected Bitmap doInBackground(Void... params) {
                long curTime=System.currentTimeMillis();
                Bitmap bitmap= stackBlurManager.processRenderScript(MainActivity.this,RADIUS);
                idleTime=System.currentTimeMillis()-curTime;
                return bitmap;
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                super.onPostExecute(bitmap);
                textView1.setText("Rs:"+idleTime+"ms");
                imageView1.setImageBitmap(bitmap);
            }
        };
        asyncTask.execute();
    }
    private void blurNative(){
        AsyncTask<Void,Void,Bitmap> asyncTask=new AsyncTask<Void,Void,Bitmap>() {
            long idleTime=0;
            @Override
            protected Bitmap doInBackground(Void... params) {
                long curTime=System.currentTimeMillis();
                Bitmap bitmap= stackBlurManager.processNatively(RADIUS);
                idleTime=System.currentTimeMillis()-curTime;
                return bitmap;
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                super.onPostExecute(bitmap);
                textView2.setText("Native:"+idleTime+"ms");
                imageView2.setImageBitmap(bitmap);
            }
        };
        asyncTask.execute();
    }
}
