package api.hm6.configuration.data.posts;

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
public class CreatePostReq {
    @SuppressWarnings("checkstyle:MemberName")
    private Integer user_id;
    private String title;
    private String body;
}
