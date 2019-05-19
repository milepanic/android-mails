# android-mails
Android project for sending messages

Android Projekat

### SplashActivity

uvodi korisnika u aplikaciju tako što mu prikazuje logo aplikacije, ali i proverava da li je uređaj povezan na Wi-Fi ili mobilni internet. U slučaju da je korisnik povezan, nakon 5 sekundi biva prebačen na LoginActivity. Ako korisnik nije povezan na Wi-Fi ili mobinli internet, prikazuje se Toast ili Snack poruka da uređaj nije povezan na internet.

### CreateEmailActivity

pruža korisniku mogućnost da kreira novu email poruku. Korisnik treba da unese potrebne informacije (specificirane modelom aplikacije) nakon čega može da posalje poruku ili da odustane od slanja. Ako korisnik odustane od slanja, poruka se briše. Ova aktivnost sadrži Toolbar sa dva dugmeta send i cancel. Aktivnost sadrži i opciju back gde se korisnik vraća na listu svih poruka, ~~a nedovršena poruka biva automatski sačuvana u folder Drafts ako je korisnik popunio bar jedno polje~~.

### ~~CreateFolderActivity~~

~~aktivnost omogućava korisniku da kreira novi folder sa svojstvima definisanim u modelu. Aktivnost sadrži Toolbar sa opciom save koja čuva podatke o novom folderu i opciju cancel tako da korisnik može da odustane od kreiranja foldera. Takođe sadrži i back dugme kojim se korisnik vraća na aktivnost koja prikazuje listu svih foldera.~~

### EmailsActivity

pruža korisniku uvid u sve email-ove za odabrani korisnički nalog u vidu liste. Svaki element liste treba da sadrži: datum prispeća poruke, kratak sadržaj poruke, predmet poruke i naziv pošiljaoca poruke, tagove koje ta poruka sadrži obojene različitim bojama. Ova aktivnost sadrži Toolbar sa akcijom za kreiranje nove email poruke. Klikom na ovo dugme, korisnik biva prebačen na CreateEmailActivity. Toolbar takođe sadrži i opciju filter gde korisnik može da unese tekst pretrage. U pretragu su uključeni: naslov poruke, sadržaj poruke i tagovi koje poruka sadrži, To, From, CC, BCC. Ova aktivnost sadrži i NavigationDrawer ili NavigationView iz koje korisnik može da izabere opciju pregleda profila - ProfileActivity, izmenu trenurtno aktivnog naloga, podešenja aplikacije - SettingsActivity ~~i pregled svih foldera za aktivan nalog - FoldersActivity~~. Klikom na neku od poruka iz liste, otvara se EmailActivity gde korisnik može da vidi detalje poruke.

### EmailActivity

prikazuje korisniku sadržaj poruke koju je izabrao. Aktivnost sadrži Toolbar koji ima opcije delete gde korisnik može da obriše poruku, replay da odgovori na istu, replay to all odgovor na poruku ali svim učeniscima i forward gde korisnik može da prosledi sadržaj poruke na neku drugu adresu. Aktivnost sadrži opciju back gde se korisnik vraća na aktivnost koja prikazuje sve poruke, ali opciju da se sačuva prilog (save attachment).

### ~~FoldersActivity~~

~~prikazuje korisniku listu svih aktivnih foldera za aktivan nalog u vidu liste. Svaki element liste treba da sadrži naslov foldera, i trenutni broj poruka u tom folderu. Klikom na bilo koji folder otvara se aktivnosti FolderActivity koja prikazuje sadržaj tog foldera i osnovne informacije o tom folderu. Ova aktivnost sadrži Toolbar koji ima akciju new koja otvara CreateFolderActivity i korisink može da kreira novi folder. Aktivnost takođe sadrži NavigationView ili NavigationDrawer gde korisnik može da pređe na druge delove aplikacije. Izborom jednog od foldera iz liste, korisniku se prikazuju sve poruke iz tog foldera.~~

### ~~FolderActivity~~

