package app;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import model.TranslateAPI;

public class AppTask implements Callable<String> {
    private String taskName;
    private String target;

    public AppTask(String taskName, String target) {
        this.taskName = taskName;
        this.target = target;
    }

    @Override
    public String call() {
        return TranslateAPI.translate(target);
    }
}