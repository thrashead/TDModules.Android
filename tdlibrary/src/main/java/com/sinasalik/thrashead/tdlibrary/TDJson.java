package com.sinasalik.thrashead.tdlibrary;

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

import java.io.IOException;

import javax.net.ssl.HttpsURLConnection;

public class TDJson {
    public static String ReturnResult(String serviceAddress, JsonObject jsonObj, boolean fromMemory) {
        String jsonResult = null;

        try {
            HttpParams httpParams = new BasicHttpParams();
            HttpClient httpClient = new DefaultHttpClient(httpParams);
            HttpPost httpPost = new HttpPost(serviceAddress);

            if (jsonObj != null) {
                if (!jsonObj.toString().equals("")) {
                    httpPost.setEntity(new StringEntity(jsonObj.toString(), "UTF-8"));
                }
            }

            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json; charset=utf-8; ");

            if (fromMemory == true) {
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

    public static String ReturnResult(String serviceAddress, JSONStringer jsonStr, boolean fromMemory) {
        String jsonResult = null;

        try {
            HttpParams httpParams = new BasicHttpParams();
            HttpClient httpClient = new DefaultHttpClient(httpParams);
            HttpPost httpPost = new HttpPost(serviceAddress);

            if (jsonStr != null) {
                if (!jsonStr.toString().equals("")) {
                    httpPost.setEntity(new StringEntity(jsonStr.toString(), "UTF-8"));
                }
            }

            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json; charset=utf-8; ");

            if (fromMemory == true) {
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

    public static String ReturnResult(String serviceAddress, JSONObject jsonObj, boolean fromMemory) {
        String jsonResult = null;

        try {
            HttpParams httpParams = new BasicHttpParams();
            HttpClient httpClient = new DefaultHttpClient(httpParams);
            HttpPost httpPost = new HttpPost(serviceAddress);

            if (jsonObj != null) {
                if (!jsonObj.toString().equals("")) {
                    httpPost.setEntity(new StringEntity(jsonObj.toString(), "UTF-8"));
                }
            }

            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json; charset=utf-8; ");

            if (fromMemory == true) {
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
}
