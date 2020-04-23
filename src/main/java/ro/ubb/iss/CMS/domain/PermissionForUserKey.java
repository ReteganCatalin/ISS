package ro.ubb.iss.CMS.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PermissionForUserKey implements Serializable {

  public PermissionForUserKey() {}

  @Column(name = "user_id")
  private Integer userID;

  @Column(name = "permission_id")
  private Integer permissionID;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PermissionForUserKey that = (PermissionForUserKey) o;
    return userID.equals(that.userID) && permissionID.equals(that.permissionID);
  }

  @Override
  public String toString() {
    return "PermissionListKey{" + "userID=" + userID + ", permissionID=" + permissionID + '}';
  }

  @Override
  public int hashCode() {
    return Objects.hash(userID, permissionID);
  }

  public Integer getUserID() {
    return userID;
  }

  public void setUserID(Integer userID) {
    this.userID = userID;
  }

  public Integer getPermissionID() {
    return permissionID;
  }

  public void setPermissionID(Integer permissionID) {
    this.permissionID = permissionID;
  }

  public PermissionForUserKey(Integer userID, Integer permissionID) {
    this.userID = userID;
    this.permissionID = permissionID;
  }
}
