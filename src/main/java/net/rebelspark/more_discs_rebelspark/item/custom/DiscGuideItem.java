package net.rebelspark.more_discs_rebelspark.item.custom;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.WrittenBookItem;
import net.minecraft.world.level.Level;
import net.rebelspark.more_discs_rebelspark.screen.disc_guide.*;
import org.jspecify.annotations.NonNull;

public class DiscGuideItem extends WrittenBookItem {
    public DiscGuideItem(Properties properties) {
        super(properties);
    }
    @Override
    public @NonNull InteractionResult use(final Level level, final @NonNull Player player, final @NonNull InteractionHand hand) {
        if (level.isClientSide()) {
            Minecraft.getInstance().gui.setScreen(new DiscGuideScreen1(Component.empty()));
        }
        player.awardStat(Stats.ITEM_USED.get(this));
        player.playSound(SoundEvents.BOOK_PAGE_TURN);
        return InteractionResult.SUCCESS;
    }

}
