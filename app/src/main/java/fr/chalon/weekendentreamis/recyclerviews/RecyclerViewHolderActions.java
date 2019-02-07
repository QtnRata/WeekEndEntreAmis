package fr.chalon.weekendentreamis.recyclerviews;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class RecyclerViewHolderActions {
    private Class<?> editActivityTarget;
    private Class<?> detailsActivityTarget;
    private Consumer<Long> deleteCommand;
    private Consumer<Long> selectCommand;

    public RecyclerViewHolderActions(Class<?> editActivityTarget, Class<?> detailsActivityTarget, Consumer<Long> deleteCommand)
    {
        this.editActivityTarget = editActivityTarget;
        this.detailsActivityTarget = detailsActivityTarget;
        this.deleteCommand = deleteCommand;
    }

    public RecyclerViewHolderActions(Consumer<Long> selectCommand)
    {
        this.selectCommand = selectCommand;
    }

    public Consumer<Long> getDeleteCommand()
    {
        return this.deleteCommand;
    }

    public void setDeleteCommand(Consumer<Long> command) {
        this.deleteCommand = command;
    }

    public Consumer<Long> getSelectCommand()
    {
        return this.selectCommand;
    }

    public void setSelectCommand(Consumer<Long> command) {
        this.selectCommand = command;
    }

    public Class<?> getDetailsActivityTarget() {
        return detailsActivityTarget;
    }

    public void setDetailsActivityTarget(Class<?> detailsActivityTarget) {
        this.detailsActivityTarget = detailsActivityTarget;
    }

    public Class<?> getEditActivityTarget() {
        return editActivityTarget;
    }

    public void setEditActivityTarget(Class<?> editActivityTarget) {
        this.editActivityTarget = editActivityTarget;
    }
}
