package web;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import model.Videos;

public class GetItemListDeserializer implements JsonDeserializer<List<Videos>> {
    @Override
    public List<Videos> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        List videos = new ArrayList<>();
        //getting first time from object
        final JsonObject jsonObject = json.getAsJsonObject();

        final JsonArray itemsJsonArray = jsonObject.getAsJsonArray("categories");
        //get the first element of array(also try to cast first element of array to object)
        final JsonObject jsonObject1 = (JsonObject) itemsJsonArray.get(0);
        //get videos array
        final JsonArray videosJsonArray = jsonObject1.getAsJsonArray("videos");
        //iterateThisArrayAndSetOneByOneObject

        for(JsonElement itemsJsonElement : videosJsonArray){
            final JsonObject videoJsonObject = itemsJsonElement.getAsJsonObject();
            final String subtitle = videoJsonObject.get("subtitle").getAsString();
            final String sources = videoJsonObject.get("sources").getAsString();
            final String thumb = videoJsonObject.get("thumb").getAsString();
            final String imageSmall = videoJsonObject.get("imageSmall").getAsString();
            final String imageLarge = videoJsonObject.get("imageLarge").getAsString();
            final String title = videoJsonObject.get("title").getAsString();
            final String studio = videoJsonObject.get("studio").getAsString();
            videos.add(new Videos(subtitle, sources, thumb, imageSmall, imageLarge, title, studio));
        }
        //return this list
        return videos;
    }
}
