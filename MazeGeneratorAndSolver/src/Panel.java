import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Date;

public class Panel extends JPanel implements ActionListener {
    String dosya;
    Izgara izgara;
    Izgara izgara2;
    Timer timer;
    private int delay = 150;
    private boolean flag = true;
    private boolean flag2 = true;
    private int calisma = 0;
    private int stackBoyu = 0;
    private boolean dumanlama = false;
    private boolean dumanlama2 = false;
    private boolean dumanIcinSifirlama = false;
    private boolean dumanIcinSifirlama2 = false;


    private int linkModu;
    private int boyutModu;
    private int mod1Calistir = 0;
    private int mod1Sonuc = 0;
    private int mod2Sonuc = 0;
    private int mod2Calistir = 0;
    private int mod1TimerlaCalis = 0;
    private int bitisSuresi;
    private int boyutX, boyutY;
    private LinkedList<Engel> list = new LinkedList<Engel>();

    private JLabel oyunModu1;
    private JLabel sureBelirleme;
    private JLabel sureyiYaz;
    private JLabel kacAdim;
    private JLabel kacAdimYaz;
    private JLabel oyunModu2;
    private JComboBox linkSec;
    private JComboBox boyutSec;
    private JButton baslat;
    private JButton baslat2;
    private JButton mod1Reset;
    private JButton mod2Reset;
    private JButton mod1Dumanla;
    private JButton mod2Dumanla;
    private JButton mod1CozumBaslat;
    private JButton mod2CozumBaslat;
    private JButton mod1SonucGor;
    private JButton mod2SonucGor;


    public final Date createdDate = new java.util.Date();

