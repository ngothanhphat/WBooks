package com.lap_trinh_android.wbooks;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;

import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.lap_trinh_android.wbooks.adapter.AdapterBook;
import com.lap_trinh_android.wbooks.adapter.AdapterCategories;
import com.lap_trinh_android.wbooks.adapter.AdapterInformation;
import com.lap_trinh_android.wbooks.database.DatabaseReadBook;
import com.lap_trinh_android.wbooks.model.Account;
import com.lap_trinh_android.wbooks.model.Book;
import com.lap_trinh_android.wbooks.model.Categories;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    ViewFlipper viewFlipper;
    NavigationView navigationView;
    ListView listView, listViewNew, listViewInformation;
    DrawerLayout drawerLayout;

    String email;
    String username;

    ArrayList<Book> BookArrayList;
    ArrayList<Categories> CategoriesArrayList;
    ArrayList<Account> AccountArrayList;

    AdapterBook AdapterBook;
    AdapterInformation AdapterInformation;
    AdapterCategories AdapterCategories;

    DatabaseReadBook DatabaseReadBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseReadBook = new DatabaseReadBook(this);

//        Nhận dữ liệu ở view đăng nhập gửi
        Intent intentpq = getIntent();
        int i = intentpq.getIntExtra("phang", 0);
        int idd = intentpq.getIntExtra("idd", 0);
        email = intentpq.getStringExtra("email");
        username = intentpq.getStringExtra("username");

        AnhXa();
        ActionBar();
        ActionViewFlipper();

//        Bắt sự kiện click item
        listViewNew.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, Content.class);

                String book_name = BookArrayList.get(position).getBookName();
                String content_book = BookArrayList.get(position).getContent();
                intent.putExtra("book_name",book_name);
                intent.putExtra("content_book", content_book);
                startActivity(intent);
            }
        });

//        Bắt click item cho listView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Đăng bài
                if (position == 0) {
                    if (i == 2) {
                        Toast.makeText(MainActivity.this, "Bạn không có quyền đăng bài", Toast.LENGTH_SHORT).show();
                        Log.e("Đăng bài: ", "Bạn không có quyền");
                    } else {
                        Intent intent = new Intent(MainActivity.this, Admin.class);
//                        Gửi id tài khoản qua view admin
                        intent.putExtra("Id", id);
                        startActivity(intent);
                    }
//                    Nếu vị trí click vào là thông tin thì sẽ chuyển qua view thông tin app
                } else if (position == 1) {
                    Intent intent = new Intent(MainActivity.this, Information.class);
                    startActivity(intent);
//                    Đăng xuất
                } else if (position == 2) {
                    finish();
                }
            }
        });
    }

//    Thanh actionbar với toolbar
    public void ActionBar() {
//        Hàm hỗ trợ toolbar
        setSupportActionBar(toolbar);

//        Set nút cho actionbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        Tao icon cho toolbar
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);

//        Bắt sự kiện click
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    //    Phương thức cho chạy quảng cáo với ViewFlipper
    private void ActionViewFlipper() {
//        Mảng chứa tấm ảnh cho quảng cáo
        ArrayList<String> arrayAd = new ArrayList<>();
//        Add ảnh vào mảng. Thêm 4 ảnh
        arrayAd.add("https://toplist.vn/images/800px/rua-va-tho-230179.jpg");
        arrayAd.add("https://toplist.vn/images/800px/deo-chuong-cho-meo-230180.jpg");
        arrayAd.add("https://toplist.vn/images/800px/cu-cai-trang-230181.jpg");
        arrayAd.add("https://toplist.vn/images/800px/de-den-va-de-trang-230182.jpg");

//        Thực hiện vòng lặp gán ảnh vào ImageView, rồi từ imgView lên app
        for (int i=0; i<arrayAd.size(); i++) {
            ImageView imageview = new ImageView(getApplicationContext());
//            Sử dụng hàm thư viện Picasso
            Picasso.get().load(arrayAd.get(i)).into(imageview);

//            Phương thức chỉnh hình ảnh vừa khung quảng cáo
            imageview.setScaleType(ImageView.ScaleType.FIT_XY);

//            Thêm ảnh từ imageview vào ViewFlipper
            viewFlipper.addView(imageview);
        }
//        Thiết lập tự động chạy cho viewFlipper chạy trong 4 giây
        viewFlipper.setFlipInterval(4000);
//        Auto run viewFlipper
        viewFlipper.setAutoStart(true);

//        Gọi animation cho vào và ra
        Animation animation_slide_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
        Animation animation_slide_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_right);

//        Gọi Animation vào viewFlipper
        viewFlipper.setInAnimation(animation_slide_in);
        viewFlipper.setInAnimation(animation_slide_out);

    }

//    Phương thức ánh xạ
    private void AnhXa() {
        toolbar = findViewById(R.id.tool_bar_main);
        viewFlipper = findViewById(R.id.view_flipper);
        listViewNew = findViewById(R.id.list_view);
        listView = findViewById (R.id.list_view_main);
        listViewInformation = findViewById(R.id.list_view_information);
        navigationView = findViewById(R.id.navigation_view);
        drawerLayout = findViewById (R.id.drawer_layout);

        BookArrayList = new ArrayList<>();

        Cursor cursor1 = DatabaseReadBook.getData1();
        while (cursor1.moveToNext()) {

            int id = cursor1.getInt(0);
            String book_name = cursor1.getString(1);
            String content = cursor1.getString(2);
            String image = cursor1.getString(3);
            int id_acc = cursor1.getInt(4);

            BookArrayList.add(new Book (id, book_name, content, image, id_acc));

            AdapterBook = new AdapterBook(getApplicationContext(), BookArrayList);
            listViewNew.setAdapter(AdapterBook);
        }
        cursor1.moveToFirst();
        cursor1.close();

//        Thông tin
        AccountArrayList = new ArrayList<>();
        AccountArrayList.add(new Account(username, email));

        AdapterInformation = new AdapterInformation(this, R.layout.navigation_information, AccountArrayList);
        listViewInformation.setAdapter(AdapterInformation);

//        Danhh mục
        CategoriesArrayList = new ArrayList<>();
        CategoriesArrayList.add(new Categories("Đăng bài", R.drawable.ic_baseline_post_add_24));
        CategoriesArrayList.add(new Categories("Thông tin", R.drawable.ic_baseline_face_24));
        CategoriesArrayList.add(new Categories("Đăng xuất", R.drawable.ic_baseline_login_24));

        AdapterCategories = new AdapterCategories(this, R.layout.categories, CategoriesArrayList);
        listView.setAdapter(AdapterCategories);
    }

//    Nạp một menu tìm kiếm vào actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater ().inflate(R.menu.my_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

//        Nếu click vào icon tìm kiếm sẽ chuyển qua màn hình tìm kiếm
        switch (item.getItemId()){
            case R.id.menu1:
                Intent intent = new Intent(MainActivity.this,Search.class);
                startActivity(intent);
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}