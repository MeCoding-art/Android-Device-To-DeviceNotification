package com.machine.androiddevicetodevicenotificationproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    EditText etName, etAddress;
    Button btSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etAddress = findViewById(R.id.et_address);
        etName = findViewById(R.id.et_name);
        btSubmit = findViewById(R.id.bt_submit);

        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseMessaging.getInstance().subscribeToTopic("all");

                String name = etName.getText().toString();
                String address = etAddress.getText().toString();

                if(name.equals("") || address.equals("")){
                    Toast.makeText(MainActivity.this, "Please enter values", Toast.LENGTH_SHORT).show();
                }
                else{
                    FcmNotificationsSender fcmNotificationsSender = new FcmNotificationsSender("/topics/all", name, address,
                            getApplicationContext(), MainActivity.this);

                    fcmNotificationsSender.SendNotifications();
                }

            }
        });

    }
}