    public Panel() throws IOException {

        this.setSize(1000, 600);
        this.setLayout(null);
        timer = new Timer(delay, this);


        //Mod 1 Başlangıç
        oyunModu1 = new JLabel("Oyun Modu 1");
        oyunModu1.setBounds(610, 50, 130, 30);
        oyunModu1.setFont(new Font("", Font.BOLD, 15));
        this.add(oyunModu1);

        String[] linkListesi = {"Link:1", "Link:2"};
        linkSec = new JComboBox(linkListesi);
        linkSec.setBounds(730, 50, 130, 30);
        linkSec.setFont(new Font("", Font.BOLD, 15));
        linkSec.setFocusable(false);
        linkSec.addActionListener(this);
        linkModu = linkSec.getSelectedIndex();
        this.add(linkSec);


        baslat = new JButton();
        baslat.setText("Baslat");
        baslat.setBounds(860, 50, 130, 30);
        baslat.setLocation(860, 50);
        baslat.setHorizontalTextPosition(JButton.CENTER);
        baslat.setVerticalTextPosition(JButton.CENTER);
        baslat.setFocusable(false);
        this.add(baslat);
        baslat.addActionListener(this);


        mod1Reset = new JButton();
        mod1Reset.setText("Reset");
        mod1Reset.setBounds(730, 150, 130, 30);
        mod1Reset.setLocation(730, 150);
        mod1Reset.setHorizontalTextPosition(JButton.CENTER);
        mod1Reset.setVerticalTextPosition(JButton.CENTER);
        mod1Reset.setFocusable(false);
        mod1Reset.setEnabled(false);
        this.add(mod1Reset);
        mod1Reset.addActionListener(this);


        mod1Dumanla = new JButton();
        mod1Dumanla.setText("Ekranı Dumanla");
        mod1Dumanla.setBounds(730, 100, 130, 30);
        mod1Dumanla.setLocation(730, 100);
        mod1Dumanla.setHorizontalTextPosition(JButton.CENTER);
        mod1Dumanla.setVerticalTextPosition(JButton.CENTER);
        mod1Dumanla.setFocusable(false);
        mod1Dumanla.setEnabled(false);
        this.add(mod1Dumanla);
        mod1Dumanla.addActionListener(this);


        mod1CozumBaslat = new JButton();
        mod1CozumBaslat.setText("Çözümü Yap");
        mod1CozumBaslat.setBounds(860, 100, 130, 30);
        mod1CozumBaslat.setLocation(860, 100);
        mod1CozumBaslat.setHorizontalTextPosition(JButton.CENTER);
        mod1CozumBaslat.setVerticalTextPosition(JButton.CENTER);
        mod1CozumBaslat.setFocusable(false);
        mod1CozumBaslat.setEnabled(false);
        this.add(mod1CozumBaslat);
        mod1CozumBaslat.addActionListener(this);


        mod1SonucGor = new JButton();
        mod1SonucGor.setText("Sonucu Gör");
        mod1SonucGor.setBounds(860, 150, 130, 30);
        mod1SonucGor.setLocation(860, 150);
        mod1SonucGor.setHorizontalTextPosition(JButton.CENTER);
        mod1SonucGor.setVerticalTextPosition(JButton.CENTER);
        mod1SonucGor.setFocusable(false);
        mod1SonucGor.setEnabled(false);
        this.add(mod1SonucGor);
        mod1SonucGor.addActionListener(this);
        // Mod 1 Bitiş

        // Süreyi ve Kaç Adım Olduğunu Yaz Başlangıç

        sureBelirleme = new JLabel("Kaç Saniye Sürdü: ");
        sureBelirleme.setBounds(610, 225, 200, 30);
        sureBelirleme.setFont(new Font("", Font.BOLD, 15));
        this.add(sureBelirleme);

        sureyiYaz = new JLabel("XXX Saniye");
        sureyiYaz.setBounds(830, 225, 200, 30);
        sureyiYaz.setFont(new Font("", Font.BOLD, 15));
        this.add(sureyiYaz);

        kacAdim = new JLabel("Kaç Adım Sürdü: ");
        kacAdim.setBounds(610, 250, 200, 30);
        kacAdim.setFont(new Font("", Font.BOLD, 15));
        this.add(kacAdim);

        kacAdimYaz = new JLabel("XXX Adım");
        kacAdimYaz.setBounds(830, 250, 200, 30);
        kacAdimYaz.setFont(new Font("", Font.BOLD, 15));
        this.add(kacAdimYaz);

        // Süreyi ve Kaç Adım Olduğunu Yaz Bitiş

        // Oyun Modu 2 Başlangıç

        oyunModu2 = new JLabel("Oyun Modu 2");
        oyunModu2.setBounds(610, 350, 130, 30);
        oyunModu2.setFont(new Font("", Font.BOLD, 15));
        this.add(oyunModu2);


        String[] boyutListesi = {"5x5", "10x10", "20x20", "40x40", "40x60", "60x80", "80x80", "100x100"};
        boyutSec = new JComboBox(boyutListesi);
        boyutSec.setBounds(730, 350, 130, 30);
        boyutSec.setFont(new Font("", Font.BOLD, 15));
        boyutSec.setFocusable(false);
        boyutSec.addActionListener(this);
        boyutModu = boyutSec.getSelectedIndex();
        this.add(boyutSec);


        baslat2 = new JButton();
        baslat2.setText("Baslat");
        baslat2.setBounds(860, 350, 130, 30);
        baslat2.setLocation(860, 350);
        baslat2.setHorizontalTextPosition(JButton.CENTER);
        baslat2.setVerticalTextPosition(JButton.CENTER);
        baslat2.setFocusable(false);
        this.add(baslat2);
        baslat2.addActionListener(this);


        mod2Reset = new JButton();
        mod2Reset.setText("Reset");
        mod2Reset.setBounds(730, 400, 130, 30);
        mod2Reset.setLocation(730, 400);
        mod2Reset.setHorizontalTextPosition(JButton.CENTER);
        mod2Reset.setVerticalTextPosition(JButton.CENTER);
        mod2Reset.setFocusable(false);
        mod2Reset.setEnabled(false);
        this.add(mod2Reset);
        mod2Reset.addActionListener(this);


       /* mod2Dumanla = new JButton();
        mod2Dumanla.setText("Ekranı Dumanla");
        mod2Dumanla.setBounds(730, 400, 130, 30);
        mod2Dumanla.setLocation(730, 400);
        mod2Dumanla.setHorizontalTextPosition(JButton.CENTER);
        mod2Dumanla.setVerticalTextPosition(JButton.CENTER);
        mod2Dumanla.setFocusable(false);
        mod2Dumanla.setEnabled(false);
        this.add(mod2Dumanla);
        mod2Dumanla.addActionListener(this);*/

        mod2CozumBaslat = new JButton();
        mod2CozumBaslat.setText("Çözümü Yap");
        mod2CozumBaslat.setBounds(860, 400, 130, 30);
        mod2CozumBaslat.setLocation(860, 400);
        mod2CozumBaslat.setHorizontalTextPosition(JButton.CENTER);
        mod2CozumBaslat.setVerticalTextPosition(JButton.CENTER);
        mod2CozumBaslat.setFocusable(false);
        mod2CozumBaslat.setEnabled(false);
        this.add(mod2CozumBaslat);
        mod2CozumBaslat.addActionListener(this);


        mod2SonucGor = new JButton();
        mod2SonucGor.setText("Sonucu Gör");
        mod2SonucGor.setBounds(790, 450, 130, 30);
        mod2SonucGor.setLocation(790, 450);
        mod2SonucGor.setHorizontalTextPosition(JButton.CENTER);
        mod2SonucGor.setVerticalTextPosition(JButton.CENTER);
        mod2SonucGor.setFocusable(false);
        mod2SonucGor.setEnabled(false);
        this.add(mod2SonucGor);
        mod2SonucGor.addActionListener(this);
        // Oyun Modu 2 Bitiş

        timer.start();
    }

