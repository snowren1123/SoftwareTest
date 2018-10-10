package AntGame;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayRoomTest {

    private PlayRoom room = new PlayRoom();

    @Before
    public void setUp() throws Exception {
        room.init_Directions();
        room.setDirs(7);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void setDirs() {
        boolean dir1 = room.creepGame.ants[0].getDirection();
        boolean dir2 = room.creepGame.ants[1].getDirection();
        boolean dir3 = room.creepGame.ants[2].getDirection();
        boolean dir4 = room.creepGame.ants[3].getDirection();
        boolean dir5 = room.creepGame.ants[4].getDirection();
        Assert.assertEquals(false, dir1);
        Assert.assertEquals(false, dir2);
        Assert.assertEquals(true, dir3);
        Assert.assertEquals(true, dir4);
        Assert.assertEquals(true, dir5);
    }
}