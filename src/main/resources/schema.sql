/* ヒーローマスターテーブル  */
CREATE TABLE IF NOT EXISTS m_hero(
	hero_id VARCHAR(50) PRIMARY KEY, password VARCHAR(100), hero_name VARCHAR(50), name VARCHAR(50),
	birthday DATE, age INT, gender BOOLEAN, role VARCHAR(50));