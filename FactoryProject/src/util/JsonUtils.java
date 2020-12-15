package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import carbuilder.CarRequirement;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import components.Component;

public class JsonUtils {

	public static <T extends Component> List<T> getComponents(File file,Class<T> clazz) throws FileNotFoundException {
		Optional<JsonArray> opt=parseArrayFromFile(file);
		if(!opt.isPresent()) throw new FileNotFoundException();

		Gson gson=new Gson();

		JsonArray arr=opt.get();
		List<JsonElement> list=JsonUtils.getElements(arr);
		List<T> ret=list.stream().map((JsonElement elem)->{
			return gson.fromJson(elem, clazz);
		}).collect(Collectors.toList());

		return ret;
	}

	public static List<CarRequirement> getCarRequirements(File file) throws FileNotFoundException {
		Optional<JsonArray> opt=parseArrayFromFile(file);
		if(!opt.isPresent()) throw new FileNotFoundException();

		Gson gson=new Gson();

		JsonArray arr=opt.get();
		List<JsonElement> list=JsonUtils.getElements(arr);
		List<CarRequirement> ret=list.stream().map((JsonElement elem)->{
			return gson.fromJson(elem, CarRequirement.class);
		}).collect(Collectors.toList());

		return ret;
	}
	
	public static <T extends Component> List<T> getComponents(String resource,Class<T> clazz) throws FileNotFoundException {
		Optional<JsonArray> opt=parseArrayFromResource(resource);
		if(!opt.isPresent()) throw new FileNotFoundException();

		Gson gson=new Gson();

		JsonArray arr=opt.get();
		List<JsonElement> list=JsonUtils.getElements(arr);
		List<T> ret=list.stream().map((JsonElement elem)->{
			return gson.fromJson(elem, clazz);
		}).collect(Collectors.toList());

		return ret;
	}

	public static List<CarRequirement> getCarRequirements(String resource) throws FileNotFoundException {
		Optional<JsonArray> opt=parseArrayFromResource(resource);
		if(!opt.isPresent()) throw new FileNotFoundException();

		Gson gson=new Gson();

		JsonArray arr=opt.get();
		List<JsonElement> list=JsonUtils.getElements(arr);
		List<CarRequirement> ret=list.stream().map((JsonElement elem)->{
			return gson.fromJson(elem, CarRequirement.class);
		}).collect(Collectors.toList());

		return ret;
	}

	private static final Optional<JsonArray> parseArrayFromFile(final File file) {
		try {
			return parseArrayFromFile(file.getCanonicalPath());
		} catch (IOException e) {
			Optional.empty();
		}
		return Optional.empty();
	}

	private static final Optional<JsonArray> parseArrayFromFile(String file) {
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
	
	private static final Optional<JsonArray> parseArrayFromResource(String res) {
		Optional<JsonReader> reader=resourceReader(res);
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

	private static final List<JsonElement> getElements(final JsonArray arr) {
		final ArrayList<JsonElement> list=new ArrayList<>();
		Iterator<JsonElement> it=arr.iterator();
		while(it.hasNext()) {
			list.add(it.next());
		}
		return list;
	}

	private static Optional<JsonReader> reader(String res) {
		try {
			return Optional.ofNullable(new Gson().newJsonReader(new InputStreamReader(new FileInputStream(res))));
		} catch (FileNotFoundException e) {
		}
		return Optional.empty();
	}
	
	private static Optional<JsonReader> resourceReader(String res) {
		try {
			return Optional.ofNullable(new Gson().newJsonReader(new InputStreamReader(ClassLoader.getSystemClassLoader().getResourceAsStream(res))));
		} catch (Exception e) {
		}
		return Optional.empty();
	}
}
