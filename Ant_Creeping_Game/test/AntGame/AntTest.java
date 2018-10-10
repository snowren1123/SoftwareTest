package AntGame;

import AntGame.Ant;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AntTest {
    private Ant ant=new Ant();

    @Before
    public void setUp() throws Exception {
        ant.setDirection(true);
        ant.setPosition(20);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void changeDirection() {
        ant.changeDirection();
        boolean result = ant.getDirection();
        Assert.assertEquals(false,result);
    }

    @Test
    public void creep() {
       ant.creep();
       int result = ant.getPosition();
       Assert.assertEquals(21, result);
    }
}