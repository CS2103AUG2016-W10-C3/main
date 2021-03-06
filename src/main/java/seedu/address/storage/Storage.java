package seedu.address.storage;

import seedu.address.commons.events.model.ToDoChangedEvent;
import seedu.address.commons.events.storage.DataSavingExceptionEvent;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyToDo;
import seedu.address.model.UserPrefs;

import java.io.IOException;
import java.util.Optional;

/**
 * API of the Storage component
 */
public interface Storage extends ToDoStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(UserPrefs userPrefs) throws IOException;

    @Override
    String getToDoFilePath();

    @Override
    Optional<ReadOnlyToDo> readToDo() throws DataConversionException, IOException;

    @Override
    void saveToDo(ReadOnlyToDo toDo) throws IOException;
    
    /**
     * Loads new XmlAddressBookStorage from file path.
     * @param toDo cannot be null.
     */
    void loadToDo(String filePath);

    /**
     * Saves the current version of the Address Book to the hard disk.
     *   Creates the data file if it is missing.
     * Raises {@link DataSavingExceptionEvent} if there was an error during saving.
     */
    void handleToDoChangedEvent(ToDoChangedEvent abce);
}
