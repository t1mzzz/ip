package duke;

import duke.task.Task;

import java.util.Scanner;

public class Ui {

    Scanner sc = new Scanner(System.in);
    private final String BORDER = "    ============================================================";
    private final String INDENT = "     ";

    public void showLine() {
        System.out.println(BORDER);
    }

    public void showWelcome() {
        System.out.println(BORDER);
        String content;
        String logo = "      ____        _        \n"
                + "     |  _ \\ _   _| | _____ \n"
                + "     | | | | | | | |/ / _ \\\n"
                + "     | |_| | |_| |   <  __/\n"
                + "     |____/ \\__,_|_|\\_\\___|\n";
        content = logo + "\n" + INDENT + "Hello! I'm Duke\n" + INDENT + "What can I do for you?\n";
        System.out.print(content);
        System.out.println(BORDER);
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showLoadingError() {
        showError("Task data not found.");
    }

    public void showAddTask(int size, String desc) {
        String plural = size > 1 ? "s" : "";
        showMessage("Got it. I've added this task:");
        showMessage("  " + desc);
        showMessage("Now you have " + size + " task" + plural  + " in the list.");
    }

    public void showError(String errMessage) {
        System.out.println(INDENT + errMessage);
    }

    public void showBye() {
        System.out.println(INDENT + "Bye. Hope to see you again soon!");
        sc.close();
    }

    public void showMessage(String message) {
        System.out.println(INDENT + message);
    }

}