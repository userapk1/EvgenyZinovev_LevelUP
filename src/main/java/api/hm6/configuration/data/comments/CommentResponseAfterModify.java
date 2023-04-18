package api.hm6.configuration.data.comments;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Builder
public class CommentResponseAfterModify {

    private Integer id;
    private Integer post_id;
    private String name;
    private String email;
    private String body;
}