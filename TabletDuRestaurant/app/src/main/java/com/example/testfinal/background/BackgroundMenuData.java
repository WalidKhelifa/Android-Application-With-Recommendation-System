package com.example.testfinal.background;

import android.content.Context;
import android.os.AsyncTask;
import com.example.testfinal.models.Avis;
import com.example.testfinal.models.Commande;
import com.example.testfinal.models.Plat;
import com.example.testfinal.models.Restaurant;
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

public class BackgroundMenuData extends AsyncTask<Object,Void,String> {

    private String menu_online_url="http://192.168.1.3:8888/PhpScriptsTable/menu_online.php";
    private String favoris_url="http://192.168.1.3:8888/PhpScriptsTable/plats_favoris.php";
    private String details_url="http://192.168.1.3:8888/PhpScriptsTable/plat_details.php";
    private String add_commande_url= "http://192.168.1.3:8888/PhpScriptsTable/add_commande.php";
    private String add_commande_off_url= "http://192.168.1.3:8888/PhpScriptsTable/add_commande_offline.php";
    private String info_resto_url="http://192.168.1.3:8888/PhpScriptsTable/info_restaurant.php";
    private String add_avis_url="http://192.168.1.3:8888/PhpScriptsTable/add_avis.php";
    private AsyncResponse response;
    private Context context;



    public BackgroundMenuData(AsyncResponse resp,Context c){
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

           case "menu_online":
            {

                try {

                    String id_client =(String)param[1];
                    String value = URLEncoder.encode("id_client","UTF-8")+ "=" +URLEncoder.encode(id_client,"UTF-8");
                    return sendRequest(menu_online_url,value);


                } catch (IOException e) {
                    e.printStackTrace();
                }

            }break;

            case "favoris":
            {
                try {

                    String id_client =(String)param[1];
                    String id_plat =(String)param[2];

                    String value = URLEncoder.encode("id_client","UTF-8")+ "=" +URLEncoder.encode(id_client,"UTF-8")+"&"+
                            URLEncoder.encode("id_plat","UTF-8")+ "=" +URLEncoder.encode(id_plat,"UTF-8");
                    return sendRequest(favoris_url,value);


                } catch (IOException e) {
                    e.printStackTrace();
                }

            }break;

            case "details":
            {
                try {

                    String id_plat =(String)param[1];
                    String value = URLEncoder.encode("id_plat","UTF-8")+ "=" +URLEncoder.encode(id_plat,"UTF-8");
                    return sendRequest(details_url,value);


                } catch (IOException e) {
                    e.printStackTrace();
                }

            }break;

            case "add_commande":
            {
                try {

                    String id_client =(String)param[1];
                    Commande commande=(Commande)param[2];

                    JSONArray array= getJSONArray(commande.getPlats_commandes());

                    String value = URLEncoder.encode("id_client","UTF-8")+ "=" +URLEncoder.encode(id_client,"UTF-8")+"&"+
                            URLEncoder.encode("date","UTF-8")+ "=" +URLEncoder.encode(commande.getDate(),"UTF-8")+"&"+
                            URLEncoder.encode("heure","UTF-8")+ "=" +URLEncoder.encode(commande.getHeure(),"UTF-8")+"&"+
                            URLEncoder.encode("montant","UTF-8")+ "=" +URLEncoder.encode(commande.getMontant(),"UTF-8")+"&"+
                            URLEncoder.encode("plats_commandes","UTF-8")+ "=" +URLEncoder.encode(array.toString(),"UTF-8");

                    return sendRequest(add_commande_url,value);


                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }

            }break;

            case "add_commande_off":
            {
                try {

                    Commande commande=(Commande)param[1];

                    JSONArray array= getJSONArray(commande.getPlats_commandes());

                    String value = URLEncoder.encode("date","UTF-8")+ "=" +URLEncoder.encode(commande.getDate(),"UTF-8")+"&"+
                            URLEncoder.encode("heure","UTF-8")+ "=" +URLEncoder.encode(commande.getHeure(),"UTF-8")+"&"+
                            URLEncoder.encode("montant","UTF-8")+ "=" +URLEncoder.encode(commande.getMontant(),"UTF-8")+"&"+
                            URLEncoder.encode("plats_commandes","UTF-8")+ "=" +URLEncoder.encode(array.toString(),"UTF-8");

                    return sendRequest(add_commande_off_url,value);


                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }

            }break;

            case "info_resto":
            {

                try {

                    return sendRequestWithoutData(info_resto_url);


                } catch (IOException e) {
                    e.printStackTrace();
                }

            }break;

            case "add_avis":
            {
                try {

                    String id_client =(String)param[1];
                    Avis avis=(Avis)param[2];


                    String value = URLEncoder.encode("id_client","UTF-8")+ "=" +URLEncoder.encode(id_client,"UTF-8")+"&"+
                            URLEncoder.encode("date","UTF-8")+ "=" +URLEncoder.encode(avis.getDate_avis(),"UTF-8")+"&"+
                            URLEncoder.encode("heure","UTF-8")+ "=" +URLEncoder.encode(avis.getHeure_avis(),"UTF-8")+"&"+
                            URLEncoder.encode("comment","UTF-8")+ "=" +URLEncoder.encode(avis.getComment(),"UTF-8")+"&"+
                            URLEncoder.encode("nb_etoile","UTF-8")+ "=" +URLEncoder.encode(avis.getNb_etoile(),"UTF-8");


                    return sendRequest(add_avis_url,value);


                } catch (IOException e) {
                    e.printStackTrace();
                }

            }break;



        }

