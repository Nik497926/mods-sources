package ru.obvilion.api.inject.permissions.luckperms;

import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.NodeType;
import net.luckperms.api.node.types.PermissionNode;
import net.luckperms.api.node.types.PrefixNode;
import net.luckperms.api.node.types.SuffixNode;
import ru.obvilion.api.inject.permissions.api.*;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LPUser implements IUser {
    private final User user;

    public LPUser(User user) {
        this.user = user;
    }

    public List<IGroup> getGroups() {
        return user.getInheritedGroups(user.getQueryOptions())
                .stream()
                .map(LPGroup::new)
                .collect(Collectors.toList());
    }

    public IGroup getPrimaryGroup() {
        return new LPGroup(user.getPrimaryGroup());
    }

    public String getPrefix() {
        IPrefix f = this.getOwnPrefix();
        if (f != null) return f.get();

        return this.user.getCachedData().getMetaData().getPrefix();
    }

    public String getSuffix() {
        ISuffix f = this.getOwnSuffix();
        if (f != null) return f.get();

        return this.user.getCachedData().getMetaData().getSuffix();
    }

    public IPrefix getPrefixObj() {
        IPrefix f = this.getOwnPrefix();
        if (f != null) return f;

        for (IGroup g : getGroups()) {
            if (g.getPrefix().get().equals(getPrefix())) {
                return g.getPrefix();
            }
        }
        return null;
    }

    public ISuffix getSuffixObj() {
        ISuffix f = this.getOwnSuffix();
        if (f != null) return f;

        for (IGroup g : getGroups()) {
            if (g.getSuffix().get().equals(getPrefix())) {
                return g.getSuffix();
            }
        }
        return null;
    }

    private List<IPrefix> getOwnPrefixes() {
        return user.getNodes(NodeType.PREFIX)
                .stream()
                .map(n -> new LPPrefix(n, this))
                .collect(Collectors.toList());
    }
    private List<ISuffix> getOwnSuffixes() {
        return user.getNodes(NodeType.SUFFIX)
                .stream()
                .map(n -> new LPSuffix(n, this))
                .collect(Collectors.toList());
    }

    public IPrefix getOwnPrefix() {
        return getOwnPrefixes()
                .stream()
                .max(Comparator.comparingInt(IPrefix::getPriority))
                .orElse(null);
    }
    public ISuffix getOwnSuffix() {
        return getOwnSuffixes()
                .stream()
                .max(Comparator.comparingInt(ISuffix::getPriority))
                .orElse(null);
    }

    public void setOwnPrefix(IPrefix to) {
        removeOwnPrefix();
        to.setOwner(this);
        user.data().add(((LPPrefix) to).node);
        save();
    }

    public void setOwnSuffix(ISuffix to) {
        removeOwnSuffix();
        to.setOwner(this);
        user.data().add(((LPSuffix) to).node);
        save();
    }

    public boolean removeOwnPrefix() {
        Collection<PrefixNode> nodes = user.getNodes(NodeType.PREFIX);
        for (PrefixNode n : nodes) {
            user.data().remove(n);
        }

        save();
        return nodes.size() > 0;
    }

    public boolean removeOwnSuffix() {
        Collection<SuffixNode> nodes = user.getNodes(NodeType.SUFFIX);
        for (SuffixNode n : nodes) {
            user.data().remove(n);
        }

        save();
        return nodes.size() > 0;
    }

    public void save() {
        LuckPermsProvider.get().getUserManager().saveUser(this.user);
    }

    public List<IPermission> getPermissions() {
        return user.getNodes(NodeType.PERMISSION)
                .stream()
                .map(pn -> new LPPermission(pn, this))
                .collect(Collectors.toList());
    }

    public IPermission getPermission(String permission) {
        return user.getNodes(NodeType.PERMISSION)
                .stream()
                .filter(pn -> pn.getPermission().equals(permission))
                .findFirst()
                .map(pn -> new LPPermission(pn, this))
                .orElse(null);
    }

    public boolean hasPermission(String permission) {
        return user.getNodes(NodeType.PERMISSION)
                .stream()
                .anyMatch(pn -> pn.getPermission().equals(permission));
    }
    public boolean hasPermission(IPermission permission) {
        return user.getNodes(NodeType.PERMISSION)
                .stream()
                .anyMatch(pn -> pn.getPermission()
                .equals(permission.get()));
    }

    public boolean addPermission(String permission) {
        if (hasPermission(permission)) return false;

        user.data().add(PermissionNode.builder(permission).build());
        save();
        return true;
    }
    public boolean addPermission(IPermission permission) {
        if (hasPermission(permission)) return false;

        user.data().add(((LPPermission) permission).getNode());
        save();
        return true;
    }

    public boolean removePermission(String permission) {
        IPermission p = getPermission(permission);
        if (p == null) return false;

        user.data().remove(((LPPermission) p).getNode());
        save();
        return true;
    }
    public boolean removePermission(IPermission permission) {
        if (!hasPermission(permission)) return false;

        user.data().remove(((LPPermission) permission).getNode());
        save();
        return true;
    }
}
