package entity;

import dto.UserDto;

public class UserModel extends UserDto {
    private int id;
    private String email;
    private String password;
    private String fullname;
    private String avatar;
    private int role_id;


    public UserModel(int id, String email, String password, String fullname, String avatar, int role_id) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.fullname = fullname;
        this.avatar = avatar;
        this.role_id = role_id;
    }
    public UserModel(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getRoleId() {
        return role_id;
    }

    public void setRoleId(int roleId) {
        this.role_id = roleId;
    }
}
