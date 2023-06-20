# NOTLARIM

## 1. Gün
    application.yml dosyası ile ilgili ayarlar
    https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html

### DB işlemleri
    1- Eğer İlişkisel bir DB kullanacak isek, Spring Data JPA yi gradle içinde eklemeliyiz
    2- Bağlantı yapacağımız DB için gerekli Driver ı gradle içine eklemeliyiz
    3- gerekli bağlantıları application.yml içine yazmalıyız.

    https://drive.google.com/drive/folders/1pb5KPhYAp5aviJ8NgjdA6GpWP6PL2iWZ?usp=sharing

### Swagger-UI için importlar

    Spring Boot Starter 3.0.0 altı için
    // https://mvnrepository.com/artifact/org.springdoc/springdoc-openapi-ui
    implementation group: 'org.springdoc', name: 'springdoc-openapi-ui', version: '1.7.0'

    Spring Boot Starter 3.0.0 üstü için
    // https://mvnrepository.com/artifact/org.springdoc/springdoc-openapi-starter-webmvc-ui
    implementation group: 'org.springdoc', name: 'springdoc-openapi-starter-webmvc-ui', version: '2.1.0'

    http://localhost:8090/swagger-ui/index.html
    
### Http İsteklerinin FArkları

    GET: Veri çekmek için kullanılır. HTML sayfasını görüntülemek için kullanılır.
    Eğer get ile sunucuya parametre iletilmek istenir ise Header üzerinden bilgi 
    gönderilir.
    http:localhost:8090/personel/save?ad=Ahmet&soyad=Kaya  -- internet tarayıcıd istek şekli
    Sorun Nedir?
    verileriniz açık bir şekilde başlıkta iletilir bu nedenle, browser de kalabilir 
    ayrıca sistemi, network ü dinleyen birisi bilgilerinizi çalar.

    POST: Veri kaydetmek için kullanılır. Veri göndermek için kullanılır.
    verilerimiz isteğin body sinde iletilir. daha güvenlidir. 

    