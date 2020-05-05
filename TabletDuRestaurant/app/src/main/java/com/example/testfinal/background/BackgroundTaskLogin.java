package com.example.testfinal.background;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.example.testfinal.R;
import com.example.testfinal.models.Client;
import com.example.testfinal.models.DialogOk;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;

public class BackgroundTaskLogin extends AsyncTask<Object,Void,String> {

    private Context context;
    private Activity activity;
    private String login_url="http://192.168.1.3:8888/PhpScriptsTable/login.php";
    private ProgressBar bar;
    private TextView text;
    private LinearLayout linearLayout;
    private AsyncResponse response;


    //CONSTRUCTOR
    public BackgroundTaskLogin(Context cxt, AsyncResponse resp){
         context=cxt;
         activity=(Activity)cxt;
         response=resp;
    }

    @Override
    protected void onPreExecute() {

        showLoadingBar();

    }

    @Override
    protected String doInBackground(Object... param) {

        String method =(String)param[0];

        if(method.equals("login"))
        {


                try {
                    ///////////////// recuperer le user name et le password ....
                    String user = (String)param[1];
                    String password = (String)param[2];
                    ///// envoi des donnees.......
                    String value = URLEncoder.encode("user","UTF-8")+ "=" +URLEncoder.encode(user,"UTF-8")+ "&" +
                            URLEncoder.encode("password","UTF-8")+ "=" +URLEncoder.encode(password,"UTF-8");

                    return sendRequest(login_url,value);

                } catch (IOException e) {
                    e.printStackTrace();
                }


        }


        return "";
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String json) {

        //decoder le fichier json recupere
        try {

            JSONObject jsonObject = new JSONObject(json);
            JSONArray array=null;
            array = jsonObject.getJSONArray("login_response");
            assert array != null; // le tableau ne peux pas etre nul

            final JSONObject value= array.getJSONObject(0);

            String message = value.getString("message");

            hideLoadingBar();
            Log.d("result",json.toString());

            switch (value.getString("success"))
             {

                 case "Login_ok": {

                     final DialogOk dialog = new DialogOk(activity,context,1);

                     dialog.execute(message);

                     dialog.getOk().setOnClickListener(new View.OnClickListener() {
                         @Override
                         public void onClick(View v) {

                             Client client= null;
                             try {

                                 dialog.close();

                                 client = new Client(value.getString("id"),value.getString("username"),value.getString("photo"));

                                 response.processFinish(client,"L1");

                             } catch (JSONException e) {
                                 e.printStackTrace();
                             }


                         }
                     });

                 }break;

                 case "L0": {

                     final DialogOk dialog = new DialogOk(activity,context,2);

                     dialog.execute("Connexion échouée");

                     dialog.getOk().setOnClickListener(new View.OnClickListener() {
                         @Override
                         public void onClick(View v) {

                                 dialog.close();

                                 response.processFinish("Mot de passe incorrect","L0");

                         }
                     });


                 }break;

                 case "L00": {

                     final DialogOk dialog = new DialogOk(activity,context,2);

                     dialog.execute("Connexion échouée");

                     dialog.getOk().setOnClickListener(new View.OnClickListener() {
                         @Override
                         public void onClick(View v) {

                             dialog.close();

                             response.processFinish("Utilisateur inexistant","L00");

                         }
                     });

                 }break;

                 case "L000": {

                     final DialogOk dialog = new DialogOk(activity,context,2);

                     dialog.execute("Connexion échouée");

                     dialog.getOk().setOnClickListener(new View.OnClickListener() {
                         @Override
                         public void onClick(View v) {

                             dialog.close();

                             response.processFinish("2","L000");

                         }
                     });

                 }break;
             }


        } catch (JSONException e) {
           e.printStackTrace();
        }

    }

    private String sendRequest(String urlS ,String value) throws IOException {


        URL url=new URL(urlS);
        HttpURLConnection huc =(HttpURLConnection) url.openConnection();
        huc.setRequestMethod("POST");
        huc.setDoInput(true);
        huc.setDoOutput(true);

        //Listeners to send data
        OutputStream outputStream = huc.getOutputStream();
        BufferedWriter writer =  new BufferedWriter(new OutputStreamWriter(outputStream, Charset.forName("UTF-8")));


        writer.write(value);
        writer.flush();
        writer.close();
        outputStream.close();

        //Listeners to get data
        InputStream inputStream = huc.getInputStream();
        BufferedReader reader =  new BufferedReader(new InputStreamReader(inputStream,Charset.forName("UTF-8")));

        StringBuilder builder = new StringBuilder();
        String line;

        while ((line=reader.readLine())!=null)
        {
            builder.append(line).append("\n");
        }

        huc.disconnect();

        return builder.toString().trim();

    }


    //IMPLEMENTING AN INTERFACE TO GET THE CODE RESULT IN ANOTHER ACTIVITY
    public interface AsyncResponse {

        void processFinish(Object result,String value);
    }


    //HERE I LL CHANGE IT TO DISPLAY AN ALERT DIALOG
    private void showDialog(String message)
    {
        Toast.makeText(context,message, Toast.LENGTH_LONG).show();
    }

    //SHOW LOADING BAR
    private void showLoadingBar() {

        bar=activity.findViewById(R.id.loading);
        text=activity.findViewById(R.id.loading_text);
        linearLayout=activity.findViewById(R.id.layout_loading);
        bar.setVisibility(View.VISIBLE);
        text.setVisibility(View.VISIBLE);
        linearLayout.setBackgroundColor(Color.TRANSPARENT);
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);


    }

    //HIDE LOADING BAR
    private void hideLoadingBar() {

        bar=activity.findViewById(R.id.loading);
        text=activity.findViewById(R.id.loading_text);
        linearLayout=activity.findViewById(R.id.layout_loading);
        bar.setVisibility(View.GONE);
        text.setVisibility(View.GONE);
        linearLayout.setBackgroundColor(0x00000000);
        activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);


    }

}
