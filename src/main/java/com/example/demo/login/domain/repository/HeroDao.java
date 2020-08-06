package com.example.demo.login.domain.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.example.demo.login.domain.model.Hero;

// リポジトリークラスのインタフェース
// インタフェースを作ることによって、
// 後から中身の実装クラスをjdbcTemplateとNamedParameterJdbcTemplateで簡単に切り替えられる
public interface HeroDao {
	// Springでは、データベース操作で例外が発生した場合、Springが提供しているDataAccessExceptionを投げる
	// この例外クラスは、SpringJDBCだけでなく、Spring+MyBatisを使ったときにも投げられる

	// Userテーブルの件数を取得
	public int count() throws DataAccessException;

	// Userテーブルにデータを1件insert
	public int insertOne(Hero hero) throws DataAccessException;

	// Userテーブルのデータを1件取得
	public Hero selectOne(String userId) throws DataAccessException;

	// Userテーブルの全データを取得
	public List<Hero> selectMany() throws DataAccessException;

	// Userテーブルを1件更新
	public int updateOne(Hero hero) throws DataAccessException;

	// Userテーブルを1件削除
	public int deleteOne(String userId) throws DataAccessException;

	// SQL取得結果をサーバーにCSVで保存する
	public void heroCsvOut() throws DataAccessException;
}
