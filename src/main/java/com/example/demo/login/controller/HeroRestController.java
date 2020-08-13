package com.example.demo.login.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.login.domain.model.Hero;
import com.example.demo.login.domain.service.RestService;

@RestController
/* REST用のコントローラークラスには、@RestControllerアノテーションをつける
 * こうすると、各メソッドの戻り値にhtmlファイル以外を指定できるようになる
 */
public class HeroRestController {
	@Autowired
	RestService service;

	// ヒーロー全権取得
	@GetMapping("/rest/get")
	public List<Hero> getHeroMany(){
		// ヒーロー全権取得
		return service.selectMany();
	}

	// ヒーロー1件取得
	@GetMapping("/rest/get/{id:.+}")
	public Hero getHeroOne(@PathVariable("id") String heroId) {
		// ユーザー1件取得
		return service.selectOne(heroId);
	}

	// ヒーローを1件登録
	@PostMapping("/rest/insert")
	// ＠RequestBodyアノテーションを使うと、HTTPリクエストのボディ部分を引数にマッピングしてくれる
	// こうすることで、POSTメソッドでもヒーロー情報を受け取ることができる
	public String postHeroOne(@RequestBody Hero hero) {
		boolean result = service.insert(hero);
		String str = "";
		if(result == true) {
			str = "{\"result\":\"ok\"}";
		} else {
			str = "{\"result\":\"error\"}";
		}
		// 結果の文字列をリターン
		return str;
	}

	// ヒーローを1件更新
	@PutMapping("/rest/update")
	public String putHeroOne(@RequestBody Hero hero) {
		 boolean resutl = service.updateOne(hero);
		 String str = "";
		 if(resutl == true) {
			 str = "{\"result\":\"ok\"}";
		 } else {
			 str = "{\"result\":\"error\"}";
		 }
		 // 結果用の文字列をリターン
		 return str;
	}

	// ヒーローを1件削除
	@DeleteMapping("/rest/delete/{id:.+}")
	public String deleteHeroOne(@PathVariable("id") String heroId) {
		// ヒーロー1件削除
		boolean result = service.deleteOne(heroId);
		String str = "";
		if(result == true) {
			str = "{\"result\":\"ok\"}";
		} else {
			str = "{\"result\":\"error\"}";
		}
		// 結果用の文字列をリターン
		return str;
	}
}
