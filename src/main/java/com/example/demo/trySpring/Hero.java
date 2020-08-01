package com.example.demo.trySpring;

import lombok.Data;
// 検索結果を入れるHeroクラス
// このように、リポジトリークラスやサービスクラスなどの間で渡すクラスのことを、Springではドメインクラスと呼ぶ
// 他にもモデルクラス、DTO(Data Transfer Object)などの呼び方がある

// @Dataアノテーションをつけると、getterやsetterなどを自動で作成してくれる
// これはSpringではなく、Lombokの機能であり、開発時のちょっとした仕様変更にすぐ対応できるためとても便利である
@Data
public class Hero {
	private int heroId;
	private String heroName;
	private int age;
}
