package Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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
	
	static Optional<JsonReader> reader(String res) {
		try {
			return Optional.ofNullable(new Gson().newJsonReader(new InputStreamReader(new FileInputStream(res))));
		} catch (FileNotFoundException e) {
		}
		return Optional.empty();
	}
	
	static Optional<JsonReader> reader(final File f) {
		try {
			return Optional.ofNullable(new Gson().newJsonReader(new InputStreamReader(new FileInputStream(f))));
		} catch (FileNotFoundException e) {
		}
		return Optional.empty();
	}
	

}
