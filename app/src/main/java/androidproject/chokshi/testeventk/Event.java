package androidproject.chokshi.testeventk;


import java.util.Date;

public class Event {
    private String title;
    private Date eventDate;
    private String imgUrl;
    private double latitude;
    private double longitude;
    private String description;
    private String address;


    public Event(String title, Date eventDate, String imgUrl, double latitude, double longitude, String description, String address) {
        this.title = title;
        this.eventDate = eventDate;
        this.imgUrl = imgUrl;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
        this.address = address;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
