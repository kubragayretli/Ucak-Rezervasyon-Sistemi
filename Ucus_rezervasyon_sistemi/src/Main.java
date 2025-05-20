import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

// ========================== Entity Sınıflar ==========================

class Ucak {
    public String model, marka, seriNo, uretimYili, tip;
    public int koltukKapasitesi;
    public boolean aktif;

    public Ucak(String model, String marka, String seriNo, int koltukKapasitesi,
                String uretimYili, String tip, boolean aktif) {
        this.model = model;
        this.marka = marka;
        this.seriNo = seriNo;
        this.koltukKapasitesi = koltukKapasitesi;
        this.uretimYili = uretimYili;
        this.tip = tip;
        this.aktif = aktif;
    }
}

class Lokasyon {
    public String ulke, sehir, havaalani, havaalaniKodu, bolge;
    public boolean aktif;

    public Lokasyon(String ulke, String sehir, String havaalani,
                    String havaalaniKodu, String bolge, boolean aktif) {
        this.ulke = ulke;
        this.sehir = sehir;
        this.havaalani = havaalani;
        this.havaalaniKodu = havaalaniKodu;
        this.bolge = bolge;
        this.aktif = aktif;
    }
}

class Ucus {
    public int id;
    public Lokasyon lokasyon;
    public String saat, ucusTarihi, ucusKodu;
    public double fiyat;
    public Ucak ucak;
    public int rezerveKoltuk = 0;

    public Ucus(int id, Lokasyon lokasyon, String saat, String ucusTarihi, String ucusKodu,
                double fiyat, Ucak ucak) {
        this.id = id;
        this.lokasyon = lokasyon;
        this.saat = saat;
        this.ucusTarihi = ucusTarihi;
        this.ucusKodu = ucusKodu;
        this.fiyat = fiyat;
        this.ucak = ucak;
    }

    public boolean yerVarMi() {
        return rezerveKoltuk < ucak.koltukKapasitesi;
    }

    public void koltukAyir() {
        rezerveKoltuk++;
    }
}

class Rezervasyon {
    public Ucus ucus;
    public String ad, soyad, cinsiyet, email, koltukNo;
    public int yas;

    public Rezervasyon(Ucus ucus, String ad, String soyad, int yas,
                       String cinsiyet, String email, String koltukNo) {
        this.ucus = ucus;
        this.ad = ad;
        this.soyad = soyad;
        this.yas = yas;
        this.cinsiyet = cinsiyet;
        this.email = email;
        this.koltukNo = koltukNo;
    }
}

// ========================== Servis Sınıfı ==========================

class RezervasyonService {
    private List<Ucak> ucaklar = new ArrayList<>();
    private List<Lokasyon> lokasyonlar = new ArrayList<>();
    private List<Ucus> ucuslar = new ArrayList<>();
    private List<Rezervasyon> rezervasyonlar = new ArrayList<>();

    public void ilkVerileriYukle() {
        // Uçaklar
        Ucak u1 = new Ucak("Boeing 737", "Boeing", "SN123", 5, "2015", "Yolcu", true);
        Ucak u2 = new Ucak("Airbus A320", "Airbus", "SN456", 6, "2018", "Yolcu", true);
        Ucak u3 = new Ucak("Embraer E190", "Embraer", "SN789", 4, "2020", "Yolcu", true);
        Ucak u4 = new Ucak("Boeing 787", "Boeing", "SN101", 8, "2022", "Yolcu", true);

        ucaklar.addAll(Arrays.asList(u1, u2, u3, u4));

        // Lokasyonlar
        Lokasyon l1 = new Lokasyon("Türkiye", "İstanbul", "İstanbul Havalimanı", "IST", "Marmara", true);
        Lokasyon l2 = new Lokasyon("Almanya", "Berlin", "Berlin Brandenburg", "BER", "Avrupa", true);
        Lokasyon l3 = new Lokasyon("Fransa", "Paris", "Charles de Gaulle", "CDG", "Avrupa", true);
        Lokasyon l4 = new Lokasyon("İtalya", "Roma", "Leonardo da Vinci", "FCO", "Avrupa", true);
        Lokasyon l5 = new Lokasyon("Türkiye", "Ankara", "Esenboğa Havalimanı", "ESB", "İç Anadolu", true);
        Lokasyon l6 = new Lokasyon("İspanya", "Madrid", "Madrid Barajas", "MAD", "Avrupa", true);

        lokasyonlar.addAll(Arrays.asList(l1, l2, l3, l4, l5, l6));

        // Uçuşlar
        ucuslar.add(new Ucus(1, l1, "08:00", "2025-06-10", "TK1001", 1500.0, u1));
        ucuslar.add(new Ucus(2, l2, "10:30", "2025-06-11", "LH2025", 1800.0, u2));
        ucuslar.add(new Ucus(3, l3, "13:00", "2025-06-12", "AF330", 1750.0, u3));
        ucuslar.add(new Ucus(4, l4, "16:45", "2025-06-13", "AZ500", 1900.0, u4));
        ucuslar.add(new Ucus(5, l5, "18:00", "2025-06-14", "TK1010", 800.0, u1));
        ucuslar.add(new Ucus(6, l6, "20:30", "2025-06-15", "IB600", 1600.0, u2));
        ucuslar.add(new Ucus(7, l1, "23:00", "2025-06-16", "TK2002", 1500.0, u4));
    }

