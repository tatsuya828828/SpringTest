package com.example.demo.login.domain.service;

import java.util.List;

import com.example.demo.login.domain.model.Hero;

public interface RestService {
	// 1件登録用メソッド
	public boolean insert(Hero hero);
	// 1件検索用メソッド
	public Hero selectOne(String heroId);
	// 全権検索用メソッド
	public List<Hero> selectMany();
	// 1件更新用メソッド
	public boolean updateOne(Hero hero);
	// 1件削除用メソッド
	public boolean delete(String heroId);
}
