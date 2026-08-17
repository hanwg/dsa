package top.hanwg.dsa.lld.texteditor;

public abstract class TextEditorCommand {

    TextEditor textEditor;

    public TextEditorCommand(TextEditor textEditor) {
        this.textEditor = textEditor;
    }

    abstract void execute();
    abstract void undo();
}
