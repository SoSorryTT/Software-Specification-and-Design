import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class BulletPool {
    private List<Bullet> bullets = new ArrayList<Bullet>();
    private Thread poolSizeManager;
    private LocalTime startTime = null;

    public BulletPool(Game game) {
        int size = 30;
        for(int i = 0; i < size; i ++) {
            bullets.add(new Bullet(-999, -999, 0, 0));
        }

        poolSizeManager = new Thread() {
            @Override
            public void run() {
                while (game.alive) {
                    // I've ignored nanoseconds since it's a pain to deal with.
                    if (bullets.size() > 30 && startTime == null) {
                        startTime = LocalTime.now().withNano(0);
                    }
                    if (LocalTime.now().withNano(0).minusSeconds(30).equals(startTime)) {
                        bullets = bullets.subList(0, 30);
                        startTime = null;
                        System.out.println("Bullet pool size reduced to " + bullets.size() + '!');
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
        if (bullets.isEmpty()) {
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
