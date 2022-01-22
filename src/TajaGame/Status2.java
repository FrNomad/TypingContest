package TajaGame;

import java.util.Random;
import java.util.regex.Pattern;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import net.md_5.bungee.api.ChatColor;

public class Status2 extends BukkitRunnable{
	
	Random rd = new Random();
	
	
	TajaMain plugin;
	public int s;
	public static int sec;
	public static boolean ifseted;
	public static boolean iftimeover;
	
	public Status2(TajaMain p) {
		s = 0;
		sec = 10;
		ifseted = false;
		iftimeover = false;
		plugin = p;
	}
	
	public void reset() {
		s = 0;
		sec = 10;
		ifseted = false;
		iftimeover = false;
	}
	
	public void correct(Player p) {
		if(!ifseted && !iftimeover) {
			ifseted = true;
			iftimeover = false;
			TajaMain.mainP = p;
		}
	}
	
	@Override
	public void run() {
		if(TajaMain.status == 2 && TajaMain.ifstart) {
			s++;
			
			if(s > 20 && sec > 0 && !ifseted) {
				s = 0;
				sec--;
			}
			
			if(ifseted) {
				if(s == 0) {
					TajaMain.Playsound(Sound.ENTITY_PLAYER_LEVELUP);
				}
				else if(s < 41) {
					TajaMain.NoticeTitleOther(TajaMain.mainP, ChatColor.GOLD + TajaMain.mainP.getName(), "아... 아쉽습니다!", 0, 200, 0);
					TajaMain.mainP.sendTitle(ChatColor.GOLD + TajaMain.mainP.getName(), "정답! 축하드려요~!", 0, 200, 0);
				}
				else if(s > 40) {
					TajaMain.status = 3;
					this.reset();
				}
			}
			else {
				if(sec > 3) {
					TajaMain.NoticeTitle(ChatColor.GOLD + Integer.toString(sec), TajaMain.q, 0, 200, 0);
				}
				else if(sec > 0 && sec <= 3) {
					if(s % 2 == 1) {
						TajaMain.NoticeTitle(ChatColor.GOLD + Integer.toString(sec), TajaMain.q, 0, 200, 0);
					}
					else {
						TajaMain.NoticeTitle(ChatColor.RED + Integer.toString(sec), TajaMain.q, 0, 200, 0);
					}
				}
				else if(sec == 0) {
					iftimeover = true;
					if(s == 0) {
						TajaMain.Playsound(Sound.BLOCK_ANVIL_LAND);
					}
					else if(s > 0 && s < 5) {
						if(s % 2 == 1) {
							TajaMain.NoticeTitle(ChatColor.RED + "TIME OVER!", "", 0, 200, 0);
						}
						else {
							TajaMain.NoticeTitle(ChatColor.GOLD + "TIME OVER!", "", 0, 200, 0);
						}
					}
					else if(s >= 5 && s < 26) {
						TajaMain.NoticeTitle(ChatColor.RED + "TIME OVER!", "", 0, 200, 0);
					}
					else if(s > 25) {
						TajaMain.status = 1;
						this.reset();
					}
				}
			}
		}
	}
}
