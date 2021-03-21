package com.example.mahjonggamev1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.database.Cursor;
import android.os.Bundle;

import com.example.mahjonggamev1.helper.MyDatabaseHelper;
import com.example.mahjonggamev1.model.GameRule;
import com.example.mahjonggamev1.viewmodel.GameViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private NavController navController;
    private GameViewModel gameViewModel;
    private AppBarConfiguration mAppBarConfiguration;
    public MyDatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.navigation_game, R.id.navigation_record, R.id.navigation_adjustment).setDrawerLayout(drawer).build();
        gameViewModel = new ViewModelProvider(this).get(GameViewModel.class);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_view);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        myDB = new MyDatabaseHelper(this);
        Cursor cursor = myDB.readAllData();
        ArrayList<GameViewModel> gList = new ArrayList<>();
        while(cursor.moveToNext()){
            GameRule g = new GameRule(Integer.parseInt(cursor.getString(0)), cursor.getString(1),Integer.parseInt(cursor.getString(2)), Integer.parseInt(cursor.getString(3)));
            GameViewModel gVM = new GameViewModel(g);
            gList.add(gVM);
        }
        gameViewModel.loadGameRules(gList);
    }

    @Override
    public boolean onSupportNavigateUp(){
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration) || super.onSupportNavigateUp();
    }


}