package com.sinasalik.thrashead.tdlibrary;

import android.os.AsyncTask;

import com.google.gson.JsonObject;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.net.ssl.HttpsURLConnection;

public class TDJsonAsync extends AsyncTask<String, String, String> {
    private TDJsonAsyncListener TDJsonAsyncListener;
    private String jsonResult;
    private JSONStringer jsonStr;
    private JSONObject jsonObj;
    private JsonObject gsonObj;
    private boolean bellekten;

    public TDJsonAsync(JSONStringer jsonStringer, TDJsonAsyncListener jsonListener, boolean bellekKullan) {
        super();
        TDJsonAsyncListener = jsonListener;
        jsonStr = jsonStringer;
        bellekten = bellekKullan;
    }

    public TDJsonAsync(JSONObject jsonObject, TDJsonAsyncListener jsonListener, boolean bellekKullan) {
        super();
        TDJsonAsyncListener = jsonListener;
        jsonObj = jsonObject;
        bellekten = bellekKullan;
    }

    public TDJsonAsync(JsonObject gsonObject, TDJsonAsyncListener jsonListener, boolean bellekKullan) {
        super();
        TDJsonAsyncListener = jsonListener;
        gsonObj = gsonObject;
        bellekten = bellekKullan;

        /* Gson Kullanım
            Select select = new Select();
            ArrayList<Where> whereList = new ArrayList<>();

            Gson gson = new Gson();
            JsonObject gsonObject = new JsonObject();

            JsonElement jSelect = gson.toJsonTree(select);
            JsonElement jWhereList = gson.toJsonTree(whereList);

            gsonObject.add("select", jSelect);
            gsonObject.add("whereList", jWhereList);

            select ve whereList Wcf servis'te biri normal obje diğeri List obje parametreleridir.
        */
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            // Aşağıdakiler için gradle'a ekle : implementation 'org.jbundle.util.osgi.wrapped:org.jbundle.util.osgi.wrapped.org.apache.http.client:4.1.2'
            HttpParams httpParams = new BasicHttpParams();
            HttpClient httpClient = new DefaultHttpClient(httpParams);
            HttpPost httpPost = new HttpPost(params[0]);

            if (jsonStr != null) {
                if (!jsonStr.toString().equals("")) {
                    httpPost.setEntity(new StringEntity(jsonStr.toString(), "UTF-8"));
                }
            } else if (jsonObj != null) {
                if (!jsonObj.toString().equals("")) {
                    httpPost.setEntity(new StringEntity(jsonObj.toString(), "UTF-8"));
                }
            } else if (gsonObj != null) {
                if (!gsonObj.toString().equals("")) {
                    httpPost.setEntity(new StringEntity(gsonObj.toString(), "UTF-8"));
                }
            }

            httpPost.setHeader("Accept", "application/jsonStr");
            httpPost.setHeader("Content-type", "application/jsonStr; charset=utf-8; ");

            if(bellekten == true) {
                //Cache'den çağırılma sıklığı. Bu satırı kaldırırsan cache'den çağırmaz.
                httpPost.setHeader("Cache-Control", "max-age=600");
            }

            HttpConnectionParams.setConnectionTimeout(httpParams, 3000);
            HttpConnectionParams.setSoTimeout(httpParams, 5000);
            HttpResponse response = httpClient.execute(httpPost);

            if (response.getStatusLine().getStatusCode() == HttpsURLConnection.HTTP_OK) {
                jsonResult = EntityUtils.toString(response.getEntity());

            } else {
                jsonResult = "error";
            }
        } catch (IOException e) {
            jsonResult = "error";
        }

        return jsonResult;
    }

    private StringBuilder inputStreamToString(InputStream is) {
        String rLine;
        StringBuilder answer = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        try {
            while ((rLine = br.readLine()) != null) {
                answer.append(rLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return answer;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        if (TDJsonAsyncListener != null) {
            if (result.equals("error")) {
                TDJsonAsyncListener.errorCallBack();
            } else {
                TDJsonAsyncListener.successCallBack(result);
            }
        }
    }
}
