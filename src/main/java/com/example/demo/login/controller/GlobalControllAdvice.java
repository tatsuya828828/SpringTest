package com.example.demo.login.controller;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/* @ControllerAdviceクラスを用意しておくと、アプリケーション全体で発生した例外処理を実装できる
 * クラスの中身は、@ExceptionHandlerをつけたメソッドを用意するだけでよい
 */
@ControllerAdvice
@Component
public class GlobalControllAdvice {
	@ExceptionHandler(DataAccessException.class)
	public String dataAccessExceptionHandler(DataAccessException e, Model model) {
		// 例外クラスのメッセージをModel登録
		model.addAttribute("error", "内部サーバーエラー(DB) : GrobalControllAdvice");
		// 例外クラスのメッセージをModelに登録
		model.addAttribute("message", "dataAccessExceptionが発生しました");
		// HTTPのエラーコード(500)をModelに登録
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);
		return "error";
	}

	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception e, Model model) {
		// 例外のクラスのメッセージをModelに登録
		model.addAttribute("error", "内部サーバーエラー : GlobalControllAdvice");
		// 例外クラスのメッセージをModelに登録
		model.addAttribute("message", "Exceptionが発生しました");
		// HTTPのエラーコード(500)をModelに登録
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);
		return "error";
	}
}
