package com.example.myfirstapp.model;

public class UserInfo {
    private String name;
    private String email;
    private String password;
    private String confirm_password;
    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name=name;
    }
    public String getConfirm_password()
    {
        return confirm_password;
    }
    public void setConfirm_password(String confirm_password)
    {
        this.confirm_password=confirm_password;
    }
}