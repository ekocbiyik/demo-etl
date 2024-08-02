package com.ttgint;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class ParallelJarRunner {

    private static final String JAR_PATH = "/app/aggregation.jar";

    static class JarRunnerTask implements Runnable {
        private final int taskId;

        public JarRunnerTask(int taskId) {
            this.taskId = taskId;
        }

        @Override
        public void run() {
            long startTime = System.currentTimeMillis();
            try {
                // ProcessBuilder kullanarak JAR dosyasını çalıştır
                ProcessBuilder processBuilder = new ProcessBuilder("java", "-jar", JAR_PATH);
                processBuilder.redirectErrorStream(true);
                Process process = processBuilder.start();

                // Çıktıyı okumak için BufferedReader kullan
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println("Thread " + taskId + ": " + line);
                }

                // İşlemin tamamlanmasını bekle
                int exitCode = process.waitFor();
                if (exitCode == 0) {
                    long endTime = System.currentTimeMillis();
                    long duration = endTime - startTime;
                    System.out.println("Thread " + taskId + " tamamlandı. Çalışma süresi: " + duration + " ms");
                } else {
                    System.out.println("Thread " + taskId + " hata ile sona erdi. Çıkış kodu: " + exitCode);
                }
            } catch (IOException | InterruptedException e) {
                System.err.println("Thread " + taskId + " hata ile sona erdi: " + e.getMessage());
            }
        }
    }
}
