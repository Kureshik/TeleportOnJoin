package teleportonjoin.main;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        double x = Main.getInstance().getConfig().getDouble("location.x");
        double y = Main.getInstance().getConfig().getDouble("location.y");
        double z = Main.getInstance().getConfig().getDouble("location.z");

        double yaw = Main.getInstance().getConfig().getDouble("direction.yaw");
        double pitch = Main.getInstance().getConfig().getDouble("direction.pitch");

        String world = Main.getInstance().getConfig().getString("world");

        Location loc = new Location(Bukkit.getServer().getWorld(world), x, y, z, (float)yaw, (float)pitch);
        p.teleport(loc);
    }
}
