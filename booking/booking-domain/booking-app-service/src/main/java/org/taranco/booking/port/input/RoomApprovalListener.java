package org.taranco.booking.port.input;

import org.taranco.booking.dto.ApprovalResponse;

public interface RoomApprovalListener {
    void approvalCompleted(ApprovalResponse approvalResponse);
}
