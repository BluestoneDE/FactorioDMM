package factorio.encoders;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import factorio.object.Blueprint;
import java.util.Base64;
import java.util.zip.*;

public class BlueprintStringEncoder {
    private Deflater _deflater;
    private Base64.Encoder _Base64Encoder;
    private Blueprint _blueprint;
    private String _json;
    private byte[] _compressedData;
    private String _bluePrintString;


    public BlueprintStringEncoder(Blueprint blueprint){
        _deflater = new Deflater(9);
        _Base64Encoder = Base64.getEncoder();
        _blueprint = blueprint;
    }

    public BlueprintStringEncoder EncodeJson(){
        GsonBuilder gb  = new GsonBuilder();
        Gson g = gb.create();
        _json = g.toJson(_blueprint);
        return this;
    }

    private BlueprintStringEncoder Gzip(){
        _deflater.setInput(_json.getBytes());
        _compressedData = new byte[_deflater.getTotalOut()];
        _deflater.deflate(_compressedData);
        return this;
    }

    private BlueprintStringEncoder Base64Encode(){
        byte[] encoded = _Base64Encoder.encode(_compressedData);
        _bluePrintString = new String(encoded);
        return this;
    }

    private String Finalize(){
        if(_bluePrintString == null) throw new IllegalStateException("Blueprint String was not Formed Properly");
        return _bluePrintString;
    }

    public static String Encode(Blueprint blueprintToEncode){
        return new BlueprintStringEncoder(blueprintToEncode).EncodeJson().Gzip().Base64Encode().Finalize();
    }
}
