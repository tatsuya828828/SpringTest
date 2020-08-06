package com.example.demo.login.domain.model;

import java.util.Date;

import lombok.Data;

// データベースから取得した値を、コントローラークラスやサービスクラスなどの間でやりとりするためのクラス
// また、ユーザーテーブルのカラムをフィールドに持つためのクラス
// @Dataアノテーションをつけて、Lombokでgetterやsetterを自動で作る
@Data
public class Hero {
	private String userId;
	private String password;
	private String userName;
	private Date birthday;
	private int age;
	private boolean marriage;
	private String role;
}