package kanban_atividade_poo.Kanban.atividade.Pooo.model.dto;

import kanban_atividade_poo.Kanban.atividade.Pooo.model.enums.Status;

public class GetStatusDTO {

    private Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
