package com.example.demo.trySpring;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
// リポジトリークラスにも@Repositoryアノテーションをつける
// こうすることでDI(依存性注入)に登録される
public class HeroRepository {
	// JdbcTemplateとはJDBC接続用のクラスである
	// JdbcTemplateを使う場合は、@Autowiredアノテーションをつける
	// @Autowiredアノテーションをつけることでインスタンスを生成している
	@Autowired
	private JdbcTemplate jdbcTemplate;
	public Map<String, Object> findOne(int id){
		// SELECT文
		String query = "SELECT "+"hero_id, "+"hero_name, "+"name, "+"age "+"FROM hero "+"WHERE hero_id=?";
		// 検索実行してMapに情報を格納
		Map<String, Object> hero = jdbcTemplate.queryForMap(query, id);

		return hero;
	}
}
