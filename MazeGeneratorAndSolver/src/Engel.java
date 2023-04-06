import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Engel {
    public boolean duvarlar[] = {true, true, true, true};
    public int satir, sutun;
    public int satirB, sutunB;
    public int hucreDegeri;
    private int hucreBoyutX, hucreBoyutY;
    public boolean visited = false;
    public Engel parent;
    public ArrayList<Engel> siradakiListe;
    public boolean visitedPath = false;
    public ArrayList<Engel> next;


    public Engel(int satir, int sutun, int hucreBoyutX, int hucreBoyutY) {
        this.satir = satir;
        this.sutun = sutun;
        satirB = satir * hucreBoyutY;
        sutunB = sutun * hucreBoyutX;
        this.hucreBoyutX = hucreBoyutX;
        this.hucreBoyutY = hucreBoyutY;
        parent = null;
        next = new ArrayList<Engel>();
    }

    public void mod1HucreCiz(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        cizgileriCiz(g);

        if (hucreDegeri == 0) {
            g2.setColor(new Color(255, 255, 255));
            g2.fillRect(sutun * hucreBoyutX, satir * hucreBoyutY, hucreBoyutX, hucreBoyutY);
        } else if (hucreDegeri == 1) {
            g2.setColor(new Color(0, 0, 0));
            g2.fillRect(sutun * hucreBoyutX, satir * hucreBoyutY, hucreBoyutX, hucreBoyutY);
        } else if (hucreDegeri == 2) {
            int random = new Random().nextInt(3);
            if (random == 0) {
                //g2.setColor(new Color(255, 255, 255));
                g2.setColor(new Color(255, 255, 255));
                g2.fillRect(sutun * hucreBoyutX, satir * hucreBoyutY, hucreBoyutX, hucreBoyutY);
                //hucreDegeri = 0;
                hucreDegeri = 0;
            } else if (random == 1 || random == 2) {
                g2.setColor(new Color(0, 0, 0));
                g2.fillRect(sutun * hucreBoyutX, satir * hucreBoyutY, hucreBoyutX, hucreBoyutY);
                hucreDegeri = 1;
            }
        } else if (hucreDegeri == 3) {
            int random = new Random().nextInt(3);
            if (random == 0) {
                //g2.setColor(new Color(255, 255, 255));
                g2.setColor(new Color(255, 255, 255));
                g2.fillRect(sutun * hucreBoyutX, satir * hucreBoyutY, hucreBoyutX, hucreBoyutY);
                //hucreDegeri = 0;
                hucreDegeri = 0;
            } else if (random == 1 || random == 2) {
                g2.setColor(new Color(0, 0, 0));
                g2.fillRect(sutun * hucreBoyutX, satir * hucreBoyutY, hucreBoyutX, hucreBoyutY);
                hucreDegeri = 1;
            }
        }
    }

    public void mod2DegerleriAta(){
        float random = new Random().nextFloat(1);

        if(random < 0.2){
            hucreDegeri = 1;
        }else{
            hucreDegeri = 0;
        }
    }

    public void mod2HucreleriCiz(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        cizgileriCiz(g);

        if(hucreDegeri == 0){
            g2.setColor(new Color(255, 255, 255));
            g2.fillRect(sutun * hucreBoyutX, satir * hucreBoyutY, hucreBoyutX, hucreBoyutY);
        }else if(hucreDegeri == 1){
            g2.setColor(new Color(0, 0, 0));
            g2.fillRect(sutun * hucreBoyutX, satir * hucreBoyutY, hucreBoyutX, hucreBoyutY);
        }
    }

    public void cizgileriCiz(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(new Color(0, 0, 0));

        if (duvarlar[0])
            g2.drawLine(sutunB, satirB, sutunB + hucreBoyutX, satirB);
        if (duvarlar[1])
            g2.drawLine(sutunB + hucreBoyutX, satirB, sutunB + hucreBoyutX, satirB + hucreBoyutY);
        if (duvarlar[2])
            g2.drawLine(sutunB + hucreBoyutX, satirB + hucreBoyutY, sutunB, satirB + hucreBoyutY);
        if (duvarlar[3])
            g2.drawLine(sutunB, satirB + hucreBoyutY, sutunB, satirB);
    }

    public void baslangicBitisCiz(char tip, Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        if (tip == 's') {
            g.setColor(Color.RED);
            g.fillRect(sutun * hucreBoyutX, satir * hucreBoyutY, hucreBoyutX, hucreBoyutY);
        } else if (tip == 'f') {
            g.setColor(Color.GREEN);
            g.fillRect(sutun * hucreBoyutX, satir * hucreBoyutY, hucreBoyutX, hucreBoyutY);
        }
    }

    public void yoluBoya(Graphics g, Color color) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(color);
        g2.fillRect(sutun * hucreBoyutX, satir * hucreBoyutY, hucreBoyutX, hucreBoyutY);
    }




    public void hucreleriResetle() {
        visited = false;
        visitedPath = false;
        parent = null;
        next = new ArrayList<>();
    }


}
