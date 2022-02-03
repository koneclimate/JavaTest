CREATE TABLE `tb_member` (
   `REG_REF_ID` varchar(12) NOT NULL,
   `MEMBER_TYPE` varchar(20) NOT NULL,
   `USERNAME` varchar(40) NOT NULL,
   `PASSWORD` varchar(40) NOT NULL,
   `NAME` varchar(90) DEFAULT NULL,
   `ADDRESS` varchar(200) DEFAULT NULL,
   `PHONE` varchar(25) DEFAULT NULL,
   `EMAIL` varchar(80) DEFAULT NULL,
   `SALARY` int DEFAULT NULL,
   `CREATE_DATE` varchar(8) DEFAULT NULL,
   `CREATE_TIME` varchar(6) DEFAULT NULL,
   `STATUS` varchar(1) NOT NULL,
   PRIMARY KEY (`REG_REF_ID`,`USERNAME`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
 
 CREATE TABLE `roles` (
   `id` bigint NOT NULL AUTO_INCREMENT,
   `name` varchar(60) DEFAULT NULL,
   PRIMARY KEY (`id`),
   UNIQUE KEY `UK_nb4h0p6txrmfc0xbrd1kglp9t` (`name`)
 ) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
 
 CREATE TABLE `user_roles` (
   `user_id` bigint NOT NULL,
   `role_id` bigint NOT NULL,
   PRIMARY KEY (`user_id`,`role_id`),
   KEY `FKh8ciramu9cc9q3qcqiv4ue8a6` (`role_id`),
   CONSTRAINT `FKh8ciramu9cc9q3qcqiv4ue8a6` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
   CONSTRAINT `FKhfh9dx7w3ubf1co1vdev94g3f` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
 
 
 CREATE TABLE `users` (
   `id` bigint NOT NULL AUTO_INCREMENT,
   `email` varchar(50) DEFAULT NULL,
   `name` varchar(50) DEFAULT NULL,
   `password` varchar(100) DEFAULT NULL,
   `username` varchar(50) DEFAULT NULL,
   PRIMARY KEY (`id`),
   UNIQUE KEY `UKr43af9ap4edm43mmtq01oddj6` (`username`),
   UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`)
 ) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
 
INSERT INTO `javatest`.`roles` (`id`, `name`) VALUES ('1', 'ROLE_USER');
INSERT INTO `javatest`.`roles` (`id`, `name`) VALUES ('2', 'ROLE_PM');
INSERT INTO `javatest`.`roles` (`id`, `name`) VALUES ('3', 'ROLE_ADMIN');

INSERT INTO `javatest`.`users` (`id`, `email`, `name`, `password`, `username`) VALUES ('1', 'admin@admin.com', 'admin', '$2a$10$p6jm.8uqlni.sATP6zNXSOAdFlw9gHwIBrB2xfTj/TNUuTAFVqR4S', 'admin');
INSERT INTO `javatest`.`users` (`id`, `email`, `name`, `password`, `username`) VALUES ('2', 'test@test.com', 'test', '$2a$10$CgsJ6ff61qoQZHN8AthxseiKaR.JFjwfgGbOnglhiZfeZLwuJ9Pta', 'test');
INSERT INTO `javatest`.`users` (`id`, `email`, `name`, `password`, `username`) VALUES ('3', 'test2@test.com', 'test2', '$2a$10$Y4Jbsj/cO5g8LwvqZ2j7nuaRiGtYslZx0U609utxU3ijTI/t31t9W', 'test2');

INSERT INTO `javatest`.`user_roles` (`user_id`, `role_id`) VALUES ('1', '1');
INSERT INTO `javatest`.`user_roles` (`user_id`, `role_id`) VALUES ('1', '2');
INSERT INTO `javatest`.`user_roles` (`user_id`, `role_id`) VALUES ('2', '1');
INSERT INTO `javatest`.`user_roles` (`user_id`, `role_id`) VALUES ('3', '3');
