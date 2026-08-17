package top.hanwg.dsa.lld.texteditor;

public class DeleteTextCommand extends TextEditorCommand {

    int row;
    int startColumn;
    int length;
    String deletedString;

    DeleteTextCommand(TextEditor textEditor, int row, int startColumn, int length) {
        super(textEditor);
        this.row = row;
        this.startColumn = startColumn;
        this.length = length;
    }

    @Override
    void execute() {
        StringBuilder string = textEditor.document.get(row);
        deletedString = string.substring(startColumn, startColumn + length);
        string.delete(startColumn, startColumn + length);
    }

    @Override
    void undo() {
        StringBuilder string = textEditor.document.get(row);
        string.insert(startColumn, deletedString);
    }
}
