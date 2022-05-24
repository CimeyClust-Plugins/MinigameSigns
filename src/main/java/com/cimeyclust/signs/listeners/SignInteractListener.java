package com.cimeyclust.signs.listeners;

import cn.nukkit.blockentity.BlockEntitySign;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.block.BlockBreakEvent;
import cn.nukkit.event.player.PlayerInteractEvent;
import com.cimeyclust.signs.MinigameSigns;
import com.cimeyclust.signs.Signs;

import java.util.ArrayList;
import java.util.List;

public class SignInteractListener implements Listener {
    private MinigameSigns minigameSigns;

    public SignInteractListener(MinigameSigns minigameSigns) {
        this.minigameSigns = minigameSigns;
    }

    @EventHandler
    public void onBreakSign(BlockBreakEvent event) {
        BlockEntitySign sign = (BlockEntitySign) event.getBlock().getLevel().getBlockEntity(event.getBlock().getLocation());

        if (sign == null) return;

        Signs signs = Signs.getSigns(sign);
        if (signs == null) return;

        if (event.getPlayer().isOp()) {
            List<BlockEntitySign> keys = new ArrayList<>(signs.getPluginSigns().keySet());
            this.minigameSigns.getConfigManager().deleteSign(signs.getPluginInstance().getName(), keys.indexOf(sign));
            signs.getPluginSigns().remove(sign);

            return;
        }

        event.setCancelled(true);
    }

    @EventHandler
    public void onSignInteract(PlayerInteractEvent event) {
        BlockEntitySign sign = (BlockEntitySign) event.getBlock().getLevel().getBlockEntity(event.getBlock().getLocation());

        if (sign == null) return;

        Signs signs = Signs.getSigns(sign);
        if (signs == null) return;

        this.minigameSigns.getServer().dispatchCommand(
                event.getPlayer(),
                signs.getPluginSigns().get(sign).getInteractCommand()
        );

        event.setCancelled(true);
    }
}
