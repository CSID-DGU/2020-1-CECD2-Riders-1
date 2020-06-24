package project.ridersserver.ridersserverapp.VideoConverter;

import org.opencv.core.Core;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LibLoadingConfig {
    static {
        try{
            nu.pattern.OpenCV.loadShared();
            System.loadLibrary(org.opencv.core.Core.NATIVE_LIBRARY_NAME);
        }catch (UnsatisfiedLinkError ignore){
            //dev-tool을 쓰게되면 리로드 할 때 이 오류가 뜨니까 그냥 무시하도록 하자
        }
    }
}
