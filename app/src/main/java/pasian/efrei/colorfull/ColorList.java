package pasian.efrei.colorfull;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import static android.graphics.Color.parseColor;

public class ColorList extends AppCompatActivity {

    private RecyclerView rView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_list);
        rView = (RecyclerView) findViewById(R.id.color_list);
        rView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        rView.setLayoutManager(mLayoutManager);

        int[] colorsS = this.getResources().getIntArray(R.array.colors);

        mAdapter = new ColorsListAdapter(this.getResources().getStringArray(R.array.color_names),colorsS,this);
        rView.setAdapter(mAdapter);

    }
}
