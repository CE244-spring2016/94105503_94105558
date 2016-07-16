package GUI;

import java.awt.image.BufferedImage;
import Controller.*;

/**
 * Created by ruhollah on 7/7/2016.
 */
public class EnemySprite
{
    private UltimateImage enemyImage;
    private Integer id;
    private BattleScenario.EnemyLabel enemyLabel;

    public EnemySprite(UltimateImage ultimateImage, Integer id)
    {
        this.enemyImage = ultimateImage;
        this.id = id;
    }

    public UltimateImage getEnemyImage()
    {
        return enemyImage;
    }

    public void setEnemyImage(UltimateImage enemyImage)
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
