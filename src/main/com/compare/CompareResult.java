package compare;


import java.util.Collection;

public class CompareResult<T>{
    public Collection<T> toUpdate;
    public Collection<T> toSave;
    public Collection<T> toRemove;

    public CompareResult(Collection<T> toUpdate, Collection<T> toSave, Collection<T> toRemove) {
        this.toUpdate = toUpdate;
        this.toSave = toSave;
        this.toRemove = toRemove;
    }

    public Collection<T> getToUpdate() {
        return toUpdate;
    }

    public void setToUpdate(Collection<T> toUpdate) {
        this.toUpdate = toUpdate;
    }

    public Collection<T> getToSave() {
        return toSave;
    }

    public void setToSave(Collection<T> toSave) {
        this.toSave = toSave;
    }

    public Collection<T> getToRemove() {
        return toRemove;
    }

    public void setToRemove(Collection<T> toRemove) {
        this.toRemove = toRemove;
    }
}
