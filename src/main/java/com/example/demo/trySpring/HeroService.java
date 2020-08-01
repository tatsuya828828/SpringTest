package com.example.demo.trySpring;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// サービスクラスにも@Serviceアノテーションをつける
@Service
public class HeroService {
	// リポジトリークラスをインスタンス化して使うために@Autowiredを使う
	@Autowired
	private HeroRepository heroRepository;

	public Hero findOne(int id) {
		// リポジトリークラスのfindOneメソッドを呼び出し、1件検索実行
		Map<String, Object> map = heroRepository.findOne(id);
		// リポジトリークラスでMapに格納した情報から値を取得
		// リポジトリークラスでjdbcTemplateのqueryForMapメソッドを使って検索した結果は、Mapに入っている
		// そのためMapのgetメソッドでテーブルのフィールド名を指定すれば、値を取得することができる
		int heroId = (Integer) map.get("hero_id");
		String heroName = (String) map.get("hero_name");
		String name = (String) map.get("name");
		int age = (Integer) map.get("age");

		// Mapから取得した値をHeroクラスにセットして返却する
		Hero hero = new Hero();
		hero.setHeroId(heroId);
		hero.setHeroName(heroName);
		hero.setName(name);
		hero.setAge(age);
		return hero;
	}
}
