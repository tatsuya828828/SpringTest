package com.example.demo.login.domain.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.demo.login.domain.model.Hero;

/* RowMapperを使用するには、RowMapper<?>インタフェースを継承する
 * ?の部分には、マッピングに使うJavaオブジェクトのクラスを指定する
 */
public class HeroRowMapper implements RowMapper<Hero> {
	// RowMapperを継承して、mapRowメソッドをOverrideする
	// このとき引数のResultSetには、Select結果が入っている
	// そのため、ResultSetの値をHeroクラスにセットする
	@Override
	public Hero mapRow(ResultSet rs, int rowNum) throws SQLException {
		// 戻り値用のHeroインスタンスを生成
		Hero hero = new Hero();
		// ResultSetの取得結果をHeroインスタンスにセット
		hero.setHeroId(rs.getString("hero_id"));
		hero.setPassword(rs.getString("password"));
		hero.setHeroName(rs.getString("hero_name"));
		hero.setName(rs.getString("name"));
		hero.setBirthday(rs.getDate("birthday"));
		hero.setAge(rs.getInt("age"));
		hero.setGender(rs.getBoolean("gender"));
		hero.setRole(rs.getString("role"));
		// 最後にHeroクラスのインスタンスを返す
		return hero;
	}
}
