-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Servidor: bbdd
-- Tiempo de generación: 17-05-2022 a las 16:52:14
-- Versión del servidor: 8.0.27
-- Versión de PHP: 7.4.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `taller_db`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedores`
--

CREATE TABLE `proveedores` (
  `id` int NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `fechaAlta` date NOT NULL,
  `direccion` varchar(100) NOT NULL,
  `telefono` varchar(13) NOT NULL,
  `email` varchar(100) NOT NULL,
  `contacto` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `proveedores`
--

INSERT INTO `proveedores` (`id`, `nombre`, `fechaAlta`, `direccion`, `telefono`, `email`, `contacto`) VALUES
(1, 'SEAT Customer Service SA', '2018-05-08', 'C/Villanueva de Luengos, 23. Madrid', '0034676767671', 'spareparts@madrid.seatservice.com', 'Tomás Del Bosque'),
(2, 'Opel España Técnica', '2019-02-08', 'C/Alto del Jarama, 56. Madrid', '0034655665599', 'servicemadrid@spainopel.com', 'Diego Ferrola'),
(3, 'Daimler-Benz AG ', '2016-02-08', 'Mercedesstrasse, 137, Stuttgart, Deutschland', '0049711259064', 'gebrauchtteile@daimler-benz.com', 'Marcus Müller'),
(4, 'Recambios Málaga SA', '2022-05-03', 'C/ Huerta Alta, 4. 29010. Málaga', '0034666555444', 'clientes@recambiosmalaga.com', 'Ángel Gordillo');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `proveedores`
--
ALTER TABLE `proveedores`
  ADD PRIMARY KEY (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
