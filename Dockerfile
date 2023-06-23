# Hangi imajı kullanıcak, uygulamaları çalışması için gerekli minimum sistem gereksinimlerini içeren
# işletim sistemi imajı.
# Docker JDK imaj adı
FROM azul/zulu-openjdk:17.0.7
# Kendi Uygulamamızı docker imaj içine kopyalıyoruz.
COPY /build/libs/ilkproje-v.0.1.jar app.jar
# uygulamayı çalıştırıyoruz.
ENTRYPOINT ["java","-Xmx16384M","-jar","/app.jar"]