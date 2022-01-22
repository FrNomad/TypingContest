package TajaGame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class TajaMain extends JavaPlugin {
	
	static boolean ifstart = false;
	static int status;                  //0 : �Ϲ�, 1 : ������ ����, 2 : Ÿ�� 10��, 3 : ������ �Է�
	static Player mainP;
	static String q;
	
	static Status1 st1;
	static Status2 st2;
	static Status3 st3;
	
	@Override
	public void onEnable() {
		this.getCommand("taja").setTabCompleter(new TabComplete());
		getServer().getPluginManager().registerEvents(new TajaInGame(), this);
		PluginDescriptionFile pdfile = this.getDescription();
		System.out.println(ChatColor.GREEN + "==================================================");
		System.out.println(ChatColor.GREEN + pdfile.getName() + pdfile.getVersion() + "  �÷������� Ȱ��ȭ�Ǿ����ϴ�.");
		System.out.println(ChatColor.GREEN + "==================================================");
		st1 = new Status1(this);
		st1.runTaskTimer(this, 0, 1);
		st2 = new Status2(this);
		st2.runTaskTimer(this, 0, 1);
		st3 = new Status3(this);
		st3.runTaskTimer(this, 0, 1);
	}
	
	@Override
	public void onDisable() {
		PluginDescriptionFile pdfile = this.getDescription();
		System.out.println(ChatColor.GREEN + "==================================================");
		System.out.println(ChatColor.GREEN + pdfile.getName() + pdfile.getVersion() + "  �÷������� ��Ȱ��ȭ�Ǿ����ϴ�.");
		System.out.println(ChatColor.GREEN + "==================================================");
	}
	
	public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
		
		if(label.equalsIgnoreCase("taja")) {
			if(!(s instanceof Player)) {
				s.sendMessage(ChatColor.RED + "�÷��̾ ������ ������ �� �ֽ��ϴ�!");
			}
			else {
				Player p = (Player) s;
				if(args.length < 1) {
					p.sendMessage(ChatColor.RED + "�����Ұ��� �������� �Ⱦ˷��ָ� ��¼�ڴ°ž�?");
				}
				else {
					if(args[0].equalsIgnoreCase("start")) {
						if(ifstart) {
							p.sendMessage(ChatColor.GOLD + "�̹� ������ �������Դϴ�.");
						}
						else {
							st1.reset();
							ifstart = true;
							status = 1;
						}
					}
					else if(args[0].equalsIgnoreCase("stop")) {
						if(ifstart) {
							if(mainP == p) {
								p.sendMessage(ChatColor.GREEN + "Ÿ�ڰ����� ����ϴ�.");
								status = 0;
								ifstart = false;
								NoticeTitle(ChatColor.GOLD + "Ÿ�ڰ��� ����", ChatColor.GREEN + "�����ڰ� Ÿ�ڰ����� �������׽��ϴ�", 0, 70, 30);	
								Playsound(Sound.BLOCK_ENDER_CHEST_OPEN);
							}
							else {
								p.sendMessage(ChatColor.GOLD + "�� ���� �����ڸ� ������ ���ߴ� ���� �����մϴ�.");
							}
						}
						else {
							p.sendMessage(ChatColor.GOLD + "Ÿ�ڰ����� ���۵��� �ʾҽ��ϴ�.");
						}
					}
					else {
						p.sendMessage(ChatColor.RED + "�ùٸ� ��ɾ �Է��Ͻʽÿ�!");
					}
				}	
			}
			
		}
		return false;
	}
	
	public static ChatColor ChatColorByHEX(int r, int g, int b) {
		Color c = new Color(r, g, b);
		return ChatColor.of(c);
	}
	
	public static void NoticeTitle(String maintitle, String subtitle, int fadein, int remain, int fadeout) {
		List<World> worlds = Bukkit.getWorlds();
		for(int j = 0; j < worlds.size(); j++) {
			World w = worlds.get(j);
			List<Player> players = w.getPlayers();
			for(int i = 0; i < players.size(); i++) {
				players.get(i).sendTitle(maintitle, subtitle, fadein, remain, fadeout);
			}
		}
	}
	
	public static void NoticeTitleOther(Player p, String maintitle, String subtitle, int fadein, int remain, int fadeout) {
		List<World> worlds = Bukkit.getWorlds();
		for(int j = 0; j < worlds.size(); j++) {
			World w = worlds.get(j);
			List<Player> players = w.getPlayers();
			for(int i = 0; i < players.size(); i++) {
				if(!(p == players.get(i))) players.get(i).sendTitle(maintitle, subtitle, fadein, remain, fadeout);
			}
		}
	}
	
	public static void Notice(String s) {
		List<World> worlds = Bukkit.getWorlds();
		for(int j = 0; j < worlds.size(); j++) {
			World w = worlds.get(j);
			List<Player> players = w.getPlayers();
			for(int i = 0; i < players.size(); i++) {
				players.get(i).sendMessage(s);;
			}
		}
	}

	
	public static void Playsound(Sound s) {
		List<World> worlds = Bukkit.getWorlds();
		for(int j = 0; j < worlds.size(); j++) {
			World w = worlds.get(j);
			List<Player> players = w.getPlayers();
			for(int i = 0; i < players.size(); i++) {
				players.get(i).playSound(players.get(i).getLocation(), s, 100000000, 1);
			}
		}
		
	}
	
	public static List<Player> GetPlayerList() {
		List<Player> playerlist = new ArrayList<Player>();
		List<World> worlds = Bukkit.getWorlds();
		for(int j = 0; j < worlds.size(); j++) {
			World w = worlds.get(j);
			List<Player> players = w.getPlayers();
			playerlist.addAll(players);
		}
		return playerlist;
	}
	
	public static ChatColor RandomChatColor(int a, int b) {
		Random rd = new Random();
		int rd1 = rd.nextInt(b - a + 1) + a;
		int rd2 = rd.nextInt(b - a + 1) + a;
		int rd3 = rd.nextInt(b - a + 1) + a;
		Color c = new Color(rd1, rd2, rd3);
		return ChatColor.of(c);
	}
	
}
