package net.rebelspark.more_discs_rebelspark.item.custom;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.WrittenBookItem;
import net.minecraft.world.level.Level;
import net.rebelspark.more_discs_rebelspark.screen.DiscGuideScreen1;

public class DiscGuide extends WrittenBookItem {
    public DiscGuide(Properties properties) {
        super(properties);
    }
    @Override
    public InteractionResult use(final Level level, final Player player, final InteractionHand hand) {
        if (level.isClientSide()) {
            Minecraft.getInstance().gui.setScreen(new DiscGuideScreen1(Component.empty()));
        }
        player.awardStat(Stats.ITEM_USED.get(this));
        return InteractionResult.SUCCESS;
    }

}
