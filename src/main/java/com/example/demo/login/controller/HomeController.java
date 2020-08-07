package com.example.demo.login.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.login.domain.model.Hero;
import com.example.demo.login.domain.service.HeroService;



@Controller
public class HomeController {
	@Autowired
	HeroService heroService;

	// ホーム画面のGET用メソッド
	// これは、/homeにGETリクエストがきたときに、Modelクラスの"contents"というキーに"login/home :: home_contents"という値をセットしている
	// この値が、homeLayout.htmlのth:include属性に代入される
	@GetMapping("/home")
	public String getHome(Model model) {
		// コンテンツ部分にホーム画面を表示するための文字列を登録(th:include属性に渡す値)
		model.addAttribute("contents", "login/home :: home_contents");
		return "login/homeLayout";
	}

	// ヒーロー一覧画面のGET用メソッド
	@GetMapping("/heroList")
	public String getHeroList(Model model) {
		// コンテンツ部分にヒーロー一覧を表示する目の文字列を登録
		model.addAttribute("contents", "login/hero/List :: heroList_contents");
		// ユーザー一覧の生成
		List<Hero> heroList = heroService.selectMany();
		// Modelにヒーローリストを登録
		model.addAttribute("heroList", heroList);
		// データ件数を取得
		int count = heroService.count();
		model.addAttribute("heroListCount", count);
		return "login/homeLayout";
	}

	// ログアウト用メソッド
	@PostMapping("/logout")
	public String postLogout() {
		// ログイン画面へリダイレクト
		return "redirect:/login";
	}

	// ユーザー一覧のCSV出力用メソッド
	@GetMapping("/heroList/csv")
	public String getHeroListCsv(Model model) {
		// 現段階では、何もせずにヒーロー一覧画面に戻るだけ
		return getHeroList(model);
	}
}
