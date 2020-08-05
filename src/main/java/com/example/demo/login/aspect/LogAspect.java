package com.example.demo.login.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

// AOPのクラスには、@Aspectアノテーションをつける
// 同時にDIコンテナへBean定義をするため、@Componentアノテーションもつける
// この2つのアノテーションをセットでつけると覚えておくと良い
@Aspect
@Component
public class LogAspect {
	/* AOPを実行するメソッドには@Beforeや@Afterアノテーションをつける
	 * これらは、JoinPoint(タイミング)と同じ名称を指定する
	 * そして、アノテーション内のパラメーターには、どのクラスのどのメソッドが呼び出されるかを指定する
	 * excutionの指定方法は
	 * "excution(戻り値 パッケージ名.クラス名.メソッド名(引数)"といった形式で指定する
	 * なお、パッケージ名やクラス名などには正規表現が使える
	 * 今回は、LoginControllerクラスのgetLoginメソッドをPointCut(実行場所)にしている
	 */
	@Before("execution( * com.example.demo.login.controller.LoginController.getLogin(..))")
	public void startLog(JoinPoint jp) {
		System.out.println("メソッド開始:"+ jp.getSignature());
	}

	@After("execution( * com.example.demo.login.controller.LoginController.getLogin(..))")
	public void endLog(JoinPoint jp) {
		System.out.println("メソッド終了:"+ jp.getSignature());
	}
}
