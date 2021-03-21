package com.example.mahjonggamev1.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mahjonggamev1.R;
import com.example.mahjonggamev1.databinding.GameRoundBinding;
import com.example.mahjonggamev1.viewmodel.GameViewModel;

import java.util.ArrayList;

public class GameRoundAdapter extends RecyclerView.Adapter<GameRoundAdapter.GameRoundViewHolder>{

    public ArrayList<GameViewModel> gameViewModelArrayList;

    public GameRoundAdapter(ArrayList<GameViewModel> gameRoundList){
        this.gameViewModelArrayList = gameRoundList;
    }

    @NonNull
    @Override
    public GameRoundViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        GameRoundBinding gameRoundBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.gameround_row,parent,false);
        return new GameRoundViewHolder(gameRoundBinding);
    }

    @NonNull
    @Override
    public void onBindViewHolder(@NonNull GameRoundViewHolder holder, int position){
        GameViewModel gameViewModel = gameViewModelArrayList.get(position);
        holder.bind(gameViewModel);
    }

    @Override
    public int getItemCount(){ return gameViewModelArrayList.size(); }

    public class GameRoundViewHolder extends RecyclerView.ViewHolder{
        private GameRoundBinding gameRoundBinding;

        public GameRoundViewHolder(GameRoundBinding gameRoundBinding){
            super(gameRoundBinding.getRoot());
            this.gameRoundBinding = gameRoundBinding;
        }

        public void bind(GameViewModel gameViewModel){
            gameRoundBinding.orderId.setText(String.valueOf(getAdapterPosition()+1));
            gameRoundBinding.setGameRoundModel(gameViewModel);
            gameRoundBinding.executePendingBindings();
        }
    }
}
