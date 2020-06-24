package project.ridersserver.ridersserverapp.VideoConverter.Old;

import javafx.util.Pair;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.springframework.stereotype.Component;
import project.ridersserver.ridersserverapp.VideoConverter.Old.NameConverter;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

@Component
public class VideoConverter {

    private String tesseractPath;

    private String videoFilePath;

    private double frameRate;

    private int imageX;

    private int imageY;

    private int imageW;

    private int imageH;

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

    public void setImageDomain(int x, int y, int w,int h )
    {
        imageX = x;
        imageY = y;
        imageW = w;
        imageH = h;
    }

    public ArrayList<Pair<Double,String>> ConvertVideoToString() throws FrameGrabber.Exception, TesseractException {
        boolean isGo = true;
        ArrayList<Pair<Double,String>> ocrResultList = new ArrayList<>();
        NameConverter nameConverter = new NameConverter();
        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath(tesseractPath);
        FFmpegFrameGrabber g = new FFmpegFrameGrabber(videoFilePath);
        g.setFrameRate(frameRate);
        g.start();
        Java2DFrameConverter java2DFrameConverter = new Java2DFrameConverter();
        BufferedImage convertImage;

        for (int i = 0 ; i < g.getLengthInFrames() ; i++) {
            if(i%frameRate == 0) {
                Frame singleFrame = g.grabImage();
                convertImage = java2DFrameConverter.convert(singleFrame);
                BufferedImage subimage = convertImage.getSubimage(imageX, imageY, imageW, imageH);
                String ocrStr = tesseract.doOCR(subimage);

                //문자열 만들기
                String succesStr;
                if (nameConverter.IsRightString(ocrStr)) {
                    if(isGo == true){
                        String[] compontents = ocrStr.split("/");
                        String whatTeam = nameConverter.WhatTeam(compontents[0]);
                        String whatAction = nameConverter.WhatAction(compontents[1]);
                        succesStr = whatTeam + "-" + whatAction;

                        //시간 뽑아내기(i번째 프래임이겠지?)
                        double currentVideoTime = (double) i / frameRate;
                        ocrResultList.add(new Pair<>(currentVideoTime,succesStr));
                        isGo = false;
                    }
                }
                else
                    isGo = true;
            }
            else
                g.grabImage();
        }

        g.stop();
        return ocrResultList;
    }
}
