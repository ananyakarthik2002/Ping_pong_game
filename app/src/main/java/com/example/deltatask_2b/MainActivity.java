package com.example.deltatask_2b;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;

public class MainActivity extends AppCompatActivity {
    private myCanvas gameView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameView = new myCanvas(this);
        setContentView(gameView);
    }
}