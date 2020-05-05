package com.example.testfinal.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import com.example.testfinal.R;
import com.example.testfinal.models.CountryItem;
import com.example.testfinal.models.Donnees;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class JouerActivity extends AppCompatActivity {

    Button btn1,btn2,btn3,btn4;
    ImageView imageView;
    List<CountryItem> list;
    Random r = new Random();
    private Toolbar toolbar;
    int turn =1;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jouer);

        btn1 = (Button) findViewById(R.id.button1);
        btn2 = (Button) findViewById(R.id.button2);
        btn3 = (Button) findViewById(R.id.button3);
        btn4 = (Button) findViewById(R.id.button4);
        imageView = (ImageView) findViewById(R.id.imageView);
        toolbar = findViewById(R.id.toolbar);


        //SETUP THE TOOL BAR

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(clickBackListener); //button de retour


        list = new ArrayList<>();


        //////////
        for(int i = 0; i< new Donnees().answer.length; i++) {
            list.add(new CountryItem(new Donnees().answer[i], new Donnees().flags[i]));
        }
        Collections.shuffle(list);
        newQuestion(turn);



        ////////////////////////////////////////////

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btn1.getText().toString().equalsIgnoreCase(list.get(turn -1).getName()))
                {

                    if(turn <list.size())
                    {
                        turn++;
                        newQuestion(turn);
                    }else
                    {

                        Intent i = new Intent(JouerActivity.this, GagneActivity.class);
                        startActivity(i);


                    }
                }else
                {
                    Intent i = new Intent(JouerActivity.this, DivertissementActivity.class);
                    startActivity(i);
                }

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btn2.getText().toString().equalsIgnoreCase(list.get(turn -1).getName()))
                {

                    if(turn <list.size())
                    {
                        turn++;
                        newQuestion(turn);
                    }else
                    {
                        Intent i = new Intent(JouerActivity.this, GagneActivity.class);
                        startActivity(i);


                    }
                }else
                {

                    Intent i = new Intent(JouerActivity.this, DivertissementActivity.class);
                    startActivity(i);

                }
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(btn3.getText().toString().equalsIgnoreCase(list.get(turn -1).getName()))
                {

                    if(turn <list.size())
                    {
                        turn++;
                        newQuestion(turn);
                    }else
                    {
                        Intent i = new Intent(JouerActivity.this, GagneActivity.class);
                        startActivity(i);


                    }
                }else
                {
                    Intent i = new Intent(JouerActivity.this, DivertissementActivity.class);
                    startActivity(i);

                }
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(btn4.getText().toString().equalsIgnoreCase(list.get(turn -1).getName()))
                {

                    if(turn <list.size())
                    {
                        turn++;
                        newQuestion(turn);
                    }else
                    {
                        Intent i = new Intent(JouerActivity.this, GagneActivity.class);
                        startActivity(i);


                    }
                }else
                {
                    Intent i = new Intent(JouerActivity.this, DivertissementActivity.class);
                    startActivity(i);

                }
            }
        });






    }

    // retour
    View.OnClickListener clickBackListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            startActivity(new Intent(getApplicationContext(), DivertissementActivity.class));
            finish();

        }
    };
    private void newQuestion(int number)
    {
        imageView.setImageResource(list.get(number-1).getImage());

        int correct_answer = r.nextInt(4) +1;
        int firstbtn = number -1;
        int secondbtn;
        int thirdbtn;
        int forthbtn;

        switch (correct_answer)
        {
            case 1:
                btn1.setText(list.get(firstbtn).getName());

                do {
                    secondbtn = r.nextInt(list.size());
                }while (secondbtn==firstbtn);

                do {
                    thirdbtn = r.nextInt(list.size());
                }while (thirdbtn==firstbtn || thirdbtn==secondbtn );

                do {
                    forthbtn = r.nextInt(list.size());
                }while (forthbtn==firstbtn || forthbtn==secondbtn || forthbtn==thirdbtn );

                btn2.setText(list.get(secondbtn).getName());
                btn3.setText(list.get(thirdbtn).getName());
                btn4.setText(list.get(forthbtn).getName());

                break;

            case 2:
                btn2.setText(list.get(firstbtn).getName());

                do {
                    secondbtn = r.nextInt(list.size());
                }while (secondbtn==firstbtn);

                do {
                    thirdbtn = r.nextInt(list.size());
                }while (thirdbtn==firstbtn || thirdbtn==secondbtn );

                do {
                    forthbtn = r.nextInt(list.size());
                }while (forthbtn==firstbtn || forthbtn==secondbtn || forthbtn==thirdbtn );

                btn1.setText(list.get(secondbtn).getName());
                btn3.setText(list.get(thirdbtn).getName());
                btn4.setText(list.get(forthbtn).getName());

                break;

            case 3:
                btn3.setText(list.get(firstbtn).getName());

                do {
                    secondbtn = r.nextInt(list.size());
                }while (secondbtn==firstbtn);

                do {
                    thirdbtn = r.nextInt(list.size());
                }while (thirdbtn==firstbtn || thirdbtn==secondbtn );

                do {
                    forthbtn = r.nextInt(list.size());
                }while (forthbtn==firstbtn || forthbtn==secondbtn || forthbtn==thirdbtn );

                btn2.setText(list.get(secondbtn).getName());
                btn1.setText(list.get(thirdbtn).getName());
                btn4.setText(list.get(forthbtn).getName());

                break;

            case 4:
                btn4.setText(list.get(firstbtn).getName());

                do {
                    secondbtn = r.nextInt(list.size());
                }while (secondbtn==firstbtn);

                do {
                    thirdbtn = r.nextInt(list.size());
                }while (thirdbtn==firstbtn || thirdbtn==secondbtn );

                do {
                    forthbtn = r.nextInt(list.size());
                }while (forthbtn==firstbtn || forthbtn==secondbtn || forthbtn==thirdbtn );

                btn2.setText(list.get(secondbtn).getName());
                btn3.setText(list.get(thirdbtn).getName());
                btn1.setText(list.get(forthbtn).getName());

                break;


        }

    }

}
