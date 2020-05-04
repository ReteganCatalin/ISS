package ro.ubb.iss.CMS.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@ToString
@Builder
public class ProposalListDto {
    private Integer sectionID;
    private Integer proposalID;
}
