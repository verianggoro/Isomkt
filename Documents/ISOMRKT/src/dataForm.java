/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author cahndeso
 */


import ISOMKT.koneksi;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class dataForm extends javax.swing.JFrame {
dataFormDao df;
    /**
     * Creates new form Data_Siswa
     */
    
        
         

    /**
     * Creates new form dataForm
     */
    public dataForm() {
        df = new dataFormDao(); //create/ 
        df.getConnection();
        
        initComponents();
        setLocationRelativeTo(null);
        viewAll();
        
    }
    
    public void viewAll(){
        
        try {
            
            Statement statement =df.connection.createStatement(); 
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*)FROM dataform");// memanggil Resulset dari library dg var resulset dg nilai query utk mengetahui jml baris yg ada di table
             
            resultSet.next(); //melihat baris berikutnya 
            int rows = resultSet.getInt(1); //akan berisi jml baris dari table db
            Object[]header = {"Nama Staff Market","Nama Manager","No. Dok. Log","No. Dok Status","No. Dok Keluhan","Tanggal","Revisi"};
            Object[][]data =new Object [rows][7]; // 7 adalah jml kolom . jml rows dikdeklarasikan 1
            resultSet = statement.executeQuery("SELECT * FROM dataform ORDER BY nama_staf_market");
            resultSet.beforeFirst();
            int i =0; //variable indeks
            while(resultSet.next()){ //selama reulset masin memiliki data dan while bernilai true maka memasukkan data dalam array data 
            data[i][0]=resultSet.getString(1);
            data[i][1]=resultSet.getString(2);
            data[i][2]=resultSet.getString(3);
            data[i][3]=resultSet.getString(4);
            data[i][4]=resultSet.getString(5);
            data[i][5]=resultSet.getString(6);
            data[i][6]=resultSet.getString(7);
            
            i++;
        }
            DefaultTableModel tablemodel = new DefaultTableModel(data,header);// membuat kelas DefaultTableModel dari library dan objek kelas tablemodel
            //Tabel model berfungsi menampikan data dari table database ke jtabel(tabel java) form
            mytable.setModel(tablemodel); // memberikan nilai tableviw(jtabel) melalui objek kelas tablemodel yg sudah dibuat
            //utk bisa menampilkanya buatlah method viewALL() di construktor
        } catch (SQLException ex) {
            Logger.getLogger(dataForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        } //sampai sini pembuatan tablemodel yg ada dlm method viewALL selesai .data dari table db  sudah bisa dimasukan dalam jtable form
    
    // mthod ini digunakan utk menampilkan data dari jtable ke masing2 textfield(nim,nama,dll)
    protected void getSelectedData(int row){ //parameter (int row) utk memilih data dari baris mana yg akan  ditampilkan ke textfield
        txtNamaStafMarketing.setText(mytable.getValueAt(row,0).toString());// mmbri nilai textNim dri tableview dg cara mengambil nilai dari (indeks 0 = kolom ke 1) di table mahasisiwadb lalu mngbah Objek(array) kestring 
        txtnamaManagerMarketing.setText(mytable.getValueAt(row,1).toString());
        txt_no_DokumenLogBook.setText(mytable.getValueAt(row,2).toString());
        //txtDocumentStatusPO.setSelectedItem(mytable.getValueAt(row,3));
        txtDocumentStatusPO.setText(mytable.getValueAt(row,3).toString());
        txtDokumenKeluhan.setText(mytable.getValueAt(row,4).toString());
        txtTanggal.setText(mytable.getValueAt(row,5).toString());
        txtRevis.setText(mytable.getValueAt(row,6).toString());
        
        
    }//utk manggil method ini klk kanan  jtable>mouse clicked ketikkan kode getSelectedData(tableview.getSelectedRow());
    
    //membuat method insert untuk memasukan data dari masing2 komponen text field yg ada di  kolom ke objek mahasiswadao lalu ke table mahasiswa lalu ditampilkan ke jtable
    //method ini digunakan utk memfungsikan tombol jbuton yg diberi nama Save
    protected void insert(){ 
        df.setNama_staf_market(txtNamaStafMarketing.getText()); //memberikan nilai nama ke table data_siswa dari textfield Nim ke objek mahasiswadao
        df.setNama_manager(txtnamaManagerMarketing.getText());
        df.setNo_dokumen_log(txt_no_DokumenLogBook.getText());
        //df.setNo_dokumen_statuspo(cmbjk.getSelectedItem().toString()); //combo merupakan objek jd harus dirubah kestring dulu
        df.setNo_dokumen_statuspo(txtDocumentStatusPO.getText());
        df.setNo_dok_keluhan(txtDokumenKeluhan.getText());
        df.setTanggal(txtTanggal.getText());
        df.setRevisi(txtRevis.getText());
        
        
        //data_siswaDao.setPos(Integer.parseInt(txtPOs.getText()));
        //sekarang objek mahasiwadao sudah terisi data dari masing2 variable(nim,nama,jurusan,alamat,email,telepon) tinggal memasukan ke table mahasiswa yg ada di db
        df.insertData(); //memanggil methode insertdataData yg sdh dibuat dimahasiswadao utk memasukan/save nilai dari masing2 textfield kedlm table mahasiwa db
        viewAll(); //memanggil metode viewALL yg berisi objek kelas tablemodel yg dpt menampilkan table mahasiswa db ke jtabel form
    }// method insert sudah jadi utk menggunakanya klik 2x pada tombol Save kemudian tulis kode insert();
    //utk mengujinya . silahkan di RUn masukan data di masing2 kolom  textfiel(nim, nama,dll) kemudian klik tombol Save maka data akan tersimpan
    
    protected void update(){ //method ini fungsinya utk merubah data langkah2 kurang lebih sama dg methode insert();
        df.setNama_staf_market(txtNamaStafMarketing.getText()); //memberikan nilai nama ke table data_siswa dari textfield Nim ke objek mahasiswadao
        df.setNama_manager(txtnamaManagerMarketing.getText());
        df.setNo_dokumen_log(txt_no_DokumenLogBook.getText());
        //df.setNo_dokumen_statuspo(cmbjk.getSelectedItem().toString()); //combo merupakan objek jd harus dirubah kestring dulu
        df.setNo_dokumen_statuspo(txtDocumentStatusPO.getText());
        df.setNo_dok_keluhan(txtDokumenKeluhan.getText());
        df.setTanggal(txtTanggal.getText());
        df.setRevisi(txtRevis.getText());
        df.updateData(); //memanggil methode updateData() yg sdh dibuat di data_siswaDao utk merubah/Update nilai dari masing2 textfield kedlm table mahasiwa db
        viewAll();//memanggil metode viewALL yg berisi objek kelas tablemodel yg dpt menampilkan table mahasiswa db ke jtabel form
        //membuat method getSelectedData utk menampilkan nilai dari jtable ke masing2 textfield(nim,nama,dll) saat di click data dari jtabel 
    }// method update sudah jadi utk menggunakanya klik 2x pada tombol Update kemudian tulis kode update();
    //utk mengujinya . silahkan di RUn masukan data di masing2 kolom  textfiel(nim, nama,dll) kemudian klik tombol Save maka data akan tersimpan
    protected void delete(){
        df.setNama_staf_market(txtNamaStafMarketing.getText());      
        df.deleteData(); //memanggil method ini utk menghapus data di table mahasiswa 
        viewAll(); //menampilkan data setelah dihapus ke jtable form
    } //utk mgunakan method delete() klik 2x pd tombol Delete pada desain form lalu ketikkan kode Delete();
    // utk memunculkan pemberitahuan konfirmasi ketkkan kode if(JOptionPane.showConfirmDialog(null,"Apakah Anda yakin akan menghapus data ini?","Hapus data",1)==0) sebelum kode delete();
     
    public void reset(){
    
    //cmbjk.setSelectedIndex(0);
    txtNamaStafMarketing.setText("");
    txtnamaManagerMarketing.setText("");
    txt_no_DokumenLogBook.setText("");
    txtDocumentStatusPO.setText("");
    txtDokumenKeluhan.setText("");
    txtTanggal.setText("");
    txtRevis.setText("");
    
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        mytable = new javax.swing.JTable();
        txtNamaStafMarketing = new javax.swing.JTextField();
        txtnamaManagerMarketing = new javax.swing.JTextField();
        txt_no_DokumenLogBook = new javax.swing.JTextField();
        txtDocumentStatusPO = new javax.swing.JTextField();
        txtDokumenKeluhan = new javax.swing.JTextField();
        txtTanggal = new javax.swing.JTextField();
        txtRevis = new javax.swing.JTextField();
        btn_Simpan = new javax.swing.JButton();
        btn_Hapus = new javax.swing.JButton();
        btn_ubah = new javax.swing.JButton();
        btn_reset = new javax.swing.JButton();
        kembali = new javax.swing.JButton();
        date = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setForeground(java.awt.Color.blue);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logo.jpg"))); // NOI18N

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("APLIKASI MONITORING PROSEDUR MUTU BAGIAN PEMASARAN ");

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("PT TEHNIK BAYU MURNI");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addContainerGap(269, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11)
                        .addGap(0, 14, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel1.setText("Data form dan pegawai");

        jLabel2.setText("Nama Staff Marketing");

        jLabel3.setText("Nama Manager Marketing");

        jLabel4.setText("No. Document Log Book Marketing");

        jLabel5.setText("No. Document Status PO & Kontrak");

        jLabel6.setText("No. Document Keluhan Pelanggan");

        jLabel7.setText("Tanggal Efektif");

        jLabel8.setText("Revisi");

        mytable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Nama Staff", "Nama Manager", "No. Dok. Log", "No. Dok. Status PO", "No.Dok.Keluhan", "Revisi", "Revisi"
            }
        ));
        mytable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mytableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(mytable);

        btn_Simpan.setBackground(new java.awt.Color(255, 255, 255));
        btn_Simpan.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_Simpan.setText("Simpan");
        btn_Simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SimpanActionPerformed(evt);
            }
        });

        btn_Hapus.setBackground(new java.awt.Color(255, 0, 0));
        btn_Hapus.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_Hapus.setText("Hapus");
        btn_Hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_HapusActionPerformed(evt);
            }
        });

        btn_ubah.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_ubah.setText("Ubah");
        btn_ubah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ubahActionPerformed(evt);
            }
        });

        btn_reset.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_reset.setText("Reset");
        btn_reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_resetActionPerformed(evt);
            }
        });

        kembali.setBackground(new java.awt.Color(51, 51, 255));
        kembali.setText("Kembali");
        kembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kembaliActionPerformed(evt);
            }
        });

        date.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                datePropertyChange(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(btn_ubah))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtDocumentStatusPO, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                                    .addComponent(txtDokumenKeluhan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                                    .addComponent(txtRevis, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                                    .addComponent(txtTanggal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                                    .addComponent(txt_no_DokumenLogBook, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(70, 70, 70)
                                        .addComponent(btn_Simpan)
                                        .addGap(18, 18, 18)
                                        .addComponent(btn_Hapus)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btn_reset))))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtNamaStafMarketing, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
                                .addComponent(txtnamaManagerMarketing)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(kembali, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16))))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNamaStafMarketing, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtnamaManagerMarketing, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txt_no_DokumenLogBook, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtDocumentStatusPO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtDokumenKeluhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(txtTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtRevis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Simpan)
                    .addComponent(btn_Hapus)
                    .addComponent(btn_reset))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_ubah)
                    .addComponent(kembali))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mytableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mytableMouseClicked
        // TODO add your handling code here:
        getSelectedData(mytable.getSelectedRow());
    }//GEN-LAST:event_mytableMouseClicked

    private void datePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_datePropertyChange
        // TODO add your handling code here:
        try{
            String tampilan ="dd-MMM-yyyy" ;
            SimpleDateFormat fm = new SimpleDateFormat(tampilan);
            String tanggal = String.valueOf(fm.format( date.getDate()));
            txtTanggal.setText(tanggal);
        }catch(Exception e){}
    }//GEN-LAST:event_datePropertyChange

    private void btn_SimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SimpanActionPerformed
        // TODO add your handling code here:
        insert();
    }//GEN-LAST:event_btn_SimpanActionPerformed

    private void btn_HapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_HapusActionPerformed
        // TODO add your handling code here:
        delete();
    }//GEN-LAST:event_btn_HapusActionPerformed

    private void btn_ubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ubahActionPerformed
        // TODO add your handling code here:
        update();
    }//GEN-LAST:event_btn_ubahActionPerformed

    private void btn_resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_resetActionPerformed
        // TODO add your handling code here:
        reset();
    }//GEN-LAST:event_btn_resetActionPerformed

    private void kembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kembaliActionPerformed
        Menu_utama xx;
        xx =new Menu_utama();
        xx.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_kembaliActionPerformed
    

    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(dataForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dataForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dataForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dataForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new dataForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Hapus;
    private javax.swing.JButton btn_Simpan;
    private javax.swing.JButton btn_reset;
    private javax.swing.JButton btn_ubah;
    private com.toedter.calendar.JDateChooser date;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton kembali;
    private javax.swing.JTable mytable;
    private javax.swing.JTextField txtDocumentStatusPO;
    private javax.swing.JTextField txtDokumenKeluhan;
    private javax.swing.JTextField txtNamaStafMarketing;
    private javax.swing.JTextField txtRevis;
    private javax.swing.JTextField txtTanggal;
    private javax.swing.JTextField txt_no_DokumenLogBook;
    private javax.swing.JTextField txtnamaManagerMarketing;
    // End of variables declaration//GEN-END:variables
}
