package com.martintintin3.Cardboard.events;

import com.martintintin3.Cardboard.Server;
import net.minestom.server.entity.EntityType;
import net.minestom.server.entity.LivingEntity;
import net.minestom.server.entity.Player;
import net.minestom.server.entity.damage.DamageType;
import net.minestom.server.event.entity.EntityAttackEvent;
import net.minestom.server.item.ItemStack;

public class EntityAttackEventHandler {
    public static void handle(EntityAttackEvent event, Server server) {
        if(event.getEntity().getEntityType().equals(EntityType.PLAYER) && event.getTarget() instanceof LivingEntity) {
            LivingEntity target = (LivingEntity) event.getTarget();

            if(((Player) event.getEntity()).getItemInMainHand().equals(ItemStack.AIR)) {
                target.damage(DamageType.fromPlayer((Player) event.getEntity()), 1f);
            }
        }
    }
}
