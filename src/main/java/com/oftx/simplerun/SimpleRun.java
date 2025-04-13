package com.oftx.simplerun;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class SimpleRun extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getCommand("run").setExecutor(new RunCommandExecutor());
        this.getCommand("exit").setExecutor(new RunCommandExecutor());
        this.getCommand("quit").setExecutor(new RunCommandExecutor());
    }

    @Override
    public void onDisable() {
        // 插件禁用时的逻辑
    }

    // 命令执行器类
    public class RunCommandExecutor implements CommandExecutor {

        @Override
        public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
            // 确保命令发送者是玩家
            if (sender instanceof Player) {
                Player player = (Player) sender;
                String playerName = player.getName();
                String commandName = command.getName();

                // 广播消息给所有玩家
                if (commandName.equals("run")) {
                    String message = "<" + playerName + "> /run";
                    Bukkit.broadcastMessage(message);
                    player.kickPlayer("§a跑路成功§r");
                } else if (commandName.equals("exit") || commandName.equals("quit")) {
                    player.kickPlayer("§a已退出游戏，再见！§r");
                }

                return true;
            } else {
                sender.sendMessage("This command can only be executed by a player.");
                return false;
            }
        }
    }
}