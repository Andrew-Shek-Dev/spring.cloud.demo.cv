package io.tecky.data;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
public class UserInfo implements Serializable {
    @Id
    private Long id;

    private String user_id;

    private String nick_name;

    private String mobile_no;

    private String password;

    private int is_login;

    private LocalDateTime login_time;

    @Builder.Default private int is_del = 0;

    @Builder.Default private LocalDateTime create_time = LocalDateTime.now();


}
