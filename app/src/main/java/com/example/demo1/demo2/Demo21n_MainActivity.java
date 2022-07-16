package com.example.demo1.demo2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.demo1.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Demo21n_MainActivity extends AppCompatActivity {

    private EditText edittv1;
    private EditText edittv2;
    private Button btnan;
    private TextView tv123;
     String name ="";
     String mark ="";
     String path ="https://batdongsanabc.000webhostapp.com/mob403/demo2_api_get.php";
    String kq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo21n_main);

        edittv1 = (EditText) findViewById(R.id.edittv1);
        edittv2 = (EditText) findViewById(R.id.edittv2);
        btnan = (Button) findViewById(R.id.btnan);
        tv123 = (TextView) findViewById(R.id.tv123);
        btnan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = edittv1.getText().toString();
                name = edittv2.getText().toString();
                new Demo21nAsyncTask().execute();
            }
        });
    }

    protected class Demo21nAsyncTask extends AsyncTask<Void,Void,Void>{
        //xu ly du lieu o Server
        @Override
        protected Void doInBackground(Void... voids) {
            path +="?name="+name+"&mark="+mark;
            try {
                URL url = new URL(path);//lay duong dan server
                //mo ket noi va tao bo dem br
                BufferedReader br
                        =new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));
                //doc du lieu
                StringBuilder stringBuilder = new StringBuilder();//chua ket qua doc duoc
                String line="";//chua ket qua tam thoi
                while ((line=br.readLine())!=null)
                {
                    stringBuilder.append(line);//doc tung dong va dua vao bo chua ket qua
                }
                kq=stringBuilder.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
                kq = e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                kq = e.getMessage();
            }
            return null;
        }
        //tra ve ket qua cho client
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            tv123.setText(kq);
        }
    }
}
