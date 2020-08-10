package com.example.demo.login.domain.repository.jdbc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domain.model.Hero;
import com.example.demo.login.domain.repository.HeroDao;

@Repository("HeroDaoNamedJdbcImpl")
public class HeroDaoNamedJdbcImpl implements HeroDao {
	@Autowired
	private NamedParameterJdbcTemplate jdbc;
	// Heroテーブルの件数を取得
	@Override
	public int count() {
		// SQL文
		String sql = "SELECT COUNT(*) FROM m_hero";
		// パラメーター生成
		SqlParameterSource params = new MapSqlParameterSource();
		// 全件取得してカウント
		return jdbc.queryForObject(sql, params, Integer.class);
	}

	// Heroテーブルにデータを1件insert
	@Override
	public int insertOne(Hero hero) {
		// SQL文
		// NamedJdbcTemplateでは、PreparedStatementに?ではなく、キー名を使う
		// 指定方法は、:キー名といった形式で指定する
		String sql = "INSERT INTO m_hero(hero_id, "+"password, "+"hero_name, "+"name, "
				+"birthday, "+"age, "+"gender, "+"role) "
				+"VALUES(:heroId, "+":password, "+":heroName"+":name, "
				+":birthday, "+":age, "+":gender, "+":role)";
		/* パラメーター
		 * SQL文に入れるパラメーターを設定するために、SqlParameterSourceクラスを使う
		 * SqlParameterSourceクラスをnewして、addValue()メソッドにキーと値をセットしていく
		 * 今回は、addValue()メソッドを連続して呼び出しているが、このような呼び出し方をメソッドチェーンという
		 * addValue()メソッドの第1引数にはキー名、第2引数には値をセットする
		 * そして、NamedJdbcParameterのメソッドにSqlParameterSourceを渡す
		 */
		SqlParameterSource params = new MapSqlParameterSource().addValue("heroId", hero.getHeroId()).addValue("password", hero.getPassword())
									.addValue("heroName", hero.getHeroName()).addValue("name", hero.getName()).addValue("birthday", hero.getBirthday())
									.addValue("age", hero.getAge()).addValue("gender", hero.isGender()).addValue("role", hero.getRole());
		// SQL実行
		return jdbc.update(sql, params);
	}

	// Heroテーブルのデータを1件取得
	@Override
	public Hero selectOne(String heroId) {
		// SQL文
		String sql = "SELECT * FROM m_hero WHERE hero_id = :heroId";
		// パラメーター
		SqlParameterSource params = new MapSqlParameterSource().addValue("heroId", heroId);
		// SQL実行
		Map<String, Object> map = jdbc.queryForMap(sql, params);
		// 結果返却用のインスタンスを生成
		Hero hero = new Hero();

		// 取得データをインスタンスをセットしていく
		hero.setHeroId((String) map.get("hero_id"));
		hero.setPassword((String) map.get("password"));
		hero.setHeroName((String) map.get("hero_name"));
		hero.setName((String) map.get("name"));
		hero.setBirthday((Date) map.get("birthday"));
		hero.setAge((Integer) map.get("age"));
		hero.setGender((Boolean) map.get("gender"));
		hero.setRole((String) map.get("role"));

		return hero;
	}

	// Heroテーブルの全データを取得
	@Override
	public List<Hero> selectMany() {
		// SQL文
		String sql = "SELECT * FROM m_hero";
		// パラメーター
		SqlParameterSource params = new MapSqlParameterSource();
		// SQL実行
		List<Map<String, Object>> getList = jdbc.queryForList(sql, params);
		// 結果返却用のList
		List<Hero> heroList = new ArrayList<>();
		// 取得データ分 loop
		for(Map<String, Object> map: getList) {
			// Heroインスタンスの生成
			Hero hero = new Hero();
			// Heroインスタンスに取得したデータをセットする
			hero.setHeroId((String) map.get("hero_id"));
			hero.setPassword((String) map.get("password"));
			hero.setHeroName((String) map.get("hero_name"));
			hero.setName((String) map.get("name"));
			hero.setBirthday((Date) map.get("birthday"));
			hero.setAge((Integer) map.get("age"));
			hero.setGender((Boolean) map.get("gender"));
			hero.setRole((String) map.get("role"));
			// Listに追加
			heroList.add(hero);
		}
		return heroList;
	}

	// Heroテーブルを1件更新
	@Override
	public int updateOne(Hero hero) {
		// SQL文
		String sql = "UPDATE M_HERO"+" SET "+"password = :password, "+"hero_name = :heroName, "+"name = :name, "
					+"birthday = :birthday, "+"age = :age, "+"gender = :gender, "+"WHERE hero_id = :heroId";
		// パラメータ
		SqlParameterSource params = new MapSqlParameterSource().addValue("heroId", hero.getHeroId()).addValue("password", hero.getPassword())
									.addValue("heroName", hero.getHeroName()).addValue("name", hero.getName()).addValue("birthday", hero.getBirthday())
									.addValue("age", hero.getAge()).addValue("gender", hero.isGender());
		// SQL実行
		return jdbc.update(sql, params);
	}

	// Heroテーブル1件削除
	@Override
	public int deleteOne(String heroId) {
		// SQL文
		String sql = "DELETE FROM m_hero WHERE hero_id = :heroId";
		// パラメーター
		SqlParameterSource params = new MapSqlParameterSource().addValue("heroId", heroId);
		// SQL実行
		int rowNumber = jdbc.update(sql, params);
		return rowNumber;
	}

	// SQL取得結果をサーバーにCSVで保存する
	@Override
	public void heroCsvOut() {
		// M_HEROテーブルのデータを全権取得するSQL
		String sql = "SELECT * FROM m_hero";
		// ResultSetExtractorの生成
		HeroRowCallbackHandler handler = new HeroRowCallbackHandler();
		// クエリ実行&CSV出力
		jdbc.query(sql, handler);
	}
}