    public void sureTut() {
        long startTime = (new Date()).getTime() - this.createdDate.getTime();
        long elapsedTime = startTime / 1000;
        bitisSuresi = (int) elapsedTime;
        int sure;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);


        if (mod1Calistir == 1) {
            calisma = 1;
            izgara.haritayiCiz(g);
            baslat.setEnabled(false);
        }


        if (dumanlama) {
            izgara.ekraniDumanla(g);
            if (izgara.dumanlandi) {
                //timer.stop();
            }

        }

        if (!flag) {
            izgara.aktifYol(g);
            if (izgara.aktifYol) {
                System.out.println("Bitiş Süresi: " + bitisSuresi);
                System.out.println("Kaç Adım Sürdü: " + izgara.ikiNoktaArasiYol.size());
                System.out.println();
                sureyiYaz.setText(bitisSuresi + " Saniye");
                kacAdimYaz.setText(izgara.ikiNoktaArasiYol.size() + " Adım");
                timer.stop();
            }

        }


        if (mod1Sonuc == 1) {
            izgara.sonucuHizliGoster(g);
            System.out.println("Bitiş Süresi: " + bitisSuresi);
            System.out.println("Kaç Adım Sürdü: " + izgara.ikiNoktaArasiYol.size());
            System.out.println();
            sureyiYaz.setText(bitisSuresi + " Saniye");
            kacAdimYaz.setText(izgara.ikiNoktaArasiYol.size() + " Adım");
            if (izgara.bitisDegeri) {
                mod1Sonuc = 0;
                timer.stop();
            }
        }


        if (mod2Calistir == 1) {
            calisma = 1;
            izgara2.haritayiCiz(g);
            baslat2.setEnabled(false);
        }

        if (!flag2) {
            izgara2.aktifYol2(g);
            if (izgara2.aktifYol) {
                System.out.println("Bitiş Süresi: " + bitisSuresi);
                System.out.println("Kaç Adım Sürdü: " + izgara2.ikiNoktaArasiYol.size());
                System.out.println();
                sureyiYaz.setText(bitisSuresi + " Saniye");
                kacAdimYaz.setText(izgara2.ikiNoktaArasiYol.size() + " Adım");
                timer.stop();
            }
        }

