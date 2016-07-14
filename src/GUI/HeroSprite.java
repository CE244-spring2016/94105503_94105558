package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;

/**
 * Created by ruhollah on 6/27/2016.
 */
public class HeroSprite
{
    private int width = 32;
    private int height = 32;
    private int dx = 0;
    private int dy = 0;
    private int x;
    private int y;
    private ArrayList<JLabel> images = new ArrayList<>();
    private JLabel currentPosition = new JLabel();
    private int prevMoveCode = 0;
    private boolean walking = false;
    private WalkingForm walkingFormID = WalkingForm.SecondStep;
    private BufferedImage allImages;

    public HeroSprite(BufferedImage bufferedImage)
    {
        this.allImages = bufferedImage;
        show(bufferedImage);
    }

    private void show(BufferedImage bufferedImage)
    {
        BufferedImage[] sprites = new BufferedImage[12];
        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                if (bufferedImage != null)
                {
                    sprites[(i * 3) + j] = bufferedImage.getSubimage(j * width, i * height, width, height);
                    images.add(new JLabel(new ImageIcon(sprites[(i * 3) + j])));
                }
            }
        }
        x = 0;
        y = 0;
        currentPosition.setIcon(getFirstUpImage().getIcon());
    }

    public void keyReleased(KeyEvent e)
    {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT)
        {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT)
        {
            dx = 0;
        }

        if (key == KeyEvent.VK_UP)
        {
            dy = 0;
        }

        if (key == KeyEvent.VK_DOWN)
        {
            dy = 0;
        }
    }

    public void keyPressed(KeyEvent e)
    {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT)
        {
            dx = -8;
            if(prevMoveCode != key)
            {
                if(walkingFormID != WalkingForm.FirstStep)
                {
                    currentPosition.setIcon(getSecondLeftImage().getIcon());
                    walkingFormID = WalkingForm.FirstStep;
                }
                else
                {
                    currentPosition.setIcon(getThirdLeftImage().getIcon());
                    walkingFormID = WalkingForm.SecondStep;
                }
                prevMoveCode = key;
                walking = true;
            }
            else if(walking)
            {
                walking = false;
                prevMoveCode = 1;
                currentPosition.setIcon(getFirstLeftImage().getIcon());
            }
        }

        if (key == KeyEvent.VK_RIGHT)
        {
            dx = 8;
            if(prevMoveCode != key)
            {
                if(walkingFormID != WalkingForm.FirstStep)
                {
                    currentPosition.setIcon(getSecondRightImage().getIcon());
                    walkingFormID = WalkingForm.FirstStep;
                }
                else
                {
                    currentPosition.setIcon(getThirdRightImage().getIcon());
                    walkingFormID = WalkingForm.SecondStep;
                }
                prevMoveCode = key;
                walking = true;
            }
            else if(walking)
            {
                walking = false;
                prevMoveCode = 1;
                currentPosition.setIcon(getFirstRightImage().getIcon());
            }
        }

        if (key == KeyEvent.VK_UP)
        {
            dy = -8;
            if(prevMoveCode != key)
            {
                if(walkingFormID != WalkingForm.FirstStep)
                {
                    currentPosition.setIcon(getSecondUpImage().getIcon());
                    walkingFormID = WalkingForm.FirstStep;
                }
                else
                {
                    currentPosition.setIcon(getThirdUpImage().getIcon());
                    walkingFormID = WalkingForm.SecondStep;
                }
                prevMoveCode = key;
                walking = true;
            }
            else if(walking)
            {
                walking = false;
                prevMoveCode = 1;
                currentPosition.setIcon(getFirstUpImage().getIcon());
            }
        }

        if (key == KeyEvent.VK_DOWN)
        {
            dy = 8;
            if(prevMoveCode != key)
            {
                if(walkingFormID != WalkingForm.FirstStep)
                {
                    currentPosition.setIcon(getSecondDownImage().getIcon());
                    walkingFormID = WalkingForm.FirstStep;
                }
                else
                {
                    currentPosition.setIcon(getThirdDownImage().getIcon());
                    walkingFormID = WalkingForm.SecondStep;
                }
                prevMoveCode = key;
                walking = true;
            }
            else if(walking)
            {
                walking = false;
                prevMoveCode = 1;
                currentPosition.setIcon(getFirstDownImage().getIcon());
            }
        }
    }

    public void move()
    {
        x += dx;
        y += dy;
    }

    public void moveBack()
    {
        x -= dx;
        y -= dy;
    }

    public void changeSide(KeyEvent e)
    {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT)
        {
            currentPosition.setIcon(getFirstLeftImage().getIcon());
            prevMoveCode = key;
        }

        if (key == KeyEvent.VK_RIGHT)
        {
            currentPosition.setIcon(getFirstRightImage().getIcon());
            prevMoveCode = key;
        }

        if (key == KeyEvent.VK_UP)
        {
            currentPosition.setIcon(getFirstUpImage().getIcon());
            prevMoveCode = key;
        }

        if (key == KeyEvent.VK_DOWN)
        {
            currentPosition.setIcon(getFirstDownImage().getIcon());
            prevMoveCode = key;
        }
    }

    public int getWidth()
    {
        return width;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    public int getHeight()
    {
        return height;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

    public JLabel getFirstDownImage()
    {
        return images.get(1);
    }

    public JLabel getSecondDownImage()
    {
        return images.get(0);
    }

    public JLabel getThirdDownImage()
    {
        return images.get(2);
    }

    public JLabel getFirstRightImage()
    {
        return images.get(7);
    }

    public JLabel getSecondRightImage()
    {
        return images.get(6);
    }

    public JLabel getThirdRightImage()
    {
        return images.get(8);
    }

    public JLabel getFirstLeftImage()
    {
        return images.get(4);
    }

    public JLabel getSecondLeftImage()
    {
        return images.get(3);
    }

    public JLabel getThirdLeftImage()
    {
        return images.get(5);
    }

    public JLabel getFirstUpImage()
    {
        return images.get(10);
    }

    public JLabel getSecondUpImage()
    {
        return images.get(9);
    }

    public JLabel getThirdUpImage()
    {
        return images.get(11);
    }

    public void setImage(ArrayList<JLabel> image)
    {
        this.images = image;
    }

    public ArrayList<JLabel> getImage()
    {
        return images;
    }

    public int getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public int getY()
    {
        return y;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public JLabel getCurrentPosition()
    {
        return currentPosition;
    }

    public void setCurrentPosition(JLabel currentPosition)
    {
        this.currentPosition = currentPosition;
    }

    public boolean isWalking()
    {
        return walking;
    }

    public void setWalking(boolean walking)
    {
        this.walking = walking;
    }

    public BufferedImage getAllImages()
    {
        return allImages;
    }

    public void setAllImages(BufferedImage allImages)
    {
        this.allImages = allImages;
    }

    enum WalkingForm
    {
        FirstStep, SecondStep
    }
}
