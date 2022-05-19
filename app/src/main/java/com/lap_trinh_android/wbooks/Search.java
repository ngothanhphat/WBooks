package com.lap_trinh_android.wbooks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.lap_trinh_android.wbooks.adapter.AdapterBook;
import com.lap_trinh_android.wbooks.database.DatabaseReadBook;
import com.lap_trinh_android.wbooks.model.Book;

import java.util.ArrayList;

public class Search extends AppCompatActivity {

    ListView listView;
    EditText edt;

    ArrayList<Book> BookArrayList;
    ArrayList<Book> arrayList;

    AdapterBook AdapterBook;

    DatabaseReadBook DatabaseReadBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        listView = findViewById(R.id.list_view_search);
        edt = findViewById(R.id.search);

        initList();

//        Bắt click cho item
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Search.this, Content.class);

                String nameB = arrayList.get(position).getBookName();
                String contentB = arrayList.get(position).getContent();
                intent.putExtra("book_name", nameB);
                intent.putExtra("content", contentB);
                startActivity(intent);
            }
        });

//        editText search
        edt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });
    }

//    Search
    private void filter(String  text) {
//        Xóa dữ liệu bảng
        arrayList.clear();
        ArrayList<Book> filteredList = new ArrayList<>();
        for (Book item: BookArrayList){
            if (item.getBookName().toLowerCase().contains(text.toLowerCase())){
//                Thêm item vào  filteredList
                filteredList.add(item);

//                Thêm vào mảng
                arrayList.add(item);
            }
        }
        AdapterBook.filterList(filteredList);
    }

//    Phương thức lấy dữ liệu, gán vào listView
    private  void initList() {
        BookArrayList = new ArrayList<>();
        arrayList = new ArrayList<>();

        DatabaseReadBook = new DatabaseReadBook(this);

        Cursor cursor = DatabaseReadBook.getData2();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String book_name = cursor.getString(1);
            String content = cursor.getString(2);
            String image = cursor.getString(3);
            int id_acc = cursor.getInt(4);

            BookArrayList.add(new Book(id, book_name, content, image, id_acc));
            arrayList.add(new Book(id, book_name, content,image,id_acc));

            AdapterBook = new AdapterBook(getApplicationContext(), BookArrayList);

            listView.setAdapter(AdapterBook);
        }
        cursor.moveToFirst();
        cursor.close();
    }
}