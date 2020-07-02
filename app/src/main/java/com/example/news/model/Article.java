package com.example.news.model;

import android.graphics.Bitmap;

import java.util.Objects;

public class Article {

    private int id;
    private String title;
    private String author;
    private String description;
    private String content;
    private String imageURL;
    private Bitmap image;
    private Bitmap contentImage;
    private String date;

    public Article(String title, String author, String description, String content, String imageURL, String date) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.content = content;
        this.imageURL = imageURL;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title == null || title.isEmpty() ? "Title" : title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author == null || author.isEmpty() || author.equals("null") ? "unknown author" : author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description == null || description.isEmpty() || description.equals("null") ? "unknown author" : description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content == null || content.isEmpty() || content.equals("null") ? "unknown author" : content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Bitmap getContentImage() {
        return contentImage;
    }

    public void setContentImage(Bitmap contentImage) {
        this.contentImage = contentImage;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return id == article.id &&
                Objects.equals(title, article.title) &&
                Objects.equals(author, article.author) &&
                Objects.equals(description, article.description) &&
                Objects.equals(content, article.content) &&
                Objects.equals(imageURL, article.imageURL) &&
                Objects.equals(image, article.image) &&
                Objects.equals(date, article.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, description, content, imageURL, image, date);
    }

    @Override
    public String toString() {
        return "NewsDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", description='" + description + '\'' +
                ", content='" + content + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", image=" + image +
                ", date='" + date + '\'' +
                '}';
    }
}
