package ro.ubb.iss.CMS.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "ticketing")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
@NamedEntityGraphs({
        @NamedEntityGraph(name = "ticketWithUser",
                attributeNodes = @NamedAttributeNode(value = "user")),
})
public class MyTicket {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ticket_id")
  private Integer ticketID;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private User user;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "section_id")
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private Section section;

  @Column(name = "price")
  private Integer price;
}
