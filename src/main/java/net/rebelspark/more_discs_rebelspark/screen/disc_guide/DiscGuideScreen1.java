package net.rebelspark.more_discs_rebelspark.screen.disc_guide;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.jspecify.annotations.NonNull;

public class DiscGuideScreen1 extends Screen {
    public DiscGuideScreen1(Component title) {
        super(title);
    }

    @Override
    protected void init() {
        Button closeButton = Button.builder(Component.literal("X"), (button) -> {
            Minecraft.getInstance().gui.setScreen(null);
        }).bounds(10, 10, 20, 20).build();

        Button nextButton = Button.builder(Component.literal("next"), (button) -> {
            Minecraft.getInstance().gui.setScreen(new DiscGuideScreen2(Component.empty(), Minecraft.getInstance().gui.screen()));
        }).bounds(100, 100, 100, 30).build();

        this.addRenderableWidget(closeButton);
        this.addRenderableWidget(nextButton);
    }

    @Override
    public void extractRenderState(@NonNull GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta) {
        super.extractRenderState(graphics, mouseX, mouseY, delta);

        graphics.text(this.font, "Hi", 40, 40 - this.font.lineHeight - 10, 0xFFFFFF, true);
    }

}
