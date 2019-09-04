package com.pankaj.codeshop;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class Save_List extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    ProgressDialog pd;
    Database db;
    byte[] img;
    ListView list;
    ArrayList<SaveListItem> newsItemList;
    SavedListAdapter adapter;
    String title,date,html;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save__list);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawer_home);
        toggle=new ActionBarDrawerToggle(this, drawerLayout ,R.string.opend,R.string.closed);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView nView=(NavigationView)findViewById(R.id.nav_view);
        nView.setNavigationItemSelectedListener(this);

        pd = new ProgressDialog(Save_List.this);
        pd.setMessage("Loading data \nplease wait...");
        pd.setCancelable(false);
        pd.show();

        db=new Database(this);
        newsItemList=new ArrayList<>();
        list=(ListView)findViewById(R.id.listView);

        Cursor res=db.getAllData();
        if(res.getCount()==0)
        {
            Toast.makeText(Save_List.this, "Sorry No Song Found", Toast.LENGTH_SHORT).show();
            pd.dismiss();
            return;
        }

        while (res.moveToNext()){
            title=res.getString(1);
            date=res.getString(2);
            html=res.getString(3);
            img=res.getBlob(4);
            SaveListItem news =new SaveListItem();
            news.stitle=title;
            news.sdate=date;
            news.shtml=html;
            news.simg=img;
            newsItemList.add(news);
        }
        pd.dismiss();
        adapter=new SavedListAdapter(Save_List.this,newsItemList);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SaveListItem currentNews= newsItemList.get(position);
                Intent intent=new Intent(Save_List.this,SavePage.class);
                intent.putExtra("saveitem",currentNews);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_home);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
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
                Intent i = new Intent(Save_List.this, Home.class);
                startActivity(i);
                overridePendingTransition(R.anim.pull_in_right, R.anim.pull_in_left);
        } else if (id == R.id.category) {
            Intent i=new Intent(Save_List.this,Category.class);
            startActivity(i);
            overridePendingTransition(R.anim.pull_in_right, R.anim.pull_in_left);
        } else if (id == R.id.save) {

        } else if (id == R.id.chordlib) {
            Intent i=new Intent(Save_List.this,ChordLib.class);
            startActivity(i);
            overridePendingTransition(R.anim.pull_in_right, R.anim.pull_in_left);
        } else if (id == R.id.request) {
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto","agamyaguitarclass@gmail.com", null));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Song Request");
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Song Name: ");
            startActivity(Intent.createChooser(emailIntent, "Sending email..."));
        } else if (id == R.id.about) {
            Intent i=new Intent(Save_List.this,AboutUs.class);
            startActivity(i);
            overridePendingTransition(R.anim.pull_in_right, R.anim.pull_in_left);
        }
        else if (id == R.id.help) {
            Intent i=new Intent(Save_List.this,Help.class);
            startActivity(i);
            overridePendingTransition(R.anim.pull_in_right, R.anim.pull_in_left);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