        return "";
    }

    @Override
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
                case "menu_online_ok": {

                    JSONArray  array= jsonObject.getJSONArray("0");

                    ArrayList<Plat> plats= new ArrayList<>();

                    JSONObject value;

                    for (int i=0;i<array.length();i++)

                    {
                        value =array.getJSONObject(i);

                        plats.add(new Plat(value.getString("id"),value.getString("designation"),value.getString("photo"),
                                value.getString("categorie"),value.getString("modeCuisson"),value.getString("tempsCuisson"),
                                value.getString("smallPrice"),value.getString("mediumPrice"),value.getString("largePrice")
                                ,value.getString("fav")));

                    }

                    response.processFinish(plats,"menu_online");

                }break;


                case "favoris_ok":
                {

                    response.processFinish(jsonObject.getString("message"),"ok");

                }break;

                case "plat_detail_ok":
                {

                    JSONArray  arrayIng= jsonObject.getJSONArray("0");

                    ArrayList<String> details= new ArrayList<>();
                    StringBuilder  builder=new StringBuilder();
                    StringBuilder builder1=new StringBuilder();
                    StringBuilder builder2=new StringBuilder();
                    JSONObject value;

                    for (int i=0;i<arrayIng.length();i++)

                    {
                        value =arrayIng.getJSONObject(i);
                        builder.append("-");
                        builder.append(value.getString("nom_ing"));
                        builder.append("\n");

                    }

                    details.add(builder.toString());
                    JSONArray  arrayAll= jsonObject.getJSONArray("1");


                    for (int i=0;i<arrayAll.length();i++)

                    {
                        value =arrayAll.getJSONObject(i);
                        builder1.append("- ");
                        builder1.append(value.getString("nom_allergie"));
                        builder1.append("\n");
                    }

                    details.add(builder1.toString());


                    JSONArray  arrayCal= jsonObject.getJSONArray("2");

                    StringBuilder builder3=new StringBuilder();
                    StringBuilder builder4=new StringBuilder();
                    StringBuilder builder5=new StringBuilder();
                    for (int i=0;i<arrayCal.length();i++)

                    {
                        value =arrayCal.getJSONObject(i);


                        if(value.getString("portion").equals("small")){

                            builder3.append("- ");
                            builder3.append("Petite portion");
                            builder3.append(" : ");
                            builder3.append(value.getString("calories"));
                            builder3.append(" Kcal \n");

                        }

                        if(value.getString("portion").equals("medium")){

                            builder4.append("- ");
                            builder4.append("Moyenne portion");
                            builder4.append(" : ");
                            builder4.append(value.getString("calories"));
                            builder4.append(" Kcal \n");
                        }

                        if(value.getString("portion").equals("large")){

                            builder5.append("- ");
                            builder5.append("Grande portion");
                            builder5.append(" : ");
                            builder5.append(value.getString("calories"));
                            builder5.append(" Kcal \n");
                        }


                    }

                    builder2.append(builder3);
                    builder2.append(builder4);
                    builder2.append(builder5);

                    details.add(builder2.toString());
                    response.processFinish(details,"details");


                }break;

                case "add_commande_ok":
                {

                    response.processFinish("Votre commande est lancée...","commande");

                }break;
                case "add_commande_off_ok":
                {

                    response.processFinish("Votre commande est lancée...","commande");


                }break;

                case "info_ok": {

                    JSONArray array=jsonObject.getJSONArray("0");
                    JSONObject value;
                    value=array.getJSONObject(0);

                    Restaurant restaurant= new Restaurant(value.getString("nom"),value.getString("photo"),value.getString("desc"),
                            value.getString("email"), value.getString("tel"),value.getString("ouvre"),value.getString("ferme"));

                    response.processFinish(restaurant,"infos");

                }break;

                case "add_avis_ok":
                {
                    response.processFinish("Avis ajouté.","avis");

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

    private  JSONArray getJSONArray(ArrayList<Plat> list) throws JSONException {

        JSONObject object;
        JSONArray array=new JSONArray();


        int i=0;

        ArrayList<Plat> pref = new ArrayList<>(list);

        for (Plat plat :pref) {

            object=new JSONObject();

            object.put("id",plat.getId_plat());
            object.put("qte",plat.getSelected_quantity());
            object.put("portion",plat.getSelected_portion());

            array.put(i,object);
            i++;

        }

        return array;
    }

    //IMPLEMENTING AN INTERFACE TO GET THE CODE RESULT IN ANOTHER ACTIVITY
    public interface AsyncResponse {

        void processFinish(Object result,String value);

    }


}