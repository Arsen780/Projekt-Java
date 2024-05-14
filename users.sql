-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Cze 21, 2023 at 11:17 AM
-- Wersja serwera: 10.4.28-MariaDB
-- Wersja PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `rejestracja`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `users`
--

CREATE TABLE `users` (
  `id` int(10) NOT NULL,
  `imie` varchar(200) NOT NULL,
  `nazwisko` varchar(200) NOT NULL,
  `email` varchar(200) NOT NULL,
  `nazwa` varchar(200) NOT NULL,
  `haslo` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `imie`, `nazwisko`, `email`, `nazwa`, `haslo`) VALUES
(10, 'Jan', 'Kowalski', 'jankowalski@wp.pl', 'Jan123', 'Jankowalski1'),
(11, 'Arek', 'Kot', 'arekkot123@wp.pl', 'Arekkot', 'Qwerty123'),
(12, 'Adam', 'Nowak', 'adamnowak123@o2.pl', 'Adam.Nowak', 'AdamNowak123'),
(13, 'Arek', 'Kot', 'defew@wp.pl', 'WDEF2fv', 'dcsvrDEF33'),
(15, 'ewfe', 'efwfew', 'dfweef@wd.pl', 'efwew33', 'Hasło123'),
(16, 'Pan', 'Użytkownik', 'email@onet.pl', 'asdfghjkl', 'Hasło111'),
(17, 'Arkadiusz', 'Kot', 'arkadiuszKot@wp.pl', 'arekkot111', 'Zxcvbnm111');

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `nazwa` (`nazwa`),
  ADD UNIQUE KEY `haslo` (`haslo`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
