package ro.ubb.iss.CMS.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RoleForUserKey implements Serializable {

    @Column(name = "user_id")
    private Integer userID;

    @Column(name = "role_id")
    private Integer roleID;
    public RoleForUserKey() {
    }

    @Override
    public String toString() {
        return "RoleForUserKey{" +
                "userID=" + userID +
                ", roleID=" + roleID +
                '}';
    }

    public RoleForUserKey(Integer userID, Integer roleID) {
        this.userID = userID;
        this.roleID = roleID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleForUserKey that = (RoleForUserKey) o;
        return userID.equals(that.userID) &&
                roleID.equals(that.roleID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID, roleID);
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getRoleID() {
        return roleID;
    }

    public void setRoleID(Integer roleID) {
        this.roleID = roleID;
    }
}
