package com.example.testfinal.background;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;
import com.example.testfinal.models.Commande;
import com.example.testfinal.models.Ingredient;
import com.example.testfinal.models.Plat;
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
import java.util.ArrayList;

public class BackgroundRecommander extends AsyncTask<Object,Void,String> {

    private String commandes_recommander_url="http://192.168.43.225/PhpScriptsTable/user_commandes_recommandation.php";
    private String menu_recommander_url="http://192.168.43.225/PhpScriptsTable/user_menu_filtre_recommander.php";

    private AsyncResponse response;
    private Context context;

    public BackgroundRecommander(AsyncResponse resp,Context c){

            response=resp;
            context=c;
        }


    @Override
    protected void onPreExecute() {

    }

    @Override
    protected String doInBackground(Object... param) {

        String method =(String)param[0];

        switch (method)
        {

            case "commandes_recommander":
            {

                try {

                    String id_client =(String)param[1];
                    String value = URLEncoder.encode("id_client","UTF-8")+ "=" +URLEncoder.encode(id_client,"UTF-8");
                    return sendRequest(commandes_recommander_url,value);


                } catch (IOException e) {
                    e.printStackTrace();
                }

            }break;

            case "menu_recommander":
            {

                try {

                    String id_client =(String)param[1];
                    String value = URLEncoder.encode("id_client","UTF-8")+ "=" +URLEncoder.encode(id_client,"UTF-8");
                    return sendRequest(menu_recommander_url,value);


                } catch (IOException e) {
                    e.printStackTrace();
                }

            }break;

        }

        return null;
    }


    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String json) {


        //decoder le fichier json recupere ....
        try {
            JSONObject jsonObject = new JSONObject(json);

            switch (jsonObject.getString("success"))
            {

                case "UCR1": {

                    JSONArray  array= jsonObject.getJSONArray("0");

                    ArrayList<Commande> commandes= new ArrayList<>();

                    JSONObject value,value2,value3;

                    for (int i=0;i<array.length();i++)

                    {
                        //je get la commande
                        value =array.getJSONObject(i);


                        commandes.add(new Commande(value.getString("id"),value.getString("date"),value.getString("heure")
                                ,value.getString("montant"),value.getString("type")));


                        JSONArray array2 = value.getJSONArray("liste_plats");

                        for (int j=0;j<array2.length();j++) {

                            value2 =array2.getJSONObject(j);
                            commandes.get(i).getPlats_commandes().add(new Plat(value2.getString("id"),
                                    value2.getString("nom"), value2.getString("portion_select"),
                                    value2.getString("prix_portion_select"), value2.getString("qte_select")));

                            JSONArray array3 = value2.getJSONArray("liste_ingredients");

                            for (int k=0;k<array3.length();k++){

                                value3=array3.getJSONObject(k);
                                commandes.get(i).getPlats_commandes().get(j).getIngredients().add(
                                        new Ingredient(value3.getString("id_ing"),value3.getString("nom_ing")));

                            }
                        }


                    }

                    response.processFinish(commandes,"commandes");

                }break;
                case "UCR11": {

                    ArrayList<Commande> commandes= new ArrayList<>();

                    response.processFinish(commandes,"commandes");
                }break;

                case "UMFR11": {

                    ArrayList<Plat> plats= new ArrayList<>();

                    response.processFinish(plats,"menu");

                }break;


                case "UMFR1":{



                    JSONArray  array= jsonObject.getJSONArray("0");

                    ArrayList<Plat> plats= new ArrayList<>();

                    JSONObject value,value3;

                    for (int i=0;i<array.length();i++){

                        value =array.getJSONObject(i);

                        plats.add(new Plat(value.getString("id"),value.getString("designation"),value.getString("photo"),
                                value.getString("categorie"),value.getString("modeCuisson"),value.getString("tempsCuisson"),
                                value.getString("smallPrice"),value.getString("mediumPrice"),value.getString("largePrice")
                                ,value.getString("fav")));

                        JSONArray array3 = value.getJSONArray("liste_ingredients");

                        for (int k=0;k<array3.length();k++){

                            value3=array3.getJSONObject(k);

                            plats.get(i).getIngredients().add(
                                    new Ingredient(value3.getString("id_ing"),value3.getString("nom_ing")));

                        }

                    }
                    response.processFinish(plats,"menu");

                }break;

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }



    //SEND REQUEST WITH DATA
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

    // envois sans donnees .....
    private String sendRequestWithoutData(String urlS) throws IOException {


        URL url=new URL(urlS);
        HttpURLConnection huc =(HttpURLConnection) url.openConnection();
        huc.setRequestMethod("POST");
        huc.setDoInput(true);
        huc.setDoOutput(true);

        //Listeners to send data
        OutputStream outputStream = huc.getOutputStream();
        BufferedWriter writer =  new BufferedWriter(new OutputStreamWriter(outputStream,Charset.forName("UTF-8")));


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





}
