import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;
import org.opencv.imgproc.Imgproc;
import org.opencv.highgui.HighGui;
/**
 * @author kerwinz
 * @changtime 2023/12/31
 * @projectname SnakeGameWithOpencv
 */


public class CameraDisplay {
    public static void main(String[] args) {
        // 加载 OpenCV 库
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        // 创建 VideoCapture 对象，打开默认摄像头
        VideoCapture capture = new VideoCapture(0);
        if (!capture.isOpened()) {
            System.out.println("无法打开摄像头");
            return;
        }

        // 获取摄像头的宽度和高度
        double frameWidth = capture.get(Videoio.CAP_PROP_FRAME_WIDTH);
        double frameHeight = capture.get(Videoio.CAP_PROP_FRAME_HEIGHT);

        // 创建窗口
        HighGui.namedWindow("Camera", HighGui.WINDOW_AUTOSIZE);

        while (true) {
            // 读取帧
            Mat frame = new Mat();
            capture.read(frame);

            // 如果帧为空，则退出循环
            if (frame.empty()) {
                break;
            }

            // 在窗口中显示帧
            HighGui.imshow("Camera", frame);

            // 通过按下 ESC 键退出循环
            if (HighGui.waitKey(1) == 27) {
                break;
            }
        }

        // 释放资源
        capture.release();
        HighGui.destroyAllWindows();
    }
}