        if (mod2Sonuc == 1) {
            izgara2.sonucuHizliGoster(g);
            System.out.println("Bitiş Süresi: " + bitisSuresi);
            System.out.println("Kaç Adım Sürdü: " + izgara2.ikiNoktaArasiYol.size());
            System.out.println();
            sureyiYaz.setText(bitisSuresi + " Saniye");
            kacAdimYaz.setText(izgara2.ikiNoktaArasiYol.size() + " Adım");
            if (izgara2.bitisDegeri) {
                mod2Sonuc = 0;
                timer.stop();
            }

        }

        /*if (dumanlama2) {
            izgara2.ekraniDumanla(g);
            if (izgara2.dumanlandi) {
                //timer.stop();
            }

        }*/

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == linkSec) {
            linkModu = linkSec.getSelectedIndex();
            if (linkModu == 0) {
                dosya = "http://bilgisayar.kocaeli.edu.tr/prolab2/url1.txt";
            } else if (linkModu == 1) {
                dosya = "http://bilgisayar.kocaeli.edu.tr/prolab2/url2.txt";
            }
            System.out.println("Secilen Oyun Modu: " + linkModu);
            System.out.println("Link: " + dosya);
        }


        if (e.getSource() == baslat) {
            try {
                izgara = new Izgara(dosya);
                izgara.yollariTespitEt();
                mod1Reset.setEnabled(true);
                mod1CozumBaslat.setEnabled(true);
                mod1Dumanla.setEnabled(true);
                mod1Calistir = 1;
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

        if (e.getSource() == mod1Dumanla) {
            dumanlama = true;
            izgara.dumaniBelirleme();
        }

        if (e.getSource() == mod1Reset) {
            flag = true;
            dumanlama = false;
            dumanIcinSifirlama = true;
            izgara.labirentiSifirla();
            baslat.setEnabled(true);
            mod1Calistir = 0;
            sureyiYaz.setText("XXX Saniye");
            kacAdimYaz.setText("XXX Adım");
            repaint();
            timer.start();
        }

        if (e.getSource() == mod1CozumBaslat) {
            mod1SonucGor.setEnabled(true);
            sureTut();
            if (flag)
                flag = false;
        }

        if (e.getSource() == mod1SonucGor) {
            flag = true;
            mod1Sonuc = 1;
        }

        if (e.getSource() == boyutSec) {
            boyutModu = boyutSec.getSelectedIndex();
            if (boyutModu == 0) {
                boyutX = 5;
                boyutY = 5;
            } else if (boyutModu == 1) {
                boyutX = 10;
                boyutY = 10;
            } else if (boyutModu == 2) {
                boyutX = 20;
                boyutY = 20;
            } else if (boyutModu == 3) {
                boyutX = 40;
                boyutY = 40;
            } else if (boyutModu == 4) {
                boyutX = 40;
                boyutY = 60;
            } else if (boyutModu == 5) {
                boyutX = 60;
                boyutY = 80;
            } else if (boyutModu == 6) {
                boyutX = 80;
                boyutY = 80;
            } else if (boyutModu == 7) {
                boyutX = 100;
                boyutY = 100;
            }
        }

        if (e.getSource() == baslat2) {
            izgara2 = new Izgara(boyutX, boyutY);
            izgara2.yollariTespitEt();
            calisma = 1;
            mod2Reset.setEnabled(true);
            mod2CozumBaslat.setEnabled(true);
           // mod2Dumanla.setEnabled(true);
            mod2Calistir = 1;
        }

        if (e.getSource() == mod2CozumBaslat) {
            mod2SonucGor.setEnabled(true);
            sureTut();
            if (flag2)
                flag2 = false;
        }

        if (e.getSource() == mod2Reset) {
            flag2 = true;
            izgara2.labirentiSifirla();
            baslat2.setEnabled(true);
            mod2Calistir = 0;
            sureyiYaz.setText("XXX Saniye");
            kacAdimYaz.setText("XXX Adım");
            repaint();
            timer.start();
        }

        if (e.getSource() == mod2SonucGor) {
            flag2 = true;
            mod2Sonuc = 1;
        }

      /*  if (e.getSource() == mod2Dumanla) {
            dumanlama2 = true;
            izgara2.dumaniBelirleme();
        }*/


        if (calisma == 1) {
            repaint();
        }
    }
}
