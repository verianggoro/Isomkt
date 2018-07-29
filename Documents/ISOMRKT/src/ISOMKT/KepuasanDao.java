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
public class KepuasanDao {
Connection connection = null;


String no_kepuasan;
String nama_customer;
String nama_perusahaan;
String nama_proyek;
String tanggal;
String saran;
String nama_staff;
String nama_manager;

    public String getNo_kepuasan() {
        return no_kepuasan;
    }

    public void setNo_kepuasan(String no_kepuasan) {
        this.no_kepuasan = no_kepuasan;
    }

    public String getNama_customer() {
        return nama_customer;
    }

    public void setNama_customer(String nama_customer) {
        this.nama_customer = nama_customer;
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

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getSaran() {
        return saran;
    }

    public void setSaran(String saran) {
        this.saran = saran;
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
    String insertSQL = "INSERT INTO tb_kepuasan VALUES (?,?,?,?,?,?,?,?)"; //query untuk insert nilai variael (?) akan diisi oleh perintah preparedstatement 
    PreparedStatement statement = null; // membuat objek statement dari kelas preparedstatement dg inisialisasi null

        try {
            statement = connection.prepareStatement(insertSQL); 
            //statement ini mengkoneksikan  preparedstatement yg punya parameter perintah query sql memasukan data dgn tipedata string ke table mahasiswa di database
            //mengisi  data sesuai urutan values
            statement.setString(1,no_kepuasan); //1 = indeks parameter =nim .memberi nilai nim dg tipe data string dg peintah statement
            statement.setString(2,nama_customer);
            statement.setString(3,nama_perusahaan);
            statement.setString(4,nama_proyek);
            statement.setString(5,tanggal);
            statement.setString(6,saran); 
            statement.setString(7,nama_staff);
            statement.setString(8,nama_manager); //1 = indeks parameter =nim .memberi nilai nim dg tipe data string dg peintah statement
            
            
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
    String updateSQL = "UPDATE tb_kepuasan SET nama_customer=?,nama_perusahaan=?,nama_proyek=?,tanggal=?,saran=?,nama_staff=?,"
            + "nama_manager=?  WHERE no_kepuasan=?"; //query utk update dg nilai var ? krn akn disi dg preparedstatement
    PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(updateSQL);

            statement.setString(1,nama_customer); // sesuaikan dengan urutan di query update
            statement.setString(2,nama_perusahaan);
            statement.setString(3,nama_proyek);
            statement.setString(4,tanggal);
            statement.setString(5,saran);
            statement.setString(6,nama_staff);
            statement.setString(7,nama_manager);
            statement.setString(8,no_kepuasan);
            
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
    String updateSQL = "DELETE FROM tb_kepuasan WHERE no_kepuasan=?"; //query sql delete 
    PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(updateSQL);
            statement.setString(1, no_kepuasan); //hanya satu variabel yaitu nim sesuai dengan query DELETE sql
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
