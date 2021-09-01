package org.javamentor.social.login.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

/*
Тестовый класс можно удалить!
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TestDto {

    @NotNull
    @PositiveOrZero(message = "Отрицательный ID!")
    private Long id;

    @NotBlank(message = "Пустой email!")
    private String email;

    @NotBlank(message = "Пустой пароль!")
    private String password;

}
