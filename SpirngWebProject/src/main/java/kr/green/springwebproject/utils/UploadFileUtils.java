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

	// ������ ���ε��ϴ� �޼ҵ�
	public static String uploadFile(String uploadPath, String originalName, byte[] fileData)throws Exception{
		
		// ������ ������ ���ε� ���� �� �����ϱ� ���� ���� ���� �ĺ��ڸ� ����
		UUID uid = UUID.randomUUID();
		
		// ������ ������ ���� �̸��� ���� -> ���� ���� �ĺ��� _ ���ϸ�
		String savedName = uid.toString() +"_" + originalName;
		
		// ������ ������ ������ �����ϱ� ���� ���� ���� ���
		// ���������� ������ ������ ������ ������ ���� ���ϵ��� ���ε�Ǿ� ���� ���ϰ� �Ͼ
		// ���� ���� ������ �����ؼ� ���ϵ��� �����Ͽ� ����
		// �������� ��θ� ��ȯ
		String savedPath = calcPath(uploadPath);
		
		// �������/���������/���ϻ���
		// c:\\git\\uploadfiles\\index.html
		File target = new File(uploadPath + savedPath, savedName);
		
		// c:\\git\\uploadfiles\\index.html�� ���� �����͸� �����ؼ� ����
		FileCopyUtils.copy(fileData, target);
		
		
		// �̹����϶� ����� �����ϱ�. 
        String formatName = originalName.substring(originalName.lastIndexOf(".")+1);
        
        //���� Ȯ���� ����
        String uploadFileName=null;
        
        //Ȯ���ڰ� �̹����̸�(JPG,JPEG,PNG,GIF)
        if(MediaUtils.getMediaType(formatName)!=null) {
        	
           uploadFileName=makeThumbnail(uploadPath,savedPath,savedName);
           
        }else {
        	uploadFileName = makeIcon(uploadPath, savedPath, savedName);
        	
        }
		
		return uploadFileName;
	}
	
	// ���ε��� ������ ������ ��θ� ����ϴ� �޼ҵ�
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
		
		// ���� ���� : ���� ��� �ؿ� �⵵������ �����ϰ�, �⵵���� �ؿ� ������ ����, ������ �ؿ� ������ ����
		makeDir(uploadPath, yearPath, monthPath, datePath);
		
		return datePath;
 
	}
	
	// ������ �����ϴ� �޼ҵ�
	private static void makeDir(String uploadPath, String... paths) {
		
		// �������� �̿��Ͽ� ������� + ������ ��θ� ��ģ ��θ� �����ϰ�
		// ������ ��ΰ� �����ϸ� �۾��� �� �ʿ䰡 ����
		if(new File(paths[paths.length-1] + paths[paths.length-1]).exists())
			return;
		
		// �Ű��������� �Է¹��� ��ε��� �ϳ��� �����ͼ� �ش� ��ο� ������ �ִ��� ������ Ȯ���Ͽ� 
		// ������ �׳� �Ѿ�� ������ ������ �����ϴ� �ݺ���
		for(String path : paths) {
			File dirPath = new File(uploadPath + path);
			
			// �ش� ��ΰ� �������� ������
			if( !dirPath.exists())
				dirPath.mkdir();	// �ش��� ���� = ���� ����
		}
	}
	
	private static String makeIcon(String uploadPath, String path, String fileName)	throws Exception{
		String iconName = uploadPath + path + File.separator + fileName;
		
		// iconName.substring(uploadPath.length()) : Ǯ���(�������\�������\���ϸ�)���� ������θ� ������ ��θ� ����
		// (�������\���ϸ�)
		// .replace(File.separatorChar, '/') : �������\���ϸ��� �������/���ϸ����� ����
		// (�� : 2018\06\28 -> 2018/06/28)
		return iconName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}
	
	
	private static String makeThumbnail(String uploadPath, String path, String fileName) throws Exception{
		
		// File�� ���� �̹��� ���� ��θ� �о���� ImageIO.read�� ���� �̹��� ������ BufferedImage�� ����
		BufferedImage sourceImg = ImageIO.read(new File(uploadPath + path, fileName));
		
		// ���� �̹����� �������� ������� ����
		BufferedImage destImg = Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, 50);
		
		// ����ϸ��� �����ϴµ� ���ϸ� �տ� s_�� ����
		String thumbnailName = uploadPath + path + File.separator + "s_" + fileName;
		
		// ����ϸ��� ������ ������ ����
		File newFile = new File(thumbnailName);
		
		// �̹����� Ȯ���� ����
		String formatName = fileName.substring(fileName.lastIndexOf(".")+1);
		
		// ����� �̹����� ����
		ImageIO.write(destImg, formatName.toUpperCase(), newFile);
		
		// ����� ��θ� ���� ��ο��� URL��η� ����
		return thumbnailName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}
	
	
}








