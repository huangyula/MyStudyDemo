package com.hy.mystudydemo.listview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hy.mystudydemo.R;

import java.util.List;

/**
 * Created by Asus on 2017/4/5.
 */

public class MyBaseAdapter extends BaseAdapter {

    private List<String> strList;
    private Context context;

    public MyBaseAdapter(Context context, List<String> strList) {
        this.strList = strList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return strList.size();
    }

    @Override
    public Object getItem(int position) {
        return strList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(context, R.layout.item, null);
        TextView textView = (TextView) view.findViewById(R.id.text);
        textView.setText(strList.get(position));
        return view;
    }
}
