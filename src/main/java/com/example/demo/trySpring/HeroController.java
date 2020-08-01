package com.example.demo.trySpring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HeroController {
	@Autowired
	private HeroService heroService;

	@PostMapping("/hello/hero")
	// htmlからname属性を受け取る
	// あとはその値を、HeroServiceクラスのfindOneメソッドに渡せば、検索結果としてHeroのインスタンスが返ってくる
	// Heroクラスの値をmodel.addAttributeで登録しておけば、画面で受け取ることができる
	public String postDbRequest(@RequestParam("hero") String str, Model model) {
		// 入力された文字をString型からint型に変換
		int id = Integer.parseInt(str);
		// 1件検索
		Hero hero = heroService.findOne(id);
		// 検索結果をModelに登録
		model.addAttribute("id", hero.getHeroId());
		model.addAttribute("heroName", hero.getHeroName());
		model.addAttribute("name", hero.getName());
		model.addAttribute("age", hero.getAge());
		// heroResponseDb.htmlに画面遷移

		return "heroResponseDb";
	}
}
