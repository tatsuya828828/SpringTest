package com.example.demo.login.domain.repository.jdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domain.model.Hero;

// RowMapperを使ったリポジトリークラス
@Repository("HeroDaoJdbcImpl2")
// HeroDaoJdbcImplを継承することで、select文を実行する箇所だけRowMapperに変えて、insert文やupdate文はHeroDaoJdbcImplを同じ動きをする
public class HeroDaoJdbcImpl2 extends HeroDaoJdbcImpl {
	@Autowired
	private JdbcTemplate jdbc;
	// ヒーロー1件取得
	@Override
	public Hero selectOne(String heroId) {
		// 1件取得用SQL
		String sql = "SELECT * FROM m_hero WHERE hero_id = ?";
		// RowMapperの生成
		HeroRowMapper rowMapper = new HeroRowMapper();
		// SQL実行
		return jdbc.queryForObject(sql, rowMapper, heroId);
	}

	// ヒーロー全権取得
	@Override
	public List<Hero> selectMany() {
		// M_HEROテーブルのデータを全権取得するSQL
		String sql = "SELECT * FROM m_hero";
		// RowMapperの生成
		RowMapper<Hero> rowMapper = new HeroRowMapper();
		// SQL実行
		// JdbcTemplateのメソッドの引数に、RowMapperをセットする
		// そうすることで、select結果が入ったHeroクラスやList<Hero>の結果が返ってくる
		return jdbc.query(sql, rowMapper);
	}
}
