package dev.huskcasaca.effortless.screen.widget;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.huskcasaca.effortless.screen.ScreenUtils;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;

import javax.annotation.ParametersAreNonnullByDefault;

@Environment(EnvType.CLIENT)
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class ExtendedButton extends Button {
    public ExtendedButton(int posX, int posY, int width, int height, Component displayString, OnPress handler) {
        super(posX, posY, width, height, displayString, handler, Button.DEFAULT_NARRATION);
    }


    private int getTextureY() {
        int i = 1;
        if (!this.active) {
            i = 0;
        } else if (this.isHoveredOrFocused()) {
            i = 2;
        }

        return 46 + i * 20;
    }

    /**
     * Draws this button to the screen.
     */
    @Override
    public void renderWidget(PoseStack poseStack, int mouseX, int mouseY, float partialTick) {
        Minecraft mc = Minecraft.getInstance();
        int k = this.getTextureY();
        ScreenUtils.blitWithBorder(poseStack, WIDGETS_LOCATION, this.getX(), this.getY(), 0, 46 + k * 20, this.width, this.height, 200, 20, 2, 3, 2, 2, 1);
        //this.renderBg(poseStack, mc, mouseX, mouseY);

        Component buttonText = this.getMessage();
        int strWidth = mc.font.width(buttonText);
        int ellipsisWidth = mc.font.width("...");

        if (strWidth > width - 6 && strWidth > ellipsisWidth)
            //TODO, srg names make it hard to figure out how to append to an ITextProperties from this trim operation, wraping this in StringTextComponent is kinda dirty.
            buttonText = Component.literal(mc.font.substrByWidth(buttonText, width - 6 - ellipsisWidth).getString() + "...");

        drawCenteredString(poseStack, mc.font, buttonText, this.getX() + this.width / 2, this.getY() + (this.height - 8) / 2, 14737632);
    }
}
