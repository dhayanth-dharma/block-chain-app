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
-- Database: `cheese_coin_system_server`
--

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `_member`
--

INSERT INTO `_member` (`member_id`, `member_name`, `member_user_id`, `member_ip`, `member_port`, `status`) VALUES
(1, 'dhaya', 0, '192.168.99.1', 8825, 'active'),
(2, 'mario', 6, '66.66.66.66', 6666, 'dead'),
(3, 'dhaya', 0, '192.168.99.1', 8825, NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `_member`
--
ALTER TABLE `_member`
  ADD PRIMARY KEY (`member_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `_member`
--
ALTER TABLE `_member`
  MODIFY `member_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
