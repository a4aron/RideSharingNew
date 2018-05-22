package model;

import java.time.LocalDate;

public class Order {

    private int id;
    //    private String requesterId;
//    private String providerId;
    private LocalDate date;
    private String departure;
    private String destination;
    private boolean confirmed;
    private boolean active;
    private User requestorUser;
    private User providerUser;
    private String reqComment;
    private String provComment;

    public Order() {
    }

    public Order(int id, LocalDate date, String departure, String destination, boolean confirmed, boolean active, User requestorUser, User providerUser, String reqComment, String provComment) {
        this.id = id;
        this.date = date;
        this.departure = departure;
        this.destination = destination;
        this.confirmed = confirmed;
        this.active = active;
        this.requestorUser = requestorUser;
        this.providerUser = providerUser;
        this.reqComment = reqComment;
        this.provComment = provComment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
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

    public String getReqComment() {
        return reqComment;
    }

    public void setReqComment(String reqComment) {
        this.reqComment = reqComment;
    }

    public String getProvComment() {
        return provComment;
    }

    public void setProvComment(String provComment) {
        this.provComment = provComment;
    }
}

