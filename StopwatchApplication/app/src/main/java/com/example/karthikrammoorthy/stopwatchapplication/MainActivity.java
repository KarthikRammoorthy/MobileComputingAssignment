package com.example.karthikrammoorthy.stopwatchapplication;

//import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //properties
    private Handler timerHandler;
    private ArrayAdapter<String> itemsAdapter;
    private TextView txtTimer;
    private Button btnStartPause,btnLapReset;


    //used to keep track of time

    private long millisecondTime, startTime, pausedTime, updateTime = 0;

    //used to display time
    private int seconds, minutes, milliseconds;

    //used to handle the state of the stopwatch
    private boolean stopWatchStarted, stopWatchPaused = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //only used in one place, so shouldnt be a global variable
        ListView lvLaps;

        //timerHandler is bound to a thread
        //used to schedule our Runnable to be executed after particular actions
        timerHandler = new Handler();

        //sets the layout for each item of the list view
        //itemsAdapter = new ArrayAdapter<>(Context context: )
        itemsAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1);
        txtTimer = findViewById(R.id.txtTimer);
        btnStartPause = findViewById(R.id.btnStartPause);
        btnLapReset = findViewById(R.id.btnLapReset);
        lvLaps = findViewById(R.id.lvLaps);

        //binds data from the Adapter to the ListView
        lvLaps.setAdapter(itemsAdapter);



        //handles stopwatch actions for starting and stopping

        btnStartPause.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                if (!stopWatchStarted || stopWatchPaused) {
                    stopWatchStarted = true;
                    stopWatchPaused = false;

                    startTime = SystemClock.uptimeMillis();

                    //enques the Runnable to be called by the message queue after the specified amount of time alapses
                    timerHandler.postDelayed(timerRunnable, 0);

                    //switch label strings
                    btnStartPause.setText(R.string.lblPause);
                    btnLapReset.setText(R.string.btnLAP);
                } else  {

                    pausedTime += millisecondTime;
                    stopWatchPaused = true;

                    //remove pending posts of timerRunnable in message queue
                    timerHandler.removeCallbacks(timerRunnable);

                    //switch label strings
                    btnStartPause.setText(R.string.lblStart);
                    btnLapReset.setText(R.string.lblReset);



                }
            }


        });


        btnLapReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //check if the action is to create a new lap

                if(stopWatchStarted && !stopWatchPaused){
                    String lapTime = minutes + ":"
                            + String.format("%02d", seconds) + ":"
                            + String.format("%03d", milliseconds);
                    itemsAdapter.add(lapTime);

                } else if(stopWatchStarted) {
                    stopWatchPaused = false;
                    stopWatchStarted = false;

                    //remove pending posts of timerRunnable in message queue
                    timerHandler.removeCallbacks(timerRunnable);

                    //reset all values
                    millisecondTime = 0;
                    startTime = 0;
                    milliseconds = 0;
                    pausedTime = 0;
                    updateTime = 0;
                    seconds = 0;
                    minutes = 0;

                    txtTimer.setText(R.string.lblTimer);
                    btnLapReset.setText(R.string.btnLAP);

                    itemsAdapter.clear();
                } else {
                    Toast.makeText(getApplicationContext(),"Timer hasn't starter yet!",Toast.LENGTH_SHORT).show();
                }

            }




    });




    }

    /*
    * Runnable interface Overriding the run() method should be implemented by the class
     */

    public Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
            millisecondTime = SystemClock.uptimeMillis() - startTime;

            //Values used to keep track of where the stopwatch time left off
            updateTime     = pausedTime + millisecondTime;
            milliseconds   = (int) (updateTime % 1000);
            seconds        = (int) (updateTime / 1000);


            //convert values to display
            minutes        = seconds / 60;
            seconds        = seconds % 60;
            String updatedTime = minutes + ":"
                    + String.format("%02d", seconds) + ":"
                    + String.format("%03d", milliseconds);


            txtTimer.setText(updatedTime);


            //enqueues the Runnable to be called by the message queue after the specified amount of time elapsed

            timerHandler.postDelayed(this,0);



        }
    };








}
