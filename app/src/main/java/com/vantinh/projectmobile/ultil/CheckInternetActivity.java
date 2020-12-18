package com.vantinh.projectmobile.ultil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.vantinh.projectmobile.MainActivity;
import com.vantinh.projectmobile.R;

public class CheckInternetActivity extends AppCompatActivity {
     Button thu_lai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_internet);

        // Ánh xạ
        thu_lai = findViewById(R.id.thu_lai);

        thu_lai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });
    }
}