package seedu.address.logic.commands;

import java.util.EmptyStackException;

//@@author A0121261Y
/**
 * Reverse a undo command
 *
 */
public class RedoCommand extends Command {

    public static final String COMMAND_WORD = "redo";

    public static final String MESSAGE_SUCCESS = "Reversed your undo action successfully: ";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Reverses your last undo "
            + "action executed in SmartyDo. \n"
            + "Example: " + "add "
            + "CS2103 t;10-12-16 d;finish the project a;COM1-1-1 t/friends t/owesMoney \n"
            + "undo \n" + COMMAND_WORD;

    public static final String MESSAGE_NO_UNDO_COMMAND = "There was no undo command executed recently. ";

    Command lastCommand;

    public RedoCommand() {}

    @Override
    public CommandResult execute() {
        String lastUndoMessage;
        assert model != null;
        assert undoRedoManager != null;

        try {
            lastCommand = undoRedoManager.getRedo().pop();
            assert lastCommand instanceof Undoable;
            undoRedoManager.transferToUndo(lastCommand);
            lastUndoMessage = lastCommand.execute().feedbackToUser;
            return new CommandResult(MESSAGE_SUCCESS + System.lineSeparator() + lastUndoMessage);
        }catch (EmptyStackException ese) {
            return new CommandResult(MESSAGE_NO_UNDO_COMMAND + System.lineSeparator() + MESSAGE_USAGE);
        }
    }
}
