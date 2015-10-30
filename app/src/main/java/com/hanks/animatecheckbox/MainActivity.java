package com.hanks.animatecheckbox;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.hanks.library.AnimateCheckBox;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private List<Demo> dataList = new ArrayList<>();

    private Set<Demo> checkedSet = new HashSet<>();

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindDatas();
        bindViews();
    }

    private void bindDatas() {
        for (int i = 0; i < 100; i++) {
            Demo demo = new Demo();
            demo.setContent("this is a simple item : "+ i);
            dataList.add(demo);
        }

    }

    private void bindViews() {
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new BaseAdapter() {
            @Override public int getCount() {
                return dataList.size();
            }

            @Override public Object getItem(int position) {
                return null;
            }

            @Override public long getItemId(int position) {
                return 0;
            }

            @Override public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.item_list_demo, parent, false);
                }

                TextView text = (TextView) convertView.findViewById(R.id.text);
                final AnimateCheckBox checkBox = (AnimateCheckBox) convertView.findViewById(R.id.checkbox);

                final Demo item = dataList.get(position);
                text.setText(item.getContent());
                if(checkedSet.contains(item)){
                    checkBox.setChecked(true);
                }else {
                    //checkBox.setChecked(false); //has animation
                    checkBox.setUncheckStatus();
                }
                checkBox.setOnCheckedChangeListener(new AnimateCheckBox.OnCheckedChangeListener() {
                    @Override public void onCheckedChanged(View buttonView, boolean isChecked) {
                        if (isChecked) {
                            checkedSet.add(item);
                        } else {
                            checkedSet.remove(item);
                        }
                    }
                });

                return convertView;
            }
        });
    }
}
