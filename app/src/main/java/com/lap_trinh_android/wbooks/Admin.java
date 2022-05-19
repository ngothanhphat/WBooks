package com.lap_trinh_android.wbooks;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.lap_trinh_android.wbooks.adapter.AdapterBook;
import com.lap_trinh_android.wbooks.database.DatabaseReadBook;
import com.lap_trinh_android.wbooks.model.Book;

import java.util.ArrayList;

public class Admin extends AppCompatActivity {

    ListView listView;
    Button btnAdd;
    ArrayList<Book> BookArrayList;
    AdapterBook AdapterBook;
    DatabaseReadBook DatabaseReadBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        listView = findViewById(R.id.list_view_admin);
        btnAdd = findViewById(R.id.btn_add_book);

        initList();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Lấy id tài khoản để biết tài khoản admin nào đã vào chỉnh sửa
                Intent intent1 = getIntent();
                int id = intent1.getIntExtra("Id",0);

//                Tiếp tục gửi id qua màn hình thêm sách
                Intent intent = new Intent( Admin.this, UpPost.class);
                intent.putExtra( "Id", id);
                startActivity(intent);
            }
        });

//        Click itemLong sẽ xóa item
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                DialogDelete(position);
                return false;
            }
        });
    }

//    Phương thức dialog hiển thị cửa sổ xóa
    private void DialogDelete(int position) {

//        Tạo đối tượng dialog
        Dialog dialog = new Dialog(this);
//        Nạp layout vào dialog
        dialog.setContentView(R.layout.dialog_delete);
//        Tắt click ra ngoài là đóng, chỉ click nó mới đóng
        dialog.setCanceledOnTouchOutside(false);

//        Ánh xạ
        Button btnYes = dialog.findViewById(R.id.btn_yes);
        Button btnNo = dialog.findViewById(R.id.btn_no);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id_book = BookArrayList.get(position).getId();

//                Xóa dữ liệu
                DatabaseReadBook.DeleteBook(id_book);

//                Cập nhật lại activity
                Intent intent = new Intent(Admin.this,Admin.class);
                finish();
                startActivity(intent);
                Toast.makeText(Admin.this,"Xóa truyện thành công",Toast.LENGTH_SHORT).show();
            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Thực hiện đóng dialog
                dialog.cancel();
            }
        });
//        Run dialog
        dialog.show();
    }

//    Gán dữ liệu cho listView
    private void initList() {
        BookArrayList = new ArrayList<>();

        DatabaseReadBook = new DatabaseReadBook(this);

        Cursor cursor1 = DatabaseReadBook.getData2();

        while (cursor1.moveToNext()) {
            int id = cursor1.getInt(0);
            String book_name = cursor1.getString(1);
            String content = cursor1.getString(2);
            String image = cursor1.getString(3);
            int id_acc = cursor1.getInt(4);

            BookArrayList.add(new Book(id, book_name, content, image, id_acc));
            AdapterBook = new AdapterBook(getApplicationContext(), BookArrayList);

            listView.setAdapter(AdapterBook);
        }
        cursor1.moveToFirst();
        cursor1.close();
    }
}