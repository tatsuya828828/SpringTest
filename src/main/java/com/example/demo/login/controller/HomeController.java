package com.example.demo.login.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.login.domain.model.Hero;
import com.example.demo.login.domain.model.SignupForm;
import com.example.demo.login.domain.service.HeroService;



@Controller
public class HomeController {
	@Autowired
	HeroService heroService;

	// 性別ステータスのラジオボタン用変数
	private Map<String, String> radioGender;
	// ラジオボタンの初期化メソッド
	private Map<String, String> initRadioGender() {
		Map<String, String> radio = new LinkedHashMap<>();
		// 男性、女性をMapに格納
		radio.put("男性", "true");
		radio.put("女性", "false");
		return radio;
	}

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
		model.addAttribute("contents", "login/heroList :: heroList_contents");
		// ユーザー一覧の生成
		List<Hero> heroList = heroService.selectMany();
		// Modelにヒーローリストを登録
		model.addAttribute("heroList", heroList);
		// データ件数を取得
		int count = heroService.count();
		model.addAttribute("heroListCount", count);
		return "login/homeLayout";
	}

	// ヒーロー詳細画面のGET用メソッド
	/* 動的なURLに対応したメソッドを作るためには、@GetMappingや@PostMappingの値に/{変数名}をつける
	 * 例えば、ヒーローIDを受け取る場合は、@GetMapping(/heroDetail/{id})とする
	 * 今回の場合はIDがメールアドレス形式となっているため正規表現を使用する
	 */
	@GetMapping("/heroDetail/{id:.+}")
	// @PathVariableアノテーションをつけると、渡されてきたパス(URL)の値を引数の変数に入れることができる
	public String getHeroDetail(@ModelAttribute SignupForm form, Model model, @PathVariable("id") String heroId) {
		// ヒーローID確認(デバッグ)
		System.out.println("heroId ="+ heroId);
		// コンテンツ部分にユーザー詳細を表示するための文字列を登録
		model.addAttribute("contents", "login/heroDetail :: heroDetail_contents");
		// 性別ステータス用のラジオボタンの初期化
		radioGender = initRadioGender();
		// ラジオボタン用のMapをModelに登録
		model.addAttribute("radioGender", radioGender);
		// ヒーローIDのチェック
		if(heroId != null && heroId.length()>0) {
			// ヒーロー情報を取得
			Hero hero = heroService.selectOne(heroId);
			// Heroクラスをフォームクラスに変換
			form.setHeroId(hero.getHeroId());
			form.setHeroName(hero.getHeroName());
			form.setName(hero.getName());
			form.setBirthday(hero.getBirthday());
			form.setAge(hero.getAge());
			form.setGender(hero.isGender());
			// Modelに登録
			model.addAttribute("signupForm", form);
		}
		return "login/homeLayout";
	}

	// ヒーロー更新用処理
	@PostMapping(value = "/heroDetail", params = "update")
	public String postHeroDetailUpdate(@ModelAttribute SignupForm form, Model model) {
		System.out.println("更新ボタンの処理");
		// Heroインスタンスの生成
		Hero hero = new Hero();
		// フォームクラスをHeroクラスに変換
		hero.setHeroId(form.getHeroId());
		hero.setPassword(form.getPassword());
		hero.setHeroName(form.getHeroName());
		hero.setName(form.getName());
		hero.setBirthday(form.getBirthday());
		hero.setAge(form.getAge());
		hero.setGender(form.isGender());
		// 更新実行
		boolean result = heroService.updateOne(hero);
		if(result == true) {
			model.addAttribute("result", "更新成功");
		} else {
			model.addAttribute("result", "更新失敗");
		}
		// ヒーロー一覧画面を表示
		return getHeroList(model);
	}

	// ヒーロー削除用処理
	@PostMapping(value = "/heroDetail", params = "delete")
	public String postHeroDetailDelete(@ModelAttribute SignupForm form, Model model) {
		System.out.println("削除ボタンの処理");
		// 削除実行
		boolean result = heroService.deleteOne(form.getHeroId());
		if(result == true) {
			model.addAttribute("result", "削除成功");
		} else {
			model.addAttribute("result", "削除失敗");
		}
		// ヒーロー一覧画面を表示
		return getHeroList(model);
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
