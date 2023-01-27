package BenimCozumum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
public class proje_3 {

  /*
     Bu proje %90 bir onceki projeden alinmistir... Ufak degisiklikler mevcuttur.. ClASS yapisinin sizlere sagladigi kolayligi
     bu proje ile daha iyi anlayacaksiniz... Bu class gerekli kodlamalar yapildiginda video da gorulen sonucun aynisini verecek
     sekilde duzenlenmelidir...
        - not :  Tek degisiklik = Bir onceki proje de her kullanicinin 1 tek hesap numarasi vardi... Burada her musteriye 2 adet
                 hesap tanimlanmasini istiyoruz...
              :  Ayrica musteriye ait hesaplar listelenirken gecen hafta yapilan hatayi giderecek sekilde her kullanicinin kendi banka
              hesaplarinin listelenmesini saglayiniz...
     * Bu Class da KIRMIZI olarak gorulen kodlar sizin tarafinizdan eklenecek kodlar ile calisir hale gelecekler...
     * Sizlerden asagida belirtilen-kullanilmis Class lari ayri ayri olusturmanizi ve asagida tanimli methodlarin icini doldurmanizi bekliyoruz...
     */


    public static void main(String[] args) {

        // Scanner Class tanimlayin...
        Scanner scan = new Scanner(System.in);
        Scanner intOku=new Scanner(System.in);

        // Banka Islemleri (Actions) Class indan bir liste olusturun...4 adet islem tanimlayin "Para_Yatir", "Para_Cek", "Transfer", "Cikis"...
        Actions islem1=new Actions("Para_Yatir");
        Actions islem2=new Actions("Para_Cek");
        Actions islem3=new Actions("Transfer");
        Actions islem4=new Actions("Cikis");
        List<Actions> islemler=new ArrayList<>(Arrays.asList(islem1,islem2,islem3,islem4));



        // Her musteri (Customer) icin 2 adet hesap tanimlayin.. Hesap sinifi hesap numarasi ve hesaptaki para miktarini icermeli...
        Account muteri1Hesap1= new Account("1234",500);
        Account muteri1Hesap2= new Account("3565",1000);
        Account muteri2Hesap1= new Account("7896",5000);
        Account muteri2Hesap2= new Account("5321",520);
        Account muteri3Hesap1= new Account("2456",7600);
        Account muteri3Hesap2= new Account("3647",3000);



        // Banka musterilerini Customer sinifindan objeler olusturarak liste olarak tanimlayin...Customer sinifinda bir musteriye ait
        // username, password ve birden fazla hesap (Account) bulunmalidir...Buna gore bu musterileri burada tanimlayin...

        Customer muteri1=new Customer("Mirzat","970324",Arrays.asList(muteri1Hesap1,muteri1Hesap2));
        Customer muteri2=new Customer("Xirzat","000518",Arrays.asList(muteri2Hesap1,muteri3Hesap2));
        Customer muteri3=new Customer("Tumaris","190715",Arrays.asList(muteri3Hesap1,muteri3Hesap2));
        ArrayList<Customer> musteriler=new ArrayList<>(Arrays.asList(muteri1,muteri2,muteri3));



        // Asagidaki kodda KIRMIZI olarak gorulen yerler sizin olustiracaginiz siniflar ve yukarida tanimlayacaginiz objeler ile
        // normal olarak calisacaktir...

        Account secilenHesap = null;
        Customer aktifKullanici = null;

        while (true){
            System.out.print("Please enter your username: ");
            String username = scan.nextLine();
            System.out.print("Please enter your password: ");
            String password = scan.nextLine();

            // -> Bu methodu asagida hangi kullanicinin giris yaptigini belirlemek maksadiyla kullanacaksiniz, olusturun...
            aktifKullanici =hesapSIfreDoguruLama(musteriler, username, password);

            if (aktifKullanici != null){
                System.out.println("Basarili bir sekilde giris yaptiniz");
                break;
            }
            System.out.println("Sistemde kayitli boyle bir kullanici bulunamadi.. Tekrar deneyin");
        }

        // Actions starts here
        while (true){
            System.out.println("Yapmak istediginiz islemi seciniz...");

            ;
            for (int i = 0; i <islemler.size(); i++) {
                System.out.println(islemler.get(i).islemLer+ " - " + (i+1));
            }
            int secim = scan.nextInt();

            switch (secim){

                case 1: {
                    System.out.println("Lutfen yatirmak istediginiz hesap numarasini giriniz...: ");

                    String yatirmakistenenhesap = null;
                    for (Account a : aktifKullanici.hesaplar) {
                        System.out.println(a.hesapNo);
                        yatirmakistenenhesap = scan.nextLine();

                    }
                    if (hesapNumarasiDogurulama(aktifKullanici, yatirmakistenenhesap) != null) {
                        secilenHesap=hesapNumarasiDogurulama(aktifKullanici, yatirmakistenenhesap);
                        System.out.println("Yatirmak istediginiz baliye giriniz:");


                    } else
                        System.out.println("Hatali giris yaotiniz tekrar deneyiniz.");

                    int yarimakIsteninPara = intOku.nextInt();
                    secilenHesap.bakiye=secilenHesap.bakiye+yarimakIsteninPara;
                    System.out.println("bakiye=" + secilenHesap.bakiye);

                    break;

                    // Para yatirma ozelligini aktif hale getirin...
                    /*
                    Kullaniciya ait hesaplari listeleyerek hangi hesap numarasina para yatirmak istedigini sorun...
                    Kullanicinin sectigi hesap numarasi hatali ise ikaz ederek tekrar basa dondurun...
                    Secim confirmAccountNumber(currentCustomer, chosen); methodu tarafindan dogrulanirsa
                    Ne kadar para yatirmak istedigini sorun...
                    Ve kullanicinin girdisini alarak hesaptaki para miktarini arttirin...
                     */
                }

                case 2: {

                    while (true){
                        // Burada artik sadece o kullaniciya ait banka hesaplari listeleniyor... CLASS kullanmainin faydalari...
                        System.out.println("Lutfen para cekmek istediginiz hesap numarasini giriniz...: ");
                        for (Account a : aktifKullanici.hesaplar) {
                            System.out.println(a.hesapNo);
                        }
                        String cekmekistenilenHsep = scan.next();
                        // confirmAccountNumber() methodunu kullanicinin dogru hesap bilgileri girdigini teyit etmek amacli kullanacaksiniz..

                        if (hesapNumarasiDogurulama(aktifKullanici,cekmekistenilenHsep)== null) {
                            System.out.println("Hatali hesap numarasi girdiniz...");
                            continue;
                        }
                        secilenHesap=hesapNumarasiDogurulama(aktifKullanici,cekmekistenilenHsep);
                        System.out.println("Lutfen cekmek istediginiz para miktarini giriniz...: ");
                        int cekilecekPara = scan.nextInt();


                        // withdraw() methodunu kullanicinin hesabinda yeterli bakiye varsa bakiyesinden paranin dusulmesi,
                        // Yeterli bakiye yoksa kullanicinin uyarilmasi maksadiyla kullanacaksiniz...Olusturun
                        if (yetrliBakiyeVarMi(secilenHesap, cekilecekPara) == false){
                            System.out.println("Yeterli bakiyeniz yook.Lutfen baska islem yapiniz...");
                            break;
                        }
                        else{
                            secilenHesap.bakiye-=cekilecekPara;
                            System.out.println("Lutfen paranizi aliniz");
                            System.out.println("Kalan bakiye"+secilenHesap.bakiye);
                        }
                        break;
                  }
                    break;
                }

                case 3:{
                    System.out.println("Bu islemi su an gerceklestiremiyoruz...");
                    break;
                }

                case 4: System.exit(1);

                default:{
                    System.out.println("Basarili bir secim yapmadiniz...");
             }

            }
        }
    }



    // TODO - Asagidaki 3 methodun return tipini ve icerigini tanimlayin ki yukarida olmasi gerektigi gibi sonuc versinler...

    public static Customer hesapSIfreDoguruLama(ArrayList<Customer> musteriler, String username, String password){
        for (int i = 0; i < musteriler.size(); i++) {
            if (musteriler.get(i).userName.equals(username)&&musteriler.get(i).passWord.equals(password)){
                return musteriler.get(i);
            }
        }
        return null;
    }

    public static Account hesapNumarasiDogurulama(Customer musteriHesaplar, String yatirmakistenenhesap){

        for (int i = 0; i < musteriHesaplar.hesaplar.size(); i++) {
            if (musteriHesaplar.hesaplar.get(i).hesapNo.equals(yatirmakistenenhesap))
                return musteriHesaplar.hesaplar.get(i);
        }
        return null;
    }
    public static boolean yetrliBakiyeVarMi(Account hesaplar, int cekmekIstenenPara){
        if (hesaplar.bakiye>cekmekIstenenPara)
            return true;
        return false;
    }



}