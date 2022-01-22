package TajaGame;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

public class TajaInGame implements Listener {
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void IfChat(PlayerChatEvent e) {
		if(TajaMain.ifstart) {
			if(TajaMain.status == 3) {
				if(e.getPlayer() == TajaMain.mainP) {
					if(e.getMessage().length() > 20) {
						e.getPlayer().sendMessage(ChatColor.RED + "20자 이하로 입력하셔야 합니다!");
						e.setCancelled(true);
					}
					else {
						TajaMain.st3.setQuestion(e.getMessage());
					}
				}
			}
			else if(TajaMain.status == 2) {
				if(e.getMessage().equals(TajaMain.q)) {
					if(e.getPlayer() != TajaMain.mainP || TajaMain.GetPlayerList().size() < 2) {
						if(!TajaMain.st2.ifseted && !TajaMain.st2.iftimeover) {
							TajaMain.st2.correct(e.getPlayer());
							TajaMain.st2.s = -1;
						}
						else;
					}
					else;
				}
			}
			else;
		}
	}
}
