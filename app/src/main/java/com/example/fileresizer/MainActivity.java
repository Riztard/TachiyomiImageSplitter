package com.example.fileresizer;

import android.Manifest;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Resize is not as promising as I thought
        // Splitting is much more better++

        /* Button button = findViewById(R.id.resize);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkPermissions();

                Display display = getWindowManager().getDefaultDisplay();
                int screenWidth = display.getWidth();
//                WorkManager.getInstance(MainActivity.this).beginWith()

                ResizeThread thread = new ResizeThread(screenWidth, MainActivity.this);
                thread.start();
            }
        }); */

        Button split = findViewById(R.id.split);

        split.setOnClickListener(view -> {
            checkPermissions();

            int screenHeight = ThreadUtils.getHeight(MainActivity.this);

            SplitThread thread = new SplitThread(screenHeight, MainActivity.this);
            thread.start();
        });

        Button safeSplit = findViewById(R.id.safesplit);

        safeSplit.setOnClickListener(view -> {
            checkPermissions();

            int screenHeight = ThreadUtils.getHeight(MainActivity.this);

            SplitThread thread = new SplitThread(screenHeight, MainActivity.this);
            thread.setSafe();
            thread.start();
        });

        Button clean = findViewById(R.id.clean);

        clean.setOnClickListener(view -> {
            checkPermissions();

            CleanThread thread = new CleanThread(MainActivity.this);
            thread.start();
        });
    }


    private void checkPermissions() {
        String[] perms = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (EasyPermissions.hasPermissions(this, perms)) {
            Toast.makeText(this, "Using file access", Toast.LENGTH_LONG).show();
        } else {
            EasyPermissions.requestPermissions(this, "",
                    1, perms);
        }

    }


}