package com.example.demo.login.domain.service.mybatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.login.domain.model.Hero;
import com.example.demo.login.domain.repository.mybatis.HeroMapper;
import com.example.demo.login.domain.service.RestService;

@Transactional
@Service("RestServiceMybatisImpl")
public class RestServiceMybatisImpl implements RestService {
	@Autowired
	HeroMapper heroMapper;

	@Override
	public boolean insert(Hero hero) {
		// insert実行
		return heroMapper.insert(hero);
	}

	@Override
	public Hero selectOne(String heroId) {
		// select実行
		return heroMapper.selectOne(heroId);
	}

	@Override
	public List<Hero> selectMany(){
		// select実行
		return heroMapper.selectMany();
	}

	@Override
	public boolean updateOne(Hero hero) {
		// update実行
		return heroMapper.updateOne(hero);
	}

	@Override
	public boolean deleteOne(String heroId) {
		// delete実行
		return heroMapper.deleteOne(heroId);
	}
}
