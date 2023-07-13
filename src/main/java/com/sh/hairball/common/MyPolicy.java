package com.sh.hairball.common;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class MyPolicy implements FileRenamePolicy {

	@Override
	public File rename(File originalFile) {
		File renamedFile = null;
		do {
			SimpleDateFormat dateFormat  = new SimpleDateFormat("yyyyMMdd_HHmmssSSS_");
			DecimalFormat decimalFormat = new DecimalFormat("000");
			
			String originalFilename = originalFile.getName();
			String ext = "";
			int dotIndex = originalFilename.lastIndexOf(".");
			if(dotIndex > -1) {
				ext=originalFilename.substring(dotIndex);
			}
			String renamedFilename = dateFormat.format(new Date()) + decimalFormat.format(Math.random() * 1000) + ext;
			renamedFile = new File(originalFile.getParent(), renamedFilename);
			
		}while(!createNewFile(renamedFile));
		System.out.println("renamedFile = " + renamedFile);
		return renamedFile;
	}
	
	private boolean createNewFile(File f) {
	    try {
	      return f.createNewFile();
	    }
	    catch (IOException ignored) {
	      return false;
	    }
	}
}
