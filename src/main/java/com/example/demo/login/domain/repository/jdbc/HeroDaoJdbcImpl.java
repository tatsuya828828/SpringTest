package com.example.demo.login.domain.repository.jdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domain.model.Hero;
import com.example.demo.login.domain.repository.HeroDao;

// インタフェースを実装したクラス
@Repository
public class HeroDaoJdbcImpl implements HeroDao {
	// JdbcTemplateはSpringが用意してくれているため、既にBean定義がされている
	// そのため、@Autowiredするだけで使えるようになる
	// このクラスのメソッドを使って、SQLを実行していく

	@Autowired
	JdbcTemplate jdbc;

	// Heroテーブルの件数を取得
	@Override
	public int count() throws DataAccessException {
		return 0;
	}

	// Heroテーブルのデータを1件取得
	@Override
	public int insertOne(Hero hero) throws DataAccessException {
		return 0;
	}

	// Heroテーブルのデータを1件取得
	public Hero selectOne(String heroId) throws DataAccessException {
		return null;
	}

	// Heroテーブルの全データを取得
	@Override
	public List<Hero> selectMany() throws DataAccessException {
		return null;
	}

	// Heroテーブルのデータを1件更新
	@Override
	public int updateOne(Hero hero) throws DataAccessException {
		return 0;
	}

	// Userテーブルを1件削除
	@Override
	public int deleteOne(String heroId) throws DataAccessException {
		return 0;
	}

	// Heroテーブルの前データをCSVに出力する
	@Override
	public void heroCsvOut() throws DataAccessException {
	}
}
