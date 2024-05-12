package factorio.decoders.GifDecoder;

import java.awt.image.BufferedImage;

public class BufferedImageWithDelay {
    public final int delay;
    public final BufferedImage image;

    public BufferedImageWithDelay(int delay, BufferedImage image) {
        this.delay = delay;
        this.image = image;
    }
}
