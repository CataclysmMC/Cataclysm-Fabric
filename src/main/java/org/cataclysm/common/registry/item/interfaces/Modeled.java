package org.cataclysm.common.registry.item.interfaces;

import net.minecraft.data.client.Model;
import net.minecraft.data.client.Models;

public interface Modeled {
    default Model getModel() {
        return Models.GENERATED;
    }
}