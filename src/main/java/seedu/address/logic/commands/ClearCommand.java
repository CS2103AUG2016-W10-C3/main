package seedu.address.logic.commands;

import seedu.address.commons.core.EventsCenter;
import seedu.address.commons.events.ui.MinimizeRequestEvent;
import seedu.address.model.ToDo;

/**
 * Clears the SmartyDo.
 */
public class ClearCommand extends Command implements RequiresConfirm{

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";

    public ClearCommand() {}


    @Override
    public CommandResult execute() {
        assert model != null;
        model.resetData(ToDo.getEmptyAddressBook());
        undoRedoManager.resetData();
        EventsCenter.getInstance().post(new MinimizeRequestEvent());
        return new CommandResult(MESSAGE_SUCCESS);
    }


    //@@author A0135812L
    @Override
    public CommandResult prompt() {
        ConfirmCommand.AWAITINGCONFIRMATION = this;
        return prompt(COMMAND_WORD);
    }
}
