package de.johnadept.dvd_screensaver.client;

import com.mojang.blaze3d.systems.RenderSystem;
import de.johnadept.dvd_screensaver.DVDScreensaver;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

import java.util.Random;

import static de.johnadept.dvd_screensaver.commands.DVDCommand.showOverlay;

public class DVDOverlay {

    private static final ResourceLocation DVD = new ResourceLocation(DVDScreensaver.MOD_ID, "textures/gui/dvd.png");
    private static final int IMAGE_WIDTH = 170;
    private static final int IMAGE_HEIGHT = 90;

    private static int x = 0;
    private static int y = 0;
    private static int velocityX = 1; // Decreased speed
    private static int velocityY = 1; // Decreased speed

    // Adjusted scale factors to align hitbox edges with the edges of the larger image
    private static final float SCALE_X = (float) IMAGE_WIDTH / 2330;
    private static final float SCALE_Y = (float) IMAGE_HEIGHT / 1026;

    // Shader constants
    private static final ResourceLocation SHADER = new ResourceLocation(DVDScreensaver.MOD_ID, "shaders/dvd_color.glsl");

    private static float colorR = 1.0f;
    private static float colorG = 1.0f;
    private static float colorB = 1.0f;

    // Array of possible colors
    private static final float[][] COLORS = {
            {1.0f, 0.0f, 1.0f}, // Purple
            {0.0f, 1.0f, 0.0f}, // Green
            {1.0f, 0.0f, 0.0f}, // Red
            {0.0f, 0.0f, 1.0f}, // Blue
            {1.0f, 1.0f, 0.0f}, // Yellow
            {0.0f, 1.0f, 1.0f}, // Cyan
            {1.0f, 0.5f, 0.0f}, // Orange
    };

    private static final Random RANDOM = new Random();

    public static final IGuiOverlay HUD_DVD = ((gui, guiGraphics, partialTick, screenWidth, screenHeight) -> {
        if (showOverlay) {
            x += velocityX;
            y += velocityY;

            // Reverse direction if hitting edges
            if (x <= 0 || x + IMAGE_WIDTH >= screenWidth) {
                velocityX *= -1;
                // Change the color when hitting the edge
                changeColor();
            }
            if (y <= 0 || y + IMAGE_HEIGHT >= screenHeight) {
                velocityY *= -1;
                // Change the color when hitting the edge
                changeColor();
            }

            // Render a slightly transparent black background behind the logo
            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderColor(colorR, colorG, colorB, 1.0f);
            RenderSystem.setShaderTexture(0, DVD);

            // Use the scale factors to align hitbox edges with image edges
            int scaledWidth = (int) (2330 * SCALE_X);
            int scaledHeight = (int) (1026 * SCALE_Y);
            guiGraphics.fill(0, 0, screenWidth, screenHeight, 0x90000000);
            guiGraphics.blit(DVD, x, y, 0, 0, scaledWidth, scaledHeight, IMAGE_WIDTH, IMAGE_HEIGHT);

        }
    });

    // Method to change the color randomly
    private static void changeColor() {
        // Randomly select a color from COLORS array
        float[] newColor = COLORS[RANDOM.nextInt(COLORS.length)];
        colorR = newColor[0];
        colorG = newColor[1];
        colorB = newColor[2];
    }
}
