package io.tecky.data;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@Builder
public class UserSmsCode {
    @Id
    private Long id;
    private String mobile_no;
    private String sms_code;
    //Fucking DateTime Design in Java, so LocalDateTime is recommended in postgresql case
    //https://stackoverflow.com/questions/71287698/getting-a-timestamp-from-postgre-as-a-localdatetime-with-hibernateorm
    //https://www.geeksforgeeks.org/localdatetime-now-method-in-java-with-examples/
    //https://tada.github.io/pljava/use/datetime.html
    //|----PostgresSQL Type---      |---Java SQL Mapped Type ---        |
    //|date                         |java.time.LocalDate.class          |
    //|time without time zone       |java.time.LocalTime.class          |
    //|time with time zone          |java.time.OffsetTime.class         |
    //|timestamp without time zone  |java.time.LocalDateTime.class      |
    //|timestamp with time zone     |ava.time.OffsetDateTime.class      |
    @Builder.Default private LocalDateTime send_time = LocalDateTime.now();
    @Builder.Default private LocalDateTime create_time = LocalDateTime.now();
}
