package com.esq.e_list;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView ivWelcome = findViewById(R.id.ivWelcome);
        TextView tvSubWelcome = findViewById(R.id.tvAppDesc);
        TextView btnget = findViewById(R.id.btnGet);
    }

    //GO to card task if button is clicked
    public void goToCardClass(View view){
        Intent intent = new Intent(this, CardTask.class);
        startActivity(intent);
    }
}
