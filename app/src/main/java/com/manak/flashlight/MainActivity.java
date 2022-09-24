package com.manak.flashlight;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Camera;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageButton switchOn,switchOff;
    Camera camera;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        switchOff = findViewById(R.id.switchOff);
        switchOn = findViewById(R.id.switchOn);

        camera = Camera.open();
        Camera.Parameters parameters= camera.getParameters();

        switchOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchOff.setVisibility(View.GONE);
                switchOn.setVisibility(View.VISIBLE);
                parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                camera.setParameters(parameters);
                camera.startPreview();

            }
        });

        switchOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchOff.setVisibility(View.VISIBLE);
                switchOn.setVisibility(View.GONE);

                parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                camera.setParameters(parameters);
                camera.stopPreview();


            }
        });

    }
    private long mLastBackClick = 0;

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - mLastBackClick < 1100) {
            super.onBackPressed();
            onDestroy();
        } else {

            AdController.adCounter++;
            if (AdController.adCounter == AdController.adDisplayCounter) {
                AdController.showInterAd(MainActivity.this, null, 0);
            } else {
                Toast.makeText(MainActivity.this, getResources().getString(R.string.exit_alert), Toast.LENGTH_SHORT).show();
                mLastBackClick = System.currentTimeMillis();
            }
        }
    }
}