package ru.obvilion.api.inject.permissions.luckperms;

import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.group.Group;
import ru.obvilion.api.inject.permissions.api.IGroup;

public class LPGroup implements IGroup {
    Group group;

    public LPGroup(Group group) {
        this.group = group;
    }
    public LPGroup(String group) {
        this.group = LuckPermsProvider.get().getGroupManager().getGroup(group);
    }
}
