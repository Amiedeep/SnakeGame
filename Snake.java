import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
public class Snake extends JFrame implements KeyListener
{
    ArrayList<SnakeBody> al;
    int headx;
    int heady;
    boolean food;
    int foodx;
    int foody;
    Body t;
    Thread t1;
    Label lb;
    int score;
    Snake()
    {
        try 
        {
            this.setLayout(null);
            this.setTitle("Snake game implemented by Amandeep Singh(AmIe) in java :D");
            lb=new Label("");
            
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            
            score=0;
            lb.setBounds(42,0,200, 20);
            lb.setVisible(true);
            this.setVisible(true);
            this.setSize(1000,700);
            lb.setBackground(Color.red);
            lb.setForeground(Color.YELLOW);
            headx=492;
            heady=252;
            food=false;
            foodx=516;
            foody=312;
            al=new ArrayList<>();
            al.add(new SnakeBody(456,252));
            al.add(new SnakeBody(468,252));
            al.add(new SnakeBody(480,252));
            al.add(new SnakeBody(492,252));
            this.add(lb);
            t=new Body(false,false,false,true);
            t1=new Thread(t);
            t1.start();
            this.addKeyListener(this);
        }
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    
    public class Body implements Runnable
{
    boolean up;
    boolean down;
    boolean left;
    boolean right;
    Body(boolean a,boolean b,boolean c,boolean d)
    {
       up=a;
       down=b;
       left=c;
       right=d;
    }
    public void run() 
    {
        try 
        {
            
            while(true)
            {
                
                lb.setText("score : "+score);
                if(headx==foodx && heady==foody)
                    {
                        score=score+100;
                        food=true;
                    }
                else
                {
                    al.remove(0);
                }
                if(up==true)
                {
                    heady=heady-12;
                    al.add(new SnakeBody(headx,heady));
                    Thread.sleep(50);
                }
                else if(down==true)
                {
                    heady=heady+12;
                    al.add(new SnakeBody(headx,heady));
                    Thread.sleep(50);
                }
                else if(left==true)
                {
                    headx=headx-12;
                    al.add(new SnakeBody(headx,heady));
                    Thread.sleep(50);
                }
                else if(right==true)
                {
                    headx=headx+12;
                    al.add(new SnakeBody(headx,heady));
                    Thread.sleep(50);
                }
                if(heady<=50 || heady>=630 || headx<=50 || headx>=930)
                    {
                        JOptionPane.showMessageDialog(null,"game over");
                        System.exit(0);
                    }
            }
        
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
}
    public static void main(String[] args) 
    {
            Snake s=new Snake();
    }
    public void paint(Graphics g)
    {
        try 
        {
            for(int i=0;i<al.size();i++)
            {
                g.fillOval(al.get(i).a,al.get(i).b,12,12);
                g.setColor(Color.WHITE);
            }
            g.setColor(Color.WHITE);
            g.drawRect(50, 50, 900, 600);
            if(food==true)
            {
                foodx=(int)(100+(900-100)*Math.random());
                foody=(int)(100+(600-100)*Math.random());
                foodx=(int)(foodx/12);
                foody=(int)(foody/12);
                foodx=foodx*12;
                foody=foody*12;
                g.fillOval(foodx, foody,12, 12);
                g.setColor(Color.WHITE);
                food=false;
            }
            else
            {
                g.fillOval(foodx, foody, 12, 12);
                g.setColor(Color.WHITE);
            }
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }

    
    class SnakeBody
    {
        int a,b;
        SnakeBody(int x,int y)
        {
            a=x;
            b=y;
            for(int i=0;i<al.size();i++)
                        {
                            if(al.get(i).a==a && al.get(i).b==b)
                            {
                                JOptionPane.showMessageDialog(null,"game over");
                                System.exit(0);
                                break;
                            }
                        }
            repaint();
        }
    }
    public void keyPressed(KeyEvent e)
    {
        try 
        {
            if(e.getKeyCode()== KeyEvent.VK_LEFT)
            {
                if(t.right==false)
                {
                    t.left=true;
                    t.right=false;
                    t.up=false;
                    t.down=false;
                }
            }
            if(e.getKeyCode()== KeyEvent.VK_RIGHT)
            {
                if(t.left==false)
                {
                    t.left=false;
                    t.right=true;
                    t.up=false;
                    t.down=false;
                }
            }
            if(e.getKeyCode()== KeyEvent.VK_UP)
            {
                if(t.down==false)
                {
                    t.left=false;
                    t.right=false;
                    t.up=true;
                    t.down=false;
                }
            }
            if(e.getKeyCode()== KeyEvent.VK_DOWN)
            {
                if(t.up==false)
                {
                    t.left=false;
                    t.right=false;
                    t.up=false;
                    t.down=true; 
                }
            }
        } 
        catch (Exception ex) 
        {
            ex.printStackTrace();
        }
    }   
    public void keyTyped(KeyEvent e)
    {
    }
    public void keyReleased(KeyEvent e) 
    {
    }
}
