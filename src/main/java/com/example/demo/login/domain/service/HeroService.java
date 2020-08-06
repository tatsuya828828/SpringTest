package com.example.demo.login.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.login.domain.model.Hero;
import com.example.demo.login.domain.repository.HeroDao;

@Service
public class HeroService {
	@Autowired
	HeroDao dao;

	// サービスクラスのinsertメソッドで、リポジトリークラスのinsertOneメソッドを呼び出している
	// insert用メソッド
	public boolean insert(Hero hero) {
		// insert実行
		int rowNumber = dao.insertOne(hero);
		// 判定用変数
		boolean result = false;
		// 戻り値が0より大きければ、insertが成功したという判定結果をリターンしている
		if(rowNumber>0) {
			// insert成功の場合
			result = true;
		}
		return result;
	}
}
