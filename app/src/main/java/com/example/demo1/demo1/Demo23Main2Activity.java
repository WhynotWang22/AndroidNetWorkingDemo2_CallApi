package com.example.demo1.demo1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.demo1.R;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Demo23Main2Activity extends AppCompatActivity {
    ImageView imageView;
    Button button;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo23_main2);
        imageView= findViewById(R.id.demo23Img1);
        button = findViewById(R.id.demo23Btn1);
        textView = findViewById(R.id.demo23Tv1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Tao 1 thread
                final Thread myThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //tao 1 thread con de download anh
                        final Bitmap bitmap = loadAnh("http://tinypic.com/images/goodbye.jpg");
                        //tao 1 thread con de post anh ve client
                        imageView.post(new Runnable() {
                            @Override
                            public void run() {
                                imageView.setImageBitmap(bitmap);
                                textView.setText("Bai 23 doc anh thanh cong");
                            }
                        });
                    }
                });
                myThread.start();
            }
        });
    }
    private Bitmap loadAnh(String link)//ham doc anh tu mang
    {
        URL url;
        Bitmap bitmap = null;
        try {
            url = new URL(link);
            bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}
