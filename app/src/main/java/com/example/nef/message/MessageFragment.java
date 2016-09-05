package com.example.nef.message;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.nef.message.Adapter.MessageListAdapter;
import com.example.nef.message.pojo.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nef on 2016/9/2.
 */
public class MessageFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view1 = inflater.inflate(R.layout.fragment_message,null);
        ListView listView = (ListView) view1.findViewById(R.id.lv_message);
       int zid =  getArguments().getInt("put");
        Log.e("start", String.valueOf(zid )+ System.currentTimeMillis());
        DBmanager db = new DBmanager();
        final List<Message> list  = new ArrayList<>();
        for (int i=0;i<db.smscontent(zid).size();i++){
            List<String> content = db.smscontent(zid);
            list.add(new Message(content.get(i)));
        }
        Log.e("stop", String.valueOf(zid) + System.currentTimeMillis());
        MessageListAdapter adapter = new MessageListAdapter(list,getActivity());
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("smsto:10086"));
                intent.setType("vnd.android-dir/mms-sms");
                intent.putExtra("sms_body", list.get(i).getContent());
                startActivity(intent);

            }
        });

        return view1;
    }
}
