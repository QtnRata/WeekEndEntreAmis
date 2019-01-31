package fr.chalon.weekendentreamis.recyclerviews;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.util.List;

import fr.chalon.weekendentreamis.ParticipantsListFragment;
import fr.chalon.weekendentreamis.R;


public class RecyclerViewAdapter extends RecyclerView.Adapter {

    public RecyclerViewAdapter(Context context, Class<?> activityTarget) {
        this.context = context;
        this.activityTarget = activityTarget;
    }

    private List<String> data;
    private Context context;
    private Class<?> activityTarget;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // On récupère la vue qui contient un list-item pour la recycler view.
        View v = LayoutInflater.from(this.context).inflate(R.layout.recycler_view_list_item, viewGroup, false);
        return new RecyclerViewHolder(v, this.activityTarget);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        // On modifie les valeurs du viewHolder.
        ((RecyclerViewHolder)viewHolder).setTextValue(this.data.get(i));
    }

    @Override
    public int getItemCount() {
        if (this.data != null)
        {
            return this.data.size();
        }
        return 0;
    }

    public void setData(List<String> data)
    {
        this.data = data;
    }
}
