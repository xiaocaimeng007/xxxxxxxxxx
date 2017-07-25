package com.example.happy.hongyun;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button mBtnSelectCar;
    private int carLenPos = -1;
    private int carTypePos = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mBtnSelectCar = (Button) findViewById(R.id.btn_select_car);
        mBtnSelectCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CarActivity.class);
                intent.putExtra("len",carLenPos);
                intent.putExtra("type",carTypePos);
                startActivityForResult(intent, 1);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case 1:
                    String data1 = data.getStringExtra("data");
                    carLenPos = data.getIntExtra("lenPosition", -1);
                    carTypePos = data.getIntExtra("typePosition", -1);
                    if (!TextUtils.isEmpty(data1)) {
                        mBtnSelectCar.setText(data1);
                    }
                    break;
            }
        }
    }
}
