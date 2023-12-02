package io.tecky.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

//https://kucw.github.io/blog/2020/3/java-lombok/
@Data
@Builder
public class User {
    private String id;
    private String username;
}
