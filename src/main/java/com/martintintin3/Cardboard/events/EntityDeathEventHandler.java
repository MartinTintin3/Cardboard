package com.martintintin3.Cardboard.events;

import com.martintintin3.Cardboard.Server;
import net.minestom.server.entity.Entity;
import net.minestom.server.entity.EntityType;
import net.minestom.server.entity.Player;
import net.minestom.server.entity.fakeplayer.FakePlayer;
import net.minestom.server.event.entity.EntityDeathEvent;
import net.minestom.server.particle.Particle;
import net.minestom.server.particle.ParticleCreator;
import net.minestom.server.utils.time.TimeUnit;

public class EntityDeathEventHandler {
    public static void handle(EntityDeathEvent event, Server server) {
        event.getEntity().triggerStatus((byte) 60);
        server.schedulerManager.buildTask(new Runnable() {
            @Override
            public void run() {
                Entity entity = event.getEntity();

                entity.triggerStatus((byte) 60);

                if(!(entity instanceof Player) || entity instanceof FakePlayer) {
                    entity.remove();
                }
            }
        }).delay(20, TimeUnit.TICK).schedule();
    }
}
