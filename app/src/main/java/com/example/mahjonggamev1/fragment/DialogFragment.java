package com.example.mahjonggamev1.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import com.example.mahjonggamev1.R;
import com.example.mahjonggamev1.adapter.MyWheelAdapter;
import com.example.mahjonggamev1.model.Player;
import com.example.mahjonggamev1.viewmodel.GameViewModel;
import com.wx.wheelview.widget.WheelView;

import java.util.ArrayList;
import java.util.List;

public class DialogFragment extends androidx.fragment.app.DialogFragment{

    GameViewModel gameViewModel;
    private Button confirm_btn;
    private TextView diTv,taiTv,tai_title,bank_title,bankTv,total_title,totalTv,selfdraw_title,selfdrawTv;
    int currentPlayer, loserPlayer, bankPlayer,bankCount,di,tai;
    boolean isSelfDraw = false;

    int taiTotalMoney;
    int bankTotalMoney;
    int total = 0;
    int loser;

    public static DialogFragment newInstance(int currentClickPlayer){
        DialogFragment fragment = new DialogFragment(currentClickPlayer);
        return fragment;
    }

    public DialogFragment(int currentClickPlayer){ this.currentPlayer = currentClickPlayer;}

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_dialog,container,false);
        if(getDialog() != null && getDialog().getWindow() != null){
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        }

        gameViewModel = new ViewModelProvider(requireActivity()).get(GameViewModel.class);

        //
        if(gameViewModel.mutableLiveDataGameRule.getValue() == null){
            di = 50;
            tai = 20;
        }
        else{
            di = gameViewModel.getMutableLiveDataGameRule().getValue().getDi();
            tai = gameViewModel.getMutableLiveDataGameRule().getValue().getTai();
        }
        //di = gameViewModel.getGameRuleMutableLiveData().getValue().getDi();
        //tai = gameViewModel.getGameRuleMutableLiveData().getValue().getTai();

        WheelView taiWheelView = view.findViewById(R.id.tai_wheelview);
        WheelView loserWheelView = view.findViewById(R.id.loser_wheelview);
        diTv = view.findViewById(R.id.di_tv);
        taiTv = view.findViewById(R.id.tai_tv);
        tai_title = view.findViewById(R.id.tai_title);
        bank_title = view.findViewById(R.id.bank_title);
        bankTv = view.findViewById(R.id.bank_tv);
        total_title = view.findViewById(R.id.total_title);
        totalTv = view.findViewById(R.id.total_tv);
        selfdraw_title = view.findViewById(R.id.selfdraw_title);
        selfdrawTv = view.findViewById(R.id.selfdraw_tv);

        confirm_btn = view.findViewById(R.id.confirm_btn);
        taiWheelView.setWheelAdapter(new MyWheelAdapter(getContext()));
        taiWheelView.setSkin(WheelView.Skin.Holo);
        taiWheelView.setWheelData(createTaiDatas());
        WheelView.WheelViewStyle style = new WheelView.WheelViewStyle();
        taiWheelView.setStyle(style);
        style.selectedTextColor = Color.parseColor("#0288ce");
        style.textColor = Color.GRAY;
        style.selectedTextSize = 28;

        for(int i=0; i<gameViewModel.players.size();i++){
            if(gameViewModel.players.get(i).isBanker){
                bankPlayer = i;
                bankCount = gameViewModel.players.get(i).bankCount;
            }
        }

        bankTotalMoney = (bankCount * 2 + 1) * tai;

        loserWheelView.setWheelAdapter(new MyWheelAdapter(getContext()));
        loserWheelView.setSkin(WheelView.Skin.Holo);
        loserWheelView.setWheelData(createPlayers(currentPlayer));
        loserWheelView.setStyle(style);

        taiWheelView.setOnWheelItemSelectedListener(new WheelView.OnWheelItemSelectedListener(){
            @Override
            public void onItemSelected(int position, Object o){
                int player_tai = Integer.parseInt(createTaiDatas().get(position));
                taiTotalMoney = tai * player_tai;
                diTv.setText(String.valueOf(di));
                taiTv.setText(String.valueOf(taiTotalMoney));
                tai_title.setText("牌型"+String.valueOf(player_tai)+"台");

                if(isSelfDraw)
                    total = (di + taiTotalMoney + bankTotalMoney) * 3;
                else
                    total = di + taiTotalMoney + bankTotalMoney;

                totalTv.setText(String.valueOf(total));
            }
        });

        loserWheelView.setOnWheelItemSelectedListener(new WheelView.OnWheelItemSelectedListener(){
            @Override
            public void onItemSelected(int position, Object o){
                //己方是專家且自摸
                if(currentPlayer == bankPlayer && currentPlayer == position){
                    isSelfDraw = true;
                    int bankTai = bankCount * 2 + 1;
                    bank_title.setText("莊"+String.valueOf(bankTai)+"台");
                    bank_title.setVisibility(View.VISIBLE);
                    bankTv.setText(String.valueOf(bankTotalMoney));
                    bankTv.setVisibility(View.VISIBLE);
                    selfdraw_title.setVisibility(View.VISIBLE);
                    selfdrawTv.setText("x3");
                    selfdrawTv.setVisibility(View.VISIBLE);
                }
                //己方是莊家非自摸
                else if(currentPlayer == bankPlayer && currentPlayer != position){
                    isSelfDraw = false;
                    int bankTai = bankCount * 2 + 1;
                    bank_title.setText("莊"+String.valueOf(bankTai)+"台");
                    bank_title.setVisibility(View.VISIBLE);
                    bankTv.setText(String.valueOf(bankTotalMoney));
                    bankTv.setVisibility(view.VISIBLE);
                    selfdraw_title.setVisibility(View.GONE);
                    selfdrawTv.setVisibility(View.GONE);
                }
                //對方是莊家
                else if(bankPlayer == position && currentPlayer != position){
                    isSelfDraw = false;
                    int bankTai = bankCount * 2 + 1;
                    bank_title.setText("莊"+String.valueOf(bankTai)+"台");
                    bank_title.setVisibility(View.VISIBLE);
                    bankTv.setText(String.valueOf(bankTotalMoney));
                    bankTv.setVisibility(View.VISIBLE);
                }
                //己方不是莊家、對方不是莊家，但自摸
                else if(currentPlayer == position){
                    isSelfDraw = true;
                    int bankTai = bankCount * 2 + 1;
                    bank_title.setText("莊"+String.valueOf(bankTai)+"台");
                    bank_title.setVisibility(View.VISIBLE);
                    bankTv.setText(String.valueOf(bankTotalMoney));
                    bankTv.setVisibility(View.VISIBLE);
                    selfdraw_title.setVisibility(View.VISIBLE);
                    selfdrawTv.setText("x3");
                    selfdrawTv.setVisibility(View.VISIBLE);
                }
                else{
                    isSelfDraw = false;
                    bank_title.setVisibility(View.GONE);
                    bankTv.setVisibility(View.GONE);
                    selfdraw_title.setVisibility(View.GONE);
                    selfdrawTv.setVisibility(View.GONE);
                }

                if(isSelfDraw)
                {
                    if(currentPlayer != bankPlayer && position != bankPlayer)
                        total = (di + taiTotalMoney) * 3 + bankTotalMoney;
                    else
                        total = (di + taiTotalMoney + bankTotalMoney) *3;
                }
                else{
                    if(currentPlayer != bankPlayer && position != bankPlayer)
                        total = di + taiTotalMoney;
                    else
                        total = di + taiTotalMoney + bankTotalMoney;
                }


                loser = position;
                totalTv.setText(String.valueOf(total));
            }
        });

        confirm_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ArrayList<Integer> arrayList = new ArrayList<Integer>(){{add(0); add(0); add(0); add(0);}};

                if(isSelfDraw){
                    if(currentPlayer == bankPlayer){
                        for(int i=0;i<4;i++){
                            if(i==currentPlayer)
                                arrayList.set(i,total);
                            else
                                arrayList.set(i,-(di+taiTotalMoney+bankTotalMoney));
                        }
                    }
                    else{
                        for(int i=0;i<4;i++){
                            if(i == currentPlayer)
                                arrayList.set(i,total);
                            else if(bankPlayer == i)
                                arrayList.set(i,-(di+taiTotalMoney+bankTotalMoney));
                            else
                                arrayList.set(i,-(di+taiTotalMoney));
                        }
                    }
                }
                else{
                    for(int i=0;i<4;i++){
                        if(i == currentPlayer)
                            arrayList.set(i,total);
                        else if(loser == i)
                            arrayList.set(i,-(total));
                    }
                }



                ArrayList<GameViewModel> gameViewModelForRound = gameViewModel.getMutableLiveDataGameViewModelForGameRounds().getValue();
                if(gameViewModelForRound==null)
                    gameViewModelForRound = new ArrayList<GameViewModel>();
                GameViewModel gvm = new GameViewModel();

                gvm.player1Income = arrayList.get(0);
                gvm.player2Income = arrayList.get(1);
                gvm.player3Income = arrayList.get(2);
                gvm.player4Income = arrayList.get(3);

                gameViewModelForRound.add(gvm);

                gameViewModel.mutableLiveDataGameViewModelForGameRounds.setValue(gameViewModelForRound);

                Player player1 = gameViewModel.players.get(0);
                Player player2 = gameViewModel.players.get(1);
                Player player3 = gameViewModel.players.get(2);
                Player player4 = gameViewModel.players.get(3);

                player1.money += gvm.player1Income;
                player2.money += gvm.player2Income;
                player3.money += gvm.player3Income;
                player4.money += gvm.player4Income;

                ArrayList<Player> players = new ArrayList<Player>(){{add(player1); add(player2); add(player3); add(player4);}};
                if(currentPlayer == bankPlayer){
                    players.get(currentPlayer).bankCount++;
                }
                else{
                    players.get(bankPlayer).isBanker = false;
                    players.get(bankPlayer).bankCount = 0;

                    if(bankPlayer==3)
                        players.get(0).isBanker = true;
                    else
                        players.get(bankPlayer+1).isBanker = true;
                }

                gameViewModel.mutableLiveDataPlayers.setValue(players);
                getDialog().dismiss();
            }
        });


        return view;
    }

    private List<String> createPlayers(int currentPlayer){
        ArrayList<String> strings = new ArrayList<>();
        for(int i=0;i<gameViewModel.players.size();i++){
            if(currentPlayer==i)
                strings.add("自摸");
            else
                strings.add(gameViewModel.players.get(i).name);
        }
        return strings;
    }

    private List<String> createTaiDatas(){
        ArrayList<String> strings = new ArrayList<>();
        for(int i = 0;i<=100;i++){
            strings.add(String.valueOf(i));
        }
        return strings;
    }
}
