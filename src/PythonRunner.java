/**
 * @author kerwinz
 * @changtime 2024/1/1
 * @projectname SnakeGameWithOpencv
 */
import java.io.*;
import java.util.Scanner;

public class PythonRunner {

    public static class PythonThread extends Thread {
        public void run() {
            try {
                // 构建Python命令
                String pythonCommand = "python";
                String scriptPath = "E:\\java\\ideaProjects\\SnakeGameWithOpencv\\src\\Snake_Game.py";

                // 执行Python脚本
                Process process = Runtime.getRuntime().exec(pythonCommand + " " + scriptPath);

                // 获取Python脚本的输入流
                OutputStream outputStream = process.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));

                // 从Java的Terminal读取输入参数
                Scanner scanner = new Scanner(System.in);
                String inputParameter = scanner.nextLine();

                // 等待一段时间，确保Python脚本已经启动并准备好接收输入
                Thread.sleep(2000);

                // 向Python脚本发送输入参数
                writer.write(inputParameter);
                writer.newLine();
                writer.flush();

                // 获取Python脚本输出
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    // 处理Python脚本输出
                    System.out.println(line);
                }

                // 等待Python脚本执行完毕
                // 获取Python脚本的错误输出
                BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
                String errorLine;
                while ((errorLine = errorReader.readLine()) != null) {
                    // 处理Python脚本的错误输出
                    System.err.println(errorLine);
                }
                int exitCode = process.waitFor();
                System.out.println("Python脚本执行完毕，退出码: " + exitCode);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        // 创建并启动线程
        PythonThread pythonThread = new PythonThread();
        pythonThread.start();
    }
}


