package AntGame;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WoodTest {
    private Wood wood = new Wood(30);
    private Ant ant = new Ant();

    @Before
    public void setUp() throws Exception {
        ant.setPosition(0);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testIsOut1() {
        wood.isOut(ant);
        boolean result = ant.getAlive();
        Assert.assertEquals(false, result);
    }

    @Test
    public void testIsOut2(){
        ant.setPosition(31);
        wood.isOut(ant);
        boolean result = ant.getAlive();
        Assert.assertEquals(false, result);
    }
}