package com.rumaruka.simplegrinder.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.rumaruka.simplegrinder.common.menu.AbstractGrinderMenu;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.slot.Slot;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public abstract class AbstractGrinderScreen<T extends AbstractGrinderMenu> extends HandledScreen<T> {

    private boolean widthTooNarrow;
    private final Identifier texture;

    public AbstractGrinderScreen(T p_97825_, PlayerInventory p_97827_, Text p_97828_, Identifier p_97829_) {
        super(p_97825_, p_97827_, p_97828_);

        this.texture = p_97829_;
    }

    public void init() {
        super.init();
        this.widthTooNarrow = this.width < 379;
        this.titleX = (this.backgroundWidth - this.textRenderer.getWidth(this.title)) / 2;
    }

    public void handledScreenTick() {
        super.handledScreenTick();

    }
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        if (this.widthTooNarrow) {
            this.drawBackground(matrices, delta, mouseX, mouseY);
        } else {
            super.render(matrices, mouseX, mouseY, delta);
        }

        this.drawMouseoverTooltip(matrices, mouseX, mouseY);
    }

    protected void drawBackground(MatrixStack p_97853_, float p_97854_, int p_97855_, int p_97856_) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, this.texture);
        int i = this.x;
        int j = this.y;
        this.drawTexture(p_97853_, i, j, 0, 0, this.backgroundWidth, this.backgroundWidth);
        if (this.handler.isBurning()) {
            int k = this.handler.getFuelProgress();
            this.drawTexture(p_97853_, i + 56, j + 36 + 12 - k, 176, 12 - k, 14, k + 1);
        }

        int l = this.handler.getCookProgress();
        this.drawTexture(p_97853_, i + 79, j + 34, 176, 14, l + 1, 16);
    }

    public boolean mouseClicked(double p_97834_, double p_97835_, int p_97836_) {

        return this.widthTooNarrow || super.mouseClicked(p_97834_, p_97835_, p_97836_);

    }

    protected void onMouseClick(Slot p_97848_, int p_97849_, int p_97850_, SlotActionType p_97851_) {
        super.onMouseClick(p_97848_, p_97849_, p_97850_, p_97851_);
    }

    public boolean keyPressed(int p_97844_, int p_97845_, int p_97846_) {
        return super.keyPressed(p_97844_, p_97845_, p_97846_);
    }

    protected boolean isClickOutsideBounds(double p_97838_, double p_97839_, int p_97840_, int p_97841_, int p_97842_) {
        boolean flag = p_97838_ < (double) p_97840_ || p_97839_ < (double) p_97841_ || p_97838_ >= (double) (p_97840_ + this.backgroundWidth) || p_97839_ >= (double) (p_97841_ + this.backgroundHeight);
        return flag;
    }

    public boolean charTyped(char p_97831_, int p_97832_) {
        return super.charTyped(p_97831_, p_97832_);
    }


    public void removed() {
        super.removed();
    }
}