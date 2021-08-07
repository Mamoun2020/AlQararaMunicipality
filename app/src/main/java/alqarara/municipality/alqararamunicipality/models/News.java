package alqarara.municipality.alqararamunicipality.models;

public class News {
    String image;
    String title;
    public News(){

    }
    public News(String image, String title) {
        this.image = image;
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
