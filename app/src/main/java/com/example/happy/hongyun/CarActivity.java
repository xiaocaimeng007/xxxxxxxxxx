package com.example.happy.hongyun;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.happy.hongyun.adapter.MyAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by happy on 2017/7/24.
 */

public class CarActivity extends Activity {

    private GridView mGridViewCarLength;
    private GridView mGridViewCarType;
    private TextView mSubmit;
    private int len;
    private int type;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);
        Intent intent = getIntent();
        if(intent != null){
            len = intent.getIntExtra("len", -1);
            type = intent.getIntExtra("type", -1);
        }
        initView();
    }

    private void initView() {
        mGridViewCarLength = (GridView) findViewById(R.id.grid_view_car_length);
        mGridViewCarType = (GridView) findViewById(R.id.grid_view_car_type);
        mSubmit = (TextView) findViewById(R.id.submit);
        //车长
        final List<String> list = new ArrayList<>();
        String[] stringArray = getResources().getStringArray(R.array.lengths);
        for (int i = 0; i < stringArray.length; i++) {
            list.add(stringArray[i]);
        }
        final MyAdapter myAdapter = new MyAdapter(this, list);
        myAdapter.setSeletedPotision(len);
        mGridViewCarLength.setAdapter(myAdapter);

        //车型
        final List<String> list2 = new ArrayList<>();
        String[] stringArray2 = getResources().getStringArray(R.array.types);
        for (int i = 0; i < stringArray2.length; i++) {
            list2.add(stringArray2[i]);
        }
        final MyAdapter myAdapter2 = new MyAdapter(this, list2);
        myAdapter2.setSeletedPotision(type);
        mGridViewCarType.setAdapter(myAdapter2);

        //提交
        mSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int seletedPotision = myAdapter.getSeletedPotision();
                    int seletedPotision1 = myAdapter2.getSeletedPotision();
                    if(seletedPotision == -1){
                        Toast.makeText(CarActivity.this, "请选择车长xx", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(seletedPotision1 == -1){
                        Toast.makeText(CarActivity.this, "请选择车型", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Intent intent = new Intent();
                    String s = list.get(seletedPotision) +":::"+ list2.get(seletedPotision1);

                    intent.putExtra("data",s);
                    intent.putExtra("lenPosition",seletedPotision);
                    intent.putExtra("typePosition",seletedPotision1);
                    setResult(Activity.RESULT_OK,intent);
                    finish();
                }
        });
    }
}
