package com.example.demo.login.domain.model;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class SignupForm {
	// groups属性にインタフェースのクラスを指定することで、フィールドとグループの紐付けができる
	// 必須入力、メールアドレス形式
	@NotBlank(groups = ValidGroup1.class)
	@Email(groups = ValidGroup2.class)
	private String heroId;

	// 必須入力、長さ4から100桁まで、半角英数字のみ
	@NotBlank(groups = ValidGroup1.class)
	@Length(min = 4, max = 100, groups = ValidGroup2.class)
	@Pattern(regexp = "^[a-zA-Z0-9]+$", groups = ValidGroup3.class)
	private String password;

	// 必須入力
	@NotBlank(groups = ValidGroup1.class)
	private String heroName;

	// 必須入力
	@NotBlank(groups = ValidGroup1.class)
	private String name;

	// @DateTimeFormatアノテーションをフィールドにつけることで、画面から渡されてきた文字列を日付型に変換してくれる
	// なお、pattern属性にどのようなフォーマットでデータが渡されてくるかを指定する
	// 必須入力、パターン指定
	@NotNull(groups = ValidGroup1.class)
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date birthday;

	// 年齢制限
	@Min(value = 20, groups = ValidGroup2.class)
	@Max(value = 100, groups = ValidGroup2.class)
	private int age;

	private boolean gender;
}