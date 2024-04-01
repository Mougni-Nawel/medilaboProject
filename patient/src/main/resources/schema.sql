-- // create table
DROP TABLE IF EXISTS `patient`;
CREATE TABLE IF NOT EXISTS `patient` (
                                         `id` int NOT NULL AUTO_INCREMENT,
                                         `address` varchar(255) DEFAULT NULL,
    `birthdate` datetime(6) DEFAULT NULL,
    `firstname` varchar(255) DEFAULT NULL,
    `gender` enum('F','M') DEFAULT NULL,
    `lastname` varchar(255) DEFAULT NULL,
    `phone` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;