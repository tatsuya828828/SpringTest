package com.example.demo.trySpring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
// Springでは、コントローラークラスに@Controllerアノテーションをつける
// @ControllerアノテーションをつけることでDI（依存性注入)で利用できるようになる
public class HelloController {
	// @GetMappingアノテーションをメソッドにつけると、HTTPリクエストのGETメソッドを処理できるようになる
	// 今回の場合であれば、localhost:8080/helloへのGETリクエストに対する処理をgetHelloメソッドで行うという意味になる
	// なお、GETリクエストの場合、メソッド名の最初にgetをつけるのが慣習となっている
	@GetMapping("/hello")
	public String getHello() {
		// hello.htmlに画面遷移
		// メソッドの戻り値には拡張子なしのhtmlファイル名を指定する
		// htmlファイルは、resources/templatesフォルダからのパスを指定する
		// 今回の場合は、resources/templates/hello.htmlを指定している
		// これだけで、lovalhost:8080/helloにGETリクエストが来るとhello.htmlを表示してくれる
		return "hello";
	}
}
