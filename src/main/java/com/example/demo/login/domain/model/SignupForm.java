package com.example.demo.login.domain.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class SignupForm {
	private String heroId;
	private String password;
	private String heroName;
	private String name;
	// @DateTimeFormatアノテーションをフィールドにつけることで、画面から渡されてきた文字列を日付型に変換してくれる
	// なお、pattern属性にどのようなフォーマットでデータが渡されてくるかを指定する
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date birthday;
	private int age;
	private boolean gender;
}