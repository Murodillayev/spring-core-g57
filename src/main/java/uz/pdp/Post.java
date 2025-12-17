package uz.pdp;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class Post {
    private String id;
    private String title;
    private String content;
    private String author;
}
