package com.cloud.alibaba.common.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class FiltUtil {

	public static String readFile(String filePath)throws IOException {
		String result = "";
		if(notEmptyOrNull(filePath)) {
			return result;
		}
		BufferedReader reader = null;
		try {
			File file = new File(filePath);
			if(!file.exists()) {
				return result;
			}
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
		    // 一次读入一行，直到读入null为文件结束
		    while ((tempString = reader.readLine()) != null) {
		    	result+=tempString+"\n";
		    }
		    reader.close();
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			if(reader != null) {
				try {
					reader.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	public static List<String> readLines(String filePath) throws IOException{
		List<String> lines = new ArrayList<String>();
		if(notEmptyOrNull(filePath)){
			return lines;
		}
		BufferedReader reader = null;
		try {
			File file = new File(filePath);
			if(!file.exists()){
				return lines;
			}
		    reader = new BufferedReader(new FileReader(file));
		    String tempString = null;
		    // 一次读入一行，直到读入null为文件结束
		    while ((tempString = reader.readLine()) != null) {
		    	lines.add(tempString.trim());
		    }
		    reader.close();
		} catch (IOException e) {
		    e.printStackTrace();
		    throw e;
		} finally {
			if (reader != null) {
			    try {
			        reader.close();
			    } catch (IOException e1) {
			    }
			}
		}
		return lines;
	}
	public static byte[] readByte(String filePath) throws IOException{
		byte[] data = null;
		if(notEmptyOrNull(filePath)){
			return null;
		}
		FileInputStream fileInputStream =null;
		try {
			File file = new File(filePath);
			if(!file.exists()){
				return null;
			}
		    fileInputStream = new FileInputStream(file);
		    int size = fileInputStream.available();
		    data = new byte[size];
		    fileInputStream.read(data);
		} catch (IOException e) {
		    e.printStackTrace();
		    throw e;
		} finally {
			if (fileInputStream != null) {
			    try {
			    	fileInputStream.close();
			    } catch (IOException e1) {
			    }
			}
		}
		return data;
	}
	
	public static byte[] readByte(InputStream inputStream) throws IOException {
		ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
		byte[] in2b = null;
		try {
			byte[] buff = new byte[100];
			int rc = 0;
			while ((rc = inputStream.read(buff, 0, 100)) > 0) {
				swapStream.write(buff, 0, rc);
			}
			in2b = swapStream.toByteArray();
		} finally {
			if(null != swapStream) {
				swapStream.close();
			}
		}
        return in2b;
    }
	
	public static List<String> readLines(InputStream inputStream) throws IOException {
		List<String> lines = new ArrayList<String>();
		if(null == inputStream){
			return lines;
		}
		BufferedReader reader = null;
		try {
		    reader = new BufferedReader(new InputStreamReader(inputStream));
		    String tempString = null;
		    // 一次读入一行，直到读入null为文件结束
		    while ((tempString = reader.readLine()) != null) {
		    	lines.add(tempString.trim());
		    }
		    reader.close();
		} catch (IOException e) {
		    e.printStackTrace();
		    throw e;
		} finally {
			if (reader != null) {
			    try {
			        reader.close();
			    } catch (IOException e1) {
			    }
			}
		}
		return lines;
	}
	
	public static String read(InputStream inputStream) throws IOException{
		StringBuffer sb = new StringBuffer();
		List<String> lines = readLines(inputStream);
		for (String line : lines) {
			sb.append(line);
			sb.append("\n");
		}
		return sb.toString();
	}
	
	/**
	 * 创建文件夹
	 * 
	 * @param strFilePath
	 *            文件夹路径
	 */
	public static boolean mkdirFolder(String strFilePath) {
		boolean bFlag = false;
		try {
			File file = new File(strFilePath);
			if (!file.exists()) {
				bFlag = file.mkdir();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bFlag;
	}
	
	/**
	 * 	获取目录下所有文件路径
	 */
	public static List<String> getFilesPath(String dirPath){
		List<String> filePaths = new ArrayList<String>();
		
		File file = new File(dirPath);
		File[] subFile = file.listFiles();
		if (null == subFile) {
			return filePaths;
		}
		for (File fileTemp : subFile) {
			if (fileTemp.isDirectory()) {
				filePaths.addAll(getFilesPath(fileTemp.getAbsolutePath()));
			}else{
				filePaths.add(fileTemp.getAbsolutePath());
			}
		}
		return filePaths; 
	}
	
	/**
	 * 	获取当前目录下的文件名
	 */
	public static List<String> getFileNames(String dirPath){
		List<String> filePaths = new ArrayList<String>();
		File file = new File(dirPath);
		File[] subFile = file.listFiles();
		if (null == subFile) {
			return filePaths;
		}
		for (File fileTemp : subFile) {
			if (!fileTemp.isDirectory()) {
				filePaths.add(fileTemp.getName());
			}
		}
		return filePaths; 
	}
	
	public static void write(String filePath, String data) {
		if(notEmptyOrNull(filePath)){
			return ;
		}
		BufferedWriter write = null;
		try {
			File file = new File(filePath);
			write = new BufferedWriter(new FileWriter(file, true));
			write.write(data);
			write.flush();
			write.close();
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			if(write != null) {
				try {
					write.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public static boolean notEmptyOrNull(String str) {
		if(str == null || str.equals("")) {
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		String filePath = "/Users/fzz/Desktop/user.sql";
//		for(int i = 0; i<10000; i++) {
			String result = FiltUtil.readFile(filePath);
//			FiltUtil.write(filePath, result);
			System.out.println("read over");
//		}
	}
}




















































