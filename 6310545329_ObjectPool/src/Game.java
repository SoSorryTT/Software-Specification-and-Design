import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import org.w3c.dom.ranges.Range;

// import java.time.LocalDateTime;

public class Game extends Observable {
    private BulletPool bulletPool;

    private int width = 600;
    private int height = 600;

    private List<Bullet> bullets;
    private Thread mainLoop;
    private boolean alive;

    public Game() {
        bulletPool = new BulletPool(this);

        alive = true;
        bullets = new ArrayList<Bullet>();
        mainLoop = new Thread() {
            @Override
            public void run() {
                while(alive) {
                    // int check = 30;
                    tick();
                    // int count = 0;
                    // LocalDateTime date = LocalDateTime.now();
                    // int seconds = date.toLocalTime().toSecondOfDay();
                    setChanged();
                    notifyObservers();
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // if (check+seconds == seconds+30) {
                    //     for (int i=30; i<bullets.size(); i++) {
                    //         bullets.remove(i);
                    //         System.out.println(bullets.size());
                    //     }
                    // System.out.println(bullets.size());
                    // }
                }
            }
        };
        mainLoop.start();
    }

    public void tick() {
        // LocalDateTime date = LocalDateTime.now();
        // int seconds = date.toLocalTime().toSecondOfDay();
        moveBullets();
        cleanupBullets();
    }

    private void moveBullets() {
        for(Bullet bullet : bullets) {
            bullet.move();
        }
    }

    private void cleanupBullets() {
        List<Bullet> toRemove = new ArrayList<Bullet>();
        for(Bullet bullet : bullets) {
            if(bullet.getX() <= 0 ||
                    bullet.getX() >= width ||
                    bullet.getY() <= 0 ||
                    bullet.getY() >= height) {
                toRemove.add(bullet);
            }
        }
        for(Bullet bullet : toRemove) {
            bullets.remove(bullet);
            bulletPool.releaseBullet(bullet);
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public List<Bullet> getBullets() {
        return bullets;
    }

    public void burstBullets(int x, int y) {
        bullets.add(bulletPool.requestBullet(x, y, 1, 0));
        bullets.add(bulletPool.requestBullet(x, y, 0, 1));
        bullets.add(bulletPool.requestBullet(x, y, -1, 0));
        bullets.add(bulletPool.requestBullet(x, y, 0, -1));
        bullets.add(bulletPool.requestBullet(x, y, 1, 1));
        bullets.add(bulletPool.requestBullet(x, y, 1, -1));
        bullets.add(bulletPool.requestBullet(x, y, -1, 1));
        bullets.add(bulletPool.requestBullet(x, y, -1, -1));
    }
}
