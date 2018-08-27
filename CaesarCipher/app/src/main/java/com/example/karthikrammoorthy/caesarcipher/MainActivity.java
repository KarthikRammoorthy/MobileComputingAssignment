package com.example.karthikrammoorthy.caesarcipher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    /*
    * Declare input values and handlers
     */

    private TextView encrypt_decrypt_output;
    private EditText input_value;
    private TextView shift_value;
    private SeekBar seek_bar;
    private Button encrypt_decrypt;
    private CaesarCipher caesarCipher;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

     encrypt_decrypt_output = findViewById(R.id.encrypt_decrypt_value);
     input_value = findViewById(R.id.input_value);
     shift_value = findViewById(R.id.shift_value);
     seek_bar = findViewById(R.id.seek_bar);
     encrypt_decrypt = findViewById(R.id.encrypt_decrypt);
     caesarCipher = new CaesarCipher();

     shift_value.setText(String.valueOf(seek_bar.getProgress()));
     input_value.setFilters(new InputFilter[]{new InputFilter.AllCaps()});

     seek_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
         @Override
         public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
             shift_value.setText(String.valueOf(progress));
         }

         @Override
         public void onStartTrackingTouch(SeekBar seekBar) {

         }

         @Override
         public void onStopTrackingTouch(SeekBar seekBar) {

         }
     });


        encrypt_decrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

             caesarCipher.setInput(input_value.getText().toString());
             caesarCipher.setShiftVlaue(seek_bar.getProgress());
             caesarCipher.caesar();
             encrypt_decrypt_output.setText(caesarCipher.getCryptedInput());


            }
        });








    }


}
