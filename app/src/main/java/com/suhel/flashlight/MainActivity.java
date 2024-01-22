package com.suhel.flashlight;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button=findViewById(R.id.button);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (button.getText().toString().equals("TURN ON")){
                    button.setText("TORN OFF");

                    changeLightState(true);
                }else {
                    button.setText("TURN ON");
                    changeLightState(false);
                }
            }
        });
    }

    private void changeLightState(boolean state) {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            CameraManager cameraManager= (CameraManager) getSystemService(CAMERA_SERVICE);
            String CamId=null;

            try {
                CamId=cameraManager.getCameraIdList()[0];
                cameraManager.setTorchMode(CamId,state);
            } catch (CameraAccessException e) {
                throw new RuntimeException(e);
            }


        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        button.setText("TURN ON");

    }
}