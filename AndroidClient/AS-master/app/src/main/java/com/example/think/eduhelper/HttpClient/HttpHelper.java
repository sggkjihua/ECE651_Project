package com.example.think.eduhelper.HttpClient;
import android.drm.DrmStore;

import org.json.JSONObject;

import java.io.IOException;
import java.sql.ClientInfoStatus;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class HttpHelper {

    private static HttpHelper mHttpHelper;
    public static HttpHelper getInstance(){
        if(mHttpHelper == null){
            mHttpHelper = new HttpHelper();
        }
        return mHttpHelper;
    }

    public OkHttpClient Client = new OkHttpClient();
    // TO DO change the url here
    private String Url = "https://postman-echo.com/get?foo1=bar1&foo2=bar2'";

    public void getData(final HttpCallback getCallback) {
        Request request = new Request.Builder()
                .url(Url)
                .build();
        Client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful())
                        throw new IOException("Unexpected code " + response);

                    String data = responseBody.string();
                    getCallback.getDataCallback(data);
                }
            }
        });
    }
    public void postData(JSONObject parameter) {
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, parameter.toString());
        //TO DO The URL need to be changed!
        Request request = new Request.Builder()
                .url("http://192.168.0.16:5000/register")
                .post(body)
                .addHeader("content-type", "application/json; charset=utf-8")
                .build();

        Call call = Client.newCall(request);
        //final Context thisobj = this;
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                //Toast.makeText(thisobj,response.body().string()+"Please login",Toast.LENGTH_SHORT).show();
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code" + response.body().string());
                } else {
                    System.out.println(response.body().string());
                }
            }
        });
        //post end
    }
}
