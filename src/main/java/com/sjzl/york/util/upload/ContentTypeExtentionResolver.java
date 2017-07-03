package com.sjzl.york.util.upload;
/**
 * 
 * @author 作者： zhangshufan
 * @date 创建时间：2015年7月11日 下午7:17:11
 * @version 1.0
 * @since jdk1.6.0_27
 */
public class ContentTypeExtentionResolver {
	private static final String DEFAULT_EXTENTION = ".png";
	private static final String DEFAULT_BACK_EXTENTION = "image/png";
	
	/**
	 * 依据内容类型(contentType)获取对应的文件后缀名
	 * @param contentType
	 * @return
	 */
	public static String resolve(String contentType){
		String result = DEFAULT_EXTENTION;
		if("image/png".equalsIgnoreCase(contentType) || contentType.contains("PNG")){
			return ".png";
		}else if("image/jpeg".equalsIgnoreCase(contentType) || contentType.contains("JPEG")){
			return ".jpg";
		}else if("image/gif".equalsIgnoreCase(contentType) || contentType.contains("GIF")){
			return ".gif";
		}else if("text/plain".equalsIgnoreCase(contentType)){
			return ".txt";
		}else if("video/mp4".equalsIgnoreCase(contentType)){
			return ".mp4";
		}else if ("webp".equalsIgnoreCase(contentType)){
			return ".webp";
		}
		
		return result;
	}

	/**
	 * 根据图片类型转换成“image/...”的格式，以便阿里云调用
	 * @param imgType
	 * @return
	 */
	public static String backResolve(String imgType){
		String result = DEFAULT_BACK_EXTENTION;
		if (imgType.contains("PNG")){
			return "image/png";
		}else if (imgType.contains("JPEG")){
			return "image/JPEG";
		}else if (imgType.contains("GIF")){
			return "image/gif";
		}else if (imgType.contains("webp")){
			return "image/webp";
		}
		return result;
	}
}
