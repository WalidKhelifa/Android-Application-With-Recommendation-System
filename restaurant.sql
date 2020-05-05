-- phpMyAdmin SQL Dump
-- version 4.9.3
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost:8889
-- Généré le :  lun. 04 mai 2020 à 18:38
-- Version du serveur :  5.7.26
-- Version de PHP :  7.4.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `restaurant`
--

-- --------------------------------------------------------

--
-- Structure de la table `allergies`
--

CREATE TABLE `allergies` (
  `id_allergie` varchar(5) NOT NULL,
  `nom_allergie` varchar(30) NOT NULL,
  `desc_allergie` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `allergies`
--

INSERT INTO `allergies` (`id_allergie`, `nom_allergie`, `desc_allergie`) VALUES
('f1	', 'Blanc d\'oeuf', 'Produits laitiers, oeufs'),
('f10', 'Sésame', 'Farines'),
('f105', 'Chocolat', 'Autres'),
('f11', 'Sarrasin', 'Farines'),
('f122', 'Olive noire', 'Plantes'),
('f13', 'Arachide', 'Noix, Solanacées'),
('f132', 'Haricots verts', 'Fruits et Légumes'),
('f14', 'Soja', 'Noix, Solanacées'),
('f144', 'Pistache', 'Fruits et Légumes'),
('f146', 'Mûre', 'Fruits et Légumes'),
('f168', 'Lait en poudre', 'Produits laitiers, oeufs'),
('f17', 'Noisette', 'Noix, Solanacées'),
('f2', 'Lait (vache)', 'Produits laitiers, oeuf'),
('f20', 'Amande', 'Noix, Solanacées'),
('f222', 'Graines de potiron	', 'Plantes'),
('f231', 'Litchi', 'Fruits et Légumes'),
('f234', 'Crabe', 'Produits de la mer'),
('f237', 'Abricot', 'Fruits et Légumes'),
('f24', 'Crevette', 'Produits de la mer'),
('f240', 'Gingembre', 'Fruits et Légumes'),
('f244', 'Concombre', 'Fruits et Légumes'),
('f25', 'Tomate', 'Fruits et Légumes'),
('f26', 'Porc', 'Viandes'),
('f27', 'Boeuf', 'Viandes'),
('f292', 'Maïs', 'Autres'),
('f3', 'Morue', 'Produits de la mer'),
('f31', 'Carotte', 'Fruits et Légumes'),
('f328', 'Figue', 'Fruits et Légumes'),
('f33', 'Orange', 'Fruits et Légumes'),
('f35', 'Pomme de Terre', 'Fruits et Légumes'),
('f36', 'Noix de Coco', 'Fruits et Légumes'),
('f38', 'Epinard', 'Fruits et Légumes'),
('f4', 'Blé	', 'Farines'),
('f40', 'Thon', 'Produits de la mer'),
('f41', 'Saumon', 'Produits de la mer'),
('f44', 'Fraise', 'Fruits et Légumes'),
('f45', 'Levure', 'Farines'),
('f47', 'Ail	', 'Plantes'),
('f48', 'Oignon', 'Légumes, épices'),
('f49', 'Pomme', 'Fruits et Légumes'),
('f5', 'Seigle', 'Farines'),
('f50', 'Raisin', 'Fruits et Légumes'),
('f56', 'Perche', 'Produits de la mer'),
('f6', 'Orge', 'Farines'),
('f7', 'Avoine', 'Farines'),
('f73', 'Cacao', 'Autres'),
('f74', 'Café', 'Autres'),
('f75', 'Jaune doeuf', 'Produits laitiers, oeufs'),
('f80', 'Homard', 'Produits de la mer'),
('f81', 'Cheddar', 'Produits laitiers, oeufs'),
('f83', 'Poulet', 'Viandes'),
('f84', 'Kiwi', 'Fruits et Légumes'),
('f85', 'Céleri', 'Noix, Solanacées'),
('f88', 'Agneau	', 'Viandes'),
('f89', 'Moutarde', 'Légumes, épices'),
('f9', 'Riz', 'Farines'),
('f92 ', 'Banane', 'Fruits et Légumes'),
('f93', 'Mangue', 'Fruits et Légumes'),
('f95', 'Pêche', 'Fruits et Légumes'),
('f97', 'Cerise', 'Fruits et Légumes'),
('u87', 'Graines de tournesol', 'Plantes');

-- --------------------------------------------------------

--
-- Structure de la table `allergies_client`
--

CREATE TABLE `allergies_client` (
  `id_client` int(11) NOT NULL,
  `id_ingredient` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `avis`
--

CREATE TABLE `avis` (
  `id_avis` int(11) NOT NULL,
  `date_avis` varchar(10) NOT NULL,
  `nb_etoile` varchar(2) NOT NULL,
  `heure_avis` varchar(8) NOT NULL,
  `commentaire` text,
  `id_client` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `avis`
--

INSERT INTO `avis` (`id_avis`, `date_avis`, `nb_etoile`, `heure_avis`, `commentaire`, `id_client`) VALUES
(1, '2019/05/21', '1', '21:21', NULL, 1),
(2, '2019/05/21', '3', '21:23', NULL, 1),
(3, '2019/05/21', '5', '21:23', NULL, 1),
(4, '2019/05/21', '4', '21:23', NULL, NULL),
(5, '2019/05/30', '3', '22:38', ' hhhh', 1),
(6, '2019/05/30', '2', '22:39', NULL, 1),
(7, '2019/05/30', '1', '22:40', NULL, NULL),
(8, '2019/05/30', '1', '22:40', 'fgsegsg', NULL),
(9, '2019/05/31', '1', '00:46', NULL, 1),
(10, '2019/06/17', '5', '11:53', 'nbhg', NULL),
(11, '2019/06/17', '5', '11:53', 'nbhg', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

CREATE TABLE `client` (
  `id_client` int(11) NOT NULL,
  `userName` varchar(40) NOT NULL,
  `mdpClient` varchar(40) NOT NULL,
  `photo` text,
  `dateNaissCl` varchar(10) DEFAULT NULL,
  `genre` varchar(1) DEFAULT NULL,
  `taille` varchar(5) DEFAULT NULL,
  `poids` varchar(5) DEFAULT NULL,
  `email` varchar(30) NOT NULL,
  `numeroTel` varchar(20) DEFAULT NULL,
  `adrC` varchar(40) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `client`
--

INSERT INTO `client` (`id_client`, `userName`, `mdpClient`, `photo`, `dateNaissCl`, `genre`, `taille`, `poids`, `email`, `numeroTel`, `adrC`) VALUES
(1, 'rania', 'rania', 'http://192.168.1.2/PhpScripts/Users_uploads/Jun_22_2019_07_23_28_1.jpg', '07/09/1998', 'H', NULL, NULL, 'ranben@gmail.com', NULL, 'Racha Benaissa'),
(27, 'fairy', 'pizza', NULL, NULL, 'F', NULL, NULL, 'benaissaracha2004@gmail.com', NULL, NULL),
(28, 'raniz', 'ffff', NULL, NULL, NULL, NULL, NULL, 'rania', '69', 'Vg'),
(29, 'test', 'test', NULL, NULL, NULL, NULL, NULL, 'test', '3248', 'Ff');

-- --------------------------------------------------------

--
-- Structure de la table `client_ingredients_aimes`
--

CREATE TABLE `client_ingredients_aimes` (
  `id_client` int(11) NOT NULL,
  `id_ingredient` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `client_ingredients_detestes`
--

CREATE TABLE `client_ingredients_detestes` (
  `id_client` int(11) NOT NULL,
  `id_ingredient` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `client_plats_favoris`
--

CREATE TABLE `client_plats_favoris` (
  `id_client` int(11) NOT NULL,
  `id_plat` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `client_plats_favoris`
--

INSERT INTO `client_plats_favoris` (`id_client`, `id_plat`) VALUES
(1, 4),
(1, 6),
(1, 8),
(1, 12),
(27, 51),
(27, 55),
(1, 58);

-- --------------------------------------------------------

--
-- Structure de la table `commande`
--

CREATE TABLE `commande` (
  `id_commande` int(11) NOT NULL,
  `date_commande` varchar(10) NOT NULL,
  `heure_commande` varchar(8) NOT NULL,
  `montant` varchar(10) NOT NULL,
  `type_commande` varchar(30) NOT NULL,
  `id_client` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `commande`
--

INSERT INTO `commande` (`id_commande`, `date_commande`, `heure_commande`, `montant`, `type_commande`, `id_client`) VALUES
(75, '25/05/2019', '00:12', '180.0', 'Livraison à domicile', NULL),
(76, '25/05/2019', '00:13', '180.0', 'Livraison à domicile', NULL),
(77, '25/05/2019', '00:13', '400.0', 'Livraison à domicile', NULL),
(78, '25/05/2019', '00:29', '180.0', 'Livraison à domicile', NULL),
(88, '2019/05/30', '22:42', '250.0', '', NULL),
(90, '09/06/2019', '23:32', '580.0', 'Livraison à domicile', NULL),
(91, '2019/06/12', '21:08', '150.0', '', NULL),
(92, '2019/06/12', '21:10', '200.0', '', NULL),
(93, '2019/06/13', '21:25', '1280.0', '', 1),
(94, '2019/06/16', '11:57', '250.0', '', 27),
(95, '16/06/2019', '19:27', '150.0', 'Livraison à domicile', 1),
(96, '16/06/2019', '19:42', '150.0', 'Livraison à domicile', 1),
(97, '16/06/2019', '19:43', '180.0', 'Livraison à domicile', 1),
(98, '16/06/2019', '19:47', '610.0', 'Livraison à domicile', 1),
(99, '16/06/2019', '19:52', '330.0', 'Livraison à domicile', 1),
(100, '16/06/2019', '19:51', '210.0', 'Livraison à domicile', 1),
(101, '2019/06/17', '11:52', '650.0', '', NULL),
(103, '2019/06/19', '21:42', '330.0', '', 1),
(104, '2019/06/19', '22:27', '150.0', '', 1),
(105, '25/06/2019', '15:36', '180.0', 'Livraison à domicile', 1),
(106, '25/06/2019', '15:36', '180.0', 'Livraison à domicile', 1),
(107, '25/06/2019', '15:39', '150.0', 'Livraison à domicile', 1),
(108, '25/06/2019', '15:39', '180.0', 'Livraison à domicile', 1),
(109, '25/06/2019', '15:40', '150.0', 'Livraison à domicile', 1),
(110, '2019/06/25', '16:20', '150.0', '', 1),
(111, '25/06/2019', '16:26', '360.0', 'Livraison à domicile', 1),
(112, '25/06/2019', '16:27', '330.0', 'Livraison à domicile', 1),
(113, '25/06/2019', '16:29', '330.0', 'Repas au restaurant', 1),
(114, '25/06/2019', '17:00', '330.0', 'Repas à emporter', 1),
(115, '25/06/2019', '18:30', '210.0', 'Livraison à domicile', 1),
(116, '25/06/2019', '19:35', '150.0', 'Livraison à domicile', 1),
(117, '25/06/2019', '19:39', '150.0', 'Livraison à domicile', 1),
(118, '26/06/2019', '20:09', '1480.0', 'Livraison à domicile', 1),
(119, '28/06/2019', '21:32', '180.0', 'Livraison à domicile', 29);

-- --------------------------------------------------------

--
-- Structure de la table `gerant`
--

CREATE TABLE `gerant` (
  `username_ger` varchar(40) NOT NULL,
  `mdp_ger` varchar(40) NOT NULL,
  `email_ger` varchar(30) DEFAULT NULL,
  `nom_rest` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `gerant`
--

INSERT INTO `gerant` (`username_ger`, `mdp_ger`, `email_ger`, `nom_rest`) VALUES
('a', 'a', 'a@live.fr', 'Myresto'),
('rania', 'rania', 'rania@gmail.com', 'Myresto'),
('Walid', 'itashiNL11', 'itashinv@live.fr', 'Myresto');

-- --------------------------------------------------------

--
-- Structure de la table `ingredient`
--

CREATE TABLE `ingredient` (
  `id_ingredient` int(11) NOT NULL,
  `nom_ing` varchar(20) DEFAULT NULL,
  `id_allergie` varchar(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `ingredient`
--

INSERT INTO `ingredient` (`id_ingredient`, `nom_ing`, `id_allergie`) VALUES
(1, 'Chocolat au lait', 'f105'),
(2, 'Sucre', NULL),
(3, 'Oeufs', NULL),
(4, 'Beurre', NULL),
(5, 'Farine', NULL),
(6, 'Framboises', NULL),
(7, 'Poivron', NULL),
(8, 'Sauce tomate', 'f25'),
(9, 'Olives Noirs', 'f122'),
(30, 'Mozzarella', NULL),
(31, 'Poichiche', NULL),
(32, 'Poulet', 'f83'),
(33, 'Navets', NULL),
(34, 'Laitue', NULL),
(35, 'Tomate', 'f25'),
(36, 'Thon', 'f40'),
(37, 'Gruyère', NULL),
(38, 'Riz', 'f9'),
(39, 'Spaghetti', NULL),
(40, 'Oignon', 'f48'),
(41, 'Viande hachée', NULL),
(42, 'Huile d\'olive', NULL),
(43, 'Cheddar', 'f81'),
(44, 'Feuilles de Nori', NULL),
(45, 'Saumon', 'f41'),
(46, 'Sauce béchamel', NULL),
(47, 'Persil', NULL),
(48, 'Carottes', 'f31'),
(49, 'Pommes de terre', 'f35'),
(50, 'Courgettes', NULL),
(51, 'Potiron', NULL),
(52, 'Crème fraiche', NULL),
(53, 'Lasagnes', NULL),
(54, 'Cerises', 'f97'),
(55, 'Fraises', 'f44'),
(56, 'Menthe fraiche', NULL),
(57, 'Noisettes', 'f17'),
(58, 'Citrons', NULL),
(59, 'Bananes', 'f92 '),
(60, 'Crevettes', 'f24'),
(61, 'Ail', 'f47'),
(63, 'Sauce maison', NULL),
(64, 'Champignons', NULL),
(65, 'Poulpe', NULL),
(66, 'Calamar', NULL),
(67, 'Moules', NULL),
(68, 'Meringue', 'f1	'),
(69, 'Aubergines', NULL),
(70, 'Vol au vent', NULL),
(71, 'Grains de café', 'f74'),
(72, 'Lait', 'f2'),
(73, 'Oranges', 'f33'),
(75, 'Eau', NULL),
(76, 'Menthe séchée', NULL),
(77, 'Huile', NULL),
(78, 'Petit pois', NULL),
(79, 'Rascasse', NULL),
(80, 'Dorade', NULL),
(81, 'Rouget', NULL),
(82, 'Sole', NULL),
(83, 'Pate à pizza', NULL),
(84, 'Rechta', NULL),
(85, 'Feuilles de Brik', NULL),
(86, 'Cacao', 'f73'),
(87, 'Biscuits', NULL),
(88, 'Crème liquide', NULL),
(89, 'Vanille', NULL),
(90, 'Levure', 'f45'),
(91, 'Chocolat noir', 'f105'),
(92, 'Canelle', NULL),
(93, 'Café', 'f74');

-- --------------------------------------------------------

--
-- Structure de la table `maladies`
--

CREATE TABLE `maladies` (
  `id_maladie` int(11) NOT NULL,
  `libelle` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `maladies`
--

INSERT INTO `maladies` (`id_maladie`, `libelle`) VALUES
(1, 'diabète'),
(2, 'hypertension artérielle '),
(3, 'hyperlipémie');

-- --------------------------------------------------------

--
-- Structure de la table `maladies_client`
--

CREATE TABLE `maladies_client` (
  `id_client` int(11) NOT NULL,
  `id_maladie` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `maladies_client`
--

INSERT INTO `maladies_client` (`id_client`, `id_maladie`) VALUES
(1, 1);

-- --------------------------------------------------------

--
-- Structure de la table `maladie_ingredients`
--

CREATE TABLE `maladie_ingredients` (
  `id_ingredient` int(11) NOT NULL,
  `id_maladie` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `maladie_ingredients`
--

INSERT INTO `maladie_ingredients` (`id_ingredient`, `id_maladie`) VALUES
(2, 1),
(9, 2),
(36, 2),
(3, 3),
(41, 3),
(46, 3),
(63, 3),
(77, 3);

-- --------------------------------------------------------

--
-- Structure de la table `personnel`
--

CREATE TABLE `personnel` (
  `id_personnel` int(11) NOT NULL,
  `nom_pers` varchar(40) DEFAULT NULL,
  `pren_pers` varchar(40) DEFAULT NULL,
  `dateNaissPers` varchar(10) DEFAULT NULL,
  `genre_pers` varchar(10) DEFAULT NULL,
  `type_pers` varchar(10) DEFAULT NULL,
  `adresse_pers` varchar(40) DEFAULT NULL,
  `num_tel_pers` varchar(20) DEFAULT NULL,
  `email_pers` varchar(30) DEFAULT NULL,
  `username_ger` varchar(40) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `personnel`
--

INSERT INTO `personnel` (`id_personnel`, `nom_pers`, `pren_pers`, `dateNaissPers`, `genre_pers`, `type_pers`, `adresse_pers`, `num_tel_pers`, `email_pers`, `username_ger`) VALUES
(3, 'Khelifa', 'walid', '2001-01-01', 'Homme', 'Serveur', 'New York', '23290972', 'itashi@live.fr', 'a');

-- --------------------------------------------------------

--
-- Structure de la table `personnel_service`
--

CREATE TABLE `personnel_service` (
  `id_perso_service` int(11) NOT NULL,
  `date_service` varchar(10) NOT NULL,
  `id_personnel` int(11) NOT NULL,
  `id_service` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `plat`
--

CREATE TABLE `plat` (
  `id_plat` int(11) NOT NULL,
  `designation` varchar(50) NOT NULL,
  `photo_plat` text NOT NULL,
  `dateAjout` date NOT NULL,
  `categorie` varchar(20) NOT NULL,
  `modeCuisson` varchar(20) DEFAULT NULL,
  `tempsCuisson` varchar(20) DEFAULT NULL,
  `username_ger` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `plat`
--

INSERT INTO `plat` (`id_plat`, `designation`, `photo_plat`, `dateAjout`, `categorie`, `modeCuisson`, `tempsCuisson`, `username_ger`) VALUES
(1, 'Brownies', 'http://192.168.1.3:8888/Pictures/Restaurant/plats/brownie.jpg', '2019-04-01', 'dessert', 'Au four', '20min', 'rania'),
(2, 'Glace à la framboise', 'http://192.168.1.3:8888/Pictures/Restaurant/plats/glace_framboise.jpg', '2019-04-01', 'dessert', 'Aucun', 'Aucun', 'rania'),
(3, 'Pizza végétarienne', 'http://192.168.1.3:80/Pictures/Restaurant/plats/pizzaVegan.jpg', '2019-04-01', 'plat', 'Au four', '30min', 'rania'),
(4, 'Rechta', 'http://192.168.1.3/Pictures/Restaurant/plats/rechta1.jpg', '2019-04-01', 'plat', 'Cocotte minute', '1h', 'rania'),
(5, 'Salade', 'http://192.168.43.225/Pictures/Restaurant/plats/salade.jpg', '2019-04-01', 'entree', 'Aucun', 'Aucun', 'rania'),
(6, 'Salade de riz', 'http://192.168.43.225/Pictures/Restaurant/plats/salade-riz.jpg', '2019-04-01', 'entree', 'Aucun', 'Aucun', 'rania'),
(7, 'Spaghetti', 'http://192.168.43.225/Pictures/Restaurant/plats/spaghetti.jpg', '2019-04-02', 'plat', 'A la poêle', '20min', 'rania'),
(8, 'Humberger', 'http://192.168.43.225/Pictures/Restaurant/plats/hamburger.jpg', '2019-04-02', 'plat', 'Aucun', '20min', 'rania'),
(9, 'Cocacola', 'http://192.168.43.225/Pictures/Restaurant/plats/cocacola.jpg', '2019-04-02', 'boisson', 'Aucun', 'Aucun', 'rania'),
(11, 'Fanta pomme', 'http://192.168.43.225/Pictures/Restaurant/plats/fanta-green-apple-soda.jpg', '2019-04-02', 'boisson', 'Aucun', 'Aucun', 'rania'),
(12, 'Sushi', 'http://192.168.43.225/Pictures/Restaurant/plats/sushi.jpg', '2019-04-02', 'entree', 'Aucun', 'Aucun', 'rania'),
(18, 'Eau Minérale', 'http://192.168.43.225/Pictures/Restaurant/plats/eau_minerale.jpg', '2019-04-30', 'boisson', 'Aucun', 'Aucun', 'rania'),
(19, 'Jus d\'orange', 'http://192.168.43.225/Pictures/Restaurant/plats/jus_d_orange.jpg', '2019-04-30', 'boisson', '', '5min', 'rania'),
(20, 'Café', 'http://192.168.43.225/Pictures/Restaurant/plats/cafe.jpg', '2019-04-30', 'boisson', 'Machine à Expresso', '5min', 'rania'),
(21, 'Milkshake à la fraise', 'http://192.168.43.225/Pictures/Restaurant/plats/milkshake_fraise.jpg', '2019-04-30', 'boisson', 'Aucun', '5min', 'rania'),
(22, 'Thé à la menthe', 'http://192.168.43.225/Pictures/Restaurant/plats/the_menthe.jpg', '2019-04-30', 'boisson', 'Théière ', '5min', 'rania'),
(23, 'Chocolat chaud', 'http://192.168.43.225/Pictures/Restaurant/plats/chocolat_lait.jpg', '2019-04-30', 'boisson', 'Aucun', 'Aucun', 'rania'),
(24, 'Cocktail à la cerise', 'http://192.168.43.225/Pictures/Restaurant/plats/cocktail_cerise.jpg', '2019-04-30', 'boisson', 'Aucun', 'Aucun', 'rania'),
(25, 'Cocacola light', 'http://192.168.43.225/Pictures/Restaurant/plats/cocacola_light.jpg', '2019-04-30', 'boisson', 'Aucun', 'Aucun', 'rania'),
(26, 'Gaufre aux fruits', 'http://192.168.43.225/Pictures/Restaurant/plats/gaufres.jpg', '2019-04-30', 'dessert', 'A la poêle', '20min', 'rania'),
(41, 'Tarte au citron', 'http://192.168.43.225/Pictures/Restaurant/plats/tarte_citron.jpg', '2019-05-01', 'dessert', 'Au four', '1h', 'rania'),
(42, 'Crêpe au chocolat et à la banane', 'http://192.168.43.225/Pictures/Restaurant/plats/crepe_chocolat_banane.jpg', '2019-05-01', 'dessert', 'Crêpière', '20min', 'rania'),
(43, 'Cookies aux noisettes', 'http://192.168.43.225/Pictures/Restaurant/plats/cookies_noisettes.jpg', '2019-05-01', 'dessert', 'Au four', '30min', 'rania'),
(44, 'Tiramisu', 'http://192.168.43.225/Pictures/Restaurant/plats/tiramisu.jpg', '2019-05-01', 'dessert', 'Aucun', 'Aucun', 'rania'),
(45, 'Mousse au chocolat', 'http://192.168.43.225/Pictures/Restaurant/plats/mousse_chocolat.jpg', '2019-05-01', 'dessert', 'Aucun', 'Aucun', 'rania'),
(46, 'Charlotte aux fraises', 'http://192.168.43.225/Pictures/Restaurant/plats/charlotte_fraise.jpg', '2019-05-01', 'dessert', 'Au four', '1h', 'rania'),
(47, 'Cupcake au chocolat noir', 'http://192.168.43.225/Pictures/Restaurant/plats/cupcake_chocolat_noir.jpg', '2019-05-01', 'dessert', 'Au four', '1h', 'rania'),
(48, 'Soupe aux  légumes', 'http://192.168.43.225/Pictures/Restaurant/plats/soupe_legumes.jpg', '2019-05-01', 'entree', 'Cocotte minute', '1h', 'rania'),
(49, 'Soupe de potiron', 'http://192.168.43.225/Pictures/Restaurant/plats/soupe_potiron.jpg', '2019-05-01', 'entree', 'Cocotte minute', '1h', 'rania'),
(50, 'Soupe de poisson', 'http://192.168.43.225/Pictures/Restaurant/plats/soupe_poisson.jpg', '2019-05-01', 'entree', 'Cocotte minute', '1h', 'rania'),
(51, 'Bourak aux crevettes ', 'http://192.168.43.225/Pictures/Restaurant/plats/bourek_crevettes.jpg', '2019-05-01', 'entree', 'A la poêle', '1h', 'rania'),
(52, 'Bourak à la viande hachée', 'http://192.168.43.225/Pictures/Restaurant/plats/bourek.jpg', '2019-05-01', 'entree', 'A la poêle', '1h', 'rania'),
(53, 'Chekchouka', 'http://192.168.43.225/Pictures/Restaurant/plats/chekchouka.jpg', '2019-05-01', 'entree', 'A la poêle', '20min', 'rania'),
(54, 'Lasagnes', 'http://192.168.43.225/Pictures/Restaurant/plats/lasagnes.jpg', '2019-05-01', 'plat', 'Au four', '2h', 'rania'),
(55, 'Frites', 'http://192.168.43.225/Pictures/Restaurant/plats/frites.jpg', '2019-05-06', 'entree', 'Friteuse', '10min', 'rania'),
(56, 'Chawarma', 'http://192.168.43.225/Pictures/Restaurant/plats/chawarma.jpg', '2019-05-06', 'plat', 'A la poêle', '20min', 'rania'),
(57, 'Paella', 'http://192.168.43.225/Pictures/Restaurant/plats/paella2.jpg', '2019-05-07', 'plat', 'A la poêle', '1h', 'rania'),
(58, 'Gratin de poulet aux champignons', 'http://192.168.43.225/Pictures/Restaurant/plats/gratin_poulet.jpg', '2019-05-07', 'plat', 'Au four', '20min', 'rania'),
(59, 'Vol au vent', 'http://192.168.43.225/Pictures/Restaurant/plats/vole_au_vent.jpg', '2019-06-14', 'plat', 'Au four', '20', 'rania'),
(60, 'Aubergines farcies au riz', 'http://192.168.43.225/Pictures/Restaurant/plats/aubergine.jpg', '2019-06-14', 'plat', 'Au four', '30min', 'rania');

-- --------------------------------------------------------

--
-- Structure de la table `plats_commandes`
--

CREATE TABLE `plats_commandes` (
  `id_plat_commande` int(11) NOT NULL,
  `qte` varchar(2) NOT NULL,
  `portion` varchar(10) NOT NULL,
  `id_plat` int(11) NOT NULL,
  `id_commande` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `plats_commandes`
--

INSERT INTO `plats_commandes` (`id_plat_commande`, `qte`, `portion`, `id_plat`, `id_commande`) VALUES
(156, '1', 'medium', 51, 75),
(157, '1', 'medium', 51, 76),
(158, '1', 'medium', 46, 77),
(159, '1', 'medium', 1, 77),
(160, '1', 'medium', 51, 78),
(174, '1', 'medium', 46, 88),
(177, '1', 'medium', 51, 90),
(178, '2', 'large', 55, 90),
(179, '1', 'small', 52, 91),
(180, '1', 'large', 55, 92),
(181, '2', 'small', 51, 93),
(182, '1', 'medium', 56, 93),
(183, '2', 'large', 46, 93),
(184, '1', 'large', 18, 93),
(185, '1', 'large', 5, 94),
(186, '1', 'medium', 53, 95),
(187, '1', 'medium', 53, 96),
(188, '1', 'medium', 52, 97),
(189, '1', 'medium', 51, 98),
(190, '1', 'medium', 52, 98),
(191, '1', 'medium', 46, 98),
(192, '1', 'medium', 52, 99),
(193, '1', 'medium', 55, 99),
(194, '1', 'large', 52, 100),
(195, '3', 'medium', 55, 101),
(196, '2', 'small', 53, 101),
(200, '1', 'medium', 52, 103),
(201, '1', 'medium', 55, 103),
(202, '1', 'medium', 53, 104),
(203, '1', 'medium', 51, 105),
(204, '1', 'medium', 51, 105),
(205, '1', 'medium', 53, 107),
(206, '1', 'medium', 52, 108),
(207, '1', 'medium', 53, 109),
(208, '1', 'medium', 53, 110),
(209, '1', 'medium', 51, 111),
(210, '1', 'medium', 52, 111),
(211, '1', 'medium', 52, 112),
(212, '1', 'medium', 53, 112),
(213, '1', 'medium', 53, 113),
(214, '1', 'medium', 52, 113),
(215, '1', 'medium', 51, 114),
(216, '1', 'medium', 53, 114),
(217, '1', 'large', 51, 115),
(218, '1', 'medium', 53, 116),
(219, '1', 'medium', 53, 117),
(220, '2', 'medium', 55, 118),
(221, '2', 'small', 52, 118),
(222, '1', 'medium', 59, 118),
(223, '2', 'medium', 46, 118),
(224, '1', 'large', 18, 118),
(225, '1', 'medium', 51, 119);

-- --------------------------------------------------------

--
-- Structure de la table `plat_ingredients`
--

CREATE TABLE `plat_ingredients` (
  `id_plat` int(11) NOT NULL,
  `id_ingredient` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `plat_ingredients`
--

INSERT INTO `plat_ingredients` (`id_plat`, `id_ingredient`) VALUES
(1, 1),
(23, 1),
(42, 1),
(43, 1),
(45, 1),
(2, 2),
(20, 2),
(22, 2),
(26, 2),
(41, 2),
(42, 2),
(43, 2),
(44, 2),
(46, 2),
(47, 2),
(1, 3),
(41, 3),
(42, 3),
(43, 3),
(44, 3),
(45, 3),
(47, 3),
(52, 3),
(1, 4),
(26, 4),
(41, 4),
(42, 4),
(43, 4),
(45, 4),
(47, 4),
(1, 5),
(26, 5),
(41, 5),
(42, 5),
(43, 5),
(47, 5),
(2, 6),
(26, 6),
(3, 7),
(6, 7),
(53, 7),
(3, 8),
(7, 8),
(54, 8),
(60, 8),
(3, 9),
(5, 9),
(3, 30),
(7, 30),
(60, 30),
(4, 31),
(4, 32),
(56, 32),
(57, 32),
(58, 32),
(59, 32),
(60, 32),
(4, 33),
(48, 33),
(5, 34),
(8, 34),
(56, 34),
(5, 35),
(6, 35),
(8, 35),
(53, 35),
(56, 35),
(57, 35),
(5, 36),
(6, 36),
(5, 37),
(6, 37),
(49, 37),
(51, 37),
(52, 37),
(58, 37),
(6, 38),
(12, 38),
(57, 38),
(7, 39),
(4, 40),
(8, 40),
(48, 40),
(52, 40),
(56, 40),
(57, 40),
(7, 41),
(8, 41),
(52, 41),
(54, 41),
(7, 42),
(53, 42),
(8, 43),
(56, 43),
(59, 43),
(12, 44),
(12, 45),
(51, 46),
(58, 46),
(59, 46),
(51, 47),
(52, 47),
(48, 48),
(50, 48),
(48, 49),
(50, 49),
(55, 49),
(56, 49),
(48, 50),
(49, 51),
(49, 52),
(54, 53),
(24, 54),
(21, 55),
(26, 55),
(46, 55),
(22, 56),
(43, 57),
(41, 58),
(26, 59),
(42, 59),
(51, 60),
(57, 60),
(50, 61),
(51, 61),
(53, 61),
(56, 63),
(58, 64),
(59, 64),
(60, 64),
(57, 65),
(50, 66),
(57, 66),
(57, 67),
(60, 69),
(59, 70),
(20, 71),
(21, 72),
(23, 72),
(42, 72),
(47, 72),
(19, 73),
(22, 75),
(22, 76),
(55, 77),
(6, 78),
(50, 79),
(50, 80),
(50, 81),
(50, 82),
(3, 83),
(4, 84),
(51, 85),
(52, 85),
(1, 86),
(44, 86),
(44, 87),
(46, 87),
(46, 88),
(43, 89),
(46, 89),
(26, 90),
(43, 90),
(47, 90),
(44, 91),
(47, 91),
(26, 92);

-- --------------------------------------------------------

--
-- Structure de la table `portion`
--

CREATE TABLE `portion` (
  `id_portion` int(11) NOT NULL,
  `portionNom` varchar(10) NOT NULL,
  `portionPrixUni` float NOT NULL,
  `portionCalories` int(5) DEFAULT NULL,
  `id_plat` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `portion`
--

INSERT INTO `portion` (`id_portion`, `portionNom`, `portionPrixUni`, `portionCalories`, `id_plat`) VALUES
(1, 'medium', 150, 610, 1),
(2, 'medium', 150, 270, 2),
(3, 'medium', 500, 609, 3),
(4, 'medium', 500, 250, 4),
(5, 'medium', 200, 230, 5),
(6, 'medium', 200, 160, 6),
(7, 'medium', 250, 175, 7),
(8, 'medium', 250, 752, 8),
(9, 'medium', 90, 220, 9),
(10, 'medium', 90, 210, 11),
(11, 'medium', 1200, 350, 12),
(12, 'small', 120, 224, 1),
(13, 'large', 200, 896, 1),
(14, 'small', 120, 158, 2),
(15, 'large', 200, 350, 2),
(16, 'small', 350, 350, 3),
(17, 'large', 600, 1000, 3),
(18, 'small', 400, 184, 4),
(19, 'large', 600, 570, 4),
(20, 'small', 150, 120, 5),
(21, 'large', 250, 371, 5),
(22, 'small', 150, 100, 6),
(23, 'large', 250, 201, 6),
(24, 'small', 200, 140, 7),
(25, 'large', 300, 364, 7),
(26, 'small', 200, 550, 8),
(27, 'large', 300, 900, 8),
(28, 'small', 85, 145, 9),
(29, 'large', 100, 310, 9),
(30, 'small', 1000, 250, 12),
(31, 'large', 1500, 500, 12),
(32, 'small', 85, 135, 11),
(33, 'large', 100, 300, 11),
(34, 'small', 150, 261, 51),
(35, 'medium', 180, 284, 51),
(36, 'large', 210, 311, 51),
(37, 'small', 150, 250, 52),
(38, 'medium', 180, 294, 52),
(39, 'large', 210, 324, 52),
(41, 'small', 100, 120, 53),
(42, 'medium', 150, 157, 53),
(43, 'large', 200, 180, 53),
(44, 'small', 200, 74, 48),
(45, 'medium', 250, 149, 48),
(46, 'large', 300, 224, 48),
(47, 'small', 200, 40, 49),
(48, 'medium', 250, 80, 49),
(49, 'large', 300, 120, 49),
(50, 'small', 200, 60, 50),
(51, 'medium', 250, 40, 50),
(52, 'large', 300, 31, 50),
(53, 'small', 200, 190, 54),
(54, 'medium', 300, 222, 54),
(55, 'large', 400, 249, 54),
(56, 'small', 50, 6, 20),
(57, 'medium', 75, 10, 20),
(58, 'large', 100, 10, 20),
(59, 'small', 50, 150, 23),
(60, 'medium', 75, 194, 23),
(61, 'large', 100, 288, 23),
(62, 'small', 85, 12, 25),
(63, 'medium', 90, 12, 25),
(64, 'large', 100, 12, 25),
(65, 'medium', 100, 54, 24),
(66, 'large', 150, 70, 24),
(67, 'small', 75, 43, 24),
(68, 'medium', 50, 0, 18),
(69, 'large', 80, 0, 18),
(70, 'small', 30, 0, 18),
(71, 'medium', 100, 47, 19),
(72, 'large', 150, 60, 19),
(73, 'small', 80, 32, 19),
(74, 'medium', 150, 357, 21),
(75, 'large', 200, 422, 21),
(76, 'small', 100, 298, 21),
(77, 'medium', 50, 10, 22),
(78, 'large', 80, 12, 22),
(79, 'small', 30, 6, 22),
(80, 'small', 250, 270, 56),
(81, 'medium', 300, 515, 56),
(82, 'large', 400, 773, 56),
(83, 'small', 200, 94, 46),
(84, 'medium', 250, 187, 46),
(85, 'large', 300, 281, 46),
(86, 'small', 100, 70, 43),
(87, 'medium', 130, 90, 43),
(88, 'large', 160, 111, 43),
(89, 'small', 150, 137, 42),
(90, 'medium', 200, 156, 42),
(91, 'large', 250, 197, 42),
(92, 'small', 100, 100, 47),
(93, 'medium', 130, 196, 47),
(94, 'large', 160, 294, 47),
(95, 'small', 150, 206, 26),
(96, 'medium', 200, 278, 26),
(97, 'large', 250, 310, 26),
(98, 'small', 100, 169, 45),
(99, 'medium', 150, 338, 45),
(100, 'large', 200, 507, 45),
(101, 'small', 100, 291, 41),
(102, 'medium', 150, 322, 41),
(103, 'large', 200, 382, 41),
(104, 'small', 200, 211, 44),
(105, 'medium', 250, 240, 44),
(106, 'large', 300, 246, 44),
(107, 'small', 100, 229, 55),
(108, 'medium', 150, 378, 55),
(109, 'large', 200, 410, 55),
(110, 'small', 200, 300, 59),
(111, 'medium', 300, 320, 59),
(112, 'large', 400, 350, 59),
(113, 'small', 200, 280, 60),
(114, 'medium', 250, 297, 60),
(115, 'large', 300, 341, 60),
(116, 'small', 400, 550, 57),
(117, 'medium', 500, 600, 57),
(118, 'large', 600, 700, 57),
(119, 'small', 200, 450, 58),
(120, 'medium', 300, 500, 58),
(121, 'large', 400, 560, 58);

-- --------------------------------------------------------

--
-- Structure de la table `repas_personnalises`
--

CREATE TABLE `repas_personnalises` (
  `id_repas` int(11) NOT NULL,
  `nom_repas` varchar(20) NOT NULL,
  `id_client` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `repas_personnalises`
--

INSERT INTO `repas_personnalises` (`id_repas`, `nom_repas`, `id_client`) VALUES
(3, 'Déjeuné Habituel', 1),
(4, 'Regime7', 1),
(5, 'Spéciale pizza', 1),
(15, 'Soupes', 1),
(16, 'regime', 27),
(17, 'Racha Benaissa', 1);

-- --------------------------------------------------------

--
-- Structure de la table `repas_personnalise_plats`
--

CREATE TABLE `repas_personnalise_plats` (
  `id_repas` int(11) NOT NULL,
  `qte` varchar(2) NOT NULL,
  `portion` varchar(10) NOT NULL,
  `id_plat` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `repas_personnalise_plats`
--

INSERT INTO `repas_personnalise_plats` (`id_repas`, `qte`, `portion`, `id_plat`) VALUES
(3, '1', 'medium', 5),
(3, '1', 'medium', 12),
(4, '2', 'large', 52),
(4, '3', 'medium', 53),
(5, '1', 'medium', 12),
(15, '1', 'medium', 53),
(16, '2', 'large', 5),
(16, '1', 'medium', 53),
(17, '1', 'medium', 53);

-- --------------------------------------------------------

--
-- Structure de la table `reservation`
--

CREATE TABLE `reservation` (
  `id_reservation` int(11) NOT NULL,
  `date_reserv` varchar(10) NOT NULL,
  `heure_reserv` varchar(8) NOT NULL,
  `date_rdv` varchar(10) NOT NULL,
  `heure_rdv` varchar(8) NOT NULL,
  `etat_reserv` varchar(20) NOT NULL,
  `nb_personnes` varchar(2) NOT NULL,
  `id_table` int(11) NOT NULL,
  `id_client` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `reservation`
--

INSERT INTO `reservation` (`id_reservation`, `date_reserv`, `heure_reserv`, `date_rdv`, `heure_rdv`, `etat_reserv`, `nb_personnes`, `id_table`, `id_client`) VALUES
(4, '19/04/2019', '20:46', '28/04/2019', '21:46', 'annule', '1', 1, 1),
(7, '19/04/2019', '21:27', '20/04/2019', '11:27', 'annule', '1', 1, 1),
(8, '19/04/2019', '21:31', '24/04/2019', '21:31', 'annule', '1', 2, 1),
(11, '02/05/2019', '19:00', '09/05/2019', '19:00', 'annule', '1', 1, 1),
(12, '02/05/2019', '19:01', '09/05/2019', '19:00', 'valide', '1', 9, 1),
(13, '24/05/2019', '23:01', '30/05/2019', '23:01', 'valide', '1', 8, 1),
(14, '09/06/2019', '21:30', '10/06/2019', '12:30', 'annule', '2', 1, 1),
(15, '14/06/2019', '18:42', '15/06/2019', '18:42', 'annule', '1', 1, 1),
(16, '14/06/2019', '18:48', '15/06/2019', '18:48', 'valide', '1', 1, 1),
(17, '14/06/2019', '20:24', '15/06/2019', '20:24', 'attente', '1', 1, 27),
(18, '20/06/2019', '19:32', '21/06/2019', '21:31', 'annule', '4', 4, 1),
(19, '21/06/2019', '22:33', '24/06/2019', '22:33', 'annule', '1', 1, 1),
(20, '25/06/2019', '15:52', '27/06/2019', '11:16', 'annule', '1', 1, 1),
(21, '25/06/2019', '19:40', '30/06/2019', '09:40', 'annule', '1', 1, 1),
(22, '26/06/2019', '20:01', '30/06/2019', '18:52', 'annule', '6', 2, 1),
(23, '26/06/2019', '20:01', '30/06/2019', '18:52', 'attente', '6', 2, 1),
(24, '26/06/2019', '20:01', '27/06/2019', '18:52', 'annule', '6', 2, 1),
(27, '28/06/2019', '20:23', '29/06/2019', '12:23', 'reserve', '1', 1, 1);

-- --------------------------------------------------------

--
-- Structure de la table `restaurant`
--

CREATE TABLE `restaurant` (
  `nom_rest` varchar(20) NOT NULL,
  `photo_rest` text NOT NULL,
  `description` text,
  `num_tel_rest` varchar(20) DEFAULT NULL,
  `email_rest` varchar(30) DEFAULT NULL,
  `horaire_ouv` varchar(8) NOT NULL,
  `horaire_ferm` varchar(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `restaurant`
--

INSERT INTO `restaurant` (`nom_rest`, `photo_rest`, `description`, `num_tel_rest`, `email_rest`, `horaire_ouv`, `horaire_ferm`) VALUES
('Myresto', '', 'My resto est un restaurant connecté venez y jeter un coup doeil !', '023290972', 'myresto@gmail.com', '10:00', '23:00');

-- --------------------------------------------------------

--
-- Structure de la table `service`
--

CREATE TABLE `service` (
  `id_service` int(11) NOT NULL,
  `type_service` varchar(20) DEFAULT NULL,
  `heure_debut` varchar(8) DEFAULT NULL,
  `heure_fin` varchar(8) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `tableconnectee`
--

CREATE TABLE `tableconnectee` (
  `id_table` int(11) NOT NULL,
  `nb_personnes` varchar(1) NOT NULL,
  `dispo` int(1) NOT NULL,
  `caracteristiques` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `tableconnectee`
--

INSERT INTO `tableconnectee` (`id_table`, `nb_personnes`, `dispo`, `caracteristiques`) VALUES
(1, '6', 1, 'Table 1\r\nNombre de personnes : 6\r\n'),
(2, '6', 1, 'Table 2\r\nNombre de personnes : 6'),
(3, '6', 1, 'Table 3\r\nNombre de personnes : 6'),
(4, '6', 1, 'Table 4\r\nNombre de personnes : 6'),
(5, '6', 1, 'Table 5\r\nNombre de personnes : 6'),
(6, '6', 1, 'Table 6\r\nNombre de personnes : 6'),
(7, '4', 1, 'Table 7\r\nNombre de personnes : 4'),
(8, '4', 1, 'Table 8\r\nNombre de personnes : 4'),
(9, '4', 1, 'Table 9\r\nNombre de personnes : 4');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `allergies`
--
ALTER TABLE `allergies`
  ADD PRIMARY KEY (`id_allergie`);

--
-- Index pour la table `allergies_client`
--
ALTER TABLE `allergies_client`
  ADD PRIMARY KEY (`id_client`,`id_ingredient`),
  ADD KEY `FK_ALLERGIE_CLIENT` (`id_ingredient`);

--
-- Index pour la table `avis`
--
ALTER TABLE `avis`
  ADD PRIMARY KEY (`id_avis`),
  ADD KEY `FK_AVIS_CLIENT` (`id_client`);

--
-- Index pour la table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`id_client`);

--
-- Index pour la table `client_ingredients_aimes`
--
ALTER TABLE `client_ingredients_aimes`
  ADD PRIMARY KEY (`id_client`,`id_ingredient`),
  ADD KEY `FK_INGREDIENT_AIMES_CLIENT` (`id_ingredient`);

--
-- Index pour la table `client_ingredients_detestes`
--
ALTER TABLE `client_ingredients_detestes`
  ADD PRIMARY KEY (`id_client`,`id_ingredient`),
  ADD KEY `FK_INGREDIENT_DETESTES_CLIENT` (`id_ingredient`);

--
-- Index pour la table `client_plats_favoris`
--
ALTER TABLE `client_plats_favoris`
  ADD PRIMARY KEY (`id_client`,`id_plat`),
  ADD KEY `FK_PLAT_FAVORIS_CLIENT` (`id_plat`);

--
-- Index pour la table `commande`
--
ALTER TABLE `commande`
  ADD PRIMARY KEY (`id_commande`),
  ADD KEY `FK_CLIENT_COMMANDE` (`id_client`);

--
-- Index pour la table `gerant`
--
ALTER TABLE `gerant`
  ADD PRIMARY KEY (`username_ger`),
  ADD KEY `FK_GERANT_RESTAURANT` (`nom_rest`);

--
-- Index pour la table `ingredient`
--
ALTER TABLE `ingredient`
  ADD PRIMARY KEY (`id_ingredient`),
  ADD KEY `Fk_ingredient_allergie` (`id_allergie`);

--
-- Index pour la table `maladies`
--
ALTER TABLE `maladies`
  ADD PRIMARY KEY (`id_maladie`);

--
-- Index pour la table `maladies_client`
--
ALTER TABLE `maladies_client`
  ADD PRIMARY KEY (`id_client`,`id_maladie`),
  ADD KEY `FK_MALADIE_CLIENT` (`id_maladie`);

--
-- Index pour la table `maladie_ingredients`
--
ALTER TABLE `maladie_ingredients`
  ADD PRIMARY KEY (`id_ingredient`,`id_maladie`),
  ADD KEY `FK_MALADIE_INGREDIENT` (`id_maladie`);

--
-- Index pour la table `personnel`
--
ALTER TABLE `personnel`
  ADD PRIMARY KEY (`id_personnel`),
  ADD KEY `FK_PERSONNEL_GERANT` (`username_ger`);

--
-- Index pour la table `personnel_service`
--
ALTER TABLE `personnel_service`
  ADD PRIMARY KEY (`id_perso_service`),
  ADD KEY `FK_PERSONNEL_SERVICE_PERSO` (`id_personnel`),
  ADD KEY `FK_PERSONNEL_SERVICE_SERV` (`id_service`);

--
-- Index pour la table `plat`
--
ALTER TABLE `plat`
  ADD PRIMARY KEY (`id_plat`),
  ADD KEY `FK_GERANT_PLAT` (`username_ger`);

--
-- Index pour la table `plats_commandes`
--
ALTER TABLE `plats_commandes`
  ADD PRIMARY KEY (`id_plat_commande`),
  ADD KEY `FK_PLAT_COMMANDE` (`id_plat`),
  ADD KEY `FK_COMMANDE_PLAT` (`id_commande`);

--
-- Index pour la table `plat_ingredients`
--
ALTER TABLE `plat_ingredients`
  ADD PRIMARY KEY (`id_plat`,`id_ingredient`),
  ADD KEY `FK_INGREDIENT_PLAT` (`id_ingredient`);

--
-- Index pour la table `portion`
--
ALTER TABLE `portion`
  ADD PRIMARY KEY (`id_portion`),
  ADD KEY `FK_PLAT_PORTION` (`id_plat`);

--
-- Index pour la table `repas_personnalises`
--
ALTER TABLE `repas_personnalises`
  ADD PRIMARY KEY (`id_repas`),
  ADD KEY `FK_REPAS_PERSONNALISES_CLIENT` (`id_client`);

--
-- Index pour la table `repas_personnalise_plats`
--
ALTER TABLE `repas_personnalise_plats`
  ADD PRIMARY KEY (`id_repas`,`id_plat`),
  ADD KEY `FK_PLAT_REPAS_PERSONALISES` (`id_plat`);

--
-- Index pour la table `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`id_reservation`),
  ADD KEY `FK_CLIENT_TABLE` (`id_table`),
  ADD KEY `FK_CLIENT_RESERVATION` (`id_client`);

--
-- Index pour la table `restaurant`
--
ALTER TABLE `restaurant`
  ADD PRIMARY KEY (`nom_rest`);

--
-- Index pour la table `service`
--
ALTER TABLE `service`
  ADD PRIMARY KEY (`id_service`);

--
-- Index pour la table `tableconnectee`
--
ALTER TABLE `tableconnectee`
  ADD PRIMARY KEY (`id_table`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `avis`
--
ALTER TABLE `avis`
  MODIFY `id_avis` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT pour la table `client`
--
ALTER TABLE `client`
  MODIFY `id_client` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT pour la table `commande`
--
ALTER TABLE `commande`
  MODIFY `id_commande` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=120;

--
-- AUTO_INCREMENT pour la table `ingredient`
--
ALTER TABLE `ingredient`
  MODIFY `id_ingredient` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=94;

--
-- AUTO_INCREMENT pour la table `maladies`
--
ALTER TABLE `maladies`
  MODIFY `id_maladie` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `personnel`
--
ALTER TABLE `personnel`
  MODIFY `id_personnel` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `personnel_service`
--
ALTER TABLE `personnel_service`
  MODIFY `id_perso_service` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `plat`
--
ALTER TABLE `plat`
  MODIFY `id_plat` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=61;

--
-- AUTO_INCREMENT pour la table `plats_commandes`
--
ALTER TABLE `plats_commandes`
  MODIFY `id_plat_commande` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=226;

--
-- AUTO_INCREMENT pour la table `portion`
--
ALTER TABLE `portion`
  MODIFY `id_portion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=122;

--
-- AUTO_INCREMENT pour la table `repas_personnalises`
--
ALTER TABLE `repas_personnalises`
  MODIFY `id_repas` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT pour la table `reservation`
--
ALTER TABLE `reservation`
  MODIFY `id_reservation` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT pour la table `service`
--
ALTER TABLE `service`
  MODIFY `id_service` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `tableconnectee`
--
ALTER TABLE `tableconnectee`
  MODIFY `id_table` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `allergies_client`
--
ALTER TABLE `allergies_client`
  ADD CONSTRAINT `FK_ALLERGIE_CLIENT` FOREIGN KEY (`id_ingredient`) REFERENCES `ingredient` (`id_ingredient`),
  ADD CONSTRAINT `FK_CLIENT_ALLERGIE` FOREIGN KEY (`id_client`) REFERENCES `client` (`id_client`) ON DELETE CASCADE;

--
-- Contraintes pour la table `avis`
--
ALTER TABLE `avis`
  ADD CONSTRAINT `FK_AVIS_CLIENT` FOREIGN KEY (`id_client`) REFERENCES `client` (`id_client`) ON DELETE CASCADE;

--
-- Contraintes pour la table `client_ingredients_aimes`
--
ALTER TABLE `client_ingredients_aimes`
  ADD CONSTRAINT `FK_CLIENT_INGREDIENT_AIMES` FOREIGN KEY (`id_client`) REFERENCES `client` (`id_client`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK_INGREDIENT_AIMES_CLIENT` FOREIGN KEY (`id_ingredient`) REFERENCES `ingredient` (`id_ingredient`) ON DELETE CASCADE;

--
-- Contraintes pour la table `client_ingredients_detestes`
--
ALTER TABLE `client_ingredients_detestes`
  ADD CONSTRAINT `FK_CLIENT_INGREDIENT_DETESTES` FOREIGN KEY (`id_client`) REFERENCES `client` (`id_client`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK_INGREDIENT_DETESTES_CLIENT` FOREIGN KEY (`id_ingredient`) REFERENCES `ingredient` (`id_ingredient`) ON DELETE CASCADE;

--
-- Contraintes pour la table `client_plats_favoris`
--
ALTER TABLE `client_plats_favoris`
  ADD CONSTRAINT `FK_CLIENT_PLAT_FAVORIS` FOREIGN KEY (`id_client`) REFERENCES `client` (`id_client`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK_PLAT_FAVORIS_CLIENT` FOREIGN KEY (`id_plat`) REFERENCES `plat` (`id_plat`) ON DELETE CASCADE;

--
-- Contraintes pour la table `commande`
--
ALTER TABLE `commande`
  ADD CONSTRAINT `FK_CLIENT_COMMANDE` FOREIGN KEY (`id_client`) REFERENCES `client` (`id_client`) ON DELETE SET NULL;

--
-- Contraintes pour la table `gerant`
--
ALTER TABLE `gerant`
  ADD CONSTRAINT `FK_GERANT_RESTAURANT` FOREIGN KEY (`nom_rest`) REFERENCES `restaurant` (`nom_rest`);

--
-- Contraintes pour la table `ingredient`
--
ALTER TABLE `ingredient`
  ADD CONSTRAINT `Fk_ingredient_allergie` FOREIGN KEY (`id_allergie`) REFERENCES `allergies` (`id_allergie`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Contraintes pour la table `maladies_client`
--
ALTER TABLE `maladies_client`
  ADD CONSTRAINT `FK_CLIENT_MALADIE` FOREIGN KEY (`id_client`) REFERENCES `client` (`id_client`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK_MALADIE_CLIENT` FOREIGN KEY (`id_maladie`) REFERENCES `maladies` (`id_maladie`);

--
-- Contraintes pour la table `maladie_ingredients`
--
ALTER TABLE `maladie_ingredients`
  ADD CONSTRAINT `FK_INGREDIENT_MALADIE` FOREIGN KEY (`id_ingredient`) REFERENCES `ingredient` (`id_ingredient`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK_MALADIE_INGREDIENT` FOREIGN KEY (`id_maladie`) REFERENCES `maladies` (`id_maladie`) ON DELETE CASCADE;

--
-- Contraintes pour la table `personnel`
--
ALTER TABLE `personnel`
  ADD CONSTRAINT `FK_PERSONNEL_GERANT` FOREIGN KEY (`username_ger`) REFERENCES `gerant` (`username_ger`) ON DELETE CASCADE;

--
-- Contraintes pour la table `personnel_service`
--
ALTER TABLE `personnel_service`
  ADD CONSTRAINT `FK_PERSONNEL_SERVICE_PERSO` FOREIGN KEY (`id_personnel`) REFERENCES `personnel` (`id_personnel`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_PERSONNEL_SERVICE_SERV` FOREIGN KEY (`id_service`) REFERENCES `service` (`id_service`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `plat`
--
ALTER TABLE `plat`
  ADD CONSTRAINT `FK_GERANT_PLAT` FOREIGN KEY (`username_ger`) REFERENCES `gerant` (`username_ger`);

--
-- Contraintes pour la table `plats_commandes`
--
ALTER TABLE `plats_commandes`
  ADD CONSTRAINT `FK_COMMANDE_PLAT` FOREIGN KEY (`id_commande`) REFERENCES `commande` (`id_commande`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK_PLAT_COMMANDE` FOREIGN KEY (`id_plat`) REFERENCES `plat` (`id_plat`) ON DELETE CASCADE;

--
-- Contraintes pour la table `plat_ingredients`
--
ALTER TABLE `plat_ingredients`
  ADD CONSTRAINT `FK_INGREDIENT_PLAT` FOREIGN KEY (`id_ingredient`) REFERENCES `ingredient` (`id_ingredient`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK_PLAT_INGREDIENT` FOREIGN KEY (`id_plat`) REFERENCES `plat` (`id_plat`);

--
-- Contraintes pour la table `portion`
--
ALTER TABLE `portion`
  ADD CONSTRAINT `FK_PLAT_PORTION` FOREIGN KEY (`id_plat`) REFERENCES `plat` (`id_plat`) ON DELETE CASCADE;

--
-- Contraintes pour la table `repas_personnalises`
--
ALTER TABLE `repas_personnalises`
  ADD CONSTRAINT `FK_REPAS_PERSONNALISES_CLIENT` FOREIGN KEY (`id_client`) REFERENCES `client` (`id_client`) ON DELETE CASCADE;

--
-- Contraintes pour la table `repas_personnalise_plats`
--
ALTER TABLE `repas_personnalise_plats`
  ADD CONSTRAINT `FK_PLAT_REPAS_PERSONALISES` FOREIGN KEY (`id_plat`) REFERENCES `plat` (`id_plat`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK_REPAS_PERSONALISES_PLATS` FOREIGN KEY (`id_repas`) REFERENCES `repas_personnalises` (`id_repas`) ON DELETE CASCADE;

--
-- Contraintes pour la table `reservation`
--
ALTER TABLE `reservation`
  ADD CONSTRAINT `FK_CLIENT_RESERVATION` FOREIGN KEY (`id_client`) REFERENCES `client` (`id_client`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK_CLIENT_TABLE` FOREIGN KEY (`id_table`) REFERENCES `tableconnectee` (`id_table`);

DELIMITER $$
--
-- Évènements
--
CREATE DEFINER=`root`@`localhost` EVENT `maj_reservations` ON SCHEDULE EVERY 1 HOUR STARTS '2019-06-14 16:38:20' ON COMPLETION PRESERVE ENABLE DO UPDATE reservation 
set etat_reserv='valide'
WHERE etat_reserv='reserve' and str_to_date(date_rdv,'%d/%m/%Y') <= CURDATE()$$

DELIMITER ;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
