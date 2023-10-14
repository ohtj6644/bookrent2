package com.example.bookRent.book;


import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookForm {

    @NotEmpty(message = "제목은 필수항목입니다.")
    private String name;


    @NotEmpty(message = "글쓴이는 필수항목입니다.")
    private String writer;

}
