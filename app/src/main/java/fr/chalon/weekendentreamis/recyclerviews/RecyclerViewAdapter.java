package fr.chalon.weekendentreamis.recyclerviews;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Map;

import fr.chalon.weekendentreamis.R;


public class RecyclerViewAdapter extends RecyclerView.Adapter {

    public RecyclerViewAdapter(Context context, RecyclerViewHolderActions actions) {
        this.context = context;
        this.actions = actions;
    }

    private Map<Long, String> data;
    private Context context;
    private RecyclerViewHolderActions actions;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // On récupère la vue qui contient un list-item pour la recycler view.
        View v = LayoutInflater.from(this.context).inflate(R.layout.recycler_view_list_item, viewGroup, false);
        return new RecyclerViewHolder(v, this.actions);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Map.Entry<Long,String> pair = (Map.Entry<Long,String>)this.data.entrySet().toArray()[i];

        ((RecyclerViewHolder)viewHolder).setTextValue(pair.getValue());
        ((RecyclerViewHolder)viewHolder).setId(pair.getKey());

        ((RecyclerViewHolder)viewHolder).getTextView().setOnClickListener(v -> ((RecyclerViewHolder)viewHolder).textViewOnClick());
        ((RecyclerViewHolder)viewHolder).getBtnEdit().setOnClickListener(v -> ((RecyclerViewHolder)viewHolder).buttonEditOnClick());
        ((RecyclerViewHolder)viewHolder).getBtnRemove().setOnClickListener(v -> ((RecyclerViewHolder)viewHolder).buttonRemoveOnClick());
    }

    @Override
    public int getItemCount() {
        if (this.data != null)
        {
            return this.data.size();

        }
        return 0;
    }

    public void setData(Map<Long, String> data)
    {

        this.data = data;
        notifyDataSetChanged();
    }
}

