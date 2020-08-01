package com.example.demo.trySpring;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

	// @PostMappingアノテーションをつけることで、POSTメソッドで送られてきた場合の処理ができるようになる
	@PostMapping("/hello")
	// メソッドの引数に@RequestParamアノテーションをつけることで、画面からの入力内容を受け取ることができる
	// アノテーションの引数には、htmlのname属性の値を指定する
	public String postRequets(@RequestParam("text1") String str1, @RequestParam("text2") String str2, Model model) {
		// 画面から受け取った文字列をModelに登録
		// model.addAttributeにキーと値をセットしておく、そうすることで、画面から指定したキーの値を受け取ることができる
		model.addAttribute("name", str1);
		model.addAttribute("like", str2);
		// helloResponse.htmlに画面遷移
		return "helloResponse";
	}
}
