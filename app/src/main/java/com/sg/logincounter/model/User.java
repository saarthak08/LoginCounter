package com.sg.logincounter.model;


public class User {
    private String name;
    private String password;
    private Integer id;
    private Integer counter=0;
    private String email;

    public static final String TABLE_NAME = "users";
    public static final String COLUMN_ID = "user_id";
    public static final String COLUMN_NAME = "user_name";
    public static final String COLUMN_EMAIL = "user_email";
    public static final String COLUMN_PASSWORD = "user_pswrd";
    public static final String COLUMN_COUNTER="user_counter";

    public User(String name, String password,String email, Integer id, Integer counter) {
        this.name = name;
        this.password = password;
        this.id = id;
        this.counter = counter;
        this.email=email;
    }

    public User()
    {

    }
    public  void updateCounter()
    {
        counter++;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCounter() {
        return counter;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
    }
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_NAME + " TEXT,"
                    + COLUMN_EMAIL + " DATETIME DEFAULT CURRENT_TIMESTAMP,"
                    + COLUMN_PASSWORD + " TEXT,"
                    + COLUMN_COUNTER + " INTEGER"
                    + ")";
}
