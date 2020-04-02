import java.awt.Color;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import javax.swing.JFrame;

public class PixelDigger extends JFrame implements KeyListener, MouseListener, MouseWheelListener{
    
    private final int WIDTH = 90, HEIGHT = 53;
    private int mouseX = 0, mouseY = 0; 
    private boolean loop = false;
    private final int SCALE = 16;
    
    int t[][] = new int[WIDTH][HEIGHT];
    boolean k[][] = new boolean[WIDTH][HEIGHT];
    boolean m[][] = new boolean[WIDTH][HEIGHT];
    boolean re[][] = new boolean[WIDTH][HEIGHT];
    boolean p[][] = new boolean[WIDTH][HEIGHT];
    int i[] = new int[11];
    int s = 0;
    int scroll = 0;
    int playerX = WIDTH/2;
    int playerY = HEIGHT/5;
    boolean inAir = true;
    boolean left = false;
    boolean right = false;
    boolean up = false;
    int rs = 0;
    int ls = 0;
    String save = "";
    String load = "";
    
    
    //vars go here
    
    public PixelDigger(){
        super("Pixel Digger");
        setSize(SCALE*WIDTH, SCALE*HEIGHT+22);
        setVisible(true);
        requestFocusInWindow();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addKeyListener(this);
        addMouseListener(this);
        addMouseWheelListener(this);
        
        loop = true;
        tileGen();
        while(true){
            this.repaint();
            updateMouse();
            update();
            try{
                Thread.sleep(50);
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
    private void tileGen(){
        for (int x = 0; x < WIDTH; x++){
            for (int y = 0; y < HEIGHT; y++){
                re[x][y] = false;
                t[x][y] = 0;
            }
        }
        caGen();
        diGen();
        grGen();        
        siGen();
        alGen();
        feGen();
        naGen();
        kxGen();
        mgGen();
        cvGen();
        trGen();
    }
    private void grGen(){
        for (int x = 0; x < WIDTH; x++){
            for (int y = 0; y < HEIGHT; y++){
                if(y == (HEIGHT/3)){
                    t[x][y] = 1;
                }
            }
        }
    }
    private void diGen(){
        for (int x = 0; x < WIDTH; x++){
            for (int y = 0; y < HEIGHT; y++){
                if(y > (HEIGHT/3) && y < (HEIGHT/3)+4){
                    t[x][y] = 9;
                }
            }
        }
    }
    private void siGen(){
        boolean w[][] = new boolean[WIDTH][HEIGHT];
        boolean c[][] = new boolean[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x++){
            for (int y = 0; y < HEIGHT; y++){
                if (t[x][y] == 2){
                    if (Math.random() < .03){
                        w[x][y] = true;
                    }
                }
            }
        }
        for (int i = 0; i < 60; i++){
            for (int x = 0; x < WIDTH; x++){
                for (int y = 0; y < HEIGHT; y++){
                    if (t[x][y] == 2){
                        if(c[x][y]){
                            w[x][y] = true;
                        }
                        c[x][y] = false;
                        if(!w[x][y]){
                            if(x >= 1){
                                if (w[x-1][y]){
                                    if (Math.random() > .99){
                                        c[x][y] = true;
                                    }
                                }
                            }
                            if(x < WIDTH-1){
                                if (w[x+1][y]){
                                    if (Math.random() > .99){
                                        c[x][y] = true;
                                    }
                                }
                            }
                            if(y >= 1){
                                if (w[x][y-1]){
                                    if (Math.random() > .99){
                                        c[x][y] = true;
                                    }
                                }
                            }
                            if(y < HEIGHT-1){
                                if (w[x][y+1]){
                                    if (Math.random() > .99){
                                        c[x][y] = true;
                                    }
                                }
                            }
                        }
                    }
                }
            }           
        }
        for (int x = 0; x < WIDTH; x++){
            for (int y = 0; y < HEIGHT; y++){
                if (w[x][y]){
                    t[x][y] = 3;
                }
            }
        }
    }
    private void alGen(){
        boolean w[][] = new boolean[WIDTH][HEIGHT];
        boolean c[][] = new boolean[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x++){
            for (int y = 0; y < HEIGHT; y++){
                if (t[x][y] == 2){
                    if (Math.random() < .03){
                        w[x][y] = true;
                    }
                }
            }
        }
        for (int i = 0; i < 20; i++){
            for (int x = 0; x < WIDTH; x++){
                for (int y = 0; y < HEIGHT; y++){
                    if (t[x][y] == 2){
                        if(c[x][y]){
                            w[x][y] = true;
                        }
                        c[x][y] = false;
                        if(!w[x][y]){
                            if(x >= 1){
                                if (w[x-1][y]){
                                    if (Math.random() > .99){
                                        c[x][y] = true;
                                    }
                                }
                            }
                            if(x < WIDTH-1){
                                if (w[x+1][y]){
                                    if (Math.random() > .99){
                                        c[x][y] = true;
                                    }
                                }
                            }
                            if(y >= 1){
                                if (w[x][y-1]){
                                    if (Math.random() > .99){
                                        c[x][y] = true;
                                    }
                                }
                            }
                            if(y < HEIGHT-1){
                                if (w[x][y+1]){
                                    if (Math.random() > .99){
                                        c[x][y] = true;
                                    }
                                }
                            }
                        }
                    }
                }
            }           
        }
        for (int x = 0; x < WIDTH; x++){
            for (int y = 0; y < HEIGHT; y++){
                if (w[x][y]){
                    t[x][y] = 4;
                }
            }
        }
    }
    private void feGen(){
        boolean w[][] = new boolean[WIDTH][HEIGHT];
        boolean c[][] = new boolean[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x++){
            for (int y = 0; y < HEIGHT; y++){
                if (t[x][y] == 2){
                    if (Math.random() < .03){
                        w[x][y] = true;
                    }
                }
            }
        }
        for (int i = 0; i < 20; i++){
            for (int x = 0; x < WIDTH; x++){
                for (int y = 0; y < HEIGHT; y++){
                    if (t[x][y] == 2){
                        if(c[x][y]){
                            w[x][y] = true;
                        }
                        c[x][y] = false;
                        if(!w[x][y]){
                            if(x >= 1){
                                if (w[x-1][y]){
                                    if (Math.random() > .99){
                                        c[x][y] = true;
                                    }
                                }
                            }
                            if(x < WIDTH-1){
                                if (w[x+1][y]){
                                    if (Math.random() > .99){
                                        c[x][y] = true;
                                    }
                                }
                            }
                            if(y >= 1){
                                if (w[x][y-1]){
                                    if (Math.random() > .99){
                                        c[x][y] = true;
                                    }
                                }
                            }
                            if(y < HEIGHT-1){
                                if (w[x][y+1]){
                                    if (Math.random() > .99){
                                        c[x][y] = true;
                                    }
                                }
                            }
                        }
                    }
                }
            }           
        }
        for (int x = 0; x < WIDTH; x++){
            for (int y = 0; y < HEIGHT; y++){
                if (w[x][y]){
                    t[x][y] = 5;
                }
            }
        }
    }
    private void caGen(){
        for (int x = 0; x < WIDTH; x++){
            for (int y = 0; y < HEIGHT; y++){
                if(y > (HEIGHT/3)){
                    t[x][y] = 2;
                }
            }
        }
    }
    private void naGen(){
        boolean w[][] = new boolean[WIDTH][HEIGHT];
        boolean c[][] = new boolean[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x++){
            for (int y = 0; y < HEIGHT; y++){
                if (t[x][y] == 2){
                    if (Math.random() < .03){
                        w[x][y] = true;
                    }
                }
            }
        }
        for (int i = 0; i < 75; i++){
            for (int x = 0; x < WIDTH; x++){
                for (int y = 0; y < HEIGHT; y++){
                    if (t[x][y] == 2){
                        if(c[x][y]){
                            w[x][y] = true;
                        }
                        c[x][y] = false;
                        if(!w[x][y]){
                            if(x >= 1){
                                if (w[x-1][y]){
                                    if (Math.random() > .99){
                                        c[x][y] = true;
                                    }
                                }
                            }
                            if(x < WIDTH-1){
                                if (w[x+1][y]){
                                    if (Math.random() > .99){
                                        c[x][y] = true;
                                    }
                                }
                            }
                            if(y >= 1){
                                if (w[x][y-1]){
                                    if (Math.random() > .99){
                                        c[x][y] = true;
                                    }
                                }
                            }
                            if(y < HEIGHT-1){
                                if (w[x][y+1]){
                                    if (Math.random() > .99){
                                        c[x][y] = true;
                                    }
                                }
                            }
                        }
                    }
                }
            }           
        }
        for (int x = 0; x < WIDTH; x++){
            for (int y = 0; y < HEIGHT; y++){
                if (w[x][y]){
                    t[x][y] = 6;
                }
            }
        }
    }
    private void kxGen(){
        boolean w[][] = new boolean[WIDTH][HEIGHT];
        boolean c[][] = new boolean[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x++){
            for (int y = 0; y < HEIGHT; y++){
                if (t[x][y] == 6){
                    if (Math.random() < .03){
                        w[x][y] = true;
                    }
                }
            }
        }
        for (int i = 0; i < 50; i++){
            for (int x = 0; x < WIDTH; x++){
                for (int y = 0; y < HEIGHT; y++){
                    if (t[x][y] == 6){
                        if(c[x][y]){
                            w[x][y] = true;
                        }
                        c[x][y] = false;
                        if(!w[x][y]){
                            if(x >= 1){
                                if (w[x-1][y]){
                                    if (Math.random() > .99){
                                        c[x][y] = true;
                                    }
                                }
                            }
                            if(x < WIDTH-1){
                                if (w[x+1][y]){
                                    if (Math.random() > .99){
                                        c[x][y] = true;
                                    }
                                }
                            }
                            if(y >= 1){
                                if (w[x][y-1]){
                                    if (Math.random() > .99){
                                        c[x][y] = true;
                                    }
                                }
                            }
                            if(y < HEIGHT-1){
                                if (w[x][y+1]){
                                    if (Math.random() > .99){
                                        c[x][y] = true;
                                    }
                                }
                            }
                        }
                    }
                }
            }           
        }
        for (int x = 0; x < WIDTH; x++){
            for (int y = 0; y < HEIGHT; y++){
                if (w[x][y]){
                    t[x][y] = 7;
                }
            }
        }
    }
    private void mgGen(){
        boolean w[][] = new boolean[WIDTH][HEIGHT];
        boolean c[][] = new boolean[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x++){
            for (int y = 0; y < HEIGHT; y++){
                if (t[x][y] == 6){
                    if (Math.random() < .03){
                        w[x][y] = true;
                    }
                }
            }
        }
        for (int i = 0; i < 50; i++){
            for (int x = 0; x < WIDTH; x++){
                for (int y = 0; y < HEIGHT; y++){
                    if (t[x][y] == 6){
                        if(c[x][y]){
                            w[x][y] = true;
                        }
                        c[x][y] = false;
                        if(!w[x][y]){
                            if(x >= 1){
                                if (w[x-1][y]){
                                    if (Math.random() > .99){
                                        c[x][y] = true;
                                    }
                                }
                            }
                            if(x < WIDTH-1){
                                if (w[x+1][y]){
                                    if (Math.random() > .99){
                                        c[x][y] = true;
                                    }
                                }
                            }
                            if(y >= 1){
                                if (w[x][y-1]){
                                    if (Math.random() > .99){
                                        c[x][y] = true;
                                    }
                                }
                            }
                            if(y < HEIGHT-1){
                                if (w[x][y+1]){
                                    if (Math.random() > .99){
                                        c[x][y] = true;
                                    }
                                }
                            }
                        }
                    }
                }
            }           
        }
        for (int x = 0; x < WIDTH; x++){
            for (int y = 0; y < HEIGHT; y++){
                if (w[x][y]){
                    t[x][y] = 8;
                }
            }
        }
    }
    private void cvGen(){
        boolean w[][] = new boolean[WIDTH][HEIGHT];
        boolean c[][] = new boolean[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x++){
            for (int y = 0; y < HEIGHT; y++){
                if (t[x][y] != 0 || t[x][y] != 0){
                    if (Math.random() < .01){
                        w[x][y] = true;
                    }
                }
            }
        }
        for (int i = 0; i < 200; i++){
            for (int x = 0; x < WIDTH; x++){
                for (int y = 0; y < HEIGHT; y++){
                    if (t[x][y] != 0 && t[x][y] != 0){
                        if(c[x][y]){
                            w[x][y] = true;
                        }
                        c[x][y] = false;
                        if(!w[x][y]){
                            if(x >= 1){
                                if (w[x-1][y]){
                                    if (Math.random() > .99){
                                        c[x][y] = true;
                                    }
                                }
                            }
                            if(x < WIDTH-1){
                                if (w[x+1][y]){
                                    if (Math.random() > .99){
                                        c[x][y] = true;
                                    }
                                }
                            }
                            if(y >= 1){
                                if (w[x][y-1]){
                                    if (Math.random() > .99){
                                        c[x][y] = true;
                                    }
                                }
                            }
                            if(y < HEIGHT-1){
                                if (w[x][y+1]){
                                    if (Math.random() > .99){
                                        c[x][y] = true;
                                    }
                                }
                            }
                        }
                    }
                }
            }           
        }
        for (int x = 0; x < WIDTH; x++){
            for (int y = 0; y < HEIGHT; y++){
                if (w[x][y]){
                    t[x][y] = 11;
                }
            }
        }
    }
    private void trGen(){
        for (int x = 0; x < WIDTH; x++){
            for (int y = 0; y < HEIGHT; y++){
                if(y == (HEIGHT/3)-1){
                    if(x >= 1 && x < WIDTH-1 && y < HEIGHT-1){
                        if(t[x-1][y] != 10 && t[x+1][y] != 10 && t[x][y+1] == 1){
                            if(Math.random() < .2){
                                t[x][y] = 10;
                            }
                        }
                    }
                }
            }
        }
        for (int k = 0; k < 30; k++){
            for (int x = 0; x < WIDTH; x++){
                for (int y = 0; y < HEIGHT; y++){
                    if(y < HEIGHT-1){
                        if(t[x][y+1] == 10){
                            if(Math.random() < .2){
                                t[x][y] = 10;
                            }
                        }
                    }
                
                }
            }
        }
        for (int x = 0; x < WIDTH; x++){
            for (int y = 0; y < HEIGHT; y++){
                if(x >= 1 && x < WIDTH-1){
                    if(t[x-1][y] == 10 || (t[x+1][y] == 10 && t[x+2][y] != 10)){
                        if(Math.random() < .1){
                            t[x][y] = 10;
                        }
                    }
                }
            }
        }
    }
    private void mouse(){
        for (int x = 0; x < WIDTH; x++){
            for (int y = 0; y < HEIGHT; y++){
                if(mouseX/SCALE == x && mouseY/SCALE == y){
                    m[x][y] = true;
                }
                else{
                    m[x][y] = false;
                }
            }
        }
    }
    private void revealed(){
        for (int x = 0; x < WIDTH; x++){
            for (int y = 0; y < HEIGHT; y++){
                if(t[x][y] == 0){
                    re[x][y] = true;
                }
                else if(t[x][y] == 11){
                    if(x >= 1){
                        if(t[x-1][y] == 0 || (t[x-1][y] == 11 && re[x-1][y])){
                            re[x][y] = true;
                        }
                    }
                    if(x < WIDTH-1){
                        if(t[x+1][y] == 0 || (t[x+1][y] == 11 && re[x+1][y])){
                            re[x][y] = true;
                        }
                    }
                    if(y >= 1){
                        if(t[x][y-1] == 0 || t[x][y-1] == 10 || (t[x][y-1] == 11 && re[x][y-1])){//change for place10
                            re[x][y] = true;
                        }
                    }
                    if(y < HEIGHT-1){
                        if(t[x][y+1] == 0 || (t[x][y+1] == 11 && re[x][y+1])){
                            re[x][y] = true;
                        }
                    }
                }
                else{
                    if(x >= 1){
                        if (t[x-1][y] == 0 || t[x-1][y] == 10 || (t[x-1][y] == 11 && re[x-1][y])){
                            re[x][y] = true;
                        }
                    }
                    if(x < WIDTH-1){
                        if (t[x+1][y] == 0 || t[x+1][y] == 10 || (t[x+1][y] == 11 && re[x+1][y])){
                            re[x][y] = true;
                        }
                    }
                    if(y >= 1){
                        if (t[x][y-1] == 0 || t[x][y-1] == 10 || (t[x][y-1] == 11 && re[x][y-1])){
                            re[x][y] = true;
                        }
                    }
                    if(y < HEIGHT-1){
                        if (t[x][y+1] == 0 || t[x][y+1] == 10 || (t[x][y+1] == 11 && re[x][y+1])){
                            re[x][y] = true;
                        }
                    }
                }
            }
        }
    }
    private void indicator(){
        for (int x = 0; x < WIDTH; x++){
            for (int y = 0; y < HEIGHT; y++){
                if(x == 0 && y == 0){
                    t[x][y] = s+1;
                }
            }
        }
    }
    private void move(){
        
        if(playerY >= 1 && playerY < WIDTH-1){
            if(t[playerX][playerY+1] == 0 || t[playerX][playerY+1] == 11 || t[playerX][playerY+1] == 10){
                playerY++;
            }
            else{
                inAir = false;
            }
        }
        if(left){
            System.out.println("Acting:Left");
            if(playerX > 0){
                if(t[playerX-1][playerY] == 11 || t[playerX-1][playerY] == 10 || t[playerX-1][playerY] == 0){
                    playerX--;
                    System.out.println("Done:Left");
                }
            }
        }
        if(right){
            System.out.println("Acting:Right");
            if(playerX < WIDTH-1){
                if(t[playerX+1][playerY] == 11 || t[playerX+1][playerY] == 10 || t[playerX+1][playerY] == 0){
                    playerX++;
                    System.out.println("Done:Right");
                }
            }
            
        }
        if(up){
            System.out.println("Acting:Up");
            if(playerY >= 0){
                if(t[playerX][playerY-1] == 11 || t[playerX][playerY-1] == 10 || t[playerX][playerY-1] == 0){
                    if(!inAir){
                        for(int k = 0; k < 3; k++){
                            playerY--;
                            System.out.println("Done: Up |"+(k+1)+"/3|");
                            if(t[playerX][playerY-1] != 11 && t[playerX][playerY-1] != 10 && t[playerX][playerY-1] != 0){
                                k = 3;
                            }
                            try{
                            Thread.sleep(16);
                            }
                            catch(Exception ex){
                                ex.printStackTrace();
                            }
                        }
                        inAir = true;
                    }
                }
            }
        }
    }
    private void save(int sn){
        for (int x = 0; x < WIDTH; x++){
            for (int y = 0; y < HEIGHT; y++){
                save = save + t[x][y] + " ";
            }
        }
        try{
            PrintWriter writer = new PrintWriter(Paths.get("").toAbsolutePath().toString()+"/Saves/PixDigSave"+sn);
            writer.print(save);
            writer.flush();
            writer.close();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    private void load(int ln){
        for (int x = 0; x < WIDTH; x++){
            for (int y = 0; y < HEIGHT; y++){
                re[x][y] = false;
            }
        }
        try{
            FileReader fr = new FileReader(Paths.get("").toAbsolutePath().toString()+"/Saves/PixDigSave"+ln);
            BufferedReader br = new BufferedReader(fr);
            load = br.readLine();
            br.close();
            fr.close();
        }
        catch(FileNotFoundException ex){
            System.out.println("File not found");
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
        String[] l = load.split(" ");
        for (int x = 0; x < WIDTH; x++){
            for (int y = 0; y < HEIGHT; y++){
                t[x][y] = Integer.parseInt(l[x*HEIGHT+y]);
            }
        }
    }
    public void paint(Graphics g){
        if(loop){
            BufferedImage B = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics r = B.getGraphics();
            
            for (int x = 0; x < WIDTH; x++){
                for (int y = 0; y < HEIGHT; y++){
                    int r9 = 161-(5*(y-(HEIGHT/3)));
                    int g9 = 194-(5*(y-(HEIGHT/3)));
                    int b9 = 236-(5*(y-(HEIGHT/3)));
                    r9=r9<0?0:r9;
                    g9=g9<0?0:g9;
                    b9=b9<0?0:b9;
                    r9=r9>161?161:r9;
                    g9=g9>194?194:g9;
                    b9=b9>236?236:b9;
                    if (t[x][y] == 0){//Air
                        r.setColor(new Color(161, 194, 236));
                    }
                    else if(t[x][y] == 1){//Surface
                        if(re[x][y]){
                            r.setColor(Color.GREEN);
                        }else{
                            r.setColor(Color.BLACK);
                        }
                    }
                    else if(t[x][y] == 2){//Limestone (Calcium)
                        if(re[x][y]){
                            r.setColor(Color.GRAY);
                        }else{
                            r.setColor(Color.BLACK);
                        }
                    }
                    else if(t[x][y] == 3){//Sand (Silicon)
                        if(re[x][y]){
                            r.setColor(Color.YELLOW);
                        }else{
                            r.setColor(Color.BLACK);
                        }
                    }
                    else if(t[x][y] == 4){//Bauxite (Aluminum)
                        if(re[x][y]){
                            r.setColor(Color.LIGHT_GRAY);
                        }else{
                            r.setColor(Color.BLACK);
                        }
                    }
                    else if(t[x][y] == 5){//Iron Ore (Iron)
                        if(re[x][y]){
                            r.setColor(Color.ORANGE);
                        }else{
                            r.setColor(Color.BLACK);
                        }
                    }
                    else if(t[x][y] == 6){//Salt (Sodium)
                        if(re[x][y]){
                            r.setColor(Color.WHITE);
                        }else{
                            r.setColor(Color.BLACK);
                        }
                    }
                    else if(t[x][y] == 7){//Salt (Potassium)
                        if(re[x][y]){
                            r.setColor(new Color(204, 229, 255));
                        }
                    }
                    else if(t[x][y] == 8){//Salt (Magnesium)
                        if(re[x][y]){
                            r.setColor(new Color(204, 255, 229));
                        }else{
                            r.setColor(Color.BLACK);
                        }
                    }
                    else if(t[x][y] == 11){//Cavern Wall
                        if(re[x][y]){
                            r.setColor(new Color(r9, g9, b9));
                        }else{
                            r.setColor(Color.BLACK);
                        }
                    }
                    else if(t[x][y] == 9){//Dirt
                        if(re[x][y]){
                            r.setColor(new Color(153, 76, 0));
                        }else{
                            r.setColor(Color.BLACK);
                        }
                    }
                    else if(t[x][y] == 10){//Trees
                        if(re[x][y]){
                            r.setColor(new Color(200, 76, 0));
                        }else{
                            r.setColor(Color.BLACK);
                        }
                    }
                    if(x == 1 && y == 0){
                        r.setColor(Color.BLUE);
                    }
                    if(x == 2 && y == 0){
                        r.setColor(new Color(0, 0, 200));
                    }
                    if(x == 3 && y == 0){
                        r.setColor(Color.BLUE);
                    }
                    if(x == WIDTH-1 && y == 0){
                        r.setColor(Color.RED);
                    }
                    if(x == WIDTH-2 && y == 0){
                        r.setColor(new Color(200, 0, 0));
                    }
                    if(x == WIDTH-3 && y == 0){
                        r.setColor(Color.RED);
                    }
                    if(x == WIDTH-4 && y == 0){
                        r.setColor(Color.GREEN);
                    }
                    /*if(x == playerX && y == playerY){//player
                        r.setColor(Color.RED);
                    }*///(player)
                    if(m[x][y]){
                        r.setColor(Color.BLACK);
                    }
                    r.fillRect(x*SCALE, y*SCALE, SCALE, SCALE);
                }
            }
            
            g.drawImage(B, 0, 22, this);
        }
    }
    private void updateMouse(){
        mouseX=(MouseInfo.getPointerInfo().getLocation().x-getLocationOnScreen().x);
        mouseY=(MouseInfo.getPointerInfo().getLocation().y-getLocationOnScreen().y - 22);
    }
    public static void main(String[] args) {
        new PixelDigger();
    }
    private void update(){
        mouse();
        revealed();
        indicator();
        //move();(player)
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_SPACE){
            up = true;
            System.out.println("Seen:Up");
        }
        if(e.getKeyCode()==KeyEvent.VK_A){
            left = true;
            System.out.println("Seen:Left");
        }
        if(e.getKeyCode()==KeyEvent.VK_D){
            right = true;
            System.out.println("Seen:Right");
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_D){
            right = false;
            System.out.println("Stopped:Right");
        }
        if(e.getKeyCode()==KeyEvent.VK_A){
            left = false;
            System.out.println("Stopped:Left");
        }
        if(e.getKeyCode()==KeyEvent.VK_SPACE){
            up = false;
            System.out.println("Stopped:Up");
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton()==MouseEvent.BUTTON1){
            if(mouseX/SCALE == 1 && mouseY/SCALE == 0){
                save(1);
            }
            if(mouseX/SCALE == 2 && mouseY/SCALE == 0){
                save(2);
            }
            if(mouseX/SCALE == 3 && mouseY/SCALE == 0){
                save(3);
            }
            if(mouseX/SCALE == WIDTH-1 && mouseY/SCALE == 0){
                load(3);
            }
            if(mouseX/SCALE == WIDTH-2 && mouseY/SCALE == 0){
                load(2);
            }
            if(mouseX/SCALE == WIDTH-3 && mouseY/SCALE == 0){
                load(1);
            }
            if(mouseX/SCALE == WIDTH-4 && mouseY/SCALE == 0){
                tileGen();
            }
            if(mouseX/SCALE == WIDTH-1 && mouseY/SCALE == 3){
                for (int x = 0; x < WIDTH; x++){
                    for (int y = 0; y < HEIGHT; y++){
                        re[x][y] = true;
                    }
                }
            }
            if(mouseX/SCALE == WIDTH-1 && mouseY/SCALE == 4){
                for (int x = 0; x < WIDTH; x++){
                    for (int y = 0; y < HEIGHT; y++){
                        re[x][y] = false;
                    }
                }
            }
            if(mouseX/SCALE >= 1){
                if(t[(mouseX/SCALE)-1][mouseY/SCALE] == 11 || t[(mouseX/SCALE)-1][mouseY/SCALE] == 0){
                    for(int x = 0; x < 10; x++){
                        if (t[mouseX/SCALE][mouseY/SCALE] == x+1){
                            i[x]++;
                        }
                    }
                    if(t[mouseX/SCALE][mouseY/SCALE] != 0){
                        t[mouseX/SCALE][mouseY/SCALE] = 11;
                    }
                }
            }
            if(mouseX/SCALE < WIDTH-1){
                if (t[(mouseX/SCALE)+1][mouseY/SCALE] == 11 || t[(mouseX/SCALE)+1][mouseY/SCALE] == 0){
                    for(int x = 0; x < 10; x++){
                        if (t[mouseX/SCALE][mouseY/SCALE] == x+1){
                            i[x]++;
                        }
                    }
                    if(t[mouseX/SCALE][mouseY/SCALE] != 0){
                        t[mouseX/SCALE][mouseY/SCALE] = 11;
                    }
                }
            }
            if(mouseY/SCALE >= 1){
                if (t[mouseX/SCALE][(mouseY/SCALE)-1] == 11 || t[mouseX/SCALE][(mouseY/SCALE)-1] == 0){
                    for(int x = 0; x < 10; x++){
                        if (t[mouseX/SCALE][mouseY/SCALE] == x+1){
                            i[x]++;
                        }
                    }
                    if(t[mouseX/SCALE][mouseY/SCALE] != 0){
                        t[mouseX/SCALE][mouseY/SCALE] = 11;
                    }
                }
            }
            if(mouseY/SCALE < HEIGHT-1){
                if (t[mouseX/SCALE][(mouseY/SCALE)+1] == 11 || t[mouseX/SCALE][(mouseY/SCALE)+1] == 0){
                    for(int x = 0; x < 10; x++){
                        if (t[mouseX/SCALE][mouseY/SCALE] == x+1){
                            i[x]++;
                        }
                    }
                    if(t[mouseX/SCALE][mouseY/SCALE] != 0){
                        t[mouseX/SCALE][mouseY/SCALE] = 11;
                    }
                }
            }
        }
        if(e.getButton()==MouseEvent.BUTTON3){
            if(mouseX/SCALE >= 1 && mouseX/SCALE < WIDTH-1 && mouseY/SCALE >= 1 && mouseY/SCALE < WIDTH-1){
                if (t[(mouseX/SCALE)-1][mouseY/SCALE] != 11 && t[(mouseX/SCALE)-1][mouseY/SCALE] != 0 || t[(mouseX/SCALE)+1][mouseY/SCALE] != 11 && t[(mouseX/SCALE)+1][mouseY/SCALE] != 0 || t[mouseX/SCALE][(mouseY/SCALE)-1] != 11 && t[mouseX/SCALE][(mouseY/SCALE)-1] != 0 || t[mouseX/SCALE][(mouseY/SCALE)+1] != 11 && t[mouseX/SCALE][(mouseY/SCALE)+1] != 0){
                    if(i[s] > 0){
                        if(t[mouseX/SCALE][mouseY/SCALE] == 0 || t[mouseX/SCALE][mouseY/SCALE] == 11){
                            t[mouseX/SCALE][mouseY/SCALE] = s+1;
                            i[s]--;
                        }
                    }
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        int scroll = e.getWheelRotation();
        s = s+scroll;
        s=s>=10?0:s;
        s=s<0?9:s;
    }
}
