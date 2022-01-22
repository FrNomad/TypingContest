package TajaGame;

import java.util.Random;
import java.util.regex.Pattern;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import net.md_5.bungee.api.ChatColor;

public class Status3 extends BukkitRunnable{
	
	Random rd = new Random();
	
	
	TajaMain plugin;
	public static int s;
	public static int deathline;
	public static boolean ifseted;
	
	public Status3(TajaMain p) {
		s = 0;
		deathline = 0;
		ifseted = false;
		plugin = p;
	}
	
	public void reset() {
		s = 0;
		deathline = 0;
		ifseted = false;
	}
	
	public void setQuestion(String s) {
		ifseted = true;
		TajaMain.q = s;
	}
	
	@Override
	public void run() {
		if(TajaMain.status == 3 && TajaMain.ifstart) {
			s++;
			if(s >= 0 && s < 21) {
				TajaMain.NoticeTitleOther(TajaMain.mainP, ChatColor.AQUA + TajaMain.mainP.getName(), "출제자가 문장을 입력하는 중입니다...", 0, 200, 0);
				TajaMain.mainP.sendTitle(ChatColor.AQUA + TajaMain.mainP.getName(), "문장을 입력해주세요!", 0, 200, 0);
			}
			else if(s >= 21 && s < 41) {
				TajaMain.NoticeTitleOther(TajaMain.mainP, ChatColor.LIGHT_PURPLE + TajaMain.mainP.getName(), "출제자가 문장을 입력하는 중입니다...", 0, 200, 0);
				TajaMain.mainP.sendTitle(ChatColor.LIGHT_PURPLE + TajaMain.mainP.getName(), "문장을 입력해주세요!", 0, 200, 0);
			}
			else if(s > 40) {
				s = 0;
				deathline++;
			}
			
			if(deathline > 30) {
				TajaMain.Notice(ChatColor.LIGHT_PURPLE + "출제자가 잠수인것으로 판별되어 다음 턴으로 넘어갑니다.");
				TajaMain.status = 1;
				this.reset();
			}
			
			if(ifseted) {
				TajaMain.status = 2;
				this.reset();
			}
		}
	}
}
