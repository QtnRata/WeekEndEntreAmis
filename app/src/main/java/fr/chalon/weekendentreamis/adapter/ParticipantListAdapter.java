package fr.chalon.weekendentreamis.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import fr.chalon.weekendentreamis.R;
import fr.chalon.weekendentreamis.database.entities.Participant;

public class ParticipantListAdapter extends RecyclerView.Adapter<ParticipantListAdapter.ParticipantViewHolder> {


    class ParticipantViewHolder extends RecyclerView.ViewHolder{
        private final TextView participantItemView;

        private ParticipantViewHolder(View itemView){
            super(itemView);
            participantItemView = itemView.findViewById(R.id.textViewParticipant);
        }
    }

    private final LayoutInflater inflater;
    private List<Participant> participants;


    public ParticipantListAdapter(Context context) {inflater = LayoutInflater.from(context);}

    @Override
    public ParticipantViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = inflater.inflate(R.layout.recyclerview_item, parent, false);
        return new ParticipantViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ParticipantViewHolder holder, int position){
        if(participants !=null){
            Participant current = participants.get(position);
            holder.participantItemView.setText(current.getPrenom());
        }else{
            holder.participantItemView.setText("Pas de participant");
        }
    }

    public void setParticipant(List<Participant> lstParticipants){
        participants = lstParticipants;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (participants != null)
            return participants.size();
        else return 0;
    }}
