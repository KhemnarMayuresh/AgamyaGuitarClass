package com.pankaj.codeshop;

import android.content.Intent;
import android.provider.Settings;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity {
    Button b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Intent i=new Intent(Home.this,List.class);
        String url="https://agamyaguitarclass.wordpress.com/";
        i.putExtra("url",url);
        startActivity(i);
    }
    @Override
    public void onResume()
    {
        super.onResume();
        b1=(Button)findViewById(R.id.yes);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            finishAffinity();
                System.exit(0);
            }
        });
        b2=(Button)findViewById(R.id.no);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Home.this,List.class);
                String url="https://agamyaguitarclass.wordpress.com/";
                i.putExtra("url",url);
                startActivity(i);
            }
        });

    }
    @Override
    public void onBackPressed() {
        finishAffinity();
        System.exit(0);
    }
}
