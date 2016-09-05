package com.example.nef.message.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import com.example.nef.message.DBmanager;
import com.example.nef.message.MainActivity;

import java.util.List;

/**
 * Created by nef on 2016/9/2.
 */
public class MessageAdapter extends FragmentPagerAdapter {

    private List<Fragment> list ;
    DBmanager db;
    private  List<String> zname;

    public MessageAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list = list;
        db = new DBmanager();
        zname = db.tiTle();
    }
    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return zname.get(position);
    }
}
