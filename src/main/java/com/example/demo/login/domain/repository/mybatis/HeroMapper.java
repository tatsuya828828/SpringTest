package com.example.demo.login.domain.repository.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.login.domain.model.Hero;

// MyBatisでSQL実行するクラスには@Mapperアノテーションをつける
@Mapper
public interface HeroMapper {
	// 変数の指定
	// 登録用メソッド
	// SQLを実行するためには、@Insertや@Selectなどのアノテーションをつける
	// #{変数名}と指定することで、SQL文にメソッドの引数をセットできる
	@Insert("INSERT INTO m_hero("+" hero_id, "+"password, "+"hero_name, "+"name, "
			+"birthday, "+"age, "+"gender, "+"role)"
			+"VALUES("+" #{heroID}, "+"#{password}, "+"#{heroName}, "+"#{name}"
			+"#{birthday}, "+"#{age}, "+"#{gender}, "+"{#role})")
	public boolean insert(Hero hero);

	// カラム名
	// 1件検索用メソッド
	// テーブルのカラム名とクラスのフィールド名が一致しない場合はAS句を用いてカラム名を変更する
	@Select("SELECT hero_id AS heroId,"+" password, "+"hero_name AS heroName, "+"name, "
			+"birthday, "+"age, "+"gender, "+"role "+"FROM m_hero "+"WHERE hero_id = #{heroId}")
	public Hero selectOne(String heroId);

	// 全権検索用メソッド
	@Select("SELECT hero_id AS heroId,"+" password, "+"hero_name AS heroName, "+"name, "
			+"birthday, "+"age, "+"gender, "+"role "+"FROM m_hero")
	public List<Hero> selectMany();

	// 1件更新用メソッド
	@Update("UPDATE m_hero SET"+" password = #{password}, "+"hero_name = #{heroName}, "+"name = #{name}, "
			+"birthday = #{birthday}, "+"age = #{age}, "+"gender = #{gender} "+"WHERE hero_id = #{heroId}")
	public boolean updateOne(Hero hero);

	// 1件削除用メソッド
	@Delete("DELETE FROM m_hero WHERE hero_id = #{heroId}")
	public boolean deleteOne(String heroId);
}
