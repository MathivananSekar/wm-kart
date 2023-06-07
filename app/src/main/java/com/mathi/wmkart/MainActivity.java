package com.mathi.wmkart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.EditText;

import com.mathi.wmkart.fragment.HomeFragment;

public class MainActivity extends AppCompatActivity {


    Fragment homeFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String query = getIntent().getStringExtra("search_query");
        setContentView(R.layout.activity_main);
        homeFragment= new HomeFragment(query);
        loadFragment(homeFragment);
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_container,homeFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


}