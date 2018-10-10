package AntGame;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CreepGameTest {
    private CreepGame game = new CreepGame();

    @Before
    public void setUp() throws Exception {
        for(int i = 0; i < game.ants.length; i++){
            game.ants[i].setDirection(true);
            game.ants[i].setPosition(10 + i);
        }
        game.ants[4].setDirection(false);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void checkCollision() {
        game.checkCollision();
        boolean left = game.ants[3].getDirection();
        boolean right = game.ants[4].getDirection();
        Assert.assertEquals(false, left);
        Assert.assertEquals(true, right);
    }

}