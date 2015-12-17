package hack.ayush.iothackday;


import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

public class stats extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stats);
      final  ImageView mImageView = (ImageView) findViewById(R.id.imageView2);




            new Thread(new Runnable() {
                private Bitmap loadImageFromNetwork(String url) {
                    try {
                        Bitmap bitmap = BitmapFactory
                                .decodeStream((InputStream) new URL(url)
                                        .getContent());
                        return bitmap;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return null;
                }


                public void run() {
                    final Bitmap bitmap =
                            loadImageFromNetwork("http://example.com/image.png");
                    mImageView.post(new Runnable() {


                        public void run() {
                            mImageView.setImageBitmap(bitmap);
                        }
                    });
                }
            }).start();

    }
}
