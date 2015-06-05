package kuzasandnicolay.simonsays;

import android.app.ListActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class HighScoreMenu extends ActionBarActivity {


    TextView highScoreTracker;
    TextView highPlayerTracker;
    String player;
    Integer score;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score_menu);
        //String [] highScores = {"HighScore 1 , HighScore 2"};
        /*ArrayAdapter <String> adapter = new ArrayAdapter<String>(getListView().getContext(), android.R.layout.simple_list_item_1,highScores);
        getListView ().setAdapter(adapter);*/

        highPlayerTracker = (TextView) findViewById(R.id.player1);
        highScoreTracker = (TextView) findViewById(R.id.highScore1);

        player = getIntent ().getExtras ().getString ("PlayerName");
        score = getIntent().getExtras().getInt("HighScore");

        highScoreTracker.setText (Integer.toString (score));
        highPlayerTracker.setText (player);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_high_score_menu, menu);
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
