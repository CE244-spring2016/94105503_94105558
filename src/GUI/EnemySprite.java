package GUI;

import java.awt.image.BufferedImage;

/**
 * Created by ruhollah on 7/7/2016.
 */
public class EnemySprite
{
    private BufferedImage enemyImage;
    private Integer id;
    private BattleScenario.EnemyLabel enemyLabel;

    public EnemySprite(BufferedImage bufferedImage, Integer id)
    {
        this.enemyImage = bufferedImage;
        this.id = id;
    }

    public BufferedImage getEnemyImage()
    {
        return enemyImage;
    }

    public void setEnemyImage(BufferedImage enemyImage)
    {
        this.enemyImage = enemyImage;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public BattleScenario.EnemyLabel getEnemyLabel()
    {
        return enemyLabel;
    }

    public void setEnemyLabel(BattleScenario.EnemyLabel enemyLabel)
    {
        this.enemyLabel = enemyLabel;
    }
}
