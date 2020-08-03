package com.example.demo.login.domain.model;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class SignupForm {
	// 必須入力、メールアドレス形式
	@NotBlank
	@Email
	private String heroId;

	// 必須入力、長さ4から100桁まで、半角英数字のみ
	@NotBlank
	@Length(min = 4, max = 100)
	@Pattern(regexp = "^[a-zA-Z0-9]+$")
	private String password;

	// 必須入力
	@NotBlank
	private String heroName;

	// 必須入力
	@NotBlank
	private String name;

	// @DateTimeFormatアノテーションをフィールドにつけることで、画面から渡されてきた文字列を日付型に変換してくれる
	// なお、pattern属性にどのようなフォーマットでデータが渡されてくるかを指定する
	// 必須入力、パターン指定
	@NotNull
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date birthday;

	// 必須入力、年齢制限
	@Range(min = 20, max = 100)
	private int age;

	private boolean gender;
}