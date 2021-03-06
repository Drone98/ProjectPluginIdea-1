import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.project.Project;

public class FromRussianToEnglish extends AnAction {
    @Override
    public void update(AnActionEvent e){
        final Project project = e.getProject();
        final Editor editor = e.getData(CommonDataKeys.EDITOR);
        e.getPresentation().setVisible(project != null && editor != null &&
            editor.getSelectionModel().hasSelection());
    }

    @Override
    public void actionPerformed(final AnActionEvent e){
        final Editor editor = e.getRequiredData(CommonDataKeys.EDITOR);
        final Project project = e.getProject();

        final Document document = editor.getDocument();
        final SelectionModel selectionModel = editor.getSelectionModel();
        System.out.println(selectionModel.getSelectedText());

        final int start = selectionModel.getSelectionStart();
        final int end = selectionModel.getSelectionEnd();
        KeyboardMapping keyboardMapping = new KeyboardMapping();
        WriteCommandAction.runWriteCommandAction(project, () ->
                document.replaceString(start, end, keyboardMapping.changeLayout(selectionModel.getSelectedText()))
        );
        selectionModel.removeSelection();
    }
}
