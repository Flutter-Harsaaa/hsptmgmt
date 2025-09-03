// com/hsptMgmt/service/SlotService.java
package com.hsptMgmt.service;

import com.hsptMgmt.dao.SlotDAO;
import com.hsptMgmt.entity.Slot;
import java.util.List;

public class SlotService {
    private SlotDAO slotDAO;

    public SlotService(SlotDAO slotDAO) {
        this.slotDAO = slotDAO;
    }

    public Slot addSlot(Slot slot) {
        return slotDAO.save(slot);
    }

    public Slot getSlot(int id) {
        return slotDAO.findById(id);
    }

    public List<Slot> getAllSlots() {
        return slotDAO.findAll();
    }

    public void deleteSlot(int id) {
        slotDAO.delete(id);
    }
}
