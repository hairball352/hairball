package com.sh.hairball.common.util;

import com.oreilly.servlet.multipart.FileRenamePolicy;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class AnimalFileRenamePolicy implements FileRenamePolicy {
    @Override
    public File rename(File originalFile) { // rename()메소드는 파일의 이름을 변경하여 새로운 File 객체를 반환
        File renamedFile = null;
        do {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmssSSS_");
            // -> 파일 이름에 사용할 날짜 및 시간 형식을 지정하기 위해 SimpleDateFormat을 생성
            DecimalFormat df = new DecimalFormat("000"); // 23 -> 023
            // -> 파일 이름에 사용할 숫자 형식을 지정하기 위해 DecimalFormat을 생성

            // 확장자 가져오기
            String originalFilename = originalFile.getName();
            String ext = "";
            int dotIndex = originalFilename.lastIndexOf(".");
            // -> 마지막 점의 인덱스를 구함
            if(dotIndex > -1) {
                ext = originalFilename.substring(dotIndex); // .txt
                // -> 확장자를 추출하기 위해 originalFilename.substring(dotIndex)를 사용하여 점 이후의 문자열을 가져옴
            }

            String renamedFilename = sdf.format(new Date()) + df.format(Math.random()*1000) + ext;
            // -> 새로운 파일 이름을 생성
            renamedFile = new File(originalFile.getParent(), renamedFilename); // new File(부모경로, 파일명)
            // -> 부모 경로와 새로운 파일 이름을 사용하여 새로운 File 객체를 생성
        } while (!createNewFile(renamedFile));
        // -> 루프를 사용하여 파일 이름이 중복되지 않도록 함.
//		System.out.println("renamedFile = " + renamedFile);
        return renamedFile;
    }

    /**
     * File#createNewFile 전달된 File객체를 생성
     * - 이미 존재하면 IOException 던짐
     * - 존재하지않으면 파일 생성!
     */
    private boolean createNewFile(File f) {
        try {
            return f.createNewFile();
        }
        catch (IOException ignored) {
            return false;
        }
    }

}