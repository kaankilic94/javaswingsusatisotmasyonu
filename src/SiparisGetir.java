/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Java_sabah
 */
public class SiparisGetir {

    ArrayList<Siparis> ls = new ArrayList<>();

    public void SiparisleriGetir() {

        ls.clear();
        DB db = new DB();
        try {
            String query = "select *from siparisler";
            ResultSet rs = db.baglan().executeQuery(query);
            while (rs.next()) {
                Siparis ks = new Siparis();
                ks.setId(rs.getString("" + MEnum.sid));
                ks.setAdi(rs.getString("" + MEnum.smusteriadi));
                ks.setSoyadi(rs.getString("" + MEnum.smusterisoyadi));
                ks.setDurum(rs.getString(""+MEnum.sdurum));
                ks.setAdres(rs.getString("" + MEnum.sadres));
                ks.setTel(rs.getString("" + MEnum.stelefon));
                ks.setTutar(rs.getString("" + MEnum.stutar));

                ls.add(ks);

            }
        } catch (Exception e) {
            System.err.println("Data Getirme Hatası :" + e);
        }finally{
            db.kapat();
        }
        System.out.println("----------------------");
        //table model oluşturuluyor
        

    }

    public DefaultTableModel siparisListele() {
        SiparisleriGetir();
        DefaultTableModel dtm = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }            
        };
        //sutun bilgisi
        dtm.addColumn("id");
        dtm.addColumn("İsim");
        dtm.addColumn("Soyisim");
        dtm.addColumn("Durum");
        dtm.addColumn("Adres");
        dtm.addColumn("Telefon");
        dtm.addColumn("Tutar");
        String durum="";
        for (Siparis l : ls) {
            if (l.getDurum().equals("0")) {
                durum = "Hazırlanıyor";
            }else if(l.getDurum().equals("1")){
                durum = "Yolda";
            }else if(l.getDurum().equals("2")){
                durum = "Teslim Edildi";
            }
            dtm.addRow(new String[]{l.getId(), l.getAdi(), l.getSoyadi(),durum, l.getAdres(),l.getTel(), l.getTutar()});
            durum = "";

        }
        return(dtm);

    }
}
