package com.example.demo.login.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.login.domain.model.GroupOrder;
import com.example.demo.login.domain.model.Hero;
import com.example.demo.login.domain.model.SignupForm;
import com.example.demo.login.domain.service.HeroService;

@Controller
public class SignupController {
	@Autowired
	private HeroService heroService;
	// ラジオボタンの実装
	// タイムリーフでラジオボタンの値を動的に変更するためには、Mapを用意する
	// そのMapに入ったキーと値を画面に表示することができる
	private Map<String, String> radioGender;
	// ラジオボタンの初期化メソッド
	// 今回の場合は、initRadioGender()というメソッドの中で、Mapに値を入れている
	// そして、ユーザー登録画面にGETリクエストが来たら、ModelクラスにMapを登録している
	// こうすることで、画面からMapの値を取得できるようになる
	private Map<String, String> initRadioGender() {
		Map<String, String> radio = new LinkedHashMap<>();
		// 既婚・未婚をMapに格納
		radio.put("男性", "true");
		radio.put("女性", "false");

		return radio;
	}

	// ユーザー登録画面のGET用コントローラー
	@GetMapping("/signup")
	// 引数のフォームクラスに@ModelAttributeアノテーションをつけると、自動でModelクラスに登録(add.Attribute)してくれる
	// つまり、model.addAttribute("SignupForm", form)のコードと同じ動きをしている
	// なお、@ModelAttributeをつけた場合、デフォルトではクラス名の最初の文字を小文字に変えた文字列が、キー名に登録される
	// なので、もしキー名を変えたい場合は、@ModelAttribute("キー名")とパラメータを指定する
	public String getSignUp(@ModelAttribute SignupForm form, Model model) {
		// ラジオボタンの初期化メソッド呼び出し
		radioGender = initRadioGender();
		// ラジオボタン用のMapをModelに登録
		model.addAttribute("radioGender", radioGender);
		// signup.htmlに画面遷移
		return "login/signup";
	}

	// ユーザー登録画面のPOST用コントローラー
	@PostMapping("/signup")
	// データバインドの結果を受け取るためには、メソッドの引数にBindingResultクラスを追加する
	// また、このクラスのhasErrors()メソッドで、データバインドに失敗しているかどうかがわかる

	// @Validatedアノテーションのパラメータに実行順序のインタフェースを指定することで、バリデーションがグループ実行される
	// また、実行順序のインタフェースでなく、グループのインタフェース(今回であればValidGroup1など)を直接指定することもできる
	public String postSignUp(@ModelAttribute @Validated(GroupOrder.class) SignupForm form, BindingResult bindingResult, Model model) {
		// 入力チェックに引っかかった場合、新規登録画面へ戻る
		// データバインドに失敗した場合、BindingResultのhasErrorsメソッドでfalseが返ってくる
		// 今回の場合はデータバインドに失敗した場合、ラジオボタン用の変数を初期化したいためユーザー登録画面に戻す
		if(bindingResult.hasErrors()) {
			// GETリクエスト用のメソッドを呼び出して、新規登録画面に戻す
			return getSignUp(form, model);
		}
		// formの中身をコンソールに出して確認する
		System.out.println(form);

		// insert用変換
		// サービスクラスに渡すHeroクラスをnewする
		Hero hero = new Hero();
		// Heroクラスに画面から入力された値をセットしていく
		hero.setHeroId(form.getHeroId());
		hero.setPassword(form.getPassword());
		hero.setHeroName(form.getHeroName());
		hero.setName(form.getName());
		hero.setBirthday(form.getBirthday());
		hero.setAge(form.getAge());
		hero.setGender(form.isGender());
		hero.setRole("ROLE_GENERAL"); // 一般ユーザー

		// ヒーロー登録処理
		// サービスクラスのinsertを呼び出して変換
		boolean result = heroService.insert(hero);

		// ヒーロー登録結果の判定
		if(result == true) {
			System.out.println("insert成功");
		} else {
			System.out.println("insert失敗");
		}

		// login.htmlにリダイレクト
		// リダイレクトする場合は、メソッドの返却値にredirect:遷移先のパスと指定する
		// リダイレクトすると、遷移先のControllerクラスのメソッドが呼ばれる
		// 今回の場合であれば、loginページにGETメソッドでHTTPリクエストが送られる
		// そして、LoginControllerのgetLoginメソッドが呼び出される
		return "redirect:/login";
	}

	/* @ExceptionHandlerアノテーションをつけたメソッドを用意すると、Exceptionごとの例外処理を実装することができる
	 * アノテーションの引数に、例外クラスを指定することで、例外ごとの処理を実行できる、また、メソッドは複数用意することもできる
	 * 今回は共通エラーページに遷移するようにしている
	 * その際に、エラーメッセージをModelクラスに登録している
	 */
	@ExceptionHandler(DataAccessException.class)
	public String dataAccessExceptionHandler(DataAccessException e, Model model) {
		// 例外クラスのメッセージをModelに登録
		model.addAttribute("error", "内部サーバーエラー(DB) : ExceptionHandler");
		// 例外クラスのメッセージをModelに登録
		model.addAttribute("message", "SignupControllerでDataAccessExceptionが発生しました");
		// HTTPのエラーコード(500)をModelに登録
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);
		return "error";
	}

	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception e, Model model) {
		// 例外k流明日のメッセージをModelに登録
		model.addAttribute("error", "内部サーバーエラー : ExceptionHandler");
		// 例外クラスのメッセージをModelに登録
		model.addAttribute("message", "SignupControllerでExceptionが発生しました");
		// HTTPのエラーコード(500)をModelに登録
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);
		return "error";
	}
}
