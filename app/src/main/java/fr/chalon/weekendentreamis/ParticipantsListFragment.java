package fr.chalon.weekendentreamis;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fr.chalon.weekendentreamis.database.entities.Participant;
import fr.chalon.weekendentreamis.recyclerviews.RecyclerViewAdapter;
import fr.chalon.weekendentreamis.recyclerviews.RecyclerViewHolderActions;
import fr.chalon.weekendentreamis.repository.ParticipantRepository;
import fr.chalon.weekendentreamis.viewmodels.ParticipantsListViewModel;

public class ParticipantsListFragment extends Fragment {

    private ParticipantsListViewModel viewModel;
    private ParticipantRepository repository;
    private RecyclerView participantsRecyclerView;
    private RecyclerView.LayoutManager participantsRecyclerViewLayoutManager;
    private RecyclerView.Adapter participantsRecyclerViewAdapter;
    private FloatingActionButton addButton;

    public static ParticipantsListFragment newInstance() {
        return new ParticipantsListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.participants_list_fragment, container, false);

        this.addButton = view.findViewById(R.id.participants_add);
        this.addButton.setOnClickListener(c -> {
            Intent i = new Intent(this.getContext(), ParticipantEditionActivity.class);
            this.getContext().startActivity(i);
        });

        Toolbar toolbar = (Toolbar) container.getRootView().findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.title_fragment_participants_list);

        // on créé des instances de tout ce dont on a besoin
        this.participantsRecyclerViewLayoutManager = new LinearLayoutManager(container.getContext());

        RecyclerViewHolderActions actions = new RecyclerViewHolderActions(
            ParticipantEditionActivity.class, ParticipantDetailsActivity.class,
            (Long id) -> {
                this.repository.deleteParticipantById(id);
            }
        );

        this.participantsRecyclerViewAdapter = new RecyclerViewAdapter(container.getContext(), actions);

        // On récupère le recycler view
        this.participantsRecyclerView = view.findViewById(R.id.participants_recycler_view);

        // on lui passe le layout manager
        this.participantsRecyclerView.setLayoutManager(this.participantsRecyclerViewLayoutManager);

        // et l'adapter
        this.participantsRecyclerView.setAdapter(this.participantsRecyclerViewAdapter);

        this.viewModel = ViewModelProviders.of(this).get(ParticipantsListViewModel.class);
        this.repository = new ParticipantRepository(this.getActivity().getApplication());

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Création d'une instance du VM
        viewModel = ViewModelProviders.of(this).get(ParticipantsListViewModel.class);

        // On observe les changements sur les participants.
        this.repository.getAllParticipants().observe(this, participants -> {

            // On récupère les noms des participants.
            Map<Long, String> dataWithIds = participants.stream()
                    .collect(Collectors.toMap(p -> p.getId(), p -> p.getNom().toUpperCase() + " " + p.getPrenom()));

            ((RecyclerViewAdapter)this.participantsRecyclerViewAdapter).setData(dataWithIds);
        });
    }
}
