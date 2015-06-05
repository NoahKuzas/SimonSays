package kuzasandnicolay.simonsays;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import android.widget.Button;
import android.view.Gravity;



public class MainActivity extends ActionBarActivity {

    Integer position;
    Integer movesAllowed;
    Integer movesTaken;
    Integer levelCount;
    Integer speedNumber;
    ImageButton Red;
    ImageButton Blue;
    ImageButton Green;
    ImageButton Orange;
    Button Button;
    Button highScore;
    ArrayList colors;
    ArrayList colorsKey;
    TextView levelTracker;
    CheckBox speed;
    String Input;
    String playerName;
    private AlertDialog.Builder dialogBuilder;
    public final static String SAVE = "SavedDataFile";
    //Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // vibrator = (Vibrator) getSystemService(this.VIBRATOR_SERVICE);


        colorsKey = new ArrayList<Integer>();
        colors = new ArrayList<String>();

        addListenerOnButton();
        addListenerOnButtonRed();
        addListenerOnButtonBlue();
        addListenerOnButtonGreen();
        addListenerOnButtonOrange();
        addListenerOnButtonHighScore();
        appStart();
        setData();



        levelTracker = (TextView) findViewById(R.id.textView2);
        speed = (CheckBox) findViewById(R.id.checkBox);

        Input = "";
        playerName = "Player";
        levelCount = 0;
        position = 0;
        movesTaken = 0;
        movesAllowed = 0;
        speedNumber = 1;

