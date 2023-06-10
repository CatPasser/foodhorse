-- phpMyAdmin SQL Dump
-- version 4.9.5deb2
-- https://www.phpmyadmin.net/
--
-- 主機： localhost:3306
-- 產生時間： 2023 年 06 月 10 日 13:03
-- 伺服器版本： 8.0.33-0ubuntu0.20.04.1
-- PHP 版本： 7.4.3-4ubuntu2.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 資料庫： `foodhorse`
--

-- --------------------------------------------------------

--
-- 資料表結構 `byorder`
--

CREATE TABLE `byorder` (
  `byID` int NOT NULL,
  `fID` int UNSIGNED NOT NULL,
  `uID` int UNSIGNED NOT NULL,
  `oID` int UNSIGNED NOT NULL,
  `amount` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- 傾印資料表的資料 `byorder`
--

INSERT INTO `byorder` (`byID`, `fID`, `uID`, `oID`, `amount`) VALUES
(30, 8, 2, 30, 3),
(31, 3, 2, 31, 1),
(32, 6, 2, 31, 1),
(33, 7, 2, 31, 1),
(34, 2, 2, 31, 1),
(35, 4, 2, 31, 1),
(36, 5, 2, 31, 1),
(37, 8, 2, 31, 1),
(38, 3, 2, 32, 1),
(39, 9, 2, 33, 1),
(40, 3, 2, 34, 2),
(41, 6, 2, 34, 1),
(45, 3, 2, 38, 1),
(47, 3, 2, 40, 1),
(48, 3, 2, 41, 1),
(49, 9, 2, 41, 1),
(55, 3, 2, 43, 1),
(57, 3, 5, 45, 1),
(58, 9, 5, 46, 1),
(67, 9, 4, 52, 70),
(69, 3, 4, 54, 1),
(70, 9, 4, 54, 2),
(71, 3, 4, 55, 2),
(72, 7, 4, 55, 4),
(73, 20, 4, 56, 2),
(74, 18, 4, 56, 2),
(75, 9, 4, 56, 3);

-- --------------------------------------------------------

--
-- 資料表結構 `cart`
--

CREATE TABLE `cart` (
  `cID` int UNSIGNED NOT NULL,
  `uID` int UNSIGNED NOT NULL,
  `sID` int UNSIGNED NOT NULL,
  `fID` int UNSIGNED NOT NULL,
  `amount` int NOT NULL,
  `user_notes` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `created_at` timestamp NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- 傾印資料表的資料 `cart`
--

INSERT INTO `cart` (`cID`, `uID`, `sID`, `fID`, `amount`, `user_notes`, `created_at`) VALUES
(120, 5, 2, 9, 1, '', '2023-06-07 14:17:35'),
(125, 2, 1, 3, 1, '', '2023-06-07 14:42:32'),
(136, 2, 1, 3, 1, '', '2023-06-07 15:22:04'),
(142, 4, 1, 3, 1, '', '2023-06-07 15:59:18'),
(144, 4, 2, 9, 4, '', '2023-06-07 15:59:45'),
(145, 2, 8, 20, 1, '', '2023-06-07 16:05:01'),
(146, 2, 2, 9, 1, '', '2023-06-07 16:10:06'),
(147, 4, 1, 5, 4, '', '2023-06-08 00:42:10'),
(148, 4, 1, 8, 3, '', '2023-06-08 00:42:14');

-- --------------------------------------------------------

--
-- 資料表結構 `food`
--

CREATE TABLE `food` (
  `fID` int UNSIGNED NOT NULL,
  `sID` int UNSIGNED NOT NULL,
  `foodname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `tag` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `photo` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `price` int NOT NULL,
  `notes` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- 傾印資料表的資料 `food`
--

INSERT INTO `food` (`fID`, `sID`, `foodname`, `tag`, `photo`, `price`, `notes`, `created_at`, `updated_at`) VALUES
(2, 1, '套餐-大麥克', '套餐', 'photo/foodPhoto/upload/03-04-2023-1680508938-.webp', 165, '', '2023-04-03 08:02:18', NULL),
(3, 1, '麥脆雞腿', '單點', 'photo/foodPhoto/upload/03-04-2023-1680509030-.webp', 65, '原味/辣味', '2023-04-03 08:03:50', '2023-04-08 07:52:40'),
(4, 1, '套餐-蕈菇安格斯牛肉堡', '套餐', 'photo/foodPhoto/upload/03-04-2023-1680509120-.webp', 204, '', '2023-04-03 08:05:20', NULL),
(5, 1, '套餐-BLT嫩煎雞腿堡', '套餐', 'photo/foodPhoto/upload/03-04-2023-1680509192-.webp', 194, '', '2023-04-03 08:06:32', NULL),
(6, 1, '麥香雞', '單點', 'photo/foodPhoto/upload/03-04-2023-1680509346-.webp', 65, '', '2023-04-03 08:09:06', NULL),
(7, 1, '麥克雞塊(6塊)', '單點', 'photo/foodPhoto/upload/03-04-2023-1680509509-.webp', 69, '', '2023-04-03 08:11:49', NULL),
(8, 1, '可口可樂', '飲料', 'photo/foodPhoto/upload/03-04-2023-1680509588-.webp', 38, '', '2023-04-03 08:13:08', NULL),
(9, 2, '抹茶蛋糕', '蛋糕', 'photo/foodPhoto/upload/08-04-2023-1680937650-.webp', 70, '不是抹茶蛋糕', '2023-04-08 07:07:30', '2023-04-08 07:51:54'),
(17, 7, '火雞肉飯', '飯', 'photo/foodPhoto/upload/07-06-2023-1686147595-.webp', 14, '', '2023-06-07 14:19:55', '2023-06-07 14:23:10'),
(18, 8, '1', '1', 'photo/foodPhoto/upload/07-06-2023-1686147693-.webp', 1, '1', '2023-06-07 14:21:33', NULL),
(19, 7, '嘖嘖', '嘖', 'photo/foodPhoto/upload/07-06-2023-1686147844-.webp', 18, '', '2023-06-07 14:24:04', NULL),
(20, 8, '開水煮青蛙', '青蛙', 'photo/foodPhoto/upload/07-06-2023-1686148330-.webp', 1000, '', '2023-06-07 14:32:10', NULL),
(21, 7, '就解決', '就解決', 'photo/foodPhoto/upload/07-06-2023-1686148443-.webp', 10, '', '2023-06-07 14:34:03', NULL);

-- --------------------------------------------------------

--
-- 資料表結構 `horse`
--

CREATE TABLE `horse` (
  `hID` int UNSIGNED NOT NULL,
  `horsename` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `fullname` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `phone` int NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- 傾印資料表的資料 `horse`
--

INSERT INTO `horse` (`hID`, `horsename`, `password`, `fullname`, `phone`, `created_at`, `updated_at`) VALUES
(1, 'horse1', '$2y$10$pE4P.ONtSIE7HDDS2iLsBe7k25tdGbdH5X8Z..CTOAgLUESo2amUW', NULL, 123456789, '2023-04-07 07:43:22', NULL),
(2, '1', '$2y$10$gHQLBkvB2LqGgPFGQRw9peZScroKYkcDzO5.NSrWprUZBUp1vATVe', '1', 1, '2023-04-10 13:40:52', '2023-04-10 13:41:28'),
(3, 'horse123', '$2y$10$o6gHrJT5FnIN5Ib/MU6LqekzUV/POoPtpcz6nARQFcovrbDrnQwGe', NULL, 646864578, '2023-05-02 06:00:17', NULL),
(4, 'horse111', '$2y$10$XGlY5P1r8Ve4e4cDHfHFG.PJxRPzrvVJKTRdT3uEDhhv4ICbZYQjS', NULL, 22222222, '2023-06-07 12:55:08', NULL),
(8, 'zxc', '$2y$10$3CUi3VFZ6dbI1SbBeXSOQ.rATYHej6Odsmb1FVsL/TtLy4MdsGEUK', NULL, 123456666, '2023-06-07 13:22:44', NULL);

-- --------------------------------------------------------

--
-- 資料表結構 `horse_evaluation`
--

CREATE TABLE `horse_evaluation` (
  `heID` int NOT NULL,
  `hID` int UNSIGNED NOT NULL,
  `score` int UNSIGNED NOT NULL DEFAULT '0',
  `number` int UNSIGNED NOT NULL DEFAULT '0',
  `average` float DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- 傾印資料表的資料 `horse_evaluation`
--

INSERT INTO `horse_evaluation` (`heID`, `hID`, `score`, `number`, `average`) VALUES
(4, 2, 14, 5, 4),
(5, 8, 4, 1, 4);

-- --------------------------------------------------------

--
-- 資料表結構 `orders`
--

CREATE TABLE `orders` (
  `oID` int UNSIGNED NOT NULL,
  `uID` int UNSIGNED NOT NULL,
  `sID` int UNSIGNED NOT NULL,
  `hID` int UNSIGNED DEFAULT NULL,
  `location` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `notes` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `total_price` int NOT NULL,
  `state` enum('外送員配對中','餐點配送中','已送達','已完成','已評價') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- 傾印資料表的資料 `orders`
--

INSERT INTO `orders` (`oID`, `uID`, `sID`, `hID`, `location`, `notes`, `total_price`, `state`, `created_at`) VALUES
(30, 2, 1, 2, '高雄市三民區建工路99號', '', 114, '已評價', '2023-05-10 12:20:00'),
(31, 2, 1, 2, '高雄市三民區建工路200號', '', 800, '已評價', '2023-05-10 13:05:58'),
(32, 2, 1, 2, '高雄市三民區建工路200號', '', 65, '已評價', '2023-05-10 13:53:27'),
(33, 2, 2, 2, '高雄市三民區建工路200號', '', 70, '已評價', '2023-05-13 13:42:13'),
(34, 2, 1, 2, '高雄市三民區建工路200號', '', 195, '已評價', '2023-05-13 14:14:17'),
(38, 2, 1, 2, '高雄市三民區建工路200號', '', 65, '餐點配送中', '2023-06-03 15:57:28'),
(40, 2, 1, NULL, '高雄市三民區建工路200號', '', 65, '外送員配對中', '2023-06-07 12:36:31'),
(41, 2, 1, NULL, '高雄市三民區建工路200號', '', 135, '外送員配對中', '2023-06-07 12:37:45'),
(43, 2, 1, NULL, '高雄市三民區建工路200號', '', 65, '外送員配對中', '2023-06-07 12:38:30'),
(45, 5, 1, NULL, '高雄市三民區建工路400號', '', 65, '外送員配對中', '2023-06-07 12:50:31'),
(46, 5, 2, 4, '高雄市三民區建工路400號', '', 70, '餐點配送中', '2023-06-07 12:50:59'),
(52, 4, 2, 8, '5807高雄市三民區大豐二路203號', '', 4900, '已評價', '2023-06-07 14:03:43'),
(54, 4, 1, NULL, '807台灣高雄市三民區建武路228號', '', 205, '外送員配對中', '2023-06-07 15:30:59'),
(55, 4, 1, NULL, '807台灣高雄市三民區建武路228號', '', 406, '外送員配對中', '2023-06-07 15:34:30'),
(56, 4, 8, NULL, '807台灣高雄市三民區建武路228號', '', 2212, '外送員配對中', '2023-06-07 15:35:32');

-- --------------------------------------------------------

--
-- 資料表結構 `shop`
--

CREATE TABLE `shop` (
  `sID` int UNSIGNED NOT NULL,
  `shopname` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `fullname` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `phone` int NOT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `photo` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `tag` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- 傾印資料表的資料 `shop`
--

INSERT INTO `shop` (`sID`, `shopname`, `password`, `fullname`, `phone`, `address`, `photo`, `tag`, `created_at`, `updated_at`) VALUES
(1, 'shop1', '$2y$10$eF34jzmQzAvJPFZF8yxA0ug5/xwjvbhVCN/3E4K.Oczt1aX.6O/Vm', '麥當勞(鳳山鳳頂店)', 123456789, '高雄市鳳山區鳳頂路50號', 'photo/shopPhoto/upload/03-04-2023-1680510378-.webp', '快餐', '2023-04-03 07:33:12', '2023-06-07 14:38:29'),
(2, 'shop2', '$2y$10$tN2sGtgCxlypeTiuUOxSr.gfipQAENyzKLMbcdwyASGBM5dIQY/Am', '鶴茶樓(鳳山五甲店)', 987654321, '830高雄市鳳山區五甲二路657號', 'photo/shopPhoto/upload/03-04-2023-1680511708-.webp', '飲料', '2023-04-03 08:39:34', '2023-06-07 14:38:48'),
(3, 'shop3', '$2y$10$v4krQ3D2H.ZQdHD31wVWNuVCxjdOOZuKWfIKv7cLzp1frxiTYVKt.', 'fad', 123123123, NULL, NULL, NULL, '2023-04-03 08:39:58', NULL),
(5, '2', '$2y$10$cKauosXLORWUGwAXL3VsbeDLxPc5CV/ly8TxBCTK9RoyiPSqY86eq', '2', 222, '高雄市鳳山區鳳頂路90號', NULL, NULL, '2023-05-02 05:52:59', '2023-05-02 05:53:15'),
(6, 'shop123', '$2y$10$kw8xSJDMvVH7hI2Q79MbiuCuULdVrVqF8NMe9Vb0o2glnPkcbvHca', 'shop123', 738364687, NULL, NULL, NULL, '2023-05-02 05:59:58', NULL),
(7, '宜', '$2y$10$kd49zRl.GRiwV0kMJr9P5OwtsZbN.U40VnCqh/qpCRugp0rtHU3VO', '嘖嘖嘖嘖', 1234, '807高雄市三民區大豐二路203號', NULL, '標籤2', '2023-06-07 13:03:13', '2023-06-07 14:27:25'),
(8, 'shop111', '$2y$10$AUWnsEZzm4cjFRdhHbLs2eXxyQAHXb/qNCPqfvbdQd8o2jrPtF0nW', 'shop111', 33437676, '高雄市三民區建工路400號', 'photo/shopPhoto/upload/07-06-2023-1686147636-.webp', '早餐', '2023-06-07 14:19:46', '2023-06-07 14:30:33');

-- --------------------------------------------------------

--
-- 資料表結構 `shop_evaluation`
--

CREATE TABLE `shop_evaluation` (
  `seID` int NOT NULL,
  `sID` int UNSIGNED NOT NULL,
  `score` int NOT NULL DEFAULT '0',
  `number` int NOT NULL DEFAULT '0',
  `average` float DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- 傾印資料表的資料 `shop_evaluation`
--

INSERT INTO `shop_evaluation` (`seID`, `sID`, `score`, `number`, `average`) VALUES
(2, 1, 13, 3, 3),
(3, 2, 3, 1, 3);

-- --------------------------------------------------------

--
-- 資料表結構 `users`
--

CREATE TABLE `users` (
  `uID` int UNSIGNED NOT NULL,
  `username` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `fullname` text COLLATE utf8mb4_unicode_ci,
  `phone` int NOT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- 傾印資料表的資料 `users`
--

INSERT INTO `users` (`uID`, `username`, `password`, `fullname`, `phone`, `address`, `created_at`, `updated_at`) VALUES
(1, 'user1', '$2y$10$bz2m1kccVN64BH8aYhgyKO/O020vjy54pkhasgddpeyNxlsT868rK', NULL, 987654321, NULL, '2023-04-07 07:45:03', NULL),
(2, '1', '$2y$10$at4lRYJ53dUL5KzFYbS/8.E5bEJ0fqw.XSQWejee/IJ0/nGvMtfB.', '2', 912345678, '高雄市三民區建工路200號', '2023-04-10 13:39:37', '2023-06-03 15:59:23'),
(3, 'user123', '$2y$10$Cl82J.5fwA7eMYakBNliwOj5vcrBCw0iEylTfYAss7vuJv40zjMxG', 'user123', 45795634, '高雄市三民區建工路70號', '2023-05-02 05:59:36', '2023-05-02 06:02:01'),
(4, '文', '$2y$10$AuuVGMKkHj/fGT.CG.SBYuilR77bNrKcgf3byUKJO4W.RQ9o9iLw2', '文', 908531107, '高雄市三民區大豐二路203號', '2023-06-07 12:28:40', '2023-06-07 14:47:24'),
(5, 'user111', '$2y$10$rB6nhR2cSWinQY02UsvIvupMak7k5XzNmLG.fLchDgZ03tEmq0gPG', NULL, 111111111, NULL, '2023-06-07 12:44:34', NULL),
(7, '文1', '$2y$10$AM9i1wuIZ2P86Rx84J7gceJb44.iIDatJYi6hoTE6Ow/BATN5hVg2', NULL, 1, NULL, '2023-06-07 12:47:27', NULL);

--
-- 已傾印資料表的索引
--

--
-- 資料表索引 `byorder`
--
ALTER TABLE `byorder`
  ADD PRIMARY KEY (`byID`),
  ADD KEY `oID` (`oID`),
  ADD KEY `uID` (`uID`),
  ADD KEY `byorder_fID_foreign` (`fID`) USING BTREE;

--
-- 資料表索引 `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`cID`),
  ADD KEY `cart_uID_foreign` (`uID`) USING BTREE,
  ADD KEY `cart_fID_foreign` (`fID`) USING BTREE,
  ADD KEY `cart_sID_foreign` (`sID`) USING BTREE;

--
-- 資料表索引 `food`
--
ALTER TABLE `food`
  ADD PRIMARY KEY (`fID`),
  ADD KEY `food_sID_foreign` (`sID`) USING BTREE;

--
-- 資料表索引 `horse`
--
ALTER TABLE `horse`
  ADD PRIMARY KEY (`hID`),
  ADD UNIQUE KEY `horse_name` (`horsename`),
  ADD UNIQUE KEY `horse_phone` (`phone`);

--
-- 資料表索引 `horse_evaluation`
--
ALTER TABLE `horse_evaluation`
  ADD PRIMARY KEY (`heID`),
  ADD KEY `hID` (`hID`);

--
-- 資料表索引 `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`oID`),
  ADD KEY `orders_hID_foreign` (`hID`),
  ADD KEY `orders_uID_foreign` (`uID`) USING BTREE,
  ADD KEY `orders_sID_foreign` (`sID`);

--
-- 資料表索引 `shop`
--
ALTER TABLE `shop`
  ADD PRIMARY KEY (`sID`),
  ADD UNIQUE KEY `shop_name` (`shopname`),
  ADD UNIQUE KEY `shop_phone` (`phone`),
  ADD UNIQUE KEY `shop_address` (`address`);

--
-- 資料表索引 `shop_evaluation`
--
ALTER TABLE `shop_evaluation`
  ADD PRIMARY KEY (`seID`),
  ADD KEY `sID` (`sID`);

--
-- 資料表索引 `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`uID`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `phone` (`phone`),
  ADD UNIQUE KEY `address` (`address`);

--
-- 在傾印的資料表使用自動遞增(AUTO_INCREMENT)
--

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `byorder`
--
ALTER TABLE `byorder`
  MODIFY `byID` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=76;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `cart`
--
ALTER TABLE `cart`
  MODIFY `cID` int UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=149;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `food`
--
ALTER TABLE `food`
  MODIFY `fID` int UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `horse`
--
ALTER TABLE `horse`
  MODIFY `hID` int UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `horse_evaluation`
--
ALTER TABLE `horse_evaluation`
  MODIFY `heID` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `orders`
--
ALTER TABLE `orders`
  MODIFY `oID` int UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=57;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `shop`
--
ALTER TABLE `shop`
  MODIFY `sID` int UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `shop_evaluation`
--
ALTER TABLE `shop_evaluation`
  MODIFY `seID` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `users`
--
ALTER TABLE `users`
  MODIFY `uID` int UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- 已傾印資料表的限制式
--

--
-- 資料表的限制式 `byorder`
--
ALTER TABLE `byorder`
  ADD CONSTRAINT `byorder_ibfk_1` FOREIGN KEY (`fID`) REFERENCES `food` (`fID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `byorder_ibfk_2` FOREIGN KEY (`oID`) REFERENCES `orders` (`oID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `byorder_ibfk_3` FOREIGN KEY (`uID`) REFERENCES `users` (`uID`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- 資料表的限制式 `cart`
--
ALTER TABLE `cart`
  ADD CONSTRAINT `cart_ibfk_1` FOREIGN KEY (`fID`) REFERENCES `food` (`fID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `cart_ibfk_2` FOREIGN KEY (`sID`) REFERENCES `shop` (`sID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `cart_ibfk_3` FOREIGN KEY (`uID`) REFERENCES `users` (`uID`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- 資料表的限制式 `food`
--
ALTER TABLE `food`
  ADD CONSTRAINT `product_sid_foreign` FOREIGN KEY (`sID`) REFERENCES `shop` (`sID`);

--
-- 資料表的限制式 `horse_evaluation`
--
ALTER TABLE `horse_evaluation`
  ADD CONSTRAINT `horse_evaluation_ibfk_1` FOREIGN KEY (`hID`) REFERENCES `horse` (`hID`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- 資料表的限制式 `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`hID`) REFERENCES `horse` (`hID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`uID`) REFERENCES `users` (`uID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `orders_ibfk_3` FOREIGN KEY (`sID`) REFERENCES `shop` (`sID`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- 資料表的限制式 `shop_evaluation`
--
ALTER TABLE `shop_evaluation`
  ADD CONSTRAINT `shop_evaluation_ibfk_1` FOREIGN KEY (`sID`) REFERENCES `shop` (`sID`) ON DELETE RESTRICT ON UPDATE RESTRICT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
