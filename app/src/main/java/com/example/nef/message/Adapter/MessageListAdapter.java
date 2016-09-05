package com.example.nef.message.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nef.message.DBmanager;
import com.example.nef.message.R;
import com.example.nef.message.pojo.Message;

import java.util.List;

/**
 * Created by nef on 2016/9/3.
 */
public class MessageListAdapter extends BaseAdapter {
    private List<Message> list;
    Context context;
    LayoutInflater layoutInflater;

    public MessageListAdapter(List<Message> list, Context context) {
        this.list = list;
        this.context = context;
        layoutInflater= LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        viewHoder hoder = null;
       if (view==null){
           view = layoutInflater.inflate(R.layout.messagelistview,null);
           hoder = new viewHoder();
          hoder.textView = (TextView) view.findViewById(R.id.tv_content);
          hoder.imageView = (ImageView) view.findViewById(R.id.iv_img);
           view.setTag(hoder);
       }else{
        hoder = (viewHoder) view.getTag();
       }
        hoder.textView.setText(list.get(i).getContent());
        hoder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBmanager db = new DBmanager();
                int id = db.id(list.get(i).getContent());
                int ZCATEGORY_ID = db.collect();
                db.insert(ZCATEGORY_ID,id);
            }
        });
        return view;
    }
    private  class   viewHoder{
        TextView textView;
        ImageView imageView;
    }
}
