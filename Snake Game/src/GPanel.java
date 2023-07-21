import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class GPanel extends JPanel implements ActionListener,KeyListener{
    private int[] snakeLength_x=new int[750];
    private int[] snakeLength_y=new int[750];

    private int x_pos[]={25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625,650,675,700,725,750,775,800,825,850};
    private int y_pos[]={75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625};

    private Random rand=new Random();
    private int enemy_x,enemy_y;
    private int lengthOfSnake=3;
    private int B_WIDTH=850;
    private int B_HEIGHT=575;
    
    private boolean left=false;
    private boolean right=true;
    private boolean up=false;
    private boolean down=false;
    private boolean gameOver=false;
    private boolean inGame=true;
    

    private int moves=0;
    private int score=0;

    private ImageIcon snaketitle=new ImageIcon(getClass().getResource("snaketitle.jpg"));
    private ImageIcon leftmouth=new ImageIcon(getClass().getResource("leftmouth.png"));
    private ImageIcon rightmouth=new ImageIcon(getClass().getResource("rightmouth.png"));
    private ImageIcon upmouth=new ImageIcon(getClass().getResource("upmouth.png"));
    private ImageIcon downmouth=new ImageIcon(getClass().getResource("downmouth.png"));
    private ImageIcon snakeHead=new ImageIcon(getClass().getResource("snakeimage.png"));
    private ImageIcon enemy=new ImageIcon(getClass().getResource("enemy.png"));
    

    private Timer timer;
    private final int DELAY=100;
    
    GPanel(){
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);

        timer=new Timer(DELAY, this);
        timer.start();

        newEnemy();
    }

    private void newEnemy() {
        enemy_x=x_pos[rand.nextInt(34)];
        enemy_y=y_pos[rand.nextInt(23)];

        for(int i=lengthOfSnake-1;i>=0;i--){
            if(snakeLength_x[i]==enemy_x && snakeLength_y[i]==enemy_y){
                newEnemy();
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(Color.CYAN);
        g.drawRect(24, 10, 851, 55);
        g.drawRect(24, 74, 851, 576);

        snaketitle.paintIcon(this,g,25,11);

        g.setColor(Color.DARK_GRAY);
        g.fillRect(25, 75, 850, 575);

        if(moves==0){
            snakeLength_x[0]=100;
            snakeLength_x[1]=75;
            snakeLength_x[2]=50;

            snakeLength_y[0]=100;
            snakeLength_y[1]=100;
            snakeLength_y[2]=100;
            
          
        }

        if(left){
            leftmouth.paintIcon(this, g, snakeLength_x[0], snakeLength_y[0]);
        }
        if(right){
            rightmouth.paintIcon(this, g, snakeLength_x[0], snakeLength_y[0]);
        }
        if(up){
            upmouth.paintIcon(this, g, snakeLength_x[0], snakeLength_y[0]);
        }
        if(down){
            downmouth.paintIcon(this, g, snakeLength_x[0], snakeLength_y[0]);
        }

        for(int i=1;i<lengthOfSnake;i++){
            snakeHead.paintIcon(this, g, snakeLength_x[i], snakeLength_y[i]);
        }

        enemy.paintIcon(this, g, enemy_x, enemy_y);

        if(gameOver){
            g.setColor(Color.CYAN);
            g.setFont(new Font("Times New Roman", Font.BOLD, 40));
            g.drawString("Game Over", 300, 300);
            FontMetrics metri=getFontMetrics(g.getFont());
           
            g.setFont(new Font("Times New Roman", Font.PLAIN, 35));
            g.drawString("Press Enter To Restart", 230, 350);
            metri=getFontMetrics(g.getFont());

        }

        g.setColor(Color.RED);
        g.setFont(new Font("Times New Roman", Font.BOLD, 20));
        g.drawString("Score: "+score, 750, 30);
        g.drawString("Length: "+lengthOfSnake, 750, 50);
        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for(int i=lengthOfSnake-1;i>0;i--){
            snakeLength_x[i]=snakeLength_x[i-1];
            snakeLength_y[i]=snakeLength_y[i-1];
        }
       if(left){
        snakeLength_x[0]=snakeLength_x[0]-25;
       }
       if(right){
        snakeLength_x[0]=snakeLength_x[0]+25;
       }
       if(up){
        snakeLength_y[0]=snakeLength_y[0]-25;
       }
       if(down){
        snakeLength_y[0]=snakeLength_y[0]+25;
       }

       if(snakeLength_x[0]>850){
        snakeLength_x[0]=25;
       }
       else if(snakeLength_x[0]<25){
        snakeLength_x[0]=850;
       }

       if(snakeLength_y[0]>625){
        snakeLength_y[0]=75;
       }
       else if(snakeLength_y[0]<75){
        snakeLength_y[0]=625;
       }

       collidesWithEnemy();
       collidesWithBody();
       repaint();
    }

    

    private void collidesWithEnemy() {
        if(snakeLength_x[0]==enemy_x && snakeLength_y[0]==enemy_y){
            newEnemy();
            lengthOfSnake++;
            score++;
        }
    }

    private void collidesWithBody() {
        for(int i=lengthOfSnake-1;i>0;i--){
            if(snakeLength_x[i]==snakeLength_x[0] && snakeLength_y[i]==snakeLength_y[0]){
                timer.stop();
                gameOver=true;
            }
        }
            // if (snakeLength_y[0] >= B_HEIGHT) {
            //     inGame = false;
            // }
    
            // if (snakeLength_y[0] < 0) {
            //     inGame = false;
            // }
    
            // if (snakeLength_x[0] >= B_WIDTH) {
            //     inGame = false;
            // }
    
            // if (snakeLength_x[0] < 0) {
            //     inGame = false;
            // }
            
            // if (!inGame) {
            //     timer.stop();
            //     gameOver=true;
            // }
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
       

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if(e.getKeyCode()==KeyEvent.VK_SPACE || e.getKeyCode()==KeyEvent.VK_ENTER){
            restart();
        }
        if(e.getKeyCode()==KeyEvent.VK_LEFT && (!right)){
            left=true;
            right=false;
            up=false;
            down=false;
            moves++;
        }
        
        else if(e.getKeyCode()==KeyEvent.VK_RIGHT && (!left)){
            left=false;
            right=true;
            up=false;
            down=false;
            moves++;
        }

        else if(e.getKeyCode()==KeyEvent.VK_UP && (!down)){
            left=false;
            right=false;
            up=true;
            down=false;
            moves++;
        }

        else if(e.getKeyCode()==KeyEvent.VK_DOWN && (!up)){
            left=false;
            right=false;
            up=false;
            down=true;
            moves++;
        }
    }

    private void restart() {
        gameOver=false;
        moves=0;
        score=0;
        lengthOfSnake=3;
        left=false;
        right=true;
        up=false;
        down=false;
        timer.start();
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
