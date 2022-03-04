package ru.obvilion.api.inject.permissions.luckperms;

import net.luckperms.api.model.user.User;
import net.luckperms.api.node.NodeType;
import ru.obvilion.api.inject.permissions.api.IGroup;
import ru.obvilion.api.inject.permissions.api.IPrefix;
import ru.obvilion.api.inject.permissions.api.ISuffix;
import ru.obvilion.api.inject.permissions.api.IUser;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LPUser implements IUser {
    private final User user;

    public LPUser(User user) {
        this.user = user;
    }

    public List<IGroup> getGroups() {
        return user.getInheritedGroups(user.getQueryOptions()).stream().map(LPGroup::new).collect(Collectors.toList());
    }

    public IGroup getPrimaryGroup() {
        return new LPGroup(user.getPrimaryGroup());
    }

    public List<IPrefix> getOwnPrefixes() {
        return user.getNodes(NodeType.PREFIX).stream().map(LPPrefix::new).collect(Collectors.toList());
    }
    public List<ISuffix> getOwnSuffixes() {
        return user.getNodes(NodeType.SUFFIX).stream().map(LPSuffix::new).collect(Collectors.toList());
    }

    public IPrefix getOwnPrefix() {
        return getOwnPrefixes().stream().max(Comparator.comparingInt(IPrefix::getPriority)).orElse(null);
    }
    public ISuffix getOwnSuffix() {
        return getOwnSuffixes().stream().max(Comparator.comparingInt(ISuffix::getPriority)).orElse(null);
    }

    @Override
    public IPrefix editOwnPrefix(IPrefix from, String prefix, long expiry) {
        return null;
    }

    @Override
    public ISuffix editOwnSuffix(ISuffix from, String prefix, long expiry) {
        return null;
    }

    @Override
    public void removeOwnPrefix(IPrefix prefix) {

    }

    @Override
    public void removeOwnSuffix(ISuffix suffix) {

    }

    @Override
    public void save() {

    }

    @Override
    public boolean hasPermission(String permission) {
        return false;
    }

    @Override
    public boolean addPermission(String permission) {
        return false;
    }

    @Override
    public boolean addPermission(String permission, long expiry) {
        return false;
    }

    @Override
    public void removePermission(String permission) {

    }
}
