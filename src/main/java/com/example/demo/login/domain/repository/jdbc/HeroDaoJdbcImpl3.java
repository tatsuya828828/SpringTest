package com.example.demo.login.domain.repository.jdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domain.model.Hero;

@Repository("HeroDaoJdbcImpl3")
public class HeroDaoJdbcImpl3 extends HeroDaoJdbcImpl {
	@Autowired
	private JdbcTemplate jdbc;
	// ヒーロー1件取得
	@Override
	public Hero selectOne(String heroId) {
		// 1件取得用SQL
		String sql = "SELECT * FROM m_hero WHERE hero_id = ?";
		// RowMapperの生成
		/* BeanPropertyRowMapperでは、データベースから取得してきたカラム名と同一のフィールド名がクラスにあれば、自動でマッピングをしてくれる
		 * つまり、RowMapperのようにどのカラムとどのフィールドを一致させるか、いちいち用意する必要がない
		 * ただし、自動でマッピングするためには、
		 * カラム名は単語ごとに_で区切る、フィールド名は、2つ目の単語から大文字にする
		 * といった形式にする必要がある
		 * もし、カラム名とフィールド名が一致する場合はBeanPropertyRowMapperを使うと便利である
		 */
		RowMapper<Hero> rowMapper = new BeanPropertyRowMapper<Hero>(Hero.class);
		// SQL実行
		return jdbc.queryForObject(sql, rowMapper, heroId);
	}

	// ヒーロー全件取得
	@Override
	public List<Hero> selectMany() {
		// M_HEROテーブルのデータを全件取得するSQL
		String sql = "SELECT * FROM m_hero";
		// RowMapperの生成
		RowMapper<Hero> rowMapper = new BeanPropertyRowMapper<Hero>(Hero.class);
		// SQL実行
		return jdbc.query(sql, rowMapper);
	}
}
