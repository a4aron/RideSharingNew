package model;

public class Order {

    private String id;
//    private String requesterId;
//    private String providerId;
    private String date;
    private String departure;
    private String destination;
    private String comment;
    private boolean confirmed;
    private boolean active;
    private User requestorUser;
    private User providerUser;

    public Order(String id, String date, String departure, String destination, String comment, boolean confirmed, boolean active, User requestorUser, User providerUser) {
        this.id = id;
        this.date = date;
        this.departure = departure;
        this.destination = destination;
        this.comment = comment;
        this.confirmed = confirmed;
        this.active = active;
        this.requestorUser = requestorUser;
        this.providerUser = providerUser;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public User getRequestorUser() {
        return requestorUser;
    }

    public void setRequestorUser(User requestorUser) {
        this.requestorUser = requestorUser;
    }

    public User getProviderUser() {
        return providerUser;
    }

    public void setProviderUser(User providerUser) {
        this.providerUser = providerUser;
    }
}
