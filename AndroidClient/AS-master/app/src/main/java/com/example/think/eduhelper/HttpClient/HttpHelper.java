package com.example.think.eduhelper.HttpClient;
import android.drm.DrmStore;
import android.util.Log;

import com.example.think.eduhelper.MainActivity;
import com.example.think.eduhelper.SignUpActivity;

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

    public void postData(JSONObject parameter, String address,final HttpCallback dataBack){
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, parameter.toString());
        //TO DO The URL need to be changed!
        Request request = new Request.Builder()
                .url("http://backend-frank0767.c9users.io/"+address)
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
                    dataBack.postDataCallback(response.body().string());
                }
            }
        });//post end
    }
}
