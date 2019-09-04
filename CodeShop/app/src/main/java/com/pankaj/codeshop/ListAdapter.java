package com.pankaj.codeshop;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

 import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter
{
    public ListAdapter(Context context, ArrayList<ListItem> newslist) {
        this.context = context;
        this.newslist = newslist;
    }

    Context context;
    ArrayList<ListItem> newslist;
    @Override
    public int getCount() {
        return  newslist.size();
    }

    @Override
    public ListItem getItem(int position) {
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
            view= View.inflate(context,R.layout.list_item_layout,null);
        }
        ListItem currentNews=newslist.get(position);
        ImageView img=(ImageView)view.findViewById(R.id.imageView_1);
        TextView title=(TextView)view.findViewById(R.id.textView_1);
        TextView date=(TextView)view.findViewById(R.id.textView_2);
        Picasso.with(context).load(currentNews.limg).into(img);
        title.setText(currentNews.ltitle);
        date.setText(currentNews.ldt);
        return view;
    }
}
