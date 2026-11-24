import org.example.TodoItem;
import org.example.TodoList;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TodoListTest {

    @Test
    void addAndList() {
        TodoList t = new TodoList();
        t.add(" task1 ");
        assertEquals(1, t.size());
        assertEquals("task1", t.getAll().getFirst());
    }

    @Test
    void remove() {
        TodoList t = new TodoList();
        t.add("a");
        t.add("b");
        assertTrue(t.remove(0));
        assertEquals(1, t.size());
        assertFalse(t.remove(10));
    }

    @Test
    void addEmptyIgnored() {
        TodoList t = new TodoList();
        t.add(" ");
        assertEquals(0, t.size());
    }

    @Test
    void markDoneMarksOnlyTargetItem() {
        TodoList t = new TodoList();
        t.add("a");
        t.add("b");

        assertTrue(t.markDone(0));
        assertTrue(t.getItem(0).isDone());
        assertFalse(t.getItem(1).isDone());
    }

    @Test
    void markDoneOutOfRangeReturnsFalse() {
        TodoList t = new TodoList();
        t.add("a");
        assertFalse(t.markDone(-1));
        assertFalse(t.markDone(5));
        assertFalse(t.getItem(0).isDone());
    }

    @Test
    void clearRemovesAllItems() {
        TodoList t = new TodoList();
        t.add("a");
        t.add("b");
        t.add("c");

        t.clear();

        assertEquals(0, t.size());
        assertTrue(t.getAllItems().isEmpty());
    }

    @Test
    void clearOnEmptyListIsNoOp() {
        TodoList t = new TodoList();
        t.clear();
        assertEquals(0, t.size());
    }

    @Test
    void searchFindsSubstringCaseInsensitive() {
        TodoList t = new TodoList();
        t.add("Buy milk");
        t.add("buy bread");
        t.add("Walk dog");

        List<Integer> matches = t.search("BUY");

        assertEquals(List.of(0, 1), matches);
    }

    @Test
    void searchReturnsEmptyWhenNoMatch() {
        TodoList t = new TodoList();
        t.add("Buy milk");

        assertTrue(t.search("xyz").isEmpty());
    }

    @Test
    void searchHandlesNullAndEmpty() {
        TodoList t = new TodoList();
        t.add("a");

        assertTrue(t.search(null).isEmpty());
        // пустая подстрока содержится в любой строке
        assertEquals(List.of(0), t.search(""));
    }

    @Test
    void doneFlagPersistsAcrossListAndSearch() {
        TodoList t = new TodoList();
        t.add("task one");
        t.add("task two");

        t.markDone(1);

        List<TodoItem> all = t.getAllItems();
        assertFalse(all.get(0).isDone());
        assertTrue(all.get(1).isDone());

        List<Integer> found = t.search("task");
        assertEquals(List.of(0, 1), found);
        assertTrue(t.getItem(found.get(1)).isDone());
    }
}