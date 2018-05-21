package model;
import java.time.LocalDate;

public class User {

    private int id;
    private String name;
    private LocalDate birthday;
    private String address;
    private String telNum;
    private String userName;
    private String passWord;
    private String type;
    private String comment;

    public User(int id, String name, LocalDate birthday, String address, String telNum, String userName, String passWord, String type, String comment) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.address = address;
        this.telNum = telNum;
        this.userName = userName;
        this.passWord = passWord;
        this.type = type;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelNum() {
        return telNum;
    }

    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
