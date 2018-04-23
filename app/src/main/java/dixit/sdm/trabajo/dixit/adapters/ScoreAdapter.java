package dixit.sdm.trabajo.dixit.adapters;


import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import dixit.sdm.trabajo.dixit.R;
import dixit.sdm.trabajo.dixit.Database.Score;

public class ScoreAdapter extends ArrayAdapter {

    private List<Score> list;
    private int layout;
    private Context context;

    private class ViewHolder {
        TextView tvScoreName;
        TextView tvScoreScore;
    }

    public ScoreAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);

        this.list = objects;
        this.context = context;
        this.layout = resource;
    }

    public View getView(int position, @Nullable View convertView , @NonNull ViewGroup parent) {
        View result = convertView;
        ViewHolder holder;

        if (convertView == null) {

            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            result = ((Activity) this.context).getLayoutInflater().inflate(this.layout, null);

            // Keep references for View elements in the layout
            holder = new ViewHolder();
            holder.tvScoreName = result.findViewById(R.id.tvName);
            holder.tvScoreScore = result.findViewById(R.id.tvScore);
            // Associate the ViewHolder to the View
            result.setTag(holder);
        }

        // Retrieve the ViewHolder from the View
        holder = (ViewHolder) result.getTag();
        // Populate the View with information from the required position of the data source
        holder.tvScoreName.setText(list.get(position).getName());
        holder.tvScoreScore.setText( Integer.toString(list.get(position).getScore()));

        // Return the View
        return result;
    }
}
