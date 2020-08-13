package com.example.demo.login.domain.service.jdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.login.domain.model.Hero;
import com.example.demo.login.domain.repository.HeroDao;
import com.example.demo.login.domain.service.RestService;

@Transactional
@Service
public class RestServiceJdbcImpl implements RestService {
	@Autowired
	@Qualifier("HeroDaoJdbcImpl")
	HeroDao dao;

	// 1件登録用メソッド
	@Override
	public boolean insert(Hero hero) {
		int result = dao.insertOne(hero);
		if(result == 0) {
			return false;
		} else {
			return true;
		}
	}

	// 1件検索用メソッド
	public Hero selectOne(String heroId) {
		return dao.selectOne(heroId);
	}

	// 全権検索用メソッド
	public List<Hero> selectMany() {
		return dao.selectMany();
	}

	// 1件更新用メソッド
	@Override
	public boolean updateOne(Hero hero) {
		int result = dao.updateOne(hero);
		if(result == 0) {
			return false;
		} else {
			return true;
		}
	}

	// 1件削除用メソッド
	@Override
	public boolean delete(String heroId) {
		int result = dao.deleteOne(heroId);
		if(result == 0) {
			return false;
		} else {
			return true;
		}
	}
}
