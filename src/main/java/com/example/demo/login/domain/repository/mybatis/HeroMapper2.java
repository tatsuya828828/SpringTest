package com.example.demo.login.domain.repository.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.login.domain.model.Hero;

// 設定ファイルを用いた場合用のインタフェース
@Mapper
public interface HeroMapper2 {
	// 登録用メソッド
	public boolean insert(Hero hero);
	// 1件検索用メソッド
	public Hero selectOne(String heroId);
	// 全権検索用メソッド
	public List<Hero> selectMany();
	// 1件更新メソッド
	public boolean updateOne(Hero hero);
	// 1件削除用メソッド
	public boolean deleteOne(String heroId);
}
