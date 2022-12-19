package org.eu.codezhangborui.consolechat;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class ConsoleChat extends JavaPlugin {
	public String consoler = "";
	
	public void say(String s) {
		// 用于在控制台输出提示
		CommandSender sender = Bukkit.getConsoleSender();
		sender.sendMessage(s);
	}
	
	@Override
	public void onEnable() {
		say("§b[ConsoleChat]§r Plugin by CodeZhangBorui, Version 1.0.0");
		say("§b[ConsoleChat]§r 注册指令……");
		Bukkit.getPluginCommand("cc").setExecutor(this);
		Bukkit.getPluginCommand("setconsoler").setExecutor(this);
		Bukkit.getPluginCommand("getconsoler").setExecutor(this);
		Bukkit.getPluginCommand("delconsoler").setExecutor(this);
		say("§b[ConsoleChat]§r Console Chat Plugin 启用成功！键入 /setconsoler 开始使用");
	}
	
	@Override
	public void onDisable() {
		say("§b[ConsoleChat]§r Console Chat Plugin 卸载成功！");
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(command.getName().equalsIgnoreCase("cc")) {
			if(sender instanceof Player) {
				sender.sendMessage("§b[ConsoleChat]§r §c此命令只能在控制台使用！");
				return true;
			}
			if(args.length < 1) {
				sender.sendMessage("§b[ConsoleChat]§r §c参数过少！");
				sender.sendMessage("用法：/cc <信息>");
				return true;
			}
			if(consoler == "") {
				sender.sendMessage("§b[ConsoleChat]§r §c你还没有声明你的身份！请使用 /setconsoler <名称> 来设置你的身份。");
				return true;
			}
			String mess = "";
			for(int i = 0; i < args.length; i++) {
				mess = mess + args[i] + " ";
			}
			Bukkit.getServer().dispatchCommand(
				Bukkit.getConsoleSender(),
				"tellraw @a \"<§b" + consoler + "§r> " + mess + "\""
			);
			say("<§b" + consoler + "§r> " + mess);
			return true;
		}
		if(command.getName().equalsIgnoreCase("setconsoler")) {
			if(sender instanceof Player) {
				sender.sendMessage("§b[ConsoleChat]§r §c此命令只能在控制台使用！");
				return true;
			}
			if(args.length < 1) {
				sender.sendMessage("§b[ConsoleChat]§r §c参数过少！");
				sender.sendMessage("用法：setconsoler <名称>");
				return true;
			}
			if(args.length > 1) {
				sender.sendMessage("§b[ConsoleChat]§r §c参数过多！");
				sender.sendMessage("用法：/setconsoler <名称>");
				return true;
			}
			consoler = args[0];
			sender.sendMessage("§b[ConsoleChat]§r 已成功设置当前后台使用者：" + consoler);
			return true;
		}
		if(command.getName().equalsIgnoreCase("getconsoler")) {
			if(args.length > 0) {
				sender.sendMessage("§b[ConsoleChat]§r §c参数过多！");
				sender.sendMessage("用法：/getconsoler");
				return true;
			}
			if(consoler == "") {
				sender.sendMessage("§b[ConsoleChat]§r 后台还没有过声明他的身份！");
				return true;
			}
			sender.sendMessage("§b[ConsoleChat]§r 当前后台使用者：" + consoler);
			return true;
		}
		if(command.getName().equalsIgnoreCase("delconsoler")) {
			if(sender instanceof Player) {
				sender.sendMessage("§b[ConsoleChat]§r §c此命令只能在控制台使用！");
				return true;
			}
			if(args.length > 0) {
				sender.sendMessage("§b[ConsoleChat]§r §c参数过多！");
				sender.sendMessage("用法：/delconsoler");
				return true;
			}
			if(consoler == "") {
				sender.sendMessage("§b[ConsoleChat]§r 后台还没有过声明他的身份！");
				return true;
			}
			consoler = "";
			sender.sendMessage("§b[ConsoleChat]§r 已成功移除当前后台使用者！");
			return true;
		}
		return false;
	}
}
