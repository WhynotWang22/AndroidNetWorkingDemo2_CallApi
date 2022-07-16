package com.example.demo1.demo1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class Demo21AsyncTask extends AsyncTask<String,Void, Bitmap> {
    private Demo21Interface iListenner;
    private Context context;
    public Demo21AsyncTask(Demo21Interface iListenner,Context context)
    {
        this.context = context;
        this.iListenner = iListenner;
    }
    //ham doc du lieu
    @Override
    protected Bitmap doInBackground(String... strings) {
        try
        {
            //load du lieu tu mang
            return BitmapFactory.decodeStream((InputStream)new URL(strings[0]).getContent());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
    //ham tra ve ket qua
    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        if(bitmap!=null)
        {
            iListenner.loadAnh(bitmap);//tra ve ket qua cho android
        }
        else
        {
            iListenner.loi();//neu co loi
        }
    }
}
