-- ヒーローマスターのデータ(アドミン権限)
INSERT INTO m_hero(hero_id, password, hero_name, name, birthday, age, gender, role)
VALUES('shield@gmail.com', 'shield', 'フューリー大佐', 'ニック・フューリー', '1950-07-04', 70, true, 'ROLE_ADMIN');

-- ヒーローマスターのデータ(一般ユーザー)
INSERT INTO m_hero(hero_id, password, hero_name, name, birthday, age, gender, role)
VALUES('ironman@gmail.com', 'ironman', 'アイアンマン', 'トニー・スターク', '1970-05-29', 50, true, 'ROLE_GENERAL');