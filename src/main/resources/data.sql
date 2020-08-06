/* ヒーローテーブルのデータ
 * このファイルに記述しているものはSpringBootを起動するたびにINSERTされる*/
INSERT INTO hero(hero_id, hero_name, name, age) VALUES(1, 'アイアンマン', 'トニー・スターク', 49);
INSERT INTO hero(hero_id, hero_name, name, age) VALUES(2, 'キャプテン・アメリカ', 'スティーブ・ロジャース', 106)

-- ヒーローマスターのデータ(アドミン権限)
INSERT INTO m_hero(hero_id, password, hero_name, name, birthday, age, gender, role)
VALUES('shield', 'shield', 'フューリー大佐', 'ニック・フューリー', '1950-07-04', 70, true, 'ROLE_ADMIN');