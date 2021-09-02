package com.com.busantourisme;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.com.busantourisme.view.get.MainActivity;

public class LaunchingAppAcitivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launching_app_acitivity);

        Handler handler = new Handler();
        handler.postDelayed(new LaunchingHandler(),4000);
    }

    private class LaunchingHandler implements Runnable {

        @Override
        public void run() {
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            LaunchingAppAcitivity.this.finish();
        }
    }
}