~~prikazuje sadržaj foldera u vidu liste poruka. Aktivnost sadrži i Toolbar sa opcim edit čijim izborom korisnik može da izmeni podatke o folderu.~~

### SettingsActivity

omogućava korisniku osnovna podešenja aplikaicije. Korisnik može da izabere vreme osvežavanja novih poruka izborom unapred specificiranog intervala. Sadrži opciju sortiranja poruka po datumu (rastuće/opadajuće). Aktivnost sadrži Toolbar i back button čime se korisnik vraća na prethodnu aktivnost.

### ProfileActivity

pruža korisniku informacije o svom profilu definisane modelom. Ova aktivnost sadži Toolbar sa opciom logout na koju korisnik može da se odjavi sa sistema. Izborom ove opcije korisnik biva prebačen na LoginActivity. Sadrži NavigationDrawer ili NavigationView gde korisnik može da pređe na druge delove aplikacije. ~~Ova aktivnost omogućava korisniku da specificira pravila pod kojim će prispele poruke odlaziti u određeni folder~~.

### ~~ContactsActivity~~

~~prikazuje listu svih kontakata za prijavljenog korisnika. Kontakti se prikazuju u vidu liste, a svaki kontakt u listi treba da ima sliku i naziv. Klikom na bilo koji kontakt u listi otvara se nova aktivnost ContactActivity, gde korisnik može da vidi detalje ili izmeni podatke o kontatku. Aktivnost sadrži Toolbar sa opciom new nakon čega se otvara CreateContactActivity. Aktivnost sadrži i NavigationDrawer ili NavigationView gde korisnik može da pređe na druge delove aplikacije.~~

### ~~CreateContactActivity~~

~~korisniku pruža mogućnost da doda novi kontakt popunjavajući podatke specificirane modelom. Aktivnost sadrži Toolbar sa opciom save i cancel kojom korisnik čuva, odnosvno odustaje od dodavanje novog kontakta i vraća se na prethodnu aktivnost.~~

### ~~ContactActivity~~

~~prikazuje inoformacije o pojedinačnom kontaktu. Aktivnost sadrži Toolbar sa opcijom save čijim klikom se radi izmena podataka o kontaktu, i opciju back čime korisnik biva vraćen na prethodnu aktivnost.~~



> Studenti koji zele da brane projekat samostalno ne treba da implementiraju sledece funkcije:
>
> 
>
> **Foldere** i pravila po kojima email se smesta u odredjeni folder
>
> **Kontakte**, dovoljno je da unesete email adresu kome zelite da posaljete poruku
>
> **Drafts**, ako korisnik izabere back prilikom kreiranja email-a cela poruka se brise i ne cuva se nista



###### 10. maj

- [x] Inicijalizovati projekat
- [x] Splash Screen
- [x] Login
- [x] Ostale aktivnosti
- [x] Layout za sve aktivnosti
- [x] Prebacivanje na druge aktivnosti
- [x] Navigation Drawer
- [x] Settings

###### 11. maj

- [x] Modeli
- [x] Adapter za listu mejlova
- [x] Adapter za profil

###### 13. maj

- [x] SplashActivity
- [x] HTTP konekcija
- [x] API

###### 14. maj

- [x] Login
- [x] Prikazivanje podataka ulogovanog korisnika
- [x] HTTP za adaptere
- [x] Novi mail
- [x] Svi mailovi
- [x] Pojedinacni mail

###### 15. maj

- [x] Registracija
- [x] Edit profila
- [ ] Tagovi
- [x] Brisanje maila
- [x] Sortiranje svih mailova po datumu
- [x] Automatsko osvjezavanje svih mailova

###### 17. maj

- [x] Reply na mail
- [x] Reply to all na mail
- [x] Forward mail
- [x] Attachment maila - preko Photos/Camera aplikacije
- [x] Prikaz attachmenta
- [ ] CC i BCC
- [x] Popraviti dizajn

###### 18. maj

- [ ] Poliranje projekta
