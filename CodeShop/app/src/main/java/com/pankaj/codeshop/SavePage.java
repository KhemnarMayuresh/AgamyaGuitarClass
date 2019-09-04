package com.pankaj.codeshop;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


public class SavePage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    ProgressDialog pd;
    TextView date,data;
    ImageView img;
    String html;

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

        pd = new ProgressDialog(SavePage.this);
        pd.setMessage("Loading data please wait...");
        pd.setCancelable(false);
        pd.show();


        img=(ImageView)findViewById(R.id.imageView);
        date=(TextView)findViewById(R.id.textView2);
        data=(TextView)findViewById(R.id.text);
        final Toolbar title=(Toolbar)findViewById(R.id.toolbar_save);

        Intent i=getIntent();
        SaveListItem newsItem=(SaveListItem) getIntent().getSerializableExtra("saveitem");
       // Picasso.with(this).load(newsItem.simg).into(img);
        title.setTitle(newsItem.stitle);
        date.setText(newsItem.sdate);
        html=newsItem.shtml;
        Bitmap bmp = BitmapFactory.decodeByteArray(newsItem.simg,0,newsItem.simg.length);
        img.setImageBitmap(bmp);
        data.setText(Html.fromHtml(html));
        pd.dismiss();
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
            Intent i=new Intent(SavePage.this,Home.class);
            startActivity(i);
            overridePendingTransition(R.anim.pull_in_right, R.anim.pull_in_left);
        } else if (id == R.id.category) {
            Intent i=new Intent(SavePage.this,Category.class);
            startActivity(i);
            overridePendingTransition(R.anim.pull_in_right, R.anim.pull_in_left);
        } else if (id == R.id.save) {
            Intent i=new Intent(SavePage.this,Save_List.class);
            startActivity(i);
            overridePendingTransition(R.anim.pull_in_right, R.anim.pull_in_left);
        } else if (id == R.id.chordlib) {
            Intent i=new Intent(SavePage.this,ChordLib.class);
            startActivity(i);
            overridePendingTransition(R.anim.pull_in_right, R.anim.pull_in_left);
        } else if (id == R.id.request) {
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto","agamyaguitarclass@gmail.com", null));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Song Request");
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Song Name: ");
            startActivity(Intent.createChooser(emailIntent, "Sending email..."));
        } else if (id == R.id.about) {
            Intent i=new Intent(SavePage.this,AboutUs.class);
            startActivity(i);
            overridePendingTransition(R.anim.pull_in_right, R.anim.pull_in_left);
        }
        else if (id == R.id.help) {
            Intent i=new Intent(SavePage.this,Help.class);
            startActivity(i);
            overridePendingTransition(R.anim.pull_in_right, R.anim.pull_in_left);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}

