package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Todo;

/**
 * AddTodoCommand is a Command when the user wants to add a Todo task.
 */
public class AddTodoCommand extends Command {

    private String desc;

    /**
     * Initializes an AddTodoCommand object.
     *
     * @param desc The description of the task.
     */
    public AddTodoCommand(String desc) {
        this.desc = desc;
    }

    /**
     * Adds a Todo task to the TaskList.
     *
     * @param tasks The list of tasks.
     * @param ui The class that deals with interactions with the user.
     * @param storage The class that deals with loading and storing tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Todo todo = new Todo(desc);
        tasks.add(todo);
        return todo.toString();
    }
}
