package bp1proje1;
/**
*@file BP1PROJE1
*@description Java programlama dili 2B bir matris de labirent keşif oyunu oluşturmak.
*@assignment 1. Proje
*@date 29.11.23-31.12.23
*@author Rahime Çalık-rahime.calik@stu.fsm.edu.tr
*/

import java.util.Scanner;

public class BP1PROJE1 {

    public static void main(String[] args) {

        char[][] labirent = {
            {'#', '!', '.', '.', 'R', '.', '.', '.', '.', '.', '.', '#', '.', '.', '.'},
            {'.', '.', '#', '.', '.', '.', '#', '.', 'H', '.', '.', '.', '.', '.', '!'},
            {'F', '.', '.', '.', '#', '.', '!', '.', '.', 'R', '.', '.', '#', '#', '.'},
            {'.', '.', '#', '.', '.', '#', '.', '.', '.', '.', 'F', '.', '.', '.', '.'},
            {'.', '!', '.', '.', '#', '.', '#', '.', '#', '.', '.', '#', '.', '.', '.'},
            {'.', '.', 'H', '.', '.', '!', '.', '.', 'H', '.', '.', 'F', '.', '.', 'R'},
            {'#', '#', '#', '#', '.', '.', '#', '.', '.', '.', 'T', '.', '.', '.', 'E'},
            {'.', '.', '#', '.', 'F', '.', '#', '#', '.', '#', '#', '#', '#', '.', '.'},
            {'.', '#', '.', '.', '.', '.', '!', '.', '#', '.', '.', '.', '#', '.', '.'},
            {'.', 'T', 'T', '.', '#', '#', '.', '.', '.', '.', 'T', '.', '.', '.', 'R'},
            {'.', '.', '.', '#', '.', '.', '.', '#', '.', '#', '.', '#', '.', 'T', '.'},
            {'B', '.', '#', '.', '.', '!', '.', '!', '.', '.', '.', '.', '.', '.', '#'},
            {'.', '.', '.', 'F', '!', '.', '.', '.', 'H', '.', '.', 'R', '.', '.', '.'},
            {'.', '.', 'H', '.', '.', '.', '!', '.', '.', '.', '#', '.', '.', '#', '.'},
            {'.', '.', '.', '#', '.', '.', '#', '.', '#', '.', '#', '.', '.', '#', '#'}};

        Scanner input = new Scanner(System.in);
        int adimSayisi = 0; //sadece random değişim için.
        int hamleSayisi = 0; //genel ilerlemem mayındaki hamle azaltmayı ve H bonusunu da kapsıyor.

        int[] baslangicNoktasi = Baslangic(labirent);
        int[] bitisNoktasi = Bitis(labirent);
        int[] sonKonum = baslangicNoktasi;
        int[] bonuslar = new int[4];
        String menu
                = "W (Yukarı ilerleme işlemi)\n"
                + "S (Aşağı ilerleme işlemi)\n"
                + "D (Sağa ilerleme işlemi)\n"
                + "A (Sola ilerleme işlemi)\n"
                + "+ (Bonusları görme işlemi)\n"
                + "E (Çıkış)";
        System.out.println("Menü\n" + menu);

        while (true) {
            
            hareketiYazdir(labirent, sonKonum);
            System.out.println("Hamle Sayısı: " + hamleSayisi);
            System.out.println("Adım Sayısı:" + adimSayisi);
            System.out.println("Oyuncunun Son Konumu: [" + sonKonum[0] + ", " + sonKonum[1] + "]");

            System.out.println("W, A, S, D karakterlerinden birini giriniz ya da bonuslarınızı görmek ve kullanmak için '+' tuşuna basınız. Çıkış yapmak için 'E' tuşuna basın.");

            System.out.println("Bir işlem seçiniz:");
            char secim = Character.toUpperCase(input.next().charAt(0));
            if (secim == 'E') {
                break;
            }

            switch (secim) {
                case 'W':
                    if(sonKonum[0]!= 0){
                    if (labirent[sonKonum[0] - 1][sonKonum[1]] == '#') {
                        Duvar(labirent, bonuslar, sonKonum, input, secim, adimSayisi);
                        break;
                    }
                    }
                    if (sonKonum[0] >0) {
                        System.out.println("W tuşuna basıldı yukarı yöne ilerlediniz");
                        sonKonum[0]--;


                    }if (sonKonum[0] <= 0){
                        System.out.println("Labirent dışına çıktınız tekrar seçim yapınız");
                        adimSayisi--;
                        hamleSayisi--;
                    }
                    break;

                case 'S':
                    if(sonKonum[0]!= labirent.length-1){
                if (labirent[sonKonum[0] + 1][sonKonum[1]] == '#') {
                        Duvar(labirent, bonuslar, sonKonum, input, secim, adimSayisi);
                        break;
                    }
                    }
                    if (sonKonum[0] <= labirent.length - 1) {
                        System.out.println("S tuşuna basıldı aşağı yöne ilerlediniz");
                        sonKonum[0]++;
                    }
                    if (sonKonum[0] > labirent[0].length - 1 || sonKonum[1] > labirent[0].length - 1) {
                        System.out.println("Labirent dışına çıktınız tekrar seçim yapınız");
                        sonKonum[0]--;
                        adimSayisi--;
                        hamleSayisi--;
                    }
                    break;

                case 'D':
                    if(sonKonum[1]!=labirent[0].length-1){
                    if (labirent[sonKonum[0]][sonKonum[1] + 1] == '#') {
                        Duvar(labirent, bonuslar, sonKonum, input, secim, adimSayisi);
                        break;
                    }
                    }
                    if (sonKonum[1] <= labirent[0].length - 1) {
                        System.out.println("D tuşuna basıldı sağa ilerlediniz");
                        sonKonum[1]++;

                        
                    } if (sonKonum[0] > labirent[0].length - 1 || sonKonum[1] > labirent[0].length - 1) {
                    System.out.println("Lütfen labirent dışına çıkmayınız. Tekrar karakter giriniz.");
                    sonKonum[1]--;
                    adimSayisi--;
                hamleSayisi--;
                    }
                    break;

                case 'A':
                    if(sonKonum[1] != 0){
                    if (labirent[sonKonum[0]][sonKonum[1] - 1] == '#') {
                        Duvar(labirent, bonuslar, sonKonum, input, secim, adimSayisi);
                       break;
                    }
                    }
                    if (sonKonum[1] >= 0) {
                        System.out.println("Sola ilerlediniz");
                        sonKonum[1]--;
              
                        
                    } if (sonKonum[0]<0 || sonKonum[1]< 0){
                        System.out.println("Labirent dışına çıktınız tekrar seçim yapınız");
                        sonKonum[1]++;
                        adimSayisi--;
                        hamleSayisi--;
                    }
                    break;

                case '+':
                    System.out.println("Bonuslarınız yazdırılıyor");
                    toplaBonus(labirent, bonuslar, sonKonum);
                    bonusKullanma(labirent, bonuslar, hamleSayisi, input, sonKonum, baslangicNoktasi, adimSayisi);
                    break;
                    
                default:
                    System.out.println("Geçersiz Yön Tuşu!");
                    break;

            }
            
            if (sonKonum[0] == bitisNoktasi[0] && sonKonum[1] == bitisNoktasi[1]) {
                System.out.println("Çıkışa ulaştınız. Oyun bitti, Tebrikler");
                break;
            }

            hamleSayisi++;
            adimSayisi++;
            
            hamleSayisi=Mayın(labirent, sonKonum, bonuslar, hamleSayisi, input);
            toplaBonus(labirent, bonuslar, sonKonum);
            
            if (adimSayisi % 5 == 0 && adimSayisi != 0) {
                System.out.println("Labirent değişti");
                rastgeleDegistirme(labirent);

            }

        }
    }

