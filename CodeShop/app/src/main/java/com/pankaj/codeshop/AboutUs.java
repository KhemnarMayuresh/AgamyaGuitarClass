package com.pankaj.codeshop;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AboutUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        Button web,u,insta,face;
        web=(Button)findViewById(R.id.website_btn);
        u=(Button)findViewById(R.id.youtube_btn);
        insta=(Button)findViewById(R.id.instagram_btn);
        face=(Button)findViewById(R.id.facebook_btn);

        web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String url="https://agamyaguitarclass.wordpress.com/";
                Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse(url));
                startActivity(intent);
            }
        });
        u.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.youtube.com/channel/UCrsCwDLFg7ZMDoOGupe67Gw"));
                intent.setPackage("com.google.android.youtube");
                try {
                    startActivity(intent);
                }
                catch (ActivityNotFoundException ae){
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://www.youtube.com/channel/UCrsCwDLFg7ZMDoOGupe67Gw")));
                }
            }
        });
        insta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.instagram.com/agamyaguitarclass/?hl=en");
                Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

                likeIng.setPackage("com.instagram.android");

                try {
                    startActivity(likeIng);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://www.instagram.com/agamyaguitarclass/?hl=en")));
                }
            }
        });
        face.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
                String facebookUrl = "https://www.facebook.com/ashishweldode5397/";
                facebookIntent.setData(Uri.parse(facebookUrl));
                try {
                    startActivity(facebookIntent);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse(facebookUrl)));
                }
            }
        });
    }
}
