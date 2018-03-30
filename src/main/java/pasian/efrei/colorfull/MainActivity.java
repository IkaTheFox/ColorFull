package fr.univ_lille1.iuta.place.palette;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Spinner spinner;
    private CharSequence[] colorNames;
    private int[] colors;
    private int color;


    class Primary {
        private int value ;
        private SeekBar colorSpinner;
        private TextView textView;
        private SeekBar.OnSeekBarChangeListener seekBarListener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                value = progress;
                textView.setText("" + value);
                majButtonColor();
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        } ;

        public Primary(int seekBarId, int textViewId) {
            colorSpinner = (SeekBar) findViewById(seekBarId);
            textView = (TextView) findViewById(textViewId);
            value = 128;
            colorSpinner.setProgress(value);
            colorSpinner.setMax(255);
            textView.setText("" + value);
            colorSpinner.setOnSeekBarChangeListener(seekBarListener);
        }
        public int getValue() {
            return value;
        }
    }

    private Primary red;
    private Primary green;
    private Primary blue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        colorNames = this.getResources().getStringArray(R.array.color_names);
        colors = this.getResources().getIntArray(R.array.colors);
        spinner = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter spinnerAd = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.color_names));
        spinner.setAdapter(spinnerAd);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView demo = (TextView) findViewById(R.id.demo);
                demo.setText(spinner.getSelectedItem().toString());
                demo.setBackgroundColor(colors[(int) id]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        red = new Primary(R.id.Rouge, R.id.EditRouge);
        green = new Primary(R.id.Vert, R.id.EditVert);
        blue = new Primary(R.id.Bleu, R.id.EditBleu);
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

    private void majButtonColor() {
        ((Button) findViewById(R.id.lookup)).setBackgroundColor(Color.rgb(red.getValue(), green.getValue(), blue.getValue())) ;
    }

    public void seekClosestColor(View view){
        double min=1000000000;
        int res=0;
        int userRed = red.getValue();
        int userGreen = green.getValue();
        int userBlue = blue.getValue();
        double distance;
        for(int i=0;i<colors.length;i++){
            int toTest=colors[i];
            int toTestRed=Color.red(toTest);
            int toTestGreen=Color.green(toTest);
            int toTestBlue=Color.blue(toTest);

            if((distance=(Math.pow(userRed-toTestRed,2)+Math.pow(userGreen-toTestGreen,2)+Math.pow(userBlue-toTestBlue,2)))<min){
                res=i;
                min=distance;
            }
        }

        TextView demo = (TextView) findViewById(R.id.demo);
        demo.setText(colorNames[res]);
        demo.setBackgroundColor(colors[res]);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("Color", color);
    }

    @Override
    public void onRestoreInstanceState(Bundle inState) {
        super.onRestoreInstanceState(inState);
        color = inState.getInt("Color");
        ((Button) findViewById(R.id.lookup)).setBackgroundColor(color);
    }
}
