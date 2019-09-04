package com.pankaj.codeshop;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SavedListAdapter extends BaseAdapter
{
    public SavedListAdapter(Context context, ArrayList<SaveListItem> newslist) {
        this.context = context;
        this.newslist = newslist;
    }

    Context context;
    ArrayList<SaveListItem> newslist;
    @Override
    public int getCount() {
        return  newslist.size();
    }

    @Override
    public SaveListItem getItem(int position) {
            return newslist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if(view==null)
        {
            view= View.inflate(context,R.layout.list_save_item,null);
        }
        SaveListItem currentNews=newslist.get(position);
        ImageView img=(ImageView)view.findViewById(R.id.imageView_1);
        TextView title=(TextView)view.findViewById(R.id.textView_1);
        TextView date=(TextView)view.findViewById(R.id.textView_2);

        Bitmap bmp = BitmapFactory.decodeByteArray(currentNews.simg,0,currentNews.simg.length);
        img.setImageBitmap(bmp);
        //Picasso.with(context).load(currentNews.simg).into(img);
        title.setText(currentNews.stitle);
        date.setText(currentNews.sdate);
        return view;
    }
}
