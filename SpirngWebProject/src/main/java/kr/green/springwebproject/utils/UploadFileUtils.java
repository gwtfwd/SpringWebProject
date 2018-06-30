package kr.green.springwebproject.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.springframework.util.FileCopyUtils;

public class UploadFileUtils {

	// 파일을 업로드하는 메소드
	public static String uploadFile(String uploadPath, String originalName, byte[] fileData)throws Exception{
		
		// 동일한 파일을 업로드 했을 때 구분하기 위한 고유 범용 식별자를 생성
		UUID uid = UUID.randomUUID();
		
		// 서버에 저장할 파일 이름을 설정 -> 고유 범용 식별자 _ 파일명
		String savedName = uid.toString() +"_" + originalName;
		
		// 서버에 지정한 폴더에 저장하기 위해 만들 폴더 경로
		// 하위폴더가 없으면 서버에 지정한 폴더에 많은 파일들이 업로드되어 성능 저하가 일어남
		// 따라서 하위 폴더를 생성해서 파일들을 분할하여 관리
		// 일폴더의 경로를 반환
		String savedPath = calcPath(uploadPath);
		
		// 서버경로/일폴더경로/파일생성
		// c:\\git\\uploadfiles\\index.html
		File target = new File(uploadPath + savedPath, savedName);
		
		// c:\\git\\uploadfiles\\index.html에 실제 데이터를 복사해서 생성
		FileCopyUtils.copy(fileData, target);
		
		
		// 이미지일때 썸네일 생성하기. 
        String formatName = originalName.substring(originalName.lastIndexOf(".")+1);
        
        //파일 확장자 추출
        String uploadFileName=null;
        
        //확장자가 이미지이면(JPG,JPEG,PNG,GIF)
        if(MediaUtils.getMediaType(formatName)!=null) {
        	
           uploadFileName=makeThumbnail(uploadPath,savedPath,savedName);
           
        }else {
        	uploadFileName = makeIcon(uploadPath, savedPath, savedName);
        	
        }
		
		return uploadFileName;
	}
	
	// 업로드한 파일을 저장할 경로를 계산하는 메소드
	private static String calcPath(String uploadPath) {
		Calendar cal = Calendar.getInstance();
		
		// File.separator : \\2018
		String yearPath = File.separator+cal.get(Calendar.YEAR);
		
		// \\2018\\
		String monthPath = yearPath + File.separator 
            + new DecimalFormat("00").format(cal.get(Calendar.MONTH)+1);
		
		// \\2018\\06\\28
		String datePath = monthPath + File.separator 
            + new DecimalFormat("00").format(cal.get(Calendar.DATE));
		
		// 폴더 생성 : 서버 경로 밑에 년도폴더를 생성하고, 년도폴더 밑에 달폴더 생성, 달폴더 밑에 일폴더 생성
		makeDir(uploadPath, yearPath, monthPath, datePath);
		
		return datePath;
 
	}
	
	// 폴더를 생성하는 메소드
	private static void makeDir(String uploadPath, String... paths) {
		
		// 일폴더를 이용하여 서버경로 + 일폴더 경로를 합친 경로를 생성하고
		// 생성된 경로가 존재하면 작업을 할 필요가 없음
		if(new File(paths[paths.length-1] + paths[paths.length-1]).exists())
			return;
		
		// 매개변수에서 입력받은 경로들을 하나씩 가져와서 해당 경로에 폴더가 있는지 없는지 확인하여 
		// 있으면 그냥 넘어가고 없으면 폴더를 생성하는 반복문
		for(String path : paths) {
			File dirPath = new File(uploadPath + path);
			
			// 해당 경로가 존재하지 않으면
			if( !dirPath.exists())
				dirPath.mkdir();	// 해당경로 생성 = 폴더 생성
		}
	}
	
	private static String makeIcon(String uploadPath, String path, String fileName)	throws Exception{
		String iconName = uploadPath + path + File.separator + fileName;
		
		// iconName.substring(uploadPath.length()) : 풀경로(서버경로\폴더경로\파일명)에서 서버경로를 제외한 경로를 추출
		// (폴더경로\파일명)
		// .replace(File.separatorChar, '/') : 폴더경로\파일명을 폴더경로/파일명으로 수정
		// (예 : 2018\06\28 -> 2018/06/28)
		return iconName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}
	
	
	private static String makeThumbnail(String uploadPath, String path, String fileName) throws Exception{
		
		// File을 통해 이미지 파일 경로를 읽어오고 ImageIO.read응 통해 이미지 파일을 BufferedImage에 저장
		BufferedImage sourceImg = ImageIO.read(new File(uploadPath + path, fileName));
		
		// 원본 이미지를 기준으로 썸네일을 생성
		BufferedImage destImg = Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, 50);
		
		// 썸네일명을 생성하는데 파일명 앞에 s_가 붙음
		String thumbnailName = uploadPath + path + File.separator + "s_" + fileName;
		
		// 썸네일명을 가지는 파일을 생성
		File newFile = new File(thumbnailName);
		
		// 이미지의 확장자 추출
		String formatName = fileName.substring(fileName.lastIndexOf(".")+1);
		
		// 썸네일 이미지를 저장
		ImageIO.write(destImg, formatName.toUpperCase(), newFile);
		
		// 썸네일 경로를 서버 경로에서 URL경로로 수정
		return thumbnailName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}
	
	
}








