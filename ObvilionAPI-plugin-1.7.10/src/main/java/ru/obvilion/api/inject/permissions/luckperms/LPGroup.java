package ru.obvilion.api.inject.permissions.luckperms;

import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.node.NodeType;
import net.luckperms.api.node.types.PermissionNode;
import net.luckperms.api.node.types.PrefixNode;
import net.luckperms.api.node.types.SuffixNode;
import ru.obvilion.api.inject.permissions.api.IGroup;
import ru.obvilion.api.inject.permissions.api.IPermission;
import ru.obvilion.api.inject.permissions.api.IPrefix;
import ru.obvilion.api.inject.permissions.api.ISuffix;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LPGroup implements IGroup {
    public Group group;

    public LPGroup(Group group) {
        this.group = group;
    }

    public LPGroup(String group) {
        this.group = LuckPermsProvider.get().getGroupManager().getGroup(group);
    }

    @Override
    public IPrefix getPrefix() {
        return group.getNodes(NodeType.PREFIX).stream()
                .map(n -> new LPPrefix(n, this))
                .max(Comparator.comparingInt(IPrefix::getPriority))
                .orElse(null);
    }

    @Override
    public ISuffix getSuffix() {
        return group.getNodes(NodeType.SUFFIX).stream()
                .map(n -> new LPSuffix(n, this))
                .max(Comparator.comparingInt(ISuffix::getPriority))
                .orElse(null);
    }

    @Override
    public void setPrefix(IPrefix prefix) {
        removePrefix();
        prefix.setOwner(this);

        group.data().add(((LPPrefix) prefix).node);
        save();
    }

    @Override
    public void setSuffix(ISuffix suffix) {
        removeSuffix();
        suffix.setOwner(this);

        group.data().add(((LPSuffix) suffix).node);
        save();
    }

    @Override
    public boolean removePrefix() {
        Collection<PrefixNode> nodes = group.getNodes(NodeType.PREFIX);
        for (PrefixNode n : nodes) {
            group.data().remove(n);
        }

        save();
        return nodes.size() > 0;
    }

    @Override
    public boolean removeSuffix() {
        Collection<SuffixNode> nodes = group.getNodes(NodeType.SUFFIX);
        for (SuffixNode n : nodes) {
            group.data().remove(n);
        }

        save();
        return nodes.size() > 0;
    }

    @Override
    public List<IPermission> getPermissions() {
        return group.getNodes(NodeType.PERMISSION)
                .stream()
                .map(pn -> new LPPermission(pn, this))
                .collect(Collectors.toList());
    }

    @Override
    public IPermission getPermission(String permission) {
        return group.getNodes(NodeType.PERMISSION)
                .stream()
                .filter(pn -> pn.getPermission().equals(permission))
                .findFirst()
                .map(pn -> new LPPermission(pn, this))
                .orElse(null);
    }

    @Override
    public boolean hasPermission(String permission) {
        return group.getNodes(NodeType.PERMISSION)
                .stream()
                .anyMatch(pn -> pn.getPermission().equals(permission));
    }

    @Override
    public boolean hasPermission(IPermission permission) {
        return group.getNodes(NodeType.PERMISSION)
                .stream()
                .anyMatch(pn -> pn.getPermission()
                .equals(permission.get()));
    }

    @Override
    public boolean addPermission(String permission) {
        if (hasPermission(permission)) return false;

        group.data().add(PermissionNode.builder(permission).build());
        save();
        return true;
    }

    @Override
    public boolean addPermission(IPermission permission) {
        if (hasPermission(permission)) return false;

        permission.setOwner(this);
        group.data().add(((LPPermission) permission).getNode());
        save();
        return true;
    }

    @Override
    public boolean removePermission(String permission) {
        IPermission p = getPermission(permission);
        if (p == null) return false;

        group.data().remove(((LPPermission) p).getNode());
        save();
        return true;
    }

    @Override
    public boolean removePermission(IPermission permission) {
        if (!hasPermission(permission)) return false;

        permission.setOwner(null);
        group.data().remove(((LPPermission) permission).getNode());
        save();
        return true;
    }

    @Override
    public void save() {
        LuckPermsProvider.get().getGroupManager().saveGroup(group);
    }
}
