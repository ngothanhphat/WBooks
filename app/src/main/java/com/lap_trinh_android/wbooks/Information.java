package com.lap_trinh_android.wbooks;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Information extends AppCompatActivity {

    TextView txtInformationApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        txtInformationApp = findViewById(R.id.txt_information);

        String infomation = " Ứng dụng được phát hành bởi 'Thành Phát & Văn Linh'\n"+
                " Uy tín đạt 100%";

        txtInformationApp.setText(infomation);
    }
}