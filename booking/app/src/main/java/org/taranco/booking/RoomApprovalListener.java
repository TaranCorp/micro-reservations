package org.taranco.booking;

import org.taranco.booking.dto.ApprovalResponse;

public interface RoomApprovalListener {
    void approvalCompleted(ApprovalResponse approvalResponse);
}
