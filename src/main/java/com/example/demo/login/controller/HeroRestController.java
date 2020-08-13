package com.example.demo.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.login.domain.service.RestService;

@RestController
/* REST用のコントローラークラスには、@RestControllerアノテーションをつける
 * こうすると、各メソッドの戻り値にhtmlファイル以外を指定できるようになる
 */
public class HeroRestController {
	@Autowired
	RestService service;
}
