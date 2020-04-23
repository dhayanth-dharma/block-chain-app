-- phpMyAdmin SQL Dump
-- version 4.3.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Mar 24, 2020 at 11:33 PM
-- Server version: 5.6.24
-- PHP Version: 5.5.24

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `cheese_coin_system_2`
--

-- --------------------------------------------------------

--
-- Table structure for table `_account`
--

CREATE TABLE IF NOT EXISTS `_account` (
  `account_id` int(11) NOT NULL,
  `public_key` varchar(8000) DEFAULT NULL,
  `private_key` varchar(8000) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `account_number` int(11) DEFAULT NULL,
  `balance` double DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `_account`
--

INSERT INTO `_account` (`account_id`, `public_key`, `private_key`, `user_id`, `account_number`, `balance`) VALUES
(1, 'asdkjaskd', 'aklsjdlakdj', 2, 544, 0),
(2, 'MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCh3mJyYthG/OP/xalbEUbe9YypRRWxsTkjf8OKqCb9PkYmjnZsk+DbIu/bGvI3p1rKh1+FGWeZ9uh9GX5gPUCIDo0L02qiHS/rFubp3aok8P5Iicbjo9h6BDsNmNU5qk7TFnveSe6GgT25giAXGF7rEpUxRjqhcO5WtCNdGCFE3wIDAQAB', 'MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKHeYnJi2Eb84//FqVsRRt71jKlFFbGxOSN/w4qoJv0+RiaOdmyT4Nsi79sa8jenWsqHX4UZZ5n26H0ZfmA9QIgOjQvTaqIdL+sW5undqiTw/kiJxuOj2HoEOw2Y1TmqTtMWe95J7oaBPbmCIBcYXusSlTFGOqFw7la0I10YIUTfAgMBAAECgYEAm2SFN/CCVivCqbFU8tL0KWirP3jN6ccL5nNqAjuJKoZVDACp2XRhYZ35i2sj6p+jvlgVMA6CccEh0a7ern8+6Y9Mhzi0Re/ExFeNoU1O+DcKoBGpIxtr41iQwwZXhnqA5OhQsEFne3MP/Kw593g9AWvOrqKMrLoQddABdGPFD3kCQQD6sVKXELwzGBgFLl/tf9j+Mj0nRvxu4JbZXV1qcozxRN39bxNpfSYILQdY92Jyojkw0q4x8Lap1jYMDMhWUTTjAkEApUupyO2sO3xyJho9X1MbPyoS+fAq1MPwi16cizqve0EDXOPCMGdMb133jFqnVTbNZJ0g5M54oKdgZ0fLNyXs1QJAdDesYBgEB3PN/ujm08WHJSDJNOI1uHkptY8Q9X4xVNBmUU0jJK496WBmnZnh697EwJuK4eAD7mw+Fgv++/UdnQJAXYB/enl3g+7RY1xUeye3sfNK6LdCgziTSUMrqlZ4DXCpRlJP9rF4O0ccUPi2zy1A4jk/8i4Zbkc/8Sxbd8asQQJAJStMRcplgzSIBlOUJQIx5KKGbq3s5yoE16gN0hqTtAXMmVHXcnpZIr/Lvm/d/pLFYH2gitzXtuqbdavqKMAEbQ==', 17, 545, 0);

-- --------------------------------------------------------

--
-- Table structure for table `_cheese`
--

CREATE TABLE IF NOT EXISTS `_cheese` (
  `cheese_id` int(11) NOT NULL,
  `previous_hash` varchar(255) DEFAULT NULL,
  `hash` varchar(255) DEFAULT NULL,
  `nonce` int(11) DEFAULT NULL,
  `dificulty` int(11) DEFAULT NULL,
  `member_id` int(11) DEFAULT NULL,
  `public_transaction_id` int(11) DEFAULT NULL,
  `time_stamp` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `reward` double NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `_cheese`
--

INSERT INTO `_cheese` (`cheese_id`, `previous_hash`, `hash`, `nonce`, `dificulty`, `member_id`, `public_transaction_id`, `time_stamp`, `reward`) VALUES
(2, '', 'b94d27b9934d3e08a52e52d7da7dabfac484efe37a5380ee9088f7ace2efcde9', 0, 3, 0, 0, '2020-04-10 00:00:00', 0),
(3, 'b94d27b9934d3e08a52e52d7da7dabfac484efe37a5380ee9088f7ace2efcde9', '0yuo9NsBCNvHr8rBjC6tU25t2M6xAs//OnEhAIAh2/Q=', 64, 1, 1, 3, '2020-03-22 19:38:04', 0.12),
(4, '0yuo9NsBCNvHr8rBjC6tU25t2M6xAs//OnEhAIAh2/Q=', '0nzhtTw3ozCDJ/aGrnXcqWdjWvuhoPQN7WKkt+RrLPM=', 143, 1, 1, 1, '2020-03-22 20:42:32', 0.12);

-- --------------------------------------------------------

--
-- Table structure for table `_member`
--

CREATE TABLE IF NOT EXISTS `_member` (
  `member_id` int(11) NOT NULL,
  `member_name` varchar(255) DEFAULT NULL,
  `member_user_id` int(11) DEFAULT NULL,
  `member_ip` varchar(255) DEFAULT NULL,
  `member_port` int(11) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `_member`
--

INSERT INTO `_member` (`member_id`, `member_name`, `member_user_id`, `member_ip`, `member_port`, `status`) VALUES
(1, 'dhaya', 0, '192.168.99.1', 8825, 'active'),
(2, 'mario', 6, '66.66.66.66', 6666, 'dead');

-- --------------------------------------------------------

--
-- Table structure for table `_member_list`
--

CREATE TABLE IF NOT EXISTS `_member_list` (
  `member_id` int(11) NOT NULL,
  `member_ip` varchar(255) DEFAULT NULL,
  `member_port` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `_proof_of_work`
--

CREATE TABLE IF NOT EXISTS `_proof_of_work` (
  `proof_of_work_id` int(11) NOT NULL,
  `transaction_id` int(11) DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `receiver_account_id` int(11) DEFAULT NULL,
  `date_time` datetime DEFAULT NULL,
  `public_transaction_id` int(11) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `_sender`
--

CREATE TABLE IF NOT EXISTS `_sender` (
  `sender_id` int(11) NOT NULL,
  `public_transaction_id` int(11) DEFAULT NULL,
  `sender_account_id` int(11) DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `date_time` datetime DEFAULT NULL,
  `receiver_account_id` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `_sender`
--

INSERT INTO `_sender` (`sender_id`, `public_transaction_id`, `sender_account_id`, `amount`, `date_time`, `receiver_account_id`) VALUES
(11, 1, 1, 12, '2020-03-22 19:30:00', 2),
(12, 2, 1, 12, '2020-03-22 19:31:20', 2),
(13, 3, 1, 12, '2020-03-22 19:37:23', 2),
(14, 1, 1, 12, '2020-03-22 20:40:43', 2);

-- --------------------------------------------------------

--
-- Table structure for table `_transaction`
--

CREATE TABLE IF NOT EXISTS `_transaction` (
  `transaction_id` int(11) NOT NULL,
  `amount` double DEFAULT NULL,
  `receiver_account_id` int(11) DEFAULT NULL,
  `date_time` datetime DEFAULT NULL,
  `public_transaction_id` int(11) DEFAULT NULL,
  `time_stamp` datetime DEFAULT NULL,
  `status` varchar(255) DEFAULT 'waiting'
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `_transaction`
--

INSERT INTO `_transaction` (`transaction_id`, `amount`, `receiver_account_id`, `date_time`, `public_transaction_id`, `time_stamp`, `status`) VALUES
(18, 20, 2, '2020-03-22 20:40:43', 1, NULL, 'waiting');

-- --------------------------------------------------------

--
-- Table structure for table `_user`
--

CREATE TABLE IF NOT EXISTS `_user` (
  `use_id` int(11) NOT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `user_type` varchar(255) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `_user`
--

INSERT INTO `_user` (`use_id`, `user_name`, `password`, `user_type`) VALUES
(1, 'dhayanth', 'mario', 'client'),
(2, 'mathan', 'hello', 'client'),
(3, 'dhaya', '123abc', 'client'),
(4, 'dhaqeqe', '123abc', 'client'),
(5, 'dhayawqe', '123abc', 'client'),
(6, 'dhaya234', '123abc', 'client'),
(9, 'ramCh', 'char234', 'user'),
(10, 'jangle', 'jksdhfkjsdhf', 'user'),
(11, 'jilaby', 'jksdhfkjsdhf', 'user'),
(12, 'asdasd', 'akjsdhkjashd', 'user'),
(13, 'user name', '', 'user'),
(14, 'sfsdf', 'sdfsdf', 'user'),
(15, 'sadasd', 'sadas', 'user'),
(16, 'sadasd', 'sadas', 'user'),
(17, 'asd', 'sadasd', 'user');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `_account`
--
ALTER TABLE `_account`
  ADD PRIMARY KEY (`account_id`);

--
-- Indexes for table `_cheese`
--
ALTER TABLE `_cheese`
  ADD PRIMARY KEY (`cheese_id`);

--
-- Indexes for table `_member`
--
ALTER TABLE `_member`
  ADD PRIMARY KEY (`member_id`);

--
-- Indexes for table `_member_list`
--
ALTER TABLE `_member_list`
  ADD PRIMARY KEY (`member_id`);

--
-- Indexes for table `_proof_of_work`
--
ALTER TABLE `_proof_of_work`
  ADD PRIMARY KEY (`proof_of_work_id`);

--
-- Indexes for table `_sender`
--
ALTER TABLE `_sender`
  ADD PRIMARY KEY (`sender_id`);

--
-- Indexes for table `_transaction`
--
ALTER TABLE `_transaction`
  ADD PRIMARY KEY (`transaction_id`);

--
-- Indexes for table `_user`
--
ALTER TABLE `_user`
  ADD PRIMARY KEY (`use_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `_account`
--
ALTER TABLE `_account`
  MODIFY `account_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `_cheese`
--
ALTER TABLE `_cheese`
  MODIFY `cheese_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `_proof_of_work`
--
ALTER TABLE `_proof_of_work`
  MODIFY `proof_of_work_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `_sender`
--
ALTER TABLE `_sender`
  MODIFY `sender_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=15;
--
-- AUTO_INCREMENT for table `_transaction`
--
ALTER TABLE `_transaction`
  MODIFY `transaction_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=19;
--
-- AUTO_INCREMENT for table `_user`
--
ALTER TABLE `_user`
  MODIFY `use_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=18;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
