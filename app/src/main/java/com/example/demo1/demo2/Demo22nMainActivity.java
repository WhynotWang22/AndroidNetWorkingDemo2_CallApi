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
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

public class Demo22nMainActivity extends AppCompatActivity {

    private EditText demo22txt1;
    private Button demo22btn1;
    private TextView demo22tv1;
     String kq;
    String canh_param = "";
    String path = "https://batdongsanabc.000webhostapp.com/mob403/demo2_api_post.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo22n_main);

        demo22txt1 = (EditText) findViewById(R.id.demo22txt1);
        demo22btn1 = (Button) findViewById(R.id.demo22btn1);
        demo22tv1 = (TextView) findViewById(R.id.demo22tv1);
        demo22btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canh_param = demo22txt1.getText().toString().trim();
                new Demo22nAsyncTask().execute();
            }
        });
    }

    protected class Demo22nAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                //1.lay duong dan
                URL url = new URL(path);
                String param = "canh=" + URLEncoder.encode(canh_param, "utf-8");
                //2.mo ket noi
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//3. xu ly cac thuoc tinh cua tham so
                urlConnection.setDoOutput(true);
                urlConnection.setRequestMethod("POST");
                urlConnection.setFixedLengthStreamingMode(param.getBytes().length);
                urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                //4. truyen tham so
                PrintWriter printWriter = new PrintWriter(urlConnection.getOutputStream());
                printWriter.print(param);
                printWriter.close();
                //5. doc du lieu
                StringBuilder stringBuilder = new StringBuilder();
                String line = "";
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(urlConnection.getInputStream()));
                while ((line = br.readLine()) != null) {
                    stringBuilder.append(line);
                }
                kq = stringBuilder.toString();
                urlConnection.disconnect();
            } catch (MalformedURLException | UnsupportedEncodingException e) {
                e.printStackTrace();
                kq = e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                kq = e.getMessage();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            demo22tv1.setText(kq);//tra ket qua cho nguoi dung
        }
    }
}