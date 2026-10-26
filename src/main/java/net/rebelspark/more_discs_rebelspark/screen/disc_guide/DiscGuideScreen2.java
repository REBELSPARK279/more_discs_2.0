package net.rebelspark.more_discs_rebelspark.screen.disc_guide;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;

@Environment(EnvType.CLIENT)
public class DiscGuideScreen2 extends Screen {
    private static final Identifier DISC_GUIDE_PAGE_2_LOCATION = Identifier.withDefaultNamespace("textures/gui/container/crafting_table.png");

    private Screen parent;
    public DiscGuideScreen2(Component title, Screen parent) {
        super(title);
        this.parent = parent;
    }

    @Override
    protected void init() {
        super.init();
        Button closeButton = Button.builder(Component.literal("Back"), (button) -> {
            Minecraft.getInstance().gui.setScreen(this.parent);
        }).bounds(10, 10, 80, 20).build();

        this.addRenderableWidget(closeButton);
    }


    @Override
    public void extractBackground(final @NonNull GuiGraphicsExtractor g, final int mouseX, final int mouseY, final float a) {
        super.extractBackground(g, mouseX, mouseY, a);
        assert Minecraft.getInstance().level != null;
        g.blit(DISC_GUIDE_PAGE_2_LOCATION,0, 0, g.guiWidth(), g.guiHeight(),0.0f, 1.0f,0.0f, 1.0f);
    }

}
