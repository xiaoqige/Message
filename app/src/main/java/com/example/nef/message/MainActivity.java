package com.example.nef.message;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.astuetz.PagerSlidingTabStrip;
import com.example.nef.message.Adapter.MessageAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    PagerSlidingTabStrip psts;
    ViewPager viewPager ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DBmanager db = new DBmanager();
        db.copy(this);
        List<String> zname = db.tiTle();
        List<Integer> zid = db.zid();
        psts = (PagerSlidingTabStrip) findViewById(R.id.psts);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        List<Fragment> list = new ArrayList<>();
      for (int i=0;i<zname.size();i++){
          Bundle bundle = new Bundle();
          bundle.putInt("put",zid.get(i));
          MessageFragment messageFragment = new MessageFragment();
          messageFragment.setArguments(bundle);
          list.add(messageFragment);
      }
        MessageAdapter adapter  = new MessageAdapter(getSupportFragmentManager(),list);
        viewPager.setAdapter(adapter);
        psts.setViewPager(viewPager);
//jgsdkfsjkljflaksjdflajsdlfjasldjfklasdjflasd
    }
}
