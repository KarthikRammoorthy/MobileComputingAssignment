package com.example.karthikrammoorthy.helloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



public class MainActivity extends AppCompatActivity {

//    ----- Declare variables for colorchanger event ------

    private boolean clicked;
    private Button colorChanger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        colorChanger = findViewById(R.id.btnChangeText);
        clicked = false;

        colorChanger.setOnClickListener(new View.OnClickListener() {

            public void onClick (View v){

                if(!clicked){
                    colorChanger.setText(getResources().getText(R.string.btn_clicked));
                    colorChanger.setBackgroundColor(getResources().getColor(R.color.colorRed,getResources().newTheme()));
                    clicked = true;
                }
                else {
                    colorChanger.setText(getResources().getText(R.string.btn_not_clicked));
                    colorChanger.setBackgroundColor(getResources().getColor(R.color.colorGray,getResources().newTheme()));
                    clicked = false;
                }

            }
        });


    }








}
