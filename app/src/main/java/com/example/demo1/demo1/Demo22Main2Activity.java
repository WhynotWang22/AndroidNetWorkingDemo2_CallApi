package com.example.demo1.demo1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.demo1.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.MalformedInputException;


public class Demo22Main2Activity extends AppCompatActivity {

    ImageView image22;
    Button button22callapi;
    TextView textView22apianh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo22_main2);


        image22 = (ImageView) findViewById(R.id.image22);
        button22callapi = (Button) findViewById(R.id.button22callapi);
        textView22apianh = (TextView) findViewById(R.id.textView22apianh);

        button22callapi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             new Demo22AsyncTask().execute("https://images.unsplash.com/photo-1653730748789-91397af99d7b?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80");
            }
        });
    }
    public  class Demo22AsyncTask extends AsyncTask<String,Void, Bitmap>{

        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
                return BitmapFactory.decodeStream((InputStream)new URL(strings[0]).getContent());
            }
            catch (MalformedInputException e){
                e.printStackTrace();
            }
            catch (IOException e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if (bitmap!=null)
            {
                image22.setImageBitmap(bitmap);

            }
            else{
                textView22apianh.setText("loi khong load duoc anh");
            }
        }
    }

}