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
		say("Console Chat Plugin, by CodeZhangBorui, Version 1.0.0");
		say("注册指令……");
		Bukkit.getPluginCommand("cc").setExecutor(this);
		Bukkit.getPluginCommand("setconsoler").setExecutor(this);		
		say("Console Chat Plugin 启用成功！键入 /setconsoler 开始使用");
	}
	
	@Override
	public void onDisable() {
		say("Console Chat Plugin 卸载成功！");
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(command.getName().equalsIgnoreCase("cc")) {
			if(sender instanceof Player) {
				sender.sendMessage("此命令只能在控制台使用！");
				return true;
			}
			if(args.length < 1) {
				sender.sendMessage("参数过少！");
				sender.sendMessage("用法：/cc <信息>");
				return true;
			}
			if(args.length > 2) {
				sender.sendMessage("参数过多！");
				sender.sendMessage("用法：/cc <信息>");
				return true;
			}
			if(consoler == "") {
				sender.sendMessage("你还没有声明你的身份！请使用 /setconsoler <名称> 来设置你的身份。");
				return true;
			}
			Bukkit.getServer().dispatchCommand(
				Bukkit.getConsoleSender(),
				"/tellraw @a \"<" + consoler + "> " + args[0] + "\""
			);
			sender.sendMessage("已成功发送了一条消息");
			return true;
		}
		if(command.getName().equalsIgnoreCase("setconsoler")) {
			if(sender instanceof Player) {
				sender.sendMessage("此命令只能在控制台使用！");
				return true;
			}
			if(args.length < 1) {
				sender.sendMessage("参数过少！");
				sender.sendMessage("用法：/cc <信息>");
				return true;
			}
			if(args.length > 2) {
				sender.sendMessage("参数过多！");
				sender.sendMessage("用法：/cc <信息>");
				return true;
			}
			consoler = args[0];
			sender.sendMessage("已成功设置当前后台使用者：" + consoler);
			return true;
		}
		if(command.getName().equalsIgnoreCase("getconsoler")) {
			if(args.length > 1) {
				sender.sendMessage("参数过多！");
				sender.sendMessage("用法：/cc <信息>");
				return true;
			}
			if(consoler == "") {
				sender.sendMessage("后台还没有过声明他的身份！");
				return true;
			}
			sender.sendMessage("当前后台使用者：" + consoler);
			return true;
		}
		return false;
	}
}
