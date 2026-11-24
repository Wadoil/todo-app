package org.example;

import java.util.ArrayList;
import java.util.List;

public class TodoList {
    private final List<TodoItem> items = new ArrayList<>();

    public void add(String item) {
        if (item != null) {
            item = item.trim();
            if (!item.isEmpty()) {
                items.add(new TodoItem(item));
            }
        }
    }

    public boolean remove(int index) {
        if (index >= 0 && index < items.size()) {
            items.remove(index);
            return true;
        }
        return false;
    }

    public boolean markDone(int index) {
        if (index >= 0 && index < items.size()) {
            items.get(index).markDone();
            return true;
        }
        return false;
    }

    public void clear() {
        items.clear();
    }

    public List<Integer> search(String query) {
        List<Integer> result = new ArrayList<>();
        if (query == null) {
            return result;
        }
        String q = query.toLowerCase();
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getText().toLowerCase().contains(q)) {
                result.add(i);
            }
        }
        return result;
    }

    public TodoItem getItem(int index) {
        return items.get(index);
    }

    public List<String> getAll() {
        List<String> texts = new ArrayList<>(items.size());
        for (TodoItem item : items) {
            texts.add(item.getText());
        }
        return texts;
    }

    public List<TodoItem> getAllItems() {
        return new ArrayList<>(items);
    }

    public int size() {
        return items.size();
    }
}