package fr.chalon.weekendentreamis.recyclerviewadapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import fr.chalon.weekendentreamis.R;

class RecyclerViewHolder extends RecyclerView.ViewHolder {

    private TextView text;

    public RecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        text = itemView.findViewById(R.id.list_item_value);
    }
}