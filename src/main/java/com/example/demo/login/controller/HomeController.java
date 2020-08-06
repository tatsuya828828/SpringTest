package com.example.demo.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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

	@PostMapping("/logout")
	public String postLogout() {
		// ログイン画面へリダイレクト
		return "redirect:/login";
	}
}
