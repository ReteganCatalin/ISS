package ro.ubb.iss.CMS.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
@Embeddable
public class PcMemberKey implements Serializable {

  @Column(name = "user_id")
  private Integer userID;

  @Column(name = "conference_id")
  private Integer conferenceID;
}
