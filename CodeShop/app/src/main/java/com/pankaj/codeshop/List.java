package com.pankaj.codeshop;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.NavigationView;
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

public class List extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener , SwipeRefreshLayout.OnRefreshListener {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private SwipeRefreshLayout sr;
    ProgressDialog pd;
    String title,link,img,date,category;
    ListView list;
    ArrayList<ListItem> newsItemList;
    ListAdapter adapter;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawer_home);
        toggle=new ActionBarDrawerToggle(this, drawerLayout ,R.string.opend,R.string.closed);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView nView=(NavigationView)findViewById(R.id.nav_view);
        nView.setNavigationItemSelectedListener(this);

        pd = new ProgressDialog(List.this);
        pd.setMessage("Loading data \nplease wait...");
        pd.setCancelable(false);
        pd.show();

        sr = (SwipeRefreshLayout) findViewById(R.id.sr);
        sr.setOnRefreshListener(this);

        Intent i=getIntent();
        url= i.getExtras().getString("url");
        onRefresh();
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
    public void onRefresh() {
        newsItemList=new ArrayList<>();
        list=(ListView)findViewById(R.id.listView);
        Toast.makeText(List.this, "sending Request", Toast.LENGTH_SHORT).show();
        RequestQueue queue= Volley.newRequestQueue(this);
        StringRequest request=new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Document doc= Jsoup.parse(response);
                Elements articles= doc.getElementsByTag("article");
                if(articles.size()==0) {
                    Toast.makeText(List.this, "Sorry No Song Found", Toast.LENGTH_SHORT).show();
                }
                for (int i=0;i<articles.size();i++)
                {
                    Element article=articles.get(i);
                    link=article.getElementsByTag("a").first().attr("href");
                    img=article.getElementsByTag("img").first().attr("src");
                    title=article.getElementsByTag("a").first().attr("title");

                    Elements posts=article.getElementsByTag("p");
                    category=posts.get(0).text();
                    date=posts.get(1).text();
                        int k=date.indexOf(",");
                        k=k+6;
                        date=date.substring(0,k);
                    ListItem news =new ListItem();
                    news.ltitle=title;
                    news.limg=img;
                    news.ldt=date;
                    news.llink=link;
                    newsItemList.add(news);
                }
                pd.dismiss();
                adapter=new ListAdapter(List.this,newsItemList);
                list.setAdapter(adapter);
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        ListItem currentNews= newsItemList.get(position);
                        Intent intent=new Intent(List.this,Page.class);
                        intent.putExtra("newsitem",currentNews);
                        startActivity(intent);
                    }
                });
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.dismiss();
            Intent ie=new Intent(List.this,InternetError.class);
                startActivity(ie);
            }
        });
        queue.add(request);
        sr.setRefreshing(false);
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
            if(url!="https://agamyaguitarclass.wordpress.com/") {
                Intent i = new Intent(List.this, Home.class);
                startActivity(i);
                overridePendingTransition(R.anim.pull_in_right, R.anim.pull_in_left);
            }
        } else if (id == R.id.category) {
            Intent i=new Intent(List.this,Category.class);
            startActivity(i);
            overridePendingTransition(R.anim.pull_in_right, R.anim.pull_in_left);
        } else if (id == R.id.save) {
            Intent i=new Intent(List.this,Save_List.class);
            startActivity(i);
            overridePendingTransition(R.anim.pull_in_right, R.anim.pull_in_left);
        } else if (id == R.id.chordlib) {
            Intent i=new Intent(List.this,ChordLib.class);
            startActivity(i);
            overridePendingTransition(R.anim.pull_in_right, R.anim.pull_in_left);
        } else if (id == R.id.request) {
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto","agamyaguitarclass@gmail.com", null));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Song Request");
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Song Name: ");
            startActivity(Intent.createChooser(emailIntent, "Sending email..."));
            overridePendingTransition(R.anim.pull_in_right, R.anim.pull_in_left);
        } else if (id == R.id.about) {
            Intent i=new Intent(List.this,AboutUs.class);
            startActivity(i);
            overridePendingTransition(R.anim.pull_in_right, R.anim.pull_in_left);
        }
        else if (id == R.id.help) {
            Intent i=new Intent(List.this,Help.class);
            startActivity(i);
            overridePendingTransition(R.anim.pull_in_right, R.anim.pull_in_left);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
