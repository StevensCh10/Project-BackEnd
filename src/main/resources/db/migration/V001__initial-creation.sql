CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `age` int NOT NULL,
  `email` varchar(50) NOT NULL UNIQUE,
  `name` varchar(50) NOT NULL UNIQUE,
  `password` varchar(50) NOT NULL,
  `userName` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `project` (
  `id` bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `description` varchar(120) NOT NULL,
  `name` varchar(50) NOT NULL UNIQUE KEY,
  `situation` bit(1) NOT NULL,
  `fk_user` bigint DEFAULT NULL,
  CONSTRAINT `fk_user` FOREIGN KEY (`fk_user`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;