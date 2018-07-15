package menu.com.girish.shareddemo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {
    private TextView textViewNews,textViewHeading;
    private SeekBar seekBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewNews = findViewById(R.id.textViewNews);
        textViewHeading = findViewById(R.id.textView);
        seekBar = findViewById(R.id.seekBar);

        //Add listener to seekbar to increase and to decrese font size
        //use OnSeekBarChangeListener
        seekBar.setOnSeekBarChangeListener(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //2. Check for last saved preferences
        SharedPreferences sharedPreferences = getSharedPreferences("my_pref",MODE_PRIVATE);
        int size = sharedPreferences.getInt("f_size",15);
        textViewNews.setTextSize(size);
        seekBar.setProgress(size);


        ///Using Default Preference.
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        String strSize = settings.getString("list_preference_1","25");
        float  def_size =  Integer.parseInt(strSize);
        textViewHeading.setTextSize(def_size);
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
            startActivity(new Intent(this,MyPreferenceActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //Listener methods from SeekBarChangeListener
    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        textViewNews.setTextSize(i);
        //1. Create sharedpreferences
        SharedPreferences sharedPreferences = getSharedPreferences("my_pref",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("f_size",i);
        editor.apply();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
