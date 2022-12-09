package at.ac.htlinn.androidexamples.json;

import java.util.Arrays;

public class Product {
    private int id;
    private String title;
    private String description;
    private double rating;
    private String[] images;

    public Product(int id, String title, String description, double rating, String[] images) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.rating = rating;
        this.images = images;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", rating=" + rating +
                ", images=" + Arrays.toString(images) +
                '}';
    }
}
