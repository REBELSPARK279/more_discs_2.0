package net.rebelspark.more_discs_rebelspark.item.custom.discHolders;

import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemProvider;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.BundleContents;
import net.minecraft.world.item.component.ContainerComponent;
import net.minecraft.world.item.slot.SlotCollection;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class DiscHolderContents extends BundleContents.Mutable implements ContainerComponent<BundleContents>, TooltipComponent {


    public static boolean canItemBeInBundle(final ItemStack itemToAdd) {
        return !itemToAdd.isEmpty() && itemToAdd.getItem().canFitInsideContainerItems();
    }

    /// @param newContents
    /// @return
    @Override
    public BundleContents copyWithContents(Stream<ItemStack> newContents) {
        return null;
    }

    /// @return
    @Override
    public Mutable<BundleContents> asMutable() {
        return null;
    }

    /// @param items
    /// @return
    @Override
    public int replaceSlotItems(ItemProvider items) {
        return super.replaceSlotItems(items);
    }

    /// @param consumer
    @Override
    public void modifySlots(Consumer<? super SlotAccess> consumer) {
        super.modifySlots(consumer);
    }

    /// @param predicate
    /// @return
    @Override
    public SlotCollection filter(Predicate<? super ItemStack> predicate) {
        return super.filter(predicate);
    }

    /// @param mapper
    /// @return
    @Override
    public SlotCollection flatMap(Function<ItemStack, ? extends SlotCollection> mapper) {
        return super.flatMap(mapper);
    }

    /// @param limit
    /// @return
    @Override
    public SlotCollection limit(int limit) {
        return super.limit(limit);
    }
}
