-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: May 12, 2020 at 08:32 PM
-- Server version: 5.7.26
-- PHP Version: 7.2.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `job_matching`
--

-- --------------------------------------------------------

--
-- Table structure for table `credentials`
--

DROP TABLE IF EXISTS `credentials`;
CREATE TABLE IF NOT EXISTS `credentials` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `password` varchar(50) DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `credentials`
--

INSERT INTO `credentials` (`user_id`, `password`, `username`) VALUES
(1, 'qwerty', 'test@gmail.com'),
(2, 'gemi123JOB', 'gemitest@gmail.com'),
(3, 'qwertt', 'test1@gmail.com'),
(4, '123', 'abcxyz@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `jobs`
--

DROP TABLE IF EXISTS `jobs`;
CREATE TABLE IF NOT EXISTS `jobs` (
  `job_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(500) DEFAULT NULL,
  `experience` varchar(30) DEFAULT NULL,
  `location` varchar(30) DEFAULT NULL,
  `salary` double DEFAULT NULL,
  `title` varchar(30) DEFAULT NULL,
  `is_full_time` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`job_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `jobs`
--

INSERT INTO `jobs` (`job_id`, `description`, `experience`, `location`, `salary`, `title`, `is_full_time`) VALUES
(1, 'We are looking for a Front-End Web Developer who is motivated to combine the art of design with the art of programming. \r\nResponsibilities will include translation of the UI/UX design wireframes to actual code that will produce visual elements of \r\nthe application. You will work with the UI/UX designer and bridge the gap between graphical design and technical implementation and \r\ntaking an active role on both sides and defining how the application looks as well as how it works.', '2_under_5', 'Austin TX', 150000, 'Front-end Web Developer', 0),
(2, 'We are looking for an analytical, results-driven Back-end Developer who will work with team members to troubleshoot and improve current back-end applications and processes. The Back-end Developer will use his or her understanding of programming languages and tools to analyze current codes and industry developments, formulate more efficient processes, solve problems, and create a more seamless experience for users. You should have excellent communication, computer, and project management skills.', '3_under_5', 'Washington, DC', 150000, 'Back-end Web Developer', 1),
(3, 'We?re looking for a Full Stack developer who will take a key role on our team. Our Full Stack developer must have knowledge in all stages of software development.  You?ll be working alongside other engineers and developers collaborating on the various layers of the infrastructure for our platform', '5_under_8', 'Fairfield, IO', 260000, 'Full-stack Web Developer', 1),
(4, 'We are looking for a skilled Software Engineer who along with our excellent software development team will be responsible for working on projects that are currently being developed on by our company. Duties will include but are not limited to developing and directing software system validation and testing methods, as well as directing our software programming initiatives. You will also be working closely with clients and cross-functional departments to communicate project statuses and proposals.', '1_under_2', 'Seattle, WA', 160000, 'Software Engineer', 1);

-- --------------------------------------------------------

--
-- Table structure for table `job_skill_mappings`
--

DROP TABLE IF EXISTS `job_skill_mappings`;
CREATE TABLE IF NOT EXISTS `job_skill_mappings` (
  `job_id` bigint(20) NOT NULL,
  `skill_id` bigint(20) NOT NULL,
  PRIMARY KEY (`job_id`,`skill_id`),
  KEY `FK4m7nswu5w3q0r4gsr6q10em1n` (`skill_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `job_skill_mappings`
--

INSERT INTO `job_skill_mappings` (`job_id`, `skill_id`) VALUES
(1, 4),
(3, 4),
(1, 8),
(3, 9),
(1, 13),
(2, 13),
(3, 14),
(4, 20),
(4, 23),
(4, 29),
(4, 38),
(1, 43),
(1, 48),
(3, 48),
(1, 51),
(2, 53),
(3, 54),
(4, 54),
(2, 59),
(3, 59),
(2, 64),
(3, 64),
(4, 69),
(4, 73);

-- --------------------------------------------------------

--
-- Table structure for table `job_technology_mappings`
--

DROP TABLE IF EXISTS `job_technology_mappings`;
CREATE TABLE IF NOT EXISTS `job_technology_mappings` (
  `job_id` bigint(20) NOT NULL,
  `tech_id` bigint(20) NOT NULL,
  PRIMARY KEY (`job_id`,`tech_id`),
  KEY `FKtog1hjfgel2cu8d04jmeobwx1` (`tech_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `job_technology_mappings`
--

INSERT INTO `job_technology_mappings` (`job_id`, `tech_id`) VALUES
(1, 3),
(2, 4),
(3, 4),
(1, 7),
(3, 7),
(1, 13),
(1, 19),
(3, 19),
(2, 24),
(3, 24),
(4, 35),
(4, 40),
(4, 44),
(1, 47),
(4, 53),
(4, 58),
(4, 69),
(2, 78),
(3, 78),
(4, 83);

-- --------------------------------------------------------

--
-- Table structure for table `profiles`
--

DROP TABLE IF EXISTS `profiles`;
CREATE TABLE IF NOT EXISTS `profiles` (
  `profile_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `experience` varchar(500) DEFAULT NULL,
  `job_expected` varchar(500) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`profile_id`),
  KEY `FKbblggxqwpx9jkvoc2og3ii9h9` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `profiles`
--

INSERT INTO `profiles` (`profile_id`, `experience`, `job_expected`, `user_id`) VALUES
(2, '1-under-3', 'Front-end Web Developer', 4),
(3, '1-under-3', 'Front-end Web Developer', 1);

-- --------------------------------------------------------

--
-- Table structure for table `profile_skill_mappings`
--

DROP TABLE IF EXISTS `profile_skill_mappings`;
CREATE TABLE IF NOT EXISTS `profile_skill_mappings` (
  `profile_id` bigint(20) NOT NULL,
  `skill_id` bigint(20) NOT NULL,
  PRIMARY KEY (`profile_id`,`skill_id`),
  KEY `FKgyk9xy8tp42y7oqvpo3394psg` (`skill_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `profile_skill_mappings`
--

INSERT INTO `profile_skill_mappings` (`profile_id`, `skill_id`) VALUES
(3, 6),
(2, 7),
(3, 13),
(3, 26);

-- --------------------------------------------------------

--
-- Table structure for table `profile_technology_mappings`
--

DROP TABLE IF EXISTS `profile_technology_mappings`;
CREATE TABLE IF NOT EXISTS `profile_technology_mappings` (
  `profile_id` bigint(20) NOT NULL,
  `tech_id` bigint(20) NOT NULL,
  PRIMARY KEY (`profile_id`,`tech_id`),
  KEY `FKc0yw1fmcr522h06x5qpc7kdia` (`tech_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `profile_technology_mappings`
--

INSERT INTO `profile_technology_mappings` (`profile_id`, `tech_id`) VALUES
(2, 8),
(3, 23),
(3, 34);

-- --------------------------------------------------------

--
-- Table structure for table `skills`
--

DROP TABLE IF EXISTS `skills`;
CREATE TABLE IF NOT EXISTS `skills` (
  `skill_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  `level` varchar(20) DEFAULT NULL,
  `name_level` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`skill_id`)
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `skills`
--

INSERT INTO `skills` (`skill_id`, `name`, `level`, `name_level`) VALUES
(1, 'HTML', '1', 'HTML_1'),
(2, 'HTML', '2', 'HTML_2'),
(3, 'HTML', '3', 'HTML_3'),
(4, 'HTML', '4', 'HTML_4'),
(5, 'HTML', '5', 'HTML_5'),
(6, 'CSS', '1', 'CSS_1'),
(7, 'CSS', '2', 'CSS_2'),
(8, 'CSS', '3', 'CSS_3'),
(9, 'CSS', '4', 'CSS_4'),
(10, 'CSS', '5', 'CSS_5'),
(11, 'Javascript', '1', 'Javascript_1'),
(12, 'Javascript', '2', 'Javascript_2'),
(13, 'Javascript', '3', 'Javascript_3'),
(14, 'Javascript', '4', 'Javascript_4'),
(15, 'Javascript', '5', 'Javascript_5'),
(16, 'Machine learning', '1', 'Machine learning_1'),
(17, 'Machine learning', '2', 'Machine learning_2'),
(18, 'Machine learning', '3', 'Machine learning_3'),
(19, 'Machine learning', '4', 'Machine learning_4'),
(20, 'Machine learning', '5', 'Machine learning_5'),
(21, 'Deep learning', '1', 'Deep learning_1'),
(22, 'Deep learning', '2', 'Deep learning_2'),
(23, 'Deep learning', '3', 'Deep learning_3'),
(24, 'Deep learning', '4', 'Deep learning_4'),
(25, 'Deep learning', '5', 'Deep learning_5'),
(26, 'NLP', '1', 'NLP_1'),
(27, 'NLP', '2', 'NLP_2'),
(28, 'NLP', '3', 'NLP_3'),
(29, 'NLP', '4', 'NLP_4'),
(30, 'NLP', '5', 'NLP_5'),
(31, 'Computer Vision', '1', 'Computer Vision_1'),
(32, 'Computer Vision', '2', 'Computer Vision_2'),
(33, 'Computer Vision', '3', 'Computer Vision_3'),
(34, 'Computer Vision', '4', 'Computer Vision_4'),
(35, 'Computer Vision', '5', 'Computer Vision_5'),
(36, 'Data visualization', '1', 'Data visualization_1'),
(37, 'Data visualization', '2', 'Data visualization_2'),
(38, 'Data visualization', '3', 'Data visualization_3'),
(39, 'Data visualization', '4', 'Data visualization_4'),
(40, 'Data visualization', '5', 'Data visualization_5'),
(41, 'jQuery', '1', 'jQuery_1'),
(42, 'jQuery', '2', 'jQuery_2'),
(43, 'jQuery', '3', 'jQuery_3'),
(44, 'jQuery', '4', 'jQuery_4'),
(45, 'jQuery', '5', 'jQuery_5'),
(46, 'Ajax & JSON', '1', 'Ajax & JSON_1'),
(47, 'Ajax & JSON', '2', 'Ajax & JSON_2'),
(48, 'Ajax & JSON', '3', 'Ajax & JSON_3'),
(49, 'Ajax & JSON', '4', 'Ajax & JSON_4'),
(50, 'Ajax & JSON', '5', 'Ajax & JSON_5'),
(51, 'MongoDB', '1', 'MongoDB_1'),
(52, 'MongoDB', '2', 'MongoDB_2'),
(53, 'MongoDB', '3', 'MongoDB_3'),
(54, 'MongoDB', '4', 'MongoDB_4'),
(55, 'MongoDB', '5', 'MongoDB_5'),
(56, 'MySQL', '1', 'MySQL_1'),
(57, 'MySQL', '2', 'MySQL_2'),
(58, 'MySQL', '3', 'MySQL_3'),
(59, 'MySQL', '4', 'MySQL_4'),
(60, 'MySQL', '5', 'MySQL_5'),
(61, 'Java', '1', 'Java_1'),
(62, 'Java', '2', 'Java_2'),
(63, 'Java', '3', 'Java_3'),
(64, 'Java', '4', 'Java_4'),
(65, 'Java', '5', 'Java_5'),
(66, 'Python', '1', 'Python_1'),
(67, 'Python', '2', 'Python_2'),
(68, 'Python', '3', 'Python_3'),
(69, 'Python', '4', 'Python_4'),
(70, 'Python', '5', 'Python_5'),
(71, 'R', '1', 'R_1'),
(72, 'R', '2', 'R_2'),
(73, 'R', '3', 'R_3'),
(74, 'R', '4', 'R_4'),
(75, 'R', '5', 'R_5');

-- --------------------------------------------------------

--
-- Table structure for table `technologies`
--

DROP TABLE IF EXISTS `technologies`;
CREATE TABLE IF NOT EXISTS `technologies` (
  `tech_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  `level` varchar(20) DEFAULT NULL,
  `name_level` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`tech_id`)
) ENGINE=InnoDB AUTO_INCREMENT=86 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `technologies`
--

INSERT INTO `technologies` (`tech_id`, `name`, `level`, `name_level`) VALUES
(1, 'NodeJS', '1', 'NodeJS_1'),
(2, 'NodeJS', '2', 'NodeJS_2'),
(3, 'NodeJS', '3', 'NodeJS_3'),
(4, 'NodeJS', '4', 'NodeJS_4'),
(5, 'NodeJS', '5', 'NodeJS_5'),
(6, 'VueJS', '1', 'VueJS_1'),
(7, 'VueJS', '2', 'VueJS_2'),
(8, 'VueJS', '3', 'VueJS_3'),
(9, 'VueJS', '4', 'VueJS_4'),
(10, 'VueJS', '5', 'VueJS_5'),
(11, 'React', '1', 'React_1'),
(12, 'React', '2', 'React_2'),
(13, 'React', '3', 'React_3'),
(14, 'React', '4', 'React_4'),
(15, 'React', '5', 'React_5'),
(16, 'AngularJS', '1', 'AngularJS_1'),
(17, 'AngularJS', '2', 'AngularJS_2'),
(18, 'AngularJS', '3', 'AngularJS_3'),
(19, 'AngularJS', '4', 'AngularJS_4'),
(20, 'AngularJS', '5', 'AngularJS_5'),
(21, 'Spring Boot', '1', 'Spring Boot_1'),
(22, 'Spring Boot', '2', 'Spring Boot_2'),
(23, 'Spring Boot', '3', 'Spring Boot_3'),
(24, 'Spring Boot', '4', 'Spring Boot_4'),
(25, 'Spring Boot', '5', 'Spring Boot_5'),
(26, 'Django', '1', 'Django_1'),
(27, 'Django', '2', 'Django_2'),
(28, 'Django', '3', 'Django_3'),
(29, 'Django', '4', 'Django_4'),
(30, 'Django', '5', 'Django_5'),
(31, 'Numpy', '1', 'Numpy_1'),
(32, 'Numpy', '2', 'Numpy_2'),
(33, 'Numpy', '3', 'Numpy_3'),
(34, 'Numpy', '4', 'Numpy_4'),
(35, 'Numpy', '5', 'Numpy_5'),
(36, 'Pandas', '1', 'Pandas_1'),
(37, 'Pandas', '2', 'Pandas_2'),
(38, 'Pandas', '3', 'Pandas_3'),
(39, 'Pandas', '4', 'Pandas_4'),
(40, 'Pandas', '5', 'Pandas_5'),
(41, 'Tensorflow', '1', 'Tensorflow_1'),
(42, 'Tensorflow', '2', 'Tensorflow_2'),
(43, 'Tensorflow', '3', 'Tensorflow_3'),
(44, 'Tensorflow', '4', 'Tensorflow_4'),
(45, 'Tensorflow', '5', 'Tensorflow_5'),
(46, 'ExpressJS', '1', 'ExpressJS_1'),
(47, 'ExpressJS', '2', 'ExpressJS_2'),
(48, 'ExpressJS', '3', 'ExpressJS_3'),
(49, 'ExpressJS', '4', 'ExpressJS_4'),
(50, 'ExpressJS', '5', 'ExpressJS_5'),
(51, 'PyTorch', '1', 'PyTorch_1'),
(52, 'PyTorch', '2', 'PyTorch_2'),
(53, 'PyTorch', '3', 'PyTorch_3'),
(54, 'PyTorch', '4', 'PyTorch_4'),
(55, 'PyTorch', '5', 'PyTorch_5'),
(56, 'CNTK', '1', 'CNTK_1'),
(57, 'CNTK', '2', 'CNTK_2'),
(58, 'CNTK', '3', 'CNTK_3'),
(59, 'CNTK', '4', 'CNTK_4'),
(60, 'CNTK', '5', 'CNTK_5'),
(61, 'TFLearn', '1', 'TFLearn_1'),
(62, 'TFLearn', '2', 'TFLearn_2'),
(63, 'TFLearn', '3', 'TFLearn_3'),
(64, 'TFLearn', '4', 'TFLearn_4'),
(65, 'TFLearn', '5', 'TFLearn_5'),
(66, 'Scikit-Learn', '1', 'Scikit-Learn_1'),
(67, 'Scikit-Learn', '2', 'Scikit-Learn_2'),
(68, 'Scikit-Learn', '3', 'Scikit-Learn_3'),
(69, 'Scikit-Learn', '4', 'Scikit-Learn_4'),
(70, 'Scikit-Learn', '5', 'Scikit-Learn_5'),
(71, 'Keras', '1', 'Keras_1'),
(72, 'Keras', '2', 'Keras_2'),
(73, 'Keras', '3', 'Keras_3'),
(74, 'Keras', '4', 'Keras_4'),
(75, 'Keras', '5', 'Keras_5'),
(76, 'Ruby/Rails', '1', 'Ruby/Rails_1'),
(77, 'Ruby/Rails', '2', 'Ruby/Rails_2'),
(78, 'Ruby/Rails', '3', 'Ruby/Rails_3'),
(79, 'Ruby/Rails', '4', 'Ruby/Rails_4'),
(80, 'Ruby/Rails', '5', 'Ruby/Rails_5'),
(81, 'Flask', '1', 'Flask_1'),
(82, 'Flask', '2', 'Flask_2'),
(83, 'Flask', '3', 'Flask_3'),
(84, 'Flask', '4', 'Flask_4'),
(85, 'Flask', '5', 'Flask_5');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `job_skill_mappings`
--
ALTER TABLE `job_skill_mappings`
  ADD CONSTRAINT `FK4m7nswu5w3q0r4gsr6q10em1n` FOREIGN KEY (`skill_id`) REFERENCES `skills` (`skill_id`),
  ADD CONSTRAINT `FKb18cfyxgm0mg7crhthvcu83qt` FOREIGN KEY (`job_id`) REFERENCES `jobs` (`job_id`);

--
-- Constraints for table `job_technology_mappings`
--
ALTER TABLE `job_technology_mappings`
  ADD CONSTRAINT `FKqpedbt40ekl36y3x718bq36da` FOREIGN KEY (`job_id`) REFERENCES `jobs` (`job_id`),
  ADD CONSTRAINT `FKtog1hjfgel2cu8d04jmeobwx1` FOREIGN KEY (`tech_id`) REFERENCES `technologies` (`tech_id`);

--
-- Constraints for table `profiles`
--
ALTER TABLE `profiles`
  ADD CONSTRAINT `FKbblggxqwpx9jkvoc2og3ii9h9` FOREIGN KEY (`user_id`) REFERENCES `credentials` (`user_id`);

--
-- Constraints for table `profile_skill_mappings`
--
ALTER TABLE `profile_skill_mappings`
  ADD CONSTRAINT `FKgyk9xy8tp42y7oqvpo3394psg` FOREIGN KEY (`skill_id`) REFERENCES `skills` (`skill_id`),
  ADD CONSTRAINT `FKra5f6itu7dn9hsigdmg3nudv8` FOREIGN KEY (`profile_id`) REFERENCES `profiles` (`profile_id`);

--
-- Constraints for table `profile_technology_mappings`
--
ALTER TABLE `profile_technology_mappings`
  ADD CONSTRAINT `FK479j20p77hp14af4a1xy8rcl8` FOREIGN KEY (`profile_id`) REFERENCES `profiles` (`profile_id`),
  ADD CONSTRAINT `FKc0yw1fmcr522h06x5qpc7kdia` FOREIGN KEY (`tech_id`) REFERENCES `technologies` (`tech_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
