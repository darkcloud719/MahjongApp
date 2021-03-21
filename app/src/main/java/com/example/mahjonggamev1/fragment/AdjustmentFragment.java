package com.example.mahjonggamev1.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mahjonggamev1.AddGameRuleActivity;
import com.example.mahjonggamev1.R;
import com.example.mahjonggamev1.adapter.GameRuleAdapter;
import com.example.mahjonggamev1.adapter.GameRuleForDefaultAdapter;
import com.example.mahjonggamev1.helper.MyDatabaseHelper;
import com.example.mahjonggamev1.model.GameRule;
import com.example.mahjonggamev1.model.Player;
import com.example.mahjonggamev1.viewmodel.GameViewModel;

import java.util.ArrayList;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class AdjustmentFragment extends Fragment implements GameRuleAdapter.OnItemClickListener , GameRuleForDefaultAdapter.OnItemClickListenerForGameRuleDefault{



    private RecyclerView recyclerView ,recyclerView_default;
    private GameRuleAdapter gameRuleAdapter;
    private GameRuleForDefaultAdapter gameRuleAdapter_default;
    private NavController navController;
    private GameViewModel gameViewModel;
    private ArrayList<GameViewModel> gameViewModelArrayList = new ArrayList();
    private ArrayList<GameViewModel> gameViewModelArrayList_default = new ArrayList<>();
    private ImageView add_button;

    public MyDatabaseHelper myDB;

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_adjustment,container,false);
        //myDB = new MyDatabaseHelper(requireActivity());

    }

    @Override
    public void onViewCreated(@NonNull View view, @NonNull Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        recyclerView = view.findViewById(R.id.recyclerView);

        recyclerView_default = view.findViewById(R.id.recyclerView_default);

        add_button = view.findViewById(R.id.add_button);
        myDB = new MyDatabaseHelper(getActivity());

        gameRuleAdapter = new GameRuleAdapter(gameViewModelArrayList, this);
        recyclerView.setAdapter(gameRuleAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(requireActivity(), DividerItemDecoration.VERTICAL));
        //new
        recyclerView.setFocusable(false);
        recyclerView.setNestedScrollingEnabled(false);

        storeDefaultDataInArrays();

        gameRuleAdapter_default = new GameRuleForDefaultAdapter(gameViewModelArrayList_default, this);
        recyclerView_default.setAdapter(gameRuleAdapter_default);
        recyclerView.addItemDecoration(new DividerItemDecoration(requireActivity(), DividerItemDecoration.VERTICAL));
        recyclerView.setFocusable(false);
        recyclerView.setNestedScrollingEnabled(false);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);


        gameViewModel = new ViewModelProvider(requireActivity()).get(GameViewModel.class);
        gameViewModel.getMutableLiveDataGameViewModelForGameRules().observe(getViewLifecycleOwner(), new Observer<ArrayList<GameViewModel>>(){
            @Override
            public void onChanged(ArrayList<GameViewModel> gameRuleList){
                gameViewModelArrayList.clear();
                gameViewModelArrayList.addAll(gameRuleList);
                gameRuleAdapter.notifyDataSetChanged();
            }
        });

        add_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getActivity(), AddGameRuleActivity.class);
                startActivityForResult(intent, 1);
                //navController.navigate(R.id.action_navigation_adjustment_to_navigation_addGameRule);
            }
        });
        /*gameViewModel.getGameViewModelList_for_gameRule().observe(getViewLifecycleOwner(), new Observer<ArrayList<GameViewModel>>(){
            @Override
            public void onChanged(ArrayList<GameViewModel> gameRuleList){
                gameViewModelArrayList.clear();
                gameViewModelArrayList.addAll(gameRuleList);
                gameRuleAdapter.notifyDataSetChanged();
            }
        });*/
    }

    /*@Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        inflater.inflate(R.menu.gamerule_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }*/

    @Override
    public void onItemClick(int position){
        GameViewModel gvm = gameViewModelArrayList.get(position);
        GameRule gameRule = new GameRule(gvm.di, gvm.tai);
        gameViewModel.mutableLiveDataGameRule.setValue(gameRule);
        gameViewModel.mutableLiveDataGameViewModelForGameRounds.setValue(new ArrayList<GameViewModel>());
        gameViewModel.mutableLiveDataPlayers.setValue(new ArrayList<Player>(){{add(new Player()); add(new Player()); add(new Player()); add(new Player());}});
        navController.navigate(R.id.action_navigation_adjustment_to_navigation_game);
        //Toast.makeText(getActivity(),String.valueOf(gameViewModelArrayList.get(position).di),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClickForGameRuleDefault(int position) {
        GameViewModel gvm = gameViewModelArrayList_default.get(position);
        GameRule gameRule = new GameRule(gvm.di, gvm.tai);
        gameViewModel.mutableLiveDataGameRule.setValue(gameRule);
        gameViewModel.mutableLiveDataGameViewModelForGameRounds.setValue(new ArrayList<GameViewModel>());
        gameViewModel.mutableLiveDataPlayers.setValue(new ArrayList<Player>(){{add(new Player()); add(new Player()); add(new Player()); add(new Player());}});
        navController.navigate(R.id.action_navigation_adjustment_to_navigation_game);
    }

    public void storeDefaultDataInArrays(){
        //Cursor cursor = myDB.readAllData();
        GameRule gr1 = new GameRule(-1,"500底200台",500,200);
        GameRule gr2 = new GameRule(-1, "300底100台",300,100);
        GameRule gr3 = new GameRule(-1,"100底20台",100,20);
        GameRule gr4 = new GameRule(-1,"50底20台",50,20);
        gameViewModelArrayList_default.add(new GameViewModel(gr1));
        gameViewModelArrayList_default.add(new GameViewModel(gr2));
        gameViewModelArrayList_default.add(new GameViewModel(gr3));
        gameViewModelArrayList_default.add(new GameViewModel(gr4));
    }

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT){
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target){
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction){
            int position = viewHolder.getAdapterPosition();

            switch(direction){
                case ItemTouchHelper.LEFT:
                    myDB.deleteGameRule(String.valueOf(gameViewModelArrayList.get(position).gameRuleId));
                    Cursor cursor = myDB.readAllData();
                    ArrayList<GameViewModel> gList = new ArrayList<>();
                    while(cursor.moveToNext()){
                        GameRule g = new GameRule(Integer.parseInt(cursor.getString(0)), cursor.getString(1),Integer.parseInt(cursor.getString(2)), Integer.parseInt(cursor.getString(3)));
                        GameViewModel gVM = new GameViewModel(g);
                        gList.add(gVM);
                    }
                    gameViewModel.loadGameRules(gList);
                    break;
            }
        }

        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive){
            new RecyclerViewSwipeDecorator.Builder(getActivity(), c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    .addSwipeLeftBackgroundColor(ContextCompat.getColor(getActivity(),R.color.red))
                    .addSwipeLeftActionIcon(R.drawable.ic_delete)
                    .create()
                    .decorate();

            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, @NonNull Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            Cursor cursor = myDB.readAllData();
            ArrayList<GameViewModel> gList = new ArrayList<>();
            while(cursor.moveToNext()){
                GameRule g = new GameRule(Integer.parseInt(cursor.getString(0)), cursor.getString(1),Integer.parseInt(cursor.getString(2)), Integer.parseInt(cursor.getString(3)));
                GameViewModel gVM = new GameViewModel(g);
                gList.add(gVM);
            }
            gameViewModel.loadGameRules(gList);
        }
    }


}
