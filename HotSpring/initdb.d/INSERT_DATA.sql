CREATE TABLE `M_HOT_SPRING` (
  `HOT_SPRING_ID` int NOT NULL AUTO_INCREMENT,
  `HOT_SPRING_NAME` varchar(50) NOT NULL,
  `LOCATION` varchar(50) DEFAULT NULL,
  `URL` varchar(300) DEFAULT NULL,
  `PHOTO_IMAGE` mediumblob,
  PRIMARY KEY (`HOT_SPRING_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `M_RANK` (
  `RANK_ID` int NOT NULL,
  `RANK_NAME` varchar(50) NOT NULL,
  PRIMARY KEY (`RANK_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `T_EVALUATION` (
  `EVALATION_ID` int NOT NULL AUTO_INCREMENT,
  `HOT_SPRING_ID` int NOT NULL,
  `VISIT_DATE` date NOT NULL,
  `OVERALL_RANK` varchar(1) NOT NULL,
  `CAPACIOUSNESS_RANK` varchar(1) NOT NULL,
  `VARIETY_RANK` varchar(1) NOT NULL,
  `PRICE_RANK` varchar(1) NOT NULL,
  PRIMARY KEY (`EVALATION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT 
INTO hotspring_db.`M_RANK`(`RANK_ID`, `RANK_NAME`) 
VALUES (1, '1')
, (2, '2')
, (3, '3')
, (4, '4')
, (5, '5');
