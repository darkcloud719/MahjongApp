package com.example.mahjonggamev1.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mahjonggamev1.R;
import com.example.mahjonggamev1.databinding.GameRuleBinding;
import com.example.mahjonggamev1.viewmodel.GameViewModel;

import java.util.ArrayList;

public class GameRuleForDefaultAdapter extends RecyclerView.Adapter<GameRuleForDefaultAdapter.GameRuleViewHolder>{

    public ArrayList<GameViewModel> gameViewModelArrayList;
    public OnItemClickListenerForGameRuleDefault onItemClickListenerForGameRuleDefault;

    public GameRuleForDefaultAdapter(ArrayList<GameViewModel> gameRuleList, OnItemClickListenerForGameRuleDefault onItemClickListenerForGameRuleDefault){
        this.gameViewModelArrayList = gameRuleList;
        this.onItemClickListenerForGameRuleDefault = onItemClickListenerForGameRuleDefault;
    }

    @NonNull
    @Override
    public GameRuleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        GameRuleBinding gameRuleBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.gamerule_row,parent,false);
        return new GameRuleViewHolder(gameRuleBinding);
    }

    @NonNull
    @Override
    public void onBindViewHolder(@NonNull GameRuleForDefaultAdapter.GameRuleViewHolder holder, int position){
        GameViewModel gameViewModel = gameViewModelArrayList.get(position);
        holder.bind(gameViewModel);
    }

    @Override
    public int getItemCount(){
        return gameViewModelArrayList.size();
    }

    public class GameRuleViewHolder extends RecyclerView.ViewHolder{
        private GameRuleBinding gameRuleBinding;

        public GameRuleViewHolder(GameRuleBinding gameRuleBinding){
            super(gameRuleBinding.getRoot());
            this.gameRuleBinding = gameRuleBinding;
            gameRuleBinding.getRoot().setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    onItemClickListenerForGameRuleDefault.onItemClickForGameRuleDefault(getAdapterPosition());
                }
            });
        }
        public void bind(GameViewModel gameViewModel){
            gameRuleBinding.setGameRuleModel(gameViewModel);
            gameRuleBinding.executePendingBindings();
        }
    }

    public interface OnItemClickListenerForGameRuleDefault{
        public void onItemClickForGameRuleDefault(int position);
    }
}
