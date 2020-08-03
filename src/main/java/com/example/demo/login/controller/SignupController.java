package com.example.demo.login.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.login.domain.model.SignupForm;

@Controller
public class SignupController {
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
	public String postSignUp(@ModelAttribute @Validated SignupForm form, BindingResult bindingResult, Model model) {
		// 入力チェックに引っかかった場合、新規登録画面へ戻る
		// データバインドに失敗した場合、BindingResultのhasErrorsメソッドでfalseが返ってくる
		// 今回の場合はデータバインドに失敗した場合、ラジオボタン用の変数を初期化したいためユーザー登録画面に戻す
		if(bindingResult.hasErrors()) {
			// GETリクエスト用のメソッドを呼び出して、新規登録画面に戻す
			return getSignUp(form, model);
		}
		// formの中身をコンソールに出して確認する
		System.out.println(form);
		// login.htmlにリダイレクト
		// リダイレクトする場合は、メソッドの返却値にredirect:遷移先のパスと指定する
		// リダイレクトすると、遷移先のControllerクラスのメソッドが呼ばれる
		// 今回の場合であれば、loginページにGETメソッドでHTTPリクエストが送られる
		// そして、LoginControllerのgetLoginメソッドが呼び出される
		return "redirect:/login";
	}
}
