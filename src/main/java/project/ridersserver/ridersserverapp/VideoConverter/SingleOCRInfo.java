package project.ridersserver.ridersserverapp.VideoConverter;


import org.opencv.core.Rect;

public class SingleOCRInfo {
    private String ocrStr;

    private double score;

    private Rect rect;

    public SingleOCRInfo(String ocrStr, double score, Rect rect) {
        this.ocrStr = ocrStr;
        this.score = score;
        this.rect = rect;
    }

    public String getOcrStr() {
        return ocrStr;
    }

    public void setOcrStr(String ocrStr) {
        this.ocrStr = ocrStr;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Rect getRect() {
        return rect;
    }

    public void setRect(Rect rect) {
        this.rect = rect;
    }
}
