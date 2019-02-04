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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import fr.chalon.weekendentreamis.recyclerviews.RecyclerViewAdapter;
import fr.chalon.weekendentreamis.recyclerviews.RecyclerViewHolderActions;
import fr.chalon.weekendentreamis.repository.SejourRepository;
import fr.chalon.weekendentreamis.viewmodels.SejourListViewModel;

public class SejourListFragment extends Fragment {

    private SejourListViewModel mViewModel;

    private RecyclerView sejoursRecyclerView;
    private RecyclerView.LayoutManager sejoursRecyclerViewLayoutManager;
    private RecyclerView.Adapter sejoursRecyclerViewAdapter;
    private RecyclerViewHolderActions actions;

    private SejourRepository sejourRepository;

    public static SejourListFragment newInstance() {
        return new SejourListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        inflater.inflate(R.layout.sejour_list_fragment, container, false);

        View v = inflater.inflate(R.layout.sejour_list_fragment,container, false);

        Toolbar toolbar = (Toolbar) container.getRootView().findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.title_fragment_sejours_list);

        sejoursRecyclerViewLayoutManager = new LinearLayoutManager(container.getContext());

        this.actions = new RecyclerViewHolderActions(
                SejourEditionActivity.class,
                SejourDetailsActivity.class,
                (Long idSejour) ->{
                    sejourRepository.deleteBySejourId(idSejour);
                });

        sejoursRecyclerViewAdapter = new RecyclerViewAdapter(container.getContext(),this.actions);

        sejoursRecyclerView = v.findViewById(R.id.sejours_recycler_view);
        this.sejoursRecyclerView.setLayoutManager(sejoursRecyclerViewLayoutManager);


        //ajout adapter
        this.sejoursRecyclerView.setAdapter(sejoursRecyclerViewAdapter);

        FloatingActionButton fab = v.findViewById(R.id.btnAddSejour);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(v.getContext(), SejourEditionActivity.class);
            startActivity(intent);
        });
        Log.d("this", container.getContext().toString());
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SejourListViewModel.class);
        // TODO: Use the ViewModel

        sejourRepository = new SejourRepository(this.getActivity().getApplication());
        sejourRepository.getAllSejours().observe(this, sejours -> {
            // On récupère les noms des sejours.
            Map<Long, String> dataWithId = sejours.stream()
                    .collect(Collectors.toMap(p -> p.getId(), p -> p.getNom()));

            // On les passe à l'adapter.
            ((RecyclerViewAdapter)this.sejoursRecyclerViewAdapter).setData(dataWithId);
        });
    }



}
