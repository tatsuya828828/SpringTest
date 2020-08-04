package com.example.demo.login.domain.model;

import javax.validation.GroupSequence;

// グループの実行順序用インタフェース

// バリデーションをグループ実行するためには、実行順序を設定するインタフェースに@GroupSequenceアノテーションをつける
// そしてアノテーションのパラメータに、各グループの.classを指定する
// なお、指定した順番にバリデーションが実行されていく
@GroupSequence({ValidGroup1.class, ValidGroup2.class, ValidGroup3.class})
public interface GroupOrder {

}
