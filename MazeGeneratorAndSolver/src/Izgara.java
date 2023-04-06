import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class Izgara {
    private int satir, sutun;
    String[] satirLabirent;
    private Engel[][] grids;
    private Engel[][] grids2;
    public int hucreBoyutX = 40;
    public int hucreBoyutY = 40;
    public Engel suanki;
    public Engel siradaki;
    public Engel baslangic, bitis;
    private int boyut;
    public Stack<Engel> yigin = new Stack<Engel>();
    public ArrayList<Engel> ikiNoktaArasiYol;
    Stack<Engel> enKisaYol = new Stack<Engel>();
    private Engel gecici;
    public boolean bitisDegeri = false;
    private Engel nolursun;
    private Engel nolursun2;
    private int arttir = 0;
    private int arttir2 = 0;
    private int arttir3 = 0;
    private int arttir4 = 0;
    private int stackSayac = 0;
    public boolean aktifYol = false;
    Stack<Engel> cikmazli = new Stack<>();
    public boolean dumanlandi = false;
    ArrayList<Engel> duman = new ArrayList<Engel>();
    ArrayList<Engel> komsulariTopla = new ArrayList<Engel>();


    public Izgara(String link) throws IOException {
        URL veri = new URL(link);
        Scanner okuyucu = new Scanner(veri.openConnection().getInputStream());

        while (okuyucu.hasNext()) {
            sutun = okuyucu.nextLine().length();
            satir++;
        }
        hucreBoyutX = (int) 600 / sutun;
        hucreBoyutY = (int) 572 / satir;

        Scanner okuyucu2 = new Scanner(veri.openConnection().getInputStream());
        satirLabirent = new String[satir];
        int satirSay = 0;
        while (okuyucu2.hasNext()) {
            satirLabirent[satirSay] = okuyucu2.nextLine();
            satirSay++;
        }
        boyut = satir * sutun;
        labirentYap();
        ikiNoktaArasiYol = new ArrayList<Engel>();
    }

    public Izgara(int satir, int sutun) {
        this.satir = satir;
        this.sutun = sutun;

        hucreBoyutX = (int) 600 / sutun;
        hucreBoyutY = (int) 572 / satir;

        boyut = satir * sutun;

        labirentYap2();
        ikiNoktaArasiYol = new ArrayList<Engel>();
    }

    public void labirentYap() {
        grids = new Engel[satir][sutun];
        for (int i = 0; i < satir; i++) {
            for (int j = 0; j < sutun; j++) {
                grids[i][j] = new Engel(i, j, hucreBoyutX, hucreBoyutY);
                grids[i][j].hucreDegeri = Character.getNumericValue(satirLabirent[i].charAt(j));
            }
        }
        labirentiYaz();
        baslangicBitis();
    }

    public void labirentYap2() {
        grids = new Engel[satir][sutun];
        for (int i = 0; i < satir; i++) {
            for (int j = 0; j < sutun; j++) {
                grids[i][j] = new Engel(i, j, hucreBoyutX, hucreBoyutY);
                grids[i][j].mod2DegerleriAta();
            }
        }
        baslangicBitis3();
    }


    public void labirentiYaz() {
        for (int i = 0; i < satir; i++) {
            for (int j = 0; j < sutun; j++) {
                System.out.print(grids[i][j].hucreDegeri);
            }
            System.out.println();
        }
    }

    public void labirentiYaz2() {
        for (int i = 0; i < satir; i++) {
            for (int j = 0; j < sutun; j++) {
                System.out.print(grids[i][j].hucreDegeri);
            }
            System.out.println();
        }
    }

    public void haritayiCiz(Graphics g) {
        for (int i = 0; i < satir; i++) {
            for (int j = 0; j < sutun; j++) {
                grids[i][j].mod1HucreCiz(g);
            }
        }
        baslangicBitisCiz(g);
    }

    public void haritayiCiz2(Graphics g) {
        for (int i = 0; i < satir; i++) {
            for (int j = 0; j < sutun; j++) {
                grids[i][j].mod2HucreleriCiz(g);
            }
        }
        baslangicBitisCiz(g);
    }

    public void ekraniDumanla(Graphics g) {
        for (int i = 0; i < duman.size(); i++) {
            duman.get(i).yoluBoya(g, Color.DARK_GRAY);
        }

        for (int i = 0; i < satir; i++) {
            for (int j = 0; j < sutun; j++)
                grids[i][j].cizgileriCiz(g);
        }
    }

    public void dumaniBelirleme() {
        for (int i = 0; i < satir; i++) {
            for (int j = 0; j < sutun; j++) {
                duman.add(grids[i][j]);
            }
        }
    }

    public void baslangicBitis() {
        baslangic = grids[new Random().nextInt(satir)][new Random().nextInt(sutun)];
        bitis = grids[new Random().nextInt(satir)][new Random().nextInt(sutun)];

        if (baslangic == bitis)
            baslangic = grids[new Random().nextInt(satir)][new Random().nextInt(sutun)];

        if (baslangic.hucreDegeri != 0)
            baslangicBitis();
        if (bitis.hucreDegeri != 0)
            baslangicBitis();

        for (int i = 0; i < satir; i++) {
            for (int j = 0; j < sutun; j++) {
                grids[i][j].visited = false;
            }
        }

        suanki = baslangic;
        siradaki = suanki;
    }

    public void baslangicBitis2() {

        baslangic = grids[new Random().nextInt(satir)][new Random().nextInt(sutun)];
        bitis = grids[new Random().nextInt(satir)][new Random().nextInt(sutun)];

        if (baslangic.hucreDegeri != 0)
            baslangicBitis2();
        if (bitis.hucreDegeri != 0)
            baslangicBitis2();

        for (int i = 0; i < satir; i++) {
            for (int j = 0; j < sutun; j++) {
                grids[i][j].visited = false;
            }
        }

        suanki = baslangic;
        siradaki = suanki;
    }

    public void baslangicBitis3() {
        baslangic = grids[0][0];
        bitis = grids[satir - 1][sutun - 1];

        if (baslangic.hucreDegeri != 0)
            baslangic.hucreDegeri = 0;
        if (bitis.hucreDegeri != 0)
            bitis.hucreDegeri = 0;

        for (int i = 0; i < satir; i++) {
            for (int j = 0; j < sutun; j++) {
                grids[i][j].visited = false;
            }
        }

        suanki = baslangic;
        siradaki = suanki;
    }

    public void baslangicBitisCiz(Graphics g) {
        if (baslangic.hucreDegeri == 0 && bitis.hucreDegeri == 0) {
            baslangic.baslangicBitisCiz('s', g);
            suanki = baslangic;
            bitis.baslangicBitisCiz('f', g);
        }
    }

    public void labirentiSifirla() {
        for (int i = 0; i < satir; i++) {
            for (int j = 0; j < sutun; j++) {
                grids[i][j].hucreleriResetle();
            }
        }
        bitisDegeri = false;
    }

    public Engel komsular(Engel current) {
        ArrayList<Engel> komsular = new ArrayList<Engel>();
        if (current.satir - 1 >= 0 && grids[current.satir - 1][current.sutun].hucreDegeri == 0
                && !grids[current.satir - 1][current.sutun].visited) {
            //Yukarı Yönde
            komsular.add(grids[current.satir - 1][current.sutun]);
        }
        if (current.sutun + 1 < sutun && grids[current.satir][current.sutun + 1].hucreDegeri == 0
                && !grids[current.satir][current.sutun + 1].visited) {
            //Sağa Yönde
            komsular.add(grids[current.satir][current.sutun + 1]);
        }
        if (current.satir + 1 < satir && grids[current.satir + 1][current.sutun].hucreDegeri == 0
                && !grids[current.satir + 1][current.sutun].visited) {
            //Aşağı Yönde
            komsular.add(grids[current.satir + 1][current.sutun]);
        }
        if (current.sutun - 1 >= 0 && grids[current.satir][current.sutun - 1].hucreDegeri == 0
                && !grids[current.satir][current.sutun - 1].visited) {
            //Sola Yönde
            komsular.add(grids[current.satir][current.sutun - 1]);
        }

        if (komsular.isEmpty()) {
            return null;
        }
        for (int i = 0; i < komsular.size(); i++) {
            if (komsular.get(i).equals(bitis)) {
                return komsular.get(i);
            } else if (komsular.get(i).equals(baslangic)) {
                komsular.remove(i);
            }
        }
        int random = new Random().nextInt(komsular.size());
        return komsular.get(random);


        //new Random().nextInt(komsular.size())
        //return komsular.get(0);
    }

    public void komsulariTopla(Engel current) {

        for (int i = 0; i < komsulariTopla.size(); i++)
            komsulariTopla.remove(komsulariTopla.get(i));

        //&& grids[current.satir - 1][current.sutun].hucreDegeri == 0
        //&& !grids[current.satir - 1][current.sutun].visited
        if (current.satir - 1 >= 0) {
            //Yukarı Yönde
            komsulariTopla.add(grids[current.satir - 1][current.sutun]);
        }
        //&& grids[current.satir][current.sutun + 1].hucreDegeri == 0
        //&& !grids[current.satir][current.sutun + 1].visited
        if (current.sutun + 1 < sutun) {
            //Sağa Yönde
            komsulariTopla.add(grids[current.satir][current.sutun + 1]);
        }
        // &&grids[current.satir + 1][current.sutun].hucreDegeri == 0
        // && !grids[current.satir + 1][current.sutun].visited
        if (current.satir + 1 < satir) {
            //Aşağı Yönde
            komsulariTopla.add(grids[current.satir + 1][current.sutun]);
        }
        //&& grids[current.satir][current.sutun - 1].hucreDegeri == 0
        //&& !grids[current.satir][current.sutun - 1].visited
        if (current.sutun - 1 >= 0) {
            //Sola Yönde
            komsulariTopla.add(grids[current.satir][current.sutun - 1]);
        }
    }

    public void dumaniKaldir(Graphics g, Engel current) {
        for (int i = 0; i < komsulariTopla.size(); i++) {
            if (komsulariTopla.get(i) == baslangic) {
                komsulariTopla.get(i).yoluBoya(g, Color.RED);
                komsulariTopla.get(i).cizgileriCiz(g);
            }
            if (komsulariTopla.get(i).hucreDegeri == 1) {
                komsulariTopla.get(i).yoluBoya(g, Color.BLACK);
                komsulariTopla.get(i).cizgileriCiz(g);
            } else if (komsulariTopla.get(i).hucreDegeri == 0) {
                komsulariTopla.get(i).yoluBoya(g, Color.WHITE);
                komsulariTopla.get(i).cizgileriCiz(g);
            }
        }
    }

    public void cizimMizim5(Graphics g) {
        if (arttir < cikmazli.size()) {
            if (cikmazli.get(arttir) != null) {
                nolursun = cikmazli.get(arttir);
                //nolursun.drawPath2(g, Color.orange);
                nolursun.yoluBoya(g, Color.orange);
                arttir++;
            } else {
                if (!cikmazli.isEmpty()) {
                    nolursun = cikmazli.get(arttir - 1);
                    // nolursun.drawPath2(g, Color.orange);
                    nolursun.yoluBoya(g, Color.orange);
                } else {
                    System.out.println("Buraya mı düştüm");
                    return;
                }
            }
        } else {
            bitisDegeri = true;

            baslangic.yoluBoya(g, Color.GREEN);
            bitis.yoluBoya(g, Color.RED);

            for (int i = 0; i < satir; i++) {
                for (int j = 0; j < sutun; j++) {
                    if (cikmazli.contains(grids[i][j])) {
                        grids[i][j].yoluBoya(g, Color.pink);
                    }
                    grids[i][j].cizgileriCiz(g);
                }
            }
            return;
        }
    }

    public void aktifYol(Graphics g) {
        if (arttir3 < ikiNoktaArasiYol.size()) {
            komsulariTopla(ikiNoktaArasiYol.get(arttir3));
            dumaniKaldir(g, ikiNoktaArasiYol.get(arttir3));
            ikiNoktaArasiYol.get(arttir3).yoluBoya(g, Color.ORANGE);
            arttir3++;
        } else {
            System.out.println("Aktif Yol Tamamlandı.");
            aktifYol = true;
            baslangic.yoluBoya(g, Color.RED);
            bitis.yoluBoya(g, Color.GREEN);

            for (int i = 0; i < satir; i++) {
                for (int j = 0; j < sutun; j++) {
                    if (cikmazli.contains(grids[i][j])) {
                        grids[i][j].yoluBoya(g, Color.PINK);
                    }
                    grids[i][j].cizgileriCiz(g);
                }
            }
            return;
        }
    }

    public void aktifYol2(Graphics g){
        if(arttir3 < ikiNoktaArasiYol.size()){
            ikiNoktaArasiYol.get(arttir3).yoluBoya(g, Color.ORANGE);
            arttir3++;
        }else{
            System.out.println("Aktif Yol Tamamlandı.");
            aktifYol = true;
            baslangic.yoluBoya(g, Color.RED);
            bitis.yoluBoya(g, Color.GREEN);

            for (int i = 0; i < satir; i++) {
                for (int j = 0; j < sutun; j++) {
                    if (cikmazli.contains(grids[i][j])) {
                        grids[i][j].yoluBoya(g, Color.PINK);
                    }
                    grids[i][j].cizgileriCiz(g);
                }
            }
            return;
        }
    }

    public void sonucuHizliGoster(Graphics g) {
        if (arttir2 < stackSayac) {
            nolursun2 = cikmazli.get(arttir2);
            nolursun2.yoluBoya(g, Color.pink);
            arttir2++;
            sonucuHizliGoster(g);
        } else {
            System.out.println("Sonuç Hızlı Gösterildi.");
            bitisDegeri = true;

            baslangic.yoluBoya(g, Color.RED);
            bitis.yoluBoya(g, Color.GREEN);

            for (int i = 0; i < satir; i++) {
                for (int j = 0; j < sutun; j++) {
                    grids[i][j].cizgileriCiz(g);
                }
            }
            return;
        }
    }

    public void yollariTespitEt() {
        siradaki = komsular(suanki);
        akrabaYap(suanki, siradaki);
        suanki = siradaki;
        if (suanki == bitis) {
            System.out.println("Bitti");
            stackSayac = cikmazli.size();
            arttir = cikmazli.size();
            akrabaliklariAta();
            return;
        } else if (suanki != bitis && suanki != null) {
            System.out.println("Yeni Adım");
            yigin.push(suanki);
            cikmazli.push(suanki);
            siradaki.visitedPath = true;
            suanki.visited = true;
            yollariTespitEt();
        } else if (suanki == null) {
            if (!yigin.isEmpty()) {
                System.out.println("Çıkmaz");
                //cikmazli.push(suanki);
                suanki = yigin.pop();
                yollariTespitEt();
            } else {
                return;
            }
        }
    }

    public void akrabaliklariAta() {
        ikiNoktaArasiYol.add(bitis);
        Engel temp = bitis.parent;

        while (temp != baslangic) {
            ikiNoktaArasiYol.add(temp);
            temp = temp.parent;
        }
    }

    public void akrabaYap(Engel A, Engel B) {
        if (B != null) {
            B.parent = A;
        }
        if (A != null)
            A.next.add(B);
    }
}
