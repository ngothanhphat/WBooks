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

//        Nh???n d??? li???u ??? view ????ng nh???p g???i
        Intent intentpq = getIntent();
        int i = intentpq.getIntExtra("phang", 0);
        int idd = intentpq.getIntExtra("idd", 0);
        email = intentpq.getStringExtra("email");
        username = intentpq.getStringExtra("username");

        AnhXa();
        ActionBar();
        ActionViewFlipper();

//        B???t s??? ki???n click item
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

//        B???t click item cho listView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                ????ng b??i
                if (position == 0) {
                    if (i == 2) {
                        Toast.makeText(MainActivity.this, "B???n kh??ng c?? quy???n ????ng b??i", Toast.LENGTH_SHORT).show();
                        Log.e("????ng b??i: ", "B???n kh??ng c?? quy???n");
                    } else {
                        Intent intent = new Intent(MainActivity.this, Admin.class);
//                        G???i id t??i kho???n qua view admin
                        intent.putExtra("Id", id);
                        startActivity(intent);
                    }
//                    N???u v??? tr?? click v??o l?? th??ng tin th?? s??? chuy???n qua view th??ng tin app
                } else if (position == 1) {
                    Intent intent = new Intent(MainActivity.this, Information.class);
                    startActivity(intent);
//                    ????ng xu???t
                } else if (position == 2) {
                    finish();
                }
            }
        });
    }

//    Thanh actionbar v???i toolbar
    public void ActionBar() {
//        H??m h??? tr??? toolbar
        setSupportActionBar(toolbar);

//        Set n??t cho actionbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        Tao icon cho toolbar
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);

//        B???t s??? ki???n click
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    //    Ph????ng th???c cho ch???y qu???ng c??o v???i ViewFlipper
    private void ActionViewFlipper() {
//        M???ng ch???a t???m ???nh cho qu???ng c??o
        ArrayList<String> arrayAd = new ArrayList<>();
//        Add ???nh v??o m???ng. Th??m 4 ???nh
        arrayAd.add("https://toplist.vn/images/800px/rua-va-tho-230179.jpg");
        arrayAd.add("https://toplist.vn/images/800px/deo-chuong-cho-meo-230180.jpg");
        arrayAd.add("https://toplist.vn/images/800px/cu-cai-trang-230181.jpg");
        arrayAd.add("https://toplist.vn/images/800px/de-den-va-de-trang-230182.jpg");

//        Th???c hi???n v??ng l???p g??n ???nh v??o ImageView, r???i t??? imgView l??n app
        for (int i=0; i<arrayAd.size(); i++) {
            ImageView imageview = new ImageView(getApplicationContext());
//            S??? d???ng h??m th?? vi???n Picasso
            Picasso.get().load(arrayAd.get(i)).into(imageview);

//            Ph????ng th???c ch???nh h??nh ???nh v???a khung qu???ng c??o
            imageview.setScaleType(ImageView.ScaleType.FIT_XY);

//            Th??m ???nh t??? imageview v??o ViewFlipper
            viewFlipper.addView(imageview);
        }
//        Thi???t l???p t??? ?????ng ch???y cho viewFlipper ch???y trong 4 gi??y
        viewFlipper.setFlipInterval(4000);
//        Auto run viewFlipper
        viewFlipper.setAutoStart(true);

//        G???i animation cho v??o v?? ra
        Animation animation_slide_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
        Animation animation_slide_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_right);

//        G???i Animation v??o viewFlipper
        viewFlipper.setInAnimation(animation_slide_in);
        viewFlipper.setInAnimation(animation_slide_out);

    }

//    Ph????ng th???c ??nh x???
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

//        Th??ng tin
        AccountArrayList = new ArrayList<>();
        AccountArrayList.add(new Account(username, email));

        AdapterInformation = new AdapterInformation(this, R.layout.navigation_information, AccountArrayList);
        listViewInformation.setAdapter(AdapterInformation);

//        Danhh m???c
        CategoriesArrayList = new ArrayList<>();
        CategoriesArrayList.add(new Categories("????ng b??i", R.drawable.ic_baseline_post_add_24));
        CategoriesArrayList.add(new Categories("Th??ng tin", R.drawable.ic_baseline_face_24));
        CategoriesArrayList.add(new Categories("????ng xu???t", R.drawable.ic_baseline_login_24));

        AdapterCategories = new AdapterCategories(this, R.layout.categories, CategoriesArrayList);
        listView.setAdapter(AdapterCategories);
    }

//    N???p m???t menu t??m ki???m v??o actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater ().inflate(R.menu.my_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

//        N???u click v??o icon t??m ki???m s??? chuy???n qua m??n h??nh t??m ki???m
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