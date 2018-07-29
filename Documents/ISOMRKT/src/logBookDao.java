/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author cahndeso
 */
public class logBookDao {
Connection connection = null;


String kode_marketing;
String no_penawaran;
String tanggal;
String nilai_penawaran;
String kualifikasi;
String nama_perusahaan;
String nama_proyek;
String status;
String no_dok_logbook;
String tanggal_efektif;
String revisi;
String nama_staf;
String nama_manager;

    public String getKode_marketing() {
        return kode_marketing;
    }

    public void setKode_marketing(String kode_marketing) {
        this.kode_marketing = kode_marketing;
    }

    public String getNo_penawaran() {
        return no_penawaran;
    }

    public void setNo_penawaran(String no_penawaran) {
        this.no_penawaran = no_penawaran;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getNilai_penawaran() {
        return nilai_penawaran;
    }

    public void setNilai_penawaran(String nilai_penawaran) {
        this.nilai_penawaran = nilai_penawaran;
    }

    public String getKualifikasi() {
        return kualifikasi;
    }

    public void setKualifikasi(String kualifikasi) {
        this.kualifikasi = kualifikasi;
    }

    public String getNama_perusahaan() {
        return nama_perusahaan;
    }

    public void setNama_perusahaan(String nama_perusahaan) {
        this.nama_perusahaan = nama_perusahaan;
    }

    public String getNama_proyek() {
        return nama_proyek;
    }

    public void setNama_proyek(String nama_proyek) {
        this.nama_proyek = nama_proyek;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNo_dok_logbook() {
        return no_dok_logbook;
    }

    public void setNo_dok_logbook(String no_dok_logbook) {
        this.no_dok_logbook = no_dok_logbook;
    }

    public String getTanggal_efektif() {
        return tanggal_efektif;
    }

    public void setTanggal_efektif(String tanggal_efektif) {
        this.tanggal_efektif = tanggal_efektif;
    }

    public String getRevisi() {
        return revisi;
    }

    public void setRevisi(String revisi) {
        this.revisi = revisi;
    }

    public String getNama_staf() {
        return nama_staf;
    }

    public void setNama_staf(String nama_staf) {
        this.nama_staf = nama_staf;
    }

    public String getNama_manager() {
        return nama_manager;
    }

    public void setNama_manager(String nama_manager) {
        this.nama_manager = nama_manager;
    }

    

    public Connection getConnection() {
        String url="jdbc:mysql://localhost:3306/db_kkp";
         String driver = "com.mysql.jdbc.Driver";
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null,"Class tidak ditemukan"+ex.getMessage());
        }
        try {
            connection = DriverManager.getConnection(url, "root","");
            //JOptionPane.showMessageDialog(null,"Selamat datang di Aplikasi TK Kamis");
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null,"Koneksi Gagal"+ex.getMessage());
        }
        return connection;
    }

    

    
    
    public void insertData(){
    String insertSQL = "INSERT INTO tb_logbook VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)"; //query untuk insert nilai variael (?) akan diisi oleh perintah preparedstatement 
    PreparedStatement statement = null; // membuat objek statement dari kelas preparedstatement dg inisialisasi null

        try {
            statement = connection.prepareStatement(insertSQL); 
            //statement ini mengkoneksikan  preparedstatement yg punya parameter perintah query sql memasukan data dgn tipedata string ke table mahasiswa di database
            //mengisi  data sesuai urutan values
            statement.setString(1,kode_marketing); //1 = indeks parameter =nim .memberi nilai nim dg tipe data string dg peintah statement
            statement.setString(2,no_penawaran);
            statement.setString(3,tanggal);
            statement.setString(4,nilai_penawaran);
            statement.setString(5,kualifikasi);
            statement.setString(6,nama_perusahaan); 
            statement.setString(7,nama_proyek);
            statement.setString(8,status);
            statement.setString(9,no_dok_logbook); //1 = indeks parameter =nim .memberi nilai nim dg tipe data string dg peintah statement
            statement.setString(10,tanggal_efektif);
            statement.setString(11,revisi);
            statement.setString(12,nama_staf);
            statement.setString(13,nama_manager);
            
            
            statement.executeUpdate(); // mengekskusi peintah insert 
            JOptionPane.showMessageDialog(null, "Data Sukses disimpan"); //menampilkan pesan bahwa data berhasil di simpan
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Data Gagal disimpan"+ex.getMessage());//menampilkan pesan jika data gagal di simpan
        }finally{
            try {
                statement.close(); //mengclose statement yg sudah dibuka
            } catch (SQLException ex) {
                Logger.getLogger(logBookDao.class.getName()).log(Level.SEVERE, null, ex);
            } //smpai dsini selesai mbuat method insertData(); utk menguji buka kelas main dan masukan data dan panggil method ini //smpai dsini selesai mbuat method insertData(); utk menguji buka kelas main dan masukan data dan panggil method ini
}
}

    public void updateData(){
    String updateSQL = "UPDATE tb_logbook SET no_penawaran=?,tanggal=?,nilai_penawaran=?,kualifikasi=?,nama_perusahaan=?,nama_proyek=?,"
            + "status=?,no_dok_logbook=?,tanggal_efektif=?,revisi=?,nama_staf=?,nama_manager=?  WHERE kode_marketing=?"; //query utk update dg nilai var ? krn akn disi dg preparedstatement
    PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(updateSQL);

            statement.setString(1,no_penawaran); // sesuaikan dengan urutan di query update
            statement.setString(2,tanggal);
            statement.setString(3,nilai_penawaran);
            statement.setString(4,kualifikasi);
            statement.setString(5,nama_perusahaan);
            statement.setString(6,nama_proyek);
            statement.setString(7,status);
            statement.setString(8,no_dok_logbook);
            statement.setString(9,tanggal_efektif);
            statement.setString(10,revisi);
            statement.setString(11,nama_staf);
            statement.setString(12,nama_manager);
            statement.setString(13,kode_marketing);
            statement.executeUpdate(); 
            JOptionPane.showMessageDialog(null, "Data Sukses diupdate");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Data Gagal diupdate"+ex.getMessage());
        }finally{
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(logBookDao.class.getName()).log(Level.SEVERE, null, ex);
            }
}
}//sampai disini methode updateData(); selesai utk uji ke class main kmudian masukkan data yg akan diupdate lalu panggil metod ini

     public void deleteData(){
    String updateSQL = "DELETE FROM tb_logbook WHERE kode_marketing=?"; //query sql delete 
    PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(updateSQL);
            statement.setString(1, kode_marketing); //hanya satu variabel yaitu nim sesuai dengan query DELETE sql
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Sukses dihapus");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Data Gagal dihapus"+ex.getMessage());
        }finally{
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(dataFormDao.class.getName()).log(Level.SEVERE, null, ex);
            }
}
}
    
}
