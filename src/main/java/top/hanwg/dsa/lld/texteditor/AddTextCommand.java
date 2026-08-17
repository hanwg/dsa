package top.hanwg.dsa.lld.texteditor;

public class AddTextCommand extends TextEditorCommand {

    int row;
    int col;
    String text;

    public AddTextCommand(TextEditor textEditor, int row, int col, String text) {
        super(textEditor);
        this.row = row;
        this.col = col;
        this.text = text;
    }

    @Override
    public void execute() {
        if (row == textEditor.document.size()) {
            textEditor.document.add(new StringBuilder());
        }

        StringBuilder string = textEditor.document.get(row);
        string.insert(col, text);
    }

    @Override
    public void undo() {
        StringBuilder string = textEditor.document.get(row);
        string.delete(col, col + text.length());
    }
}
