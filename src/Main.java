import java.util.ArrayList;
import java.util.List;

class Tren {
    String ad;
    List<Vagon> vagonlar;
}

class Vagon {
    String ad;
    int kapasite;
    int doluKoltukAdet;

    public Vagon(String a, int b, int c) {
    }
}

class RezervasyonIstegi {
    Tren tren;
    int rezervasyonYapilacakKisiSayisi;
    boolean kisilerFarkliVagonlaraYerlestirilebilir;
}

class RezervasyonSonuc {
    boolean rezervasyonYapilabilir;
    List<YerlesimAyrinti> yerlesimAyrinti;
}

class YerlesimAyrinti {
    String vagonAdi;
    int kisiSayisi;
}

public class Main {
    public static void main(String[] args) {

        RezervasyonIstegi rezervasyonIstegi = new RezervasyonIstegi();
        rezervasyonIstegi.tren = new Tren();
        rezervasyonIstegi.tren.ad = "Başkent Ekspres";
        rezervasyonIstegi.tren.vagonlar = new ArrayList<>();
        rezervasyonIstegi.tren.vagonlar.add(new Vagon("Vagon 1", 100, 68));
        rezervasyonIstegi.tren.vagonlar.add(new Vagon("Vagon 2", 90, 50));
        rezervasyonIstegi.tren.vagonlar.add(new Vagon("Vagon 3", 80, 80));
        rezervasyonIstegi.rezervasyonYapilacakKisiSayisi = 3;
        rezervasyonIstegi.kisilerFarkliVagonlaraYerlestirilebilir = true;


        RezervasyonSonuc rezervasyonSonuc = rezervasyonYap(rezervasyonIstegi);


        System.out.println("Rezervasyon Yapılabilir: " + rezervasyonSonuc.rezervasyonYapilabilir);
        for (YerlesimAyrinti ayrinti : rezervasyonSonuc.yerlesimAyrinti) {
            System.out.println("Vagon Adı: " + ayrinti.vagonAdi + ", Kişi Sayısı: " + ayrinti.kisiSayisi);
        }
    }

    static RezervasyonSonuc rezervasyonYap(RezervasyonIstegi istek) {
        RezervasyonSonuc sonuc = new RezervasyonSonuc();
        sonuc.rezervasyonYapilabilir = false;
        sonuc.yerlesimAyrinti = new ArrayList<>();

        Tren tren = istek.tren;
        int kisiSayisi = istek.rezervasyonYapilacakKisiSayisi;
        boolean farkliVagonlaraYerlestirilebilir = istek.kisilerFarkliVagonlaraYerlestirilebilir;

        for (Vagon vagon : tren.vagonlar) {
            int bosKoltukSayisi = vagon.kapasite - vagon.doluKoltukAdet;
            if (bosKoltukSayisi >= kisiSayisi && (farkliVagonlaraYerlestirilebilir || bosKoltukSayisi >= kisiSayisi)) {
                YerlesimAyrinti ayrinti = new YerlesimAyrinti();
                ayrinti.vagonAdi = vagon.ad;
                ayrinti.kisiSayisi = kisiSayisi;
                sonuc.yerlesimAyrinti.add(ayrinti);
                sonuc.rezervasyonYapilabilir = true;
                break;
            }
        }
        return sonuc;
    }
}
