package com.lap_trinh_android.wbooks.model;

public class Book {
    private int Id;
    private String BookName;
    private String Content;
    private String Image;
    private int Id_Acc;

    public Book(String bookName, String content, String image, int id_Acc) {
        BookName = bookName;
        Content = content;
        Image = image;
        Id_Acc = id_Acc;
    }

    public Book(int id, String bookName, String content, String image, int id_Acc) {
        Id = id;
        BookName = bookName;
        Content = content;
        Image = image;
        Id_Acc = id_Acc;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getBookName() {
        return BookName;
    }

    public void setBookName(String bookName) {
        BookName = bookName;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public int getId_Acc() {
        return Id_Acc;
    }

    public void setId_Acc(int id_Acc) {
        Id_Acc = id_Acc;
    }
}
