import java.util.ArrayList;
import java.util.List;
import java.time.LocalTime;

public class BulletPool {
    private List<Bullet> bullets = new ArrayList<Bullet>();

    private Thread poolSizeManager;
    private LocalTime time = null;
    

    public BulletPool(Game game) {
        int size = 30;
        for(int i = 0; i < size; i ++) {
            bullets.add(new Bullet(-999, -999, 0, 0));
        }
        poolSizeManager = new Thread() {
            @Override
            public void run() {
                while (game.alive) {
                    if (bullets.size() > 30 && time == null) {
                        time = LocalTime.now().withNano(0);
                    }
                    if (LocalTime.now().withNano(0).minusSeconds(30).equals(time)) {
                        bullets = bullets.subList(0, 30);
                        time = null;
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        poolSizeManager.start();
    }

    public Bullet requestBullet(int x, int y, int dx, int dy) {
        if (bullets.size() == 0) {
            bullets.add(new Bullet(-999, -999, 0, 0));
        }
        Bullet bullet = bullets.remove(0);
        bullet.refreshState(x, y, dx, dy);
        return bullet;
    }

    public void releaseBullet(Bullet bullet) {
        bullets.add(bullet);
    }
}
