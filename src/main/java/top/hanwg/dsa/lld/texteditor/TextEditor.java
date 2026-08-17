package top.hanwg.dsa.lld.texteditor;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TextEditor {
    List<StringBuilder> document = new ArrayList<>();
    Stack<TextEditorCommand> undo = new Stack<>();
    Stack<TextEditorCommand> redo = new Stack<>();

    void addText(int row, int column, String text) {
        AddTextCommand command = new AddTextCommand(this, row, column, text);

        undo.add(command);
        command.execute();

        redo.clear();
    }

    void deleteText(int row, int startColumn, int length) {
        DeleteTextCommand command = new DeleteTextCommand(this, row, startColumn, length);

        undo.add(command);
        command.execute();

        redo.clear();
    }

    void undo() {
        if (undo.empty()) {
            return;
        }

        TextEditorCommand command = undo.pop();
        command.undo();

        redo.push(command);
    }

    void redo() {
        if (redo.empty()) {
            return;
        }

        TextEditorCommand command = redo.pop();
        command.execute();
    }

    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        editor.addText(0, 0, "hello");
        editor.deleteText(0, 2, 2);
        editor.undo();
        /*editor.addText(0, 3, "-");
        editor.undo();
        editor.undo();
        editor.redo();*/
        System.out.println();
    }
}
