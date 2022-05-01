Pentru a utiliza aplicatia este necesar de pornit fisierele compilate pe mai multe dispozitive in aceeasi retea. Pentru acesta:
1. Sa fie instalat Java JDK 1.8
2. In directoriul /src/ se vor compila toate fisierele *.java cu comanda javac *.java
3. Se va porni in cmd/bash fisierul ChatAppCompanion.class cu comanda java ChatAppCompanion
4. Se vor repeta pasii 1-3 pentru alt dispozitiv prin retea (de dorit sa aplicatia sa fie pornita in aceeasi retea, preferabil privata)

Cum are loc transmiterea mesajelor:
1. De pe un dispozitiv se va transmite un broadcast pentru ca utilizatorii sa cunoasca ip adresa utilizatorului activ (Exemplu 192.168.43.255 Mesaj)
Mesajul este delimitat de ip adresa destinatie prin spatiu (ip_adress" "message)
2. Pentru a transmite mesaj privat se va utiliza ip cunoscut in retea a utilizatorilor ce au transmis mesaje broadcast (Exemplu Message public from 192.168.43.2)
Pentru a transmite doar utilizatorului dat se va utiliza "192.168.43.2 mesaj"