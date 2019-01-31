package fr.chalon.weekendentreamis.recyclerviews;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import fr.chalon.weekendentreamis.MainActivity;
import fr.chalon.weekendentreamis.R;

class RecyclerViewHolder extends RecyclerView.ViewHolder {

    private TextView textView;
    private Class<?> activityTarget;

    public RecyclerViewHolder(@NonNull View itemView, Class<?> activityTarget) {
        super(itemView);
        textView = itemView.findViewById(R.id.list_item_value);
        this.activityTarget = activityTarget;
        this.textView.setOnClickListener(e -> {
            Intent i = new Intent(itemView.getContext(), this.activityTarget);
            itemView.getContext().startActivity(i);
            //(MainActivity)(itemView.getRootView())).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_content, this.fragment).commit();
        });
    }

    public void setTextValue(String value) {
        this.textView.setText(value);
    }


}