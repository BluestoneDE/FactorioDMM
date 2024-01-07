package factorio.decoders.GifDecoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class GifDecoder {
    public static DecodedGif DecodeGif(File file) throws IOException{
        if(!file.canRead()){
            throw new IOException("Can not read File!");
        }
        FileInputStream stream = new FileInputStream(file);
        GifDecoderInternal decoder = new GifDecoderInternal();
        decoder.read(stream);
        BufferedImageWithDelay[] FrameArray  = new BufferedImageWithDelay[decoder.frameCount];
        for (int i = 0; i < decoder.frameCount; i++){
            FrameArray[i]= new BufferedImageWithDelay(decoder.getDelay(i), decoder.getFrame(i));
        }
        return new DecodedGif(FrameArray);
    }
}


