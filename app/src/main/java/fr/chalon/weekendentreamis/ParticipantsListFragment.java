package fr.chalon.weekendentreamis;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import fr.chalon.weekendentreamis.database.entities.Participant;
import fr.chalon.weekendentreamis.recyclerviews.RecyclerViewAdapter;
import fr.chalon.weekendentreamis.viewmodels.ParticipantsListViewModel;

public class ParticipantsListFragment extends Fragment {

    private ParticipantsListViewModel viewModel;

    private RecyclerView participantsRecyclerView;
    private RecyclerView.LayoutManager participantsRecyclerViewLayoutManager;
    private RecyclerView.Adapter participantsRecyclerViewAdapter;

    public static ParticipantsListFragment newInstance() {
        return new ParticipantsListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.participants_list_fragment, container, false);

        Toolbar toolbar = (Toolbar) container.getRootView().findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.title_fragment_participants_list);

        // on créé des instances de tout ce dont on a besoin
        this.participantsRecyclerViewLayoutManager = new LinearLayoutManager(container.getContext());
        this.participantsRecyclerViewAdapter = new RecyclerViewAdapter(container.getContext(), ParticipantDetailsActivity.class);

        // On récupère le recycler view
        this.participantsRecyclerView = view.findViewById(R.id.participants_recycler_view);

        // on lui passe le layout manager
        this.participantsRecyclerView.setLayoutManager(this.participantsRecyclerViewLayoutManager);

        // et l'adapter
        this.participantsRecyclerView.setAdapter(this.participantsRecyclerViewAdapter);

        this.viewModel = ViewModelProviders.of(this).get(ParticipantsListViewModel.class);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Création d'une instance du VM
        viewModel = ViewModelProviders.of(this).get(ParticipantsListViewModel.class);

        ArrayList<Participant> participantsList = new ArrayList<Participant>();
        participantsList.add(new Participant("Patatanouille", "Dit lent"));
        participantsList.add(new Participant("Quentouille", "Quant hein"));
        viewModel.setParticipants(participantsList);

        // On observe les changements sur les participants.
        viewModel.getParticipants().observe(this, participants -> {

            // On récupère les noms des participants.
            List<String> data = participants.stream()
                    .map(p -> p.getNom().toUpperCase() + " " + p.getPrenom())
                    .collect(Collectors.toList());

            // On les passe à l'adapter.
            ((RecyclerViewAdapter)this.participantsRecyclerViewAdapter).setData(data);
        });
    }

}
