package Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.google.gson.stream.JsonReader;

public class JsonUtils {
	
	public static final Optional<JsonElement> parseString(String str) {
		try{
			return Optional.ofNullable(JsonParser.parseString(str));
		} catch(Exception e) {
			return Optional.empty();
		}
	}
	
	public static final Optional<JsonObject> parseObjectFromString(String str) {
		Optional<JsonElement> elem=parseString(str);
		if(!elem.isPresent()) return Optional.empty();
		return elem.get().isJsonObject() ? Optional.of(elem.get().getAsJsonObject()) : Optional.empty();
	}
	
	public static final <T extends JsonElement> Optional<T> parseJsonString(String str,Class<T> clazz) {
		final Optional<JsonElement> elem=parseString(str);
		if(!elem.isPresent()) return Optional.empty();
		try{
			return Optional.of(clazz.cast(elem.get()));
		} catch(ClassCastException ex) {
			return Optional.empty();
		} catch(Exception e) {
			return Optional.empty();
		}
	}
	
	public static final <T extends JsonElement> Optional<T> parseJsonFromFile(final File file,Class<T> elementClass) {
		Optional<JsonReader> reader=reader(file);
		if(reader.isPresent()) {
			try{
				final JsonElement elem=JsonParser.parseReader(reader.get());
				return Optional.of(elementClass.cast(elem));
			} catch(Exception e) {
				return Optional.empty();
			}
			
		}
		return Optional.empty();
	}
	
	
	public static final Optional<JsonObject> parseObjectFromFile(String file) {
		Optional<JsonReader> reader=reader(file);
		if(reader.isPresent()) {
			try{
				final JsonElement elem=JsonParser.parseReader(reader.get());
				if(elem.isJsonObject()) {
					return Optional.ofNullable(elem.getAsJsonObject());
				}
			} catch(Exception e) {
				return Optional.empty();
			}
			
		}
		return Optional.empty();
	}
	
	public static final Optional<JsonObject> parseObjectFromFile(final File file) {
		Optional<JsonReader> reader=reader(file);
		if(reader.isPresent()) {
			try{
				final JsonElement elem=JsonParser.parseReader(reader.get());
				if(elem.isJsonObject()) {
					return Optional.ofNullable(elem.getAsJsonObject());
				}
			} catch(Exception e) {
				return Optional.empty();
			}
			
		}
		return Optional.empty();
	}
	
	public static final Optional<JsonArray> parseArrayFromFile(String file) {
		Optional<JsonReader> reader=reader(file);
		if(reader.isPresent()) {
			try{
				final JsonElement elem=JsonParser.parseReader(reader.get());
				if(elem.isJsonArray()) {
					return Optional.ofNullable(elem.getAsJsonArray());
				}
			} catch(Exception e) {
				return Optional.empty();
			}
			
		}
		return Optional.empty();
	}
	
	public static final Optional<JsonArray> parseArrayFromFile(final File file) {
		try {
			return parseArrayFromFile(file.getCanonicalPath());
		} catch (IOException e) {
			Optional.empty();
		}
		return Optional.empty();
	}
	
	public static final Optional<String> get(final JsonObject jobj,String key) {
		if(jobj==null || key==null || key.isEmpty()) return Optional.empty();
		if(!jobj.has(key)) return Optional.empty();
		
		JsonElement elem=jobj.get(key);
		if(elem.isJsonPrimitive()) {
			return Optional.ofNullable(elem.getAsString());
		}
		
		return Optional.empty();
	}
	
	public static final Optional<JsonPrimitive> getJsonPrimitive(final JsonObject jobj,String key) {
		if(jobj==null || key==null || key.isEmpty()) return Optional.empty();
		if(!jobj.has(key)) return Optional.empty();
		
		JsonElement elem=jobj.get(key);
		if(elem.isJsonPrimitive()) {
			return Optional.of((JsonPrimitive)elem);
		}
		
		return Optional.empty();
	}
	
	
	public static final OptionalInt getInt(final JsonObject jobj,String key) {
		if(jobj==null || key==null || key.isEmpty()) return OptionalInt.empty();
		if(!jobj.has(key)) return OptionalInt.empty();
	
		JsonElement elem=jobj.get(key);
		if(elem.isJsonPrimitive()) {
			return OptionalInt.of(elem.getAsInt());
		}
		return OptionalInt.empty();
	}
	
	public static final OptionalDouble getDouble(final JsonObject jobj,String key) {
		if(jobj==null || key==null || key.isEmpty()) return OptionalDouble.empty();
		if(!jobj.has(key)) return OptionalDouble.empty();
		
		JsonElement elem=jobj.get(key);
		if(elem.isJsonPrimitive()) {
			return OptionalDouble.of(elem.getAsDouble());
		}
		return OptionalDouble.empty();
		
	}
	
	public static final Set<String> getKeys(final JsonObject obj) {
		return obj.keySet();
	}
	
	public static final List<JsonElement> getElements(final JsonArray arr) {
		final ArrayList<JsonElement> list=new ArrayList<>();
		Iterator<JsonElement> it=arr.iterator();
		while(it.hasNext()) {
			list.add(it.next());
		}
		return list;
	}
	
	public static final <T> T unboxOptional(Optional<T> opt) {
		return opt.isPresent() ? opt.get() : null;
	}
	
	public static Optional<JsonReader> reader(String res) {
		try {
			return Optional.ofNullable(new Gson().newJsonReader(new InputStreamReader(new FileInputStream(res))));
		} catch (FileNotFoundException e) {
		}
		return Optional.empty();
	}
	
	public static Optional<JsonReader> reader(final File f) {
		try {
			return Optional.ofNullable(new Gson().newJsonReader(new InputStreamReader(new FileInputStream(f))));
		} catch (FileNotFoundException e) {
		}
		return Optional.empty();
	}
	

}
