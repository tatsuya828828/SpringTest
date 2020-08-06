package com.example.demo.login.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
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
	 * 正規表現を使うことで、コントローラークラスの全てのメソッドを指定することも簡潔に表せる
	 */
	@Around("execution(* *..*.*Controller.*(..))")
	public Object startLog(ProceedingJoinPoint jp) throws Throwable {
		System.out.println("メソッド開始:"+ jp.getSignature());
		try {
			// メソッド実行
			Object result = jp.proceed();
			System.out.println("メソッド終了:"+ jp.getSignature());
			return result;
		} catch(Exception e) {
			System.out.println("メソッド異常終了:"+ jp.getSignature());
			e.printStackTrace();
			throw e;
		}
	}

	// HeroDaoクラスのログ出力
	@Around("execution(* *..*.*HeroDao*.*(..))")
	public Object daoLog(ProceedingJoinPoint jp) throws Throwable {
		System.out.println("メソッド開始:"+ jp.getSignature());
		try {
			Object result = jp.proceed();
			System.out.println("メソッド終了:"+ jp.getSignature());
			return result;
		} catch(Exception e) {
			System.out.println("メソッド異常終了:"+ jp.getSignature());
			e.printStackTrace();
			throw e;
		}
	}
}
