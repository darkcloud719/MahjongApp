package com.example.mahjonggamev1.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mahjonggamev1.R;
import com.example.mahjonggamev1.adapter.GameRoundAdapter;
import com.example.mahjonggamev1.databinding.GameRecordBinding;
import com.example.mahjonggamev1.model.Player;
import com.example.mahjonggamev1.viewmodel.GameViewModel;

import java.util.ArrayList;

public class RecordFragment extends Fragment {

    private GameViewModel gameViewModel;
    private GameRecordBinding recordBinding;
    private RecyclerView recyclerView;
    private GameRoundAdapter gameRoundAdapter;
    private ArrayList<GameViewModel> gameViewModelArrayList = new ArrayList();

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        recordBinding = DataBindingUtil.inflate(getLayoutInflater(),R.layout.fragment_record,container,false);
        View view = recordBinding.getRoot();
        gameViewModel = new ViewModelProvider(requireActivity()).get(GameViewModel.class);
        recordBinding.setGameViewModel(gameViewModel);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @NonNull Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerView);

        gameRoundAdapter = new GameRoundAdapter(gameViewModelArrayList);
        recyclerView.setAdapter(gameRoundAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(requireActivity(),DividerItemDecoration.VERTICAL));
        recyclerView.setFocusable(false);
        recyclerView.setNestedScrollingEnabled(false);

        gameViewModel.getMutableLiveDataPlayers().observe(getViewLifecycleOwner(), new Observer<ArrayList<Player>>(){
            @Override
            public void onChanged(ArrayList<Player> players){
                gameViewModel.players = players;
                recordBinding.setGameViewModel(gameViewModel);
            }
        });

        gameViewModel.getMutableLiveDataGameViewModelForGameRounds().observe(getViewLifecycleOwner(), new Observer<ArrayList<GameViewModel>>(){
            @Override
            public void onChanged(ArrayList<GameViewModel> gameRoundList){
                gameViewModelArrayList.clear();
                gameViewModelArrayList.addAll(gameRoundList);
                gameRoundAdapter.notifyDataSetChanged();
            }
        });


    }


}
