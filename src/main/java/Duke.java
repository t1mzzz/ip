import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    ArrayList<Task> tasks = new ArrayList<>(100);
    final String BORDER = "    ____________________________________________________________";
    final String INDENT = "     ";

    public void run() {
        welcome();
        command();
        bye();
    }

    public void msg(String content) {
        System.out.println(BORDER);
        System.out.print(content);
        System.out.println(BORDER);
        System.out.println();
    }

    public void welcome() {
        String content;
        String logo = "      ____        _        \n"
                + "     |  _ \\ _   _| | _____ \n"
                + "     | | | | | | | |/ / _ \\\n"
                + "     | |_| | |_| |   <  __/\n"
                + "     |____/ \\__,_|_|\\_\\___|\n";
        content = logo + "\n" + INDENT + "Hello! I'm Duke\n" + INDENT + "What can I do for you?\n";
        msg(content);
    }

    public void command() {
        Scanner sc = new Scanner(System.in);
        String cmd = sc.nextLine();
        while (!cmd.equals("bye")) {
            if (cmd.equals("list")) {
                list();
            } else if (cmd.split(" ")[0].equals("mark")) {
                mark(cmd);
            } else if (cmd.split(" ")[0].equals("unmark")) {
                unmark(cmd);
            } else {
                addTask(cmd);
            }
            cmd = sc.nextLine();
        }
        sc.close();
    }

    public void addTask(String cmd) {
        String type = cmd.split(" ")[0];
        boolean added = false;
        int sizePrev = tasks.size();
        switch (type) {
            case "todo":
                addTodo(cmd);
                added = sizePrev != tasks.size();
                break;

            case "deadline":
                addDeadline(cmd);
                added = sizePrev != tasks.size();
                break;

            case "event":
                addEvent(cmd);
                added = sizePrev != tasks.size();
                break;

            default:
                msg(INDENT + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n");
        }
        if (added) {
            String content;
            Task task = tasks.get(tasks.size() - 1);
            content = INDENT + "Got it. I've added this task:\n";
            content += INDENT + "  " + task + "\n";
            content += INDENT + "Now you have " + tasks.size() + " tasks in the list.\n";
            msg(content);
        }
    }

    public void addTodo(String cmd) throws DukeException {
        try {
            String desc = cmd.split(" ", 2)[1];
            Todo todo = new Todo(desc);
            tasks.add(tasks.size(), todo);
        } catch (Exception e) {
            msg(INDENT + "☹ OOPS!!! The description of a todo cannot be empty.\n");
//            throw new DukeException("");
        }
    }

    public void addDeadline(String cmd) {
        String[] div = cmd.split("/");
        String desc = div[0].split(" ", 2)[1];
        String by = div[1].split(" ", 2)[1];
        Deadline deadline = new Deadline(desc, by);
        tasks.add(tasks.size(), deadline);
    }

    public void addEvent(String cmd) {
        String[] div = cmd.split("/");
        String desc = div[0].split(" ", 2)[1];
        String at = div[1].split(" ", 2)[1];
        Event event = new Event(desc, at);
        tasks.add(tasks.size(), event);
    }

    public void list() {
        String content;
        content = INDENT + "Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            content += INDENT + (i + 1) + "." + task + "\n";
        }
        msg(content);
    }

    public void mark(String cmd) throws DukeException {
        try {
            int index = Integer.parseInt(cmd.split(" ")[1]) - 1;
            Task curr = tasks.get(index);
            if (curr.getStatusIcon().equals(" ")) {
                curr.markAsDone();
                msg(INDENT + "Nice! I've marked this task as done:\n" + INDENT + "  " + curr + "\n");
            } else {
                msg(INDENT + "This task was already done.\n" + INDENT + "  " + curr + "\n");
            }
        } catch (Exception e) {
            msg(INDENT + "☹ OOPS!!! This mark command is invalid. \n");
        }
    }

    public void unmark(String cmd) {
        try {
            int index = Integer.parseInt(cmd.split(" ")[1]) - 1;
            Task curr = tasks.get(index);
            if (curr.getStatusIcon().equals("X")) {
                curr.unmarkTask();
                msg(INDENT + "OK, I've marked this task as not done yet:\n" + INDENT + "  " + curr + "\n");
            } else {
                msg(INDENT + "This task has not been done in the first place.\n" + INDENT + "  " + curr + "\n");
            }
        } catch (Exception e) {
            msg(INDENT + "☹ OOPS!!! This unmark command is invalid. \n");
        }
    }

    public void bye() {
        msg(INDENT + "Bye. Hope to see you again soon!\n");
    }

    public static void main(String[] args) {
        Duke program = new Duke();
        program.run();
    }
}
