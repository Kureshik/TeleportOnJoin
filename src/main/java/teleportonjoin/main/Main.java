package teleportonjoin.main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private static Main instance;
    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();
        Bukkit.getPluginManager().registerEvents(new JoinListener(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Main getInstance() {
        return instance;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) return true;
        if (!sender.hasPermission("TeleportOnJoin.cmd")) {
            sender.sendMessage(ChatColor.RED + "You don't have permission.");
            return true;
        }

        Player p = (Player)sender;
        if (args.length == 0) {
            if (cmd.getName().equalsIgnoreCase("teleportonjoin") || cmd.getName().equalsIgnoreCase("tponjoin") || cmd.getName().equalsIgnoreCase("toj")) {
                sender.sendMessage(ChatColor.YELLOW + "Command list:");
                sender.sendMessage(ChatColor.YELLOW + " > " + ChatColor.WHITE + "toj reload: reload plugin config");
                sender.sendMessage(ChatColor.YELLOW + " > " + ChatColor.WHITE + "toj test: teleport you to location in config");
                return true;
            }
        }
        switch (args[0].toLowerCase()) {
            case "reload":
                Main.getInstance().reloadConfig();
                sender.sendMessage(ChatColor.GREEN + "TeleportOnJoin reloaded!");
                return true;
            case "test":
                teleportPlayer(p);
                sender.sendMessage(ChatColor.GREEN + "You just teleported!");
                return true;
            default:
                return false;
        }
    }

    private void teleportPlayer(Player p) {
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