    public void ucuslariListele() {
        System.out.println("\n--- Mevcut Uçuşlar ---");
        for (Ucus u : ucuslar) {
            System.out.println("ID: " + u.id +
                    " | Kod: " + u.ucusKodu +
                    " | Tarih: " + u.ucusTarihi +
                    " | Saat: " + u.saat +
                    " | Lokasyon: " + u.lokasyon.sehir +
                    " | Havalimanı: " + u.lokasyon.havaalani +
                    " | Fiyat: " + u.fiyat + "₺" +
                    " | Uçak: " + u.ucak.model +
                    " | Boş Koltuk: " + (u.ucak.koltukKapasitesi - u.rezerveKoltuk));
        }
    }

    public void rezervasyonYap(int ucusID, String ad, String soyad, int yas,
                               String cinsiyet, String email, String koltukNo) {
        for (Ucus u : ucuslar) {
            if (u.id == ucusID) {
                if (u.yerVarMi()) {
                    u.koltukAyir();
                    Rezervasyon r = new Rezervasyon(u, ad, soyad, yas, cinsiyet, email, koltukNo);
                    rezervasyonlar.add(r);
                    dosyayaYaz(r);
                    System.out.println("\n✅ Rezervasyon başarıyla yapıldı!");
                } else {
                    System.out.println("\n❌ Maalesef uçakta yer kalmamış.");
                }
                return;
            }
        }
        System.out.println("\n❌ Uçuş bulunamadı.");
    }

    private void dosyayaYaz(Rezervasyon r) {
        try (FileWriter writer = new FileWriter("veriler.csv", true)) {
            writer.write(r.ad + "," + r.soyad + "," + r.yas + "," +
                    r.cinsiyet + "," + r.email + "," + r.koltukNo + "," +
                    r.ucus.ucusKodu + "," + r.ucus.ucusTarihi + "," + r.ucus.saat + "," +
                    r.ucus.lokasyon.sehir + "," + r.ucus.fiyat + "\n");
        } catch (IOException e) {
            System.out.println("❌ Dosya yazma hatası: " + e.getMessage());
        }
    }
}

// ========================== Main Metodu ==========================

public class Main {
    public static void main(String[] args) {
        RezervasyonService rs = new RezervasyonService();
        rs.ilkVerileriYukle();

        Scanner scanner = new Scanner(System.in);
        rs.ucuslariListele();

        System.out.print("\n📌 Rezervasyon için uçuş ID giriniz: ");
        int ucusID = scanner.nextInt(); scanner.nextLine();

        System.out.print("Adınız: ");
        String ad = scanner.nextLine();

        System.out.print("Soyadınız: ");
        String soyad = scanner.nextLine();

        System.out.print("Yaşınız: ");
        int yas = scanner.nextInt(); scanner.nextLine();

        System.out.print("Cinsiyet (E/K): ");
        String cinsiyet = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Koltuk No (örn. 1A): ");
        String koltukNo = scanner.nextLine();

        rs.rezervasyonYap(ucusID, ad, soyad, yas, cinsiyet, email, koltukNo);
    }
}
