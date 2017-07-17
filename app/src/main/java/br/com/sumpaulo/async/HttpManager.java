package br.com.sumpaulo.async;

import android.net.Uri;
import android.util.Base64;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by paulo on 15/07/17.
 */

public class HttpManager {

    public static String getData(String uri){
        BufferedReader reader = null;

        try {

            URL url = new URL(uri);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            StringBuilder stringBuilder = new StringBuilder();
            reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while((line = reader.readLine()) != null){
                stringBuilder.append(line + "\n");
            }

            reader.close();
            return stringBuilder.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return "Falha";
        }
        finally {
            try {
                reader.close();
            } catch (IOException e) {

                e.printStackTrace();
                return "Falha";
            }
        }
    }

    public static String getData(String uri, String username, String password){
        BufferedReader reader = null;

        byte[] loginBytes = (username + ":" + password).getBytes();
        StringBuilder loginBuilder = new StringBuilder();

        loginBuilder.append("Basic ");
        loginBuilder.append(Base64.encodeToString(loginBytes, Base64.DEFAULT));


        try {

            URL url = new URL(uri);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.addRequestProperty("Authorization", loginBuilder.toString());

            StringBuilder stringBuilder = new StringBuilder();
            reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while((line = reader.readLine()) != null){
                stringBuilder.append(line + "\n");
            }

            reader.close();
            return stringBuilder.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        finally {
            try {
                reader.close();
            } catch (IOException e) {

                e.printStackTrace();
                return null;
            }
        }
    }
}