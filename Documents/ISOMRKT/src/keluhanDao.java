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
public class keluhanDao {
Connection connection = null;


String no_register;
String nama_perusahaan;
String tanggal;
String nama_pic;
String isi_keluhan;
String tindak_lanjut;
String nama_proyek;
String no_dokumen_keluhan;
String tanggal_efektif;
String revisi;
String nama_staff;
String nama_manager;

    public String getNo_register() {
        return no_register;
    }

    public void setNo_register(String no_register) {
        this.no_register = no_register;
    }

    public String getNama_perusahaan() {
        return nama_perusahaan;
    }

    public void setNama_perusahaan(String nama_perusahaan) {
        this.nama_perusahaan = nama_perusahaan;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getNama_pic() {
        return nama_pic;
    }

    public void setNama_pic(String nama_pic) {
        this.nama_pic = nama_pic;
    }

    public String getIsi_keluhan() {
        return isi_keluhan;
    }

    public void setIsi_keluhan(String isi_keluhan) {
        this.isi_keluhan = isi_keluhan;
    }

    public String getTindak_lanjut() {
        return tindak_lanjut;
    }

    public void setTindak_lanjut(String tindak_lanjut) {
        this.tindak_lanjut = tindak_lanjut;
    }

    public String getNama_proyek() {
        return nama_proyek;
    }

    public void setNama_proyek(String nama_proyek) {
        this.nama_proyek = nama_proyek;
    }

    public String getNo_dokumen_keluhan() {
        return no_dokumen_keluhan;
    }

    public void setNo_dokumen_keluhan(String no_dokumen_keluhan) {
        this.no_dokumen_keluhan = no_dokumen_keluhan;
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

    public String getNama_staff() {
        return nama_staff;
    }

    public void setNama_staff(String nama_staff) {
        this.nama_staff = nama_staff;
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
    String insertSQL = "INSERT INTO tb_keluhan VALUES (?,?,?,?,?,?,?,?,?,?,?,?)"; //query untuk insert nilai variael (?) akan diisi oleh perintah preparedstatement 
    PreparedStatement statement = null; // membuat objek statement dari kelas preparedstatement dg inisialisasi null

        try {
            statement = connection.prepareStatement(insertSQL); 
            //statement ini mengkoneksikan  preparedstatement yg punya parameter perintah query sql memasukan data dgn tipedata string ke table mahasiswa di database
            //mengisi  data sesuai urutan values
            statement.setString(1,no_register); //1 = indeks parameter =nim .memberi nilai nim dg tipe data string dg peintah statement
            statement.setString(2,nama_perusahaan);
            statement.setString(3,tanggal);
            statement.setString(4,nama_pic);
            statement.setString(5,isi_keluhan);
            statement.setString(6,tindak_lanjut); 
            statement.setString(7,nama_proyek);
            statement.setString(8,no_dokumen_keluhan); //1 = indeks parameter =nim .memberi nilai nim dg tipe data string dg peintah statement
            statement.setString(9,tanggal_efektif);
            statement.setString(10,revisi);
            statement.setString(11,nama_staff);
            statement.setString(12,nama_manager);
            
            
            statement.executeUpdate(); // mengekskusi peintah insert 
            JOptionPane.showMessageDialog(null, "Data Sukses disimpan"); //menampilkan pesan bahwa data berhasil di simpan
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Data Gagal disimpan"+ex.getMessage());//menampilkan pesan jika data gagal di simpan
        }finally{
            try {
                statement.close(); //mengclose statement yg sudah dibuka
            } catch (SQLException ex) {
                Logger.getLogger(keluhanDao.class.getName()).log(Level.SEVERE, null, ex);
            } //smpai dsini selesai mbuat method insertData(); utk menguji buka kelas main dan masukan data dan panggil method ini //smpai dsini selesai mbuat method insertData(); utk menguji buka kelas main dan masukan data dan panggil method ini
}
}

    public void updateData(){
    String updateSQL = "UPDATE tb_keluhan SET nama_perusahaan=?,tanggal=?,nama_pic=?,isi_keluhan=?,tindak_lanjut=?,nama_proyek=?,"
            + "no_dokumen_keluhan=?,tanggal_efektif=?,revisi=?,nama_staff=?,nama_manager=?  WHERE no_register=?"; //query utk update dg nilai var ? krn akn disi dg preparedstatement
    PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(updateSQL);

            statement.setString(1,nama_perusahaan); // sesuaikan dengan urutan di query update
            statement.setString(2,tanggal);
            statement.setString(3,nama_pic);
            statement.setString(4,isi_keluhan);
            statement.setString(5,tindak_lanjut);
            statement.setString(6,nama_proyek);
            statement.setString(7,no_dokumen_keluhan);
            statement.setString(8,tanggal_efektif);
            statement.setString(9,revisi);
            statement.setString(10,nama_staff);
            statement.setString(11,nama_manager);
            statement.setString(12,no_register);
            statement.executeUpdate(); 
            JOptionPane.showMessageDialog(null, "Data Sukses diupdate");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Data Gagal diupdate"+ex.getMessage());
        }finally{
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(keluhanDao.class.getName()).log(Level.SEVERE, null, ex);
            }
}
}//sampai disini methode updateData(); selesai utk uji ke class main kmudian masukkan data yg akan diupdate lalu panggil metod ini

     public void deleteData(){
    String updateSQL = "DELETE FROM tb_keluhan WHERE no_register=?"; //query sql delete 
    PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(updateSQL);
            statement.setString(1, no_register); //hanya satu variabel yaitu nim sesuai dengan query DELETE sql
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Sukses dihapus");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Data Gagal dihapus"+ex.getMessage());
        }finally{
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(keluhanDao.class.getName()).log(Level.SEVERE, null, ex);
            }
}
}
    
}
