package project.ridersserver.ridersserverapp.VideoConverter;

import javafx.util.Pair;
import net.sourceforge.tess4j.Tesseract;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.opencv.core.Core;
import org.opencv.core.Rect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

@Component
public class VideoTextDetector {

    @Autowired
    private ImageTextDetector imageTextDetector;

    private String tesseractPath;

    private String videoFilePath;

    private double frameRate;

    public ArrayList<Pair<Double,String>> run(String hostfileName) throws Exception {
        ArrayList<Pair<Double,String>> ocrResultList = new ArrayList<>();
        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath(tesseractPath);
        tesseract.setLanguage("eng");
        FFmpegFrameGrabber g = new FFmpegFrameGrabber(videoFilePath);
        g.setFrameRate(30);
        Java2DFrameConverter java2DFrameConverter = new Java2DFrameConverter();
        BufferedImage frameImage, candidateArea;
        int k = 0;
        int x,y,w,h;
        Integer uy = null,uh = null;    //올바른 문장이 나오는 위치를 피드벡 할때 update할 높이
        Integer ux = null,uw = null;    //올바른 문장이 나오는 위치를 피드벡 할때 update할 너비

        Rect updateRect = null;         //updateRect : 최적화 자막 영역이 나왔다면 그걸 담는것 //isFitted: 최적화 영역이 되었는지
        boolean isFitted = false;

        g.start();
        //초기 자막 후보 영역 설정
        Frame singleFrame = g.grabImage();
        frameImage = java2DFrameConverter.convert(singleFrame);
        x = 0;
        y = frameImage.getHeight()-250;
        w = frameImage.getWidth();
        h = 200;

        boolean isGo = true;
        for (int i = 1 ; i < g.getLengthInFrames() ; i++) {
            //1초 단위 프레임 처리
            if(i%frameRate == 0) {    //  i/30 => 1초
                singleFrame = g.grabImage();
                if(singleFrame == null) //프레임의 끝까지 다 읽으면 끝내야함
                    break;
                frameImage = java2DFrameConverter.convert(singleFrame);
                candidateArea = frameImage.getSubimage(x,y,w,h);

                if(isFitted) {//피드벡을 통한 candidateArea최적화
                    if(ux + uw.intValue() >= w){
                        candidateArea = candidateArea.getSubimage(x, uy.intValue(), w, uh.intValue());
                    }
                    else
                        candidateArea = candidateArea.getSubimage(ux, uy.intValue(), uw.intValue(), uh.intValue());
                }

                Pair<Rect,String> imageDectionInfo = imageTextDetector.DetectText(candidateArea,tesseract,(double) i / frameRate,hostfileName);

                if(imageDectionInfo != null ) { //올바른 문장이 있을 때
                    if(isGo == true){
                        updateRect = imageDectionInfo.getKey();
                        if(isFitted == false){
                            uy = updateRect.y - 10;
                            uh = updateRect.height + 20;
                        }
                        if(ux == null && uw == null){
                            ux = updateRect.x - 60;
                            uw = updateRect.width + 120;
                        }
                        else{
                            ux = ux + updateRect.x - 60;
                            uw = updateRect.width + 120;
                        }
                        isFitted = true;

                        String succesStr = imageDectionInfo.getValue();
                        double currentVideoTime = (double) i / frameRate;
                        ocrResultList.add(new Pair<>(currentVideoTime, succesStr));
                        isGo = false;
                    }
                    else
                        isGo = true;
                }
            }
            else
                g.grabImage();
        }
        g.stop();
        return ocrResultList;
    }



    public String getTesseractPath() {
        return tesseractPath;
    }

    public void setTesseractPath(String tesseractPath) {
        this.tesseractPath = tesseractPath;
    }

    public String getVideoFilePath() {
        return videoFilePath;
    }

    public void setVideoFilePath(String videoFilePath) {
        this.videoFilePath = videoFilePath;
    }

    public double getFrameRate() {
        return frameRate;
    }

    public void setFrameRate(double frameRate) {
        this.frameRate = frameRate;
    }
}

