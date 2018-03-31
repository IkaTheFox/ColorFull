package pasian.efrei.colorfull;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Main_menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        Button gtL = (Button) findViewById(R.id.gotolistbutton);
        gtL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Main_menu.this.startActivity(new Intent(Main_menu.this,ColorList.class));
            }
        });

        /*Button gtS = (Button) findViewById(R.id.gotoSchemeButton);
        gtS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Main_menu.this.startActivity(new Intent(Main_menu.this,Schemer.class));
            }
        });*/

        ImageButton mainLogo = (ImageButton) findViewById(R.id.LauncherButton);
        mainLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Main_menu.this.startActivity(new Intent(Main_menu.this,MainActivity.class));
            }
        });
    }

}
