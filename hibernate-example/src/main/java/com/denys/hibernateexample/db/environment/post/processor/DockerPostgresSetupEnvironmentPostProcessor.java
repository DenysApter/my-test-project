package com.denys.hibernateexample.db.environment.post.processor;

import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;

import java.io.*;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public class DockerPostgresSetupEnvironmentPostProcessor implements EnvironmentPostProcessor {
    private static final String LOCAL = "local";
    private static final String OS_NAME = "os.name";
    private static final String WINDOWS = "windows";

    @SneakyThrows
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        String activeProfile = environment.getActiveProfiles()[0];
        String homeDirectory = System.getProperty("user.home");
        String[] unixCmdline = {"sh", "-c", "docker run --rm --name pg-docker -e POSTGRES_PASSWORD=docker -d -p 5432:5432 -v $HOME/docker/volumes/postgres:/var/lib/postgresql/data postgres", homeDirectory};
        String[] windowsCmdline = {"cmd.exe", "/c", "docker run --rm --name pg-docker -e POSTGRES_PASSWORD=docker -d -p 5432:5432 -v $HOME/docker/volumes/postgres:/var/lib/postgresql/data postgres", homeDirectory};

        if (activeProfile.equals(LOCAL)) {
            boolean isWindows = System.getProperty(OS_NAME).toLowerCase().startsWith(WINDOWS);
            if (isWindows) {
                executeCommand(windowsCmdline); //TODO: check command for Windows OS
            } else {
                executeCommand(unixCmdline);
            }
        }
    }

    private void executeCommand(String[] cmdline) throws InterruptedException, IOException {
        Process process = Runtime.getRuntime().exec(cmdline);
        StreamGobbler streamGobbler = new StreamGobbler(process.getInputStream(), System.out::println);
        Executors.newSingleThreadExecutor().submit(streamGobbler);
        int exitCode = process.waitFor();
        assert exitCode == 0;
    }

    private static class StreamGobbler implements Runnable {
        private InputStream inputStream;
        private Consumer<String> consumer;

        public StreamGobbler(InputStream inputStream, Consumer<String> consumer) {
            this.inputStream = inputStream;
            this.consumer = consumer;
        }

        @Override
        public void run() {
            new BufferedReader(new InputStreamReader(inputStream)).lines().forEach(consumer);
        }
    }
}