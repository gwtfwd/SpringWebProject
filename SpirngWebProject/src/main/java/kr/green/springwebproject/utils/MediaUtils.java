package kr.green.springwebproject.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;

public class MediaUtils {

	private static Map<String, MediaType> mediaMap;
	
	static {
		
		mediaMap = new HashMap<String, MediaType>();
		mediaMap.put("JPG", MediaType.IMAGE_JPEG);
		mediaMap.put("JPG", MediaType.IMAGE_GIF);
		mediaMap.put("JPG", MediaType.IMAGE_PNG);
				
	}
	
	public static MediaType getMediaType(String type) {
		
		return mediaMap.get(type.toUpperCase());
	}
	
	
	
	
	
	
	
}







