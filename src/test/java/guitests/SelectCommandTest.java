package guitests;

import org.junit.Test;

import seedu.address.model.task.ReadOnlyTask;

import static org.junit.Assert.assertEquals;

public class SelectCommandTest extends AddressBookGuiTest {


    @Test
    public void selectTask_nonEmptyList() {

        assertSelectionInvalid(10); //invalid index
        assertNoTaskSelected();

        assertSelectionSuccess(1); //first task in the list
        int taskCount = td.getTypicalTask().length;
        assertSelectionSuccess(taskCount); //last task in the list
        int middleIndex = taskCount / 2;
        assertSelectionSuccess(middleIndex); //a task in the middle of the list

        assertSelectionInvalid(taskCount + 1); //invalid index
        assertTaskSelected(middleIndex); //assert previous selection remains

        /* Testing other invalid indexes such as -1 should be done when testing the SelectCommand */
    }

    @Test
    public void selectTask_emptyList(){
        commandBox.runCommandAndConfirm("clear");
        assertListSize(0);
        assertSelectionInvalid(1); //invalid index
    }

    private void assertSelectionInvalid(int index) {
        commandBox.runCommand("select " + index);
        assertResultMessage("The task index provided is invalid.");
    }

    private void assertSelectionSuccess(int index) {
        commandBox.runCommand("select " + index);
        assertResultMessage("Selected Task #"+index);
        assertTaskSelected(index);
    }

    private void assertTaskSelected(int index) {
        assertEquals(taskListPanel.getSelectedTask().size(), 1);
        ReadOnlyTask selectedTask = taskListPanel.getSelectedTask().get(0);
        assertEquals(taskListPanel.getTask(index-1), selectedTask);
    }

    private void assertNoTaskSelected() {
        assertEquals(taskListPanel.getSelectedTask().size(), 0);
    }

}
