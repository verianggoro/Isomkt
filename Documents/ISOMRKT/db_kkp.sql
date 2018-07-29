-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jul 16, 2018 at 08:22 AM
-- Server version: 5.6.20
-- PHP Version: 5.5.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `db_kkp`
--

-- --------------------------------------------------------

--
-- Table structure for table `dataform`
--

CREATE TABLE IF NOT EXISTS `dataform` (
  `nama_staf_market` varchar(25) NOT NULL,
  `nama_manager` varchar(25) NOT NULL,
  `no_dokumen_log` varchar(15) NOT NULL,
  `no_dokumen_statuspo` varchar(15) NOT NULL,
  `no_dok_keluhan` varchar(15) NOT NULL,
  `tanggal` varchar(25) NOT NULL,
  `revisi` varchar(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `dataform`
--

INSERT INTO `dataform` (`nama_staf_market`, `nama_manager`, `no_dokumen_log`, `no_dokumen_statuspo`, `no_dok_keluhan`, `tanggal`, `revisi`) VALUES
('dwi', 'dwi h', 'l-01', 's-01', 'k-01', '16-Jul-2018', '0'),
('eva', 'eva e', 'l-02', 's-02', 'k-03', '10-Jul-2018', '1'),
('suci', 'suci n', 'l-03', 's-03', 'k-03', '23-Jul-2018', '0');

-- --------------------------------------------------------

--
-- Table structure for table `tb_admin`
--

CREATE TABLE IF NOT EXISTS `tb_admin` (
  `id_admin` varchar(3) NOT NULL,
  `username` varchar(252) NOT NULL,
  `password` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_admin`
--

INSERT INTO `tb_admin` (`id_admin`, `username`, `password`) VALUES
('1', 'dwi', '123'),
('2', 'suci', '123');

-- --------------------------------------------------------

--
-- Table structure for table `tb_keluhan`
--

CREATE TABLE IF NOT EXISTS `tb_keluhan` (
  `no_register` varchar(10) NOT NULL,
  `nama_perusahaan` varchar(50) NOT NULL,
  `tanggal` varchar(20) NOT NULL,
  `nama_pic` varchar(50) NOT NULL,
  `isi_keluhan` longtext NOT NULL,
  `tindak_lanjut` varchar(30) NOT NULL,
  `nama_proyek` varchar(50) NOT NULL,
  `no_dokumen_keluhan` varchar(10) NOT NULL,
  `tanggal_efektif` varchar(20) NOT NULL,
  `revisi` varchar(2) NOT NULL,
  `nama_staff` varchar(25) NOT NULL,
  `nama_manager` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_keluhan`
--

INSERT INTO `tb_keluhan` (`no_register`, `nama_perusahaan`, `tanggal`, `nama_pic`, `isi_keluhan`, `tindak_lanjut`, `nama_proyek`, `no_dokumen_keluhan`, `tanggal_efektif`, `revisi`, `nama_staff`, `nama_manager`) VALUES
('r-01', 'pt. maju bersama', '15-Jul-2018', 'budi', 'pembangunan tidak sesuai dengan jangka\nwaktu yang ditentukan', 'percepatan', 'pembangunan infrastruktur', '12345', '2018-07-07', '0', 'dwi', 'dwi h'),
('r-02', 'pt. maju terus', '15-Jul-2018', 'budi', 'pembangunan tidak sesuai dengan jangka\nwaktu yang ditentukan', 'percepatan', 'pembangunan infrastruktur', '12346', '2018-07-07', '2', 'eva', 'eva e'),
('r-03', 'pt. berkah jaya', '15-Jul-2018', 'budi', 'pembangunan tidak sesuai dengan jangka\nwaktu yang ditentukan', 'percepatan', 'pembangunan infrastruktur', '12347', '2018-07-07', '3', 'suci', 'suci n');

-- --------------------------------------------------------

--
-- Table structure for table `tb_kepuasan`
--

CREATE TABLE IF NOT EXISTS `tb_kepuasan` (
  `no_kepuasan` varchar(10) NOT NULL,
  `nama_customer` varchar(30) NOT NULL,
  `nama_perusahaan` varchar(30) NOT NULL,
  `nama_proyek` varchar(50) NOT NULL,
  `tanggal` varchar(20) NOT NULL,
  `saran` longtext NOT NULL,
  `nama_staff` varchar(30) NOT NULL,
  `nama_manager` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_kepuasan`
--

INSERT INTO `tb_kepuasan` (`no_kepuasan`, `nama_customer`, `nama_perusahaan`, `nama_proyek`, `tanggal`, `saran`, `nama_staff`, `nama_manager`) VALUES
('K-945', 'P. Udin', 'PT. Maju  Jaya', 'Proyek Pembangunan', '14-July-2018', 'Pelayanan sangat baik, harap terus ditingkatkan', 'Eva E', 'Eva Emayanti'),
('K-987', 'P. Andi', 'PT. Maju Terus Jaya', 'Proyek Pembangunan', '14-July-2018', 'Pelayanan sangat baik, harap terus ditingkatkan', 'Suchi', 'Suchi Nila W'),
('K-999', 'Si Budi', 'PT. Maju Terus', 'Proyek Pembangunan', '15-July-2018', 'Pelayanan sangat baik, harap terus ditingkatkan', 'Dwi H', 'Dwi Handayani');

-- --------------------------------------------------------

--
-- Table structure for table `tb_logbook`
--

CREATE TABLE IF NOT EXISTS `tb_logbook` (
  `kode_marketing` varchar(10) NOT NULL,
  `no_penawaran` varchar(10) NOT NULL,
  `tanggal` varchar(20) NOT NULL,
  `nilai_penawaran` varchar(15) NOT NULL,
  `kualifikasi` varchar(30) NOT NULL,
  `nama_perusahaan` varchar(50) NOT NULL,
  `nama_proyek` varchar(50) NOT NULL,
  `status` varchar(20) NOT NULL,
  `no_dok_logbook` varchar(15) NOT NULL,
  `tanggal_efektif` varchar(20) NOT NULL,
  `revisi` varchar(2) NOT NULL,
  `nama_staf` varchar(30) NOT NULL,
  `nama_manager` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_logbook`
--

INSERT INTO `tb_logbook` (`kode_marketing`, `no_penawaran`, `tanggal`, `nilai_penawaran`, `kualifikasi`, `nama_perusahaan`, `nama_proyek`, `status`, `no_dok_logbook`, `tanggal_efektif`, `revisi`, `nama_staf`, `nama_manager`) VALUES
('km-01', 'p-123', '15-Jul-2018', '1 M', 'Baik', 'PT. Berkah Jaya', 'Pembangunan Infrasrtuktur', 'Item 1', '123345', '2018-07-07', '1', 'eva', 'eva e'),
('km-02', 'p-144', '06-Jul-2018', '2 M', 'Sangat Baik', 'PT. Berkah Jaya abadi', 'Pembangunan Infrasrtuktur', 'Finishing', 'l-01', '16-Jul-2018', '0', 'dwi', 'dwi h'),
('km-03', 'p-133', '10-Jul-2018', '3 M', 'Sangat Baik', 'PT. Berkah  abadi', 'Pembangunan Infrasrtuktur', 'Dalam Pengerjaan', 'l-03', '23-Jul-2018', '0', 'suci', 'suci n');

-- --------------------------------------------------------

--
-- Table structure for table `tb_statusPO`
--

CREATE TABLE IF NOT EXISTS `tb_statusPO` (
  `kd_proyek` varchar(15) NOT NULL,
  `tgl_spk` varchar(20) NOT NULL,
  `nm_perusahaan` varchar(30) NOT NULL,
  `no_spk` varchar(15) NOT NULL,
  `status` varchar(10) NOT NULL,
  `nm_proyek` varchar(30) NOT NULL,
  `nilai_kontrak` varchar(3) NOT NULL,
  `no_dokumen_statuspo` varchar(15) NOT NULL,
  `revisi` varchar(2) NOT NULL,
  `tanggal` varchar(20) NOT NULL,
  `nama_staf_market` varchar(25) NOT NULL,
  `nama_manager` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_statusPO`
--

INSERT INTO `tb_statusPO` (`kd_proyek`, `tgl_spk`, `nm_perusahaan`, `no_spk`, `status`, `nm_proyek`, `nilai_kontrak`, `no_dokumen_statuspo`, `revisi`, `tanggal`, `nama_staf_market`, `nama_manager`) VALUES
('K-123', '14-Jul-2018', 'PT. ABC', 'SP-123', 'Item 1', 'PEMBANGUNAN', '1 M', '1232', '1', '2018-07-07', 'eva', 'eva e'),
('K-1234', '15-Jul-2018', 'PT. XYZ', 'SP-1234', 'Item 2', 'PEMBANGUNAN', '2 M', '1233', '0', '2018-07-07', 'suci', 'suci n'),
('K-12345', '16-Jul-2018', 'PT. XYZ', 'SP-1234', 'Item 2', 'PEMBANGUNAN', '2 M', '1231', '0', '2018-07-07', 'dwi', 'dwi h');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `dataform`
--
ALTER TABLE `dataform`
 ADD PRIMARY KEY (`no_dokumen_statuspo`);

--
-- Indexes for table `tb_keluhan`
--
ALTER TABLE `tb_keluhan`
 ADD PRIMARY KEY (`no_register`);

--
-- Indexes for table `tb_kepuasan`
--
ALTER TABLE `tb_kepuasan`
 ADD PRIMARY KEY (`no_kepuasan`);

--
-- Indexes for table `tb_logbook`
--
ALTER TABLE `tb_logbook`
 ADD PRIMARY KEY (`kode_marketing`);

--
-- Indexes for table `tb_statusPO`
--
ALTER TABLE `tb_statusPO`
 ADD PRIMARY KEY (`kd_proyek`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
