package kanban_atividade_poo.Kanban.atividade.Pooo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import kanban_atividade_poo.Kanban.atividade.Pooo.model.enums.Priority;
import kanban_atividade_poo.Kanban.atividade.Pooo.model.enums.Status;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String description;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date creationDate;

    private Status status;
    private Priority priority;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date limitDate;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Status getStatus() {
        return status;
    }

    public Priority getPriority() {
        return priority;
    }

    public Date getLimitDate() {
        return limitDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public void setLimitDate(Date limitDate) {
        this.limitDate = limitDate;
    }

    public void moveStatus() {
        Status[] values = Status.values();
        int currentIndex = this.status.ordinal();
        this.status = values[(currentIndex + 1) % values.length];
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", creationDate=" + creationDate +
                ", status=" + status +
                ", priority=" + priority +
                ", limitDate=" + limitDate +
                '}';
    }
}
