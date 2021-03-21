package com.example.mahjonggamev1.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.mahjonggamev1.R;
import com.example.mahjonggamev1.databinding.GameBinding;
import com.example.mahjonggamev1.model.Player;
import com.example.mahjonggamev1.viewmodel.GameViewModel;

import java.util.ArrayList;

public class GameFragment extends Fragment{

    CardView player1CardView, player2CardView, player3CardView, player4CardView;
    GameViewModel gameViewModel;
    Player player1,player2,player3,player4;
    GameBinding gameBinding;
    int bankPlayer;
    int bankCount;

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        //View view = inflater.inflate(R.layout.fragment_game,container,false);
        setHasOptionsMenu(true);
        gameBinding = DataBindingUtil.inflate(getLayoutInflater(),R.layout.fragment_game,container,false);
        init(gameBinding);
        View view = gameBinding.getRoot();
        gameViewModel = new ViewModelProvider(requireActivity()).get(GameViewModel.class);
        gameBinding.setGameViewModel(gameViewModel);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @NonNull Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        //GameBinding gameBinding = DataBindingUtil.setContentView(requireActivity(),R.layout.fragment_game);



        gameViewModel.getMutableLiveDataPlayers().observe(getViewLifecycleOwner(), new Observer<ArrayList<Player>>(){
            @Override
            public void onChanged(ArrayList<Player> players){
                gameViewModel.players = players;
                gameBinding.setGameViewModel(gameViewModel);
            }
        });
    }

    private void showDialogFragment(int currentPlayer){
        DialogFragment d = DialogFragment.newInstance(currentPlayer);
        d.show(getChildFragmentManager(),"123");
    }

    public void init(GameBinding gameBinding){

        gameBinding.player1CardView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(gameViewModel.checkInitial() == false){
                    AlertDialog.Builder myDialog = new AlertDialog.Builder(getActivity());
                    myDialog.setTitle("請輸入玩家的名稱");

                    final EditText nameEdit = new EditText(getActivity());
                    nameEdit.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
                    nameEdit.setGravity(Gravity.CENTER_HORIZONTAL);
                    myDialog.setView(nameEdit);

                    myDialog.setPositiveButton("OK", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i){
                            String myName = nameEdit.getText().toString().trim();
                            player1 = new Player("1",myName,0,true);
                            gameViewModel.players.set(0,player1);
                            gameBinding.setGameViewModel(gameViewModel);
                        }
                    });

                    myDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i){
                            dialogInterface.cancel();
                        }
                    });
                    myDialog.show();
                }else{
                    showDialogFragment(0);
                }
            }
        });

        gameBinding.player2CardView .setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(gameViewModel.checkInitial() == false){
                    AlertDialog.Builder myDialog = new AlertDialog.Builder(getActivity());
                    myDialog.setTitle("請輸入玩家的名稱");

                    final EditText nameEdit = new EditText(getActivity());
                    nameEdit.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
                    nameEdit.setGravity(Gravity.CENTER_HORIZONTAL);
                    myDialog.setView(nameEdit);

                    myDialog.setPositiveButton("OK", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i){
                            String myName = nameEdit.getText().toString().trim();
                            player1 = new Player("2",myName,0,false);
                            gameViewModel.players.set(1,player1);
                            gameBinding.setGameViewModel(gameViewModel);
                        }
                    });

                    myDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i){
                            dialogInterface.cancel();
                        }
                    });
                    myDialog.show();
                }else{
                    showDialogFragment(1);
                }
            }
        });

        gameBinding.player3CardView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(gameViewModel.checkInitial() == false){
                    AlertDialog.Builder myDialog = new AlertDialog.Builder(getActivity());
                    myDialog.setTitle("請輸入玩家的名稱");

                    final EditText nameEdit = new EditText(getActivity());
                    nameEdit.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
                    nameEdit.setGravity(Gravity.CENTER_HORIZONTAL);
                    myDialog.setView(nameEdit);

                    myDialog.setPositiveButton("OK", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i){
                            String myName = nameEdit.getText().toString().trim();
                            player1 = new Player("2",myName,0,false);
                            gameViewModel.players.set(2,player1);
                            gameBinding.setGameViewModel(gameViewModel);
                        }
                    });

                    myDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i){
                            dialogInterface.cancel();
                        }
                    });
                    myDialog.show();
                }else{
                    showDialogFragment(2);
                }
            }
        });

        gameBinding.player4CardView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(gameViewModel.checkInitial() == false){
                    AlertDialog.Builder myDialog = new AlertDialog.Builder(getActivity());
                    myDialog.setTitle("請輸入玩家的名稱");

                    final EditText nameEdit = new EditText(getActivity());
                    nameEdit.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
                    nameEdit.setGravity(Gravity.CENTER_HORIZONTAL);
                    myDialog.setView(nameEdit);

                    myDialog.setPositiveButton("OK", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i){
                            String myName = nameEdit.getText().toString().trim();
                            player1 = new Player("4",myName,0,false);
                            gameViewModel.players.set(3,player1);
                            gameBinding.setGameViewModel(gameViewModel);
                        }
                    });

                    myDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i){
                            dialogInterface.cancel();
                        }
                    });
                    myDialog.show();
                }else{
                    showDialogFragment(3);
                }
            }
        });

        gameBinding.draw.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(gameViewModel.checkInitial() == true){
                    ArrayList<GameViewModel> gameViewModelForRound = gameViewModel.getMutableLiveDataGameViewModelForGameRounds().getValue();
                    if(gameViewModelForRound==null)
                        gameViewModelForRound = new ArrayList<GameViewModel>();
                    GameViewModel gvm = new GameViewModel();

                    gvm.player1Income = 0;
                    gvm.player2Income = 0;
                    gvm.player3Income = 0;
                    gvm.player4Income = 0;

                    gameViewModelForRound.add(gvm);

                    gameViewModel.mutableLiveDataGameViewModelForGameRounds.setValue(gameViewModelForRound);

                    Player player1 = gameViewModel.players.get(0);
                    Player player2 = gameViewModel.players.get(1);
                    Player player3 = gameViewModel.players.get(2);
                    Player player4 = gameViewModel.players.get(3);

                    ArrayList<Player> players = new ArrayList<Player>(){{add(player1); add(player2); add(player3); add(player4);}};

                    //找出莊家
                    for(int i=0; i<gameViewModel.players.size();i++){
                        if(gameViewModel.players.get(i).isBanker){
                            bankPlayer = i;
                            bankCount = gameViewModel.players.get(i).bankCount;
                        }
                    }

                    players.get(bankPlayer).bankCount++;

                    gameViewModel.mutableLiveDataPlayers.setValue(players);
                }
            }
        });

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        inflater.inflate(R.menu.game_round_menu,menu);
        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == R.id.action_replay){
            ArrayList<GameViewModel> gameViewModelsForRound = gameViewModel.getMutableLiveDataGameViewModelForGameRounds().getValue();
            ArrayList<Player> players = gameViewModel.players;
            if(gameViewModelsForRound == null || gameViewModelsForRound.size() == 0)
                return super.onOptionsItemSelected(item);
            else{
                int bankPlayer_local = 0;
                int bankCount_local,previousBanker_local;
                for(int i=0;i<players.size();i++){
                    if(players.get(i).isBanker){
                        bankPlayer_local = i;
                        bankCount_local = players.get(i).bankCount;
                    }
                }
                if(players.get(bankPlayer_local).bankCount>0){
                    previousBanker_local = bankPlayer_local;
                }
                else{
                    if(bankPlayer_local==0)
                        previousBanker_local = 3;
                    else
                        previousBanker_local = bankPlayer_local-1;
                }

                GameViewModel gameViewModelForRound = gameViewModelsForRound.get(gameViewModelsForRound.size()-1);
                players.get(0).money -= gameViewModelForRound.player1Income;
                players.get(1).money -= gameViewModelForRound.player2Income;
                players.get(2).money -= gameViewModelForRound.player3Income;
                players.get(3).money -= gameViewModelForRound.player4Income;
                gameViewModelsForRound.remove(gameViewModelsForRound.size()-1);
                if(previousBanker_local == bankPlayer_local){
                    players.get(bankPlayer_local).bankCount -= 1;
                }
                else{
                    players.get(bankPlayer_local).bankCount = 0;
                    players.get(bankPlayer_local).isBanker = false;
                    players.get(previousBanker_local).isBanker = true;
                }


                gameViewModel.mutableLiveDataPlayers.setValue(players);
                gameViewModel.mutableLiveDataGameViewModelForGameRounds.setValue(gameViewModelsForRound);


            }
        }
        return super.onOptionsItemSelected(item);
    }


}
