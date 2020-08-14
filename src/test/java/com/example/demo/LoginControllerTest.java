package com.example.demo;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
/* Springのモック
 * 画面の表示内容の確認をするためには、モックをつかう
 * モックとは、クラスの動作をシュミレートするためのオブジェクト
 * Springのモックを使うためには、@AutoConfigureMockMvcアノテーションをクラスにつける
 * その後、MockMvcクラスを@Autowiredすれば、Springのモックを使うことができる
 */
@AutoConfigureMockMvc
public class LoginControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@Test
	public void ログイン画面表示() throws Exception {
		// 画面表示内容の確認
		mockMvc.perform(get("/login")).andExpect(status().isOk())
									.andExpect(content().string(containsString("ヒーローID")));
	}
}
