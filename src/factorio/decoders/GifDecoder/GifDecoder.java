package factorio.decoders.GifDecoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class GifDecoder {
    public static DecodedGif DecodeGif(File file) throws IOException{
        if(!file.canRead()){
            throw new IOException("Can not read File!");
        }
        var stream = new FileInputStream(file);
        var decoder = new GifDecoderInternal();
        decoder.read(stream);
        var FrameArray  = new BufferedImageWithDelay[decoder.frameCount];
        for (var i = 0; i < decoder.frameCount; i++){
            FrameArray[i]= new BufferedImageWithDelay(decoder.getDelay(i), decoder.getFrame(i));
        }
        return new DecodedGif(FrameArray);
    }

    public static DecodedGif DecodeGif(InputStream stream){
        var decoder = new GifDecoderInternal();
        decoder.read(stream);
        var FrameArray  = new BufferedImageWithDelay[decoder.frameCount];
        for (var i = 0; i < decoder.frameCount; i++){
            FrameArray[i]= new BufferedImageWithDelay(decoder.getDelay(i), decoder.getFrame(i));
        }
        return new DecodedGif(FrameArray);
    }
}


