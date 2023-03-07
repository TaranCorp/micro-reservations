package org.taranco.booking;

import org.taranco.booking.dto.ApprovalResponse;

interface RoomApprovalListener {
    void approvalCompleted(ApprovalResponse approvalResponse);
}
