package com.example.demo.login.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
	public String getSignUp(Model model) {
		// ラジオボタンの初期化メソッド呼び出し
		radioGender = initRadioGender();
		// ラジオボタン用のMapをModelに登録
		model.addAttribute("radioGender", radioGender);
		// signup.htmlに画面遷移
		return "login/signup";
	}

	// ユーザー登録画面のPOST用コントローラー
	@PostMapping("/signup")
	public String postSignUp(Model model) {
		// login.htmlにリダイレクト
		// リダイレクトする場合は、メソッドの返却値にredirect:遷移先のパスと指定する
		// リダイレクトすると、遷移先のControllerクラスのメソッドが呼ばれる
		// 今回の場合であれば、loginページにGETメソッドでHTTPリクエストが送られる
		// そして、LoginControllerのgetLoginメソッドが呼び出される
		return "redirect:/login";
	}
}
