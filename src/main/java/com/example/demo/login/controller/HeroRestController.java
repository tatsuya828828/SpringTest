package com.example.demo.login.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
}
