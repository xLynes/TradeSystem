package de.codingair.tradesystem.trade.listeners;

import de.codingair.codingapi.tools.time.TimeList;
import de.codingair.tradesystem.TradeSystem;
import de.codingair.tradesystem.trade.commands.TradeCMD;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

public class TradeListener implements Listener {
    private TimeList<Player> players = new TimeList<>();

    @EventHandler
    public void onInteract(PlayerInteractAtEntityEvent e) {
        if(!TradeSystem.getInstance().getTradeManager().isRequestOnRightclick() || players.contains(e.getPlayer())) return;

        if(e.getRightClicked() instanceof Player) {
            Player p = e.getPlayer();
            Player other = (Player) e.getRightClicked();

            if(TradeSystem.getInstance().getTradeManager().isShiftclick() == p.isSneaking()) {
                players.add(p, 1);
                TradeCMD.request(p, other);
            }
        }
    }

}
