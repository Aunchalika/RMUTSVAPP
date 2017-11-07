package app.rmutsv.sampuriwat.rmutsvservice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import app.rmutsv.sampuriwat.rmutsvservice.fragment.MainFragment;

public class MainActivity extends AppCompatActivity {
    // onCreate is a main method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);  // save process of program that still running
        setContentView(R.layout.activity_main);

//        Add Fragment to Activity
        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentFragmentMain, new MainFragment()).commit();
        }

    }// main method
}// main class
