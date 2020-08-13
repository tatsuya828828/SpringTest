-- ヒーローマスターのデータ(アドミン権限)
INSERT INTO m_hero(hero_id, password, hero_name, name, birthday, age, gender, role)
VALUES('shield@gmail.com', '$2a$10$csG4Y0.BiskqwGT6QgvqbuHPvfPcP4WTDx8UizqkWnPlqZgdxi4k2', 'フューリー大佐', 'ニック・フューリー', '1950-07-04', 70, true, 'ROLE_ADMIN');

-- ヒーローマスターのデータ(一般ユーザー)
INSERT INTO m_hero(hero_id, password, hero_name, name, birthday, age, gender, role)
VALUES('ironman@gmail.com', '$2a$10$wBc.NB9D0dQfmU/Vz4Zl4OFvBIjU2ID/VBgqVAFFbcWOVasAAwDFi', 'アイアンマン', 'トニー・スターク', '1970-05-29', 50, true, 'ROLE_GENERAL');