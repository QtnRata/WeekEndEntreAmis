package fr.chalon.weekendentreamis.recyclerviewadapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import fr.chalon.weekendentreamis.R;


public class ParticipantsRecyclerViewAdapter extends RecyclerView.Adapter {

    public ParticipantsRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    private ArrayList<String> data;
    private Context context;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(this.context).inflate(R.layout.recycler_view_list_item, viewGroup);
        return new ParticipantsRecyclerViewViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        // go chercher tes données dans la liste avec i, et tu refourgues tout au viewHolder
        ((ParticipantsRecyclerViewViewHolder)viewHolder).text.setText(this.data.get(i));
    }

    @Override
    public int getItemCount() {
        if (this.data != null)
        {
            return this.data.size();
        }
        return 0;
    }
}
