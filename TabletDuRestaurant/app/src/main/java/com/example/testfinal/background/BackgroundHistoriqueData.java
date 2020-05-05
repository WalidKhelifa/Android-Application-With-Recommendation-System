package com.example.testfinal.background;

import android.content.Context;
import android.os.AsyncTask;
import com.example.testfinal.models.Commande;
import com.example.testfinal.models.Plat;
import com.example.testfinal.models.Repas;
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

public class BackgroundHistoriqueData extends AsyncTask<Object,Void,String> {

    private Context context;
    private String commandes_url="http://192.168.43.225/PhpScriptsTable/get_user_commandes.php";
    private String add_repas_url="http://192.168.43.225/PhpScriptsTable/add_user_repas.php";
    private String delete_repas_url="http://192.168.43.225/PhpScriptsTable/delete_user_repas.php";
    private String get_repas_url="http://192.168.43.225/PhpScriptsTable/get_user_repas.php";
    private String modify_repas_url="http://192.168.43.225/PhpScriptsTable/modify_user_repas.php";
    private BackgroundHistoriqueData.AsyncResponse response;


    //CONSTRUCTOR
    public BackgroundHistoriqueData(Context c, BackgroundHistoriqueData.AsyncResponse resp){

        response=resp;
        context=c;
    }

    @Override
    protected String doInBackground(Object... param) {

        String method =(String)param[0];

        switch (method) {

            case "get_user_commandes": {

                try {

                    String id_client = (String) param[1];

                    String value = URLEncoder.encode("id_client", "UTF-8") + "=" + URLEncoder.encode(id_client, "UTF-8");

                    return sendRequest(commandes_url,value);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            break;

            case "get_user_repas": {

                try {

                    String id_client = (String) param[1];

                    String value = URLEncoder.encode("id_client", "UTF-8") + "=" + URLEncoder.encode(id_client, "UTF-8");

                    return sendRequest(get_repas_url,value);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            break;

            case "add_user_repas":{

                String id_client =(String)param[1];
                Repas repas=(Repas)param[2];

                JSONArray array= null;
                try {
                    array = getJSONArray(repas.getPlats());


                String value = URLEncoder.encode("id_client","UTF-8")+ "=" +URLEncoder.encode(id_client,"UTF-8")+"&"+
                        URLEncoder.encode("nom_repas","UTF-8")+ "=" +URLEncoder.encode(repas.getNom(),"UTF-8")+"&"+
                        URLEncoder.encode("repas_client","UTF-8")+ "=" +URLEncoder.encode(array.toString(),"UTF-8");

                return sendRequest(add_repas_url,value);


                } catch (JSONException | IOException e) {
                    e.printStackTrace();
                }
            }


            case "delete_user_repas":{

                String id_client =(String)param[1];
                String nom=(String) param[2];

                try {

                    String value = URLEncoder.encode("id_client","UTF-8")+ "=" +URLEncoder.encode(id_client,"UTF-8")+"&"+
                            URLEncoder.encode("nom_repas","UTF-8")+ "=" +URLEncoder.encode(nom,"UTF-8");

                    return sendRequest(delete_repas_url,value);


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            case "modify_user_repas":{

                String id_client =(String)param[1];
                String ancien_nom=(String) param[2];
                String nouveau_nom=(String) param[3];

                try {

                    String value = URLEncoder.encode("id_client","UTF-8")+ "=" +URLEncoder.encode(id_client,"UTF-8")+"&"+
                            URLEncoder.encode("nom_repas","UTF-8")+ "=" +URLEncoder.encode(ancien_nom,"UTF-8")+"&"+
                            URLEncoder.encode("new_nom_repas","UTF-8")+ "=" +URLEncoder.encode(nouveau_nom,"UTF-8");

                    return sendRequest(modify_repas_url,value);


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }


    @Override
    protected void onPostExecute(String json) {

        //to decode json data
        try {

            JSONObject jsonObject = new JSONObject(json);

            switch (jsonObject.getString("success"))
            {
                case "GUC1": {

                  JSONArray commande_array= jsonObject.getJSONArray("0");
                  JSONObject value,value2;
                  ArrayList<Commande> liste_commandes=new ArrayList<>();
                  Commande commande;
                  Plat plat;


                    for (int i=0;i<commande_array.length();i++)
                    {

                        value=commande_array.getJSONObject(i);

                        commande= new Commande(value.getString("id"),
                                value.getString("date"),value.getString("heure"),value.getString("montant"),value.getString("type"));

                        JSONArray plats_commandes= value.getJSONArray("liste_plats");

                        for (int j=0;j<plats_commandes.length();j++)
                        {
                            value2=plats_commandes.getJSONObject(j);

                            plat=new Plat(value2.getString("id"),value2.getString("nom"),value2.getString("portion_select"),
                                    value2.getString("prix_portion_select"),value2.getString("qte_select"));


                            commande.getPlats_commandes().add(plat);
                        }

                        liste_commandes.add(commande);
                    }

                     response.processFinish(liste_commandes,"historique");

                }break;


                case "GUR1": {

                    JSONArray repas_array= jsonObject.getJSONArray("0");
                    JSONObject value,value2;
                    ArrayList<Repas> liste_repas=new ArrayList<>();
                    Repas repas;
                    Plat plat;


                    for (int i=0;i<repas_array.length();i++)
                    {

                        value=repas_array.getJSONObject(i);

                        repas= new Repas(value.getString("id"),value.getString("nom"));

                        JSONArray plats_repas= value.getJSONArray("liste_plats");

                        for (int j=0;j<plats_repas.length();j++)
                        {
                            value2=plats_repas.getJSONObject(j);

                            plat=new Plat(value2.getString("id"),value2.getString("nom"),value2.getString("portion_select"),
                                    value2.getString("prix_portion_select"),value2.getString("qte_select"));


                            repas.getPlats().add(plat);
                        }

                        liste_repas.add(repas);
                    }

                    response.processFinish(liste_repas,"repas");

                }break;


                case "GUC0":
                {
                    response.processFinish("nonHistorique","nonHistorique");

                }break;

                case "GUR0":
                {
                    response.processFinish("nonHistorique","nonRepas");

                }break;

                case "AUR1":
                {

                    response.processFinish("ajout","Repas ajouté..");

                }break;
                case "DUR1":
                {

                    response.processFinish("suppress","Repas supprimé..");
                }break;

                case "MUR1":
                {

                    response.processFinish("modify","Repas modifié..");

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
        BufferedWriter writer =  new BufferedWriter(new OutputStreamWriter(outputStream,Charset.forName("UTF-8")));


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

        void processFinish(Object result, String type);
    }


}
