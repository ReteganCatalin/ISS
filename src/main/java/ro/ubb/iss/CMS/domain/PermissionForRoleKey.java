package ro.ubb.iss.CMS.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class PermissionForRoleKey {

    @Column(name = "role_id")
    private Integer roleID;

    @Column(name = "permission_id")
    private Integer permissionID;

    public PermissionForRoleKey() {
    }

    @Override
    public String toString() {
        return "PermissionForRoleKey{" +
                "userID=" + roleID +
                ", permissionID=" + permissionID +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PermissionForRoleKey that = (PermissionForRoleKey) o;
        return roleID.equals(that.roleID) &&
                permissionID.equals(that.permissionID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleID, permissionID);
    }

    public Integer getRoleID() {
        return roleID;
    }

    public void setRoleID(Integer userID) {
        this.roleID = userID;
    }

    public Integer getPermissionID() {
        return permissionID;
    }

    public void setPermissionID(Integer permissionID) {
        this.permissionID = permissionID;
    }
}
