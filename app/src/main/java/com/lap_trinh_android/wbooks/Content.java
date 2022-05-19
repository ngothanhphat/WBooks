package com.lap_trinh_android.wbooks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class Content extends AppCompatActivity {

    TextView txtBookName, txtContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        txtContent = findViewById(R.id.content);
        txtBookName = findViewById(R.id.book_name);

//        Lấy dữ liệu
        Intent intent = getIntent();
        String book_name = intent.getStringExtra("book_name");
        String content = intent.getStringExtra("content_book");

        txtBookName.setText(book_name);
        txtContent.setText(content);

//        Cho phép cuộn nội dung sách
        txtContent.setMovementMethod(new ScrollingMovementMethod());
    }
}