package ro.ubb.iss.CMS.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@ToString
@Builder
public class ProposalDto {

    private Integer proposalID;
    private Integer userInfoID;
    private Integer paperID;
    private Integer metaInfoID;
    private Integer abstractID;


}