    public static void hareketiYazdir(char[][] labirent, int[] sonKonum) {
        for (int i = 0; i < labirent.length; i++) {
            for (int j = 0; j < labirent[i].length; j++) {
                if (i == sonKonum[0] && j == sonKonum[1]) {
                    System.out.print("+" + " ");
                } else {
                    System.out.print(labirent[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    public static void duvarMi(char[][] labirent, int[] sonKonum, Scanner input, char seçim) {

        if (seçim == 'D') {
            sonKonum[1]--;
        }
        if (seçim == 'W') {
            sonKonum[0]--;
        }
        if (seçim == 'S') {
            sonKonum[0]++;
        }
        if (seçim == 'A') {
            sonKonum[1]++;
        }

    }

    public static void Duvar(char[][] labirent, int[] bonuslar, int[] sonKonum, Scanner input, char seçim, int adimSayisi) {

        if (bonuslar[1] > 0) {
            System.out.println("Duvara gelindi! Bonus kullanmak ister misiniz? (Y:Yes/N:No)");
            char cevap = Character.toUpperCase(input.next().charAt(0));

            if (cevap == 'Y') {
                duvarMi(labirent, sonKonum, input, seçim);
                System.out.println("Duvarı kaldırdınız!");
                labirent[sonKonum[0]][sonKonum[1]] = '.';
                bonuslar[1]--;
            }
            if (cevap == 'N') {
                System.out.println("Duvar ile karşılaştınız, bonus kullanmadığınız için konumunuz değişmemektedir");
            } else {
                System.out.println("Yanlış karakter girdiniz");
                Duvar(labirent, bonuslar, sonKonum, input, seçim, adimSayisi);
            }

        } else {
            System.out.println("Duvar ile karşılaştınız bonusa sahip değilsiniz, tekrardan seçim yapınız");

        }

    }
    public static int Mayın(char[][] labirent, int[] sonKonum, int[] bonuslar, int hamleSayisi, Scanner input) {
    if (labirent[sonKonum[0]][sonKonum[1]] == '!') {
        if (bonuslar[3] > 0) {
            System.out.println("Mayına gelindi! Bonus kullanmak ister misiniz? (Y:Evet/N:Hayır)");
            char cevap = Character.toUpperCase(input.next().charAt(0));
            if (cevap == 'Y') {
                System.out.println("Mayını çözdünüz!");
                labirent[sonKonum[0]][sonKonum[1]] = '.';
                bonuslar[3]--;
            } else if (cevap == 'N') {
                System.out.println("Mayına rastladınız! Mayın patladı.");
                labirent[sonKonum[0]][sonKonum[1]] = '.';
                hamleSayisi = hamleSayisi + 5;
            } else {
                System.out.println("Geçersiz bir seçim yaptınız.");
                hamleSayisi = Mayın(labirent, sonKonum, bonuslar, hamleSayisi, input);
            }
        } else {
            System.out.println("Mayına rastladınız! Bonusa sahip değilsiniz mayın patladı.");
            labirent[sonKonum[0]][sonKonum[1]] = '.';
            hamleSayisi += 5;
        }
    }
    return hamleSayisi;
}

    public static int[] Baslangic(char[][] labirent) {

        int[] baslangicNoktasi = new int[2];
        for (int i = 0; i < labirent.length; i++) {
            for (int j = 0; j < labirent[i].length; j++) {
                if (labirent[i][j] == 'B') {
                    baslangicNoktasi[0] = i;
                    baslangicNoktasi[1] = j;
                    break;
                }

            }
        }
        return baslangicNoktasi;
    }

    public static int[] Bitis(char[][] labirent) {
        int[] bitisNoktasi = new int[2];
        for (int i = 0; i < labirent.length; i++) {
            for (int j = 0; j < labirent[i].length; j++) {
                if (labirent[i][j] == 'E') {
                    bitisNoktasi[0] = i;
                    bitisNoktasi[1] = j;

                }

            }
        }
        return bitisNoktasi;
    }

    public static void toplaBonus(char[][] labirent, int[] bonuslar, int[] sonKonum) {

        switch (labirent[sonKonum[0]][sonKonum[1]]) {
            case 'T':
            case 't':
                bonuslar[0]++;
                System.out.println("T ile karşılaştınız. Bonuslarınıza eklendi.");
                labirent[sonKonum[0]][sonKonum[1]] = '.';
                break;

            case 'R':
            case 'r':
                bonuslar[1]++;
                System.out.println("R ile karşılaştınız. Bonuslarınıza eklendi.");
                labirent[sonKonum[0]][sonKonum[1]] = '.';
                break;

            case 'H':
            case 'h':
                bonuslar[2]++;
                System.out.println("H ile karşılaştınız. Bonuslarınıza eklendi.");
                labirent[sonKonum[0]][sonKonum[1]] = '.';
                break;

            case 'F':
            case 'f':
                bonuslar[3]++;
                System.out.println("F ile karşılaştınız. Bonuslarınıza eklendi.");
                labirent[sonKonum[0]][sonKonum[1]] = '.';
                break;
        }
        
    }

    public static void bonusKullanma(char[][] labirent, int[] bonuslar, int adımSayısı, Scanner input, int[] sonKonum, int[] baslangicNoktasi, int adimSayisi) {
        System.out.println("Toplam Bonus T: " + bonuslar[0]);
        System.out.println("Toplam Bonus R: " + bonuslar[1]);
        System.out.println("Toplam Bonus H: " + bonuslar[2]);
        System.out.println("Toplam Bonus F: " + bonuslar[3]);
        System.out.println("Bonusları kullanmak istediğiniz karakteri girin (T, R, H, F): ");
        char bonusKarakter = input.next().charAt(0);

        switch (bonusKarakter) {
            case 'T':
            case 't':
                isinlanmaT(labirent, sonKonum, input, bonuslar, baslangicNoktasi, adimSayisi);
                break;
            case 'R':
            case 'r':
                System.out.println("Duvar engeli ile karşılaşmadığınız için bu bonusu kullanamazsınız");
                break;
            case 'H':
            case 'h':
                hamleSayisiAzalt(labirent, bonuslar, sonKonum);
                break;
            case 'F':
            case 'f':
                System.out.println("Mayın engeli ile karşılaşmadığınız için bu bonusu kullanamazsınız");
                break;
            default:
                System.out.println("Geçersiz bonus karakteri!");
                bonusKullanma(labirent, bonuslar, adımSayısı, input, sonKonum, baslangicNoktasi, adimSayisi);
        }
    }

    public static void isinlanmaT(char[][] labirent, int[] sonKonum, Scanner input, int[] bonuslar, int[] baslangicNoktasi, int adimSayisi) {
        while (true) {
            if (bonuslar[0] > 0) {
                System.out.println("Işınlanmak istediğiniz noktanın koordinatlarını girin (x y): ");
                System.out.println("X koordinatını giriniz");
                int isinlanX = input.nextInt();
                System.out.println("Y koordinatını giriniz");
                int isinlanY = input.nextInt();
                if (isinlanX < 0 || isinlanY < 0 || isinlanX >= labirent.length || isinlanY >= labirent[0].length) {
                    System.out.println("Girilen değerler labirentin dışında. ");
                    continue;
                }
                if (labirent[isinlanX][isinlanY] == '!' || labirent[isinlanX][isinlanY] == '#') {
                    System.out.println("oraya ışınlanamazsınız.");
                    continue;
                }
                sonKonum[0] = isinlanX;
                sonKonum[1] = isinlanY;
                bonuslar[0]--;
                break;

            } else {
                System.out.println("T bonusuna sahip değilsiniz");
                adimSayisi--;
                break;
            }
        }
    }

    public static void hamleSayisiAzalt(char[][] labirent, int[] bonuslar, int[] sonKonum) {
        if (bonuslar[2] > 0) {
            System.out.println("Hamle sayısı 2 azaldı.");
            bonuslar[2]--;
            labirent[sonKonum[0]][sonKonum[1]] = '.'; // Bonus kullanıldıktan sonra mevcut konumu temizle
        } else {
            System.out.println("H bonusuna sahip değilsiniz.");
        }
    }

    public static void rastgeleDegistirme(char[][] labirent) {

        for (int i = 0; i < labirent.length; i++) {
            for (int j = 0; j < labirent[i].length; j++) {
                if (labirent[i][j] == 'T' || labirent[i][j] == 'H' || labirent[i][j] == 'R' || labirent[i][j] == 'F') {
                    int iYeni, jYeni;
                    do {
                        iYeni = (int) (Math.random() * labirent.length);
                        jYeni = (int) (Math.random() * labirent[i].length);
                    } while (labirent[iYeni][jYeni] == '#' || labirent[iYeni][jYeni] == '!' || labirent[iYeni][jYeni] == 'B' || labirent[iYeni][jYeni] == 'E' || labirent[iYeni][jYeni] == '+');

                    char temp = labirent[i][j];
                    labirent[i][j] = labirent[iYeni][jYeni];
                    labirent[iYeni][jYeni] = temp;
                }
                if (labirent[i][j] == '!') {
                    int iYeni, jYeni;
                    do {
                        iYeni = (int) (Math.random() * labirent.length);
                        jYeni = (int) (Math.random() * labirent[i].length);
                    } while (labirent[iYeni][jYeni] == '#' || labirent[iYeni][jYeni] == 'T' || labirent[iYeni][jYeni] == 'R' || labirent[iYeni][jYeni] == 'F' || labirent[iYeni][jYeni] == 'H' || labirent[iYeni][jYeni] == 'B' || labirent[iYeni][jYeni] == 'E' || labirent[iYeni][jYeni] == '+');

                    char temp = labirent[i][j];
                    labirent[i][j] = labirent[iYeni][jYeni];
                    labirent[iYeni][jYeni] = temp;
                }

            }

        }
    }

}
