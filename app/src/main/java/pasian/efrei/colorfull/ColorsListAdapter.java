package pasian.efrei.colorfull;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


public class ColorsListAdapter extends RecyclerView.Adapter<ColorsListAdapter.ViewHolder>{

    private ColorTuple[] colors;
    ClipboardManager clipboard;
    Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public Button mButton;
        public ViewHolder(Button v) {
            super(v);
            mButton = v;
        }
    }

    public ColorsListAdapter(String[] names, int[] values, Context context){
        this.context = context;
        int length = names.length;
        colors = new ColorTuple[length];
        for( int i = 0 ; i < length; i++){
            colors[i] = new ColorTuple(names[i],values[i]);
        }

        clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);

    }

    @Override
    public ColorsListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Button v = (Button) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.color, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(ColorsListAdapter.ViewHolder holder, int position) {
        String toPrompt = colors[position].colorName;
        holder.mButton.setText((CharSequence) toPrompt);
        holder.mButton.setBackgroundColor(colors[position].colorValue);
        /*if(colors[position].colorName == "BLACK"){
            holder.mButton.setTextColor(0xFFFFFF);
        }else{
            holder.mButton.setTextColor(0x000000);
        }*/
        final int pos = position;
        holder.mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipData clip = ClipData.newPlainText(colors[pos].colorName, String.format("#%06X", (0xFFFFFF & colors[pos].colorValue)));
                clipboard.setPrimaryClip(clip);
                Toast.makeText(context.getApplicationContext(), "Color hex code copied in clipboard", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return colors.length;
    }
}
