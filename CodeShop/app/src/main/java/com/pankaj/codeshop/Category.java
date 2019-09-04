package com.pankaj.codeshop;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Category extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
Button b1,b2,b3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawer_home);
        toggle=new ActionBarDrawerToggle(this, drawerLayout ,R.string.opend,R.string.closed);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView nView=(NavigationView)findViewById(R.id.nav_view);
        nView.setNavigationItemSelectedListener(this);


        b1=(Button) findViewById(R.id.simple);
        b2=(Button)findViewById(R.id.medium);
        b3=(Button)findViewById(R.id.hard);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Category.this,List.class);
                String url="https://agamyaguitarclass.wordpress.com/category/simple/";
                i.putExtra("url",url);
                startActivity(i);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Category.this,List.class);
                String url="https://agamyaguitarclass.wordpress.com/category/medium/";
                i.putExtra("url",url);
                startActivity(i);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Category.this,List.class);
                String url="https://agamyaguitarclass.wordpress.com/category/hard/";
                i.putExtra("url",url);
                startActivity(i);
            }
        });
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_home);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            finish();
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Inflate the menu; this adds items to the action bar if it is present.
        if(toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id=item.getItemId();
        if (id == R.id.home) {
            Intent i=new Intent(Category.this,Home.class);
            startActivity(i);
            overridePendingTransition(R.anim.pull_in_right, R.anim.pull_in_left);
        } else if (id == R.id.category) {

        } else if (id == R.id.save) {
            Intent i=new Intent(Category.this,Save_List.class);
            startActivity(i);
            overridePendingTransition(R.anim.pull_in_right, R.anim.pull_in_left);
        } else if (id == R.id.chordlib) {
            Intent i=new Intent(Category.this,ChordLib.class);
            startActivity(i);
            overridePendingTransition(R.anim.pull_in_right, R.anim.pull_in_left);
        } else if (id == R.id.request) {
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto","agamyaguitarclass@gmail.com", null));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Song Request");
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Song Name: ");
            startActivity(Intent.createChooser(emailIntent, "Sending email..."));
        } else if (id == R.id.about) {
            Intent i=new Intent(Category.this,AboutUs.class);
            startActivity(i);
            overridePendingTransition(R.anim.pull_in_right, R.anim.pull_in_left);
        }
        else if (id == R.id.help) {
            Intent i=new Intent(Category.this,Help.class);
            startActivity(i);
            overridePendingTransition(R.anim.pull_in_right, R.anim.pull_in_left);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
