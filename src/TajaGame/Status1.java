package TajaGame;

import java.util.Random;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import net.md_5.bungee.api.ChatColor;

public class Status1 extends BukkitRunnable{
	
	Random rd = new Random();
	
	int rdtime;
	int rdp;
	int rdp1;
	Player mp1;
	Player mp2;
	
	TajaMain plugin;
	public static int s;
	
	public Status1(TajaMain p) {
		s = 0;
		plugin = p;
		rdtime = 0;
		rdp = 0;
		rdp1 = 0;
		mp1 = null;
		mp2 = null;
	}
	
	public void reset() {
		s = 0;
		rdtime = 0;
		rdp = 0;
		rdp1 = 0;
		mp1 = null;
		mp2 = null;
	}
	
	@Override
	public void run() {
		if(TajaMain.status == 1 && TajaMain.ifstart) {
			s++;
			if(s == 1) {
				rdtime = rd.nextInt(20) + 30;
				rdp = rd.nextInt(TajaMain.GetPlayerList().size());
				mp1 = TajaMain.GetPlayerList().get(rdp);
			}
			else if(s > 1 && s <= rdtime) {
				while(true) {
					rdp1 = rd.nextInt(TajaMain.GetPlayerList().size());
					mp2 = TajaMain.GetPlayerList().get(rdp1);
					if(mp1 != mp2 || TajaMain.GetPlayerList().size() < 2) {
						mp1 = mp2;
						break;
					}
				}
				TajaMain.NoticeTitle(TajaMain.RandomChatColor(200, 254) + mp1.getName(), "출제자를 선택하는 중입니다...", 0, 200, 0);
				TajaMain.Playsound(Sound.ENTITY_EXPERIENCE_ORB_PICKUP);
			}
			else if(s == rdtime + 1) {
				while(true) {
					rdp1 = rd.nextInt(TajaMain.GetPlayerList().size());
					mp2 = TajaMain.GetPlayerList().get(rdp1);
					if(mp1 != mp2 || TajaMain.GetPlayerList().size() < 2) {
						mp1 = mp2;
						break;
					}
				}
				TajaMain.Playsound(Sound.ENTITY_PLAYER_LEVELUP);
			}
			else if(s > rdtime + 1 && s < rdtime + 10) {
				if(s % 2 == 1) {
					TajaMain.NoticeTitleOther(mp1, ChatColor.GOLD + mp1.getName(), "출제자가 아니군요! 다음 기회를 노려보아요!", 0, 200, 0);
					mp1.sendTitle(ChatColor.GOLD + mp1.getName(), "출제자입니다! 축하드려요!", 0, 200, 0);
				}
				else {
					TajaMain.NoticeTitleOther(mp1, ChatColor.RED + mp1.getName(), "출제자가 아니군요! 다음 기회를 노려보아요!", 0, 200, 0);
					mp1.sendTitle(ChatColor.RED + mp1.getName(), "출제자입니다! 축하드려요!", 0, 200, 0);
				}
			}
			else if(s > rdtime + 30) {
				TajaMain.status = 3;
				TajaMain.mainP = mp1;
				this.reset();
			}
			
		}
	}
}
