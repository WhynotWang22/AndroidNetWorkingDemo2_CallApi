package com.example.demo1.demo1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.demo1.R;

public class Demo21Main2Activity extends AppCompatActivity
        implements Demo21Interface, View.OnClickListener {
    ImageView imageView;
    Button button;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo21_main2);
        imageView= findViewById(R.id.demo21Img1);
        button = findViewById(R.id.demo21Btn1);
        textView = findViewById(R.id.demo21Tv1);
        button.setOnClickListener(this);
    }

    @Override
    public void loadAnh(Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
    }

    @Override
    public void loi() {
        textView.setText("Loi doc du lieu");
    }

    @Override
    public void onClick(View view) {
        //khi click vao button thi goi den asynctask
        new Demo21AsyncTask(this,this)
                .execute("http://tinypic.com/images/goodbye.jpg");
    }
}
