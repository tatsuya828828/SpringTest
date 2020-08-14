package com.example.demo;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.login.domain.repository.HeroDao;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class HeroDaoTest {
	@Autowired
	@Qualifier("HeroDaoJdbcImpl")
	HeroDao dao;

	// カウントメソッドのテスト1
	@Test
	public void countTest1() {
		// カウントメソッドの結果が２件であることをテスト
		assertEquals(dao.count(), 2);
	}

	// カウントメソッドのテスト2
	@Test
	/* @Sqlアノテーションを使用すると、そのSQLを実行した後の状態でテストされる
	 * "!ただし、@Sqlに記載されているSQLはそのメソッド内だけで有効である
	 * 今回の場合で言うと、countTest2のメソッド以外では、2件のデータしか返ってこないということ
	 */
	@Sql("/testdata.sql")
	public void countTest2() {
		// カウントメソッドの結果が3件であることをテスト
		assertEquals(dao.count(), 3);
	}
}
