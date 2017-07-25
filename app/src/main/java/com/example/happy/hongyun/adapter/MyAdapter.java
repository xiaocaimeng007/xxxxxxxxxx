package com.example.happy.hongyun.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.example.happy.hongyun.R;
import com.example.happy.hongyun.base.CommomAdapter;

import java.util.List;

/**
 * Created by happy on 2017/7/24.
 */

public class MyAdapter extends CommomAdapter<String> {
    private int seletedPotision = -1;
    public MyAdapter(Context context, List<String> list) {
        super(context, list);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item,null);
            viewHolder = new ViewHolder();
            viewHolder.checkBox = (CheckBox) convertView.findViewById(R.id.checkbox);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if(seletedPotision == position){
            viewHolder.checkBox.setChecked(true);
            viewHolder.checkBox.setEnabled(false);
        }else{
            viewHolder.checkBox.setChecked(false);
            viewHolder.checkBox.setEnabled(true);
        }
        viewHolder.checkBox.setText(getItem(position));
        viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(viewHolder.checkBox.isChecked()){
                    seletedPotision = position;
                    notifyDataSetChanged();
                }
            }
        });
        return convertView;
    }

    public class ViewHolder{
        CheckBox checkBox;
    }

    public int getSeletedPotision(){
        return seletedPotision;
    }
    public void setSeletedPotision(int potision){
        this.seletedPotision = potision;
    }
}
