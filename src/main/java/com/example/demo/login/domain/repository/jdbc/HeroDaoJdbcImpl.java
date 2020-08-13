package com.example.demo.login.domain.repository.jdbc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domain.model.Hero;
import com.example.demo.login.domain.repository.HeroDao;

// インタフェースを実装したリポジトリークラス
@Repository("HeroDaoJdbcImpl")
public class HeroDaoJdbcImpl implements HeroDao {
	// JdbcTemplateはSpringが用意してくれているため、既にBean定義がされている
	// そのため、@Autowiredするだけで使えるようになる
	// このクラスのメソッドを使って、SQLを実行していく
	@Autowired
	JdbcTemplate jdbc;
	@Autowired
	PasswordEncoder passwordEncoder;

	// Heroテーブルの件数を取得
	@Override
	public int count() throws DataAccessException {
		// 全権取得してカウント
		int count = jdbc.queryForObject("SELECT COUNT(*) FROM m_hero", Integer.class);
		return count;
	}

	// Heroテーブルのデータを1件取得
	@Override
	public int insertOne(Hero hero) throws DataAccessException {
		// パスワード暗号化
		String password = passwordEncoder.encode(hero.getPassword());
		// DBにデータを1件登録
		int rowNumber = jdbc.update("INSERT INTO m_hero(hero_id, "+"password, "+"hero_name, "+"name, "+"birthday, "
									+"age, "+"gender, "+"role)"+" VALUES(?,?,?,?,?,?,?,?)"
									, hero.getHeroId(), password, hero.getHeroName(), hero.getName()
									, hero.getBirthday(), hero.getAge(), hero.isGender(), hero.getRole());
		return rowNumber;
	}

	// Heroテーブルのデータを1件取得
	@Override
	public Hero selectOne(String heroId) throws DataAccessException {
		/* 1件のレコードを取得するには、queryForMapメソッドを使う
		 * 戻り値は、Map<String, Object>型
		 * 第1引数にSQL文、第2引数以降にPreparedStatementを指定する
		 * 戻り値のMapのgetメソッドにカラムを指定することで、値を取得することができる
		 */
		Map<String, Object> map = jdbc.queryForMap("SELECT * FROM m_hero"+" WHERE hero_id = ?", heroId);
		// 結果返却用の変数
		Hero hero = new Hero();
		// 取得したデータを結果返却用の変数にセットしていく
		hero.setHeroId((String) map.get("hero_id"));
		hero.setPassword((String) map.get("password"));
		hero.setHeroName((String) map.get("hero_name"));
		hero.setName((String) map.get("name"));
		hero.setBirthday((Date) map.get("birthday"));
		hero.setAge((Integer) map.get("age"));
		hero.setGender((Boolean) map.get("gender"));
		hero.setRole((String) map.get("map"));
		return hero;
	}

	// Heroテーブルの全データを取得
	@Override
	public List<Hero> selectMany() throws DataAccessException {
		// m_heroテーブルのデータを全権取得
		/* カウントの結果や、カラムを1つだけ取得してくるような場合には、queryForObjectメソッドを使う
		 * 第1引数にSQL文、第2引数に戻り値のオブジェクトのclassを指定する
		 * 複数件selectをする場合は、queryForListメソッドを使う戻り値型には、List<Map<String, Object>>を指定する
		 * Listが行を表していて、Mapが列を表している
		 * Mapのgetメソッドでテーブルのカラム名を指定することで値を取得できる
		 * 引数を追加すれば、PreparedStatementを使うこともできる
		 * 今回の場合は、拡張for文を使って、List<Map<String, Object>>をList<Hero>に変換している
		 */
		List<Map<String, Object>> getList = jdbc.queryForList("SELECT * FROM m_hero");
		// 結果返却用の変数
		List<Hero> heroList = new ArrayList<>();
		// 取得したデータを結果返却用のListに格納していく
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
			// 結果返却用のListに追加
			heroList.add(hero);
		}
		return heroList;
	}

	// Heroテーブルのデータを1件更新
	@Override
	public int updateOne(Hero hero) throws DataAccessException {
		// パスワード暗号化
		String password = passwordEncoder.encode(hero.getPassword());
		// 1件更新
		int rowNumber = jdbc.update("UPDATE M_HERO"+" SET"+" password=?, "+"hero_name=?, "+"name=?, "+"birthday=?, "
									+"age=?, "+"gender=? "+"WHERE hero_id=?",
									password, hero.getHeroName(), hero.getName(), hero.getBirthday(),
									hero.getAge(), hero.isGender(), hero.getHeroId());
//		トランザクション確認のため、わざと例外をthrowする
//		if(rowNumber>0) {
//			throw new DataAccessException("トランザクションテスト") {};
//		}
		return rowNumber;
	}

	// Userテーブルを1件削除
	@Override
	public int deleteOne(String heroId) throws DataAccessException {
		// 1件削除
		int rowNumber = jdbc.update("DELETE FROM m_hero WHERE hero_id = ?", heroId);
		return rowNumber;
	}

	// Heroテーブルの前データをCSVに出力する
	@Override
	public void heroCsvOut() throws DataAccessException {
		// M_HEROテーブルのデータを全件取得するSQL
		String sql = "SELECT * FROM m_hero";
		// ResultSetExtractorの生成
		HeroRowCallbackHandler handler = new HeroRowCallbackHandler();
		// SQL実行&CSV出力
		jdbc.query(sql, handler);
	}
}
