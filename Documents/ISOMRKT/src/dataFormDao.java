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
public class dataFormDao {
Connection connection = null;


String nama_staf_market;
String nama_manager;

String no_dokumen_log;
String no_dokumen_statuspo;
String no_dok_keluhan;
String tanggal;
String revisi;

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

    

    public String getNama_staf_market() {
        return nama_staf_market;
    }

    public void setNama_staf_market(String nama_staf_market) {
        this.nama_staf_market = nama_staf_market;
    }

    public String getNama_manager() {
        return nama_manager;
    }

    public void setNama_manager(String nama_manager) {
        this.nama_manager = nama_manager;
    }

    public String getNo_dokumen_log() {
        return no_dokumen_log;
    }

    public void setNo_dokumen_log(String no_dokumen_log) {
        this.no_dokumen_log = no_dokumen_log;
    }

    public String getNo_dokumen_statuspo() {
        return no_dokumen_statuspo;
    }

    public void setNo_dokumen_statuspo(String no_dokumen_statuspo) {
        this.no_dokumen_statuspo = no_dokumen_statuspo;
    }

    public String getNo_dok_keluhan() {
        return no_dok_keluhan;
    }

    public void setNo_dok_keluhan(String no_dok_keluhan) {
        this.no_dok_keluhan = no_dok_keluhan;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getRevisi() {
        return revisi;
    }

    public void setRevisi(String revisi) {
        this.revisi = revisi;
    }
    
    public void insertData(){
    String insertSQL = "INSERT INTO dataform VALUES (?,?,?,?,?,?,?)"; //query untuk insert nilai variael (?) akan diisi oleh perintah preparedstatement 
    PreparedStatement statement = null; // membuat objek statement dari kelas preparedstatement dg inisialisasi null

        try {
            statement = connection.prepareStatement(insertSQL); 
            //statement ini mengkoneksikan  preparedstatement yg punya parameter perintah query sql memasukan data dgn tipedata string ke table mahasiswa di database
            //mengisi  data sesuai urutan values
            statement.setString(1,nama_staf_market); //1 = indeks parameter =nim .memberi nilai nim dg tipe data string dg peintah statement
            statement.setString(2,nama_manager);
            statement.setString(3,no_dokumen_log);
            statement.setString(4,no_dokumen_statuspo);
            statement.setString(5,no_dok_keluhan);
            statement.setString(6,tanggal); 
            statement.setString(7,revisi);
            
            
            statement.executeUpdate(); // mengekskusi peintah insert 
            JOptionPane.showMessageDialog(null, "Data Sukses disimpan"); //menampilkan pesan bahwa data berhasil di simpan
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Data Gagal disimpan"+ex.getMessage());//menampilkan pesan jika data gagal di simpan
        }finally{
            try {
                statement.close(); //mengclose statement yg sudah dibuka
            } catch (SQLException ex) {
                Logger.getLogger(dataFormDao.class.getName()).log(Level.SEVERE, null, ex);
            } //smpai dsini selesai mbuat method insertData(); utk menguji buka kelas main dan masukan data dan panggil method ini //smpai dsini selesai mbuat method insertData(); utk menguji buka kelas main dan masukan data dan panggil method ini
}
}

    public void updateData(){
    String updateSQL = "UPDATE dataform SET nama_manager=?,no_dokumen_log=?,no_dokumen_statuspo=?,no_dok_keluhan=?,tanggal=?,revisi=?  WHERE nama_staf_market=?"; //query utk update dg nilai var ? krn akn disi dg preparedstatement
    PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(updateSQL);

            statement.setString(1,nama_manager); // sesuaikan dengan urutan di query update
            statement.setString(2,no_dokumen_log);
            statement.setString(3,no_dokumen_statuspo);
            statement.setString(4,no_dok_keluhan);
            statement.setString(5,tanggal);
            statement.setString(6,revisi);
            statement.setString(7,nama_staf_market);
            statement.executeUpdate(); 
            JOptionPane.showMessageDialog(null, "Data Sukses diupdate");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Data Gagal diupdate"+ex.getMessage());
        }finally{
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(dataFormDao.class.getName()).log(Level.SEVERE, null, ex);
            }
}
}//sampai disini methode updateData(); selesai utk uji ke class main kmudian masukkan data yg akan diupdate lalu panggil metod ini

     public void deleteData(){
    String updateSQL = "DELETE FROM dataform WHERE nama_staf_market=?"; //query sql delete 
    PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(updateSQL);
            statement.setString(1, nama_staf_market); //hanya satu variabel yaitu nim sesuai dengan query DELETE sql
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
