package ro.ubb.iss.CMS.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
class PermissionListKey implements Serializable {

  public PermissionListKey() {
  }

  @Column(name = "user_id")
  private Integer userID;

  @Column(name = "permission_id")
  private Integer permissionID;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PermissionListKey that = (PermissionListKey) o;
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

  public PermissionListKey(Integer userID, Integer permissionID) {
    this.userID = userID;
    this.permissionID = permissionID;
  }
}

@Entity
@Table(name = "permission_list")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class PermissionForUser {

  @EmbeddedId PermissionListKey permissionListKey;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @MapsId("user_id")
  @JoinColumn(name = "user_id")
  User user;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @MapsId("permission_id")
  @JoinColumn(name = "permission_id")
  Permission permission;
}
