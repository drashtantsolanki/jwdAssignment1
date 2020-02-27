-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 27, 2020 at 06:07 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.2.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `employeedb`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`` PROCEDURE `sp_getEmployeeByDepartment` (IN `did` INT)  NO SQL
SELECT DISTINCT tblemployee.Name,tblemployee.Salary 
from tblemployee,tbldepartment
WHERE tblemployee.DeptId=tbldepartment.DeptId AND tbldepartment.DeptId=did$$

CREATE DEFINER=`` PROCEDURE `sp_getTotalSalaryOfDepartment` ()  NO SQL
SELECT tbldepartment.DepartmentName,SUM(tblemployee.Salary)
from tblemployee,tbldepartment
WHERE tblemployee.DeptId=tbldepartment.DeptId
GROUP BY tbldepartment.DeptId$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `tbldepartment`
--

CREATE TABLE `tbldepartment` (
  `DeptId` int(11) NOT NULL,
  `DepartmentName` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tbldepartment`
--

INSERT INTO `tbldepartment` (`DeptId`, `DepartmentName`) VALUES
(1, 'mscit'),
(2, 'mscICT'),
(3, 'BscIT'),
(6, 'Physics'),
(7, 'mscit1'),
(8, 'mscit'),
(11, 'Chemistry');

-- --------------------------------------------------------

--
-- Table structure for table `tblemployee`
--

CREATE TABLE `tblemployee` (
  `Id` int(11) NOT NULL,
  `Name` varchar(25) NOT NULL,
  `Salary` int(11) NOT NULL,
  `DeptId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tblemployee`
--

INSERT INTO `tblemployee` (`Id`, `Name`, `Salary`, `DeptId`) VALUES
(1, 'john D. wick', 8000, 6),
(2, 'Jonathan Wick', 451245, 2),
(3, 'Leo', 45789, 6),
(4, 'john D. wick', 8000, 6),
(5, 'Will Robinson', 95124, 11);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbldepartment`
--
ALTER TABLE `tbldepartment`
  ADD PRIMARY KEY (`DeptId`);

--
-- Indexes for table `tblemployee`
--
ALTER TABLE `tblemployee`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `fk_deptId` (`DeptId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbldepartment`
--
ALTER TABLE `tbldepartment`
  MODIFY `DeptId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `tblemployee`
--
ALTER TABLE `tblemployee`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tblemployee`
--
ALTER TABLE `tblemployee`
  ADD CONSTRAINT `fk_deptId` FOREIGN KEY (`DeptId`) REFERENCES `tbldepartment` (`DeptId`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
