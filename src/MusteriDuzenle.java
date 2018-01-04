
import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class MusteriDuzenle{
    
    public boolean duzenle(String id ,String ad, String soyAd, String tel, String adres){
        
      
        if (id.equals("") || ad.equals("") || soyAd.equals("") || tel.equals("") || adres.equals("") ) {
               JOptionPane.showMessageDialog(null, "Lütfen boş alan bırakmayın!");
               return true;
        }else{
             String query = "UPDATE musteriler SET madi = '"+ad+"', msoyadi= '"+soyAd+"', mtelefon= '"+tel+"', madres='"+adres+"'   WHERE mid = '"+id+"'";
        DB db= new DB();
        try {
            int ekle = db.baglan().executeUpdate(query);
            if (ekle>0) {
                JOptionPane.showMessageDialog(null, "Musteri güncellendi!");
                MusteriListele ml = new MusteriListele();
                AnaEkran.tblMusteri.setModel(ml.listeleme());
                
            }
        } catch (Exception e) {
            System.err.println("Update hata: " + e);
        }finally{
            db.kapat();
        }
          
        
        }
        
        return false;
      
        
    }

}
