## Online Uçak ve Otobüs Bilet Satış Uygulaması


Bu proje, Patika.Dev & Logo Yazılım Java Spring Boot Bootcamp kapsamında, bitirme projesi olarak hazırlanmıştır.

```
Umutcan Boran Bozkürk
https://github.com/ubb-ubb
umutcanboran@gmail.com
```

Dört farklı mikroservis, birbirleriyle bağlantılı olarak çalışmakta ve isterleri yerine getirmektedir.

Servisler:

- Bilet Servisi (ticket-service): Kullanıcının üye olup login olabileceği, temel bilet satın alma, bilet aratma işlevlerini gerçekleştirebileceği servistir.
- Admin Servisi (ticket-service-admin) : Sadece Admin rolündekilerin kullanabileceği servis. Yeni seyahat oluşturma, seyahat bilgilerini düzenleme ve ulaşma gibi endpointlere sahiptir.
- Bilet Bildirim Servisi (ticket-notification-service): Kullanıcının ticket-service'de yaptığı işlemler sonrası devreye giren ve kullanıcıya mail veya sms gönderen mikroservis. Asenkron çalışmaktadır.
- Bilet Ödeme Servisi (ticket-payment-service): Kullancı bilet satın aldıktan sonra devreye giren, ödeme bilgilerini database'e kaydeden servistir. Senkron çalışmaktadır.

ticket-service API endpointleri ile dokümantasyona [linkten](https://documenter.getpostman.com/view/19776700/VUjMoRFu) ulaşabilirsiniz. 

![Application Chart](https://i.ibb.co/t4N8R0n/final-drawio.png)