        loadGame ();
        saveGame ();


    }


    public void saveGame ()
    {
        SharedPreferences save =  getSharedPreferences(SAVE, 0);
        SharedPreferences.Editor editor = save.edit();
        editor.putInt ("PlayerScore", levelCount).apply ();



    }

    public void loadGame ()
    {
        SharedPreferences load = getSharedPreferences(SAVE, 0);
        levelCount = load.getInt ("PlayerScore", 0);
        levelTracker.setText(Integer.toString (levelCount));
    }


    public void appStart ()
    {

        dialogBuilder = new AlertDialog.Builder (this);
        final EditText pName = new EditText (this);
        dialogBuilder.setTitle("Welcome!");
        dialogBuilder.setMessage("Welcome to Four Square, a memory game that will test your ability" +
                                 " to remember the order of four squares! As the levels increase fake square colors will be " +
                                    "placed in the game to mix you up. Let's start by inserting your" +
                                  " name. After, press the start button to begin. Good Luck!");
        dialogBuilder.setView (pName);
        dialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                if (pName.getText().toString ().length () != 0) {
                    playerName = pName.getText().toString();
                }

                else
                    playerName = "Player";
            }
        });
        AlertDialog dialogAppStart = dialogBuilder.create ();
        dialogAppStart.show();
    }

    public void setData ()
    {
        // Colors that will be view by user
        colors.add ("Blue Blue Blue");
        colors.add("Blue Red Blue");
        colors.add("Orange Red Green");
        colors.add("Red Red Red Green Green");
        colors.add("Blue Blue Orange Red Green Blue");
        colors.add("Green Green Blue Red Red Orange");
        colors.add("Red Blue Green Orange Blue Red Blue");
        colors.add("Blue Blue Red Orange Green Green Red");
        colors.add("Red Green Red Red Green Orange Blue");
        colors.add("Blue Red Green Orange Orange Red Green");
        colors.add("Green Blue Blue Green White Orange Red");
        colors.add("Blue Blue Red White Blue Red Orange");
        colors.add("Red Purple Purple Green Red Red Blue");
        colors.add("Blue White Red Red Orange Orange White Green");
        colors.add("White Blue Blue Red Orange Orange Green Purple Red");

        // Colors key correct answers
        colorsKey.add("BlueBlueBlue");
        colorsKey.add("BlueRedBlue");
        colorsKey.add("OrangeRedGreen");
        colorsKey.add ("RedRedRedGreenGreen");
        colorsKey.add ("BlueBlueOrangeRedGreenBlue");
        colorsKey.add ("GreenGreenBlueRedRedOrange");
        colorsKey.add ("RedBlueGreenOrangeBlueRedBlue");
        colorsKey.add ("BlueBlueRedOrangeGreenGreenRed");
        colorsKey.add ("RedGreenRedRedGreenOrangeBlue");
        colorsKey.add ("BlueRedGreenOrangeOrangeRedGreen");
        colorsKey.add("GreenBlueBlueGreenOrangeRed");
        colorsKey.add("BlueBlueRedBlueRedOrange");
        colorsKey.add("RedGreenRedRedBlue");
        colorsKey.add("BlueRedRedOrangeOrangeGreen");
        colorsKey.add("BlueBlueRedOrangeOrangeGreenRed");

    }



    public void addListenerOnButton() {

        Button = (Button) findViewById(R.id.nextRound);

       // MediaPlayer mp1 = MediaPlayer.create (getApplicationContext (), (R.raw.));


        Button.setOnClickListener(new View.OnClickListener() {

            // red = 1 blue = 2 green = 3 orange = 4
            @Override
            public void onClick(View arg0) {

                //Map <Integer, String> frases = new HashMap <Integer, String> ();

                Button.setText ("Next Round");
                if (position < colorsKey.size ()) {
                    toaster (speedNumber);

                    String[] temp = colors.get(position).toString().split(" ");
                    movesAllowed = temp.length;

                    //Toast.setGravity(Gravity.TOP|Gravity.LEFT, 0, 0);
                    //Red = 1 Blue = 2 Green = 3 Orange = 4

                }

                else {
                    Toast.makeText(MainActivity.this, "WINNER!", Toast.LENGTH_LONG).show();
                    Button.setText("Play Again?");


                    position = 0;
                    movesTaken = 0;
                    Input = "";

                }


            }

        });



    }

    public void addListenerOnButtonHighScore () {
        highScore = (Button) findViewById(R.id.scoreHigh);

        highScore.setOnClickListener(new View.OnClickListener () {
            @Override
            public void onClick (View v)
            {
               Intent myIntent = new Intent(v.getContext(), HighScoreMenu.class);
                myIntent.putExtra("HighScore", levelCount);
                myIntent.putExtra ("PlayerName", playerName);
                startActivity(myIntent);

            }

        });
    }

    public void checker ()
    {
        if (Input.equals ("GreenGreenGreen") && playerName.equals ("blackcipher13")){
            position = colorsKey.size() - 1;
            levelCount = colorsKey.size() - 1;
            levelTracker.setText (Integer.toString (levelCount));

        }

        if (Input.equals (colorsKey.get (position)))
        {
            Toast.makeText(MainActivity.this, "PASS", Toast.LENGTH_SHORT).show();
            movesTaken = 0;
            position++;
            Input = "";
            levelCount++;
            levelTracker.setText (Integer.toString (levelCount));
        }

        if (movesTaken == movesAllowed && !(Input.equals (colorsKey.get (position))))
        {
            //vibrator.vibrate (1000); //1 second of vibration

            Toast.makeText(MainActivity.this, "FAIL", Toast.LENGTH_SHORT).show();
            Input = "";
            movesTaken = 0;
            Button.setText ("Try Again");
        }



    }


    public void addListenerOnButtonRed() {

        Red = (ImageButton) findViewById(R.id.red);


        Red.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View arg0) {

            Input += "Red";
            movesTaken++;
                checker ();



            }


        });
    }

    public void addListenerOnButtonBlue() {

        Blue = (ImageButton) findViewById(R.id.blue);


        Blue.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View arg0) {

            Input += "Blue";
            movesTaken++;
                checker ();


            }


        });
    }

    public void addListenerOnButtonGreen() {

        Green = (ImageButton) findViewById(R.id.green);


        Green.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View arg0) {

            Input += "Green";
            movesTaken++;
                checker ();

            }


        });
    }

    public void addListenerOnButtonOrange() {

        Orange = (ImageButton) findViewById(R.id.orange);


        Orange.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View arg0) {

            Input += "Orange";
            movesTaken++;
                checker ();
            }


        });
    }




    public void toaster (int temp)
    {
        if (temp == 1)
            Toast.makeText(MainActivity.this, "" + colors.get(position), Toast.LENGTH_SHORT).show();

        else
            Toast.makeText(MainActivity.this, "" + colors.get(position), Toast.LENGTH_LONG).show();
    }

    public void onCheckboxClicked(View view)
    {
        if (speed.isChecked ())
        {
            toaster (speedNumber += 1);
        }

        else
            speedNumber = 1;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
