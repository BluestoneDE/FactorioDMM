package factorio.encoders;

import com.google.gson.*;
import factorio.object.Blueprint;
import factorio.object.Signal;

import java.lang.reflect.Type;
import java.util.Base64;
import java.util.zip.Deflater;

public class BlueprintStringEncoder {
    private final Deflater _deflater;
    private final Base64.Encoder _Base64Encoder;
    private final Blueprint _blueprint;
    private String _json;
    private byte[] _compressedData;
    private String _bluePrintString;


    public BlueprintStringEncoder(Blueprint blueprint){
        _deflater = new Deflater(9);
        _Base64Encoder = Base64.getEncoder();
        _blueprint = blueprint;
    }

    public BlueprintStringEncoder EncodeJson(){
        _json = new GsonBuilder()
                .registerTypeAdapter(Signal.class, new SignalSerializer())
                .excludeFieldsWithoutExposeAnnotation().disableHtmlEscaping()
                .create().toJson(_blueprint);
        _json = "{\"blueprint\":" + _json + "}";
        return this;
    }

    private BlueprintStringEncoder Gzip(){
        byte[] uncompressed = _json.getBytes();
        int length;
        byte[] oversized;
        try{
            _deflater.setInput(uncompressed);
            _deflater.finish();
            oversized = new byte[uncompressed.length];
            length = _deflater.deflate(oversized);
        }
        finally {
            _deflater.end();//Cleanup
        }
        _compressedData = new byte[length];
        System.arraycopy(oversized, 0, _compressedData, 0, length);
        return this;
    }

    private BlueprintStringEncoder Base64Encode(){
        byte[] encoded = _Base64Encoder.encode(_compressedData);
        _bluePrintString = new String(encoded);
        return this;
    }

    private String Finalize(){
        if(_bluePrintString == null) throw new IllegalStateException("Blueprint String was not Formed Properly");
        return "0" + _bluePrintString;//Version 0 for factorio reasons
    }

    public static String Encode(Blueprint blueprintToEncode){
        return new BlueprintStringEncoder(blueprintToEncode).EncodeJson().Gzip().Base64Encode().Finalize();
    }

    private static class SignalSerializer implements JsonSerializer<Signal> {
        @Override
        public JsonElement serialize(Signal src, Type typeOfSrc, JsonSerializationContext context) {
            JsonObject result = new JsonObject();
            result.addProperty("name", src.name);
            result.addProperty("type", src.type);
            return result;
        }
    }
}
