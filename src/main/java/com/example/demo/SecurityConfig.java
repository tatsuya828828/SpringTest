package com.example.demo;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//セキュリティ設定用クラス
//セキュリティ設定用クラスには、@EnableWebSecurityをつける
//また、WebSecurityConfigureAdapterクラスを継承する
//このクラスの各種メソッドをオーバーライドすることで、セキュリティの設定を行なっていくことができる
//そして、セキュリティ用にBean定義を行うこともあるため、@Configurationアノテーションをつける
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	// データソース
	@Autowired
	private DataSource dataSource;

	// ヒーローIDとパスワードを取得するSQL
	private static final String HERO_SQL = "SELECT"+" hero_id, "+"password, "+"true "+"FROM "+"m_hero "+"WHERE "+"hero_id = ?";
	// ヒーローのロールを取得するSQL文
	private static final String ROLE_SQL = "SELECT"+" hero_id, "+"role "+"FROM "+"m_hero "+"WHERE "+"hero_id = ?";

	@Override
	public void configure(WebSecurity web) throws Exception {
		// 静的リソースを除外
		// 静的リソースへのアクセスには、セキュリティを適用しない
		web.ignoring().antMatchers("/webjars/**", "/css/**");
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 直リンクの禁止
		// ログイン不要ページの設定
		/* webjarsやcssなどの静的リソースには、誰でもアクセスできるようにしておく
		 * そのため設定することで、セキュリティ設定を適用しないようにする
		 * なお、*(アスタリスク)を2つつけると、正規表現でいずれかのファイルとなる
		 * 今回の場合で言えば/webjars配下と/css配下のファイルはセキュリティの対象外となる
		 */
		http.authorizeRequests().antMatchers("/webjars/**").permitAll() // webjarsへアクセス許可
		.antMatchers("/css/**").permitAll() // cssへアクセス許可
		.antMatchers("/login").permitAll() // ログインページは直リンクOK
		.antMatchers("/signup").permitAll() // ヒーロー登録画面は直リンクOK
		.anyRequest().authenticated(); // それ以外は直リンク禁止
		// ログイン処理
		http.formLogin().loginProcessingUrl("/login") // ログイン処理のパス
						.loginPage("/login") // ログインページの指定
						.failureUrl("/login") // ログイン失敗時の遷移先
						.usernameParameter("heroId") // ログインページのヒーローID
						.passwordParameter("password") // ログインページのパスワード
						.defaultSuccessUrl("/home", true); // ログイン成功後の遷移先
		// CSRF対策を無効に設定
		http.csrf().disable();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// ヒーローデータの取得
		// ログイン処理時のヒーロー情報を、DBから取得する
		auth.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery(HERO_SQL).authoritiesByUsernameQuery(ROLE_SQL);
	}
}
