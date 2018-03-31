package pasian.efrei.colorfull;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.os.AsyncTask;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class Schemer extends AppCompatActivity {

    ProgressBar progressBar;
    Button col1;
    Button col2;
    Button col3;
    String hex;
    TextView errmsg;

    public class APIComm extends AsyncTask<Void,Void,String> {

        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
            errmsg.setVisibility(View.INVISIBLE);
            col1.setText("");
            col2.setText("");
            col3.setText("");



        }

        protected void onPostExecute(String response) {
            if(response == null) {
                response = "THERE WAS AN ERROR";
            }
            progressBar.setVisibility(View.GONE);
            Log.i("INFO", response);
            col1.setText(response);
        }


        @Override
        protected String doInBackground(Void... voids) {
            //Validate
            if(hex.length() == 6 ){

            }else if ( hex.length() == 7) {
                String temp = hex.substring(1);
                hex = temp;

            }else{
                errmsg.setVisibility(View.VISIBLE);
            }

            try {
                URL url = new URL("http://www.thecolorapi.com/scheme?" + "hex=" + hex);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();
                    return stringBuilder.toString();
                }
                finally{
                    urlConnection.disconnect();
                }
            }
            catch(Exception e) {
                Log.e("ERROR", e.getMessage(), e);
                return null;
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schemer);
        progressBar = findViewById(R.id.pBar);
        col1 = findViewById(R.id.col1);
        col1 = findViewById(R.id.col2);
        col1 = findViewById(R.id.col3);
        hex = ((EditText)findViewById(R.id.inputHex)).getText().toString();
        errmsg = findViewById(R.id.errorMSG);

        Button fnd = findViewById(R.id.schemer_btn);

        fnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    public void run() {
                        APIComm comm = new APIComm();
                        comm.execute();

                    }
                }).start();
            }
        });

    }
}
