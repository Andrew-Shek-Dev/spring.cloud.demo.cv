package io.tecky.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
//https://projectlombok.org/features/Builder - @Builder
@Builder
@AllArgsConstructor
public class ApiResponse<T> {
    private String code;
    private String message;
    private T data;

    //https://docs.oracle.com/javase/tutorial/extra/generics/methods.html - Generic Methods
    public static <T> ApiResponse<T> success(String code,String message,T data){
        //https://stackoverflow.com/questions/3204623/java-generics-builder-pattern - lombok x @Builder x generic type
        ApiResponse<T> response = ApiResponse.<T>builder().code(code).message(message).data(data).build();
        return  response;
    }
}
