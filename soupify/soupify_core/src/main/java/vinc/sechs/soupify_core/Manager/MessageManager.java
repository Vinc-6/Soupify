package vinc.sechs.soupify_core.Manager;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MessageManager {

    private final Map<UUID, UUID> lastMessage = new HashMap<>();

    public void setLastMessage(UUID sender, UUID target) {
        lastMessage.put(sender, target);
        lastMessage.put(target, sender);
    }

    public UUID getLastMessage(UUID player) {
        return lastMessage.get(player);
    }
}
