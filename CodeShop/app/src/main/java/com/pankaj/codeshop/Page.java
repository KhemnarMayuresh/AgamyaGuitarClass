package com.pankaj.codeshop;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.ByteArrayOutputStream;

public class Page extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    MediaController controller;
    Uri uri;
    ProgressDialog pd;
    TextView date,data;
    ImageView img;
    String url,html,utube,p1,p2;
    FloatingActionButton save;

    Database dataHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawer_home);
        toggle=new ActionBarDrawerToggle(this, drawerLayout ,R.string.opend,R.string.closed);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView nView=(NavigationView)findViewById(R.id.nav_view);
        nView.setNavigationItemSelectedListener(this);

        dataHelper=new Database(this);

        pd = new ProgressDialog(Page.this);
        pd.setMessage("Loading data please wait...");
        pd.setCancelable(false);
        pd.show();


        img=(ImageView)findViewById(R.id.imageView);
        date=(TextView)findViewById(R.id.textView2);
        data=(TextView)findViewById(R.id.text);
        final Toolbar title=(Toolbar)findViewById(R.id.toolbar_save);

        ListItem newsItem=(ListItem)getIntent().getSerializableExtra("newsitem");
        Picasso.with(this).load(newsItem.limg).into(img);
        p1=newsItem.ltitle;
        p2=newsItem.ldt;
        title.setTitle(newsItem.ltitle);
        date.setText(newsItem.ldt);
        url=newsItem.llink;
        RequestQueue queue= Volley.newRequestQueue(this);
        StringRequest request=new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Document doc= Jsoup.parse(response);
                html=doc.body().getElementsByClass("lyrics").html();
                utube=doc.getElementsByTag("iframe").attr("src");
                data.setText(Html.fromHtml(html));
                int st,en;
                String orgu="https://www.youtube.com/watch?v=",gt,uurl;
                st= utube.indexOf("embed/");
                st=st+6;
                en=utube.indexOf("?");
                gt=utube.substring(st,en);
                uurl=gt+orgu;
                pd.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Page.this, "Request Failed", Toast.LENGTH_SHORT).show();
                data.setText("Internet connection error!!!\n Try Later.");
            }
        });
        queue.add(request);


        save=(FloatingActionButton)findViewById(R.id.save_fbtn);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Page.this, "Saving "+title.getTitle()+" Song", Toast.LENGTH_SHORT).show();
                Bitmap bm=((BitmapDrawable)img.getDrawable()).getBitmap();
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    bm.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
                byte[] imgdata = outputStream.toByteArray();
                boolean res=  dataHelper.insertData(p1,p2,html,imgdata);
                if(res==true)
                {
                    Snackbar.make(v, "Song Saved Sucessfully", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }
                else
                {
                    Snackbar.make(v, "Failed to Save Song..try Again", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }

            }
        });

    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_home);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
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
            Intent i=new Intent(Page.this,Home.class);
            startActivity(i);
            overridePendingTransition(R.anim.pull_in_right, R.anim.pull_in_left);
        } else if (id == R.id.category) {
            Intent i=new Intent(Page.this,Category.class);
            startActivity(i);
            overridePendingTransition(R.anim.pull_in_right, R.anim.pull_in_left);
        } else if (id == R.id.save) {
            Intent i=new Intent(Page.this,Save_List.class);
            startActivity(i);
            overridePendingTransition(R.anim.pull_in_right, R.anim.pull_in_left);
        } else if (id == R.id.chordlib) {
            Intent i=new Intent(Page.this,ChordLib.class);
            startActivity(i);
            overridePendingTransition(R.anim.pull_in_right, R.anim.pull_in_left);
        } else if (id == R.id.request) {
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto","agamyaguitarclass@gmail.com", null));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Song Request");
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Song Name: ");
            startActivity(Intent.createChooser(emailIntent, "Sending email..."));
        } else if (id == R.id.about) {
            Intent i=new Intent(Page.this,AboutUs.class);
            startActivity(i);
            overridePendingTransition(R.anim.pull_in_right, R.anim.pull_in_left);
        }
        else if (id == R.id.help) {
            Intent i=new Intent(Page.this,Help.class);
            startActivity(i);
            overridePendingTransition(R.anim.pull_in_right, R.anim.pull_in_left);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}

