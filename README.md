# ✈️ Java Uçak Bileti Rezervasyon Sistemi

Bu proje, Java Nesneye Dayalı Programlama (OOP) prensiplerine göre geliştirilmiş basit bir **konsol tabanlı uçak bileti rezervasyon sistemidir**.

## 📌 Özellikler

- Uçak bilgileri tanımlanır ve saklanır.
- Lokasyon (ülke, şehir, havaalanı) bilgileri tanımlanır.
- Uçuşlar (uçak, lokasyon, saat, tarih, fiyat) listelenir.
- Kullanıcılar uçuşları görüntüler, uçuş seçimi yapar ve rezervasyon oluşturur.
- Uçağın koltuk kapasitesine göre rezervasyon yapılabilirlik kontrol edilir.
- Yapılan rezervasyonlar `veriler.csv` dosyasına kaydedilir.

## 🧱 Kullanılan Yapılar

- **OOP Yaklaşımları**:
  - `Class` kullanımı
  - Kalıtım gerektirmeyen bağımsız entity yapılar
- **Entity Sınıflar**:
  - `Ucak`
  - `Lokasyon`
  - `Ucus`
  - `Rezervasyon`
- **Service Sınıfı**:
  - `RezervasyonService` → İş mantığı ve listeleme/kaydetme işlevleri

## 🖥️ Kullanım

### 1. Projeyi Derleyin

```bash
javac Main.java

proje/
├── Main.java          → Ana uygulama dosyası
└── veriler.csv        → Rezervasyon bilgilerini tutar (çalışma sırasında oluşur)

Örnek CSV Kaydı:
Kübra,Gayretli,22,K,kubra@example.com,2B,TK1001,2025-06-10,08:00,İstanbul,1500.0


Bu `README.md` dosyasını projenin kök dizinine koyarak GitHub ya da okul sunumlarında kullanabilirsin. Eğer görseller, simülasyon ekran görüntüleri ya da demo videosu da eklersen daha etkileyici olur.

Hazırsan sana proje sunumu için de kısa bir konuşma metni hazırlayabilirim. İster misin?
