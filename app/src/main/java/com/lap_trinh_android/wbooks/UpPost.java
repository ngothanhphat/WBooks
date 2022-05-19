package com.lap_trinh_android.wbooks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lap_trinh_android.wbooks.database.DatabaseReadBook;
import com.lap_trinh_android.wbooks.model.Book;

public class UpPost extends AppCompatActivity {

    EditText edtBookName, edtContent, edtImage;
    Button btnUpPost;
    DatabaseReadBook DatabaseReadBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_post);

        edtImage = findViewById (R.id.up_img);
        edtBookName = findViewById(R. id.up_book_name);
        edtContent = findViewById(R.id.up_content);
        btnUpPost = findViewById(R.id.up_post);

        DatabaseReadBook = new DatabaseReadBook(this);

//        Button đăng bài
        btnUpPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String book_name = edtBookName.getText().toString();
                String content =edtContent.getText().toString();
                String img =  edtImage.getText().toString();

                Book book = CreateBook();

                if (book_name.equals("") || content.equals("") || img.equals("")) {
                    Toast.makeText(UpPost.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    Log.e("ERR", "Nhập đầy đủ thông tin");
                } else { // Nếu nhập đầy đủ thong tin thì thực hiện thêm dữ liệu
                    DatabaseReadBook.AddBook(book);

//                    Chuyển qua view Admin và cập nhật lại dữ liệu
                    Intent intent = new Intent(UpPost.this, Admin.class);
                    finish();
                    startActivity(intent);
                }
            }
        });
    }

//    Phương thức tạo sách
    private Book CreateBook(){
        String book_name = edtBookName.getText().toString();
        String content =edtContent.getText().toString();
        String img =  edtImage.getText().toString();

        Intent intent = getIntent();

        int id = intent.getIntExtra("Id", 0);
        Book book = new Book(book_name, content, img, id);
        return book;
    }
}