import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author kerwinz
 * @changtime 2024/1/1
 * @projectname SnakeGameWithOpencv
 */
public class Demo1 {
    public static void main(String[] args) {
        List<String> cmd = new ArrayList<>();
        cmd.add("python");
        cmd.add("E:\\java\\ideaProjects\\SnakeGameWithOpencv\\src\\demo.py");
        try {
            ProcessBuilder pb = new ProcessBuilder(cmd);
            // 合并 错误流和标准流
            pb.redirectErrorStream(true);
            Process process = pb.start();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {

                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println("========" + line);
                }
                // 可以使用 org.apache.commons.io 包下的 IoUtils 读流转换为String
                // IoUtils.tostring(reader);
                // 等待命令执行完成
                int code = process.waitFor();

                if (code == 0) {
                    //通常情况0 表示命令或者脚本正常退出，但是如果脚本自己有返回状态这里需要根据自己状态判断
                    System.out.println("success");
                } else {
                    System.out.println("fail");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

