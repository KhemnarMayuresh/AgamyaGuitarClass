package com.pankaj.codeshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InternetError extends AppCompatActivity {
Button b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internet_error);
        b1=(Button)findViewById(R.id.retry);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(InternetError.this,List.class);
                String url="https://agamyaguitarclass.wordpress.com/";
                i.putExtra("url",url);
                startActivity(i);
            }
        });
        b2=(Button)findViewById(R.id.jumptosave);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(InternetError.this,Save_List.class);
                startActivity(i);
            }
        });
    }
}
